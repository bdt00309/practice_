package test.memo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.memo.model.dao.MemoDAO;
import test.memo.model.dto.MemoDTO;

@WebServlet("/memo/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI().toString();

		request.setAttribute("path", path);

		String imsiUrl = uri.replace(path + "/memo/", "");
		String forwardPage = "/WEB-INF/_test/memo/";

		if (imsiUrl.equals("list")) {
			MemoDAO dao = new MemoDAO();
			List<MemoDTO> list = dao.getSelectAll();
			request.setAttribute("list", list);
			
			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("view")) {
			String memoNo_ = request.getParameter("memoNo");
			int memoNo = Integer.parseInt(memoNo_);
			
			
			MemoDTO dto = new MemoDTO();
			dto.setMemoNo(memoNo);
			
			MemoDAO dao = new MemoDAO();
			MemoDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("chuga")) {

			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("chugaProc")) {
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			// ------
			int errorCounter = 0;
			if (writer == null || writer.trim().equals("")) {
				errorCounter++;
			}
			if (content == null || content.trim().equals("")) {
				errorCounter++;
			}

			if (errorCounter > 0) {
				response.sendRedirect(path + "/memo/chuga");
				return;
			}
			writer = writer.replace("<", "&lt;");
			writer = writer.replace(">", "&gt;");
			writer = writer.replace("\"", "&quot;");
			writer = writer.replace("\'", "&apos;");

			content = content.replace("<", "&lt;");
			content = content.replace(">", "&gt;");
			content = content.replace("\"", "&quot;");
			content = content.replace("\'", "&apos;");

			MemoDTO dto = new MemoDTO();
			dto.setWriter(writer);
			dto.setContent(content);

			MemoDAO dao = new MemoDAO();
			int result = dao.setInsert(dto);

			if (result > 0) {
				response.sendRedirect(path + "/memo/list");
				return;
			} else {
				response.sendRedirect(path + "/memo/chuga");
				return;
			}

		} else if (imsiUrl.equals("sujung")) {
			String memoNo_ = request.getParameter("memoNo");
			int memoNo = Integer.parseInt(memoNo_);
			
			
			MemoDTO dto = new MemoDTO();
			dto.setMemoNo(memoNo);
			
			MemoDAO dao = new MemoDAO();
			MemoDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if (imsiUrl.equals("sujungProc")) {
			String memoNo_ = request.getParameter("memoNo");
			int memoNo = Integer.parseInt(memoNo_);
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			// ------
			int errorCounter = 0;
			if (writer == null || writer.trim().equals("")) {
				errorCounter++;
			}
			if (content == null || content.trim().equals("")) {
				errorCounter++;
			}

			if (errorCounter > 0) {
				response.sendRedirect(path + "/memo/sujung");
				return;
			}
			writer = writer.replace("<", "&lt;");
			writer = writer.replace(">", "&gt;");
			writer = writer.replace("\"", "&quot;");
			writer = writer.replace("\'", "&apos;");

			content = content.replace("<", "&lt;");
			content = content.replace(">", "&gt;");
			content = content.replace("\"", "&quot;");
			content = content.replace("\'", "&apos;");

			MemoDTO dto = new MemoDTO();
			dto.setMemoNo(memoNo);
			dto.setWriter(writer);
			dto.setContent(content);

			MemoDAO dao = new MemoDAO();
			int result = dao.setUpdate(dto);

			if (result > 0) {
				response.sendRedirect(path + "/memo/view?memoNo=" + memoNo);
				return;
			} else {
				response.sendRedirect(path + "/memo/sujung?memoNo=" + memoNo);
				return;
			}
		} else if (imsiUrl.equals("sakje")) {
			String memoNo_ = request.getParameter("memoNo");
			int memoNo = Integer.parseInt(memoNo_);
						
			MemoDTO dto = new MemoDTO();
			dto.setMemoNo(memoNo);
			
			MemoDAO dao = new MemoDAO();
			MemoDTO resultDto = dao.getSelectOne(dto);
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("sakjeProc")) {
			String memoNo_ = request.getParameter("memoNo");
			int memoNo = Integer.parseInt(memoNo_);
			
			MemoDTO dto = new MemoDTO();
			dto.setMemoNo(memoNo);
			
			MemoDAO dao = new MemoDAO();
			int result = dao.setDelete(dto);

			if (result > 0) {
				response.sendRedirect(path + "/memo/list");
				return;
			} else {
				response.sendRedirect(path + "/memo/sakje?memoNo=" + memoNo);
				return;
			}
			
		} else {
			System.out.println("존재하지 않는 주소입니다.");
			return;
		}

	}
}
