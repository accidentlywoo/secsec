package com.my.control;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.model.PageBean;
import com.my.service.BoarderService;
import com.my.vo.Board;

public class BoardController implements Controller {
	private static final long serialVersionUID = 1L;
	private BoarderService boarderService = new BoarderService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());
		System.out.println(request.getServletPath());
		System.out.println(request.getPathInfo());
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pathInfo = request.getPathInfo();
		String id = (String) session.getAttribute("loginInfo");
		if ("/write".equals(pathInfo)) {
			// 글쓰기
			String title = request.getParameter("board_title");
			String content = request.getParameter("board_content");
			Board board = new Board(title, id, content);
			try {
				boarderService.write(board);
				return "/success.jsp";
			} catch (AddException e) {
				return "/fail.jsp";
			}
		} else if ("/detail".equals(pathInfo)) {
			// 상세보기
			int boardNo = Integer.parseInt(request.getParameter("board_no"));
			try {
				request.setAttribute("board", boarderService.findByNo(boardNo).orElseGet(Board::new));
				return "/jsp/boardDetail.jsp";
			} catch (FindException e) {
				return "/fail.jsp";
			}
		} else if ("/reply".equals(pathInfo)) {
			// 답글쓰기
			return "/fail.jsp";
		} else if ("/list".equals(pathInfo)) {
			// 게시물 목록보기
			String currentPage = request.getParameter("currentPage");
			int defaultPage = 1;
			if (!currentPage.equals("")) {
				defaultPage = Integer.parseInt(currentPage);
			}
			try {
				PageBean pageBean = boarderService.findAll(defaultPage);
				String url = request.getServletPath() + request.getPathInfo();
				pageBean.setUrl(url);
				System.out.println(pageBean);
				request.setAttribute("pageBean", pageBean);
				return "/jsp/boardList.jsp";
			} catch (FindException e) {
				return "/fail.jsp";
			}
		}
		return "/fail.jsp";// 잘못된 요청
	}
}
