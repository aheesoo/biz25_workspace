package com.includesys.sm.dto.common;

public class Customer {
/*
  `fk_member_id` varchar(32) NOT NULL COMMENT '아이디',
  `pk_customer_tel` varchar(14) NOT NULL DEFAULT '' COMMENT '고객 전화번호',
  `fd_user_name` varchar(32) DEFAULT NULL COMMENT '고객 이름',
  `fd_addr` varchar(128) DEFAULT NULL COMMENT '주소',
  `fd_memo` varchar(2048) DEFAULT NULL COMMENT '고객 메모',
  `fd_view_flag` varchar(1) DEFAULT NULL COMMENT '보기옵션 0:보기 1:숨김',
  `fd_rev_sms_flag` varchar(1) DEFAULT 'N' COMMENT '수신거부 거부:Y 승락:N (기본값:N)',
  `fd_new_date` varchar(8) DEFAULT NULL COMMENT '최초 등록 년월일 ex:20140101(신규고객 대상추출)',
  `fd_mod_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '정보 수정일',
*/
	String pk_customer_tel;		// 고객 전화번호
	String fk_member_id;		// 고객 ID
	String fd_view_flag;		// 보기옵션 0:보기 1:숨김
	String fd_user_name;		// 고객 이름
	String fd_rev_sms_flag;		// 수신거부 거부:Y 승락:N (기본값:N)
	String fd_new_date;			// 최초 등록 년월일 ex:20140101(신규고객 대상추출)
	String fd_mod_date;			// 정보 수정일
	String fd_memo;				// 고객 메모
	String fd_addr;				// 주소

	/**
	 * @return the pk_customer_tel
	 */
	public String getPk_customer_tel() {
		return pk_customer_tel;
	}
	/**
	 * @param pk_customer_tel the pk_customer_tel to set
	 */
	public void setPk_customer_tel(String pk_customer_tel) {
		this.pk_customer_tel = pk_customer_tel;
	}
	/**
	 * @return the fk_member_id
	 */
	public String getFk_member_id() {
		return fk_member_id;
	}
	/**
	 * @param fk_member_id the fk_member_id to set
	 */
	public void setFk_member_id(String fk_member_id) {
		this.fk_member_id = fk_member_id;
	}
	/**
	 * @return the fd_view_flag
	 */
	public String getFd_view_flag() {
		return fd_view_flag;
	}
	/**
	 * @param fd_view_flag the fd_view_flag to set
	 */
	public void setFd_view_flag(String fd_view_flag) {
		this.fd_view_flag = fd_view_flag;
	}
	/**
	 * @return the fd_user_name
	 */
	public String getFd_user_name() {
		return fd_user_name;
	}
	/**
	 * @param fd_user_name the fd_user_name to set
	 */
	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}
	/**
	 * @return the fd_rev_sms_flag
	 */
	public String getFd_rev_sms_flag() {
		return fd_rev_sms_flag;
	}
	/**
	 * @param fd_rev_sms_flag the fd_rev_sms_flag to set
	 */
	public void setFd_rev_sms_flag(String fd_rev_sms_flag) {
		this.fd_rev_sms_flag = fd_rev_sms_flag;
	}
	/**
	 * @return the fd_new_date
	 */
	public String getFd_new_date() {
		return fd_new_date;
	}
	/**
	 * @param fd_new_date the fd_new_date to set
	 */
	public void setFd_new_date(String fd_new_date) {
		this.fd_new_date = fd_new_date;
	}
	/**
	 * @return the fd_mod_date
	 */
	public String getFd_mod_date() {
		return fd_mod_date;
	}
	/**
	 * @param fd_mod_date the fd_mod_date to set
	 */
	public void setFd_mod_date(String fd_mod_date) {
		this.fd_mod_date = fd_mod_date;
	}
	/**
	 * @return the fd_memo
	 */
	public String getFd_memo() {
		return fd_memo;
	}
	/**
	 * @param fd_memo the fd_memo to set
	 */
	public void setFd_memo(String fd_memo) {
		this.fd_memo = fd_memo;
	}
	/**
	 * @return the fd_addr
	 */
	public String getFd_addr() {
		return fd_addr;
	}
	/**
	 * @param fd_addr the fd_addr to set
	 */
	public void setFd_addr(String fd_addr) {
		this.fd_addr = fd_addr;
	}
}
