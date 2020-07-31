package com.my.service;

import java.util.List;

import com.my.dao.BoardDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.model.PageBean;
import com.my.vo.Board;

public class BoarderService {
	private BoardDAO boardDAO;
	
	public BoarderService() {
		boardDAO = new BoardDAO();
	}
	public void write(Board board) throws AddException {
		if(board.getParent_no() != 0) {
			throw new AddException("부모 글번호가 필요없습니다.");
		}
		boardDAO.insert(board);
	}
	public void reply(Board board) throws AddException{
		if(board.getParent_no() == 0) {
			throw new AddException("부모 글번호가 없습니다.");
		}
		boardDAO.insert(board);
	}
	public PageBean findAll(int page) throws FindException{
		if(page < 1) {
			page = 1;
			throw new FindException(page + " 페이지가 존재하지 않습니다.");
		}
		PageBean pageBean = new PageBean(page);
		pageBean.setList(boardDAO.selectAll(pageBean.getStartRow(), pageBean.getEndRow()));
		int rowCnt = boardDAO.selectCount(); // 게시판 총 수
		int totalPage = 0;
		if(rowCnt % PageBean.CNT_PER_PAGE > 0) {
			 totalPage =  rowCnt / PageBean.CNT_PER_PAGE+ 1;
		}else {
			 totalPage =  rowCnt / PageBean.CNT_PER_PAGE;
		}
		 // 총페이지 수
		pageBean.setTotalPage(totalPage);
		
		// ~
		pageBean.setStartPage(page);
		pageBean.setEndPage(totalPage);
		
		return pageBean;
	}
}
