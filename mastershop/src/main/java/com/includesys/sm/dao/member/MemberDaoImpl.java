package com.includesys.sm.dao.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.member.Code;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.member.MemberSub;
import com.includesys.sm.dto.member.Zipcode;
import com.includesys.sm.dto.member.ZipcodeDoro;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getCount(PageHelper pageHelper) 	{
		return Integer.parseInt(sqlSession.selectOne("Member.getCount", pageHelper).toString());
	}

	@Override
	public List<Member> getList(PageHelper pageHelper) 	{
		return sqlSession.selectList("Member.getList", pageHelper);
	}
	
	@Override
	public String checkId(String userId) 	{
		return (String) sqlSession.selectOne("Member.checkId", userId);
	}
	
	@Override
	public void register(Member member) 	{
		sqlSession.insert("Member.register", member);
	}

	@Override
	public List<Zipcode> searchZipcode(String searchDong) {
		return sqlSession.selectList("Member.searchZipcode", searchDong);
	}

	@Override
	public List<ZipcodeDoro> searchZipcodeDoro(ZipcodeDoro zipcodeDoro){
		return sqlSession.selectList("Member.searchZipcodeDoro", zipcodeDoro);
	}
	
	@Override
	public Member get(String pk_member_id) 	{
		return (Member) sqlSession.selectOne("Member.get", pk_member_id);
	}

	@Override
	public String getBusinessName(Map<String, Object> map){
		return (String) sqlSession.selectOne("Member.getBusinessName", map);
	}
	
	@Override
	public List<MemberSub> getTelInfoList(PageHelper pageHelper) {
		return sqlSession.selectList("Member.getTelInfoList", pageHelper);
	}
	
	@Override
	public Member getDetail(String pk_member_id) 	{
		return (Member) sqlSession.selectOne("Member.getDetail", pk_member_id);
	}
	
	@Override
	public void modify(Member member) 	{
		sqlSession.update("Member.modify", member);
	}
	
	@Override
	public void insAgreement(String member_id){
		sqlSession.insert("Member.insAgreement", member_id);
	}
	
	@Override
	public String checkPw(String pk_member_id)	{
		return (String) sqlSession.selectOne("Member.checkPw", pk_member_id);
	}

	@Override
	public void changePw(Member member) 	{
		sqlSession.update("Member.changePw", member);
	}	
	
	@Override
	public void remove(String pk_member_id) 	{
		sqlSession.delete("Member.remove", pk_member_id);
	}

	@Override
	public Map<String, Object> checkPay(String pk_member_id)	{
		return (Map<String, Object>) sqlSession.selectOne("Member.checkPay", pk_member_id);
	}
	
	@Override
	public List<Code> getGubun() {		
		return sqlSession.selectList("Member.getGubun");	
	}
	
	/**
	 * 해당 로그인 회원의 가입정보를 가지고 온다.
	 * 내정보 > 가입정보
	 */
	@Override
	public Member getJoinInfo(String pk_member_id) {
		return sqlSession.selectOne("Member.getJoinInfo", pk_member_id);	
	}
	
	@Override
	public int getAgreeCount(String pk_member_id) {
		return sqlSession.selectOne("Member.getAgreeCount", pk_member_id);	
	}
	
	/**
	 * 내정보 > 포인트 충전 내역
	 * @param map
	 * @return List<Point>
	 */
	public List<Map<String, Object>> getPointChargeLogList(String pk_member_id) {
		return sqlSession.selectList("Member.getPointChargeLogList", pk_member_id);
	}
	
	/*
	@Override
	public int insert(Member dto){
		return sqlSession.insert("test.mybatis.MemberMapper.add", dto);
	}
	@Override
	public int delete(String id){
		return sqlSession.delete("test.mybatis.MemberMapper.remove", id);
	}
	@Override
	public Member getInfo(String id){
		return sqlSession.selectOne("test.mybatis.MemberMapper.getInfo", id);
	}
	@Override
	public List<Member> getList(){
		return sqlSession.selectList("test.mybatis.MemberMapper.listAll");
	}
	
	*/
	
	
	
	
}
