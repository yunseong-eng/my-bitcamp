<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="board.dao.BoardDAO"%>
<%@ page import="board.bean.BoardDTO"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.io.IOException"%>

<%
    //세션이 없거나, 세션에서 memId를 찾을 수 없는 경우 로그인 페이지로 이동
    if (session.getAttribute("memId") == null) {
        out.println("<script>");
        out.println("alert('로그인이 필요합니다. 로그인 페이지로 이동합니다.');");
        out.println("location.href='../member/loginForm.jsp';");
        out.println("</script>");
        return; 
    }

    //폼에서 입력된 제목과 내용 받아오기
    request.setCharacterEncoding("UTF-8");
    
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");

    //제목 또는 내용이 없으면 에러 처리
    if (subject == null || subject.trim().isEmpty() || content == null || content.trim().isEmpty()) {
        out.println("<script>");
        out.println("alert('제목과 내용을 모두 입력해야 합니다.');");
        out.println("history.back();"); //이전 페이지로 돌아가기
        out.println("</script>");
        return;
    }

    //세션에서 ID와 이름 가져오기
    String id = (String)session.getAttribute("memId");
    String name = (String)session.getAttribute("memName");

    //세션에서 이메일 정보 가져오기
    String email1 = (String)session.getAttribute("memEmail1");
    String email2 = (String)session.getAttribute("memEmail2");
    String email = email1 + "@" + email2;

    //게시글 정보를 담을 DTO 객체 생성 및 설정
    BoardDTO boardDTO = new BoardDTO();
    BoardDAO boardDAO = BoardDAO.getInstance();

    //시퀀스 값 가져오기
    int seq = boardDAO.getNextSeq(); //시퀀스 번호 가져오기
    boardDTO.setSeq(seq);
    boardDTO.setId(id);
    boardDTO.setName(name);
    boardDTO.setEmail(email);
    boardDTO.setSubject(subject);
    boardDTO.setContent(content);
    boardDTO.setRef(seq); //ref는 seq와 동일하게 설정 (게시글 그룹 번호)

    //게시글을 DB에 삽입
    boardDAO.insertBoard(boardDTO);

   
%>
