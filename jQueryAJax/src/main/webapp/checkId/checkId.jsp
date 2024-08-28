<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> <%-- XML태그 시작전의 공백제거 --%>
<%
	//Java 구역
	String user_id = request.getParameter("user_id");
	//String user_id = "hong";

	//DB 연동
	//[중복검사]지금은 DB를 못쓰기 때문에 만약에 아이디가 
	//"hong" 이라면 DB에 이미 저장된 id라고하자 => 사용 불가능
	//"hong" 이 아니면 => 사용가능
	boolean result = false;
	if(user_id.equals("hong")) result = true;
%>  

<%-- XML 보내기 --%>
<?xml version="1.0" encoding="UTF-8"?>
<checkk_id>
	<result><%=result %></result>
</checkk_id> 

<%--
* trimDirectiveWhitespaces 속성

jsp에서 서블릿이나 EL을 이용할 경우 로딩된 페이지에서 소스보기를 해 보면 공백으로 표기가 된다.
trimDirectiveWhitespaces 속성을 true로 할 경우 해당 공백을 모두 제거해 준다.
--%> 