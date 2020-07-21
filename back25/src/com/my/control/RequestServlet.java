package com.my.control;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * service()를 사용하면 메소드를 구분해서 사용하지 않을 수 있다.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("request.getMethod()= " + req.getMethod());
		System.out.println("request.getRequestURL()= "+req.getRequestURL()); //http://IP/ContextPath/ServletPath
		System.out.println("request.getRequestURI()= "+req.getRequestURI());
		System.out.println("request.getServerName()= "+req.getServerName());
		System.out.println("request.getServletPath()= "+req.getServletPath());
		System.out.println("request.getContextPath()= "+req.getContextPath()); // 현재 배포된 프로젝트 이름 -> /back25
		
		System.out.println("=================");
		
		Enumeration< String> names = req.getHeaderNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name+" = "+req.getHeader(name)); // user-agent
		}
		System.out.println("=================");
		System.out.println("request.getContentLength()= "+ req.getContentLength()); // -1 서버로 전송할 데이터가 없는 GET 방식
		
//		req.setCharacterEncoding(); Body가없는 GET방식에서 Body의 인코딩을 설정하는것은 의미가 없다.
//		req.setParameter() // Client에서 전달받은 Parameter는 문자열타입으로 세팅이된다. 수정관련 메소드를 제공하지 않는다. getter메소드만 제공한다.
//		req.removeParameter();
		String n1 =  req.getParameter("n1");
		req.setAttribute("n1","v1"); // upcasting
		req.setAttribute("n2",2); // void:setAttribute(String, Object); antoboxing : new Integer(2)
		
		String n1Value = (String) req.getAttribute("n1");
		int n2Value = (Integer) req.getAttribute("n2");// Object -> Integer : 다운캐스팅 Integer -> int : auto unboxing
		
		req.removeAttribute("n1");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
	}
    
	
}
