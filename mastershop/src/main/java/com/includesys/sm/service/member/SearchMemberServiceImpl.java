package com.includesys.sm.service.member;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.includesys.sm.dao.member.SearchMemberDao;
import com.includesys.sm.dto.member.SearchMember;

@Service
public class SearchMemberServiceImpl implements SearchMemberService{
	
	//service클래스의 역할 : 필요한 dao호출

		private static Logger logger = LoggerFactory.getLogger(SearchMemberServiceImpl.class);

		@Autowired
		private SearchMemberDao searchMemberDao;

		@Autowired
		private DataSourceTransactionManager transactionManager;
		
		@Override
		public int selectMemberInfoCount(HashMap<String, Object> hmap) {
			return searchMemberDao.selectMemberInfoCount(hmap);
		}

		@Override
		public SearchMember selectMemberInfo(HashMap<String, Object> hmap) {
			return searchMemberDao.selectMemberInfo(hmap);
		}

		@Override
		public int insertCreateSecurityKey(HashMap<String, Object> hmap) {
			return searchMemberDao.insertCreateSecurityKey(hmap);
		}

		@Override
		public SearchMember selectMemberInfoSecurityKey(HashMap<String, Object> hmap) {
			return searchMemberDao.selectMemberInfoSecurityKey(hmap);
		}

		@Override
		public int updateMemberTempPassword(HashMap<String, Object> hmap) {
			return searchMemberDao.updateMemberTempPassword(hmap);
		}

		@Override
		public int insertMemberSearchSmsGroup(HashMap<String, Object> hmap) {
			return searchMemberDao.insertMemberSearchSmsGroup(hmap);
		}

		@Override
		public int insertMemberSearchSmsReservation(HashMap<String, Object> hmap) {
			return searchMemberDao.insertMemberSearchSmsReservation(hmap);
		}

		
		/**
		 * 아이디 찾기  SMS 발송
		 */
		@Override
		public int insertSearchSmsSend(HashMap<String, Object> hmap) {
			int result = 0;
			
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			
			def.setName("registerSms-transaction");
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			
			TransactionStatus status = transactionManager.getTransaction(def);
			
			try{
				result = searchMemberDao.insertMemberSearchSmsGroup(hmap);
				
				if(result>0){
					result = -1;
					try{
						result = searchMemberDao.insertMemberSearchSmsReservation(hmap);
					}catch(Exception e){
						result = -1;
						e.printStackTrace();
					}
				}
			}catch(Exception e){
				result = -1;
				e.printStackTrace();
			}
			
			
			if(result>0){
				transactionManager.commit(status);
			}else{			
				transactionManager.rollback(status);
			}
			return result;
		
		}
		
}
