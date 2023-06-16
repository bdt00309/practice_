package test.factory.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.factory.model.dao.BuseoDAO;
import test.factory.model.dao.EmployeeDAO;
import test.factory.model.dao.PositionDAO;
import test.factory.model.dto.BuseoDTO;
import test.factory.model.dto.EmployeeDTO;
import test.factory.model.dto.PositionDTO;

@WebServlet("/employee_servlet/*") //url-mapping
public class EmployeeController extends HttpServlet {
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
		
		String nowPage_ = request.getParameter("nowPage");
		if(nowPage_ == null || nowPage_.trim().equals("") ) {
			nowPage_ = "1";
		}
		int nowPage = Integer.parseInt(nowPage_);
		
		request.setAttribute("searchGubun", searchGubun);
		request.setAttribute("searchData", searchData);
		request.setAttribute("nowPage", nowPage);
	
		String imsiUrl = uri.replace(path+"/employee_servlet/", "");
		String forwardPage = "/WEB-INF/_test/factory/employee/";
		
		if(imsiUrl.equals("list.do")) {
			int pageSize = 2;
			int blockSize = 10;
			
			EmployeeDAO dao = new EmployeeDAO();
			
			int totalRecord = dao.getTotalRecord(searchGubun, searchData);
			//System.out.println("totalRecord: "+totalRecord);
			int totalPage = (int)Math.ceil(totalRecord * 1.0 / pageSize);
			int jj = totalRecord - pageSize * (nowPage -1 );
			
			int startRecord = (nowPage - 1) * pageSize + 1; // 제일 중요함.
			int lastRecord = startRecord + pageSize - 1;    // 제일 중요함.
			
			if(lastRecord > totalRecord) {
				lastRecord = totalRecord;
			}
			
			int totalBlock = (int) Math.ceil(totalPage * 1.0 / blockSize);
			int nowBlock = (int) Math.ceil((nowPage - 1 ) / blockSize) + 1;
			
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
			
			
			List<EmployeeDTO> list = dao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
			request.setAttribute("list", list);
			
			forwardPage += "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("view.do")) {
			String sabun_ = request.getParameter("sabun");
			int sabun = Integer.parseInt(sabun_);
			
			EmployeeDTO dto = new EmployeeDTO();
			dto.setSabun(sabun);
			
			EmployeeDAO dao = new EmployeeDAO();
			EmployeeDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("chuga.do")) {
			BuseoDAO buseoDao = new BuseoDAO();
			List<BuseoDTO> buseoList = buseoDao.getSelectAll();
			for(int i=0; i<buseoList.size(); i++) {
				BuseoDTO buseoDto = buseoList.get(i);
			}
			request.setAttribute("buseoList", buseoList);
			
			PositionDAO PositionDAO = new PositionDAO();
			List<PositionDTO> positionList = PositionDAO.getSelectAll();
			for(int i=0; i<positionList.size(); i++) {
				PositionDTO positionDTO = positionList.get(i);
			}
			request.setAttribute("positionList", positionList);
			
			forwardPage += "chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);	
		
		} else if(imsiUrl.equals("chugaProc.do")) {
			
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String hireDate_ = request.getParameter("hireDate");
			String salary_ = request.getParameter("salary");
			String buseoNo_ = request.getParameter("buseoNo");
			String positionNo_ = request.getParameter("positionNo");
			
			Date hireDate = Date.valueOf(hireDate_);
			int salary = Integer.parseInt(salary_);
			int buseoNo = Integer.parseInt(buseoNo_);
			int positionNo = Integer.parseInt(positionNo_);
			
			EmployeeDTO dto = new EmployeeDTO();
			dto.setName(name);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setHireDate(hireDate);
			dto.setSalary(salary);
			dto.setBuseoNo(buseoNo);
			dto.setPositionNo(positionNo);
			
			EmployeeDAO dao = new EmployeeDAO();
			int result = dao.setInsert(dto);
			
			String enSearchGubun = URLEncoder.encode(searchGubun, "UTF-8");
			String enSearchData = URLEncoder.encode(searchData, "UTF-8");
			String searchInfo = "searchGubun=" + enSearchGubun + "&searchData=" + enSearchData;
			
			if(result > 0 ) {
				response.sendRedirect(path + "/employee_servlet/list.do"); //  get방식으로 보낼 때는, url인코딩 디코팅해야함
			} else {
				response.sendRedirect(path + "/employee_servlet/chuga.do?" + searchInfo);
			}

			return;
			
		} else if(imsiUrl.equals("sujung.do")) {
			String sabun_ = request.getParameter("sabun");
			int sabun = Integer.parseInt(sabun_);
			
			EmployeeDTO dto = new EmployeeDTO();
			dto.setSabun(sabun);
			
			EmployeeDAO dao = new EmployeeDAO();
			EmployeeDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sujungProc.do")) {
			String sabun_ = request.getParameter("sabun");
			int sabun = Integer.parseInt(sabun_);
			
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String hireDate_ = request.getParameter("hireDate");
			String salary_ = request.getParameter("salary");
			String buseoNo_ = request.getParameter("buseoNo");
			String positionNo_ = request.getParameter("positionNo");
			
			Date hireDate = Date.valueOf(hireDate_);
			int salary = Integer.parseInt(salary_);
			int buseoNo = Integer.parseInt(buseoNo_);
			int positionNo = Integer.parseInt(positionNo_);
			
			EmployeeDTO dto = new EmployeeDTO();
			dto.setSabun(sabun);
			dto.setName(name);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setHireDate(hireDate);
			dto.setSalary(salary);
			dto.setBuseoNo(buseoNo);
			dto.setPositionNo(positionNo);
			
			EmployeeDAO dao = new EmployeeDAO();
			int result = dao.setUpdate(dto);
			
			String enSabun = URLEncoder.encode(String.valueOf(sabun), "UTF-8");
			String enSearchGubun = URLEncoder.encode(searchGubun, "UTF-8");
			String enSearchData = URLEncoder.encode(searchData, "UTF-8");
			String searchInfo = "sabun=" + enSabun + "&searchGubun=" + enSearchGubun + "&searchData=" + enSearchData;
			
			
			if(result > 0 ) {
				response.sendRedirect(path + "/employee_servlet/view.do?" + searchInfo);
			} else {
				response.sendRedirect(path + "/employee_servlet/sujung.do?" + searchInfo);
			}
			return;
			
		
		} else if(imsiUrl.equals("sakje.do")) {
			String sabun_ = request.getParameter("sabun");
			int sabun = Integer.parseInt(sabun_);
			
			EmployeeDTO dto = new EmployeeDTO();
			dto.setSabun(sabun);
			
			EmployeeDAO dao = new EmployeeDAO();
			EmployeeDTO resultDto = dao.getSelectOne(dto);
			
			request.setAttribute("resultDto", resultDto);
			
			forwardPage += "sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(imsiUrl.equals("sakjeProc.do")) {
			String sabun_ = request.getParameter("sabun");
			int sabun = Integer.parseInt(sabun_);
					
			EmployeeDTO dto = new EmployeeDTO();
			dto.setSabun(sabun);
						
			EmployeeDAO dao = new EmployeeDAO();
			int result = dao.setDelete(dto);
			
			String enSearchGubun = URLEncoder.encode(searchGubun, "UTF-8");
			String enSearchData = URLEncoder.encode(searchData, "UTF-8");
			String searchInfo = "&searchGubun=" + enSearchGubun + "&searchData=" + enSearchData;
			
			
			if(result > 0 ) {
				response.sendRedirect(path + "/employee_servlet/list.do?" + searchInfo);
			} else {
				searchInfo = "sabun=" +  sabun + "&" + searchInfo;
				response.sendRedirect(path + "/employee_servlet/sakje.do?" + searchInfo);
			}
			return;
		
		} else {
			System.out.println("존재하지 않는 주소입니다.");
			return;
		}
	
	}
}
