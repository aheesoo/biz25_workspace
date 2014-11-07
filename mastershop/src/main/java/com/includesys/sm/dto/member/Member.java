package com.includesys.sm.dto.member;

public class Member {
	
	private String pk_member_id;		   //아이디
	private String fd_member_pwd;		   //비밀번호
	private String fd_tel;				   //일반전화
	private String pk_tel;				   //일반전화 //fd_tel과 공유
	private String fd_mobile;			   //휴대폰
	private String fd_user_name;		   //상호.성명
	private String fd_business_type;	   //업종
	private String fd_business_type1;	   //업종
	private String fd_business_type2;	   //업종
	private String fd_shop_name;		   //상호.성명
	private String fd_addr;				   //주소
	private String fd_addr_detail;		   //주소 상세
	private String fd_post_num;			   //우편번호
	private String fd_regist_num;		   //주민(법인)등록번호
	private String fd_corp_regist_num;	   //사업자등록번호
	private String fd_file1;			   //청약증빙자료1
	private String fd_file2;			   //청약증빙자료
	private String fd_user_access;		   //회원징계상태 활성/비활성
	private String fd_user_access_cont;	   //징계내용
	private String fd_user_access_file1;   //징계관련파일
	//private String fd_openapi_use;			//통화openapi 가입상태
	//private String fd_openapi_reg_date;		//통화오픈api 가입일
	//private String fd_openapi_cancel_date;	//통화오픈api 탈퇴일
	//private String fd_openapi_upd_date;		//통화오픈api 갱신일자(매일 갱신)
	private String fd_reg_date;			   //등록일
	private String fd_member_state;		   //회원 여부 (기본값:Y, 탈퇴시:N)
	private String fd_member_end_date;	   //회원 탈퇴일 (탈퇴가아닌경우 null)
	private String fd_file1_org;
	private String fd_file2_org;
	private String fd_file3_org;
	private String fd_file1_path;
	private String fd_file2_path;
	private String fd_file3_path;
	private String fd_view_name;
	private String fd_openapi_member_id;
	private String fd_openapi_member_pwd;
	private String fd_openapi_autologin;
			  
	
	//SUB
	private String fd_product_type;			//회원 전화번호에 따른 상품타입
	
	public String getPk_member_id() {
		return pk_member_id;
	}
	public void setPk_member_id(String pk_member_id) {
		this.pk_member_id = pk_member_id;
	}
	public String getFd_member_pwd() {
		return fd_member_pwd;
	}
	public void setFd_member_pwd(String fd_member_pwd) {
		this.fd_member_pwd = fd_member_pwd;
	}
	public String getFd_tel() {
		return fd_tel;
	}
	public void setFd_tel(String fd_tel) {
		this.fd_tel = fd_tel;
		this.pk_tel = fd_tel;
	}
	
	public String getPk_tel() {
		return pk_tel;
	}
	public void setPk_tel(String pk_tel) {
		this.pk_tel = pk_tel;
		this.fd_tel = pk_tel;
	}
	public String getFd_mobile() {
		return fd_mobile;
	}
	public void setFd_mobile(String fd_mobile) {
		this.fd_mobile = fd_mobile;
	}
	public String getFd_user_name() {
		return fd_user_name;
	}
	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}
	public String getFd_business_type() {
		return fd_business_type;
	}
	public void setFd_business_type(String fd_business_type) {
		this.fd_business_type = fd_business_type;
	}
	public String getFd_shop_name() {
		return fd_shop_name;
	}
	public void setFd_shop_name(String fd_shop_name) {
		this.fd_shop_name = fd_shop_name;
	}
	public String getFd_addr() {
		return fd_addr;
	}
	public void setFd_addr(String fd_addr) {
		this.fd_addr = fd_addr;
	}
	public String getFd_addr_detail() {
		return fd_addr_detail;
	}
	public void setFd_addr_detail(String fd_addr_detail) {
		this.fd_addr_detail = fd_addr_detail;
	}
	public String getFd_post_num() {
		return fd_post_num;
	}
	public void setFd_post_num(String fd_post_num) {
		this.fd_post_num = fd_post_num;
	}
	public String getFd_regist_num() {
		return fd_regist_num;
	}
	public void setFd_regist_num(String fd_regist_num) {
		this.fd_regist_num = fd_regist_num;
	}
	public String getFd_corp_regist_num() {
		return fd_corp_regist_num;
	}
	public void setFd_corp_regist_num(String fd_corp_regist_num) {
		this.fd_corp_regist_num = fd_corp_regist_num;
	}
	public String getFd_file1() {
		return fd_file1;
	}
	public void setFd_file1(String fd_file1) {
		this.fd_file1 = fd_file1;
	}
	public String getFd_file2() {
		return fd_file2;
	}
	public void setFd_file2(String fd_file2) {
		this.fd_file2 = fd_file2;
	}
	public String getFd_user_access() {
		return fd_user_access;
	}
	public void setFd_user_access(String fd_user_access) {
		this.fd_user_access = fd_user_access;
	}
	public String getFd_user_access_cont() {
		return fd_user_access_cont;
	}
	public void setFd_user_access_cont(String fd_user_access_cont) {
		this.fd_user_access_cont = fd_user_access_cont;
	}
	public String getFd_user_access_file1() {
		return fd_user_access_file1;
	}
	public void setFd_user_access_file1(String fd_user_access_file1) {
		this.fd_user_access_file1 = fd_user_access_file1;
	}
	
	public String getFd_reg_date() {
		return fd_reg_date;
	}
	public void setFd_reg_date(String fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}
	public String getFd_member_state() {
		return fd_member_state;
	}
	public void setFd_member_yn(String fd_member_state) {
		this.fd_member_state = fd_member_state;
	}
	public String getFd_member_end_date() {
		return fd_member_end_date;
	}
	public void setFd_member_end_date(String fd_member_end_date) {
		this.fd_member_end_date = fd_member_end_date;
	}
	public String getFd_product_type() {
		return fd_product_type;
	}
	public void setFd_product_type(String fd_product_type) {
		this.fd_product_type = fd_product_type;
	}
	public void setFd_member_state(String fd_member_state) {
		this.fd_member_state = fd_member_state;
	}
	public String getFd_file1_org() {
		return fd_file1_org;
	}
	public void setFd_file1_org(String fd_file1_org) {
		this.fd_file1_org = fd_file1_org;
	}
	public String getFd_file2_org() {
		return fd_file2_org;
	}
	public void setFd_file2_org(String fd_file2_org) {
		this.fd_file2_org = fd_file2_org;
	}
	public String getFd_file3_org() {
		return fd_file3_org;
	}
	public void setFd_file3_org(String fd_file3_org) {
		this.fd_file3_org = fd_file3_org;
	}
	public String getFd_file1_path() {
		return fd_file1_path;
	}
	public void setFd_file1_path(String fd_file1_path) {
		this.fd_file1_path = fd_file1_path;
	}
	public String getFd_file2_path() {
		return fd_file2_path;
	}
	public void setFd_file2_path(String fd_file2_path) {
		this.fd_file2_path = fd_file2_path;
	}
	public String getFd_file3_path() {
		return fd_file3_path;
	}
	public void setFd_file3_path(String fd_file3_path) {
		this.fd_file3_path = fd_file3_path;
	}
	public String getFd_view_name() {
		return fd_view_name;
	}
	public void setFd_view_name(String fd_view_name) {
		this.fd_view_name = fd_view_name;
	}
	public String getFd_openapi_member_id() {
		return fd_openapi_member_id;
	}
	public void setFd_openapi_member_id(String fd_openapi_member_id) {
		this.fd_openapi_member_id = fd_openapi_member_id;
	}
	public String getFd_openapi_member_pwd() {
		return fd_openapi_member_pwd;
	}
	public void setFd_openapi_member_pwd(String fd_openapi_member_pwd) {
		this.fd_openapi_member_pwd = fd_openapi_member_pwd;
	}
	public String getFd_openapi_autologin() {
		return fd_openapi_autologin;
	}
	public void setFd_openapi_autologin(String fd_openapi_autologin) {
		this.fd_openapi_autologin = fd_openapi_autologin;
	}
	public String getFd_business_type1() {
		return fd_business_type1;
	}
	public void setFd_business_type1(String fd_business_type1) {
		this.fd_business_type1 = fd_business_type1;
	}
	public String getFd_business_type2() {
		return fd_business_type2;
	}
	public void setFd_business_type2(String fd_business_type2) {
		this.fd_business_type2 = fd_business_type2;
	}

}
