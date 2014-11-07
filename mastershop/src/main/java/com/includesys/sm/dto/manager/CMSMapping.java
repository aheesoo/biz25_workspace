package com.includesys.sm.dto.manager;

public class CMSMapping {
	
	private String pk_admin_id;
	private String pk_url_code;
	private String org_pk_admin_id = "";
	private String org_pk_url_code = "";
	
	public String getPk_admin_id() {
		return pk_admin_id;
	}
	public void setPk_admin_id(String pk_admin_id) {
		this.pk_admin_id = pk_admin_id;
	}
	public String getPk_url_code() {
		return pk_url_code;
	}
	public void setPk_url_code(String pk_url_code) {
		this.pk_url_code = pk_url_code;
	}
	public String getOrg_pk_admin_id() {
		return org_pk_admin_id;
	}
	public void setOrg_pk_admin_id(String org_pk_admin_id) {
		this.org_pk_admin_id = org_pk_admin_id;
	}
	public String getOrg_pk_url_code() {
		return org_pk_url_code;
	}
	public void setOrg_pk_url_code(String org_pk_url_code) {
		this.org_pk_url_code = org_pk_url_code;
	}
	
	
}
