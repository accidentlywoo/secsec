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
		
		// ���� �б�
		FileInputStream fis = null;
		fis = new FileInputStream(realPath);
		// ���� ���� �����ϱ�
//		PrintWriter out = res.getWriter(); -> �̹��������� ���� �� ����.
		ServletOutputStream sos = res.getOutputStream();
		
		// �ѹ���Ʈ�� �б�!
//		int readValue = -1;
//		while((readValue = fis.read()) != -1) {
//			sos.write(readValue);
//		}
		byte[] bArr = new byte[1024];
		int readCnt = -1; // ���� ����Ʈ ��
		while ((readCnt = fis.read(bArr)) != -1) {
			sos.write(bArr, 0, readCnt);
		}
		fis.close();
		sos.close();
	}
}