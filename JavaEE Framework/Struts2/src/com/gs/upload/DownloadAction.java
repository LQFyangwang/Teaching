package com.gs.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.gs.common.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {

	private static final long serialVersionUID = -4162434808712812975L;
	
	private String fileName; // 需要下载的文件的名称是什么，如果指定了此名称，则客户端下载后以此文件名为名称
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() { // 以客户端为中心，客户端要接收一个文件，则使用InputStream
		this.fileName = "a.jpg";
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(Constants.UPLOAD_PATH + "/1.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	
	public String execute() {
//		HttpServletResponse resp = ServletActionContext.getResponse();
//		File file = new File( Constants.UPLOAD_PATH + "/a.txt");
//		resp.setHeader("Content-disposition", "attachment; filename=" + file.getName());
//		try {
//			ServletOutputStream out = resp.getOutputStream();
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
//			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//			String line = null;
//			while((line = reader.readLine()) != null) {
//				writer.write(line);
//			}
//			writer.flush();
//			writer.close();
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return SUCCESS;
	}

}
