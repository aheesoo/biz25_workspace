package com.includesys.sm.dto.csCenter;

public class Faq {
	long pk_seq;					// 일련번호	
	String fd_title;				// 타이틀
	String fd_content;			// 내용
	String fd_user_id;			// 작성자 ID
	String fd_user_ip;			// 작성자 IP
	String fd_user_name;		// 작성자 이름
	String fd_type_code;		// 분류 코드 3600
	String fd_open_yn;			// 노출여부(Y:노출, N:비노출)
	int fd_hit;						// 조회수
	String fd_reg_date;			// 등록일
	String fd_mod_date;		// 수정일
	String fd_code_name;		// 분류 코드명
	String pk_code;				// 분류 코드값.	
	String fd_type;				// 
	String fd_mod_ip;			// 수정 아이피
	
	


	/**
	 * @return the pk_seq
	 */
	public long getPk_seq() {
		return pk_seq;
	}




	/**
	 * @return the fd_title
	 */
	public String getFd_title() {
		return fd_title;
	}




	/**
	 * @return the fd_content
	 */
	public String getFd_content() {
		return fd_content;
	}




	/**
	 * @return the fd_user_id
	 */
	public String getFd_user_id() {
		return fd_user_id;
	}




	/**
	 * @return the fd_user_ip
	 */
	public String getFd_user_ip() {
		return fd_user_ip;
	}




	/**
	 * @return the fd_user_name
	 */
	public String getFd_user_name() {
		return fd_user_name;
	}




	/**
	 * @return the fd_type_code
	 */
	public String getFd_type_code() {
		return fd_type_code;
	}




	/**
	 * @return the fd_open_yn
	 */
	public String getFd_open_yn() {
		return fd_open_yn;
	}




	/**
	 * @return the fd_hit
	 */
	public int getFd_hit() {
		return fd_hit;
	}




	/**
	 * @return the fd_reg_date
	 */
	public String getFd_reg_date() {
		return fd_reg_date;
	}




	/**
	 * @return the fd_mod_date
	 */
	public String getFd_mod_date() {
		return fd_mod_date;
	}




	/**
	 * @return the fd_code_name
	 */
	public String getFd_code_name() {
		return fd_code_name;
	}




	/**
	 * @return the pk_code
	 */
	public String getPk_code() {
		return pk_code;
	}




	/**
	 * @return the fd_type
	 */
	public String getFd_type() {
		return fd_type;
	}




	/**
	 * @return the fd_mod_ip
	 */
	public String getFd_mod_ip() {
		return fd_mod_ip;
	}




	/**
	 * @param pk_seq the pk_seq to set
	 */
	public void setPk_seq(long pk_seq) {
		this.pk_seq = pk_seq;
	}




	/**
	 * @param fd_title the fd_title to set
	 */
	public void setFd_title(String fd_title) {
		this.fd_title = fd_title;
	}




	/**
	 * @param fd_content the fd_content to set
	 */
	public void setFd_content(String fd_content) {
		this.fd_content = fd_content;
	}




	/**
	 * @param fd_user_id the fd_user_id to set
	 */
	public void setFd_user_id(String fd_user_id) {
		this.fd_user_id = fd_user_id;
	}




	/**
	 * @param fd_user_ip the fd_user_ip to set
	 */
	public void setFd_user_ip(String fd_user_ip) {
		this.fd_user_ip = fd_user_ip;
	}




	/**
	 * @param fd_user_name the fd_user_name to set
	 */
	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}




	/**
	 * @param fd_type_code the fd_type_code to set
	 */
	public void setFd_type_code(String fd_type_code) {
		this.fd_type_code = fd_type_code;
	}




	/**
	 * @param fd_open_yn the fd_open_yn to set
	 */
	public void setFd_open_yn(String fd_open_yn) {
		this.fd_open_yn = fd_open_yn;
	}




	/**
	 * @param fd_hit the fd_hit to set
	 */
	public void setFd_hit(int fd_hit) {
		this.fd_hit = fd_hit;
	}




	/**
	 * @param fd_reg_date the fd_reg_date to set
	 */
	public void setFd_reg_date(String fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}




	/**
	 * @param fd_mod_date the fd_mod_date to set
	 */
	public void setFd_mod_date(String fd_mod_date) {
		this.fd_mod_date = fd_mod_date;
	}




	/**
	 * @param fd_code_name the fd_code_name to set
	 */
	public void setFd_code_name(String fd_code_name) {
		this.fd_code_name = fd_code_name;
	}




	/**
	 * @param pk_code the pk_code to set
	 */
	public void setPk_code(String pk_code) {
		this.pk_code = pk_code;
	}




	/**
	 * @param fd_type the fd_type to set
	 */
	public void setFd_type(String fd_type) {
		this.fd_type = fd_type;
	}




	/**
	 * @param fd_mod_ip the fd_mod_ip to set
	 */
	public void setFd_mod_ip(String fd_mod_ip) {
		this.fd_mod_ip = fd_mod_ip;
	}




	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("=== [" + this.getClass().getSimpleName() +  "] ===\n");		
		sb.append("[pk_seq  			] : " 	+ pk_seq			+ "\n");
		sb.append("[fd_user_id  		] : " 	+ fd_user_id		+ "\n");
		sb.append("[fd_type_code  	] : " 	+fd_type_code	+ "\n");		
		sb.append("[fd_title  			] : " 	+ fd_title			+ "\n");
		sb.append("[fd_content  		] : " 	+ fd_content		+ "\n");
		sb.append("[fd_user_ip  		] : " 	+ fd_user_ip		+ "\n");
		sb.append("[fd_user_name  	] : " 	+ fd_user_name	+ "\n");
		sb.append("[fd_open_yn  		] : " 	+ fd_open_yn		+ "\n");
		sb.append("[fd_hit  				] : " 	+ fd_hit				+ "\n");		
		sb.append("[fd_reg_date  	] : " 	+ fd_reg_date		+ "\n");
		sb.append("[fd_mod_date  	] : " 	+ fd_mod_date	+ "\n");
		sb.append("[fd_code_name  	] : " 	+ fd_code_name	+ "\n");
		sb.append("[pk_code  		] : " 	+ pk_code		+ "\n");
		sb.append("[fd_mod_ip  		] : " 	+ fd_mod_ip		+ "\n");
		
		
		return sb.toString();	
	}
}
