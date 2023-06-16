package index.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/") //<- 프로젝트이름까지 생략되어있음, url mapping
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI().toString();
		
		request.setAttribute("path", path); // path를 jsp에도 사용하려면 객체에 담아서 사용
		request.setAttribute("url", url);
		request.setAttribute("uri", uri);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");  // 슬래시는 web content까지
		rd.forward(request, response); 
		
		
	}

}
