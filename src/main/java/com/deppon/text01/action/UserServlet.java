package com.deppon.text01.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		printHeader(request);

		if ("login_input".equals(action)) {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if ("login".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");

			System.out.println("name->" + name + ",password->" + password);
		}
	}

	public void printHeader(HttpServletRequest request) throws IOException, ServletException {
		StringBuilder sb = new StringBuilder();

		String header = null;
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			header = e.nextElement();
			if (header != null) {
				sb.append("<" + header + "> : " + request.getHeader(header) + "\n");
			}
		}
		
		System.out.println("請求標頭資訊: ");
		System.out.println(sb.toString());
		
		System.out.println("取得用戶端資訊: ");
		System.out.println("請求的伺服器: " + request.getServerName());
		System.out.println("使用協定： " + request.getProtocol());
		System.out.println("請求方法： " + request.getMethod());
		System.out.println("請求的埠號： " + request.getServerPort());
		System.out.println("Context路徑： " + request.getContextPath() );
		System.out.println("Servlet路徑： " + request.getServletPath());
		System.out.println("URI路徑： " + request.getRequestURI());
		System.out.println("查詢字串： " + request.getQueryString());
	 
		System.out.println("使用者主機IP： " + request.getRemoteAddr());
		System.out.println("使用者使用埠號： " + request.getRemotePort());
		
		// POST資料
		StringBuilder sBuilder = new StringBuilder();
		BufferedReader buffer = request.getReader();
		
		String line = "";
		while((line = buffer.readLine()) != null) {
			sBuilder.append(line);
		}
		
		System.out.println("message-body : " + sBuilder.toString());
		
		// GET資料
		String qStr = request.getQueryString();
		System.out.println("queryString : " + qStr);
		
	}

}
