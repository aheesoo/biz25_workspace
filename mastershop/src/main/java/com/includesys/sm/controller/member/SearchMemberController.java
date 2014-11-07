package com.includesys.sm.controller.member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.member.SearchMember;
import com.includesys.sm.service.member.SearchMemberService;
import com.includesys.sm.util.HashEncrypt;
import com.includesys.sm.util.SendMail;

@RequestMapping("/manager")
@Controller
public class SearchMemberController {
	
	@Autowired
	private SearchMemberService SearchMemberService; 

	//@Autowired
	//private HttpSession session;
	
	@Autowired
	private ServletContext servletContext;

	/**
	 * 아이디 찾기
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchId.do")
	public ModelAndView searchId(HttpServletRequest request){
		System.out.println("\n[CALL Controller ] SearchMemberController.searchId()");
		
		  
        Calendar cal = Calendar.getInstance();
        
        int year = cal.get(Calendar.YEAR);

		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/search/searchId");
		mav.addObject("year", year);
	
		return mav;
	}
	
	/**
	 * 비밀번호 찾기
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchPw.do")
	public ModelAndView searchPw(HttpServletRequest request){
		System.out.println("\n[CALL Controller ] SearchMemberController.searchPw()");
				
		Calendar cal = Calendar.getInstance();
        
        int year = cal.get(Calendar.YEAR);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/search/searchPw");
		mav.addObject("year", year);		
		
		return mav;
	}
	
	
	/**
	 * 아이디 찾기 요청
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchId.do" , params="event=search")	
	public JSONObject searchIdView(HttpServletRequest request){
		System.out.println("\n[CALL Controller ] SearchMemberController.searchIdView()");
		
		String req_name 				= request.getParameter("searchName")		!= null ? request.getParameter("searchName").trim() : "";
		String req_searchTel1 		= request.getParameter("searchTel1")		!= null ? request.getParameter("searchTel1").trim() : "";
		String req_searchTel2 		= request.getParameter("searchTel2")		!= null ? request.getParameter("searchTel2").trim() : "";
		String req_searchTel3 		= request.getParameter("searchTel3")		!= null ? request.getParameter("searchTel3").trim() : "";
		String req_searchType 		= request.getParameter("searchType")		!= null ? request.getParameter("searchType").trim() : "";
		String req_searchYear 		= request.getParameter("searchYear")		!= null ? request.getParameter("searchYear").trim() : "";
		String req_searchMonth 	= request.getParameter("searchMonth")		!= null ? request.getParameter("searchMonth").trim() : "";
		String req_searchDay 		= request.getParameter("searchDay")			!= null ? request.getParameter("searchDay").trim() : "";
		String req_searchGender 	= request.getParameter("searchGender")	!= null ? request.getParameter("searchGender").trim() : "";
		String req_searchInfo 		= request.getParameter("searchInfo")		!= null ? request.getParameter("searchInfo").trim() : "";
		
		boolean proc_status =true;
		String rtn_code = "200"; 
		
		String search_tel = "";
		String search_birth= "";
		JSONObject jsonObj = new JSONObject();
		
		SearchMember searchMember = null;
		
		if(req_name.equals("")){
			rtn_code = "301";
			proc_status =false;
		}else if(req_searchTel1.equals("")){
			rtn_code = "302";
			proc_status =false;
		}else if(req_searchTel2.equals("")){
			rtn_code = "303"; 
			proc_status =false;
		}else if(req_searchTel3.equals("")){
			rtn_code = "304"; 
			proc_status =false;
		}else if(req_searchType.equals("")){
			rtn_code = "305"; 
			proc_status =false;
		}
		if(proc_status){
			if(req_searchType.equals("3")){
				if(req_searchYear.equals("")){
					rtn_code = "306";
					proc_status =false;
				}else if(req_searchMonth.equals("")){
					rtn_code = "307"; 
					proc_status =false;
				}else if(req_searchDay.equals("")){
					rtn_code = "308"; 
					proc_status =false;
				}else if(req_searchGender.equals("")){
					rtn_code = "309"; 
					proc_status =false;
				}
			}else{
				if(req_searchInfo.equals("")){
					rtn_code = "310"; 
					proc_status =false;
				}
			}
		}
		
		if(proc_status){
			search_tel = req_searchTel1 + req_searchTel2 + req_searchTel3;
			search_birth = req_searchYear + req_searchMonth + req_searchDay;
			
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("search_tel", search_tel);
			hmap.put("search_birth", search_birth);
			hmap.put("search_name", req_name);
			hmap.put("search_gender", req_searchGender);
			hmap.put("search_num", req_searchInfo);
			hmap.put("search_type", req_searchType);
			
			try{
				searchMember = SearchMemberService.selectMemberInfo(hmap);
				
				if(searchMember== null){
					rtn_code = "201";
				}else{
					if(searchMember.getFd_mobile() == null ||searchMember.getFd_mobile().equals("")){
						rtn_code = "202";
					}
				}
			}catch(Exception e){
				rtn_code = "501";
				e.printStackTrace();
			}
			
			if(rtn_code.equals("200")){
				try{
					
					Random oRandom = new Random();

				    // 1~10까지의 정수를 랜덤하게 출력
					String AuthKey = "";
					for(int i=0;i<4;i++){
						int Key = oRandom.nextInt(10) ;
						AuthKey+= Integer.toString(Key);
					}
				    
				    				
					hmap.put("fd_security_key", AuthKey);
					hmap.put("fd_member_id", searchMember.getPk_member_id());
					
					int result = SearchMemberService.insertCreateSecurityKey(hmap);
					
					if(result>0){
						result = 0;
						HashMap<String , Object> sHmap = new HashMap<>(); // SMS 발송 hash Map
						
						long oneNum = System.nanoTime();
						
						String fk_custom_msg_id = "masterShopSms_"+oneNum;
						
						Date d = new Date();
				        
				        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
				        
				        String reseve_date = sdf.format(d);
				        
						sHmap.put("fk_member_id", 			"masterShopSms");
						sHmap.put("fk_tel", 					"15885668");
						
						sHmap.put("fk_custom_msg_id",	fk_custom_msg_id);
						sHmap.put("pk_group_code",			Long.toString(oneNum));
						sHmap.put("fk_group_code",			Long.toString(oneNum));
						
						
						sHmap.put("msg_type", 				"1");
						sHmap.put("msg_sub_type",			"1");
						sHmap.put("req_count", 				1);
						sHmap.put("receive_number", 		searchMember.getFd_mobile());
						
						sHmap.put("send_subject", 			"[마스터샵] 아이디 찾기 인증 번호");
						sHmap.put("send_content", 			"[인증번호:"+AuthKey+"] 마스터샵에서 보낸 인증 번호 입니다.");
						sHmap.put("reserve_time", 			reseve_date);
						sHmap.put("req_member_type", 	"I");
						sHmap.put("member_ip", 				request.getRemoteAddr());
						sHmap.put("reserve_type", 			"1");
						sHmap.put("reserve_date", 			reseve_date);						
						
						result = SearchMemberService.insertSearchSmsSend(sHmap);

						if(result<=0){
							rtn_code = "702";
						}
					}
					
				}catch(Exception e){
					rtn_code = "601";
					e.printStackTrace();
				}
			}
		}
		
		
		
		jsonObj.put("rtn_code", rtn_code);

		return jsonObj;
	}
	
	
	/**
	 * 아이디 찾기 요청
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchId.do" , params="event=proc")	
	public JSONObject searchIdProc(HttpServletRequest request){
		System.out.println("\n[CALL Controller ] SearchMemberController.searchIdProc()");
		
		String req_name 				= request.getParameter("searchName")		!= null ? request.getParameter("searchName").trim() : "";
		String req_searchTel1 		= request.getParameter("searchTel1")		!= null ? request.getParameter("searchTel1").trim() : "";
		String req_searchTel2 		= request.getParameter("searchTel2")		!= null ? request.getParameter("searchTel2").trim() : "";
		String req_searchTel3 		= request.getParameter("searchTel3")		!= null ? request.getParameter("searchTel3").trim() : "";
		String req_searchType 		= request.getParameter("searchType")		!= null ? request.getParameter("searchType").trim() : "";
		String req_searchYear 		= request.getParameter("searchYear")		!= null ? request.getParameter("searchYear").trim() : "";
		String req_searchMonth 	= request.getParameter("searchMonth")		!= null ? request.getParameter("searchMonth").trim() : "";
		String req_searchDay 		= request.getParameter("searchDay")			!= null ? request.getParameter("searchDay").trim() : "";
		String req_searchGender 	= request.getParameter("searchGender")	!= null ? request.getParameter("searchGender").trim() : "";
		String req_searchInfo 		= request.getParameter("searchInfo")		!= null ? request.getParameter("searchInfo").trim() : "";
		String req_chkNum			= request.getParameter("chkNum")		!= null ? request.getParameter("chkNum").trim() : "";
		
		
		boolean proc_status =true;
		String rtn_code = "200"; 
		
		String search_tel = "";
		String search_birth= "";
		JSONObject jsonObj = new JSONObject();
		
		SearchMember searchMemberInfo = null;
		SearchMember searchMemberSecurity = null;
		
		if(req_name.equals("")){
			rtn_code = "301";
			proc_status =false;
		}else if(req_searchTel1.equals("")){
			rtn_code = "302";
			proc_status =false;
		}else if(req_searchTel2.equals("")){
			rtn_code = "303"; 
			proc_status =false;
		}else if(req_searchTel3.equals("")){
			rtn_code = "304"; 
			proc_status =false;
		}else if(req_searchType.equals("")){
			rtn_code = "305"; 
			proc_status =false;
		}
		if(proc_status){
			if(req_searchType.equals("3")){
				if(req_searchYear.equals("")){
					rtn_code = "306";
					proc_status =false;
				}else if(req_searchMonth.equals("")){
					rtn_code = "307"; 
					proc_status =false;
				}else if(req_searchDay.equals("")){
					rtn_code = "308"; 
					proc_status =false;
				}else if(req_searchGender.equals("")){
					rtn_code = "309"; 
					proc_status =false;
				}
			}else{
				if(req_searchInfo.equals("")){
					rtn_code = "310"; 
					proc_status =false;
				}
			}
		}
		
		if(proc_status){
			search_tel = req_searchTel1 + req_searchTel2 + req_searchTel3;
			search_birth = req_searchYear + req_searchMonth + req_searchDay;
			
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("search_tel", search_tel);
			hmap.put("search_birth", search_birth);
			hmap.put("search_name", req_name);
			hmap.put("search_gender", req_searchGender);
			hmap.put("search_num", req_searchInfo);
			hmap.put("search_type", req_searchType);
			hmap.put("fd_security_key", req_chkNum);
			
			try{
				searchMemberInfo = SearchMemberService.selectMemberInfo(hmap);
				
				if(searchMemberInfo== null){
					rtn_code = "201";
				}else{
					if(searchMemberInfo.getFd_mobile() == null ||searchMemberInfo.getFd_mobile().equals("")){
						rtn_code = "202";
					}
				}
			}catch(Exception e){
				rtn_code = "501";
				e.printStackTrace();
			}
			
			if(rtn_code.equals("200")){
				try{
					hmap.put("fd_member_id", searchMemberInfo.getPk_member_id());
					searchMemberSecurity = SearchMemberService.selectMemberInfoSecurityKey(hmap);
					
					if(searchMemberSecurity== null){
						rtn_code = "203";
					}else{
						jsonObj.put("member_id", searchMemberSecurity.getFd_member_id());
					}
				}catch(Exception e){
					rtn_code = "502";
					e.printStackTrace();
				}
			}
		}
		
		
		
		jsonObj.put("rtn_code", rtn_code);

		return jsonObj;
	}
	
	
	
	/**
	 * 비밀번호 찾기 요청 처리
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchPw.do" , params="event=proc")	
	public JSONObject searchPwProc(HttpServletRequest request){
		System.out.println("\n[CALL Controller ] SearchMemberController.searchPwProc()");
		
		String req_id 					= request.getParameter("searchId")			!= null ? request.getParameter("searchId").trim() : "";
		//String req_name 				= request.getParameter("searchName")		!= null ? request.getParameter("searchName").trim() : "";
		String req_searchTel1 		= request.getParameter("searchTel1")		!= null ? request.getParameter("searchTel1").trim() : "";
		String req_searchTel2 		= request.getParameter("searchTel2")		!= null ? request.getParameter("searchTel2").trim() : "";
		String req_searchTel3 		= request.getParameter("searchTel3")		!= null ? request.getParameter("searchTel3").trim() : "";
		String req_searchType 		= request.getParameter("searchType")		!= null ? request.getParameter("searchType").trim() : "";
		String req_searchYear 		= request.getParameter("searchYear")		!= null ? request.getParameter("searchYear").trim() : "";
		String req_searchMonth 	= request.getParameter("searchMonth")		!= null ? request.getParameter("searchMonth").trim() : "";
		String req_searchDay 		= request.getParameter("searchDay")			!= null ? request.getParameter("searchDay").trim() : "";
		String req_searchGender 	= request.getParameter("searchGender")	!= null ? request.getParameter("searchGender").trim() : "";
		String req_searchInfo 		= request.getParameter("searchInfo")		!= null ? request.getParameter("searchInfo").trim() : "";
		//String req_chkNum			= request.getParameter("chkNum")		!= null ? request.getParameter("chkNum").trim() : "";
		
		boolean proc_status =true;
		String rtn_code = "200"; 
		
		String search_tel = "";
		String search_birth= "";
		JSONObject jsonObj = new JSONObject();
		
		SearchMember searchMemberInfo = null;
		SearchMember searchMemberSecurity = null;
		
		if(req_id.equals("")){
			rtn_code = "301";
			proc_status =false;
		}/*else if(req_name.equals("")){
			rtn_code = "302";
			proc_status =false;
		}*/else if(req_searchTel1.equals("")){
			rtn_code = "303";
			proc_status =false;
		}else if(req_searchTel2.equals("")){
			rtn_code = "304"; 
			proc_status =false;
		}else if(req_searchTel3.equals("")){
			rtn_code = "305"; 
			proc_status =false;
		}else if(req_searchType.equals("")){
			rtn_code = "306"; 
			proc_status =false;
		}
		if(proc_status){
			if(req_searchType.equals("3")){
				if(req_searchYear.equals("")){
					rtn_code = "307";
					proc_status =false;
				}else if(req_searchMonth.equals("")){
					rtn_code = "308"; 
					proc_status =false;
				}else if(req_searchDay.equals("")){
					rtn_code = "309"; 
					proc_status =false;
				}else if(req_searchGender.equals("")){
					rtn_code = "310"; 
					proc_status =false;
				}
			}else{
				if(req_searchInfo.equals("")){
					rtn_code = "311"; 
					proc_status =false;
				}
			}
		}
		
		if(proc_status){
			search_tel = req_searchTel1 + req_searchTel2 + req_searchTel3;
			search_birth = req_searchYear + req_searchMonth + req_searchDay;
			
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("search_id", req_id);
			hmap.put("search_tel", search_tel);
			hmap.put("search_birth", search_birth);
		//	hmap.put("search_name", req_name);
			hmap.put("search_gender", req_searchGender);
			hmap.put("search_num", req_searchInfo);
			hmap.put("search_type", req_searchType);	
			
			try{
				searchMemberInfo = SearchMemberService.selectMemberInfo(hmap);
				
				if(searchMemberInfo== null){
					rtn_code = "201";
				}else{
					if(searchMemberInfo.getPk_member_id() == null ||searchMemberInfo.getPk_member_id().equals("")){
						rtn_code = "202";
					}
				}
				
				if(rtn_code.equals("200")){
					try{
						
						String temp_paswd = "";
						
						for(int i = 0; i < 8; i++){
							   //char upperStr = (char)(Math.random() * 26 + 65);
							   char lowerStr = (char)(Math.random() * 26 + 97);
							   if(i%2 == 0){
								   temp_paswd += (int)(Math.random() * 10);
							   }else{
								   temp_paswd += lowerStr;							   
							   }
						}

						
						String nowDate = "";
						
						SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
						nowDate = ft.format(new Date());				
						HashEncrypt encrypt = new HashEncrypt(temp_paswd);
						
						//System.out.println("encrypt : " + encrypt.getEncryptData());
						
						hmap.put("temp_paswd", encrypt.getEncryptData());
						
						int result = SearchMemberService.updateMemberTempPassword(hmap);
						
						if(result<=0){
							rtn_code = "701";
						}else{
							SendMail sendMail = new SendMail();
							
							String from = "webmaster@mastershop.co.kr";
							String to = searchMemberInfo.getPk_member_id();
							String subject = "[마스터샵] 비밀번호 초기화 메일 발송";
							
							
							//String content = "고객님의 비밀번호가 초기화 되었습니다. 고객님님의 비밀번호는 <b>" +temp_paswd+ "</b>  입니다.";
							
							String content = sendMail.mailTemplate(searchMemberInfo.getFd_user_name(), temp_paswd);
							
							sendMail.sendEmail(from, to, subject, content);
							
							
							jsonObj.put("email", searchMemberInfo.getPk_member_id());
						}
						
						
						
					}catch(Exception e){
						rtn_code = "502";
						e.printStackTrace();
					}
				}
			}catch(Exception e){
				rtn_code = "501";
				e.printStackTrace();
			}
			
			
		}
		
		jsonObj.put("rtn_code", rtn_code);

		return jsonObj;
	}
	
	/**
	 * 비밀번호 찾기 요청 처리  step 1
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchPw.do" , params="event=search")	
	public JSONObject searchPwProcStep1(HttpServletRequest request){
		System.out.println("\n[CALL Controller ] SearchMemberController.searchPwProcStep1()");
		
		String req_id 					= request.getParameter("searchId")			!= null ? request.getParameter("searchId").trim() : "";		
		String req_searchTel1 		= request.getParameter("searchTel1")		!= null ? request.getParameter("searchTel1").trim() : "";
		String req_searchTel2 		= request.getParameter("searchTel2")		!= null ? request.getParameter("searchTel2").trim() : "";
		String req_searchTel3 		= request.getParameter("searchTel3")		!= null ? request.getParameter("searchTel3").trim() : "";
		
		boolean proc_status =true;
		String rtn_code = "200"; 
		
		String search_tel = "";
		String search_birth= "";
		JSONObject jsonObj = new JSONObject();
		
		SearchMember searchMemberInfo = null;
		
		if(req_id.equals("")){
			rtn_code = "301";
			proc_status =false;
		}else if(req_searchTel1.equals("")){
			rtn_code = "303";
			proc_status =false;
		}else if(req_searchTel2.equals("")){
			rtn_code = "304"; 
			proc_status =false;
		}else if(req_searchTel3.equals("")){
			rtn_code = "305"; 
			proc_status =false;
		}
		
		
		if(proc_status){
			search_tel = req_searchTel1 + req_searchTel2 + req_searchTel3;
			
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("search_id", req_id);
			hmap.put("search_tel", search_tel);
			hmap.put("search_birth", search_birth);
			
			try{
				searchMemberInfo = SearchMemberService.selectMemberInfo(hmap);
				
				if(searchMemberInfo== null){
					rtn_code = "201";
				}
			
			}catch(Exception e){
				rtn_code = "501";
				e.printStackTrace();
			}
			
		}
		
		jsonObj.put("rtn_code", rtn_code);

		return jsonObj;
	}
}
