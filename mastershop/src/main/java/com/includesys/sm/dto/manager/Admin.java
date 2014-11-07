package com.includesys.sm.dto.manager;

public class Admin 
{
	private String pk_admin_id;
	
	/**
	 * 
	 * @return 관리자 id
	 */
	public String getPk_admin_id()
	{
		return this.pk_admin_id;
	}
	
	/**
	 * 
	 * @param pk_admin_id 관리자 id
	 */
	public void setPk_admin_id(String pk_admin_id)
	{
		this.pk_admin_id = pk_admin_id;
	}
	
	private String fd_admin_pw = "";
	
	/**
	 * 
	 * @return 관리자 pw[단방향]
	 */
	public String getFd_admin_pw()
	{
		return this.fd_admin_pw;
	}
	
	/**
	 * 
	 * @param fd_admin_pw 관리자 pw
	 */
	public void setFd_admin_pw(String fd_admin_pw)
	{
		this.fd_admin_pw = fd_admin_pw;
	}
	
	private String fd_admin_name;
	
	/**
	 * 
	 * @return 관리자 이름
	 */
	public String getFd_admin_name()
	{
		return this.fd_admin_name;
	}
	
	/**
	 * 
	 * @param fd_admin_name 관리자 이름
	 */
	public void setFd_admin_name(String fd_admin_name)
	{
		this.fd_admin_name = fd_admin_name;
	}
	
	private String fd_admin_level_cd;
	
	/**
	 * 
	 * @return 관리자 등급 [총괄, 일반]
	 */
	public String getFd_admin_level_cd()
	{
		return this.fd_admin_level_cd;
	}
	
	/**
	 * 
	 * @param fd_admin_level_cd 관리자 등급 [총괄, 일반]
	 */
	public void setFd_admin_level_cd(String fd_admin_level_cd)
	{
		this.fd_admin_level_cd = fd_admin_level_cd;
	}
	
	private String fd_team;
	
	/**
	 * 
	 * @return 부서명
	 */
	public String getFd_team()
	{
		return this.fd_team;
	}
	
	/**
	 * 
	 * @param fd_team 부서명
	 */
	public void setFd_team(String fd_team)
	{
		this.fd_team = fd_team;
	}	
		
	
	private String fd_admin_status_cd;
	
	/**
	 * 
	 * @return 상태 [신청/승인/거절/해지]
	 */
	public String getFd_admin_status_cd()
	{
		return this.fd_admin_status_cd;
	}
	
	/**
	 * 
	 * @param fd_admin_status_cd 상태 [신청/승인/거절/해지]
	 */
	public void setFd_admin_status_cd(String fd_admin_status_cd)
	{
		this.fd_admin_status_cd = fd_admin_status_cd;
	}		
	
	private String fd_mobile;
	
	/**
	 * 
	 * @return 휴대전화 번호
	 */
	public String getFd_mobile()
	{
		return this.fd_mobile;
	}
	
	/**
	 * 
	 * @param fd_mobile 휴대전화 번호
	 */
	public void setFd_mobile(String fd_mobile)
	{
		this.fd_mobile = fd_mobile;
	}	
	
	private String fd_phone;
	
	/**
	 * 
	 * @return 유선전화 번호
	 */
	public String getFd_phone()
	{
		return this.fd_phone;
	}
	
	/**
	 * 
	 * @param fd_phone 유선전화 번호
	 */
	public void setFd_phone(String fd_phone)
	{
		this.fd_phone = fd_phone;
	}	
	
	private String fd_email;
	
	/**
	 * 
	 * @return 이메일
	 */
	public String getFd_email()
	{
		return this.fd_email;
	}
	
	/**
	 * 
	 * @param fd_email 이메일
	 */
	public void setFd_email(String fd_email)
	{
		this.fd_email = fd_email;
	}		
	
	private String fd_reg_date;
	
	/**
	 * 
	 * return 등록일
	 */
	public String getFd_reg_date()
	{
		return this.fd_reg_date;
	}
	
	/**
	 * 
	 * @param fd_reg_date 등록일
	 */
	public void setFd_reg_date(String fd_reg_date)
	{
		this.fd_reg_date = fd_reg_date;
	}		
	
	private String fd_login_date;
	
	/**
	 * 
	 * @return 최종 로그인 시간
	 */
	public String getFd_login_date()
	{
		return this.fd_login_date;
	}
	
	/**
	 * 
	 * @param fd_login_date 최종 로그인 시간
	 */
	public void setFd_login_date(String fd_login_date)
	{
		this.fd_login_date = fd_login_date;
	}		
	
	private String fd_change_pw_date = "";
	
	/**
	 * 
	 * @return 최종 pw 변경일
	 */
	public String getFd_change_pw_date()
	{
		return this.fd_change_pw_date;
	}
	
	/**
	 * 
	 * @param fd_change_pw_date 최종 pw 변경일
	 */
	public void setFd_change_pw_date(String fd_change_pw_date)
	{
		this.fd_change_pw_date = fd_change_pw_date;
	}		
	
	private String fd_login_ip;
	
	/**
	 * 
	 * @return 최종 로그인 ip
	 */
	public String getFd_login_ip()
	{
		return this.fd_login_ip;
	}
	
	/**
	 * 
	 * @param fd_login_ip 최종 로그인 ip
	 */
	public void setFd_login_ip(String fd_login_ip)
	{
		this.fd_login_ip = fd_login_ip;
	}	
}
