package com.includesys.sm.dto.common;

public class Common {
	String pk_product_code;				// 품목 코드 pk
	String fd_up_code;						// 상위 코드
	String fd_code_name;					// 코드 이름
	String fd_point_level_5;				// 기본 일반 포인트	
	String fd_point_level_10;				// 기본 서포터즈 포인트	
	String search_type;						// 코드 조회 타입 및 검색 타입
	String search_code;						// 검색 코드
		
	/**
	 * 품목코드 pk 
	 * @return pk_product_code
	 */
	public String getPk_product_code() {
		return pk_product_code;
	}
	
	/**
	 * 품목코드 pk
	 * @param pk_product_code	  
	 */
	public void setPk_product_code(String pk_product_code) {
		this.pk_product_code = pk_product_code;
	}
	
	/**
	 * 상위 코드
	 * @return fd_up_code
	 */
	public String getFd_up_code() {
		return fd_up_code;
	}
	
	/**
	 * 상위 코드
	 * @param fd_up_code
	 */
	public void setFd_up_code(String fd_up_code) {
		this.fd_up_code = fd_up_code;
	}
	
	/**
	 * 코드 이름
	 * @return fd_code_name
	 */
	public String getFd_code_name() {
		return fd_code_name;
	}
	
	/**
	 * 코드 이름
	 * @param fd_code_name
	 */
	public void setFd_code_name(String fd_code_name) {
		this.fd_code_name = fd_code_name;
	}
	
	/**
	 * 기본 일반 포인트	
	 * @return fd_point_level_5
	 */
	public String getFd_point_level_5() {
		return fd_point_level_5;
	}
	
	/**
	 * 기본 일반 포인트	
	 * @param fd_point_level_5
	 */
	public void setFd_point_level_5(String fd_point_level_5) {
		this.fd_point_level_5 = fd_point_level_5;
	}
	
	/**
	 * 기본 서포터즈 포인트
	 * @return fd_point_level_10
	 */
	public String getFd_point_level_10() {
		return fd_point_level_10;
	}
	
	/**
	 * 기본 서포터즈 포인트
	 * @param fd_point_level_10
	 */
	public void setFd_point_level_10(String fd_point_level_10) {
		this.fd_point_level_10 = fd_point_level_10;
	}

	
	/**
	 * 코드 조회 타입 및 검색 타입
	 * @return
	 */
	public String getSearch_type() {
		return search_type;
	}

	/**
	 * 코드 조회 타입 및 검색 타입
	 * @param search_type
	 */
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

	/**
	 * search_code
	 * @return search_code
	 */
	public String getSearch_code() {
		return search_code;
	}

	/**
	 * search_code
	 * @param search_code
	 */
	public void setSearch_code(String search_code) {
		this.search_code = search_code;
	}

	
	
}
