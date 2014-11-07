package com.includesys.sm.dto.smsManager;

public class BoardEvent {
	
	/* tbl_board_event, tbl_member_tel 공통 */
	private String fd_reg_date;		/* 등록일 */
	
	/* tbl_member_tel, tbl_sms_group 공통 */
	private String fk_member_id;		/* 아이디 */
	
	/* Table : tbl_boad_event */
	private String pk_seq;				/* 일련번호 */
	private String fd_title;				/* 타이틀 */
	private String fd_user_id;			/* 작성자아이디 */
	private String fd_user_ip;			/* 작성자아이피 */
	private String fd_yy;				/* 적용날짜_년 */
	private String fd_mm;				/* 적용날짜_월 */
	private String fd_dd;				/* 적용날짜_일 */
	private String fd_mod_id;			/* 수정자아이디 */
	private String fd_mod_ip;			/* 수정자아이피*/
	private String fd_mod_date;		/* 수정일짜 */
     
	/* Table : tbl_member_tel */
	private String pk_tel;				/* 전화번호 */
	private String fd_use_yn;			/* 사용여부 */
	private String fd_del_date;		/* 삭제일 */
	
	/* Table : tbl_sms_group */
	private String pk_group_code;					/* 그룹코드 */
	private String fk_custom_msg_id;				/* 메세지아이디 */
	private String search_week;						/* 동보전송 추출조건 1~7 (일~토) 구분자:^ */
	private String search_time;						/* 동보전송 추출조건 A~G (새벽~야간) 구분자:^ */
	private String search_req_cnt;					/* 발송 요청 건수 (원하는건수) */
	private String search_month;					/* 동보전송 추출조건 추출기간 (월) */
	private String search_call_rcv_cnt_type;		/* 추출조건 수신횟수 -1:이하 , 1:이상 */
	private String search_call_rcv_cnt;			/* 추출조건 수신 건수 */
	private String search_customer;				/* 동보전송 추출조건 고객타입 1:전체 2:단골고객 3:신규고객 -1:미사용 */
	private String fk_tel;								/* 회신 번호 (보내는번호) */
	private String reserve_time;						/* 발송요청 시간 (년월일시분) */
	private String reserve_type;						/* 예약 구분 1:즉시 발송 2:예약 */
	private String msg_type;							/* 메시지 요청 타입 sms(1:subtype=1) lms(4:subtype=1) mms (4:subtype=2~4) */
	private String msg_sub_type;					/* 메시지 세부유형 1:text 2:url 3:이미지 4:오디오 */
	private String reg_date;							/* 등록일 */
	private String msg_send_finish;					/* 전송완료여부 기본값:N  전송완료:Y */
	private String req_count;							/* 동보 요청 건수 */
	private String res_count;							/* 전송 요청한 수신 건수 */
	private String cancel_yn;							/* 발송 취소여부 기본값:N 취소시:Y */
	
	public String getFd_reg_date() {
		return fd_reg_date;
	}
	public void setFd_reg_date(String fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}
	public String getFk_member_id() {
		return fk_member_id;
	}
	public void setFk_member_id(String fk_member_id) {
		this.fk_member_id = fk_member_id;
	}
	public String getPk_seq() {
		return pk_seq;
	}
	public void setPk_seq(String pk_seq) {
		this.pk_seq = pk_seq;
	}
	public String getFd_title() {
		return fd_title;
	}
	public void setFd_title(String fd_title) {
		this.fd_title = fd_title;
	}
	public String getFd_user_id() {
		return fd_user_id;
	}
	public void setFd_user_id(String fd_user_id) {
		this.fd_user_id = fd_user_id;
	}
	public String getFd_user_ip() {
		return fd_user_ip;
	}
	public void setFd_user_ip(String fd_user_ip) {
		this.fd_user_ip = fd_user_ip;
	}
	public String getFd_yy() {
		return fd_yy;
	}
	public void setFd_yy(String fd_yy) {
		this.fd_yy = fd_yy;
	}
	public String getFd_mm() {
		return fd_mm;
	}
	public void setFd_mm(String fd_mm) {
		this.fd_mm = fd_mm;
	}
	public String getFd_dd() {
		return fd_dd;
	}
	public void setFd_dd(String fd_dd) {
		this.fd_dd = fd_dd;
	}
	public String getFd_mod_id() {
		return fd_mod_id;
	}
	public void setFd_mod_id(String fd_mod_id) {
		this.fd_mod_id = fd_mod_id;
	}
	public String getFd_mod_ip() {
		return fd_mod_ip;
	}
	public void setFd_mod_ip(String fd_mod_ip) {
		this.fd_mod_ip = fd_mod_ip;
	}
	public String getFd_mod_date() {
		return fd_mod_date;
	}
	public void setFd_mod_date(String fd_mod_date) {
		this.fd_mod_date = fd_mod_date;
	}
	public String getPk_tel() {
		return pk_tel;
	}
	public void setPk_tel(String pk_tel) {
		this.pk_tel = pk_tel;
	}
	public String getFd_use_yn() {
		return fd_use_yn;
	}
	public void setFd_use_yn(String fd_use_yn) {
		this.fd_use_yn = fd_use_yn;
	}
	public String getFd_del_date() {
		return fd_del_date;
	}
	public void setFd_del_date(String fd_del_date) {
		this.fd_del_date = fd_del_date;
	}
	public String getPk_group_code() {
		return pk_group_code;
	}
	public void setPk_group_code(String pk_group_code) {
		this.pk_group_code = pk_group_code;
	}
	public String getFk_custom_msg_id() {
		return fk_custom_msg_id;
	}
	public void setFk_custom_msg_id(String fk_custom_msg_id) {
		this.fk_custom_msg_id = fk_custom_msg_id;
	}
	public String getSearch_week() {
		return search_week;
	}
	public void setSearch_week(String search_week) {
		this.search_week = search_week;
	}
	public String getSearch_time() {
		return search_time;
	}
	public void setSearch_time(String search_time) {
		this.search_time = search_time;
	}
	public String getSearch_req_cnt() {
		return search_req_cnt;
	}
	public void setSearch_req_cnt(String search_req_cnt) {
		this.search_req_cnt = search_req_cnt;
	}
	public String getSearch_month() {
		return search_month;
	}
	public void setSearch_month(String search_month) {
		this.search_month = search_month;
	}
	public String getSearch_call_rcv_cnt_type() {
		return search_call_rcv_cnt_type;
	}
	public void setSearch_call_rcv_cnt_type(String search_call_rcv_cnt_type) {
		this.search_call_rcv_cnt_type = search_call_rcv_cnt_type;
	}
	public String getSearch_call_rcv_cnt() {
		return search_call_rcv_cnt;
	}
	public void setSearch_call_rcv_cnt(String search_call_rcv_cnt) {
		this.search_call_rcv_cnt = search_call_rcv_cnt;
	}
	public String getSearch_customer() {
		return search_customer;
	}
	public void setSearch_customer(String search_customer) {
		this.search_customer = search_customer;
	}
	public String getFk_tel() {
		return fk_tel;
	}
	public void setFk_tel(String fk_tel) {
		this.fk_tel = fk_tel;
	}
	public String getReserve_time() {
		return reserve_time;
	}
	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}
	public String getReserve_type() {
		return reserve_type;
	}
	public void setReserve_type(String reserve_type) {
		this.reserve_type = reserve_type;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_sub_type() {
		return msg_sub_type;
	}
	public void setMsg_sub_type(String msg_sub_type) {
		this.msg_sub_type = msg_sub_type;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getMsg_send_finish() {
		return msg_send_finish;
	}
	public void setMsg_send_finish(String msg_send_finish) {
		this.msg_send_finish = msg_send_finish;
	}
	public String getReq_count() {
		return req_count;
	}
	public void setReq_count(String req_count) {
		this.req_count = req_count;
	}
	public String getRes_count() {
		return res_count;
	}
	public void setRes_count(String res_count) {
		this.res_count = res_count;
	}
	public String getCancel_yn() {
		return cancel_yn;
	}
	public void setCancel_yn(String cancel_yn) {
		this.cancel_yn = cancel_yn;
	}
}