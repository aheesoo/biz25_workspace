package com.includesys.sm.dto.smsManager;


public class SmsManager {
	
	/*tbl_sms_group, tbl_sms_reservation  공통*/
	private String fk_member_id;				/*로그인아이디*/
	private String fk_tel;						/*회신번호(보내는번호)*/
	private String group_code;				/*그룹코드*/
	private String fk_custom_msg_id;			/*메세지아이디*/
	private String msg_type;					/*메시지 요청 타입 sms(1:subtype=1) lms(4:subtype=1) mms (4:subtype=2~4)*/
	private String msg_sub_type;			/*메시지 세부유형 1:text 2:url 3:이미지 4:오디오'*/
	private String res_result;					/*결과코드 0:성공*/
	private String reg_date;					/*등록일*/
	private String mcs_group_id;				/*MCS 연동규격 그룹아이디*/
	private String send_subject;				/*메세지 제목*/
	private String send_content;				/*메세지 내용*/
	private String attachment_path;		/*업로드 파일 경로*/
	private String attachment_file_size;	/*업로드 파일 사이즈*/
	private String cancel_yn;					/*발송 취소 여부 기본값:N 취소시:Y*/
	private String cancel_time;				/*발송 취소 시간*/
	private String reserve_type;				/*예약구분(1:즉시, 2:예약)*/
	private String modify_date;				/*수정일(년월일시분초)*/
	
	/*tbl_sms_group*/
	private String req_count;							/*동보 요청 건수*/
	private String res_count;							/*전송 요청한 수신건수*/
	private String mcs_res_result_success;		/*MCS 응답 성공건수*/
	private String mcs_res_result_fail;				/*MCS 응답 실패 건수*/
	private String msg_send_finish;					/*전송완료여부 기본값:N 전송완료:Y*/
	private String reserve_time;						/*발송요청시간(년월일시분*/
	private String search_month;					/*추출조건(월)*/
	private String search_customer;				/*추출조건 고객타입(1:단골고객 2:신규고객*/
	private String search_week;						/*추출조건(일요일~토요일 1~7)*/
	private String search_time;						/*동보전송 추출조건 A~G (새벽~야간) 구분자:^*/
	private String search_call_rcv_cnt;			/*추출조건 수신건수*/
	private String search_call_rcv_cnt_type;		/*추출조건 수신타입(-1:이하 1:이상)*/
	private String search_req_cnt;					/*발송 요청 건수(원하는건수)*/
	private String req_member_type;				/*등록자 구분 [웹회원:M 상담사:C]*/
	private String counsel_id_regist;				/*발송등록 상담사ID*/
	private String counsel_id_cancel;				/*발송취소 상담사ID*/
	private String member_ip;							/*웹회원IP*/
	private String counsel_ip;							/*상담사IP*/
	private String counsel_title;						/*상담내역 제목*/
	private String counsel_content;					/*상담내역 내용*/

	
	/*tbl_sms_reservation*/
	private String pk_seq;									/*일련번호*/
	private String receive_number;					/*수신번호*/
	private String cdr_id;								/*과금 지정아이디(발급아이디)*/
	private String reserve_date;						/*발송 예약 일시*/
	private String mcs_req_date;					/*MCS로 발송요청 시간*/
	private String mcs_res_date;						/*MCS에서 응답 시간*/
	private String job_id;								/*MCS 트랜잭션 일련번호*/
	private String mcs_attachment_path;			/*MCS MMS파일 업로드 실제 경로*/
	private String mcs_attachment_req_result;	/*MCS 파일 업로드 요청 결과값*/
	private String mcs_attachment_res_result;	/*MCS 파일 업로드 응답 결과값*/
	
	/*고객 추출*/
	private String extraction_year;		//년
	private String extraction_month;	//월
	private String extraction_day1;		//일요일
	private String extraction_day2;		//월요일
	private String extraction_day3;		//화요일
	private String extraction_day4;		//수요일
	private String extraction_day5;		//목요일
	private String extraction_day6;		//금요일
	private String extraction_day7;		//토요일
	private String extraction_time1;		//새벽(00~07)
	private String extraction_time2;		//아침(07~09)
	private String extraction_time3;		//오전(09~11)
	private String extraction_time4;		//점심(11~14)
	private String extraction_time5;		//오후(14~17)
	private String extraction_time6;		//저녁(17~21)
	private String extraction_time7;		//야간(21~24)
	
	/*Coin 정보*/
	private String use_coin;
	private String fd_base_coin;			/*기본사용코인*/
	private String fd_recharge_coin;	/*충전사용코인*/
	private String fd_bonus_coin;		/*보너스사용코인*/
	private String fd_total_coin;			/*총사용코인*/
	
	/*문자전송 리스트 체크된 custom_msg_id*/
	private String[] group_code_arry;
	
	private int pageStart;
	private int pageSize;
	
	/*리포트 응답건수*/
	private String call_count;
	
	/*리포트 수신자 정보*/
	private String call_date;
	private String fd_user_name;
	private String pk_customer_tel;

	public String getFk_member_id() {
		return fk_member_id;
	}

	public void setFk_member_id(String fk_member_id) {
		this.fk_member_id = fk_member_id;
	}

	public String getFk_tel() {
		return fk_tel;
	}

	public void setFk_tel(String fk_tel) {
		this.fk_tel = fk_tel;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getFk_custom_msg_id() {
		return fk_custom_msg_id;
	}

	public void setFk_custom_msg_id(String fk_custom_msg_id) {
		this.fk_custom_msg_id = fk_custom_msg_id;
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

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getMcs_group_id() {
		return mcs_group_id;
	}

	public void setMcs_group_id(String mcs_group_id) {
		this.mcs_group_id = mcs_group_id;
	}

	public String getSend_subject() {
		return send_subject;
	}

	public void setSend_subject(String send_subject) {
		this.send_subject = send_subject;
	}

	public String getSend_content() {
		return send_content;
	}

	public void setSend_content(String send_content) {
		this.send_content = send_content;
	}

	public String getAttachment_path() {
		return attachment_path;
	}

	public void setAttachment_path(String attachment_path) {
		this.attachment_path = attachment_path;
	}

	public String getAttachment_file_size() {
		return attachment_file_size;
	}

	public void setAttachment_file_size(String attachment_file_size) {
		this.attachment_file_size = attachment_file_size;
	}

	public String getCancel_yn() {
		return cancel_yn;
	}

	public void setCancel_yn(String cancel_yn) {
		this.cancel_yn = cancel_yn;
	}

	public String getCancel_time() {
		return cancel_time;
	}

	public void setCancel_time(String cancel_time) {
		this.cancel_time = cancel_time;
	}

	public String getReserve_type() {
		return reserve_type;
	}

	public void setReserve_type(String reserve_type) {
		this.reserve_type = reserve_type;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}

	public String getReq_count() {
		return req_count;
	}

	public void setReq_count(String req_count) {
		this.req_count = req_count;
	}

	public String getRes_count() {
		return res_count;
	}

	public void setRes_count(String res_count) {
		this.res_count = res_count;
	}

	public String getMcs_res_result_success() {
		return mcs_res_result_success;
	}

	public void setMcs_res_result_success(String mcs_res_result_success) {
		this.mcs_res_result_success = mcs_res_result_success;
	}

	public String getMcs_res_result_fail() {
		return mcs_res_result_fail;
	}

	public void setMcs_res_result_fail(String mcs_res_result_fail) {
		this.mcs_res_result_fail = mcs_res_result_fail;
	}

	public String getMsg_send_finish() {
		return msg_send_finish;
	}

	public void setMsg_send_finish(String msg_send_finish) {
		this.msg_send_finish = msg_send_finish;
	}

	public String getReserve_time() {
		return reserve_time;
	}

	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}

	public String getSearch_month() {
		return search_month;
	}

	public void setSearch_month(String search_month) {
		this.search_month = search_month;
	}

	public String getSearch_customer() {
		return search_customer;
	}

	public void setSearch_customer(String search_customer) {
		this.search_customer = search_customer;
	}

	public String getSearch_week() {
		return search_week;
	}

	public void setSearch_week(String search_week) {
		this.search_week = search_week;
	}

	public String getSearch_time() {
		return search_time;
	}

	public void setSearch_time(String search_time) {
		this.search_time = search_time;
	}

	public String getSearch_call_rcv_cnt() {
		return search_call_rcv_cnt;
	}

	public void setSearch_call_rcv_cnt(String search_call_rcv_cnt) {
		this.search_call_rcv_cnt = search_call_rcv_cnt;
	}

	public String getSearch_call_rcv_cnt_type() {
		return search_call_rcv_cnt_type;
	}

	public void setSearch_call_rcv_cnt_type(String search_call_rcv_cnt_type) {
		this.search_call_rcv_cnt_type = search_call_rcv_cnt_type;
	}

	public String getSearch_req_cnt() {
		return search_req_cnt;
	}

	public void setSearch_req_cnt(String search_req_cnt) {
		this.search_req_cnt = search_req_cnt;
	}

	public String getReq_member_type() {
		return req_member_type;
	}

	public void setReq_member_type(String req_member_type) {
		this.req_member_type = req_member_type;
	}

	public String getCounsel_id_regist() {
		return counsel_id_regist;
	}

	public void setCounsel_id_regist(String counsel_id_regist) {
		this.counsel_id_regist = counsel_id_regist;
	}

	public String getCounsel_id_cancel() {
		return counsel_id_cancel;
	}

	public void setCounsel_id_cancel(String counsel_id_cancel) {
		this.counsel_id_cancel = counsel_id_cancel;
	}

	public String getMember_ip() {
		return member_ip;
	}

	public void setMember_ip(String member_ip) {
		this.member_ip = member_ip;
	}

	public String getCounsel_ip() {
		return counsel_ip;
	}

	public void setCounsel_ip(String counsel_ip) {
		this.counsel_ip = counsel_ip;
	}

	public String getCounsel_title() {
		return counsel_title;
	}

	public void setCounsel_title(String counsel_title) {
		this.counsel_title = counsel_title;
	}

	public String getCounsel_content() {
		return counsel_content;
	}

	public void setCounsel_content(String counsel_content) {
		this.counsel_content = counsel_content;
	}

	public String getPk_seq() {
		return pk_seq;
	}

	public void setPk_seq(String pk_seq) {
		this.pk_seq = pk_seq;
	}

	public String getReceive_number() {
		return receive_number;
	}

	public void setReceive_number(String receive_number) {
		this.receive_number = receive_number;
	}

	public String getCdr_id() {
		return cdr_id;
	}

	public void setCdr_id(String cdr_id) {
		this.cdr_id = cdr_id;
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	public String getMcs_req_date() {
		return mcs_req_date;
	}

	public void setMcs_req_date(String mcs_req_date) {
		this.mcs_req_date = mcs_req_date;
	}

	public String getMcs_res_date() {
		return mcs_res_date;
	}

	public void setMcs_res_date(String mcs_res_date) {
		this.mcs_res_date = mcs_res_date;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getMcs_attachment_path() {
		return mcs_attachment_path;
	}

	public void setMcs_attachment_path(String mcs_attachment_path) {
		this.mcs_attachment_path = mcs_attachment_path;
	}

	public String getMcs_attachment_req_result() {
		return mcs_attachment_req_result;
	}

	public void setMcs_attachment_req_result(String mcs_attachment_req_result) {
		this.mcs_attachment_req_result = mcs_attachment_req_result;
	}

	public String getMcs_attachment_res_result() {
		return mcs_attachment_res_result;
	}

	public void setMcs_attachment_res_result(String mcs_attachment_res_result) {
		this.mcs_attachment_res_result = mcs_attachment_res_result;
	}

	public String getExtraction_year() {
		return extraction_year;
	}

	public void setExtraction_year(String extraction_year) {
		this.extraction_year = extraction_year;
	}

	public String getExtraction_month() {
		return extraction_month;
	}

	public void setExtraction_month(String extraction_month) {
		this.extraction_month = extraction_month;
	}

	public String getExtraction_day1() {
		return extraction_day1;
	}

	public void setExtraction_day1(String extraction_day1) {
		this.extraction_day1 = extraction_day1;
	}

	public String getExtraction_day2() {
		return extraction_day2;
	}

	public void setExtraction_day2(String extraction_day2) {
		this.extraction_day2 = extraction_day2;
	}

	public String getExtraction_day3() {
		return extraction_day3;
	}

	public void setExtraction_day3(String extraction_day3) {
		this.extraction_day3 = extraction_day3;
	}

	public String getExtraction_day4() {
		return extraction_day4;
	}

	public void setExtraction_day4(String extraction_day4) {
		this.extraction_day4 = extraction_day4;
	}

	public String getExtraction_day5() {
		return extraction_day5;
	}

	public void setExtraction_day5(String extraction_day5) {
		this.extraction_day5 = extraction_day5;
	}

	public String getExtraction_day6() {
		return extraction_day6;
	}

	public void setExtraction_day6(String extraction_day6) {
		this.extraction_day6 = extraction_day6;
	}

	public String getExtraction_day7() {
		return extraction_day7;
	}

	public void setExtraction_day7(String extraction_day7) {
		this.extraction_day7 = extraction_day7;
	}

	public String getExtraction_time1() {
		return extraction_time1;
	}

	public void setExtraction_time1(String extraction_time1) {
		this.extraction_time1 = extraction_time1;
	}

	public String getExtraction_time2() {
		return extraction_time2;
	}

	public void setExtraction_time2(String extraction_time2) {
		this.extraction_time2 = extraction_time2;
	}

	public String getExtraction_time3() {
		return extraction_time3;
	}

	public void setExtraction_time3(String extraction_time3) {
		this.extraction_time3 = extraction_time3;
	}

	public String getExtraction_time4() {
		return extraction_time4;
	}

	public void setExtraction_time4(String extraction_time4) {
		this.extraction_time4 = extraction_time4;
	}

	public String getExtraction_time5() {
		return extraction_time5;
	}

	public void setExtraction_time5(String extraction_time5) {
		this.extraction_time5 = extraction_time5;
	}

	public String getExtraction_time6() {
		return extraction_time6;
	}

	public void setExtraction_time6(String extraction_time6) {
		this.extraction_time6 = extraction_time6;
	}

	public String getExtraction_time7() {
		return extraction_time7;
	}

	public void setExtraction_time7(String extraction_time7) {
		this.extraction_time7 = extraction_time7;
	}

	public String getUse_coin() {
		return use_coin;
	}

	public void setUse_coin(String use_coin) {
		this.use_coin = use_coin;
	}

	public String getFd_base_coin() {
		return fd_base_coin;
	}

	public void setFd_base_coin(String fd_base_coin) {
		this.fd_base_coin = fd_base_coin;
	}

	public String getFd_recharge_coin() {
		return fd_recharge_coin;
	}

	public void setFd_recharge_coin(String fd_recharge_coin) {
		this.fd_recharge_coin = fd_recharge_coin;
	}

	public String getFd_bonus_coin() {
		return fd_bonus_coin;
	}

	public void setFd_bonus_coin(String fd_bonus_coin) {
		this.fd_bonus_coin = fd_bonus_coin;
	}

	public String getFd_total_coin() {
		return fd_total_coin;
	}

	public void setFd_total_coin(String fd_total_coin) {
		this.fd_total_coin = fd_total_coin;
	}

	public String[] getGroup_code_arry() {
		return group_code_arry;
	}
	
	public void setGroup_code_arry(String[] group_code_arry) {
		this.group_code_arry = group_code_arry;
	}
	
	public int getPageStart() {
		return pageStart;
	}
	
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getCall_count() {
		return call_count;
	}
	public void setCall_count(String call_count) {
		this.call_count = call_count;
	}
	
	public String getCall_date() {
		return call_date;
	}

	public void setCall_date(String call_date) {
		this.call_date = call_date;
	}

	public String getFd_user_name() {
		return fd_user_name;
	}

	public void setFd_user_name(String fd_user_name) {
		this.fd_user_name = fd_user_name;
	}

	public String getPk_customer_tel() {
		return pk_customer_tel;
	}

	public void setPk_customer_tel(String pk_customer_tel) {
		this.pk_customer_tel = pk_customer_tel;
	}
	
}
