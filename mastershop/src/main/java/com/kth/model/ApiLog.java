package com.kth.model;


public class ApiLog extends VoBase {

	//DB 파라메타
	private String pk_seqno				; //발신자 일련번호
	private String fd_member_id			; //번호와 맵핑된 웹회원 ID
	private String fd_tel				; //수신번호
	private String fd_rcv_tel			; //발신번호
	private String fd_reg_yy			; //수신날짜 yyyy
	private String fd_reg_mm			; //수신날짜 mm
	private String fd_reg_dd			; //수신날짜 dd
	private String fd_reg_hh			; //수신날짜 hh
	private String fd_reg_week			; //수신날짜 요일
	private String fd_reg_mi			; //수신날짜 ii
	private String fd_reg_ss			; //수신날짜 ss
	private String fd_openapi_rc_code	; //통화API 결과코드
	private String fd_openapi_skind		; //통화내역(1:All, 2:발신, 3:수신, 4:부재중, 5:실패, 6:예약전송, 7:핸드폰) - 사용안함
	private String fd_search_time		; //시간대역
	private String fd_reg_date			; //수신날짜 전체
	
	//Getter
	public String getPk_seqno() {
		return pk_seqno;
	}
	public String getFd_member_id() {
		return fd_member_id;
	}
	public String getFd_tel() {
		return fd_tel;
	}
	public String getFd_rcv_tel() {
		return fd_rcv_tel;
	}
	public String getFd_reg_yy() {
		return fd_reg_yy;
	}
	public String getFd_reg_mm() {
		return fd_reg_mm;
	}
	public String getFd_reg_dd() {
		return fd_reg_dd;
	}
	public String getFd_reg_hh() {
		return fd_reg_hh;
	}
	public String getFd_reg_week() {
		return fd_reg_week;
	}
	public String getFd_reg_mi() {
		return fd_reg_mi;
	}
	public String getFd_reg_ss() {
		return fd_reg_ss;
	}
	public String getFd_openapi_rc_code() {
		return fd_openapi_rc_code;
	}
	public String getFd_openapi_skind() {
		return fd_openapi_skind;
	}
	public String getFd_search_time() {
		return fd_search_time;
	}
	public String getFd_reg_date() {
		return fd_reg_date;
	}
	
	//Setter
	public void setPk_seqno(String pk_seqno) {
		this.pk_seqno = pk_seqno;
	}
	public void setFd_member_id(String fd_member_id) {
		this.fd_member_id = fd_member_id;
	}
	public void setFd_tel(String fd_tel) {
		this.fd_tel = fd_tel;
	}
	public void setFd_rcv_tel(String fd_rcv_tel) {
		this.fd_rcv_tel = fd_rcv_tel;
	}
	public void setFd_reg_yy(String fd_reg_yy) {
		this.fd_reg_yy = fd_reg_yy;
	}
	public void setFd_reg_mm(String fd_reg_mm) {
		this.fd_reg_mm = fd_reg_mm;
	}
	public void setFd_reg_dd(String fd_reg_dd) {
		this.fd_reg_dd = fd_reg_dd;
	}
	public void setFd_reg_hh(String fd_reg_hh) {
		this.fd_reg_hh = fd_reg_hh;
	}
	public void setFd_reg_week(String fd_reg_week) {
		this.fd_reg_week = fd_reg_week;
	}
	public void setFd_reg_mi(String fd_reg_mi) {
		this.fd_reg_mi = fd_reg_mi;
	}
	public void setFd_reg_ss(String fd_reg_ss) {
		this.fd_reg_ss = fd_reg_ss;
	}
	public void setFd_openapi_rc_code(String fd_openapi_rc_code) {
		this.fd_openapi_rc_code = fd_openapi_rc_code;
	}
	public void setFd_openapi_skind(String fd_openapi_skind) {	// 사용안함
		this.fd_openapi_skind = "";
	}
	public void setFd_search_time(String fd_search_time) {
		this.fd_search_time = fd_search_time;
	}
	public void setFd_reg_date(String fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}	

}