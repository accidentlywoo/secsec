package com.my.vo;

import java.util.Date;

public class Board {
	private long board_no;
	private long parent_no;
	private String board_title;
	private String board_writer;
//	private LocalDateTime board_dt;
	private Date board_dt;
	private String board_content;
	// sudo column 필드 추가하기
	private int level;
	public Board() {}
	
	public Board(long board_no, long parent_no, String board_title, String board_writer, Date board_dt,
			String board_content) {
		super();
		this.board_no = board_no;
		this.parent_no = parent_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_dt = board_dt;
		this.board_content = board_content;
	}

	public Board(long board_no, long parent_no, String board_title, String board_writer, Date board_dt,
			String board_content, int level) {
		this.board_no = board_no;
		this.parent_no = parent_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_dt = board_dt;
		this.board_content = board_content;
		this.level = level;
	}
	public Board(String board_title, String board_writer, String board_content) {
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
	}
	public Board(long parent_no, String board_title, String board_writer, String board_content) {
		this.parent_no = parent_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
	}
	public long getBoard_no() {
		return board_no;
	}
	public void setBoard_no(long board_no) {
		this.board_no = board_no;
	}
	public long getParent_no() {
		return parent_no;
	}
	public void setParent_no(long parent_no) {
		this.parent_no = parent_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public Date getBoard_dt() {
		return board_dt;
	}
	public void setBoard_dt(Date board_dt) {
		this.board_dt = board_dt;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", parent_no=" + parent_no + ", board_title=" + board_title
				+ ", board_writer=" + board_writer + ", board_dt=" + board_dt + ", board_content=" + board_content
				+ ", level=" + level + "]";
	}
}
