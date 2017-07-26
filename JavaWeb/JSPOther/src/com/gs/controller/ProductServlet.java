package com.gs.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.gs.bean.Product;
import com.gs.common.WebUtil;
import com.gs.service.ProductService;
import com.gs.service.ProductServiceImpl;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 3428951414322189673L;
	
	private ProductService productService;
	
	public ProductServlet() {
		this.productService = new ProductServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		
		if (method.equals("all")) {
			queryAll(req, resp);
		} else if (method.equals("edit_page")) {
			toEditPage(req, resp);
		} else if (method.equals("edit")) {
			edit(req, resp);
		} else if (method.equals("add_page")) {
			toAddPage(req, resp);
		} else if (method.equals("add")) {
			add(req, resp);
		}
	}

	private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = productService.queryAll();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/products.jsp").forward(req, resp);
	}
	
	private void toEditPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String idStr = req.getParameter("id");
		if (idStr != null) {
			try {
				int id = Integer.valueOf(idStr);
				Product p = productService.queryById(id);
				req.setAttribute("product", p);
				req.getRequestDispatcher("/product_edit.jsp").forward(req, resp);
			} catch (NumberFormatException e) {
				resp.sendRedirect("all");
			}
		} else { 
			resp.sendRedirect("all");
		}
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		String priceStr = req.getParameter("price");
		String des = req.getParameter("des");
		float price = 0;
		if (priceStr != null) {
			try {
				price = Float.valueOf(priceStr);
			} catch (NumberFormatException e) {
				
			}
		}
		Product product = new Product();
		product.setId(Integer.valueOf(idStr));
		product.setName(name);
		product.setPrice(price);
		product.setDes(des);
		productService.update(product);
		resp.sendRedirect("all");
	}
	
	private void toAddPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/product_add.jsp").forward(req, resp);
	}
	
	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/**
		 * 如果需要把由页面传递过来的文件数据获取到，则需要借助另外两个jar包，commons-io, commons-fileupload
		 * 
		 * commons-io包包含有一些封装io操作
		 * 
		 * commons-fileupload封装了文件上传所需要的功能
		 * 
		 * ServletFileUpload类是commons-fileupload包提供的文件上传类
		 * 
		 * 对于commons-fileupload来说，jsp页面中的每一个input,select, checkbox, radio都对应一个FileItem
		 */
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			// 表示由jsp页面post的数据包含有文件内容 
			FileItemFactory fileItemFactory =  new DiskFileItemFactory(); // fileItemFacotry对象用于创建与表单中input对应的FileItem对象
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			Product product = new Product();
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(req);// 去解析request请求，把每一个FileItem获取到
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) { // 判断FileItem是否为普通的表单字段
						String fieldName = fileItem.getFieldName();// 获取普通表单字段的name值
						String fieldValue = fileItem.getString();// 获取普通表单字段的value值
						if (fieldName.equals("name")) {
							product.setName(fieldValue);
						} else if (fieldName.equals("price")) {
							float price = 0;
							try {
							price = Float.valueOf(fieldValue);
							} catch (NumberFormatException e) {
							}
							product.setPrice(price);
						} else if (fieldName.equals("des")) {
							product.setDes(fieldValue);
						}
					} else { // 否则为文件字段
						String fileName = fileItem.getName();// 获取文件的名称
						// 需要获取图片数据，把图片保存到本地服务器的某个目录里，并把路径保存到数据库
						// 1、获取图片数据
						InputStream in = fileItem.getInputStream(); // 获取文件输入流
						// 2、把图片数据保存到服务器的某个目录下
						String upload = WebUtil.mkUpload(req); // 创建保存上传的文件的目录
						// new File(upload  + "/" + fileItem.getName())   上传的文件目录 + / + 文件名
						FileUtils.copyInputStreamToFile(in, new File(upload  + "/" + fileName));
						// 3、把文件的路径保存到数据库
						product.setImage(WebUtil.DEFAULT_UPLOADS + "/" + fileName);
					}
				}
				productService.add(product);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} 
		} else {
			// 如果没有上传文件，则不需要处理文件上传
		}
	}

}
