package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.board.model.dto.Board;


public class BoardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	
	
	public BoardDAO() {
		//xml에 작성된 sql을 읽어와 저장할 객체를 참조하는 변수
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	
	/**  게시글 목록 조회sql
	 * @param conn
	 * @return
	 */
	public List<Board> selectAllboard(Connection conn) throws Exception{
		
		List<Board> boardList = new ArrayList<>();
		try {
			
			//sql 작성
			String sql = prop.getProperty("selectAllBoard");
			
			// sql수행 후 결과 반환
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			//1행씩 접근하여 컬럼 값을 얻어와 옮겨담기
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				int readCount = rs.getInt("READ_COUNT");
				String createDate = rs.getString("CREATE_DT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCreateDate(createDate);
				board.setCommentCount(commentCount);
				
				boardList.add(board);
			}
		}finally {
			//jdbc객체 자원 반환
			close(rs);
			close(stmt);
		}
		// 결과 반환
		return boardList;
	}



	
	/** 
	 * @param conn
	 * @param input
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int input) throws Exception{
	      
	      Board board = null;
	      
	      try {
	      
	         String sql = prop.getProperty("selectBoard");
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(1, input);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            int boardNo = rs.getInt("BOARD_NO");
	            String boardTitle = rs.getString("BOARD_TITLE");
	            String memberName = rs.getString("MEMBER_NM");
	            int readCount = rs.getInt("READ_COUNT");
	            String createDate = rs.getString("CREATE_DT");
	            
	            String boardContent = rs.getNString("BOARD_CONTENT");
	            int memberno = rs.getInt("MEMBER_NO");
	            
	            board = new Board();
	            
	            board.setBoardNo(boardNo);
	            board.setBoardTitle(boardTitle);
	            board.setMemberName(memberName);
	            board.setReadCount(readCount);
	            board.setCreateDate(createDate);
	               
	            board.setBoardContent(boardContent);
	            board.setMemberNo(memberno);
	         }
	         
	      } finally {
	         
	         close(rs);
	         close(pstmt);

	      }
	      return board;
	   }





	/** 조회수 증가 sql수행
	 * @param conn
	 * @param input
	 * @return result
	 */
	public int updateReadCount(Connection conn, int input) throws Exception{
		
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReadCount"); 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
		
		}finally {
			close(pstmt);
		}
		return result;
	}






	/** 게시글 수정 SQL수행
	 * @param conn
	 * @param boardTitle
	 * @param boardContent
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, String boardTitle, String boardContent, int boardNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNo);
			
			result = pstmt.executeUpdate(); 
			
		}finally {
			close(pstmt);
			
		}
		return result;
	}






	/** 게시글 삭제 sql수행
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception{
		
		
		int result = 0; // 결과 저장용 변수
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();
							// DDL(CREATE, ALTER, DROP) 수행도 가능
							// 결과로 -1 반환
		}finally {
			close(pstmt);
		}
		return result;
	}







	/** 다음 게시글 번호 조회SQL 수행
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{
		
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			
			if(rs.next()) {
				boardNo = rs.getInt(1); //컬럼 순서
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		return boardNo;
	}






	/** 게시글 삽입
	 * @param conn
	 * @param boardTitle
	 * @param boardContent
	 * @param memberNo
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, String boardTitle, String boardContent, int memberNo, int boardNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, boardTitle);
			pstmt.setString(3, boardContent);
			pstmt.setInt(4, memberNo);
			
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	
	

	
	
}






























