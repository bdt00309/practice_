package test.guestBook.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import _mybatisConfig.MybatisManager;
import test.guestBook.model.dto.GuestBookDTO;

public class GuestBookDAO {
	
	
	public int getTotalRecord(String searchGubun, String searchData) {
		Map<String, String> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("guestBook.getTotalRecord", map);
		return result;
	}
	
	

	public List<GuestBookDTO> getSelectAll(int startRecord, int lastRecord, String searchGubun, String searchData) {
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord+"");
		map.put("lastRecord", lastRecord+"");
		map.put("searchData", searchData);
		map.put("searchData", searchData);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<GuestBookDTO> list = session.selectList("guestBook.getSelectAll", map);
		session.close();
		return list;
	}
	
	public GuestBookDTO getSelectOne(GuestBookDTO paramDto) {
		Map<String, Object> map = new HashMap<>();
		map.put("paramDto", paramDto);
				
		SqlSession session = MybatisManager.getInstance().openSession();
		GuestBookDTO dto = session.selectOne("guestBook.getSelectOne", map);
		session.close();
		return dto;
	}
	
	public int setInsert(GuestBookDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("guestBook.setInsert", paramDto);  // session.insert("네임스페이스.아이디", 가지고 갈 값);
		session.commit();
		session.close();
		return result;
	}
	
	public int setUpdate(GuestBookDTO paramDto) {
		Map<String, Object> map = new HashMap<>();
		map.put("paramDto", paramDto);
		map.put("tableName", "guestBook");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("guestBook.setUpdate", map);
		session.commit();
		session.close();
		return result;
	}
	
	public int setDelete(GuestBookDTO paramDto) {
		Map<String, Object> map = new HashMap<>();
		map.put("paramDto", paramDto);
		map.put("tableName", "guestBook");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("guestBook.setDelete", map);
		session.commit();
		session.close();
		return result;
		
	}
	
	
	
}
