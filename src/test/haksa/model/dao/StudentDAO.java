package test.haksa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import _common.DB;
import test.haksa.model.dto.StudentDTO;

public class StudentDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<StudentDTO> getSelectAll() {
		ArrayList<StudentDTO> list = new ArrayList<>();
		try {
			conn=DB.dbConn();
			String sql = "select * from (select A.*, Rownum as Rnum from (select * from haksaMember order by hakbun desc) A) order by hakbun desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setHakbun(rs.getInt("hakbun"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setParent_phone(rs.getString("parent_phone"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAddr3(rs.getString("addr3"));
				dto.setAddr4(rs.getString("addr4"));
				dto.setRegi_date(rs.getDate("regi_date"));
				list.add(dto);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return list;
		
	}
	
	
	public StudentDTO getSelectOne(StudentDTO paramDto) {
		StudentDTO dto = new StudentDTO();
		try {
			conn=DB.dbConn();
			String sql = "select * from haksaMember where hakbun = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getHakbun());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setHakbun(rs.getInt("hakbun"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setParent_phone(rs.getString("parent_phone"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setAddr3(rs.getString("addr3"));
				dto.setAddr4(rs.getString("addr4"));
				dto.setRegi_date(rs.getDate("regi_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public int setInsert(StudentDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "";
			sql += " insert into haksaMember ";
			sql += " (hakbun, name, phone, parent_phone, addr1, addr2, addr3, addr4, regi_date) ";
			sql += " values (seq_haksaMember.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getName());
			pstmt.setString(2, paramDto.getPhone());
			pstmt.setString(3, paramDto.getParent_phone());
			pstmt.setString(4, paramDto.getAddr1());
			pstmt.setString(5, paramDto.getAddr2());
			pstmt.setString(6, paramDto.getAddr3());
			pstmt.setString(7, paramDto.getAddr4());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}

		return result;
	}
	
	public int setUpdate(StudentDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "";
			sql += " update haksaMember set ";
			sql += " phone = ?, parent_phone=?, addr1 = ?, addr2=?, addr3=?, addr4=? ";
			sql += " where hakbun = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getPhone());
			pstmt.setString(2, paramDto.getParent_phone());
			pstmt.setString(3, paramDto.getAddr1());
			pstmt.setString(4, paramDto.getAddr2());
			pstmt.setString(5, paramDto.getAddr3());
			pstmt.setString(6, paramDto.getAddr4());
			pstmt.setInt(7, paramDto.getHakbun());
			result = pstmt.executeUpdate();
			//System.out.println("result: " +result);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}

		return result;
		
	}
	
	public int setDelete(StudentDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "delete from haksaMember where hakbun = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getHakbun());
			result = pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}

		return result;
		
	}
}
