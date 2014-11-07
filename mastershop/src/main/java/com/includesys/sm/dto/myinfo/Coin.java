package com.includesys.sm.dto.myinfo;

import java.io.Serializable;
import java.sql.Date;

public class Coin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fk_member_id;			// 아이디
	private int fd_base_coin;					// 기본 코인
	private int fd_recharge_coin;			// 충전 코인
	private int fd_bonus_coin;				// 보너스 코인
	private int fd_pay_mount;				// 결제 금액
	private int fd_pay_type;					// 결제 방식  1:휴대폰, 2:신용카드, 3:계좌이체, 4:무통장입금'
	Date fd_reg_date;							// 결제일
	
	private String pk_tel;						// 전화번호
	private String fd_tel;						// 전화번호
	private String fk_group_code;			// SMS 그룹 코드
	private String fd_group_code;			// SMS 그룹 코드
	private int fd_total_coin;					// 총 사용 포인트
		
	private String msg_type;					// 메시지 타입 1: SMS 4: MMS  4-1:LMS
	private String msg_sub_type;			//	1:TEXT 3:이미지
	private int req_count;						// 요청 건수
	private int res_count;						// 요청 응답건수
	private int mcs_res_result_success;	// 요청 성공 건수
	private int mcs_res_result_fail;			// 요청 실패 건수
	
	private String fd_use_yn;					// 사용여부
	private String fd_del_date;				// 삭제 일
	private String reserve_time;				// SMS 발송일
	private String fd_product_code;		// 코인 상품구분
	private Date fd_cancel_date;			// 코인 취소 일자 
	
	
	private long pk_seq;								// 일련번호
	private String fd_member_id;					// 회원 아이디
	private String fd_tid;								// 거래 번호
	private String fd_result_code;					// 결과 코드
	private String fd_result_msg;					// 결과 메세지
	private String fd_moid;							// 주문번호
	private String fd_appl_date;						// 승인 날짜
	private String fd_appl_time;						// 승인 시간
	private String fd_appl_num;						// 승인 번호
	private String fd_paymethod;					// 결제 방법
	private String fd_tot_price;						// 결제 금액
	private String fd_card_event_code;			// event 할부 코드
	private String fd_card_event_code_msg;	// event 할부 코드 메세지
	private String fd_card_num;						// 신용카드 번호
	private String fd_card_interest;					// 할부 종류
	private String fd_card_quota;					// 할부 개월
	private String fd_card_code;						// 카드 종류
	private String fd_card_bank_code;				// 카드 발급사
	private String fd_card_org_currency;			// 통화 코드
	private String fd_card_exchangerate;			// 통화 코드 환율
	private String fd_ocb_num;						// OK CASHBAG 적립 및 사용내역 > 카드번호
	private String fd_ocb_svae_appl_num;		// OK CASHBAG 적립 및 사용내역 > 카드 승인 번호
	private String fd_ocb_pay_appl_num;		// OK CASHBAG 적립 및 사용내역 > 사용 승인 번호
	private String fd_ocb_appl_date;				// OK CASHBAG 적립 및 사용내역 > 승인 일시
	private String fd_ocb_pay_price;				// OK CASHBAG 적립 및 사용내역 > 포인트 지불 금액
	private String fd_acct_bank_code;				// 은행코드
	private String fd_cshr_result_code;			// 현금 영수증 발급 결과 코드
	private String fd_cshr_type;						// 현금 영수증 구분 코드 0: 소득공제, 1 : 지출증빙용
	private String fd_vact_num;						// 입금 계좌 번호
	private String fd_vact_bank_code;				// 입금 은행 코드
	private String fd_vact_name;					// 예금주명
	private String fd_vact_input_name;			// 송금자명
	private String fd_vact_date;						// 송금 날짜
	private String fd_vact_time;						// 송금 시간
	private String fd_hpp_num;						// 휴대폰 번호
	
	
	/**
	 * 
	 * @return
	 */
	public String getFk_member_id() {
		if( fk_member_id  	== null ) {	return ""; }else { return fk_member_id; }
	}

	/**
	 * 
	 * @return
	 */
	public int getFd_base_coin() {
		return fd_base_coin;
	}

	/**
	 * 
	 * @return
	 */
	public int getFd_recharge_coin() {
		return fd_recharge_coin;
	}

	/**
	 * 
	 * @return
	 */
	public int getFd_bonus_coin() {
		return fd_bonus_coin;
	}

	/**
	 * 
	 * @return
	 */
	public int getFd_pay_mount() {
		return fd_pay_mount;
	}

	/**
	 * 
	 * @return
	 */
	public int getFd_pay_type() {
		return fd_pay_type;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFd_reg_date() {
		return fd_reg_date;
	}

	/**
	 * 
	 * @return
	 */
	public String getPk_tel() {
		return pk_tel;
	}

	/**
	 * 
	 * @return
	 */
	public String getFk_group_code() {
		return fk_group_code;
	}

	/**
	 * 
	 * @return
	 */
	public int getFd_total_coin() {
		return fd_total_coin;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMsg_type() {
		return msg_type;
	}

	/**
	 * 
	 * @return
	 */
	public String getMsg_sub_type() {
		return msg_sub_type;
	}

	/**
	 * 
	 * @return
	 */
	public int getReq_count() {
		return req_count;
	}

	/**
	 * 
	 * @return
	 */
	public int getRes_count() {
		return res_count;
	}

	/**
	 * 
	 * @return
	 */
	public int getMcs_res_result_success() {
		return mcs_res_result_success;
	}

	/**
	 * 
	 * @return
	 */
	public int getMcs_res_result_fail() {
		return mcs_res_result_fail;
	}
	/**
	 * 
	 * @return
	 */
	public String getFd_use_yn() {
		return fd_use_yn;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_del_date() {
		return fd_del_date;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getReserve_time() {
		return reserve_time;
	}
	
	//-- Setter --//
	/**
	 * 
	 * @param fk_member_id
	 */
	public void setFk_member_id(String fk_member_id) {
		this.fk_member_id = fk_member_id;
	}

	/**
	 * 
	 * @param fd_base_coin
	 */
	public void setFd_base_coin(int fd_base_coin) {
		this.fd_base_coin = fd_base_coin;
	}

	/**
	 * 
	 * @param fd_recharge_coin
	 */
	public void setFd_recharge_coin(int fd_recharge_coin) {
		this.fd_recharge_coin = fd_recharge_coin;
	}

	/**
	 * 
	 * @param fd_bonus_coin
	 */
	public void setFd_bonus_coin(int fd_bonus_coin) {
		this.fd_bonus_coin = fd_bonus_coin;
	}

	/**
	 * 
	 * @param fd_pay_mount
	 */
	public void setFd_pay_mount(int fd_pay_mount) {
		this.fd_pay_mount = fd_pay_mount;
	}

	/**
	 * 
	 * @param fd_pay_type
	 */
	public void setFd_pay_type(int fd_pay_type) {
		this.fd_pay_type = fd_pay_type;
	}

	/**
	 * 
	 * @param fd_reg_date
	 */
	public void setFd_reg_date(Date fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}
	/**
	 * 
	 * @param pk_tel
	 */
	public void setPk_tel(String pk_tel) {
		this.pk_tel = pk_tel;
	}

	/**
	 * 
	 * @param fk_group_code
	 */
	public void setFk_group_code(String fk_group_code) {
		this.fk_group_code = fk_group_code;
	}

	/**
	 * 
	 * @param fd_total_coin
	 */
	public void setFd_total_coin(int fd_total_coin) {
		this.fd_total_coin = fd_total_coin;
	}	
	/**
	 * 
	 * @param msg_type
	 */
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	/**
	 * 
	 * @param msg_sub_type
	 */
	public void setMsg_sub_type(String msg_sub_type) {
		this.msg_sub_type = msg_sub_type;
	}

	/**
	 * 
	 * @param req_count
	 */
	public void setReq_count(int req_count) {
		this.req_count = req_count;
	}

	/**
	 * 
	 * @param res_count
	 */
	public void setRes_count(int res_count) {
		this.res_count = res_count;
	}
	/**
	 * 
	 * @param mcs_res_result_success
	 */
	public void setMcs_res_result_success(int mcs_res_result_success) {
		this.mcs_res_result_success = mcs_res_result_success;
	}

	/**
	 * 
	 * @param mcs_res_result_fail
	 */
	public void setMcs_res_result_fail(int mcs_res_result_fail) {
		this.mcs_res_result_fail = mcs_res_result_fail;
	}
	
	/**
	 * 
	 * @param fd_use_yn
	 */
	public void setFd_use_yn(String fd_use_yn) {
		this.fd_use_yn = fd_use_yn;
	}

	/**
	 * 
	 * @param fd_del_date
	 */
	public void setFd_del_date(String fd_del_date) {
		this.fd_del_date = fd_del_date;
	}
	
	/**
	 * 
	 * @param reserve_time
	 */
	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}

	/**
	 * @return the fd_product_code
	 */
	public String getFd_product_code() {
		return fd_product_code;
	}

	/**
	 * @param fd_product_code the fd_product_code to set
	 */
	public void setFd_product_code(String fd_product_code) {
		this.fd_product_code = fd_product_code;
	}
	
	/**
	 * @return the fd_tel
	 */
	public String getFd_tel() {
		return fd_tel;
	}

	/**
	 * @return the fd_group_code
	 */
	public String getFd_group_code() {
		return fd_group_code;
	}

	/**
	 * @param fd_tel the fd_tel to set
	 */
	public void setFd_tel(String fd_tel) {
		this.fd_tel = fd_tel;
	}

	/**
	 * @param fd_group_code the fd_group_code to set
	 */
	public void setFd_group_code(String fd_group_code) {
		this.fd_group_code = fd_group_code;
	}
	

	/**
	 * @return the fd_cancel_date
	 */
	public Date getFd_cancel_date() {
		return fd_cancel_date;
	}

	/**
	 * @param fd_cancel_date the fd_cancel_date to set
	 */
	public void setFd_cancel_date(Date fd_cancel_date) {
		this.fd_cancel_date = fd_cancel_date;
	}

	
	
	
	/**
	 * @return the pk_seq
	 */
	public long getPk_seq() {
		return pk_seq;
	}

	/**
	 * @return the fd_member_id
	 */
	public String getFd_member_id() {
		return fd_member_id;
	}

	/**
	 * @return the fd_tid
	 */
	public String getFd_tid() {
		return fd_tid;
	}

	/**
	 * @return the fd_result_code
	 */
	public String getFd_result_code() {
		return fd_result_code;
	}

	/**
	 * @return the fd_result_msg
	 */
	public String getFd_result_msg() {
		return fd_result_msg;
	}

	/**
	 * @return the fd_moid
	 */
	public String getFd_moid() {
		return fd_moid;
	}

	/**
	 * @return the fd_appl_date
	 */
	public String getFd_appl_date() {
		return fd_appl_date;
	}

	/**
	 * @return the fd_appl_time
	 */
	public String getFd_appl_time() {
		return fd_appl_time;
	}

	/**
	 * @return the fd_appl_num
	 */
	public String getFd_appl_num() {
		return fd_appl_num;
	}

	/**
	 * @return the fd_paymethod
	 */
	public String getFd_paymethod() {
		return fd_paymethod;
	}

	/**
	 * @return the fd_tot_price
	 */
	public String getFd_tot_price() {
		return fd_tot_price;
	}

	/**
	 * @return the fd_card_event_code
	 */
	public String getFd_card_event_code() {
		return fd_card_event_code;
	}

	/**
	 * @return the fd_card_event_code_msg
	 */
	public String getFd_card_event_code_msg() {
		return fd_card_event_code_msg;
	}

	/**
	 * @return the fd_card_num
	 */
	public String getFd_card_num() {
		return fd_card_num;
	}

	/**
	 * @return the fd_card_interest
	 */
	public String getFd_card_interest() {
		return fd_card_interest;
	}

	/**
	 * @return the fd_card_quota
	 */
	public String getFd_card_quota() {
		return fd_card_quota;
	}

	/**
	 * @return the fd_card_code
	 */
	public String getFd_card_code() {
		return fd_card_code;
	}

	/**
	 * @return the fd_card_bank_code
	 */
	public String getFd_card_bank_code() {
		return fd_card_bank_code;
	}

	/**
	 * @return the fd_card_org_currency
	 */
	public String getFd_card_org_currency() {
		return fd_card_org_currency;
	}

	/**
	 * @return the fd_card_exchangerate
	 */
	public String getFd_card_exchangerate() {
		return fd_card_exchangerate;
	}

	/**
	 * @return the fd_ocb_num
	 */
	public String getFd_ocb_num() {
		return fd_ocb_num;
	}

	/**
	 * @return the fd_ocb_svae_appl_num
	 */
	public String getFd_ocb_svae_appl_num() {
		return fd_ocb_svae_appl_num;
	}

	/**
	 * @return the fd_ocb_pay_appl_num
	 */
	public String getFd_ocb_pay_appl_num() {
		return fd_ocb_pay_appl_num;
	}

	/**
	 * @return the fd_ocb_appl_date
	 */
	public String getFd_ocb_appl_date() {
		return fd_ocb_appl_date;
	}

	/**
	 * @return the fd_ocb_pay_price
	 */
	public String getFd_ocb_pay_price() {
		return fd_ocb_pay_price;
	}

	/**
	 * @return the fd_acct_bank_code
	 */
	public String getFd_acct_bank_code() {
		return fd_acct_bank_code;
	}

	/**
	 * @return the fd_cshr_result_code
	 */
	public String getFd_cshr_result_code() {
		return fd_cshr_result_code;
	}

	/**
	 * @return the fd_cshr_type
	 */
	public String getFd_cshr_type() {
		return fd_cshr_type;
	}

	/**
	 * @return the fd_vact_num
	 */
	public String getFd_vact_num() {
		return fd_vact_num;
	}

	/**
	 * @return the fd_vact_bank_code
	 */
	public String getFd_vact_bank_code() {
		return fd_vact_bank_code;
	}

	/**
	 * @return the fd_vact_name
	 */
	public String getFd_vact_name() {
		return fd_vact_name;
	}

	/**
	 * @return the fd_vact_input_name
	 */
	public String getFd_vact_input_name() {
		return fd_vact_input_name;
	}

	/**
	 * @return the fd_vact_date
	 */
	public String getFd_vact_date() {
		return fd_vact_date;
	}

	/**
	 * @return the fd_vact_time
	 */
	public String getFd_vact_time() {
		return fd_vact_time;
	}

	/**
	 * @return the fd_hpp_num
	 */
	public String getFd_hpp_num() {
		return fd_hpp_num;
	}

	/**
	 * @param pk_seq the pk_seq to set
	 */
	public void setPk_seq(long pk_seq) {
		this.pk_seq = pk_seq;
	}

	/**
	 * @param fd_member_id the fd_member_id to set
	 */
	public void setFd_member_id(String fd_member_id) {
		this.fd_member_id = fd_member_id;
	}

	/**
	 * @param fd_tid the fd_tid to set
	 */
	public void setFd_tid(String fd_tid) {
		this.fd_tid = fd_tid;
	}

	/**
	 * @param fd_result_code the fd_result_code to set
	 */
	public void setFd_result_code(String fd_result_code) {
		this.fd_result_code = fd_result_code;
	}

	/**
	 * @param fd_result_msg the fd_result_msg to set
	 */
	public void setFd_result_msg(String fd_result_msg) {
		this.fd_result_msg = fd_result_msg;
	}

	/**
	 * @param fd_moid the fd_moid to set
	 */
	public void setFd_moid(String fd_moid) {
		this.fd_moid = fd_moid;
	}

	/**
	 * @param fd_appl_date the fd_appl_date to set
	 */
	public void setFd_appl_date(String fd_appl_date) {
		this.fd_appl_date = fd_appl_date;
	}

	/**
	 * @param fd_appl_time the fd_appl_time to set
	 */
	public void setFd_appl_time(String fd_appl_time) {
		this.fd_appl_time = fd_appl_time;
	}

	/**
	 * @param fd_appl_num the fd_appl_num to set
	 */
	public void setFd_appl_num(String fd_appl_num) {
		this.fd_appl_num = fd_appl_num;
	}

	/**
	 * @param fd_paymethod the fd_paymethod to set
	 */
	public void setFd_paymethod(String fd_paymethod) {
		this.fd_paymethod = fd_paymethod;
	}

	/**
	 * @param fd_tot_price the fd_tot_price to set
	 */
	public void setFd_tot_price(String fd_tot_price) {
		this.fd_tot_price = fd_tot_price;
	}

	/**
	 * @param fd_card_event_code the fd_card_event_code to set
	 */
	public void setFd_card_event_code(String fd_card_event_code) {
		this.fd_card_event_code = fd_card_event_code;
	}

	/**
	 * @param fd_card_event_code_msg the fd_card_event_code_msg to set
	 */
	public void setFd_card_event_code_msg(String fd_card_event_code_msg) {
		this.fd_card_event_code_msg = fd_card_event_code_msg;
	}

	/**
	 * @param fd_card_num the fd_card_num to set
	 */
	public void setFd_card_num(String fd_card_num) {
		this.fd_card_num = fd_card_num;
	}

	/**
	 * @param fd_card_interest the fd_card_interest to set
	 */
	public void setFd_card_interest(String fd_card_interest) {
		this.fd_card_interest = fd_card_interest;
	}

	/**
	 * @param fd_card_quota the fd_card_quota to set
	 */
	public void setFd_card_quota(String fd_card_quota) {
		this.fd_card_quota = fd_card_quota;
	}

	/**
	 * @param fd_card_code the fd_card_code to set
	 */
	public void setFd_card_code(String fd_card_code) {
		this.fd_card_code = fd_card_code;
	}

	/**
	 * @param fd_card_bank_code the fd_card_bank_code to set
	 */
	public void setFd_card_bank_code(String fd_card_bank_code) {
		this.fd_card_bank_code = fd_card_bank_code;
	}

	/**
	 * @param fd_card_org_currency the fd_card_org_currency to set
	 */
	public void setFd_card_org_currency(String fd_card_org_currency) {
		this.fd_card_org_currency = fd_card_org_currency;
	}

	/**
	 * @param fd_card_exchangerate the fd_card_exchangerate to set
	 */
	public void setFd_card_exchangerate(String fd_card_exchangerate) {
		this.fd_card_exchangerate = fd_card_exchangerate;
	}

	/**
	 * @param fd_ocb_num the fd_ocb_num to set
	 */
	public void setFd_ocb_num(String fd_ocb_num) {
		this.fd_ocb_num = fd_ocb_num;
	}

	/**
	 * @param fd_ocb_svae_appl_num the fd_ocb_svae_appl_num to set
	 */
	public void setFd_ocb_svae_appl_num(String fd_ocb_svae_appl_num) {
		this.fd_ocb_svae_appl_num = fd_ocb_svae_appl_num;
	}

	/**
	 * @param fd_ocb_pay_appl_num the fd_ocb_pay_appl_num to set
	 */
	public void setFd_ocb_pay_appl_num(String fd_ocb_pay_appl_num) {
		this.fd_ocb_pay_appl_num = fd_ocb_pay_appl_num;
	}

	/**
	 * @param fd_ocb_appl_date the fd_ocb_appl_date to set
	 */
	public void setFd_ocb_appl_date(String fd_ocb_appl_date) {
		this.fd_ocb_appl_date = fd_ocb_appl_date;
	}

	/**
	 * @param fd_ocb_pay_price the fd_ocb_pay_price to set
	 */
	public void setFd_ocb_pay_price(String fd_ocb_pay_price) {
		this.fd_ocb_pay_price = fd_ocb_pay_price;
	}

	/**
	 * @param fd_acct_bank_code the fd_acct_bank_code to set
	 */
	public void setFd_acct_bank_code(String fd_acct_bank_code) {
		this.fd_acct_bank_code = fd_acct_bank_code;
	}

	/**
	 * @param fd_cshr_result_code the fd_cshr_result_code to set
	 */
	public void setFd_cshr_result_code(String fd_cshr_result_code) {
		this.fd_cshr_result_code = fd_cshr_result_code;
	}

	/**
	 * @param fd_cshr_type the fd_cshr_type to set
	 */
	public void setFd_cshr_type(String fd_cshr_type) {
		this.fd_cshr_type = fd_cshr_type;
	}

	/**
	 * @param fd_vact_num the fd_vact_num to set
	 */
	public void setFd_vact_num(String fd_vact_num) {
		this.fd_vact_num = fd_vact_num;
	}

	/**
	 * @param fd_vact_bank_code the fd_vact_bank_code to set
	 */
	public void setFd_vact_bank_code(String fd_vact_bank_code) {
		this.fd_vact_bank_code = fd_vact_bank_code;
	}

	/**
	 * @param fd_vact_name the fd_vact_name to set
	 */
	public void setFd_vact_name(String fd_vact_name) {
		this.fd_vact_name = fd_vact_name;
	}

	/**
	 * @param fd_vact_input_name the fd_vact_input_name to set
	 */
	public void setFd_vact_input_name(String fd_vact_input_name) {
		this.fd_vact_input_name = fd_vact_input_name;
	}

	/**
	 * @param fd_vact_date the fd_vact_date to set
	 */
	public void setFd_vact_date(String fd_vact_date) {
		this.fd_vact_date = fd_vact_date;
	}

	/**
	 * @param fd_vact_time the fd_vact_time to set
	 */
	public void setFd_vact_time(String fd_vact_time) {
		this.fd_vact_time = fd_vact_time;
	}

	/**
	 * @param fd_hpp_num the fd_hpp_num to set
	 */
	public void setFd_hpp_num(String fd_hpp_num) {
		this.fd_hpp_num = fd_hpp_num;
	}

	public int getTotalCoin(){
		int result = 0;
		
		result = this.fd_recharge_coin + this.fd_bonus_coin; 
		
		return result;
	}
	
	public String getPayType( ){
		
		int pay_type = this.fd_pay_type;
		System.out.println("pay_type : " + pay_type);
		System.out.println("fd_pay_type : " + fd_pay_type);
		
		
		String result = "";
		
		switch(pay_type){
			case 1: 
				result="휴대폰";
				break;
			case 2: 
				result="신용카드";
				break;
			case 3: 
				result="계좌이체";
				break;
			case 4: 
				result="무통장입금";
				break;
			default : 
				result="";
			
		}
		
		return result;
	}
	
	public int changePayType(String type){
		int result = 9;
		if(type != null){
			if(type.equals("Card")){
				result = 2;
			}else if(type.equals("HPP")){
				result = 1;
			}else if(type.equals("DirectBank")){
				result = 3;
			} else if(type.equals("VBank")){
				result = 4;
			}  
		}
		return result;
	}
	
	public boolean checkValue(String p_code, String money){
		boolean result = false;
		
		if(p_code!=null && money !=null){
			if(p_code.equals("3701") && money.equals("11000") ){
				result = true;
			}else if(p_code.equals("3702") && money.equals("33000") ){
				result = true;
			}else if(p_code.equals("3703") && money.equals("55000") ){
				result = true;
			}else if(p_code.equals("3704") && money.equals("110000") ){
				result = true;
			}else{
				result = false;
			}
			
		}else{
			result =false;
		}
		
		
		return result;
		
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("=== [" + this.getClass().getSimpleName() +  "] ===\n");		
		sb.append("[fk_member_id  				] : " 	+ fk_member_id					+ "\n");
		sb.append("[fd_base_coin  				] : " 	+ fd_base_coin					+ "\n");
		sb.append("[fd_recharge_coin  			] : " 	+ fd_recharge_coin				+ "\n");
		sb.append("[fd_bonus_coin  				] : " 	+ fd_bonus_coin					+ "\n");
		sb.append("[fd_pay_mount  				] : " 	+ fd_pay_mount					+ "\n");
		sb.append("[fd_pay_type  				] : " 	+ fd_pay_type					+ "\n");
		sb.append("[fd_reg_date  				] : " 	+ fd_reg_date						+ "\n");
		sb.append("[pk_tel  						] : " 	+ pk_tel								+ "\n");
		sb.append("[fk_group_code  				] : " 	+ fk_group_code					+ "\n");
		sb.append("[fd_total_coin  				] : " 	+ fd_total_coin					+ "\n");
		sb.append("[msg_type  					] : " 	+ msg_type						+ "\n");
		sb.append("[msg_sub_type  				] : " 	+ msg_sub_type					+ "\n");
		sb.append("[req_count  					] : " 	+ req_count						+ "\n");
		sb.append("[res_count  					] : " 	+ res_count						+ "\n");
		sb.append("[mcs_res_result_success	] : "  	+ mcs_res_result_success	+ "\n");
		sb.append("[mcs_res_result_fail  		] : " 	+ mcs_res_result_fail			+ "\n");
		sb.append("[fd_del_date  				] : " 	+ fd_del_date						+ "\n");
		sb.append("[fd_use_yn  					] : " 	+ fd_use_yn						+ "\n");
		sb.append("[reserve_time  				] : " 	+ reserve_time					+ "\n");
		sb.append("[fd_product_code  			] : " 	+ fd_product_code				+ "\n");
		
		sb.append("[fd_tel  							] : " 	+ fd_tel								+ "\n");
		sb.append("[fd_group_code  				] : " 	+ fd_group_code					+ "\n");
		sb.append("[fd_cancel_date  			] : " 	+ fd_cancel_date					+ "\n");		
		
		sb.append("[pk_seq  						] : " 	+ pk_seq							+ "\n");
		sb.append("[fd_member_id  				] : " 	+ fd_member_id					+ "\n");
		sb.append("[fd_tid  							] : " 	+ fd_tid								+ "\n");
		sb.append("[fd_result_code  				] : " 	+ fd_result_code					+ "\n");
		sb.append("[fd_result_msg  				] : " 	+ fd_result_msg					+ "\n");
		sb.append("[fd_moid  						] : " 	+ fd_moid							+ "\n");
		sb.append("[fd_appl_date  				] : " 	+ fd_appl_date					+ "\n");
		sb.append("[fd_appl_time  				] : " 	+ fd_appl_time					+ "\n");
		sb.append("[fd_appl_num  				] : " 	+ fd_appl_num					+ "\n");
		sb.append("[fd_paymethod  				] : " 	+ fd_paymethod					+ "\n");
		sb.append("[fd_tot_price  				] : " 	+ fd_tot_price						+ "\n");
		sb.append("[fd_card_event_code  		] : " 	+ fd_card_event_code			+ "\n");
		sb.append("[fd_card_event_code_msg] : " 	+ fd_card_event_code_msg	+ "\n");
		sb.append("[fd_card_num  				] : " 	+ fd_card_num					+ "\n");
		sb.append("[fd_card_interest  			] : " 	+ fd_card_interest				+ "\n");
		sb.append("[fd_card_quota  				] : " 	+ fd_card_quota					+ "\n");
		sb.append("[fd_card_code  				] : " 	+ fd_card_code					+ "\n");
		sb.append("[fd_card_bank_code  		] : " 	+ fd_card_bank_code			+ "\n");
		sb.append("[fd_card_org_currency  	] : " 	+ fd_card_org_currency		+ "\n");
		sb.append("[fd_card_exchangerate  	] : " 	+ fd_card_exchangerate		+ "\n");
		sb.append("[fd_ocb_num  				] : " 	+ fd_ocb_num						+ "\n");
		sb.append("[fd_ocb_svae_appl_num  	] : " 	+ fd_ocb_svae_appl_num		+ "\n");
		sb.append("[fd_ocb_pay_appl_num  	] : " 	+ fd_ocb_pay_appl_num		+ "\n");
		sb.append("[fd_ocb_appl_date  		] : " 	+ fd_ocb_appl_date				+ "\n");
		sb.append("[fd_ocb_pay_price  			] : " 	+ fd_ocb_pay_price				+ "\n");
		sb.append("[fd_acct_bank_code  		] : " 	+ fd_acct_bank_code			+ "\n");
		sb.append("[fd_cshr_result_code  		] : " 	+ fd_cshr_result_code			+ "\n");
		sb.append("[fd_cshr_type  				] : " 	+ fd_cshr_type					+ "\n");
		sb.append("[fd_vact_num  				] : " 	+ fd_vact_num					+ "\n");
		sb.append("[fd_vact_bank_code  		] : " 	+ fd_vact_bank_code			+ "\n");
		sb.append("[fd_vact_name  				] : " 	+ fd_vact_name					+ "\n");
		sb.append("[fd_vact_input_name  		] : " 	+ fd_vact_input_name			+ "\n");
		sb.append("[fd_vact_date  				] : " 	+ fd_vact_date					+ "\n");
		sb.append("[fd_vact_time  				] : " 	+ fd_vact_time					+ "\n");
		sb.append("[fd_hpp_num  				] : " 	+ fd_hpp_num					+ "\n");
		
		return sb.toString();	
	}
	
	
}
