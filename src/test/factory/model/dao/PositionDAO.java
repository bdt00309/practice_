package test.factory.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import _common.DB;
import _mybatisConfig.MybatisManager;
import test.factory.model.dto.PositionDTO;
import test.memo.model.dto.MemoDTO;

public class PositionDAO {

	public List<PositionDTO> getSelectAll() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<PositionDTO> list = session.selectList("position.getSelectAll");
		session.close();
		return list;
	}
	
	public PositionDTO getSelectOne(PositionDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		PositionDTO dto = session.selectOne("position.getSelectOne", paramDto);
		session.close();
		return dto;
	}
	
	public int setInsert(PositionDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("position.setInsert", paramDto);
		session.commit();
		session.close();
		return result;
	}
	
	public int setUpdate(PositionDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("position.setUpdate", paramDto);
		session.commit();
		session.close();
		return result;
	}
	
	public int setDelete(PositionDTO paramDto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("position.setDelete", paramDto);
		session.commit();
		session.close();
		return result;
	}
}
