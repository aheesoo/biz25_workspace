package com.includesys.sm.dto.csManager;

public class CsCustomer {
/*
  `fk_member_id` varchar(32) NOT NULL COMMENT '아이디',
  `pk_customer_tel` varchar(14) NOT NULL DEFAULT '' COMMENT '고객 전화번호',
  `fd_user_name` varchar(32) DEFAULT NULL COMMENT '고객 이름',
  `fd_addr` varchar(128) DEFAULT NULL COMMENT '주소',
  `fd_memo` varchar(2048) DEFAULT NULL COMMENT '고객 메모',
  `fd_view_flag` varchar(1) DEFAULT NULL COMMENT '보기옵션 0:보기 1:숨김',
  `fd_rev_sms_flag` varchar(1) DEFAULT 'N' COMMENT '수신거부 거부:Y 승락:N (기본값:N)',
  `fd_new_date` varchar(8) DEFAULT NULL COMMENT '최초 등록 년월일 ex:20140101(신규고객 대상추출)',
  `fd_mod_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '정보 수정일',
, (select sum(fd_tot_day) from tbl_report_call_cnt where  pk_member_tel=pk_customer_tel and pk_member_id=fk_member_id) as fd_call_cnt
, (select (fd_sms_success_cnt+fd_sms_fail_cnt) from tbl_report_sms_cnt where pk_tel=pk_customer_tel and pk_member_id=fk_member_id) as fd_sms_cnt
*/
	private String fk_member_id;		// 고객 ID
	private String pk_customer_tel;		// 고객 전화번호
	private String fd_view_flag;		// 보기옵션 0:보기 1:숨김
	private String fd_user_name;		// 고객 이름
	private String fd_rev_sms_flag;		// 수신거부 거부:Y 승락:N (기본값:N)
	private String fd_new_date;			// 최초 등록 년월일 ex:20140101(신규고객 대상추출)
	private String fd_mod_date;			// 정보 수정일
	private String fd_memo;				// 고객 메모
	private String fd_addr;				// 주소
	private String fd_last_date;		// 최근수신일
	private String fd_call_cnt;			// 전화 카운트
	private String fd_sms_cnt;			// SMS 카운트
	
	private String reserve_date;			//발송 예약 일시
	
	private int fd_sms_success_cnt; //문자 성공건수
	private int fd_sms_fail_cnt; //문자 실패건수
	
	private int week_total_cnt; //요일 총 통화 건수
	private int week_1_cnt; //일요일 통화건수
	private int week_2_cnt; //월요일 통화건수
	private int week_3_cnt; //화요일 통화건수
	private int week_4_cnt; //수요일 통화건수
	private int week_5_cnt; //목요일 통화건수
	private int week_6_cnt; //금요일 통화건수
	private int week_7_cnt; //토요일 통화건수
	
	private int time_total_cnt; //시간대 총 통화 건수
	private int time_a_cnt; //새벽 시간대 통화건수
	private int time_b_cnt; //아침 시간대 통화건수
	private int time_c_cnt; //오전 시간대 통화건수
	private int time_d_cnt; //점심 시간대 통화건수
	private int time_e_cnt; //오후 시간대 통화건수
	private int time_f_cnt; //저녁 시간대 통화건수
	private int time_g_cnt; //야간 시간대 통화건수
	
	private String fk_seq; //시퀀스번호
	private String fk_tel; //회신번호 (보내는번호)
	private String receive_number; //받는번호
	private String msg_type; //메시지 요청 타입 sms(1:subtype=1) lms(4:subtype=1) mms (4:subtype=2~4)
	private String msg_sub_type; //메시지 세부유형 1:text 2:url 3:이미지 4:오디오
	private String res_result; //결과코드 0:정상, 기타:실패
	private String send_content; //메세지 내용
	private String attachment_path; //파일 저장 업로드 경로
	
	
	
	
	
	public String getAttachment_path() {
		return attachment_path;
	}
	public void setAttachment_path(String attachment_path) {
		this.attachment_path = attachment_path;
	}
	public String getSend_content() {
		return send_content;
	}
	public void setSend_content(String send_content) {
		this.send_content = send_content;
	}
	public String getFk_tel() {
		return fk_tel;
	}
	public void setFk_tel(String fk_tel) {
		this.fk_tel = fk_tel;
	}
	public String getReceive_number() {
		return receive_number;
	}
	public void setReceive_number(String receive_number) {
		this.receive_number = receive_number;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getMsg_sub_type() {
		return msg_sub_type;
	}
	public void setMsg_sub_type(String msg_sub_type) {
		this.msg_sub_type = msg_sub_type;
	}
	public String getRes_result() {
		return res_result;
	}
	public void setRes_result(String res_result) {
		this.res_result = res_result;
	}
	public String getFk_seq() {
		return fk_seq;
	}
	public void setFk_seq(String fk_seq) {
		this.fk_seq = fk_seq;
	}
	public int getFd_sms_success_cnt() {
		return fd_sms_success_cnt;
	}
	public void setFd_sms_success_cnt(int fd_sms_success_cnt) {
		this.fd_sms_success_cnt = fd_sms_success_cnt;
	}
	public int getFd_sms_fail_cnt() {
		return fd_sms_fail_cnt;
	}
	public void setFd_sms_fail_cnt(int fd_sms_fail_cnt) {
		this.fd_sms_fail_cnt = fd_sms_fail_cnt;
	}
	public int getWeek_total_cnt() {
		return week_total_cnt;
	}
	public void setWeek_total_cnt(int week_total_cnt) {
		this.week_total_cnt = week_total_cnt;
	}
	public int getWeek_1_cnt() {
		return week_1_cnt;
	}
	public void setWeek_1_cnt(int week_1_cnt) {
		this.week_1_cnt = week_1_cnt;
	}
	public int getWeek_2_cnt() {
		return week_2_cnt;
	}
	public void setWeek_2_cnt(int week_2_cnt) {
		this.week_2_cnt = week_2_cnt;
	}
	public int getWeek_3_cnt() {
		return week_3_cnt;
	}
	public void setWeek_3_cnt(int week_3_cnt) {
		this.week_3_cnt = week_3_cnt;
	}
	public int getWeek_4_cnt() {
		return week_4_cnt;
	}
	public void setWeek_4_cnt(int week_4_cnt) {
		this.week_4_cnt = week_4_cnt;
	}
	public int getWeek_5_cnt() {
		return week_5_cnt;
	}
	public void setWeek_5_cnt(int week_5_cnt) {
		this.week_5_cnt = week_5_cnt;
	}
	public int getWeek_6_cnt() {
		return week_6_cnt;
	}
	public void setWeek_6_cnt(int week_6_cnt) {
		this.week_6_cnt = week_6_cnt;
	}
	public int getWeek_7_cnt() {
		return week_7_cnt;
	}
	public void setWeek_7_cnt(int week_7_cnt) {
		this.week_7_cnt = week_7_cnt;
	}
	public int getTime_total_cnt() {
		return time_total_cnt;
	}
	public void setTime_total_cnt(int time_total_cnt) {
		this.time_total_cnt = time_total_cnt;
	}
	public int getTime_a_cnt() {
		return time_a_cnt;
	}
	public void setTime_a_cnt(int time_a_cnt) {
		this.time_a_cnt = time_a_cnt;
	}
	public int getTime_b_cnt() {
		return time_b_cnt;
	}
	public void setTime_b_cnt(int time_b_cnt) {
		this.time_b_cnt = time_b_cnt;
	}
	public int getTime_c_cnt() {
		return time_c_cnt;
	}
	public void setTime_c_cnt(int time_c_cnt) {
		this.time_c_cnt = time_c_cnt;
	}
	public int getTime_d_cnt() {
		return time_d_cnt;
	}
	public void setTime_d_cnt(int time_d_cnt) {
		this.time_d_cnt = time_d_cnt;
	}
	public int getTime_e_cnt() {
		return time_e_cnt;
	}
	public void setTime_e_cnt(int time_e_cnt) {
		this.time_e_cnt = time_e_cnt;
	}
	public int getTime_f_cnt() {
		return time_f_cnt;
	}
	public void setTime_f_cnt(int time_f_cnt) {
		this.time_f_cnt = time_f_cnt;
	}
	public int getTime_g_cnt() {
		return time_g_cnt;
	}
	public void setTime_g_cnt(int time_g_cnt) {
		this.time_g_cnt = time_g_cnt;
	}
	/**
	 * @return the fk_member_id
	 */
	public String getFk_member_id() {
		return fk_member_id;
	}
	/**
	 * @param fk_member_id the fk_member_id to set
	 */
	public void setFk_member_id(String fk_member_id) {
		this.fk_member_id = fk_member_id;
	}
	/**
	 * @return the pk_customer_tel
	 */
	public String getPk_customer_tel() {
		return pk_customer_tel;
	}
	/**
	 * @param pk_customer_tel the pk_customer_tel to set
	 */
	public void setPk_customer_tel(String pk_customer_tel) {
		this.pk_customer_tel = pk_customer_tel;
	}
	/**
	 * @return the fd_view_flag
	 */
	public String getFd_view_flag() {
		return fd_view_flag;
	}
	/**
	 * @param fd_view_flag the fd_view_flag to set
	 */
	public void setFd_view_flag(String fd_view_flag) {
		this.fd_view_flag = fd_view_flag;
	}
	/**
	 * @return the fd_user_name
	 */
	public String getFd_user_name() {
		return fd_user_name;
	}
	/**
	 * @param fd_user_name the fd_user_name to set
	 */
	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}
	/**
	 * @return the fd_rev_sms_flag
	 */
	public String getFd_rev_sms_flag() {
		return fd_rev_sms_flag;
	}
	/**
	 * @param fd_rev_sms_flag the fd_rev_sms_flag to set
	 */
	public void setFd_rev_sms_flag(String fd_rev_sms_flag) {
		this.fd_rev_sms_flag = fd_rev_sms_flag;
	}
	/**
	 * @return the fd_new_date
	 */
	public String getFd_new_date() {
		return fd_new_date;
	}
	/**
	 * @param fd_new_date the fd_new_date to set
	 */
	public void setFd_new_date(String fd_new_date) {
		this.fd_new_date = fd_new_date;
	}
	/**
	 * @return the fd_mod_date
	 */
	public String getFd_mod_date() {
		return fd_mod_date;
	}
	/**
	 * @param fd_mod_date the fd_mod_date to set
	 */
	public void setFd_mod_date(String fd_mod_date) {
		this.fd_mod_date = fd_mod_date;
	}
	/**
	 * @return the fd_memo
	 */
	public String getFd_memo() {
		return fd_memo;
	}
	/**
	 * @param fd_memo the fd_memo to set
	 */
	public void setFd_memo(String fd_memo) {
		this.fd_memo = fd_memo;
	}
	/**
	 * @return the fd_addr
	 */
	public String getFd_addr() {
		return fd_addr;
	}
	/**
	 * @param fd_addr the fd_addr to set
	 */
	public void setFd_addr(String fd_addr) {
		this.fd_addr = fd_addr;
	}
	/**
	 * @return the fd_last_date
	 */
	public String getFd_last_date() {
		return fd_last_date;
	}
	/**
	 * @param fd_last_date the fd_last_date to set
	 */
	public void setFd_last_date(String fd_last_date) {
		this.fd_last_date = fd_last_date;
	}
	/**
	 * @return the fd_call_cnt
	 */
	public String getFd_call_cnt() {
		return fd_call_cnt;
	}
	/**
	 * @param fd_call_cnt the fd_call_cnt to set
	 */
	public void setFd_call_cnt(String fd_call_cnt) {
		this.fd_call_cnt = fd_call_cnt;
	}
	/**
	 * @return the fd_sms_cnt
	 */
	public String getFd_sms_cnt() {
		return fd_sms_cnt;
	}
	/**
	 * @param fd_sms_cnt the fd_sms_cnt to set
	 */
	public void setFd_sms_cnt(String fd_sms_cnt) {
		this.fd_sms_cnt = fd_sms_cnt;
	}
	
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
}
