package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.model.PageBean;
import com.my.service.BoarderService;
import com.my.vo.Board;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoarderService boarderService = new BoarderService();
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(request.getServletPath());
		System.out.println(request.getPathInfo()); // servlet의 url-pattern에서 /board/* 를 지정해야 한다.
		
		String pathInfo = request.getPathInfo();
		if("/write".equals(pathInfo)) {
			//글쓰기
			
		}else if("/reply".equals(pathInfo)) {
			//답글쓰기
			
		}else if("/list".equals(pathInfo)) {
			// 게시물 목록보기
			String currentPage = request.getParameter("currentPage");
			int defaultPage = 1;
			if(!currentPage.equals("")) {
				defaultPage = Integer.parseInt(currentPage);
			}
//			List<Board> list;
			try {
//				list = boarderService.findAll(defaultPage);
//				request.setAttribute("list", list);
				// request에 setAttribute 할 요소들이 너무 많다. 어떻게 해결해야 할까?
				// -> 페이지에 필요한 정보를 담은 객체를 만들자!
				PageBean pageBean = boarderService.findAll(defaultPage);
				String url = request.getServletPath() + request.getPathInfo();
				pageBean.setUrl(url);
				System.out.println(pageBean);
				request.setAttribute("pageBean", pageBean);
				String servletPath = "/jsp/boardList.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
				dispatcher.forward(request, response);
			} catch (FindException e) {
				e.printStackTrace();
			}
			
		}
		//		String boardTitle = "test 1";
//		String boardWriter = "writer1";
//		String boardContent = "content1";
//		
//		Board board = new Board(boardTitle, boardWriter, boardContent);
//			boarderService.write(board);
		
	}
}
