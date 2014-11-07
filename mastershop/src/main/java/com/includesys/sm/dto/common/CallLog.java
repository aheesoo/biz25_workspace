package com.includesys.sm.dto.common;

public class CallLog {
/*
//tbl_call_log
pk_rcv_tel	varchar(14)	utf8_general_ci	NO	PRI			select,insert,update,references	수신 전화번호
fk_tel	varchar(14)	utf8_general_ci	NO	PRI			select,insert,update,references	로그인 전화번호(통화오픈API 번호)
fk_member_id	varchar(32)	utf8_general_ci	NO	PRI			select,insert,update,references	로그인 ID
fd_seqno	int(9)		NO	PRI	0		select,insert,update,references	통화오픈 API일련번호
fd_search_time	varchar(1)	utf8_general_ci	YES				select,insert,update,references	시간대 A-Z
fd_reg_yy	varchar(4)	utf8_general_ci	YES				select,insert,update,references	수신 년도
fd_reg_week	int(1)		YES				select,insert,update,references	수신요일구분 1~7 (일요일~토요일)
fd_reg_ss	varchar(2)	utf8_general_ci	YES				select,insert,update,references	수신 초
fd_reg_mm	varchar(2)	utf8_general_ci	YES				select,insert,update,references	수신 월
fd_reg_mi	varchar(2)	utf8_general_ci	YES				select,insert,update,references	수신 분
fd_reg_hh	varchar(2)	utf8_general_ci	YES				select,insert,update,references	수신 시각
fd_reg_dd	varchar(2)	utf8_general_ci	YES				select,insert,update,references	수신 일
fd_openapi_skind	int(1)		YES				select,insert,update,references	통화내역리스트 요청 내역 종류
fd_openapi_rc_code	varchar(4)	utf8_general_ci	YES				select,insert,update,references	통화오픈api_수신상태 코드
fd_call_date	datetime		YES		0000-00-00 00:00:00		select,insert,update,references	통화 시작일시

//tbl_call_memo
pk_rcv_tel	varchar(14)	utf8_general_ci	NO	PRI			select,insert,update,references	수신 전화번호
fk_tel	varchar(14)	utf8_general_ci	NO	PRI			select,insert,update,references	로그인 전화번호(통화오픈API 번호)
fk_member_id	varchar(32)	utf8_general_ci	NO	PRI			select,insert,update,references	로그인 ID
fd_seqno	int(9)		NO	PRI	0		select,insert,update,references	통화오픈 API일련번호
fd_memo	varchar(2000)	utf8_general_ci	YES				select,insert,update,references	
*/
	//tbl_call_log
	String pk_seqno;			//통화오픈api_일련번호
	String fd_rcv_tel;			//로그인 전화번호(통화오픈API 번호)
	String fd_search_time;		//시간대 A-Z
	String fd_tel;				//수신 전화번호
	String fd_member_id;		//로그인 ID
	String fd_reg_yy;			//수신 년도
	String fd_reg_week;			//수신요일구분 1~7 (일요일~토요일)
	String fd_reg_ss;			//수신 초
	String fd_reg_mm;			//수신 월
	String fd_reg_mi;			//수신 분
	String fd_reg_hh;			//수신 시각
	String fd_reg_dd;			//수신 일
	String fd_openapi_skind;	//통화내역리스트 요청 내역 종류
	String fd_openapi_rc_code;	//통화오픈api_수신상태 코드
	String fd_reg_date;			//통화 시작일시

	
	//tbl_call_memo
	String fk_seq;
	String fd_memo;				//메모

	
	//tbl_customer
	String fd_view_flag;		//보기옵션 0:보기 1:숨김
	String fd_user_name;		//고객 이름
	String fd_rev_sms_flag;		//수신거부 거부:Y 승락:N (기본값:N)
	String fd_new_date;			//최초 등록 년월일 ex:20140101(신규고객 대상추출)
	String fd_mod_date;			//정보 수정일
	String fd_memo_cus;			//고객 메모
	String fd_addr;				//주소
	
	
	public String getPk_seqno() {
		return pk_seqno;
	}
	public void setPk_seqno(String pk_seqno) {
		this.pk_seqno = pk_seqno;
	}
	public String getFd_rcv_tel() {
		return fd_rcv_tel;
	}
	public void setFd_rcv_tel(String fd_rcv_tel) {
		this.fd_rcv_tel = fd_rcv_tel;
	}
	public String getFd_search_time() {
		return fd_search_time;
	}
	public void setFd_search_time(String fd_search_time) {
		this.fd_search_time = fd_search_time;
	}
	public String getFd_tel() {
		return fd_tel;
	}
	public void setFd_tel(String fd_tel) {
		this.fd_tel = fd_tel;
	}
	public String getFd_member_id() {
		return fd_member_id;
	}
	public void setFd_member_id(String fd_member_id) {
		this.fd_member_id = fd_member_id;
	}
	public String getFd_reg_yy() {
		return fd_reg_yy;
	}
	public void setFd_reg_yy(String fd_reg_yy) {
		this.fd_reg_yy = fd_reg_yy;
	}
	public String getFd_reg_week() {
		return fd_reg_week;
	}
	public void setFd_reg_week(String fd_reg_week) {
		this.fd_reg_week = fd_reg_week;
	}
	public String getFd_reg_ss() {
		return fd_reg_ss;
	}
	public void setFd_reg_ss(String fd_reg_ss) {
		this.fd_reg_ss = fd_reg_ss;
	}
	public String getFd_reg_mm() {
		return fd_reg_mm;
	}
	public void setFd_reg_mm(String fd_reg_mm) {
		this.fd_reg_mm = fd_reg_mm;
	}
	public String getFd_reg_mi() {
		return fd_reg_mi;
	}
	public void setFd_reg_mi(String fd_reg_mi) {
		this.fd_reg_mi = fd_reg_mi;
	}
	public String getFd_reg_hh() {
		return fd_reg_hh;
	}
	public void setFd_reg_hh(String fd_reg_hh) {
		this.fd_reg_hh = fd_reg_hh;
	}
	public String getFd_reg_dd() {
		return fd_reg_dd;
	}
	public void setFd_reg_dd(String fd_reg_dd) {
		this.fd_reg_dd = fd_reg_dd;
	}
	public String getFd_openapi_skind() {
		return fd_openapi_skind;
	}
	public void setFd_openapi_skind(String fd_openapi_skind) {
		this.fd_openapi_skind = fd_openapi_skind;
	}
	public String getFd_openapi_rc_code() {
		return fd_openapi_rc_code;
	}
	public void setFd_openapi_rc_code(String fd_openapi_rc_code) {
		this.fd_openapi_rc_code = fd_openapi_rc_code;
	}
	public String getFd_reg_date() {
		return fd_reg_date;
	}
	public void setFd_reg_date(String fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}
	public String getFd_memo() {
		return fd_memo;
	}
	public void setFd_memo(String fd_memo) {
		this.fd_memo = fd_memo;
	}
	public String getFd_view_flag() {
		return fd_view_flag;
	}
	public void setFd_view_flag(String fd_view_flag) {
		this.fd_view_flag = fd_view_flag;
	}
	public String getFd_user_name() {
		return fd_user_name;
	}
	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}
	public String getFd_rev_sms_flag() {
		return fd_rev_sms_flag;
	}
	public void setFd_rev_sms_flag(String fd_rev_sms_flag) {
		this.fd_rev_sms_flag = fd_rev_sms_flag;
	}
	public String getFd_new_date() {
		return fd_new_date;
	}
	public void setFd_new_date(String fd_new_date) {
		this.fd_new_date = fd_new_date;
	}
	public String getFd_mod_date() {
		return fd_mod_date;
	}
	public void setFd_mod_date(String fd_mod_date) {
		this.fd_mod_date = fd_mod_date;
	}
	public String getFd_memo_cus() {
		return fd_memo_cus;
	}
	public void setFd_memo_cus(String fd_memo_cus) {
		this.fd_memo_cus = fd_memo_cus;
	}
	public String getFd_addr() {
		return fd_addr;
	}
	public void setFd_addr(String fd_addr) {
		this.fd_addr = fd_addr;
	}
	public String getFk_seq() {
		return fk_seq;
	}
	public void setFk_seq(String fk_seq) {
		this.fk_seq = fk_seq;
	}



}