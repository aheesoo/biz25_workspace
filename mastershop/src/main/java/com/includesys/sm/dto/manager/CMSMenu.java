package com.includesys.sm.dto.manager;

public class CMSMenu 
{
	private String org_pk_url_code = "";
	
	private String fd_group_code;
	private String pk_url_code;
	private String fd_name;
	private String fd_url;
	private int fd_sort_no;
	private String fd_menu_yn;

	
	public String getOrg_pk_url_code() {
		return org_pk_url_code;
	}
	public void setOrg_pk_url_code(String org_pk_url_code) {
		this.org_pk_url_code = org_pk_url_code;
	}
	public String getFd_group_code() {
		return fd_group_code;
	}
	public void setFd_group_code(String fd_group_code) {
		this.fd_group_code = fd_group_code;
	}
	public String getPk_url_code() {
		return pk_url_code;
	}
	public void setPk_url_code(String pk_url_code) {
		this.pk_url_code = pk_url_code;
	}
	
	/**
	 * 
	 * @return 동일 level 정렬순서
	 */
	public int getFd_sort_no()
	{
		return this.fd_sort_no;
	}
	/**
	 * 
	 * @param fd_sort_no 동일 level 정렬순서
	 */
	public void setFd_sort_no(int fd_sort_no)
	{		
		this.fd_sort_no = (fd_sort_no < 1) ? 1 :  fd_sort_no;
	}
		
	/**
	 * 
	 * @return 메뉴이름
	 */
	public String getFd_name()
	{
		return this.fd_name;
	}
	/**
	 * 
	 * @param fd_name 메뉴이름
	 */
	public void setFd_name(String fd_name)
	{
		this.fd_name = fd_name;
	}
	
	/**
	 * 
	 * @return 메뉴 url
	 */
	public String getFd_url()
	{
		return this.fd_url;
	}
	/**
	 * 
	 * @param fd_url 메뉴 url
	 */
	public void setFd_url(String fd_url)
	{
		this.fd_url = fd_url;
	}
	
	/**
	 * 
	 * @return 메뉴 사용여부 yn
	 */
	public String getFd_menu_yn()
	{
		return this.fd_menu_yn;
	}
	/**
	 * 
	 * @param fd_menu_yn 메뉴 사용여부 yn
	 */
	public void setFd_menu_yn(String fd_menu_yn)
	{
		this.fd_menu_yn = fd_menu_yn;
	}
}
