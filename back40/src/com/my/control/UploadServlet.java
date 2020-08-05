package com.my.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		ServletInputStream sis = req.getInputStream();
//		
//		Scanner scanner = new Scanner(sis, "UTF-8");
//		
//		while (scanner.hasNext()) {
//			System.out.println(scanner.next());
//		}
//		scanner.close();
//		sis.close();
		String saveDirectory = this.getServletContext().getRealPath("files");
		int maxPostSize = 10*1024;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(req, saveDirectory,maxPostSize, encoding, policy);
		String r = mr.getParameter("t");
		
		req.setAttribute("files", new File(saveDirectory).listFiles());
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/files.jsp");
		dispatcher.forward(req, res);
	}
}
