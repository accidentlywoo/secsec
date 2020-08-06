package com.my.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class UploadController implements Controller{
	private static final long serialVersionUID = 1L;
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String saveDirectory = "";
//				this.getServletContext().getRealPath("files");
		int maxPostSize = 10*1024;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request, saveDirectory,maxPostSize, encoding, policy);
		String r = mr.getParameter("t");
		
		request.setAttribute("files", new File(saveDirectory).listFiles());
		return "/jsp/files.jsp";
	}
}
