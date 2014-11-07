package com.includesys.sm.dto.smsManager;


public class Emoticon {
	
	/*table : tbl_emoticon, tbl_emoticon_group, tbl_sms_group*/
	
	/*공통*/
				private String group_code;

	/*tbl_emoticon*/
				private String pk_seq;				/*일련번호*/
				private String fk_group_code;	/*그룹코드*/
				private String fd_title;				/*제목*/
	private String fd_content;		/*내용*/
				private String fd_sms_type;		/*발송타입 S:SMS L:LMS M:MMS*/
	private String fd_file_path;		/*MMS 파일경로*/
	private String fd_file_name;		/*MMS 파일명*/
	private String fd_reg_date;		/*등록일*/
	
	
	/*tbl_emoticon_group*/
				private String fd_group_type;	/*타입구분  B:기업형 G:일반*/
				private String fd_group_code;	/*그룹코드*/
				private String fd_group_name;	/*그룹명*/
				private String f_sort;				/*그룹 표시순서*/
	
	/*tbl_sms_group*/   //발송 대상 관리
				private String fk_member_id;				// 발송 아아디
				private String fk_tel;							// 발송 전화번호
				private String send_subject;					// 발송 제목
				private String send_content;				// 발송 내용
				private String attachment_path;			// 발송 첨부파일 
				private int attachment_file_size; 			// 발송 첨부 파일 용량
				private String msg_type;						// 발송 메세지 타입
				private String msg_sub_type;				// 발송 메세지 서브 타입
				
	/*앞으로 밀어버린 변수들은 emoticon 리뉴얼 이전 데이터들입니다. 보시고 필요없는 부분은 삭제해주세요!!*/
				
	/*table : tbl_emoticon_new*/
	private String pk_mid; 				/*MID*/
	private String fd_type; 			/*타입 S,L,M*/
	private String fd_category1; 	/*카테고리 대*/
	private String fd_category2; 	/*카테고리 중*/
	private String fd_category3; 	/*카테고리 하*/
	private String fd_content_no; 	/*컨텐츠 NO*/
	private String fd_subject; 		/*컨텐츠제목*/
	private String fd_event_yy; 		/*이벤트 년*/
	private String fd_event_mm; 	/*이벤트 월*/
	private String fd_event_dd; 		/*이벤트 일*/
	private String fd_event_memo; 	/*이벤트내용*/
	
	
	
	
	public String getPk_mid() {
		return pk_mid;
	}

	public void setPk_mid(String pk_mid) {
		this.pk_mid = pk_mid;
	}

	public String getFd_type() {
		return fd_type;
	}

	public void setFd_type(String fd_type) {
		this.fd_type = fd_type;
	}

	public String getFd_category1() {
		return fd_category1;
	}

	public void setFd_category1(String fd_category1) {
		this.fd_category1 = fd_category1;
	}

	public String getFd_category2() {
		return fd_category2;
	}

	public void setFd_category2(String fd_category2) {
		this.fd_category2 = fd_category2;
	}

	public String getFd_category3() {
		return fd_category3;
	}

	public void setFd_category3(String fd_category3) {
		this.fd_category3 = fd_category3;
	}

	public String getFd_content_no() {
		return fd_content_no;
	}

	public void setFd_content_no(String fd_content_no) {
		this.fd_content_no = fd_content_no;
	}

	public String getFd_subject() {
		return fd_subject;
	}

	public void setFd_subject(String fd_subject) {
		this.fd_subject = fd_subject;
	}

	public String getFd_event_yy() {
		return fd_event_yy;
	}

	public void setFd_event_yy(String fd_event_yy) {
		this.fd_event_yy = fd_event_yy;
	}

	public String getFd_event_mm() {
		return fd_event_mm;
	}

	public void setFd_event_mm(String fd_event_mm) {
		this.fd_event_mm = fd_event_mm;
	}

	public String getFd_event_dd() {
		return fd_event_dd;
	}

	public void setFd_event_dd(String fd_event_dd) {
		this.fd_event_dd = fd_event_dd;
	}

	public String getFd_event_memo() {
		return fd_event_memo;
	}

	public void setFd_event_memo(String fd_event_memo) {
		this.fd_event_memo = fd_event_memo;
	}

	/**
	 * 
	 * @return
	 */
	public String getPk_seq() {
		return pk_seq;
	}

	/**
	 * 
	 * @return
	 */
	public String getFk_group_code() {
		return fk_group_code;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_title() {
		return fd_title;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_content() {
		return fd_content;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_sms_type() {
		return fd_sms_type;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_file_path() {
		return fd_file_path;
	}

	/**
	 * 	
	 * @return
	 */
	public String getFd_file_name() {
		return fd_file_name;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_reg_date() {
		return fd_reg_date;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_group_type() {
		return fd_group_type;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_group_code() {
		return fd_group_code;
	}

	/**
	 * 
	 * @return
	 */
	public String getFd_group_name() {
		return fd_group_name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getF_sort() {
		return f_sort;
	}

	/**
	 * 
	 * @return
	 */
	public String getFk_member_id() {
		return fk_member_id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFk_tel() {
		return fk_tel;
	}

	/**
	 * 
	 * @return
	 */
	public String getSend_subject() {
		return send_subject;
	}

	/**
	 * 
	 * @return
	 */
	public String getSend_content() {
		return send_content;
	}

	/**
	 * 
	 * @return
	 */
	public String getAttachment_path() {
		return attachment_path;
	}

	/**
	 * 
	 * @return
	 */
	public int getAttachment_file_size() {
		return attachment_file_size;
	}

	/**
	 * 
	 * @return
	 */
	public String getMsg_type() {
		return msg_type;
	}

	/**
	 * 
	 * @return
	 */
	public String getMsg_sub_type() {
		return msg_sub_type;
	}

	/**
	 * 
	 * @return
	 */
	public String getGroup_code() {
		return group_code;
	}
	
	//-- Setter --//
	
	/**
	 * 
	 * @param pk_seq
	 */
	public void setPk_seq(String pk_seq) {
		this.pk_seq = pk_seq;
	}

	/**
	 * 
	 * @param fk_group_code
	 */
	public void setFk_group_code(String fk_group_code) {
		this.fk_group_code = fk_group_code;
	}

	/**
	 * 
	 * @param fd_title
	 */
	public void setFd_title(String fd_title) {
		this.fd_title = fd_title;
	}

	/**
	 * 
	 * @param fd_content
	 */
	public void setFd_content(String fd_content) {
		this.fd_content = fd_content;
	}
	
	/**
	 * 
	 * @param fd_sms_type
	 */
	public void setFd_sms_type(String fd_sms_type) {
		this.fd_sms_type = fd_sms_type;
	}

	/**
	 * 
	 * @param fd_file_path
	 */
	public void setFd_file_path(String fd_file_path) {
		this.fd_file_path = fd_file_path;
	}

	/**
	 * 
	 * @param fd_file_name
	 */
	public void setFd_file_name(String fd_file_name) {
		this.fd_file_name = fd_file_name;
	}

	/**
	 * 
	 * @param fd_reg_date
	 */
	public void setFd_reg_date(String fd_reg_date) {
		this.fd_reg_date = fd_reg_date;
	}

	/**
	 * 
	 * @param fd_group_type
	 */
	public void setFd_group_type(String fd_group_type) {
		this.fd_group_type = fd_group_type;
	}

	/**
	 * 
	 * @param fd_group_code
	 */
	public void setFd_group_code(String fd_group_code) {
		this.fd_group_code = fd_group_code;
	}

	/**
	 * 
	 * @param fd_group_name
	 */
	public void setFd_group_name(String fd_group_name) {
		this.fd_group_name = fd_group_name;
	}

	/**
	 * 
	 * @param f_sort
	 */
	public void setF_sort(String f_sort) {
		this.f_sort = f_sort;
	}

	/**
	 * 
	 * @param fk_member_id
	 */
	public void setFk_member_id(String fk_member_id) {
		this.fk_member_id = fk_member_id;
	}

	/**
	 * 
	 * @param fk_tel
	 */
	public void setFk_tel(String fk_tel) {
		this.fk_tel = fk_tel;
	}
	/**
	 * 
	 * @param send_subject
	 */
	public void setSend_subject(String send_subject) {
		this.send_subject = send_subject;
	}

	/**
	 * 
	 * @param send_content
	 */
	public void setSend_content(String send_content) {
		this.send_content = send_content;
	}

	/**
	 * 
	 * @param attachment_path
	 */
	public void setAttachment_path(String attachment_path) {
		this.attachment_path = attachment_path;
	}

	/**
	 * 
	 * @param attachment_file_size
	 */
	public void setAttachment_file_size(int attachment_file_size) {
		this.attachment_file_size = attachment_file_size;
	}

	/**
	 * 
	 * @param msg_type
	 */
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	/**
	 * 
	 * @param msg_sub_type
	 */
	public void setMsg_sub_type(String msg_sub_type) {
		this.msg_sub_type = msg_sub_type;
	}

	/**
	 * 
	 * @param group_code
	 */
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String toString(){
		StringBuffer str = new StringBuffer();
	
	
		str.append(" group_code : "				+ group_code					+"\n" ) ;
		str.append(" pk_seq : "					+ pk_seq						+"\n" ) ;
		str.append(" fk_group_code : "			+ fk_group_code				+"\n" ) ;
		str.append(" fd_title : "					+ fd_title						+"\n" ) ;
		str.append(" fd_content : "				+ fd_content					+"\n" ) ;
		str.append(" fd_sms_type : "				+ fd_sms_type				+"\n" ) ;
		str.append(" fd_file_path : "				+ fd_file_path					+"\n" ) ;
		str.append(" fd_file_name : "			+ fd_file_name				+"\n" ) ;
		str.append(" fd_reg_date : "				+ fd_reg_date					+"\n" ) ;

		str.append(" fd_group_type : "			+ fd_group_type				+"\n" ) ;
		str.append(" fd_group_type : "			+ fd_group_code				+"\n" ) ;
		str.append(" fd_group_name : "			+ fd_group_name			+"\n" ) ;
		str.append(" f_sort : "						+ f_sort							+"\n" ) ;

		str.append(" fk_member_id : "			+ fk_member_id				+"\n" ) ;
		str.append(" fk_tel : "						+ fk_tel							+"\n" ) ;
		str.append(" send_subject : "			+ send_subject				+"\n" ) ;
		str.append(" send_content : "			+ send_content				+"\n" ) ;
		str.append(" attachment_path : "		+ attachment_path			+"\n" ) ;
		str.append(" attachment_file_size : "	+ attachment_file_size	+"\n" ) ;
		str.append(" msg_type : "					+ msg_type					+"\n" ) ;
		str.append(" msg_sub_type : "			+ msg_sub_type				+"\n" ) ;

		
		return str.toString();
	}
	
}
