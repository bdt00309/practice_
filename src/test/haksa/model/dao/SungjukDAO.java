package test.haksa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import _common.DB;
import test.haksa.model.dto.SungjukDTO;

public class SungjukDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<SungjukDTO> getSelectAll() {
		ArrayList<SungjukDTO> list = new ArrayList<>();
		try {
			conn = DB.dbConn();
			String sql="select j.*, (select name from haksaMember m where m.hakbun=j.hakbun)name, (select sihum_name from haksaSihum h where h.sihum_no = j.sihum_no )sihum_name from haksaSungjuk j order by sungjuk_no desc";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SungjukDTO dto = new SungjukDTO();
				dto.setSungjuk_no(rs.getInt("sungjuk_no"));
				dto.setHakbun(rs.getInt("hakbun"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setSci(rs.getInt("sci"));
				dto.setHis(rs.getInt("his"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getDouble("avg"));
				dto.setGrade(rs.getString("grade"));
				dto.setSihum_name(rs.getString("sihum_name"));
				dto.setRegi_date(rs.getDate("regi_date"));
				
				list.add(dto);
			}
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public SungjukDTO getSelectOne(SungjukDTO paramDto) {
		SungjukDTO dto = new SungjukDTO();
		try {
			conn = DB.dbConn();
			String sql="select j.*, (select name from haksaMember m where m.hakbun=j.hakbun)name, (select sihum_name from haksaSihum h where h.sihum_no = j.sihum_no )sihum_name from haksaSungjuk j where sungjuk_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getSungjuk_no());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setSungjuk_no(rs.getInt("sungjuk_no"));
				dto.setHakbun(rs.getInt("hakbun"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setSci(rs.getInt("sci"));
				dto.setHis(rs.getInt("his"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getDouble("avg"));
				dto.setGrade(rs.getString("grade"));
				dto.setSihum_name(rs.getString("sihum_name"));
				dto.setRegi_date(rs.getDate("regi_date"));
				
			}
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return dto;
		
	}
	
	public int setInsert(SungjukDTO paramDto) {
		int result = 0;
		try {
			conn = DB.dbConn();
			String sql = "";
			sql += "insert into haksaSungjuk ";
			sql += " (sungjuk_no, kor, eng, mat, sci, his, tot, avg, grade, hakbun, sihum_no, regi_date) ";
			sql += " values (seq_haksaSungjuk.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getKor());
			pstmt.setInt(2, paramDto.getEng());
			pstmt.setInt(3, paramDto.getMat());
			pstmt.setInt(4, paramDto.getSci());
			pstmt.setInt(5, paramDto.getHis());
			pstmt.setInt(6, paramDto.getTot());
			pstmt.setDouble(7, paramDto.getAvg());
			pstmt.setString(8, paramDto.getGrade());
			pstmt.setInt(9, paramDto.getHakbun());
			pstmt.setInt(10, paramDto.getSihum_no());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int setUpdate(SungjukDTO paramDto) {
		int result=0;
		try {
			conn = DB.dbConn();
			String sql="update haksaSungjuk set kor = ?, eng=?, mat=?, sci=?, his=? where sungjuk_no = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getKor());
			pstmt.setInt(2, paramDto.getEng());
			pstmt.setInt(3, paramDto.getMat());
			pstmt.setInt(4, paramDto.getSci());
			pstmt.setInt(5, paramDto.getHis());
			pstmt.setInt(6, paramDto.getSungjuk_no());
			result = pstmt.executeUpdate();
			//System.out.println(result);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int setDelete(SungjukDTO paramDto) {
		int result=0;
		try {
			conn = DB.dbConn();
			String sql="delete from haksaSungjuk where sungjuk_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, paramDto.getSungjuk_no());
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DB.dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	

}
