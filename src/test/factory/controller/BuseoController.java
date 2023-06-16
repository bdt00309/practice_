package test.factory.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.factory.model.dao.BuseoDAO;
import test.factory.model.dto.BuseoDTO;



@WebServlet("/buseo_servlet/*")
public class BuseoController extends HttpServlet {
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
		
		request.setAttribute("path", path);  //jsp단에서 사용하기 위해서 
		
		String imsiUrl = uri.replace(path+"/buseo_servlet/", "");
		String forwardPage = "/WEB-INF/_test/factory/buseo/";
		
		if(imsiUrl.equals("list.do")) {
			BuseoDAO dao = new BuseoDAO();
			List<BuseoDTO> list = dao.getSelectAll();
			request.setAttribute("list", list);
			
			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("view.do")) {
			String buseoNo_ = request.getParameter("buseoNo");
			int buseoNo = Integer.parseInt(buseoNo_);
			
			BuseoDTO dto = new BuseoDTO();
			dto.setBuseoNo(buseoNo);
			
			BuseoDAO dao = new BuseoDAO();
			BuseoDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("chuga.do")) {
			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);	
		
		} else if(imsiUrl.equals("chugaProc.do")) {
			
			String buseoName = request.getParameter("buseoName");
			
			BuseoDTO dto = new BuseoDTO();
			dto.setBuseoName(buseoName);
						
			BuseoDAO dao = new BuseoDAO();
			int result = dao.setInsert(dto);
			
			if(result > 0 ) {
				response.sendRedirect(path + "/buseo_servlet/list.do");
			} else {
				response.sendRedirect(path + "/buseo_servlet/chuga.do");
			}
			return;
			
		} else if(imsiUrl.equals("sujung.do")) {
			String buseoNo_ = request.getParameter("buseoNo");
			int buseoNo = Integer.parseInt(buseoNo_);
			
			BuseoDTO dto = new BuseoDTO();
			dto.setBuseoNo(buseoNo);
			
			BuseoDAO dao = new BuseoDAO();
			BuseoDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sujungProc.do")) {
			String buseoNo_ = request.getParameter("buseoNo");
			int buseoNo = Integer.parseInt(buseoNo_);
			
			String buseoName = request.getParameter("buseoName");
			
			BuseoDTO dto = new BuseoDTO();
			dto.setBuseoNo(buseoNo);
			dto.setBuseoName(buseoName);
			
			
			BuseoDAO dao = new BuseoDAO();
			int result = dao.setUpdate(dto);
			
			if(result > 0 ) {
				response.sendRedirect(path + "/buseo_servlet/view.do?buseoNo=" + buseoNo);
			} else {
				response.sendRedirect(path + "/buseo_servlet/sujung.do?buseoNo=" + buseoNo);
			}
			return;
			
		
		} else if(imsiUrl.equals("sakje.do")) {
			String buseoNo_ = request.getParameter("buseoNo");
			int buseoNo = Integer.parseInt(buseoNo_);
			
			BuseoDTO dto = new BuseoDTO();
			dto.setBuseoNo(buseoNo);
			
			BuseoDAO dao = new BuseoDAO();
			BuseoDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sakjeProc.do")) {
			String buseoNo_ = request.getParameter("buseoNo");
			int buseoNo = Integer.parseInt(buseoNo_);
			
			BuseoDTO dto = new BuseoDTO();
			dto.setBuseoNo(buseoNo);
						
			BuseoDAO dao = new BuseoDAO();
			int result = dao.setDelete(dto);
			
			if(result > 0 ) {
				response.sendRedirect(path + "/buseo_servlet/list.do");
			} else {
				response.sendRedirect(path + "/buseo_servlet/sakje.do?buseoNo=" + buseoNo);
			}
			return;
		
		} else {
			System.out.println("존재하지 않는 주소입니다.");
			return;
		}
	
	}

}
