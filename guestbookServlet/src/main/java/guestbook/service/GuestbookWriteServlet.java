package guestbook.service; 

import java.io.IOException; // 입출력 관련 예외를 처리하기 위한 클래스입니다.
import java.io.PrintWriter; // HTTP 응답을 작성할 때 사용하는 클래스입니다.

import javax.servlet.ServletException; // 서블릿 관련 예외를 처리하기 위한 클래스입니다.
import javax.servlet.annotation.WebServlet; // 서블릿을 특정 URL에 매핑하기 위한 어노테이션입니다.
import javax.servlet.http.HttpServlet; // 서블릿의 기본 동작을 제공하는 클래스입니다.
import javax.servlet.http.HttpServletRequest; // HTTP 요청을 처리하기 위한 클래스입니다.
import javax.servlet.http.HttpServletResponse; // HTTP 응답을 처리하기 위한 클래스입니다.

import guestbook.bean.GuestbookDTO; // 방명록 데이터를 저장하는 DTO 클래스입니다.
import guestbook.dao.GuestbookDAO; // 데이터베이스와 상호작용을 담당하는 DAO 클래스입니다.

@WebServlet("/write") // 이 서블릿을 "/write" URL에 매핑합니다.
public class GuestbookWriteServlet extends HttpServlet { // HttpServlet을 상속받아 서블릿 기능을 구현합니다.
    private static final long serialVersionUID = 1L; // 직렬화된 객체의 고유 ID를 정의합니다.
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청이 들어왔을 때 실행되는 메서드입니다.
        
        request.setCharacterEncoding("UTF-8"); // 요청의 문자 인코딩을 UTF-8로 설정하여 한글을 처리할 수 있게 합니다.
        
        // 사용자가 입력한 데이터를 변수에 저장합니다.
        String name = request.getParameter("name"); // 사용자가 입력한 이름을 가져옵니다.
        String email = request.getParameter("email"); // 사용자가 입력한 이메일을 가져옵니다.
        String homepage = request.getParameter("homepage"); // 사용자가 입력한 홈페이지 주소를 가져옵니다.
        String subject = request.getParameter("subject"); // 사용자가 입력한 글 제목을 가져옵니다.
        String content = request.getParameter("content"); // 사용자가 입력한 글 내용을 가져옵니다.
        
        // 입력된 데이터를 DTO 객체에 저장합니다.
        GuestbookDTO guestbookDTO = new GuestbookDTO(); // 새로운 GuestbookDTO 객체를 생성합니다.
        guestbookDTO.setName(name); // DTO에 이름을 설정합니다.
        guestbookDTO.setEmail(email); // DTO에 이메일을 설정합니다.
        guestbookDTO.setHomepage(homepage); // DTO에 홈페이지 주소를 설정합니다.
        guestbookDTO.setSubject(subject); // DTO에 글 제목을 설정합니다.
        guestbookDTO.setContent(content); // DTO에 글 내용을 설정합니다.
        
        // 데이터베이스에 저장합니다.
        GuestbookDAO guestbookDAO = GuestbookDAO.getInstance(); // 싱글톤 패턴으로 GuestbookDAO 객체를 가져옵니다.
        guestbookDAO.guestbookWrite(guestbookDTO); // DTO에 저장된 데이터를 데이터베이스에 저장합니다.
        
        // 응답을 준비합니다.
        response.setContentType("text/html; charset=UTF-8"); // 응답의 콘텐츠 타입과 문자 인코딩을 설정합니다.
        PrintWriter out = response.getWriter(); // HTTP 응답을 작성할 PrintWriter 객체를 가져옵니다.
        out.println("<html>"); // HTML 응답의 시작 태그를 작성합니다.
        out.println("<body>"); // HTML 바디 태그를 작성합니다.
        out.println("<h3>작성하신 글을 저장하였습니다.</h3>"); // 사용자가 작성한 글이 저장되었음을 알리는 메시지를 출력합니다.
        out.println("<br><br>"); // 줄 바꿈을 두 번 추가합니다.
        out.println("""
                <input type='button' value='뒤로' onclick=history.go(-1) />
                <input type='button' value='글목록' 
                       onclick=location.href='/guestbookServlet/list?pg=1'
                """); // 사용자가 글을 작성한 후 뒤로 가거나 글 목록으로 이동할 수 있는 버튼을 출력합니다.
        out.println("</body>"); // HTML 바디 끝 태그를 작성합니다.
        out.println("</html>"); // HTML 끝 태그를 작성합니다.
    }
}

// 이 서블릿은 사용자가 작성한 방명록 글을 데이터베이스에 저장하고, 저장 완료 메시지를 웹 페이지에 출력하는 역할을 합니다.
