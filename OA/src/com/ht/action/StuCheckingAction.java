package com.ht.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import com.ht.bean.Stu;
import com.ht.bean.StuChecking;
import com.ht.bean.info.StuCheckingInfo;
import com.ht.common.Constants;
import com.ht.common.DateUtil;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.ExcelUtil;
import com.ht.common.web.ExportExcel;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.StuCheckingService;

/**
 * 学生考勤管理的Action
 * @author Administrator
 *
 */
public class StuCheckingAction extends BaseAction {

	private static final long serialVersionUID = 9058381704880956099L;
	
	private StuCheckingService scs;
	private AuthorityRoleService authorityRoleService;
	private List<StuChecking> rows;
	private long total;
	private String gradeId;
	private String month;
	private ControllerResult result;
	
	private HttpSession session;
	
	private int number; // 标识下载文件
	private String fileName; // 文件名
	private File file; // 用来保存上传的文件，file和表单file的name保持一致
	private String fileFileName; // 用来保存上传的文件名，表单的file+FileName

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public InputStream getInputStream() throws Exception {
		FileInputStream inputStream = null;
		if (number == 1) {
			fileName = "stuChecking.xls";
			inputStream = new FileInputStream(new File(Constants.PATH_EXCEL + Constants.CHECKING_NAME));
		}
		return inputStream;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setScs(StuCheckingService scs) {
		this.scs = scs;
	}

	public List<StuChecking> getRows() {
		return rows;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}

	public String gradeByPager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuCheckingAction.class.getName() + ".gradeByPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<StuChecking> pager = new Pager4EasyUI<StuChecking>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = scs.queryPagerByGradeId(pager, gradeId, Integer.valueOf(month));
			rows = pager.getRows();
			if (rows.size() > 0) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("该班级" + month + "月份没有考勤记录，请重新选择班级或月份");
			}
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "gradeByPager";
	}
	
	public String pagerByStu() {
		session = req.getSession();
		Stu stu = (Stu) session.getAttribute("stu");
		String gradeId = null;
		if (stu != null) {
			gradeId = stu.getGradeId();
			if (month == null || month.equals("")) {
				month = String.valueOf(DateUtil.getDateMonth());
			}
		}
		
		Pager4EasyUI<StuChecking> pager = new Pager4EasyUI<StuChecking>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = scs.queryPagerByGradeId(pager, gradeId, Integer.valueOf(month));
		rows = pager.getRows();
		if (rows != null && rows.size() > 0) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("该班级" + month + "月份没有考勤记录，请重新选择月份");
		}
		total = pager.getTotal();
		return "pagerByStu";
	}
	
	public String exportData() throws Exception {
		session = req.getSession();
		Stu stu = (Stu) session.getAttribute("stu");
		String gradeId = null;
		if (stu != null) {
			gradeId = stu.getGradeId();
			if (month == null || month.equals("")) {
				month = String.valueOf(DateUtil.getDateMonth());
			}
		}
		
		Pager4EasyUI<StuChecking> pager = new Pager4EasyUI<StuChecking>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = scs.queryPagerByGradeId(pager, gradeId, Integer.valueOf(month));
		rows = pager.getRows();
		if (rows != null && rows.size() > 0) {
			Map<String,String> titleMap = new LinkedHashMap<String,String>();
			titleMap.put("stuId", "学生编号");
			titleMap.put("stuName", "学生姓名");
			titleMap.put("checkingDay", "考勤日期");
			titleMap.put("checking1", "上午");
			titleMap.put("checking2", "下午");
			titleMap.put("checking3", "晚上");
			String sheetName = "学生考勤信息";
			File file = new File(Constants.PATH_EXCEL);
			if (!file.exists()) {
				file.mkdir();
			}
			ExportExcel.excelExport(setCheckingInfo(rows), titleMap, sheetName, "checking", Constants.PATH_EXCEL);
			result = ControllerResult.setSuccessResult("导出成功");
		} else {
			result = ControllerResult.setFailResult("导出失败");
		}
		return "exportData";
	}
	
	public String importData() {
		mkUpload(); // 调用创建上传文件的文件夹
		if (file != null) { // 表示用户有选择文件
			fileFileName = "stuChecking.xls";
			String path = Constants.PATH_UPLOAD + "\\" + fileFileName;
//			FileUtil.saveFile(file, Constants.PATH_UPLOAD + "/" + fileFileName); // 将文件保存到服务器
			try {
				FileUtils.copyFile(file, new File(path)); // 保存文件
				@SuppressWarnings("unchecked")
				List<StuCheckingInfo> scis = ExcelUtil.excleIn(StuCheckingInfo.class, path);
				for (StuCheckingInfo sci : scis) {
					StuChecking sc = setStuChecking(sci);
					scs.save(sc);
				}
				result = ControllerResult.setSuccessResult("成功导入");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else {
			result = ControllerResult.setFailResult("文件上传失败，请选择你要上传的文件");
		}
		return "importData";
	}
	
	private List<StuCheckingInfo> setCheckingInfo(List<StuChecking> scs) {
		List<StuCheckingInfo> scis = new ArrayList<StuCheckingInfo>();
		for (StuChecking sc : scs) {
			StuCheckingInfo si = new StuCheckingInfo();
			si.setStuId(sc.getStu().getStuId());
			si.setStuName(sc.getStu().getName());
			si.setCheckingDay(sc.getCheckingDay());
			si.setChecking1(sc.getChecking1());
			si.setChecking2(sc.getChecking2());
			si.setChecking3(sc.getChecking3());
			scis.add(si);
		}
		return scis;
	}
	
	private StuChecking setStuChecking(StuCheckingInfo sci) {
		StuChecking sc = new StuChecking();
		Stu stu = new Stu();
		stu.setStuId(sci.getStuId());
		sc.setStu(stu);
		sc.setCheckingDay(sci.getCheckingDay());
		sc.setChecking1(sci.getChecking1());
		sc.setChecking2(sci.getChecking2());
		sc.setChecking3(sci.getChecking3());
		return sc;
	}
	
	/**
	 * 用于新建文件夹来接收上传的文件
	 */
	private void mkUpload() {
		File file = new File(Constants.PATH_UPLOAD); // 根据这个路径来创建一个文件夹
		if (!file.exists()) { // 判断文件夹是否存在
			file.mkdir(); // 创建文件夹，用于存放上传的文件
		}
	}
	
	
	
	
}
