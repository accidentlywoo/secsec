package com.my.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ��û ���� ������ ���
		String name = req.getParameter("name");
		
		// �ٿ�ε��� ������ ���� ��� ���
		String realPath;
		ServletContext sc = this.getServletContext();
		realPath = sc.getRealPath("files\\"+name);
		System.out.println(realPath);
		
		//�������� ���� : img������ ���� ��� ��츦 ���� �⺻��.
		res.setContentType("application/octet-stream; charset=UTF-8");
		// ������� ���� : Download�� �����̸� ����
		res.setHeader("Content-Disposition", "attachment; filename=\""+name+"\"");
		
		ServletOutputStream sos = res.getOutputStream();
		com.my.model.Download download = new com.my.model.Download(realPath, sos);
		// POJO�� �̿��� ����
		
	}
}