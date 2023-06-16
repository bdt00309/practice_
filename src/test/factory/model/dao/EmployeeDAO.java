package test.factory.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import _common.DB;
import test.factory.model.dto.EmployeeDTO;

public class EmployeeDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int getTotalRecord(String searchGubun, String searchData) {
		int result = 0;
		try {
			conn = DB.dbConn();
			String sql = " select count(*)counter from employee where 1=1 ";
			
			if(searchGubun.equals("name")) {
				sql += " and name like ? ";
			} else if(searchGubun.equals("phone")) {
				sql += " and phone like ? ";
			} else if(searchGubun.equals("name_phone")) {
				sql += " and (name like ? or phone like ?) ";
			} else { 
				
			}
			pstmt=conn.prepareStatement(sql);
			if(searchGubun.equals("name")) {
				pstmt.setString(1, '%' + searchData + '%');
				
			} else if(searchGubun.equals("phone")) {
				pstmt.setString(1, '%' + searchData + '%');
				
			} else if(searchGubun.equals("name_phone")) {
				pstmt.setString(1, '%' + searchData + '%');
				pstmt.setString(2, '%' + searchData + '%');
				
			} else { 
				
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("counter");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public List<EmployeeDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord ) {
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
		
		List<EmployeeDTO> list = new ArrayList<>();
		try {
			conn=DB.dbConn();
			
			String basicSql = " select * from buseo join employee on buseo.buseoNo = employee.buseoNo ";
			basicSql += " join position on employee.positionNo = position.positionNo ";
			
			//----------------------------------------------------
			if(searchGubun.equals("name")) {
				basicSql += " and employee.name like ? ";
			} else if(searchGubun.equals("phone")) {
				basicSql += " and employee.phone like ? ";
			} else if(searchGubun.equals("name_phone")) {
				basicSql += " and (employee.name like ? or employee.phone like ?) ";
			} else { 
				
			}
			basicSql += " order by sabun desc ";
			
			
			
			String sql  = "";
			sql += " select * from (select A.*, Rownum Rnum from ( ";
			sql += basicSql;
			sql += " ) A) ";
			sql += " where Rnum >=? and Rnum <=? ";
			
			
			pstmt = conn.prepareStatement(sql);
			
			int k=0;
			if(searchGubun.equals("name")) {
				pstmt.setString(++k, '%' + searchData + '%');
			} else if(searchGubun.equals("phone")) {
				pstmt.setString(++k, '%' + searchData + '%');
			} else if(searchGubun.equals("name_phone")) {
				pstmt.setString(++k, '%' + searchData + '%');
				pstmt.setString(++k, '%' + searchData + '%');
				
			} else { 
				
			}
			pstmt.setInt(++k, startRecord);
			pstmt.setInt(++k, lastRecord);
			//----------------------------------------------------
			rs= pstmt.executeQuery();
			while(rs.next()) {
				EmployeeDTO dto = new EmployeeDTO();
				dto.setSabun(rs.getInt("sabun"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setHireDate(rs.getDate("hireDate"));
				dto.setSalary(rs.getInt("salary"));
				dto.setBuseoNo(rs.getInt("buseoNo"));
				dto.setBuseoName(rs.getString("buseoName"));
				dto.setPositionNo(rs.getInt("positionNo"));
				dto.setPositionName(rs.getString("positionName"));
				dto.setRegiDate(rs.getDate("regiDate"));
				
				list.add(dto);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public EmployeeDTO getSelectOne(EmployeeDTO paramDto) {
		EmployeeDTO dto = new EmployeeDTO();
		try {
			conn=DB.dbConn();
			String sql = "select * from buseo join employee on buseo.buseoNo = employee.buseoNo join position on employee.positionNo = position.positionNo where sabun=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getSabun());
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setSabun(rs.getInt("sabun"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setHireDate(rs.getDate("hireDate"));
				dto.setSalary(rs.getInt("salary"));
				dto.setBuseoNo(rs.getInt("buseoNo"));
				dto.setBuseoName(rs.getString("buseoName"));
				dto.setPositionNo(rs.getInt("positionNo"));
				dto.setPositionName(rs.getString("positionName"));;
				dto.setRegiDate(rs.getDate("regiDate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public int setInsert(EmployeeDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "";
			sql += " insert into employee ";
			sql += " (sabun, name, phone, email, hireDate, salary, buseoNo, positionNo, regiDate) values ";
			sql += " (seq_employee.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate) ";	
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getName());
			pstmt.setString(2, paramDto.getPhone());
			pstmt.setString(3, paramDto.getEmail());
			pstmt.setDate(4, paramDto.getHireDate());
			pstmt.setInt(5, paramDto.getSalary());
			pstmt.setInt(6, paramDto.getBuseoNo());
			pstmt.setInt(7, paramDto.getPositionNo());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int setUpdate(EmployeeDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = " update employee set ";
			sql += " name = ?, phone=?, email=?, hireDate=?, salary=?, buseoNo=?, positionNo=?, where sabun=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getName());
			pstmt.setString(2, paramDto.getPhone());
			pstmt.setString(3, paramDto.getEmail());
			pstmt.setDate(4, paramDto.getHireDate());
			pstmt.setInt(5, paramDto.getSalary());
			pstmt.setInt(6, paramDto.getBuseoNo());
			pstmt.setInt(7, paramDto.getPositionNo());
			pstmt.setInt(8, paramDto.getSabun());
			result = pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int setDelete(EmployeeDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "delete from employee where sabun=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getSabun());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	
	
	
}
