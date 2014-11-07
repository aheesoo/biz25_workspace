package com.includesys.sm.dao.member;

import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.member.Code;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.member.MemberSub;
import com.includesys.sm.dto.member.Zipcode;
import com.includesys.sm.dto.member.ZipcodeDoro;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
public interface MemberDao {

	public int getCount(PageHelper pageHelper);
	
	public List<Member> getList(PageHelper pageHelper);
	
	public String checkId(String userId);

	public void register(Member member);
	
	public List<Zipcode> searchZipcode(String searchDong);
	
	public List<ZipcodeDoro> searchZipcodeDoro(ZipcodeDoro zipcodeDoro);
	
	public Member get(String pk_member_id);
	
	public String getBusinessName(Map<String, Object> map);
	
	public List<MemberSub> getTelInfoList(PageHelper pageHelper);
	
	public Member getDetail(String pk_member_id);

	public void modify(Member member);
	
	public void insAgreement(String member_id);
	
	public String checkPw(String pk_member_id);
	
	public void changePw(Member member);
	
	public void remove(String pk_member_id);

	public Map<String, Object> checkPay(String pk_member_id);
	
	public List<Code> getGubun();
	
	/**
	 * 내정보 > 가입정보
	 * @param pk_member_id
	 * @return Member
	 */
	public Member getJoinInfo(String pk_member_id);
	
	public int getAgreeCount(String pk_member_id);
	
	/**
	 * 내정보 > 포인트 충전 내역
	 * @param pk_member_id
	 * @return List<Point>
	 */
	public List<Map<String, Object>> getPointChargeLogList(String pk_member_id);
	
	
	/*
	public int insert(Member dto);
	public int delete(String id);
	public Member getInfo(String id);
	public List<Member> getList();
	*/
	
	
}
