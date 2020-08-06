package com.my.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/") // .html, .jpg, .css등 static 파일 경로도 타게된다.
//@WebServlet("*.do")
//@WebServlet("/*.do") // 오류발생
//@WebServlet(urlPatterns = {"/login", "/logout"})
public class DispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Controller controller =null;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in DispatcherServlet");
		String servletPath = request.getServletPath();

//		if("/login".equals(servletPath)) {
//			ServletContext sc = this.getServletContext();
//			String customerEnvFileName = this.getInitParameter("customerEnv");
//		}else if("/logout".equals(servletPath)) { // ...?
//		}
		
		// 특정 프로퍼티파일(controller.properties)에서 프로퍼티명(서블릿패스)에 해당하는 프로퍼티값(클래스이름) 찾기
		// /login=com.my.control.LoginController
		// /logout=com.my.control.LogoutController
		// :
		Properties env = new Properties();
		env.load(new FileInputStream(
				getServletContext().getRealPath("controller.properties"))
			);
		
		String controllerClassName = env.getProperty(servletPath);
		//프로퍼티값(클래스 이름) 클래스 찾아 JVM에 로드
		try {
			Class clazz = Class.forName(controllerClassName);
			// 객체 생성
			Object obj = clazz.newInstance(); // 성공 : public 매개변수없는 생성자존재
			// 메서드 호출 방법1
//			controller = (Controller) obj;
//			if(controller instanceof LoginController) {
//				ServletContext sc = this.getServletContext();
//				String customerEnvFileName = sc.getInitParameter("customerEnv");
//				String realPath = request.getRealPath(customerEnvFileName);
//				((LoginController) controller).setRealPath(realPath);
//			}
			
//			controller.execute(request, resp);
			// 메서드 호출 방법2
			java.lang.reflect.Method method = clazz.getMethod("execute", HttpServletRequest.class, HttpServletResponse.class );
			System.out.println(method.getName());
			controller = (Controller)obj;
			if(controller instanceof LoginController) {
				ServletContext sc =this.getServletContext();
				String customerEnvFileName = sc.getInitParameter("customerEnv");
				String realPath = request.getRealPath(customerEnvFileName);
				((LoginController) controller).setRealPath(realPath);
			}
			
				String forwardServletPath = (String) (method.invoke(obj, request, resp));
				if(!"".equals(forwardServletPath)) {
					RequestDispatcher rd = request.getRequestDispatcher(forwardServletPath);
					rd.forward(request, resp);
				}
//			}
			
//			String forwardServletPath = controller.excute(request, resp);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
//		if (!"".equals(forwardServletPath)) {
//			RequestDispatcher reqdp = request.getRequestDispatcher(servletPath);
//			reqdp.forward(request, resp);
//		}
	}
}
