package guestbook.service;

import java.io.IOException; // 입출력 관련 예외를 처리하기 위한 클래스입니다.
import java.io.PrintWriter; // HTTP 응답을 작성할 때 사용하는 클래스입니다.
import java.util.List; // 리스트 형태의 데이터를 다루기 위한 클래스입니다.

import javax.servlet.ServletException; // 서블릿 관련 예외를 처리하기 위한 클래스입니다.
import javax.servlet.annotation.WebServlet; // 서블릿을 특정 URL에 매핑하기 위한 어노테이션입니다.
import javax.servlet.http.HttpServlet; // 서블릿의 기본 동작을 제공하는 클래스입니다.
import javax.servlet.http.HttpServletRequest; // HTTP 요청을 처리하기 위한 클래스입니다.
import javax.servlet.http.HttpServletResponse; // HTTP 응답을 처리하기 위한 클래스입니다.

import guestbook.bean.GuestbookDTO; // 방명록 데이터를 저장하는 DTO 클래스입니다.
import guestbook.dao.GuestbookDAO; // 데이터베이스와 상호작용을 담당하는 DAO 클래스입니다.

@WebServlet("/list") // 이 서블릿을 "/list" URL에 매핑합니다.
public class GuestbookListServlet extends HttpServlet { // HttpServlet을 상속받아 서블릿 기능을 구현합니다.
    private static final long serialVersionUID = 1L; // 직렬화된 객체의 고유 ID를 정의합니다.
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청이 들어왔을 때 실행되는 메서드입니다.
        
        int pg = Integer.parseInt(request.getParameter("pg")); // 요청 파라미터 "pg"를 정수로 변환하여 페이지 번호를 가져옵니다.
        
        // 1페이지당 3개의 항목을 보여주도록 설정합니다.
        int endNum = pg * 3; // 현재 페이지에서 마지막 항목의 번호를 계산합니다.
        int startNum = endNum - 2; // 현재 페이지에서 첫 번째 항목의 번호를 계산합니다.
        
        // 데이터베이스에서 방명록 항목들을 가져옵니다.
        GuestbookDAO guestbookDAO = GuestbookDAO.getInstance(); // 싱글톤 패턴으로 GuestbookDAO 객체를 가져옵니다.
        List<GuestbookDTO> list = guestbookDAO.getAllGuestbooks(startNum, endNum); // 지정된 범위의 방명록 항목들을 가져옵니다.
        System.out.println(list); // 콘솔에 리스트를 출력하여 확인합니다.
        
        // 응답을 준비합니다.
        response.setContentType("text/html; charset=UTF-8"); // 응답의 콘텐츠 타입과 문자 인코딩을 설정합니다.
        PrintWriter out = response.getWriter(); // HTTP 응답을 작성할 PrintWriter 객체를 가져옵니다.
        out.println("<html>"); // HTML 응답의 시작 태그를 작성합니다.
        out.println("<body>"); // HTML 바디 태그를 작성합니다.

        if(list != null) { // 리스트가 비어 있지 않으면 실행합니다.
            for(GuestbookDTO guestbookDTO : list) { // 리스트의 각 항목에 대해 반복합니다.
                out.println("<table border='1'>"); // 테이블 시작 태그를 작성합니다.
                out.println("<tr>"); // 테이블 행 시작 태그를 작성합니다.
                out.println("<th width='100'>작성자</th>"); // 작성자 열 제목을 작성합니다.
                out.println("<td width='150' align='center'>" + guestbookDTO.getName() + "</td>"); // 작성자 이름을 출력합니다.
                out.println("<th width='100'>작성일</th>"); // 작성일 열 제목을 작성합니다.
                out.println("<td width='150' align='center'>" + guestbookDTO.getLogtime() + "</td>"); // 작성일을 출력합니다.
                out.println("</tr>"); // 테이블 행 끝 태그를 작성합니다.
                
                out.println("<tr>"); // 새로운 테이블 행 시작 태그를 작성합니다.
                out.println("<th>이메일</th>"); // 이메일 열 제목을 작성합니다.
                out.println("<td colspan='3'>" + guestbookDTO.getEmail() + "</td>"); // 이메일 주소를 출력합니다.
                out.println("</tr>"); // 테이블 행 끝 태그를 작성합니다.
                
                out.println("<tr>"); // 새로운 테이블 행 시작 태그를 작성합니다.
                out.println("<th>홈페이지</th>"); // 홈페이지 열 제목을 작성합니다.
                out.println("<td colspan='3'>" + guestbookDTO.getHomepage() + "</td>"); // 홈페이지 주소를 출력합니다.
                out.println("</tr>"); // 테이블 행 끝 태그를 작성합니다.
                
                out.println("<tr>"); // 새로운 테이블 행 시작 태그를 작성합니다.
                out.println("<th>제목</th>"); // 제목 열 제목을 작성합니다.
                out.println("<td colspan='3'>" + guestbookDTO.getSubject() + "</td>"); // 글 제목을 출력합니다.
                out.println("</tr>"); // 테이블 행 끝 태그를 작성합니다.
                
                out.println("<tr>"); // 새로운 테이블 행 시작 태그를 작성합니다.
                out.println("<td colspan='4'><pre>" + guestbookDTO.getContent() + "</pre></td>"); // 글 내용을 출력합니다. <pre> 태그는 원래의 서식을 유지합니다.
                out.println("</tr>"); // 테이블 행 끝 태그를 작성합니다.
                out.println("</table>"); // 테이블 끝 태그를 작성합니다.
                out.println("<hr/>"); // 수평선 태그를 작성하여 항목들을 구분합니다.
            }
        }
        
        out.println("</html>"); // HTML 끝 태그를 작성합니다.
        out.println("</body>"); // HTML 바디 끝 태그를 작성합니다.
    }
}

// 이 서블릿은 특정 페이지에 해당하는 방명록 항목들을 웹 페이지에 출력하는 역할을 합니다.
