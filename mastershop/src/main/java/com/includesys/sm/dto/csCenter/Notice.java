package com.includesys.sm.dto.csCenter;

public class Notice {
	long pk_seq;					// 일련번호
	String fd_user_id;			// 작성자 ID
	String fd_title;				// 타이틀
	String fd_content;			// 내용
	String fd_user_ip;			// 작성자 IP
	String fd_user_name;		// 작성자 이름
	String fd_open_yn;			// 노출여부(Y:노출, N:비노출)
	int fd_hit;						// 조회수
	String fd_reg_date;			// 등록일
	String fd_mod_date;		// 수정일
		
	public long getPk_seq() {
		return pk_seq;
	}

	public String getFd_user_id() {
		return fd_user_id;
	}

	public String getFd_title() {
		return fd_title;
	}

	public String getFd_content() {
		return fd_content;
	}

	public String getFd_user_ip() {
		return fd_user_ip;
	}

	public String getFd_user_name() {
		return fd_user_name;
	}

	public String getFd_open_yn() {
		return fd_open_yn;
	}

	public int getFd_hit() {
		return fd_hit;
	}

	public String getFd_reg_date() {
		return fd_reg_date;
	}

	public String getFd_mod_date() {
		return fd_mod_date;
	}

	public void setPk_seq(long pk_seq) {
		this.pk_seq = pk_seq;
	}

	public void setFd_user_id(String fd_user_id) {
		this.fd_user_id = fd_user_id;
	}

	public void setFd_title(String fd_title) {
		this.fd_title = fd_title;
	}

	public void setFd_content(String fd_content) {
		this.fd_content = fd_content;
	}

	public void setFd_user_ip(String fd_user_ip) {
		this.fd_user_ip = fd_user_ip;
	}

	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}

	public void setFd_open_yn(String fd_open_yn) {
		this.fd_open_yn = fd_open_yn;
	}

	public void setFd_hit(int fd_hit) {
		this.fd_hit = fd_hit;
	}

	public void setFd_reg_date(String fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}

	public void setFd_mod_date(String fd_mod_date) {
		this.fd_mod_date = fd_mod_date;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("=== [" + this.getClass().getSimpleName() +  "] ===\n");		
		sb.append("[pk_seq  			] : " 	+ pk_seq			+ "\n");
		sb.append("[fd_user_id  		] : " 	+ fd_user_id		+ "\n");
		sb.append("[fd_title  			] : " 	+ fd_title			+ "\n");
		sb.append("[fd_content  		] : " 	+ fd_content		+ "\n");
		sb.append("[fd_user_ip  		] : " 	+ fd_user_ip		+ "\n");
		sb.append("[fd_user_name  	] : " 	+ fd_user_name	+ "\n");
		sb.append("[fd_open_yn  		] : " 	+ fd_open_yn		+ "\n");
		sb.append("[fd_hit  				] : " 	+ fd_hit				+ "\n");		
		sb.append("[fd_reg_date  	] : " 	+ fd_reg_date		+ "\n");
		sb.append("[fd_mod_date  	] : " 	+ fd_mod_date	+ "\n");
		return sb.toString();	
	}
}
