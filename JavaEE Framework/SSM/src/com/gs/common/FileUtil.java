package com.gs.common;

import java.io.File;

import javax.servlet.http.HttpSession;

public class FileUtil {
	public static String uploadPath(HttpSession session, String subDir) {
        String rootPath = session.getServletContext().getRealPath("/");
        File uploads = new File(rootPath, "uploads");
        if (!uploads.exists()) {
            uploads.mkdir();
        }
        File uploadDir = new File(uploads, subDir);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        return uploadDir.getAbsolutePath();
    }

    public static String uploadFilePath(File file) {
        String path = file.getAbsolutePath();
        return path.substring(path.indexOf("uploads"));
    }
}
