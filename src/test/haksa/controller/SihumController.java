package test.haksa.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.haksa.model.dao.SihumDAO;
import test.haksa.model.dto.SihumDTO;

@WebServlet("/haksaSihum_servlet/*")
public class SihumController extends HttpServlet {
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
		String imsiUrl = uri.replace(path+"/haksaSihum_servlet/", "");
		
		String forwardPage = "/WEB-INF/_test/haksa/sihum/";
		
		if(imsiUrl.equals("list.do")) {
			//System.out.println("1.do");
			SihumDAO dao = new SihumDAO();
			ArrayList<SihumDTO> list = dao.getSelectAll();
			request.setAttribute("list", list);
			
			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(imsiUrl.equals("view.do")) {
			//System.out.println("2.do");
			
			String sihum_no_ = request.getParameter("sihum_no");
			int sihum_no = Integer.parseInt(sihum_no_);
			
			SihumDTO dto = new SihumDTO();
			dto.setSihum_no(sihum_no);
			
			SihumDAO dao = new SihumDAO();
			SihumDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if (imsiUrl.equals("chuga.do")) {
			//System.out.println("3.do");
			
			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if (imsiUrl.equals("chugaProc.do")) {	
			String sihum_name = request.getParameter("sihum_name");
			String sihum_date_ = request.getParameter("sihum_date");
			Date sihum_date = Date.valueOf(sihum_date_);
			
			SihumDTO dto = new SihumDTO();
			dto.setSihum_name(sihum_name);
			dto.setSihum_date(sihum_date);
			
			SihumDAO dao = new SihumDAO();
			int result = dao.setInsert(dto);
			
			if(result > 0) {
				//성공 시 리스트 페이지로 이동
				response.sendRedirect(path + "/haksaSihum_servlet/list.do");
			} else {
				response.sendRedirect(path + "/haksaSihum_servlet/chuga.do");
			}
			
			
		} else if(imsiUrl.equals("sujung.do")) {
			//System.out.println("4.do");
			String sihum_no_ = request.getParameter("sihum_no");
			int sihum_no = Integer.parseInt(sihum_no_);
			
			SihumDTO dto = new SihumDTO();
			dto.setSihum_no(sihum_no);
			
			SihumDAO dao = new SihumDAO();
			SihumDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(imsiUrl.equals("sujungProc.do")) {
			String sihum_no_ = request.getParameter("sihum_no");
			int sihum_no = Integer.parseInt(sihum_no_);
			
			String sihum_name = request.getParameter("sihum_name");
			String sihum_date_ = request.getParameter("sihum_date");
			Date sihum_date = Date.valueOf(sihum_date_);
			
			SihumDTO dto = new SihumDTO();
			dto.setSihum_no(sihum_no);
			dto.setSihum_name(sihum_name);
			dto.setSihum_date(sihum_date);
			
			SihumDAO dao = new SihumDAO();
			int result = dao.setUpdate(dto);
			if(result > 0) {
				response.sendRedirect(path + "/haksaSihum_servlet/view.do?sihum_no="+sihum_no);
			} else {
				response.sendRedirect(path + "/haksaSihum_servlet/sujung.do?sihum_no="+sihum_no);
			}

		} else if(imsiUrl.equals("sakje.do")) {
			//System.out.println("5.do");
			String sihum_no_ = request.getParameter("sihum_no");
			int sihum_no = Integer.parseInt(sihum_no_);
			
			SihumDTO dto = new SihumDTO();
			dto.setSihum_no(sihum_no);
			
			SihumDAO dao = new SihumDAO();
			SihumDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sakjeProc.do")) {	
			String sihum_no_ = request.getParameter("sihum_no");
			int sihum_no = Integer.parseInt(sihum_no_);
			
			SihumDTO dto = new SihumDTO();
			dto.setSihum_no(sihum_no);
			
			SihumDAO dao = new SihumDAO();
			int result = dao.setDelete(dto);
			
			if(result > 0) {
				response.sendRedirect(path + "/haksaSihum_servlet/list.do");
			} else {
				response.sendRedirect(path + "/haksaSihum_servlet/sakje.do?sihum_no="+sihum_no);
			}
		} else {
			System.out.println("존재하지 않는 주소입니다.");
		}
		
		
		
		
	}

}
