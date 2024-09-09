<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="member.dao.MemberDAO"%>

<%
	//데이터
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	//DB
	MemberDAO memberDAO = MemberDAO.getInstance();
	String name = memberDAO.memberLogin(id, pwd);

	
	//memberDAO에서 id로 이메일을 가져오는 부분
	String email1 = memberDAO.getEmailPart1(id); 
	String email2 = memberDAO.getEmailPart2(id); 
	String email = email1 + "@" + email2;

%>

<% if(name == null) {
	//페이지 이동
	response.sendRedirect("loginFail.jsp");
	
} else {
	//세션 설정
	session.setAttribute("memName", name);  //사용자 이름을 세션에 저장
	session.setAttribute("memId", id);      //사용자 ID를 세션에 저장
	
	//이메일을 세션에 저장 (두 부분을 합쳐서 전체 이메일로 저장)
	session.setAttribute("memEmail1", email1); //이메일 첫 번째 부분 저장
	session.setAttribute("memEmail2", email2); //이메일 두 번째 부분 저장
	session.setAttribute("memEmail", email);   //전체 이메일 저장

	//로그인 성공 후 페이지로 이동
	response.sendRedirect("loginOk.jsp");
} %>
