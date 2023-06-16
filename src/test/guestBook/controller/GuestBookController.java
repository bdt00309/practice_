package test.guestBook.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.guestBook.model.dao.GuestBookDAO;
import test.guestBook.model.dto.GuestBookDTO;

@WebServlet("/guestBook/*")
public class GuestBookController extends HttpServlet {
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
		// String url = request.getRequestURL().toString();
		String uri = request.getRequestURI().toString();

		request.setAttribute("path", path); // jsp단에서 사용하기 위해서

		String deSearchGubun = request.getParameter("searchGubun");
		String deSearchData = request.getParameter("searchData");
		// --- desearch null 공백
		if(deSearchGubun == null || deSearchGubun.trim().equals("searchGubun")) {
			deSearchGubun = "";
		}
		if(deSearchData == null || deSearchData.trim().equals("searchData")) {
			deSearchData = "";
		}
		
		String searchGubun = URLDecoder.decode(deSearchGubun, "utf-8");
		String searchData = URLDecoder.decode(deSearchData, "utf-8");
		
		if(searchGubun == null || searchGubun.trim().equals("searchGubun")) {
			searchGubun = "";
		}
		if(searchData == null || searchData.trim().equals("searchData")) {
			searchData = "";
		}
		if(searchGubun.trim().equals("searchGubun") || searchData.trim().equals("searchData")) {
			searchGubun = "";
			searchData = "";
		}
		
		
		String page_ = request.getParameter("page");
		if(page_ == null || page_.trim().equals("")) {
			page_ = "1";
		}
		int page = Integer.parseInt(page_);
				
		request.setAttribute("searchGubun", searchGubun);
		request.setAttribute("searchData", searchData);
		request.setAttribute("page", page);
		
		
		String imsiSearchQuery = "";
		imsiSearchQuery += "searchGubun=" + searchGubun + "&searchData=" + searchData;  
		
		/*
		인코딩 서치구분, 서치데이터 임시서치쿼리에 붙이기
		*/
		
		
		String imsiUrl = uri.replace(path + "/guestBook/", "");
		String forwardPage = "/WEB-INF/_test/guestBook/";

		if (imsiUrl.equals("list")) {
			int pageSize = 10;
			int blockSize = 10;
			
			GuestBookDAO dao = new GuestBookDAO();
			int totalRecord = dao.getTotalRecord(searchGubun, searchData);
			int totalPage = (int)Math.ceil(totalRecord * 1.0 / pageSize);
			int jj = totalRecord - pageSize * (page -1 );
			
			int startRecord = (page - 1) * pageSize + 1; // 제일 중요함.
			int lastRecord = startRecord + pageSize - 1;    // 제일 중요함.
			
			if(lastRecord > totalRecord) {
				lastRecord = totalRecord;
			}
			
			int totalBlock = (int) Math.ceil(totalPage * 1.0 / blockSize);
			int nowBlock = (int) Math.ceil((page - 1 ) / blockSize) + 1;
			
			int startBlock = (nowBlock - 1 ) * blockSize + 1;
			int lastBlock = startBlock + blockSize - 1;
			if (lastBlock > totalPage) {
				lastBlock = totalPage;
			}
			
			int prePage = (nowBlock == 1) ? 1 : (nowBlock -1 ) * blockSize;
			int nxtPage = (nowBlock > totalBlock) ? (nowBlock * blockSize) : (nowBlock * blockSize) + 1;
			if (nxtPage >= totalPage) {
				nxtPage = totalPage;
			}

			int preBlock = 0;
			if(nowBlock > 1) {
				preBlock = nowBlock -1;
			}
			
			int nxtBlock = totalBlock;
			if(nowBlock < totalBlock) {
				nxtBlock = nowBlock +1;
			}
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("totalBlock", totalBlock);
			request.setAttribute("nowBlock", nowBlock);
			request.setAttribute("preBlock", preBlock);
			request.setAttribute("nxtPage", nxtPage);
			request.setAttribute("prePage", prePage);
			request.setAttribute("startBlock", startBlock);
			request.setAttribute("lastBlock", lastBlock);
			request.setAttribute("jj", jj);
						
			List<GuestBookDTO> list = dao.getSelectAll(startRecord, lastRecord, searchGubun, searchData);
			request.setAttribute("list", list);

			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("view")) {
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("chuga")) {
			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("chugaProc")) {
			// System.out.println("chugaProc");

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");

			GuestBookDTO dto = new GuestBookDTO();
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);

			GuestBookDAO dao = new GuestBookDAO();
			int result = dao.setInsert(dto);

			String moveUrl = path + "/guestBook/chuga?page=" + page + "&"+imsiSearchQuery;
			if (result > 0) {
				moveUrl = path + "/guestBook/list";
			}
			response.sendRedirect(moveUrl);
			return;

		} else if (imsiUrl.equals("sujung")) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			GuestBookDTO dto = new GuestBookDTO();
			dto.setNo(no);
			
			GuestBookDAO dao = new GuestBookDAO();
			GuestBookDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("sujungProc")) {
			//System.out.println("sujungProc");
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			
			GuestBookDTO dto = new GuestBookDTO();
			dto.setNo(no);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			
			GuestBookDAO dao = new GuestBookDAO();
			int result = dao.setUpdate(dto);
			
			String moveUrl = path + "/guestBook/sujung?no=" + no + "&page=" + page + "&" + imsiSearchQuery;
			if (result > 0) {
				moveUrl = path + "/guestBook/list";
			}
			response.sendRedirect(moveUrl);
			return;
			
			
		} else if (imsiUrl.equals("sakje")) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			GuestBookDTO dto = new GuestBookDTO();
			dto.setNo(no);
			
			GuestBookDAO dao = new GuestBookDAO();
			GuestBookDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);

		} else if (imsiUrl.equals("sakjeProc")) {
			//System.out.println("sakjeProc");
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			String passwd = request.getParameter("passwd");
			GuestBookDTO dto = new GuestBookDTO();
			dto.setNo(no);
			dto.setPasswd(passwd);
			
			GuestBookDAO dao = new GuestBookDAO();
			int result = dao.setDelete(dto);
			
			String moveUrl = path + "/guestBook/sakje?no=" + no + "&page=" + page + "&" + imsiSearchQuery;
			if (result > 0) {
				moveUrl = path + "/guestBook/list";
			}
			response.sendRedirect(moveUrl);
			return;
			
			
		} else {
			System.out.println("존재하지 않는 주소입니다.");
			return;
		}

	}

}
