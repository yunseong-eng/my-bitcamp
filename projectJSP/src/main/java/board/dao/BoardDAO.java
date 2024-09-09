package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.bean.BoardDTO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private DataSource ds;

    // Singleton 패턴
    private static BoardDAO instance = new BoardDAO();

    public static BoardDAO getInstance() {
        return instance;
    }

    private BoardDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // JNDI 설정
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 시퀀스 값 가져오기
    public int getNextSeq() {
        int seq = 0;
        String sql = "select seq_board.nextval from dual";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                seq = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seq;
    }

    //게시글 삽입
    public void insertBoard(BoardDTO boardDTO) {
        String sql = "insert into board (seq, id, name, email, subject, content, ref) values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, boardDTO.getSeq());
            pstmt.setString(2, boardDTO.getId());
            pstmt.setString(3, boardDTO.getName());
            pstmt.setString(4, boardDTO.getEmail());
            pstmt.setString(5, boardDTO.getSubject());
            pstmt.setString(6, boardDTO.getContent());
            pstmt.setInt(7, boardDTO.getRef());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //게시글 리스트 가져오기
    public List<BoardDTO> boardList(int startNum, int endNum) {
        List<BoardDTO> list = new ArrayList<BoardDTO>();
        
        //ROWNUM은 항상 1부터 시작하므로 BETWEEN 구문을 사용하지 않고 올바르게 범위를 처리합니다.
        String sql = """
                select * from (
                    select tt.*, rownum rn from (
                        select * from board order by ref desc, step asc
                    ) tt where rownum <= ?
                ) where rn >= ?
            """;
        
        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, endNum); //ROWNUM의 상한
            pstmt.setInt(2, startNum); //ROWNUM의 하한
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BoardDTO boardDTO = new BoardDTO();
                
                boardDTO.setSeq(rs.getInt("seq"));
                boardDTO.setId(rs.getString("id"));
                boardDTO.setName(rs.getString("name"));
                boardDTO.setEmail(rs.getString("email"));
                boardDTO.setSubject(rs.getString("subject"));
                boardDTO.setContent(rs.getString("content"));
                boardDTO.setRef(rs.getInt("ref"));
                boardDTO.setLev(rs.getInt("lev"));
                boardDTO.setStep(rs.getInt("step"));
                boardDTO.setPseq(rs.getInt("pseq"));
                boardDTO.setReply(rs.getInt("reply"));
                boardDTO.setHit(rs.getInt("hit"));
                boardDTO.setLogtime(rs.getDate("logtime"));
                
                list.add(boardDTO);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    
    //글 번호
    public int getTotalA() {
        int totalA = 0;
        String sql = "select count(*) from board"; //전체 게시글 수를 가져오는 SQL

        try {
        	con = ds.getConnection();
        	
        	pstmt = con.prepareStatement(sql);
        	rs = pstmt.executeQuery();
        	
        	rs.next();
        	totalA = rs.getInt(1);
        
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return totalA;
    }

    //DB 자원 해제
    private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
