package com.includesys.sm.dto.product;

public class Product {
	String pk_product_code;				// 품목 코드 pk
	String fd_up_code;						// 상위 코드
	String fd_code_name;					// 코드 이름
	String fd_point_level_5;				// 기본 일반 포인트	
	String fd_point_level_10;				// 기본 서포터즈 포인트
	String pk_seq;								// 적립정책 일련번호
	String fk_product_code;				// 적립정책 품목코드fk
	String fd_use_sdate;					// 유효기간 시작일
	String fd_use_edate;					// 유효기간 종료일
	String fd_open_yn;						// 적립정책 사용 유무
	int row_cnt = 0;							// row수 (품목 수)
	String search_Type;						// 코드 조회 타입 및 검색 타입
	String search_code;						// 검색 코드
	String depth1;								// 1depth 명
	String depth2;								// 2depth 명
		
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
	 * 적립정책 일련번호
	 * @return pk_seq
	 */
	public String getPk_seq() {
		return pk_seq;
	}
	
	/**
	 * 적립정책 일련번호
	 * @return pk_seq
	 */
	public void setPk_seq(String pk_seq) {
		this.pk_seq = pk_seq;
	}
	
	/**
	 * 적립정책 품목코드fk
	 * @return fk_product_code
	 */
	public String getFk_product_code() {
		return fk_product_code;
	}
	
	/**
	 * 적립정책 품목코드fk
	 * @param fk_product_code
	 */
	public void setFk_product_code(String fk_product_code) {
		this.fk_product_code = fk_product_code;
	}
	
	/**
	 * 유효기간 시작일
	 * @return fd_use_sdate
	 */
	public String getFd_use_sdate() {
		return fd_use_sdate;
	}
	
	/**
	 * 유효기간 시작일
	 * @param fd_use_sdate
	 */
	public void setFd_use_sdate(String fd_use_sdate) {
		this.fd_use_sdate = fd_use_sdate;
	}
	
	/**
	 * 유효기간 종료일
	 * @return fd_use_edate
	 */
	public String getFd_use_edate() {
		return fd_use_edate;
	}
	
	/**
	 * 유효기간 종료일
	 * @param fd_use_edate
	 */
	public void setFd_use_edate(String fd_use_edate) {
		this.fd_use_edate = fd_use_edate;
	}
	
	/**
	 * 적립정책 사용 유무 
	 * @return fd_open_yn 
	 */
	public String getFd_open_yn() {
		return fd_open_yn;
	}
	
	/**
	 * 적립정책 사용 유무
	 * @param fd_open_yn
	 * 
	 */
	public void setFd_open_yn(String fd_open_yn) {
		this.fd_open_yn = fd_open_yn;
	}

	/**
	 *  row수 (품목 수)   
	 * @return row_cnt
	 */
	
	public int getRow_cnt() {
		return row_cnt;
	}
	/**
	 *  row수 (품목 수)
	 * @param row_cnt
	 * 
	 */
	public void setRow_cnt(int row_cnt) {
		this.row_cnt = row_cnt;
	}

	/**
	 * 코드 조회 타입 및 검색 타입
	 * @return search_Type
	 */
	public String getSearch_Type() {
		return search_Type;
	}

	/**
	 * 코드 조회 타입 및 검색 타입
	 * @param search_Type
	 */
	public void setSearch_Type(String search_Type) {
		this.search_Type = search_Type;
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

	/**
	 * 1 depth 명
	 * @return depth1
	 */
	public String getDepth1() {
		return depth1;
	}

	/**
	 * 1 depth 명
	 * @param depth1
	 */
	public void setDepth1(String depth1) {
		this.depth1 = depth1;
	}

	/**
	 * 2 depth 명
	 * @return depth2
	 */
	public String getDepth2() {
		return depth2;
	}

	/**
	 * 2 depth 명
	 * @param depth2
	 */
	public void setDepth2(String depth2) {
		this.depth2 = depth2;
	}

	
	
	
}
