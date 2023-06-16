package test.haksa.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import test.haksa.model.dao.SihumDAO;
import test.haksa.model.dao.StudentDAO;
import test.haksa.model.dao.SungjukDAO;
import test.haksa.model.dto.SihumDTO;
import test.haksa.model.dto.StudentDTO;
import test.haksa.model.dto.SungjukDTO;


@WebServlet("/haksaSungjuk_servlet/*")
public class SungjukController extends HttpServlet {
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
		String imsiUrl = uri.replace(path + "/haksaSungjuk_servlet/", "");
		
		String forwardPage = "/WEB-INF/_test/haksa/sungjuk/";
		
		if(imsiUrl.equals("list.do")) {
			SungjukDAO dao = new SungjukDAO();
			ArrayList<SungjukDTO> list = dao.getSelectAll();
			request.setAttribute("list", list);
			
			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(imsiUrl.equals("view.do")) {
			String sungjuk_no_ = request.getParameter("sungjuk_no");
			int sungjuk_no = Integer.parseInt(sungjuk_no_);
			
			SungjukDTO dto = new SungjukDTO();
			dto.setSungjuk_no(sungjuk_no);
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(imsiUrl.equals("chuga.do")) {
			StudentDAO studentDao = new StudentDAO();
			List<StudentDTO> studentList = studentDao.getSelectAll();
			for(int i=0; i<studentList.size(); i++) {
				StudentDTO studentDto = studentList.get(i);
			}
			request.setAttribute("studentList", studentList);
			
			SihumDAO sihumDao = new SihumDAO();
			List<SihumDTO> sihumList = sihumDao.getSelectAll();
			for(int i=0; i<sihumList.size(); i++) {
				SihumDTO sihumDto = sihumList.get(i);
			}
			request.setAttribute("sihumList", sihumList);
			
			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
			
		} else if(imsiUrl.equals("chugaProc.do")) {
			String hakbun_ = request.getParameter("hakbun");	
			String sihum_no_ = request.getParameter("sihum_no");	
			String kor_ = request.getParameter("kor");
			String eng_ = request.getParameter("eng");
			String mat_ = request.getParameter("mat");
			String sci_ = request.getParameter("sci");
			String his_ = request.getParameter("his");
			
			int hakbun =  Integer.parseInt(hakbun_);
			int sihum_no =  Integer.parseInt(sihum_no_);
			int kor = Integer.parseInt(kor_);
			int eng = Integer.parseInt(eng_);
			int mat = Integer.parseInt(mat_);
			int sci = Integer.parseInt(sci_);
			int his = Integer.parseInt(his_);
			
			//System.out.println(kor + "/" + his);
			int tot = kor+eng+mat+sci+his;
			double avg = tot / 5.0;
			String grade="가";
			if(avg >= 90) {
				grade="수";
			} else if(avg >= 80) {
				grade="우";
			} else if(avg >= 70) {
				grade="미";
			} else if(avg >= 60) {
				grade="양";
			} 
			
			SungjukDTO dto = new SungjukDTO();
			dto.setHakbun(hakbun);
			dto.setSihum_no(sihum_no);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
			dto.setTot(tot);
			dto.setAvg(avg);
			dto.setGrade(grade);
			
			SungjukDAO dao = new SungjukDAO();
			int result = dao.setInsert(dto);
			//System.out.println("result: "+result);
			if(result > 0) {
				response.sendRedirect(path + "/haksaSungjuk_servlet/list.do");
			} else {
				response.sendRedirect(path + "/haksaSungjuk_servlet/chuga.do");
			}
		} else if(imsiUrl.equals("sujung.do")) {
			String sungjuk_no_ = request.getParameter("sungjuk_no"); // null 공백 숫자 체크
			int sungjuk_no = Integer.parseInt(sungjuk_no_);
			
			SungjukDTO dto = new SungjukDTO();
			dto.setSungjuk_no(sungjuk_no);
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
	
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sujungProc.do")) {
			//System.out.println("sujungProc.do");
			String sungjuk_no_ = request.getParameter("sungjuk_no");
			String hakbun_ = request.getParameter("hakbun");
			String sihum_no_ = request.getParameter("sihum_no");
			
			int sungjuk_no = Integer.parseInt(sungjuk_no_);
			int hakbun = Integer.parseInt(hakbun_);
			int sihum_no = Integer.parseInt(sihum_no_);
			
			System.out.println(sungjuk_no);

			String kor_ = request.getParameter("kor");
			String eng_ = request.getParameter("eng");
			String mat_ = request.getParameter("mat");
			String sci_ = request.getParameter("sci");
			String his_ = request.getParameter("his");
		
			System.out.println(sungjuk_no);
			int kor = Integer.parseInt(kor_);
			int eng = Integer.parseInt(eng_);
			int mat = Integer.parseInt(mat_);
			int sci = Integer.parseInt(sci_);
			int his = Integer.parseInt(his_);

		
			
			SungjukDTO dto = new SungjukDTO();
			dto.setSungjuk_no(sungjuk_no);
			dto.setHakbun(hakbun);
			dto.setSihum_no(sihum_no);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
		
			
			SungjukDAO dao = new SungjukDAO();
			int result = dao.setUpdate(dto);
			System.out.println("result" +result);
			if(result > 0) {
				response.sendRedirect(path + "/haksaSungjuk_servlet/view.do?sungjuk_no=" +sungjuk_no);
			} else {
				response.sendRedirect(path + "/haksaSungjuk_servlet/sujung.do?sungjuk_no=" +sungjuk_no);
			}
			
			
			
		} else if(imsiUrl.equals("sakje.do")) {
			String sungjuk_no_ = request.getParameter("sungjuk_no"); // null 공백 숫자 체크
			int sungjuk_no = Integer.parseInt(sungjuk_no_);
			
			SungjukDTO dto = new SungjukDTO();
			dto.setSungjuk_no(sungjuk_no);
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
	
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(imsiUrl.equals("sakjeProc.do")) {
			request.setCharacterEncoding("UTF-8");
			
			String sungjuk_no_ = request.getParameter("sungjuk_no");
			int sungjuk_no = Integer.parseInt(sungjuk_no_);
			
			SungjukDTO dto = new SungjukDTO();
			dto.setSungjuk_no(sungjuk_no);


			SungjukDAO dao = new SungjukDAO();
			int result = dao.setDelete(dto);
			if(result > 0) {
				response.sendRedirect(path + "/haksaSungjuk_servlet/list.do");
			} else {
				response.sendRedirect(path + "/haksaSungjuk_servlet/sakje.do?sungjuk_no=" +sungjuk_no);
			}
		} else {
			System.out.println("존재하지 않는 주소입니다.");
		}
		
	}

}
