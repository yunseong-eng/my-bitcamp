<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%--
<%   
//Java      
String result = request.getParameter("keyword");
%>

결과 = <%=result %> --%>

결과 = ${ param.keyword } <%-- EL /JSTL --%>
