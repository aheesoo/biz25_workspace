package com.includesys.sm.dto.manager;

/**
 * <p>로그인 정보</p>
 */
public class LoginInfo
{

	private String pk_member_id;
	private String fd_user_name;
	private String fd_tel;
	private String fd_openapi_use;
	private String remote_addr;
	
	private String fd_openapi_member_id;
	private String fd_openapi_member_pwd;
	private String fd_openapi_autologin;
	private String fd_view_name;
	
	
	
	public String getPk_member_id() {
		return pk_member_id;
	}


	public void setPk_member_id(String pk_member_id) {
		this.pk_member_id = pk_member_id;
	}


	public String getFd_user_name() {
		return fd_user_name;
	}


	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}


	public String getFd_tel() {
		return fd_tel;
	}


	public void setFd_tel(String fd_tel) {
		this.fd_tel = fd_tel;
	}


	public String getFd_openapi_use() {
		return fd_openapi_use;
	}


	public void setFd_openapi_use(String fd_openapi_use) {
		this.fd_openapi_use = fd_openapi_use;
	}


	public String getRemote_addr() {
		return remote_addr;
	}


	public void setRemote_addr(String remote_addr) {
		this.remote_addr = remote_addr;
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


	public String getFd_view_name() {
		return fd_view_name;
	}


	public void setFd_view_name(String fd_view_name) {
		this.fd_view_name = fd_view_name;
	}


	public LoginInfo(String pk_member_id, String fd_user_name, String fd_tel, String fd_openapi_use, String remote_addr,
			String fd_openapi_member_id, String fd_openapi_member_pwd, String fd_openapi_autologin, String fd_view_name)
	{
		this.pk_member_id			= pk_member_id;
		this.fd_user_name			= fd_user_name;
		this.fd_tel					= fd_tel;
		this.fd_openapi_use			= fd_openapi_use;
		this.remote_addr			= remote_addr;
		this.fd_openapi_member_id	= fd_openapi_member_id;
		this.fd_openapi_member_pwd	= fd_openapi_member_pwd;
		this.fd_openapi_autologin	= fd_openapi_autologin;
		this.fd_view_name 			= fd_view_name;
	}
	
}
