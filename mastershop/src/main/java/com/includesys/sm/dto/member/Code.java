package com.includesys.sm.dto.member;

public class Code {
	
	String fd_up_code, pk_code, fd_name, fd_menu_yn, fd_start, fd_finish;

	/**
	 * 상위코드
	 * @return fd_up_code
	 */
	public String getFd_up_code() {
		return fd_up_code;
	}

	/**
	 * 상위코드
	 * @param fd_up_code
	 */
	public void setFd_up_code(String fd_up_code) {
		this.fd_up_code = fd_up_code;
	}

	/**
	 * 코드 pk
	 * @return pk_code
	 */
	public String getPk_code() {
		return pk_code;
	}

	/**
	 * 코드 pk
	 * @param pk_code
	 */
	public void setPk_code(String pk_code) {
		this.pk_code = pk_code;
	}

	/**
	 * 코드이름
	 * @return fd_name
	 */
	public String getFd_name() {
		return fd_name;
	}

	/**
	 * 코드이름
	 * @param fd_name
	 */
	public void setFd_name(String fd_name) {
		this.fd_name = fd_name;
	}

	/**
	 * 코드 사용 유무
	 * @return fd_menu_yn
	 */
	public String getFd_menu_yn() {
		return fd_menu_yn;
	}

	/**
	 * 코드 사용 유무
	 * @param fd_menu_yn
	 */
	public void setFd_menu_yn(String fd_menu_yn) {
		this.fd_menu_yn = fd_menu_yn;
	}

	/**
	 * 유효 시작일
	 * @return fd_start
	 */
	public String getFd_start() {
		return fd_start;
	}

	/**
	 * 유효 시작일
	 * @param fd_start
	 */
	public void setFd_start(String fd_start) {
		this.fd_start = fd_start;
	}

	/**
	 * 유효 종료일
	 * @return fd_finish
	 */
	public String getFd_finish() {
		return fd_finish;
	}

	/**
	 * 유효 종료일
	 * @param fd_finish
	 */
	public void setFd_finish(String fd_finish) {
		this.fd_finish = fd_finish;
	}
	
}
