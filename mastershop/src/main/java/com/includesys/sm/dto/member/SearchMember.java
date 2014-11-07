package com.includesys.sm.dto.member;

import java.sql.Date;

public class SearchMember {
	String pk_member_id;			// 회원 아이디
	String pk_tel;						// 회선 전화번호						
	String fd_user_name;			// 사용자 명
	String fd_sex;						// 사용자 성별
	String fd_birthday;				// 사용자 생년월일
	String fd_regist_num;			// 법인번호
	String fd_corp_regist_num;	// 사업자 번호
	String fd_email;					// 사용자 이에일주소
	String fd_mobile;					// 사용자 연락처
	
	long pk_idx;						// 일련번호
	String fd_member_id;			// 회원 아이디
	String fd_tel;						// 회선 전화번호
	String fd_security_key;			// 인증번호
	Date fd_reg_date;				// 인증번호 발생일자
	
	/**
	 * @return the pk_member_id
	 */
	public String getPk_member_id() {
		return pk_member_id;
	}
	/**
	 * @return the pk_tel
	 */
	public String getPk_tel() {
		return pk_tel;
	}
	/**
	 * @return the fd_user_name
	 */
	public String getFd_user_name() {
		return fd_user_name;
	}
	/**
	 * @return the fd_sex
	 */
	public String getFd_sex() {
		return fd_sex;
	}
	/**
	 * @return the fd_birthday
	 */
	public String getFd_birthday() {
		return fd_birthday;
	}
	/**
	 * @return the fd_regist_num
	 */
	public String getFd_regist_num() {
		return fd_regist_num;
	}
	/**
	 * @return the fd_corp_regist_num
	 */
	public String getFd_corp_regist_num() {
		return fd_corp_regist_num;
	}
	/**
	 * @return the fd_email
	 */
	public String getFd_email() {
		return fd_email;
	}
	/**
	 * @return the pk_idx
	 */
	public long getPk_idx() {
		return pk_idx;
	}
	/**
	 * @return the fd_member_id
	 */
	public String getFd_member_id() {
		return fd_member_id;
	}
	/**
	 * @return the fd_tel
	 */
	public String getFd_tel() {
		return fd_tel;
	}
	/**
	 * @return the fd_security_key
	 */
	public String getFd_security_key() {
		return fd_security_key;
	}
	/**
	 * @return the fd_reg_date
	 */
	public Date getFd_reg_date() {
		return fd_reg_date;
	}
	/**
	 * @param pk_member_id the pk_member_id to set
	 */
	public void setPk_member_id(String pk_member_id) {
		this.pk_member_id = pk_member_id;
		this.fd_member_id = pk_member_id;
	}
	/**
	 * @param pk_tel the pk_tel to set
	 */
	public void setPk_tel(String pk_tel) {
		this.pk_tel = pk_tel;
	}
	/**
	 * @param fd_user_name the fd_user_name to set
	 */
	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}
	/**
	 * @param fd_sex the fd_sex to set
	 */
	public void setFd_sex(String fd_sex) {
		this.fd_sex = fd_sex;
	}
	/**
	 * @param fd_birthday the fd_birthday to set
	 */
	public void setFd_birthday(String fd_birthday) {
		this.fd_birthday = fd_birthday;
	}
	/**
	 * @param fd_regist_num the fd_regist_num to set
	 */
	public void setFd_regist_num(String fd_regist_num) {
		this.fd_regist_num = fd_regist_num;
	}
	/**
	 * @param fd_corp_regist_num the fd_corp_regist_num to set
	 */
	public void setFd_corp_regist_num(String fd_corp_regist_num) {
		this.fd_corp_regist_num = fd_corp_regist_num;
	}
	/**
	 * @param fd_email the fd_email to set
	 */
	public void setFd_email(String fd_email) {
		this.fd_email = fd_email;
	}
	/**
	 * @param pk_idx the pk_idx to set
	 */
	public void setPk_idx(long pk_idx) {
		this.pk_idx = pk_idx;
	}
	/**
	 * @param fd_member_id the fd_member_id to set
	 */
	public void setFd_member_id(String fd_member_id) {
		this.fd_member_id = fd_member_id;
		this.pk_member_id = fd_member_id;
	}
	/**
	 * @param fd_tel the fd_tel to set
	 */
	public void setFd_tel(String fd_tel) {
		this.fd_tel = fd_tel;
	}
	/**
	 * @param fd_security_key the fd_security_key to set
	 */
	public void setFd_security_key(String fd_security_key) {
		this.fd_security_key = fd_security_key;
	}
	/**
	 * @param fd_reg_date the fd_reg_date to set
	 */
	public void setFd_reg_date(Date fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}
	/**
	 * @return the fd_mobile
	 */
	public String getFd_mobile() {
		return fd_mobile;
	}
	/**
	 * @param fd_mobile the fd_mobile to set
	 */
	public void setFd_mobile(String fd_mobile) {
		this.fd_mobile = fd_mobile;
	}
	
}
