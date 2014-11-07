package com.includesys.sm.service.smsManager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.includesys.sm.dao.smsManager.SmsManagerDao;
import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.smsManager.BoardEvent;
import com.includesys.sm.dto.smsManager.SmsManager;

@Service
public class SmsManagerServiceImpl  implements SmsManagerService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(SmsManagerServiceImpl.class);
	
	@Autowired
	private SmsManagerDao smsManagerDao;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Override
	public List<SmsManager> getSmsCustomerTelList(Map<String, Object> map){
		return smsManagerDao.getSmsCustomerTelList(map);
	}
	
	@Override
	public int getCallCount(String fk_group_code){
		return smsManagerDao.getCallCount(fk_group_code);
	}
	
	@Override
	public String getUnvailDay(Map<String, Object> map) {
		return smsManagerDao.getUnvailDay(map);
	}
	
	@Override
	public String getSendLimit(String pk_member_id) {
		return smsManagerDao.getSendLimit(pk_member_id);
	}
	
	@Override
	public List<Map<String, Object>> getProductTel(String member_id) {
		return smsManagerDao.getProductTel(member_id);
	}
	
	@Override
	public Coin getCoin(String member_id) {
		return smsManagerDao.getCoin(member_id);
	}
	
	@Override
	public int getCustomerCount(SmsManager smsManager) {
		return smsManagerDao.getCustomerCount(smsManager);
	}
	
	@Override
	public List<SmsManager> getCustomerTelList(SmsManager smsManager) {
		return smsManagerDao.getCustomerTelList(smsManager);
	}
	
	@Override
	public SmsManager getSmsManager(String pk_group_code) {
		return smsManagerDao.getSmsManager(pk_group_code);
	}
	
	@Override
	public String registerSms(SmsManager smsManager) throws Exception{
		
		int send_count = 0, result = 0, send_cout_div;
		int use_coin = 0;
		int org_use_coin = 0;
		boolean skip_process = true;
		String result_str = "";
		String group_code_temp = "";
		
		List<SmsManager> customer_tel_list = null;
		Coin coin = new Coin();
		//Coin coin_small = new Coin();
		Map <String, Object> coin_map = new HashMap<String, Object>();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		
		def.setName("registerSms-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			//대상조회된 사용자 연락처를 갖고옴.
			customer_tel_list = smsManagerDao.getCustomerTelList(smsManager);
			
			//사용자 코인정보를 갖고옴.
			coin = smsManagerDao.getCoin(smsManager.getFk_member_id());
			/*coin_small = smsManagerDao.getCoin(smsManager.getFk_member_id());
			System.out.println("1 coin_small.getFd_base_coin() : " + coin_small.getFd_base_coin());
			System.out.println("1 coin_small.getFd_bonus_coin() : " + coin_small.getFd_bonus_coin());
			System.out.println("1 coin_small.getFd_recharge_coin() : " + coin_small.getFd_recharge_coin());
			System.out.println("1 coin_small.getFd_total_coin() : " + coin_small.getFd_total_coin());*/
		} catch (Exception e) {
			// TODO: handle exception
			result_str = "304";
			skip_process = false;
			e.printStackTrace();
		}
		
		if(skip_process) {
			try {
				send_count = Integer.parseInt(smsManager.getReq_count());
				
				//요청 발송수가 300건이 넘을때
				if(send_count > 300){
					send_cout_div = send_count/300; //ex)650건일 경우 "2"
					
					//tbl_sms_group 300 건씩 나눠서 보낸다.
					for(int i=0 ; i<send_cout_div ; i++){
						group_code_temp = Long.toString(System.nanoTime());
						smsManager.setGroup_code(group_code_temp);
						smsManager.setFk_custom_msg_id(smsManager.getFk_member_id() + "_" + group_code_temp);
						smsManager.setReq_count("300");
						result = smsManagerDao.registerSmsGroup(smsManager);
						
						//group 처리가 정상적으로 되었을 때
						if(result > 0){
							//tbl_sms_reservation 1그룹에 reservation 300건
							for(int k=0 ; k<300 ; k++){
								
								//고객사 연락처를 넣는다.
								smsManager.setReceive_number(customer_tel_list.get(300*i+k).getReceive_number());
								
								result = smsManagerDao.registerSmsReservation(smsManager);
							}
							
							//reservation 처리가 정상적으로 되었을때
							if(result > 0){
								result_str = "200";
							}else{
								result_str = "302";
								break;
							}
						}else{
							result_str = "301";
							break;
						}
						
						//사용 로그 코인 insert
						if(result_str.equals("200")){
							//use_coin = Integer.parseInt(smsManager.getUse_coin());
							coin_map.clear();
							switch(smsManager.getMsg_type()){
								case "1": 	org_use_coin = use_coin = 300*2;break;
								case "4": 	if(smsManager.getMsg_sub_type().equals("1")){
													org_use_coin = use_coin = 300*5;
												}else{
													org_use_coin = use_coin = 300*20;
												}
												break;
								default :
							}
							coin_map.put("fk_member_id", smsManager.getFk_member_id());
							//기본 코인이 사용코인보다 적을 경우
							if(coin.getFd_base_coin()  < use_coin){
								use_coin = use_coin - coin.getFd_base_coin();
								
								coin_map.put("log_fd_base_coin", coin.getFd_base_coin());
								coin.setFd_total_coin(coin.getFd_total_coin() - coin.getFd_base_coin());
								coin.setFd_base_coin(0);
								//보너스 코인이 사용코인보다 적을 경우
								if(coin.getFd_bonus_coin() < use_coin){
									use_coin = use_coin - coin.getFd_bonus_coin();
									
									coin_map.put("log_fd_bonus_coin", coin.getFd_bonus_coin());
									coin_map.put("log_fd_recharge_coin", use_coin);
									coin_map.put("log_fd_total_coin", org_use_coin);
									coin.setFd_recharge_coin(coin.getFd_recharge_coin() - use_coin);
									coin.setFd_total_coin(coin.getFd_total_coin() - coin.getFd_bonus_coin() - use_coin);
									coin.setFd_bonus_coin(0);
								}else{
									coin.setFd_bonus_coin(coin.getFd_bonus_coin() - use_coin);
									coin.setFd_total_coin(coin.getFd_total_coin() - use_coin);
									
									coin_map.put("log_fd_bonus_coin", use_coin);
									coin_map.put("log_fd_recharge_coin", 0);
									coin_map.put("log_fd_total_coin", org_use_coin);
								}
							}else{
								coin.setFd_base_coin(coin.getFd_base_coin() - org_use_coin);
								coin.setFd_total_coin(coin.getFd_total_coin() - org_use_coin);
								
								coin_map.put("log_fd_base_coin", org_use_coin);
								coin_map.put("log_fd_recharge_coin", 0);
								coin_map.put("log_fd_bonus_coin", 0);
								coin_map.put("log_fd_total_coin", org_use_coin);
							}
							coin_map.put("fd_tel", smsManager.getFk_tel());
							coin_map.put("fd_group_code", smsManager.getGroup_code());
							coin_map.put("fd_reg_date", smsManager.getReg_date());
							
							result = smsManagerDao.registerCoinUseLog(coin_map);
							if(result > 0){
								result_str = "200";
							}else{
								result_str = "303";
							}
						}
						
						
					}
					
					//나머지를 구하여 group/reservation을 넣는다.
					if((300*send_cout_div) < send_count){
						group_code_temp = Long.toString(System.nanoTime());
						smsManager.setGroup_code(group_code_temp);
						smsManager.setFk_custom_msg_id(smsManager.getFk_member_id() + "_" + group_code_temp);
						smsManager.setReq_count(Integer.toString(send_count-(300*send_cout_div)));
						result = smsManagerDao.registerSmsGroup(smsManager);
						
						if(result > 0){
							for(int k=0 ; k<send_count-(300*send_cout_div) ; k++){
								smsManager.setReceive_number(customer_tel_list.get(300*send_cout_div+k).getReceive_number());
								result = smsManagerDao.registerSmsReservation(smsManager);
								if(result > 0){
									result_str = "200";
								}else{
									result_str = "302";
									break;
								}
							}
						}else{
							result_str = "301";
						}
						
						//사용 로그 코인 insert
						if(result_str.equals("200")){
							coin_map.clear();
							coin_map.put("fk_member_id", smsManager.getFk_member_id());
							switch(smsManager.getMsg_type()){
								case "1": 	org_use_coin = use_coin = (send_count-300*send_cout_div)*2; break;
								case "4": 	if(smsManager.getMsg_sub_type().equals("1")){
													org_use_coin = use_coin = (send_count-300*send_cout_div)*5;
												}else{
													org_use_coin = use_coin = (send_count-300*send_cout_div)*20;
												}
												break;
								default :
							}
							//use_coin = Integer.parseInt(smsManager.getUse_coin());
							//기본 코인이 사용코인보다 적을 경우
							if(coin.getFd_base_coin()  < use_coin){
								use_coin = use_coin - coin.getFd_base_coin();
								
								coin_map.put("log_fd_base_coin", coin.getFd_base_coin());
								coin.setFd_total_coin(coin.getFd_total_coin() - coin.getFd_base_coin());
								coin.setFd_base_coin(0);
								//보너스 코인이 사용코인보다 적을 경우
								if(coin.getFd_bonus_coin() < use_coin){
									use_coin = use_coin - coin.getFd_bonus_coin();
									
									coin_map.put("log_fd_bonus_coin", coin.getFd_bonus_coin());
									coin_map.put("log_fd_recharge_coin", use_coin);
									coin_map.put("log_fd_total_coin", org_use_coin);
									coin.setFd_recharge_coin(coin.getFd_recharge_coin() - use_coin);
									coin.setFd_total_coin(coin.getFd_total_coin() - coin.getFd_bonus_coin() - use_coin);
									coin.setFd_bonus_coin(0);
								}else{
									coin.setFd_bonus_coin(coin.getFd_bonus_coin() - use_coin);
									coin.setFd_total_coin(coin.getFd_total_coin() - use_coin);
									
									coin_map.put("log_fd_bonus_coin", use_coin);
									coin_map.put("log_fd_recharge_coin", 0);
									coin_map.put("log_fd_total_coin", org_use_coin);
								}
							}else{
								coin.setFd_base_coin(coin.getFd_base_coin() - org_use_coin);
								coin.setFd_total_coin(coin.getFd_total_coin() - org_use_coin);
								
								coin_map.put("log_fd_base_coin", org_use_coin);
								coin_map.put("log_fd_recharge_coin", 0);
								coin_map.put("log_fd_bonus_coin", 0);
								coin_map.put("log_fd_total_coin", org_use_coin);
							}
							coin_map.put("fd_tel", smsManager.getFk_tel());
							coin_map.put("fd_group_code", smsManager.getGroup_code());
							coin_map.put("fd_reg_date", smsManager.getReg_date());
							
							result = smsManagerDao.registerCoinUseLog(coin_map);
							if(result > 0){
								result_str = "200";
							}else{
								result_str = "303";
							}
						}
					}
					
					coin_map.put("fd_base_coin", coin.getFd_base_coin());
					coin_map.put("fd_recharge_coin", coin.getFd_recharge_coin());
					coin_map.put("fd_bonus_coin", coin.getFd_bonus_coin());
					coin_map.put("fd_total_coin", coin.getFd_total_coin());
					result = smsManagerDao.modifyCoin(coin_map);
					if(result > 0){
						result_str = "200";
					}else{
						result_str = "303";
					}
					
				}else{
					//300건 이하일때
					
					//tbl_sms_group
					result = smsManagerDao.registerSmsGroup(smsManager);
					if(result > 0){
						//tbl_sms_reservation
						//System.out.println("send_count : " + send_count);
						//System.out.println("customer_tel_list.size() : " + customer_tel_list.size());
						for(int k=0 ; k<send_count ; k++){
							//System.out.println("customer_tel_list.get(" + k + ").getReceive_number() = " + customer_tel_list.get(k).getReceive_number());
							smsManager.setReceive_number(customer_tel_list.get(k).getReceive_number());
							result = smsManagerDao.registerSmsReservation(smsManager);
							if(result > 0){
								result_str = "200";
							}else{
								result_str = "302";
								break;
							}
						}
						
					}else {
						result_str = "301";
					}
					
					//사용 코인 insert
					coin_map.clear();
					if(result_str.equals("200")){
						use_coin = Integer.parseInt(smsManager.getUse_coin());
						coin_map.put("fk_member_id", smsManager.getFk_member_id());
						//기본 코인이 사용코인보다 적을 경우
						if(coin.getFd_base_coin()  < use_coin){
							use_coin = use_coin - coin.getFd_base_coin();
							coin_map.put("fd_base_coin", 0);
							
							coin_map.put("log_fd_base_coin", coin.getFd_base_coin());
							//보너스 코인이 사용코인보다 적을 경우
							if(coin.getFd_bonus_coin() < use_coin){
								use_coin = use_coin - coin.getFd_bonus_coin();
								coin_map.put("fd_bonus_coin", 0);
								coin_map.put("fd_recharge_coin", coin.getFd_recharge_coin() - use_coin);
								coin_map.put("fd_total_coin", coin.getFd_total_coin() - Integer.parseInt(smsManager.getUse_coin()));
								
								coin_map.put("log_fd_bonus_coin", coin.getFd_bonus_coin());
								coin_map.put("log_fd_recharge_coin", use_coin);
								coin_map.put("log_fd_total_coin", Integer.parseInt(smsManager.getUse_coin()));
							}else{
								coin_map.put("fd_bonus_coin", coin.getFd_bonus_coin() - use_coin);
								coin_map.put("fd_recharge_coin", coin.getFd_recharge_coin());
								coin_map.put("fd_total_coin", coin.getFd_total_coin() - Integer.parseInt(smsManager.getUse_coin()));
								
								coin_map.put("log_fd_bonus_coin", use_coin);
								coin_map.put("log_fd_recharge_coin", 0);
								coin_map.put("log_fd_total_coin", Integer.parseInt(smsManager.getUse_coin()));
							}
						}else{
							coin_map.put("fd_base_coin", coin.getFd_base_coin() - use_coin);
							coin_map.put("fd_recharge_coin", coin.getFd_recharge_coin());
							coin_map.put("fd_bonus_coin", coin.getFd_bonus_coin());
							coin_map.put("fd_total_coin", coin.getFd_total_coin() -  Integer.parseInt(smsManager.getUse_coin()));
							
							coin_map.put("log_fd_base_coin", Integer.parseInt(smsManager.getUse_coin()));
							coin_map.put("log_fd_recharge_coin", 0);
							coin_map.put("log_fd_bonus_coin", 0);
							coin_map.put("log_fd_total_coin", Integer.parseInt(smsManager.getUse_coin()));
						}
						result = smsManagerDao.modifyCoin(coin_map);
						if(result > 0){
							coin_map.put("fd_tel", smsManager.getFk_tel());
							coin_map.put("fd_group_code", smsManager.getGroup_code());
							coin_map.put("fd_reg_date", smsManager.getReg_date());
							
							result = smsManagerDao.registerCoinUseLog(coin_map);
							if(result > 0){
								result_str = "200";
							}else{
								result_str = "303";
							}
						}else{
							result_str = "303";
						}
					}
				}
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				transactionManager.rollback(status);
				throw e;
			}
			
			if(result_str.equals("200")){
				
				transactionManager.commit(status);
			}else{
				transactionManager.rollback(status);
			}
		}
		
		return result_str;
	}
	
	@Override
	public String modifySms(SmsManager smsManager) throws Exception{
		String result_str = "301";
		Coin use_log_coin = new Coin();
		Coin now_coin = new Coin();
		int fd_base_coin = 0;
		int fd_recharge_coin = 0;
		int fd_bonus_coin = 0;
		int fd_total_coin = 0;
		int use_coin = 0;
		Map<String, Object> coin_map = new HashMap<String, Object>();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		
		def.setName("modifySms-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			System.out.println("modify 1");
			if(smsManagerDao.modifySmsGroup(smsManager) > 0){
				System.out.println("modify 2");
				if(smsManagerDao.modifySmsReservation(smsManager) > 0){
					System.out.println("modify 3");
					use_log_coin = smsManagerDao.getCoinUseLog(smsManager);
					now_coin = smsManagerDao.getCoin(smsManager.getFk_member_id());
					
					fd_base_coin = now_coin.getFd_base_coin() + use_log_coin.getFd_base_coin();
					fd_recharge_coin = now_coin.getFd_recharge_coin() + use_log_coin.getFd_recharge_coin();
					fd_bonus_coin = now_coin.getFd_bonus_coin() + use_log_coin.getFd_bonus_coin();
					fd_total_coin = now_coin.getFd_total_coin() + use_log_coin.getFd_total_coin();
					use_coin = Integer.parseInt(smsManager.getUse_coin());
					System.out.println("use_coin : " + use_coin);
					
					if(fd_base_coin < use_coin){
						use_coin = use_coin - fd_base_coin;
						
						coin_map.put("fd_base_coin", 0);
						coin_map.put("log_fd_base_coin", fd_base_coin);
						if(fd_bonus_coin < use_coin){
							use_coin = use_coin - fd_bonus_coin;
							
							coin_map.put("fd_bonus_coin", 0);
							coin_map.put("fd_recharge_coin", fd_recharge_coin - use_coin);
							coin_map.put("fd_total_coin", fd_total_coin - Integer.parseInt(smsManager.getUse_coin()));
							
							coin_map.put("log_fd_bonus_coin", fd_bonus_coin);
							coin_map.put("log_fd_recharge_coin", use_coin);
							coin_map.put("log_fd_total_coin", Integer.parseInt(smsManager.getUse_coin()));
						}else{
							coin_map.put("fd_bonus_coin", fd_bonus_coin - use_coin);
							coin_map.put("fd_recharge_coin", fd_recharge_coin);
							coin_map.put("fd_total_coin", fd_total_coin - Integer.parseInt(smsManager.getUse_coin()));
							
							coin_map.put("log_fd_bonus_coin", use_coin);
							coin_map.put("log_fd_recharge_coin", 0);
							coin_map.put("log_fd_total_coin", Integer.parseInt(smsManager.getUse_coin()));
						}
					}else{
						System.out.println("modify 4");
						coin_map.put("fd_base_coin", fd_base_coin - use_coin);
						coin_map.put("fd_bonus_coin", fd_bonus_coin);
						coin_map.put("fd_recharge_coin", fd_recharge_coin);
						coin_map.put("fd_total_coin", fd_total_coin - use_coin);
						
						coin_map.put("log_fd_base_coin", use_coin);
						coin_map.put("log_fd_bonus_coin", 0);
						coin_map.put("log_fd_recharge_coin", 0);
						coin_map.put("log_fd_total_coin", use_coin);
					}
					
					System.out.println("modify 5");
					System.out.println("coin_map : " + coin_map);
					coin_map.put("fk_member_id", smsManager.getFk_member_id());
					coin_map.put("fd_group_code", smsManager.getGroup_code());
					
					if(smsManagerDao.modifyCoinUseLog(coin_map) > 0){
						System.out.println("modify 6");
						if(smsManagerDao.modifyCoin(coin_map) > 0){
							System.out.println("modify 7");
							result_str = "200";
						}
					}
					result_str = "200";
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transactionManager.rollback(status);
			result_str = "302";
		}
		
		if(result_str.equals("200")){
			transactionManager.commit(status);
		}else{
			if(!result_str.equals("302"))
				transactionManager.rollback(status);
		}
		
		return result_str;
	}

	@Override
	public int getCountCal(PageHelper pageHelper) {
		// TODO Auto-generated method stub
		return smsManagerDao.getCountCal(pageHelper);
	}

	@Override
	public List<BoardEvent> getListCal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return smsManagerDao.getListCal(map);
	}

	@Override
	public List<BoardEvent> getMemberTelList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return smsManagerDao.getMemberTelList(map);
	}

	@Override
	public List<BoardEvent> getSmsGroupList(Map<String, String> smsGroupMap) {
		// TODO Auto-generated method stub
		return smsManagerDao.getSmsGroupList(smsGroupMap);
	}
	
	@Override
	public List<BoardEvent> getSmsReserveGroupList(Map<String, String> smsGroupMap) {
		// TODO Auto-generated method stub
		return smsManagerDao.getSmsReserveGroupList(smsGroupMap);
	}
	
	
	@Override
	public String cancelationSms(SmsManager smsManager){
		// TODO Auto-generated method stub
		
		System.out.println("코인 취소 처리");
		
		String result_str = "301";
		Coin sum_use_coin = new Coin();
		Coin now_coin = new Coin();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("cancelationSms-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			
			if(smsManagerDao.cancelationSmsGroup(smsManager) > 0){ 				//SMS Group 테이블 취소 처리 (cancel - update)
				System.out.println("코인 취소 처리 if1");
				if(smsManagerDao.cancelationSmsReservation(smsManager) > 0){	//SMS 예약 테이블 취소처리
					System.out.println("코인 취소 처리 if2");
					sum_use_coin = smsManagerDao.getSumCancelUseCoin(smsManager);
					now_coin = smsManagerDao.getCoin(smsManager.getFk_member_id());
					
					smsManager.setFd_base_coin(Integer.toString(now_coin.getFd_base_coin() + sum_use_coin.getFd_base_coin()));
					smsManager.setFd_recharge_coin(Integer.toString(now_coin.getFd_recharge_coin() + sum_use_coin.getFd_recharge_coin()));
					smsManager.setFd_bonus_coin(Integer.toString(now_coin.getFd_bonus_coin() + sum_use_coin.getFd_bonus_coin()));
					smsManager.setFd_total_coin(Integer.toString(now_coin.getFd_total_coin() + sum_use_coin.getFd_total_coin()));
					
					if(smsManagerDao.cancelationCoinLog(smsManager) > 0){		//코인 사용 취소 처리
						System.out.println("코인 취소 처리 if3");
						if(smsManagerDao.updateCancelCoin(smsManager) > 0){
							result_str = "200";
						}
					}else{
						System.out.println("코인 취소 처리 else1");
					}
				}else{
					System.out.println("코인 취소 처리 else2");
				}
			}else{
				System.out.println("코인 취소 처리 else3");
			}
		} catch (Exception e) {
			System.out.println("코인 취소 처리 Exception"+e.getMessage());
			// TODO: handle exception
			e.printStackTrace();
			result_str = "302";
			transactionManager.rollback(status);
		}
		
		if(result_str.equals("200")){
			transactionManager.commit(status);
		}else{
			if(result_str.equals("301"))
				transactionManager.rollback(status);
		}
		
		return result_str;
	}
	
	@Override
	public String gotoLog(String fk_member_id){
		// TODO Auto-generated method stub
		String result_str = "301";
		
		System.out.println("SMS 예약 취소 처리");
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("gotoLog-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			if(smsManagerDao.copySmsReservation(fk_member_id) > 0){
				System.out.println("SMS 예약 취소 처리 if1");
				if(smsManagerDao.deleteSmsReservation(fk_member_id) > 0){
					System.out.println("SMS 예약 취소 처리 if2");
					result_str = "200";
				}else{
					System.out.println("SMS 예약 취소 처리 else1");
				}
			}else{
				System.out.println("SMS 예약 취소 처리 else2");
			}
		} catch (Exception e) {
			System.out.println("SMS 예약 취소 처리 Exception="+e.getMessage());
			// TODO: handle exception
			e.printStackTrace();
			result_str = "302";
			transactionManager.rollback(status);
		}
		
		if(result_str.equals("200")){
			transactionManager.commit(status);
		}else{
			if(result_str.equals("301"))
				transactionManager.rollback(status);
		}
		
		return result_str;
	}
	 
} 
