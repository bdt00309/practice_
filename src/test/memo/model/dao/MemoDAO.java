package test.memo.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import _mybatisConfig.MybatisManager;
import test.memo.model.dto.MemoDTO;

public class MemoDAO {

	public List<MemoDTO> getSelectAll() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = session.selectList("memo.getSelectAll");
		session.close();
		return list;
	}
	
	public MemoDTO getSelectOne(MemoDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		MemoDTO dto = session.selectOne("memo.getSelectOne", paramDto);
		session.close();
		return dto;
	}
	
	public int setInsert(MemoDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("memo.setInsert", paramDto);
		session.commit();
		session.close();
		return result;
	}
	
	public int setUpdate(MemoDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("memo.setUpdate", paramDto);
		session.commit();
		session.close();
		return result;
	}
	
	public int setDelete(MemoDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("memo.setDelete", paramDto);
		session.commit();
		session.close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
