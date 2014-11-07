package com.includesys.sm.service.csManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.includesys.sm.dto.csManager.CsCustomer;
import com.includesys.sm.dao.csManager.CsCustomerDao;

@Service
public class CsCustomerServiceImpl implements CsCustomerService{
	
	//service클래스의 역할 : 필요한 dao호출

	@Autowired
	private DataSourceTransactionManager transactionManager;

		private static Logger logger = LoggerFactory.getLogger(CsCustomerServiceImpl.class);

		@Autowired
		private CsCustomerDao csCustomerDao;
		
		@Override
		public int getCount(Map<String, Object> map) 	{
			return csCustomerDao.getCount(map);
		}
		
		@Override
		public List<CsCustomer> getList(Map<String, Object> map) 	{
			return csCustomerDao.getList(map);
		}

		@Override
		public void register(CsCustomer csCustomer) 	{
			csCustomerDao.register(csCustomer);
		}

		public CsCustomer get(Map<String, Object> map) 	{
			return csCustomerDao.get(map);
		}
		
		@Override
		public void modify(CsCustomer csCustomer) 	{
			csCustomerDao.modify(csCustomer);
		}
		
		@Override
		public void remove(Map<String, Object> map) 	{
			csCustomerDao.remove(map);
		}

		@Override
		public String getLatelyCallDate(Map<String, Object> map) {
			return csCustomerDao.getLatelyCallDate(map);
		}
		
		@Override
		public CsCustomer getSmsSendCount(Map<String, Object> map) {
			return csCustomerDao.getSmsSendCount(map);
		}
		
		@Override
		public CsCustomer getWeekSmsReport(Map<String, Object> map) {
			return csCustomerDao.getWeekSmsReport(map);
		}
		
		@Override
		public CsCustomer getTimeSmsReport(Map<String, Object> map) {
			return csCustomerDao.getTimeSmsReport(map);
		}
		
		@Override
		public List<CsCustomer> getSmsSendDate(Map<String, Object> map) {
			return csCustomerDao.getSmsSendDate(map);
		}
		
		@Override
		public CsCustomer getSmsSendDateDetail(Map<String, Object> map) {
			return csCustomerDao.getSmsSendDateDetail(map);
		}

		@Override
		public int registerList(ArrayList<CsCustomer> customerList) {
			// TODO Auto-generated method stub
			int result = 0;
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setName("registerCsCustomer");
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			
			TransactionStatus status = transactionManager.getTransaction(def);
			
			try{
				for(CsCustomer item:customerList){
					result = -1;
					
					try{
						System.out.println("itemList = "+item.getPk_customer_tel());
						result = csCustomerDao.registerList(item);
						System.out.println("resutNum = "+result);
					}catch(Exception e){
						result = -1;
						e.printStackTrace();
					}
				}
			}catch(Exception e){
				result = -1;
				e.printStackTrace();
			}
			System.out.println("resultNum1 = "+result);

			if(result>0){
				transactionManager.commit(status);
			}else{
				transactionManager.rollback(status);
			}
			return result;
		}
}
