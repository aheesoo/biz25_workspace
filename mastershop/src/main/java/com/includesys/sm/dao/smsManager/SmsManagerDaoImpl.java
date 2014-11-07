package com.includesys.sm.dao.smsManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.product.Product;
import com.includesys.sm.dto.smsManager.BoardEvent;
import com.includesys.sm.dto.smsManager.Emoticon;
import com.includesys.sm.dto.smsManager.SmsManager;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class SmsManagerDaoImpl implements SmsManagerDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SmsManager> getSmsCustomerTelList(Map<String, Object> map){
		return sqlSession.selectList("SmsManager.getSmsCustomerTelList", map);
	}
	
	@Override
	public int getCallCount(String fk_group_code){
		return sqlSession.selectOne("SmsManager.getCallCount", fk_group_code);
	}
	
	@Override
	public String getUnvailDay(Map<String, Object> map) {
		return sqlSession.selectOne("SmsManager.getUnvailDay", map);
	}
	
	@Override
	public String getSendLimit(String pk_member_id) {
		return sqlSession.selectOne("SmsManager.getSendLimit", pk_member_id);
	}
	
	@Override
	public List<Map<String, Object>> getProductTel(String member_id) {
		return sqlSession.selectList("SmsManager.getProductTel", member_id);	
	}
	
	@Override
	public Coin getCoin(String member_id) {
		return sqlSession.selectOne("SmsManager.getCoin", member_id);
	}
	
	@Override
	public int getCustomerCount(SmsManager smsManager) {
		return (Integer)sqlSession.selectOne("SmsManager.getCustomerCount", smsManager);
	}
	
	@Override
	public List<SmsManager> getCustomerTelList(SmsManager smsManager) {
		return sqlSession.selectList("SmsManager.getCustomerTelList", smsManager);	
	}
	
	@Override
	public SmsManager getSmsManager(String pk_group_code) {
		return sqlSession.selectOne("SmsManager.getSmsManager", pk_group_code);
	}
	
	
	

	@Override
	public int getCountCal(PageHelper pageHelper) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("BoardEvent.getCount",pageHelper);
	}

	@Override
	public List<BoardEvent> getListCal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("BoardEvent.getListCal", map);
	}

	@Override
	public List<BoardEvent> getMemberTelList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("BoardEvent.getMemberTelList",map);
	}

	@Override
	public List<BoardEvent> getSmsGroupList(Map<String, String> smsGroupMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("BoardEvent.getSmsGroupList", smsGroupMap);
	}
	
	@Override
	public List<BoardEvent> getSmsReserveGroupList(Map<String, String> smsGroupMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("BoardEvent.getSmsReserveGroupList", smsGroupMap);
	}

	@Transactional
	@Override
	public int registerSmsGroup(SmsManager smsManager) {
		return sqlSession.update("SmsManager.registerSmsGroup", smsManager);
	}
	
	@Transactional
	@Override
	public int registerSmsReservation(SmsManager smsManager) {
		return sqlSession.update("SmsManager.registerSmsReservation", smsManager);
	}
	
	@Transactional
	@Override
	public int registerCoinUseLog(Map<String, Object> map) {
		return sqlSession.update("SmsManager.registerCoinUseLog", map);
	}
	
	/***********************************************************/
	
	@Transactional
	@Override
	public int modifySmsGroup(SmsManager smsManager) {
		return sqlSession.update("SmsManager.modifySmsGroup", smsManager);
	}
	
	@Transactional
	@Override
	public int modifySmsReservation(SmsManager smsManager) {
		return sqlSession.update("SmsManager.modifySmsReservation", smsManager);
	}
	
	@Transactional
	@Override
	public int modifyCoin(Map<String, Object> map) {
		return sqlSession.update("SmsManager.modifyCoin", map);
	}
	
	@Override
	public Coin getCoinUseLog(SmsManager smsManager) {
		return sqlSession.selectOne("SmsManager.getCoinUseLog", smsManager);
	}
	
	@Transactional
	@Override
	public int modifyCoinUseLog(Map<String, Object> map) {
		return sqlSession.update("SmsManager.modifyCoinUseLog", map);
	}
	
	/************************************************************/
	
	@Transactional
	@Override
	public int cancelationSmsGroup(SmsManager smsManager){
		// TODO Auto-generated method stub
		return sqlSession.update("SmsManager.cancelationSmsGroup", smsManager);
	}
	
	@Transactional
	@Override
	public int cancelationSmsReservation(SmsManager smsManager){
		// TODO Auto-generated method stub
		return sqlSession.update("SmsManager.cancelationSmsReservation", smsManager);
	}
	
	@Transactional
	@Override
	public int cancelationCoinLog(SmsManager smsManager){
		// TODO Auto-generated method stub
		return sqlSession.update("SmsManager.cancelationCoinLog", smsManager);
	}
	
	@Override
	public Coin getSumCancelUseCoin(SmsManager smsManager){
		// TODO Auto-generated method stub
		return sqlSession.selectOne("SmsManager.getSumCancelUseCoin", smsManager);
	}
	
	@Transactional
	@Override
	public int updateCancelCoin(SmsManager smsManager){
		// TODO Auto-generated method stub
		return sqlSession.update("SmsManager.updateCancelCoin", smsManager);
	}
	
	@Transactional
	@Override
	public int copySmsReservation(String fk_member_id){
		// TODO Auto-generated method stub
		return sqlSession.update("SmsManager.copySmsReservation", fk_member_id);
	}
	
	@Transactional
	@Override
	public int deleteSmsReservation(String fk_member_id){
		// TODO Auto-generated method stub
		return sqlSession.delete("SmsManager.deleteSmsReservation", fk_member_id);
	}
	
}
