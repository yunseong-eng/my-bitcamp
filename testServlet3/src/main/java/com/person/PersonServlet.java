package com.person;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/person-servlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터
    	String name = request.getParameter("name"); //데이터 1개
        String gender = request.getParameter("gender");
        String color = request.getParameter("color");
        String[] hobby = request.getParameterValues("hobby");
        String[] subjects = request.getParameterValues("subjects");
        
    	//응답
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Person Info</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<ul style='color: " + color + ";'>");
        
        out.println("<li>이름 : " + name + "</li>");
        out.println("<li>성별 : " + gender + "</li>");
        out.println("<li>색깔 : " + color + "</li>");
        
        out.println("<li>취미 : ");
        if (hobby != null) {
	        for (int i=0; i<hobby.length; i++) {
	            out.println(hobby[i] + "&nbsp;");
	        }
        }else{
            out.println("선택한 취미가 없습니다.");
        }
        
        out.println("<li>과목 : ");
        for(String data : subjects) {
        	out.println(data + "&nbsp;");
        }
        out.println("</ul>");        
        out.println("</body>");
        out.println("</html>");
	}

}








