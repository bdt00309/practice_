package test.exam.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/exam_servlet/*")  // url-mapping 주소줄에 프로젝트/exam_servlet/
public class Examcontroller extends HttpServlet {
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
		String url = request.getRequestURL().toString(); // 브라우저 전체주소
		String uri = request.getRequestURI().toString();
		
		request.setAttribute("path", path);
		
		//System.out.println("-->url: " +url);
		
		String forwardPage = "/WEB-INF/_test/exam/";
		//if(url.indexOf("01.do") != -1) { // 파일 위치찾기?
		if(url.contains("01.do")) {
			//System.out.println("01.do");
			forwardPage += "01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
			
		} else if(url.contains("01Proc.do")) {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String age_ = request.getParameter("age");
			int age = Integer.parseInt(age_);
			//System.out.println("name: " + name);
			
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("age", age);
					
			forwardPage += "01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
						
		} else if (url.contains("02.do")) {
			//System.out.println("02.do");
			
			forwardPage += "02.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
		
		} else if(url.contains("02Proc.do")) {
			String name = request.getParameter("name");
			String birthYear_ = request.getParameter("birthYear");
			
			int birthYear = Integer.parseInt(birthYear_);
			int nowYear = 2023;
			int age = nowYear - birthYear;
		
			request.setAttribute("name", name); // servelt - jsp파일 변수 공유하려면 객체에 담아서 사용
			request.setAttribute("birthYear", birthYear);
			request.setAttribute("age", age);

			forwardPage += "02Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage); // 주소는 변하는 것없이 jsp파일을 불러옴
			rd.forward(request, response); 
			
			
		} else if(url.contains("03.do")) {
			//System.out.println("03.do");
			
			forwardPage += "03.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
		
		} else if(url.contains("03Proc.do")) {
			String name = request.getParameter("name");
			String jumin_ = request.getParameter("jumin"); // 하이픈 포함
			String address = request.getParameter("address");
			
			String birthYear_ = jumin_.substring(0, 3);
			String gender = jumin_.substring(7,8);
			
			int age = 0;
			if(gender.equals("1") || gender.equals("3")) {
				gender= "남자";
				age = 2023 - (1900 + (Integer.parseInt(birthYear_)));
			} else if(gender.equals("2") || gender.equals("4")) {
				gender="여자";
				age = 2023 - (2000 + (Integer.parseInt(birthYear_)));
			}
			
			request.setAttribute("name", name);
			request.setAttribute("jumin", jumin_);
			request.setAttribute("address", address);
			request.setAttribute("age", age);
			request.setAttribute("gender", gender); // 나이부분 다시 확인하기
			
			forwardPage += "03Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
			
		} else if(url.contains("04.do")) {
			forwardPage += "04.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
			
		} else if(url.contains("04Proc.do")) {
			
			String formCounter_ = request.getParameter("formCounter");
			int formCounter = Integer.parseInt(formCounter_);
			
			ArrayList<String> list = new ArrayList<>();
			for(int i=1; i<=formCounter; i++) {
				String name = request.getParameter("name_" + i);
				String address = request.getParameter("address_" + i);
				String age_ = request.getParameter("age_" + i);
				int age = Integer.parseInt(age_);
				//System.out.println(name + "/" + address + "/" +age_);
				String imsi = name + "|" + address + "|" + age;
				list.add(imsi);
				
			}
			
			request.setAttribute("list", list);
			
			forwardPage += "04Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
		
		} else if(url.contains("05.do")) {
			
			forwardPage += "05.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(url.contains("05Proc.do")) {	
			String[] names = request.getParameterValues("name");
			String name = "";
			for(int i=0;i<names.length;i++) {
				name += "," + names[i];
			}
			name = name.substring(1);
			
			String gender = request.getParameter("gender");
			String aboutAge = request.getParameter("aboutAge");
			
			
			String[] hobbys = request.getParameterValues("hobby");
			String hobby = "";
			for(int i=0;i<hobbys.length;i++) {
				hobby += "," + hobbys[i];
			}
			hobby = hobby.substring(1);
			
			String age_ = request.getParameter("age");
			int age = Integer.parseInt(age_);
			
			String memo = request.getParameter("memo");
			String birthDay = request.getParameter("birthDay");
			
			memo = memo.replace("\n", "<br>");
					
			HashMap<String, String> map = new HashMap<>();
			map.put("name", name);
			map.put("gender", gender);
			map.put("aboutAge", aboutAge);
			map.put("hobby", hobby);
			map.put("age", age+"");
			map.put("memo", memo);
			map.put("birthDay", birthDay);
			
			request.setAttribute("map", map);
			
			forwardPage += "05Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);		
			
		} else if(url.contains("06.do")) {
			
			forwardPage += "06.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(url.contains("06Proc.do")) {
			
			String dbId = "hong";
			String dbPw = "1234";
			
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			
			String loginResult = "X";
			if(id.equals(dbId) && passwd.equals(dbPw)) {
				loginResult = "O";
			}
			
			request.setAttribute("id", id);
			request.setAttribute("result", loginResult);
			
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/_test/exam/06Result1.jsp");
			//rd.forward(request, response);	
		
			response.sendRedirect(path + "/exam_servlet/06Move2.do?id=" + id); // <-페이지 바로 이동시킴
			
		} else if(url.contains("06Move2.do")) {	
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			
			forwardPage += "06Result2.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);	
		
		} else if(url.contains("07.do")) {
			forwardPage += "07.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(url.contains("07Proc.do")) {
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String age_ = request.getParameter("age");
			int age = Integer.parseInt(age_);
			
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("age", age);
			
			forwardPage += "07.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
			
		} else if(url.contains("08.do")) {
			forwardPage += "08.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);
		
		} else if(url.contains("08Proc.do")) {
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String test = request.getParameter("test");
			String age_ = request.getParameter("age");
			String num1_ = request.getParameter("num1");
			String num2_ = request.getParameter("num2");
			String result_ = request.getParameter("result");
			
			int age = Integer.parseInt(age_);
			int num1 = Integer.parseInt(num1_);
			int num2 = Integer.parseInt(num2_);
			int result = Integer.parseInt(result_);
			
			int imsiResult = num1 + num2;
			if(result - imsiResult != 0) {
				response.sendRedirect(path + "/exam_servlet/08.do"); // 에러메시지띄워서 페이지 이동시키거나, 자바스크립트 써서
				/*
				 * request.setAttribute("errorCode", "123123"); request.setAttribute("errorMsg",
				 * "잘못된 값이 넘어옴."); RequestDispatcher rd =
				 * request.getRequestDispatcher("/WEB-INF/_test/exam/error.jsp");
				 * rd.forward(request, response);
				 */
				return;
			}
			
			HashMap<String, String> map = new HashMap<>();
			map.put("name", name);
			map.put("address", address);
			map.put("test", test);
			map.put("age", age+"");
			map.put("num1", num1+"");
			map.put("num2", num2+"");
			map.put("result", result+"");

			request.setAttribute("map", map);
			
			forwardPage += "08Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);		
		
		} else if(url.contains("09.do")) {
			forwardPage += "09.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);	
			
		} else if(url.contains("09Proc.do")) {
			
			String name = request.getParameter("name");
			String kor_ = request.getParameter("kor");
			String eng_ = request.getParameter("eng");
			String mat_ = request.getParameter("mat");
			
			int kor = Integer.parseInt(kor_);
			int eng = Integer.parseInt(eng_);
			int mat = Integer.parseInt(mat_);
			
			int tot = kor+eng+mat;
			double avg = tot / 3.0;
			
			HashMap<String, String> map = new HashMap<>();
			map.put("name", name);
			map.put("kor", kor+"");
			map.put("eng", eng+"");
			map.put("mat", mat+"");
			map.put("tot", tot+"");
			map.put("avg", avg+"");
			
			request.setAttribute("map", map);
			
			forwardPage += "09Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);		
			
			
		} else if(url.contains("json1.do")) {
			
			forwardPage += "json1.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);	
			
		} else if(url.contains("json1Proc.do")) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String age_ = request.getParameter("age");
			int age = Integer.parseInt(age_);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("passwd", passwd);
			jsonObject.put("name", name);
			jsonObject.put("age", age);
			
			String jsonMember =  jsonObject.toString();
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonMember);
			out.flush();
			out.close();

		} else if(url.contains("json2.do")) {
			
			forwardPage += "json2.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);	
		
		} else if(url.contains("json2Proc.do")) {
			
			//입력 값 받고, 공백 널처리하고 숫자로 변환해서 총점 평균 구하고 json객체생성하고 json에 담기
			String name = request.getParameter("name");
			String kor_ = request.getParameter("kor");
			String eng_ = request.getParameter("eng");
			String mat_ = request.getParameter("mat");
			String sci_ = request.getParameter("sci");
			String his_  = request.getParameter("his");
			int kor = Integer.parseInt(kor_);
			int eng = Integer.parseInt(eng_);
			int mat = Integer.parseInt(mat_);
			int sci = Integer.parseInt(sci_);
			int his = Integer.parseInt(his_);
			
			int tot = kor+eng+mat+sci+his;
			double avg = tot / 5.0;
					
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", name);
			jsonObject.put("kor", kor);
			jsonObject.put("eng", eng);
			jsonObject.put("mat", mat);
			jsonObject.put("sci", sci);
			jsonObject.put("his", his);
			jsonObject.put("tot", tot);
			jsonObject.put("avg", avg);
			
			String jsonSj =  jsonObject.toString();
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonSj);
			out.flush();
			out.close();		
			
		} else if(url.contains("attach1.do")) {
			
			forwardPage += "attach1.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);		
			
		} else if(url.contains("attach1Proc.do")) {
			String uploadPath = "C:/ye/attach" + path + "/attachTest";
			int maxUpload = 1024 * 1024 * 10; //10메가
			
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadPath,
					maxUpload,
					"utf-8",
					new DefaultFileRenamePolicy()
					);
			
			String memo = multi.getParameter("memo");
			//System.out.println(memo);
			//	System.out.println(file_1);
			
			String originalName = "-";
			String savedName = "-";
			long fileSize = 0;
			String fileType = "-";
			String mimeType = "-";
			
			java.io.File file_1 = multi.getFile("file_1");
			if(file_1 != null) {
				originalName = multi.getOriginalFileName("file_1");
				savedName = multi.getFilesystemName("file_1");
				fileSize = file_1.length();
				fileType = multi.getContentType("file_1");
				
				Tika tika = new Tika();
				mimeType = tika.detect(file_1);				
				
			}
			/*
			 * System.out.println(originalName); System.out.println(savedName);
			 * System.out.println(fileSize); System.out.println(fileType);
			 * System.out.println(mimeType);
			 */
			
			if(!(fileType.equals(mimeType))) {
				java.io.File f1 = new java.io.File(uploadPath + "/" + savedName);
				if(f1.exists()) {
					f1.delete();
				}
							
				originalName = "-";
				savedName = "-";
				fileSize = 0;
				fileType = "-";
				mimeType = "-";	
			}
			response.sendRedirect(path + "/exam_servlet/attach1.do");
			
		} else if(url.contains("attachWhile.do")) {
			
			forwardPage += "attachWhile.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);		
				
		} else if(url.contains("attachWhileProc.do")) {
			String uploadPath = "C:/ye/attach" + path + "/attachTest";
			int maxUpload = 1024 * 1024 * 10; //10메가
			
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadPath,
					maxUpload,
					"utf-8",
					new DefaultFileRenamePolicy()
					);
			
			String fileCounter_ = multi.getParameter("fileCounter");
			int fileCounter = Integer.parseInt(fileCounter_);
			String[] array = new String[fileCounter];
			
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String originalName = "-";
				String savedName = "-";
				long fileSize = 0;
				String fileType = "-";
				String mimeType = "-";
				
				String tagName = (String) files.nextElement();
				//System.out.println(tagName);
				java.io.File f1 = multi.getFile(tagName);
				if(f1 != null) {
					originalName = multi.getOriginalFileName(tagName);
					savedName = multi.getFilesystemName(tagName);
					fileSize = f1.length();
					fileType = multi.getContentType(tagName);
					
					Tika tika = new Tika();
					mimeType = tika.detect(f1);	
				
					if(!(fileType.equals(mimeType))) {
						java.io.File f2 = new java.io.File(uploadPath + "/" + savedName);
						if(f2.exists()) {
							f2.delete();
						}
									
						originalName = "-";
						savedName = "-";
						fileSize = 0;
						fileType = "-";
						mimeType = "-";	
					}
		
				}
				
				
				
				String info = "";
				info += originalName + "|";
				info += savedName + "|";
				info += fileSize + "|";
				info += fileType + "|";
				info += mimeType;
				
				String idx_ = tagName.replace("file_", "");
				int idx = Integer.parseInt(idx_);
				array[idx] = info;

				/*
				 * System.out.println(originalName); System.out.println(savedName);
				 * System.out.println(fileSize); System.out.println(fileType);
				 * System.out.println(mimeType);
				 */

			}
			
			/*
			 * for(int i=0;i<array.length;i++) { System.out.println(i+ "." + array[i]); }
			 */
			
		} else if(url.contains("attachFor.do")) {
			
			forwardPage += "attachFor.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response);		
				
		} else if(url.contains("attachForProc.do")) {
			String uploadPath = "C:/ye/attach" + path + "/attachTest";
			int maxUpload = 1024 * 1024 * 10; //10메가
			
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadPath,
					maxUpload,
					"utf-8",
					new DefaultFileRenamePolicy()
				);	
			
			String fileCounter_ = multi.getParameter("fileCounter");
			int fileCounter = Integer.parseInt(fileCounter_);
			String[] array = new String[fileCounter];
			
			for(int i=0; i<fileCounter;i++) {
				String originalName = "-";
				String savedName = "-";
				long fileSize = 0;
				String fileType = "-";
				String mimeType = "-";
				
				
				
				String tagName = "file_" + i;
				//System.out.println(i + "." + tagName);
				java.io.File f1 = multi.getFile(tagName);
				
				if(f1 != null) {
					originalName = multi.getOriginalFileName(tagName);
					savedName = multi.getFilesystemName(tagName);
					fileSize = f1.length();
					fileType = multi.getContentType(tagName);
					
					Tika tika = new Tika();
					mimeType = tika.detect(f1);	
					
					if(!fileType.equals(mimeType)) {
						java.io.File f2 = new java.io.File(uploadPath + "/" + savedName);
						if(f2.exists()) {
							f2.delete();
						}
									
						originalName = "-";
						savedName = "-";
						fileSize = 0;
						fileType = "-";
						mimeType = "-";	
					}
				}
				String attachInfo = "";
				attachInfo += originalName + "|";
				attachInfo += savedName + "|";
				attachInfo += fileSize + "|";
				attachInfo += fileType + "|";
				attachInfo += mimeType;
				
				array[i] = attachInfo;
			}
			for(int i=0;i<array.length;i++) {
				System.out.println(array[i]);
			}
			
			response.sendRedirect(path + "/exam_servlet/attachFor.do");
			
			
		} else {
			//System.out.println("없다.");
			forwardPage += "error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
			rd.forward(request, response); 
		}
		
		
		
		
	}

}
