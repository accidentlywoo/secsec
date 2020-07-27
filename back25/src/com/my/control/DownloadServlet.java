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
		// 요청 전달 데이터 얻기
		String name = req.getParameter("name");
		
		// 다운로드할 파일의 실제 경로 얻기
		String realPath;
		ServletContext sc = this.getServletContext();
		realPath = sc.getRealPath("files\\"+name);
		System.out.println(realPath);
		
		//응답형식 지정 : img파일을 위한 모든 경우를 위한 기본값.
		res.setContentType("application/octet-stream; charset=UTF-8");
		// 응답헤더 설정 : Download할 파일이름 결정
		res.setHeader("Content-Disposition", "attachment; filename=\""+name+"\"");
		
		ServletOutputStream sos = res.getOutputStream();
		com.my.model.Download download = new com.my.model.Download(realPath, sos);
		// POJO를 이용한 구현
		
	}
}