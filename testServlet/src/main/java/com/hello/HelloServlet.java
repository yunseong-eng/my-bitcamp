package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init(ServletConfig config) {
		System.out.println("init...");
		
	}

	public void destroy() {
		System.out.println("destroy...");
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGet...");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); //웹으로
		out.println("<html>");
		out.println("<body>");
		out.println("Hello Servlet!!<br/>");
		out.println("안녕하세요 서블릿!!<br/>");
		out.println("</body>");
		out.println("</html>");
		
		/*
		 * 1. 콘솔 
		 * System.out.println("<html>");
		 * 
		 * 2. 파일 printWriter out = new PrintWriter(new FileWrite("result.txt"));
		 * out.println("<html>");
		 * 
		 * 3. 네트워크 ObjectOutputStream out = new
		 * ObjectOutputStream(소켓.getOutputStream()); out.println("<html>");
		 */
		
	}

}
