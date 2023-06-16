package test.factory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.factory.model.dao.BuseoDAO;
import test.factory.model.dao.PositionDAO;
import test.factory.model.dto.BuseoDTO;
import test.factory.model.dto.PositionDTO;

@WebServlet("/position_servlet/*")
public class PositionController extends HttpServlet {
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
		
		String imsiUrl = uri.replace(path+"/position_servlet/", "");
		String forwardPage = "/WEB-INF/_test/factory/position/";
		
		if(imsiUrl.equals("list.do")) {
			PositionDAO dao = new PositionDAO();
			List<PositionDTO> list = dao.getSelectAll();
			request.setAttribute("list", list);
			
			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("view.do")) {
			String positionNo_ = request.getParameter("positionNo");
			int positionNo = Integer.parseInt(positionNo_);
			
			PositionDTO dto = new PositionDTO();
			dto.setPositionNo(positionNo);
			
			PositionDAO dao = new PositionDAO();
			PositionDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("chuga.do")) {
			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);	
		
		} else if(imsiUrl.equals("chugaProc.do")) {
			
			String positionName = request.getParameter("positionName");
			//System.out.println("positionName: " + positionName);
			
			
			PositionDTO dto = new PositionDTO();
			dto.setPositionName(positionName);
						
			PositionDAO dao = new PositionDAO();
			int result = dao.setInsert(dto);
			
			if(result > 0 ) {
				response.sendRedirect(path + "/position_servlet/list.do");
			} else {
				response.sendRedirect(path + "/position_servlet/chuga.do");
			}
			return;
			
		} else if(imsiUrl.equals("sujung.do")) {
			String positionNo_ = request.getParameter("positionNo");
			int positionNo = Integer.parseInt(positionNo_);
			
			PositionDTO dto = new PositionDTO();
			dto.setPositionNo(positionNo);
			
			PositionDAO dao = new PositionDAO();
			PositionDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sujungProc.do")) {
			String positionNo_ = request.getParameter("positionNo");
			int positionNo = Integer.parseInt(positionNo_);
			
			String positionName = request.getParameter("positionName");
			
			PositionDTO dto = new PositionDTO();
			dto.setPositionNo(positionNo);
			dto.setPositionName(positionName);
			
			
			PositionDAO dao = new PositionDAO();
			int result = dao.setUpdate(dto);
			
			if(result > 0 ) {
				response.sendRedirect(path + "/position_servlet/view.do?positionNo=" + positionNo);
			} else {
				response.sendRedirect(path + "/position_servlet/sujung.do?positionNo=" + positionNo);
			}
			return;
			
		
		} else if(imsiUrl.equals("sakje.do")) {
			String positionNo_ = request.getParameter("positionNo");
			int positionNo = Integer.parseInt(positionNo_);
			
			PositionDTO dto = new PositionDTO();
			dto.setPositionNo(positionNo);
			
			PositionDAO dao = new PositionDAO();
			PositionDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sakjeProc.do")) {
			String positionNo_ = request.getParameter("positionNo");
			int positionNo = Integer.parseInt(positionNo_);
			
			PositionDTO dto = new PositionDTO();
			dto.setPositionNo(positionNo);
						
			PositionDAO dao = new PositionDAO();
			int result = dao.setDelete(dto);
			
			if(result > 0 ) {
				response.sendRedirect(path + "/position_servlet/list.do");
			} else {
				response.sendRedirect(path + "/position_servlet/sakje.do?positionNo=" + positionNo);
			}
			return;
		
		} else {
			System.out.println("존재하지 않는 주소입니다.");
			return;
		}
	
	}


}
