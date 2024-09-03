package guestbook.dao; 

import java.sql.Connection; // 데이터베이스와 연결을 관리하는 클래스입니다.
import java.sql.DriverManager; // 데이터베이스 드라이버를 로드하고 연결을 관리하는 클래스입니다.
import java.sql.PreparedStatement; // SQL 쿼리를 미리 준비하고 실행하는 데 사용되는 클래스입니다.
import java.sql.ResultSet; // SQL 쿼리의 결과를 저장하는 클래스입니다.
import java.sql.SQLException; // SQL 관련 예외를 처리하는 클래스입니다.
import java.util.ArrayList; // ArrayList 클래스는 가변 길이의 리스트를 구현하는 데 사용됩니다.
import java.util.List; // List 인터페이스는 리스트 형태의 데이터 구조를 정의합니다.

import guestbook.bean.GuestbookDTO; // 방명록의 각 항목을 저장하는 데이터 전송 객체인 GuestbookDTO를 가져옵니다.

public class GuestbookDAO { // 데이터베이스와 상호작용하는 클래스입니다.
    private String driver = "oracle.jdbc.driver.OracleDriver"; // Oracle JDBC 드라이버 클래스의 경로입니다.
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 연결 URL입니다.
    private String username = "c##java"; // 데이터베이스 사용자 이름입니다.
    private String password = "1234"; // 데이터베이스 사용자 비밀번호입니다.
    
    private Connection con; // 데이터베이스 연결 객체입니다.
    private PreparedStatement pstmt; // SQL 쿼리를 준비하고 실행하는 객체입니다.
    private ResultSet rs; // SQL 쿼리의 결과를 저장하는 객체입니다.
    
    private static GuestbookDAO guestbookDAO = new GuestbookDAO(); // GuestbookDAO의 단일 인스턴스를 생성합니다. 이는 싱글톤 패턴을 사용한 것입니다.

    public static GuestbookDAO getInstance() { // GuestbookDAO의 인스턴스를 반환하는 메서드입니다.
        return guestbookDAO; // 미리 생성된 guestbookDAO 인스턴스를 반환합니다.
    }
    
    public GuestbookDAO() { // GuestbookDAO의 생성자입니다.
        try {
            Class.forName(driver); // JDBC 드라이버를 메모리에 로드합니다.
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // 드라이버 로드 중 예외가 발생하면 오류 메시지를 출력합니다.
        }
    }
    
    public void getConnection() { // 데이터베이스 연결을 설정하는 메서드입니다.
        try {
            con = DriverManager.getConnection(url, username, password); // 설정된 URL, 사용자 이름, 비밀번호를 사용하여 데이터베이스에 연결합니다.
        } catch (SQLException e) {
            e.printStackTrace(); // 연결 중 예외가 발생하면 오류 메시지를 출력합니다.
        }                                             
    }

    public void guestbookWrite(GuestbookDTO guestbookDTO) { // 새로운 방명록 항목을 데이터베이스에 저장하는 메서드입니다.
        getConnection(); // 데이터베이스 연결을 설정합니다.
        
        String sql = """
            insert into guestbook values(seq_guestbook.nextval, ?,?,?,?,?, sysdate)
                """; // 방명록에 새로운 항목을 삽입하는 SQL 쿼리입니다. seq_guestbook.nextval은 다음 시퀀스 값을 자동으로 생성합니다.
        
        try {
            pstmt = con.prepareStatement(sql); // SQL 쿼리를 미리 준비합니다.
            
            // ?에 데이터 대입
            pstmt.setString(1, guestbookDTO.getName()); // 첫 번째 ?에 작성자의 이름을 설정합니다.
            pstmt.setString(2, guestbookDTO.getEmail()); // 두 번째 ?에 작성자의 이메일을 설정합니다.
            pstmt.setString(3, guestbookDTO.getHomepage()); // 세 번째 ?에 작성자의 홈페이지를 설정합니다.
            pstmt.setString(4, guestbookDTO.getSubject()); // 네 번째 ?에 글 제목을 설정합니다.
            pstmt.setString(5, guestbookDTO.getContent()); // 다섯 번째 ?에 글 내용을 설정합니다.
            
            pstmt.executeUpdate(); // 준비된 SQL 쿼리를 실행하여 데이터베이스에 삽입합니다.
        
        } catch (SQLException e) {
            e.printStackTrace(); // SQL 실행 중 예외가 발생하면 오류 메시지를 출력합니다.
        } finally {
            try {
                if(pstmt != null) pstmt.close(); // PreparedStatement 객체를 닫아 자원을 해제합니다.
                if(con != null) con.close(); // Connection 객체를 닫아 데이터베이스 연결을 해제합니다.
            } catch (SQLException e) {
                e.printStackTrace(); // 자원 해제 중 예외가 발생하면 오류 메시지를 출력합니다.
            }
        }
        
    }

    public List<GuestbookDTO> getAllGuestbooks(int startNum, int endNum) { // 특정 범위의 방명록 항목들을 가져오는 메서드입니다.
        List<GuestbookDTO> list = new ArrayList<GuestbookDTO>(); // 방명록 항목들을 저장할 리스트를 생성합니다.
        
        getConnection(); // 데이터베이스 연결을 설정합니다.
        
        String sql = """
                select * from
                (select rownum rn, tt.* from
                (select * from guestbook order by seq desc) tt
                ) where rn>=? and rn<=?
                """; // 특정 범위의 방명록 항목들을 가져오는 SQL 쿼리입니다.
        
        try {
            pstmt = con.prepareStatement(sql); // SQL 쿼리를 미리 준비합니다.
            
            pstmt.setInt(1, startNum); // 첫 번째 ?에 시작 번호를 설정합니다.
            pstmt.setInt(2, endNum); // 두 번째 ?에 종료 번호를 설정합니다.
            
            rs = pstmt.executeQuery(); // SQL 쿼리를 실행하여 결과를 가져옵니다.
            
            while(rs.next()) { // 결과 집합의 각 행을 반복 처리합니다.
                GuestbookDTO guestbookDTO = new GuestbookDTO(); // 각 행의 데이터를 저장할 DTO 객체를 생성합니다.
                guestbookDTO.setSeq(rs.getInt("seq")); // seq 컬럼의 값을 DTO의 seq 필드에 설정합니다.
                guestbookDTO.setName(rs.getString("name")); // name 컬럼의 값을 DTO의 name 필드에 설정합니다.
                guestbookDTO.setEmail(rs.getString("email")); // email 컬럼의 값을 DTO의 email 필드에 설정합니다.
                guestbookDTO.setHomepage(rs.getString("homepage")); // homepage 컬럼의 값을 DTO의 homepage 필드에 설정합니다.
                guestbookDTO.setSubject(rs.getString("subject")); // subject 컬럼의 값을 DTO의 subject 필드에 설정합니다.
                guestbookDTO.setContent(rs.getString("content")); // content 컬럼의 값을 DTO의 content 필드에 설정합니다.
                guestbookDTO.setLogtime(rs.getDate("logtime")); // logtime 컬럼의 값을 DTO의 logtime 필드에 설정합니다.
                
                list.add(guestbookDTO); // DTO 객체를 리스트에 추가합니다.
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // SQL 실행 중 예외가 발생하면 오류 메시지를 출력합니다.
            list = null; // 예외 발생 시 리스트를 null로 설정합니다.
        } finally {
            try {
                if(rs != null) rs.close(); // ResultSet 객체를 닫아 자원을 해제합니다.
                if(pstmt != null) pstmt.close(); // PreparedStatement 객체를 닫아 자원을 해제합니다.
                if(con != null) con.close(); // Connection 객체를 닫아 데이터베이스 연결을 해제합니다.
            } catch (SQLException e) {
                e.printStackTrace(); // 자원 해제 중 예외가 발생하면 오류 메시지를 출력합니다.
            }
        }
        
        return list; // 방명록 항목들이 저장된 리스트를 반환합니다.
    }
}

// 이 클래스는 데이터베이스와의 상호작용을 담당하며, 방명록 데이터를 읽고 쓰는 작업을 처리합니다.
