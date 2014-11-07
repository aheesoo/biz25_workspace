package com.includesys.sm.service.member;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.member.MemberDao;
import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.member.Code;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.member.MemberSub;
import com.includesys.sm.dto.member.Zipcode;
import com.includesys.sm.dto.member.ZipcodeDoro;

@Service
public class MemberServiceImpl implements MemberService{
	
	//service클래스의 역할 : 필요한 dao호출

		private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

		@Autowired
		private MemberDao memberDao;
		
		@Override
		public int getCount(PageHelper pageHelper) 	{
			return memberDao.getCount(pageHelper);
		}
		
		@Override
		public List<Member> getList(PageHelper pageHelper) 	{
			return memberDao.getList(pageHelper);
		}

		@Override
		public String checkId(String userId) 	{
			return memberDao.checkId(userId);
		}
		
		@Override
		public void register(Member member) 	{
			memberDao.register(member);
		}

		@Override
		public List<Zipcode> searchZipcode(String searchDong){		
			return memberDao.searchZipcode(searchDong);
		}
		
		@Override
		public List<ZipcodeDoro> searchZipcodeDoro(ZipcodeDoro zipcodeDoro){
			return memberDao.searchZipcodeDoro(zipcodeDoro);
		}
		
		@Override
		public Member get(String pk_member_id) 	{
			return memberDao.get(pk_member_id);
		}
		

		@Override
		public String getBusinessName(Map<String, Object> map){
			return memberDao.getBusinessName(map);
		}
		
		@Override
		public List<MemberSub> getTelInfoList(PageHelper pageHelper) {
			return memberDao.getTelInfoList(pageHelper);
		}
		
		@Override
		public Member getDetail(String pk_member_id) 	{
			return memberDao.getDetail(pk_member_id);
		}
		
		@Override
		public void modify(Member member) 	{
			memberDao.modify(member);
		}
		
		@Override
		public void insAgreement(String member_id){
			memberDao.insAgreement(member_id);
		}
		
		@Override
		public void remove(String pk_member_id) 	{
			memberDao.remove(pk_member_id);
		}

		@Override
		public String checkPw(String pk_member_id) {
			return memberDao.checkPw(pk_member_id);
		}

		@Override
		public void changePw(Member member) {
			memberDao.changePw(member);		
		}

		@Override
		public List<Code> getGubun() {		
			return memberDao.getGubun();
		}
		
		/**
		 * 해당 로그인 회원의 가입정보를 가지고 온다.
		 * 내정보 > 가입정보
		 */
		@Override
		public Member getJoinInfo(String pk_member_id){
			return memberDao.getJoinInfo(pk_member_id);
		}
		
		@Override
		public int getAgreeCount(String pk_member_id){
			return memberDao.getAgreeCount(pk_member_id);
		}
		
		/**
		 * 포인트 충전내역을 가지고 온다.
		 * 내정보 > 포인트 충전내역
		 */
		public List<Map<String, Object>> getPointChargeLogList(String pk_member_id) {
			return memberDao.getPointChargeLogList(pk_member_id);
		}
		
		@Override
		public Map<String, Object> checkPay(String pk_member_id) {
			return memberDao.checkPay(pk_member_id);
		}
		
	/*
	@Autowired
	private MemberDao membersDao;
	
	@Override
	public boolean insert(Member dto){
		int n = membersDao.insert(dto);
		if (n>0){
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean delete(String id){
		int n = membersDao.delete(id);
		if (n>0){
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public Member getInfo(String id){
		return membersDao.getInfo(id);
	}
	
	@Override
	public List<Member> getList(){
		return membersDao.getList();
	}
	*/
}
