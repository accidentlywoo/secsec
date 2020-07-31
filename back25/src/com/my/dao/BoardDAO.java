package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
import com.my.vo.Board;

public class BoardDAO {
//	public static final int CNT_PER_PAGE = 3;
	public void insert(Board board) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSql = null;
		try {

//			if(board.getParent_no() == 0) {
//				// 글쓰기
//				insertSql = "INSERT INTO board "
//						+ "VALUES(board_seq.NEXTVAL, 0,?,?,SYSTIMESTAMP,?)";
//			}else {
//				//댓글 쓰기
//				insertSql = "INSERT INTO board "
//						+ "VALUES(board_seq.NEXTVAL, ?,?,?,SYSTIMESTAMP,?)";
//			}
			insertSql = "INSERT INTO board " + "VALUES(board_seq.NEXTVAL, ?,?,?,SYSTIMESTAMP,?)";
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertSql);
			pstmt.setLong(1, board.getParent_no());
			pstmt.setString(2, board.getBoard_title());
			pstmt.setString(3, board.getBoard_writer());
			pstmt.setString(4, board.getBoard_content());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	public List<Board> selectAll(int startRow,int endRow) throws FindException {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet resultSet = null;
		try {

			String pageSql = "SELECT  b.*\r\n" 
					+ "        FROM (SELECT ROWNUM list,level, a.*\r\n"
					+ "                FROM (SELECT * \r\n"
					+ "                        FROM board\r\n"
					+ "                        ORDER BY board_no DESC) a\r\n"
					+ "                START WITH parent_no = 0\r\n"
					+ "                CONNECT BY PRIOR board_no = parent_no\r\n"
					+ "                ORDER SIBLINGS BY a.board_no DESC) b\r\n" + "WHERE list BETWEEN ? AND ?";
			con = MyConnection.getConnection();
			pstm = con.prepareStatement(pageSql);
			// page가 1이면 startrow 1 endRow 3
//			int startRow = (page -1)* CNT_PER_PAGE +1;
//			int endRow = startRow +2;
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);
			resultSet = pstm.executeQuery();
			List<Board> list = new ArrayList<Board>();
			while (resultSet.next()) {
				long board_no = resultSet.getLong("BOARD_NO");
				long parent_no = resultSet.getLong("PARENT_NO");
				 String board_title = resultSet.getString("BOARD_TITLE");
				 String board_writer =  resultSet.getString("BOARD_WRITER");
				 Date board_dt = resultSet.getDate("BOARD_DT");
				 String board_content = resultSet.getString("BOARD_CONTENT");
				 int level = resultSet.getInt("LEVEL");
				 Board board = new Board(board_no, parent_no, board_title, board_writer, board_dt, board_content, level);
				 list.add(board);
			}
			if(list.size() == 0) {
				throw new FindException("결과가 없음");
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new FindException();
		}finally {
			MyConnection.close(resultSet, pstm, con);
		}
	}
	public int selectCount() throws FindException{
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet resultSet = null;
		try {
			String pageSql = "select count(*) cnt from board";
			con = MyConnection.getConnection();
			pstm = con.prepareStatement(pageSql);
			resultSet = pstm.executeQuery();
			if(!resultSet.next()) {
				throw new FindException("게시글이 없습니다.");
			}
			return resultSet.getInt("cnt");
		}catch (Exception e) {
			e.printStackTrace();
			throw new FindException("error");
		}finally {
			MyConnection.close(resultSet, pstm, con);
		}
	}
}
