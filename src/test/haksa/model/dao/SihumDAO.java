package test.haksa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import _common.DB;
import test.haksa.model.dto.SihumDTO;

public class SihumDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<SihumDTO> getSelectAll() {
		ArrayList<SihumDTO> list = new ArrayList<>();
		try {
			conn=DB.dbConn();
			String sql = "select * from haksaSihum order by sihum_no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SihumDTO dto = new SihumDTO();
				dto.setSihum_no(rs.getInt("sihum_no"));
				dto.setSihum_name(rs.getString("sihum_name"));
				dto.setSihum_date(rs.getDate("sihum_date"));
				dto.setRegi_date(rs.getDate("regi_Date"));
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public SihumDTO getSelectOne(SihumDTO paramDto) {
		SihumDTO dto = new SihumDTO();
		try {
			conn=DB.dbConn();
			String sql = "select * from haksaSihum where sihum_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getSihum_no());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setSihum_no(rs.getInt("sihum_no"));
				dto.setSihum_name(rs.getString("sihum_name"));
				dto.setSihum_date(rs.getDate("sihum_date"));
				dto.setRegi_date(rs.getDate("regi_Date"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public int setInsert(SihumDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "insert into haksaSihum (sihum_no, sihum_name, sihum_date, regi_date) values (seq_haksaSihum.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getSihum_name());
			pstmt.setDate(2, paramDto.getSihum_date());
			result = pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
		
	}
	
	public int setUpdate(SihumDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "";
			sql += " update haksaSihum set ";
			sql += " sihum_name = ?, sihum_date = ? where sihum_no=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramDto.getSihum_name());
			pstmt.setDate(2, paramDto.getSihum_date());
			pstmt.setInt(3, paramDto.getSihum_no());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
		
	}
	
	public int setDelete(SihumDTO paramDto) {
		int result = 0;
		try {
			conn=DB.dbConn();
			String sql = "delete from haksaSihum where sihum_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getSihum_no());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
		
	}
	
	
	
	
	
	
	
}
