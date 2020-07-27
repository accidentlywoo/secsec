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
		
		// 파일 읽기
		FileInputStream fis = null;
		fis = new FileInputStream(realPath);
		// 파일 내용 응답하기
//		PrintWriter out = res.getWriter(); -> 이미지파일을 읽을 수 없다.
		ServletOutputStream sos = res.getOutputStream();
		
		// 한바이트씩 읽기!
//		int readValue = -1;
//		while((readValue = fis.read()) != -1) {
//			sos.write(readValue);
//		}
		byte[] bArr = new byte[1024];
		int readCnt = -1; // 읽은 바이트 수
		while ((readCnt = fis.read(bArr)) != -1) {
			sos.write(bArr, 0, readCnt);
		}
		fis.close();
		sos.close();
	}
}