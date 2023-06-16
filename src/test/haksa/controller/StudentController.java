package test.haksa.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.haksa.model.dao.StudentDAO;
import test.haksa.model.dto.StudentDTO;

@WebServlet("/haksaStudent_servlet/*")
public class StudentController extends HttpServlet {
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
		
		request.setAttribute("path", path);
		String imsiUrl = uri.replace(path+"/haksaStudent_servlet/", "");
		
		String forwardPage = "/WEB-INF/_test/haksa/student/";
		
		if(imsiUrl.equals("list.do")) {
			//System.out.println("list.do");
			StudentDAO dao = new StudentDAO();
			ArrayList<StudentDTO> list = dao.getSelectAll();
			request.setAttribute("list", list);
			
			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
			
		} else if(imsiUrl.equals("view.do")) {
			//System.out.println("view.do");
			String hakbun_ = request.getParameter("hakbun"); // null 공백 숫자 체크
			int hakbun = Integer.parseInt(hakbun_);
			
			StudentDTO dto = new StudentDTO();
			dto.setHakbun(hakbun);
			
			StudentDAO dao = new StudentDAO();
			StudentDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
			
		} else if(imsiUrl.equals("chuga.do"))	{
			//System.out.println("chuga.do");
			
			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
			
		} else if(imsiUrl.equals("sujung.do")) {
			//System.out.println("sujung.do");
			String hakbun_ = request.getParameter("hakbun"); // null 공백 숫자 체크
			int hakbun = Integer.parseInt(hakbun_);
			
			StudentDTO dto = new StudentDTO();
			dto.setHakbun(hakbun);
			
			StudentDAO dao = new StudentDAO();
			StudentDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
	
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(imsiUrl.equals("sakje.do")) {
			//System.out.println("sakje.do");
			String hakbun_ = request.getParameter("hakbun"); // null 공백 숫자 체크
			int hakbun = Integer.parseInt(hakbun_);
			
			StudentDTO dto = new StudentDTO();
			dto.setHakbun(hakbun);
			
			StudentDAO dao = new StudentDAO();
			StudentDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(imsiUrl.equals("chugaProc.do")) {
			//System.out.println("chugaProc");
			
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String parent_phone = request.getParameter("parent_phone");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String addr3 = request.getParameter("addr3");
			String addr4 = request.getParameter("addr4");
			
			StudentDTO dto = new StudentDTO();
			dto.setName(name);
			dto.setPhone(phone);
			dto.setParent_phone(parent_phone);
			dto.setAddr1(addr1);
			dto.setAddr2(addr2);
			dto.setAddr3(addr3);
			dto.setAddr4(addr4);
			
			StudentDAO dao = new StudentDAO();
			int result = dao.setInsert(dto);
			
			if(result > 0) {
				//성공 시 리스트 페이지로 이동
				response.sendRedirect(path + "/haksaStudent_servlet/list.do");
			} else {
				response.sendRedirect(path + "/haksaStudent_servlet/chuga.do");
			}
			
			
			
		} else if(imsiUrl.equals("sujungProc.do")) {
			//System.out.println("sujungProc.do");
			String hakbun_ = request.getParameter("hakbun"); 
			int hakbun = Integer.parseInt(hakbun_);
			//System.out.println(hakbun);
			String phone = request.getParameter("phone");
			String parent_phone = request.getParameter("parent_phone");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String addr3 = request.getParameter("addr3");
			String addr4 = request.getParameter("addr4");
			
			StudentDTO dto = new StudentDTO();
			dto.setHakbun(hakbun);
			dto.setPhone(phone);
			dto.setParent_phone(parent_phone);
			dto.setAddr1(addr1);
			dto.setAddr2(addr2);
			dto.setAddr3(addr3);
			dto.setAddr4(addr4);
			
			StudentDAO dao = new StudentDAO();
			int result = dao.setUpdate(dto);
			
			if(result > 0) {
				//성공 시 리스트 페이지로 이동
				response.sendRedirect(path + "/haksaStudent_servlet/view.do?hakbun=" + hakbun);
			} else {
				response.sendRedirect(path + "/haksaStudent_servlet/sujung.do?hakbun=" + hakbun);
			}
			
			
			
		} else if(imsiUrl.equals("sakjeProc.do")) {
			//System.out.println("sakjeProc.do");
			
			String hakbun_ = request.getParameter("hakbun"); // null 공백 숫자 체크
			int hakbun = Integer.parseInt(hakbun_);
			
			StudentDTO dto = new StudentDTO();
			dto.setHakbun(hakbun);
			
			StudentDAO dao = new StudentDAO();
			int result = dao.setDelete(dto);
			if(result > 0) {
				//성공 시 리스트 페이지로 이동
				response.sendRedirect(path + "/haksaStudent_servlet/list.do");
			} else {
				response.sendRedirect(path + "/haksaStudent_servlet/sakje.do?hakbun=" + hakbun);
			
			}
		} else {
			System.out.println("존재하지 않는 주소입니다.");
		}
	}

}
