<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.bean.MemberDTO"%>
<%@page import="member.dao.MemberDAO" %>

<%
    //세션에서 사용자 아이디를 가져옴
    String id = (String)session.getAttribute("memId");

    //updateForm에서 전송된 데이터 받아오기
    request.setCharacterEncoding("UTF-8");
    
    String name = request.getParameter("name");
    String pwd = request.getParameter("pwd");
    String gender = request.getParameter("gender");
    String email1 = request.getParameter("email1");
    String email2 = request.getParameter("email2");
    String tel1 = request.getParameter("tel1");
    String tel2 = request.getParameter("tel2");
    String tel3 = request.getParameter("tel3");
    String zipcode = request.getParameter("zipcode");
    String addr1 = request.getParameter("addr1");
    String addr2 = request.getParameter("addr2");

    //DB에 업데이트할 MemberDTO 생성
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setId(id);  //세션에서 가져온 ID
    memberDTO.setName(name);
    memberDTO.setPwd(pwd);
    memberDTO.setGender(gender);
    memberDTO.setEmail1(email1);
    memberDTO.setEmail2(email2);
    memberDTO.setTel1(tel1);
    memberDTO.setTel2(tel2);
    memberDTO.setTel3(tel3);
    memberDTO.setZipcode(zipcode);
    memberDTO.setAddr1(addr1);
    memberDTO.setAddr2(addr2);

    //MemberDAO를 통해 DB 업데이트 수행
    MemberDAO memberDAO = MemberDAO.getInstance();
    memberDAO.update(memberDTO); // 업데이트 메서드 실행

    //세션 만료
    session.invalidate();

    //응답: 알림창 표시 후 index.jsp로 이동
    out.println("<script>");
    out.println("alert('회원 정보 수정이 완료되었습니다. 다시 로그인해주세요.');");
    out.println("location.href='../index.jsp';");
    out.println("</script>");
%>
