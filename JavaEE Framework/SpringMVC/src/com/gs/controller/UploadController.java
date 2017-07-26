package com.gs.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@RequestMapping("up_page")
	public String upPage() {
		return "upload/upload"; // 此时返回的字符串，去找到相应的页面
	}
	
	@RequestMapping(value = "up", method = RequestMethod.POST)
	public void up(MultipartFile file) { // MultipartFile接收到上传的文件的数据
		System.out.println(file.getName());
		try {
			String uploadPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") + "/uploads";
			file.transferTo(new File(uploadPath + file.getName()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}

}
