<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>     
<%@ page import="java.util.Date" %> 
<%@ page trimDirectiveWhitespaces="true" %>

<%!
	//전역변수
	int num = 3;
%>
<%
	//Java 구역
	String num = request.getParameter("num");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	String datetime = request.getParameter("datetime");

	boolean result = true;
	String message = "덧글이 작성되었습니다";
%>

<%-- XML 보내기 --%>
<?xml version="1.0" encoding="UTF-8"?>
<comment>
	<result><%=result%></result>
	<message><%=message%></message>
	<item>
		<num><%=num%></num>
		<writer><%=writer%></writer>
		<content><%=content%></content>
		<datetime><%=datetime%></datetime>
	</item>
</comment>		