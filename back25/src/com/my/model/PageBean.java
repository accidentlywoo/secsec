package com.my.model;

import java.util.List;

import com.my.vo.Board;

public class PageBean {
	public static final int CNT_PER_PAGE = 3; // 페이지당 보여줄 목록수
	public static final int CNT_PER_PAGEGROUP = 4; // 페이지당 보여줄 페이지그룹
	private String url; // 링크 클릭시 이동할 URL
	private int currentPage; // 현재 페이지
	private int startRow; // 페이지당 보여줄 시작행 
	private int endRow; // 페이지당 보여줄 끝행
	private int totalPage; // 총 페이지
	private List< Board> list; // 
	private int startPage; //페이지 그룹의 시작 페이지
	private int endPage; //페이지 그룹의 끝 페이지
	public PageBean() {}// 자바빈 규칙 1. 기본 생성자
	public PageBean(int currentPage) {
		this.currentPage = currentPage;
		this.startRow = (currentPage -1)*CNT_PER_PAGE +1;
		this.endRow = startRow+2;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<Board> getList() {
		return list;
	}
	public void setList(List<Board> list) {
		this.list = list;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "PageBean [url=" + url + ", currentPage=" + currentPage + ", startRow=" + startRow + ", endRow=" + endRow
				+ ", totalPage=" + totalPage + ", list=" + list + ", startPage=" + startPage + ", endPage=" + endPage
				+ "]";
	}
}
