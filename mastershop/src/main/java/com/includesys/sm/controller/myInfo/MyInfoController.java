package com.includesys.sm.controller.myInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Code;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.member.MemberSub;
import com.includesys.sm.dto.member.Zipcode;
import com.includesys.sm.dto.member.ZipcodeDoro;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.smsManager.BoardEvent;
import com.includesys.sm.dto.smsManager.SmsManager;
import com.includesys.sm.service.member.CodeService;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.service.myinfo.CoinService;
import com.includesys.sm.service.smsManager.SmsManagerService;
import com.includesys.sm.util.HashEncrypt;
import com.includesys.sm.util.StrUtils;

@Controller
@RequestMapping("/myInfo")
public class MyInfoController {

	private static Logger logger = LoggerFactory.getLogger(MyInfoController.class);
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CoinService coinService; 
	
	@Autowired
	private CodeService codeService;

	@Autowired
	private SmsManagerService smsManagerService;
	
	/**
	 * 내정보 > 가입정보
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/joinInfo.do")
	public ModelAndView joinInfo(HttpServletRequest request) {
		
		System.out.println("\n[CALL Controller] MemberController.memberModifyForm()");

		int page = 1 ;				
		String searchColumn = request.getParameter("searchColumn") == null ? "" : request.getParameter("searchColumn").trim();
		String searchString = request.getParameter("searchString") == null ? "" : request.getParameter("searchString").trim();
		String pageStr = request.getParameter("page") == null ? "" : request.getParameter("page").trim();
		page = pageStr.equals("") ? page : Integer.parseInt(pageStr);

		List<Code> stateList 	= codeService.getCodeList("1300");	//회원상태 코드리스트
		List<Code> productList 	= codeService.getCodeList("3200");	//제품상태 코드리스트
		List<Code> regStateList = codeService.getCodeList("3300");	//회원상태 코드리스트
		
		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		String script = "";

		searchColumn = "id";
		searchString = loginInfo.getPk_member_id();

		PageHelper pageHelper = new PageHelper(page, 0, 0, searchColumn, searchString);

		//회원정보 취득
		Member member = memberService.get(loginInfo.getPk_member_id());
		
		//비지니스 타입명
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fd_business_type1", member.getFd_business_type1());
		map.put("fd_business_type2", member.getFd_business_type2());
		String business_type = memberService.getBusinessName(map);
		member.setFd_business_type(business_type);
		
		//상태코드값에 따른 코드이름 취득
		Code stateCode = codeService.getCode(member.getFd_member_state());
		String stateStr = stateCode.getFd_name();
		if(member == null){
			System.out.println("======= memberModifyForm null ========");
		}
		
		//핸드폰 번호를 배열로 구분
		String arrMobile[] = null;
		if(member.getFd_mobile()!=null && member.getFd_mobile().length() >= 10){
			String tmpMobile = StrUtils.formatPhoneNo(member.getFd_mobile(), "-");
			arrMobile = tmpMobile.split("-");
			
			member.setFd_mobile(StrUtils.formatPhoneNo(member.getFd_mobile(), "-"));
		}

		//우편번호를 배열로 구분
		String arrPostNum[] = null;
		System.out.println("member.getFd_post_num()="+member.getFd_post_num());
		if(member.getFd_post_num()!=null && member.getFd_post_num().length() >= 6){
			arrPostNum = (member.getFd_post_num()).split("-");
		}

		//법인(주민) 번호를 배열로 구분
		String arrRregistNum[] = {"",""};
		if(member.getFd_regist_num()!=null && member.getFd_regist_num().indexOf("-") > 0){
			arrRregistNum = (member.getFd_regist_num()).split("-");
		}else if(member.getFd_regist_num()!=null &&  member.getFd_regist_num().length() > 0){
			arrRregistNum[0] = member.getFd_regist_num();
		}
		
		//회원 서비스 번호 리스트(매핑테이블에 대한 정보조회)
		List<MemberSub> memberSubList = memberService.getTelInfoList(pageHelper);

		List<Code> productTypeArr	= new ArrayList<Code>(); // 회원 상품의 상태 (코드값 배열처리) 
		List<Code> regStateArr		= new ArrayList<Code>(); // 회원 등록 상태 (코드값 배열처리) 

		//목록 리스트
		int i=0;
		for(MemberSub memberSub : memberSubList) {
			//상태
			for(Code item : productList){
				//System.out.println("item.getPk_code()="+item.getPk_code());
				//System.out.println("memberSub.getFd_product_type()="+memberSub.getFd_product_type());
				if(item.getPk_code().equals(memberSub.getFd_product_type())){
					productTypeArr.add(item);
				}
			}
			//상태
			for(Code item : regStateList){
				if(item.getPk_code().equals(memberSub.getFd_reg_state())){
					regStateArr.add(item);
				}
			}
			
			memberSub.setPk_tel(StrUtils.formatPhoneNo(memberSub.getPk_tel(), "-"));
			memberSubList.set(i, memberSub);
			i++;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myInfo/joinInfo/joinInfo");
		
		mav.addObject("pageHelper", pageHelper);
		mav.addObject("stateList", stateList);	
		mav.addObject("stateStr", stateStr);
		mav.addObject("member", member);
		mav.addObject("memberSub", memberSubList);
		
		mav.addObject("arrMobile", 		arrMobile);
		mav.addObject("arrPostNum", 	arrPostNum);
		mav.addObject("arrRregistNum",	arrRregistNum);
		
		mav.addObject("productTypeArr", productTypeArr);
		mav.addObject("regStateArr", regStateArr);
		
		//mav.addObject("msgList", msgList);
		mav.addObject("script",script);
		
		return mav;
	}
	
	
	
	
	/**
	 * 가입정보 수정
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/joinInfoModify.do" )
	public ModelAndView joinInfoModify(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MyInfoController.joinInfoModify()");

		ModelAndView mav = new ModelAndView();
		
		mav = joinInfo(request);

		mav.setViewName("myInfo/joinInfo/joinInfoModify");
		
		return mav;
		
	}
	
	

	/**
	 * 가입정보 수정
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/joinInfoModify.do", params="proc=modify")
	public ModelAndView joinInfoModifyProc(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MyInfoController.joinInfoModifyProc");
		
		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		String pk_member_id		= loginInfo.getPk_member_id();
		String fd_mobile		= request.getParameter("fd_mobile") != null 		? request.getParameter("fd_mobile").trim() : "";
        String fd_addr          = request.getParameter("fd_addr") != null			? request.getParameter("fd_addr") : ""; 			//주소
        String fd_addr_detail   = request.getParameter("fd_addr_detail") != null 	? request.getParameter("fd_addr_detail") : ""; 		//주소 상세
		String fd_post_num1		= request.getParameter("fd_post_num1") != null 		? request.getParameter("fd_post_num1").trim() : "";
		String fd_post_num2		= request.getParameter("fd_post_num2") != null 		? request.getParameter("fd_post_num2").trim() : "";
		String fd_post_num		= fd_post_num1 +"-"+ fd_post_num2;
	    String fd_view_name     = request.getParameter("fd_view_name") != null 		? request.getParameter("fd_view_name") : ""; 		//

		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");		
		//String now_date =  ft.format(new Date( ));
		//Member pre_member = memberService.get(pk_member_id);// 변경전 맴버 정보
		
		Member member = new Member();
		member.setFd_mobile(fd_mobile.replaceAll("-", ""));
		member.setFd_addr(fd_addr);
		member.setFd_addr_detail(fd_addr_detail);
		member.setFd_post_num(fd_post_num);
		member.setPk_member_id(pk_member_id);
		member.setFd_view_name(fd_view_name);
		
		String script = "";
		
		try	{ 
			
			memberService.modify(member);
			
			script="alert('회원정보 수정이 완료 되었습니다.');";
			
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			script="alert('회원정보 수정에 실패 하셨습니다.다시 시도해주세요');";

		}
		
		System.out.println("script="+script);

		ModelAndView mav = new ModelAndView();
		
		//회원 기본정보를 가져온뒤
		mav = joinInfo(request);
		
		mav.addObject("script", script);
		mav.setViewName("myInfo/joinInfo/joinInfo");
		
		return mav;
		
	}
	
	
	
	/**
	 * 비밀번호 수정
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/changePwd.do" )
	public ModelAndView changePwd(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MyInfoController.changePwd()");

		String script = "";
		String oldPwd = request.getParameter("oldPwd") == null ? "" : request.getParameter("oldPwd").trim();
		String newPwd = request.getParameter("newPwd") == null ? "" : request.getParameter("newPwd").trim();
		System.out.println(" .login oldPwd="+oldPwd+"/ newPwd="+newPwd);

		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		Member member = memberService.get(loginInfo.getPk_member_id());
		

		HashEncrypt encrypt_old = new HashEncrypt(oldPwd);
		oldPwd = encrypt_old.getEncryptData();

		HashEncrypt encrypt_new = new HashEncrypt(newPwd);
		newPwd = encrypt_new.getEncryptData();
		
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println(" .login member.getFd_member_pwd()="+member.getFd_member_pwd()+"/ oldPwd="+oldPwd);
		
		if(member != null && member.getFd_member_pwd().equals(oldPwd))
		{

			member.setPk_member_id(loginInfo.getPk_member_id());
			member.setFd_member_pwd(newPwd);
			
			try	{
				
				memberService.changePw(member);
				script="alert('비밀번호가 정상적으로 변경되었습니다.');";
				
			} catch(Exception ex){
				
				logger.error(ex.getMessage());
				script="alert('비밀번호 변경이 실패 하였습니다.');";
				//script+="location.href='/member/changePwForm.do';";
			}

		}
		else
		{
			script = "alert('비밀번호를 잘못 입력하셨습니다.');";
		}

		System.out.println("script="+script);

		//회원 기본정보를 가져온뒤
		mav = joinInfo(request);
		
		mav.addObject("script", script);
		mav.setViewName("myInfo/joinInfo/joinInfoModify");
		
		return mav;
		
	}
	
	
	
	////////////////////////////////////////////////
	/**
	 * 가입상품
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/myProduct.do" )
	public ModelAndView myProduct(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MyInfoController.myProduct()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myInfo/myProduct/myProduct");
	
		return mav;		
	}
	
	
	////////////////////////////////////////////////
	
	/**
	 * 포인트 충전내역
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/myPointCharge.do" )
	public ModelAndView myPointCharge(HttpServletRequest request) throws Exception	{
		
		System.out.println("\n[CALL Controller] MyInfoController.myPointCharge()");
		
		
		boolean proceStatus = true;				// 처리 상황
		
		List<Coin> coinChargeLogList = null;  	// 리스트 
		int coinChargeLogListCount = 0;			// 리스트 카운트	
		
		//로그인 값 가져오기
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		if(loginInfo == null || loginInfo.getPk_member_id()==null){
			proceStatus = false;
			throw new Exception();
		}
		
		String fk_member_id = loginInfo.getPk_member_id();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("fk_member_id", fk_member_id);
		
		if(proceStatus){
			try{
				coinChargeLogListCount = coinService.getCoinChargeLogListCount(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		if(proceStatus){
			try{
				coinChargeLogList = coinService.getCoinChargeLogList(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myInfo/myPoint/myPointCharge");
		mav.addObject("coinChargeLogListCount", coinChargeLogListCount);
		mav.addObject("coinChargeLogList", coinChargeLogList);
	
		return mav;		
	}
	
	
	////////////////////////////////////////////////
	
	/**
	 * 포인트 사용내역
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/myPointUse.do" )
	public ModelAndView myPointUse(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MyInfoController.myPointUse()");
		
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMM", Locale.KOREA );
		Date currentTime = new Date ( );
		String this_month = mSimpleDateFormat.format ( currentTime );
				
		
		String search_date 	= request.getParameter("search_date") != null ? request.getParameter("search_date") : this_month;
		//String search_date 	= request.getParameter("search_date") != null ? request.getParameter("search_date") : "";
		String search_tel 	= request.getParameter("search_tel") != null ? request.getParameter("search_tel") : "";
		search_tel 			= search_tel.replaceAll("-", "");
		
		boolean proceStatus = true;				// 처리 상황
		
		List<Coin> coinUseLogList = null;  		// 포인트 사용 리스트 
		List<Coin> coinUserTelList = null;  		// 사용자 전화번호 리스트
		int coinUseLogListCount = 0;				// 리스트 카운트	
		
		//로그인 값 가져오기
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		if(loginInfo == null || loginInfo.getPk_member_id()==null){
			proceStatus = false;			
		}
		
		String fk_member_id = loginInfo.getPk_member_id();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("fk_member_id", fk_member_id);		
		map.put("req_date", search_date);
	
		
		if(proceStatus){
			try{
				coinUserTelList = coinService.getUserTelList(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		if(coinUserTelList.size()>0){
			
			if(search_tel.equals("")){
				search_tel = coinUserTelList.get(0).getPk_tel();
			}
			
			for(int i=0; i<coinUserTelList.size(); i++){
				Coin tmpCoin = coinUserTelList.get(i);
				tmpCoin.setPk_tel(StrUtils.formatPhoneNo(""+coinUserTelList.get(0).getPk_tel(),"-"));
				coinUserTelList.set(i, tmpCoin);
			}
			
		}else{
			proceStatus = false;
		}
		
		map.put("fk_tel", search_tel);
		map.put("pk_tel", search_tel);
		
		if(proceStatus){
			try{
				coinUseLogListCount = coinService.getCoinUseLogListCount(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		if(proceStatus && coinUseLogListCount>0){
			try{
				coinUseLogList = coinService.getCoinUseLogList(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myInfo/myPoint/myPointUse");
	
		mav.addObject("search_date", search_date);
		mav.addObject("search_tel", search_tel);
		mav.addObject("coinUserTelList", coinUserTelList);
		mav.addObject("coinUseLogListCount", coinUseLogListCount);
		mav.addObject("coinUseLogList", coinUseLogList);
		
		return mav;		
	}
	
	////////////////////////////////////////////////
	
	@RequestMapping("/ifrmZipcode.do")
	public ModelAndView ifrmZipcode(HttpServletRequest request){				

		ModelAndView mav = new ModelAndView();

		mav.setViewName("myInfo/joinInfo/ifrmZipcode");
		
		return mav;		
	}
	
	
	
	@RequestMapping("/zipSearch.do")
	public ModelAndView zipSearch(HttpServletRequest request){				
		
		System.out.println("###zipSearch");
		
		String searchGubun = request.getParameter("searchGubun") != null ? request.getParameter("searchGubun").trim() : "JEBUN";
		String searchValue = request.getParameter("searchValue") != null ? request.getParameter("searchValue").trim() : "";			
		String doList = request.getParameter("doList") != null ? request.getParameter("doList").trim() : "";
		String tbl_name = "";

		if(searchGubun.length() <= 1){
			searchGubun = "JEBUN";
		}
		System.out.println("###zipSearch searchGubun"+searchGubun);
		System.out.println("###zipSearch searchValue"+searchValue);
		System.out.println("###zipSearch doList"+doList);
		
		ModelAndView mav = new ModelAndView();
		
		//회원 기본정보를 가져온뒤
		mav = joinInfo(request);
		
		
		ZipcodeDoro doroSearch = new ZipcodeDoro();
		List<Zipcode> addrList	= new ArrayList<Zipcode>();
		List<ZipcodeDoro> addrDoroList	= new ArrayList<ZipcodeDoro>();
		
		//검색조건
		if(searchGubun.equals("DORO")){
			
			tbl_name = "tbl_zipcode_" + doList.toLowerCase();
			
			System.out.print("tbl_name="+tbl_name);
			
			//검색조건
			doroSearch.setSearchDoro(searchValue);
			doroSearch.setTblName(tbl_name);
			
			addrDoroList = memberService.searchZipcodeDoro(doroSearch); 
			
			System.out.println("addrDoroList");
/*			System.out.print("addrDoroList.size()="+addrDoroList.size());
			for(int i=0;i<addrDoroList.size();i++){
				System.out.println("addrDoroList.get(i).getPost_num()=" + addrDoroList.get(i).getPost_num());
			}
			*/
			mav.addObject("addrDoroList", addrDoroList);	
			
		}else if(searchGubun.equals("JEBUN")){
			
			addrList = memberService.searchZipcode(searchValue);
			mav.addObject("addrList", addrList);	
		}

		mav.addObject("searchGubun", searchGubun);	
		mav.addObject("searchValue", searchValue);	
		mav.addObject("doList", doList);	
		
		mav.setViewName("myInfo/joinInfo/ifrmZipcode");
		
		//우편번호 목록			
		
		
		
		return mav;		
	}
	
	
	/**
	 * 내정보 > 서비스 취소
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/serviceCancel.do")
	public ModelAndView serviceCancel(HttpServletRequest request) {

		System.out.println("\n[CALL Controller] MyInfoController.serviceCancel()");

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");

		//DB 에서 가져오는 부분 - start
		// tbl_sms_group
		Map<String,String> smsGroupMap = new HashMap<String,String>();
		smsGroupMap.put("fk_member_id", loginInfo.getPk_member_id());
		//getReserve_type

		List<BoardEvent> smsGroupArray = smsManagerService.getSmsReserveGroupList(smsGroupMap);
		
		
		ArrayList<Map<String, String>> smsGroupList = new ArrayList<Map<String, String>>();
		System.out.println("smsGroupArray.size() = "+smsGroupArray.size());

		if(smsGroupArray.size() > 0){
			System.out.println("smsGroupArray.size() = "+smsGroupArray.get(0).getSearch_week());

			for(BoardEvent item : smsGroupArray){
				String reserveTypeTxt = "";
				String reserveTime = "";
				String reserveTimeTxt = "";
				String reserveDayTxt = "";
				String reg_dateTxt = "";
				String msgTypeTxt="";
				String search_customerTxt="";
				String resever_statusTxt="";
				String searchCallRcvCntTypeTxt = "";

				Map<String, String> itemMap = new HashMap<String, String>();

				//발송요청시간
				reserveTime = item.getReserve_time() == null || item.getReserve_time().equals("") ? "" : item.getReserve_time();
				reserveTimeTxt = reserveTime.substring(8, 10)+":"+reserveTime.substring(10, 12);
				
				//발송일
				reserveDayTxt = reserveTime.substring(6, 8);
				
				//요청일
				reg_dateTxt = item.getReg_date().split(" ")[0].replace("-",".");

				reserveTypeTxt 		= "예약발송";
				resever_statusTxt	= "발송예약";
				
				//sms 타입
				if(item.getMsg_type() != null && item.getMsg_sub_type() != null){
					if(item.getMsg_type().equals("1") && item.getMsg_sub_type().equals("1")){
						msgTypeTxt = "SMS";
					}else if(item.getMsg_type().equals("4") && item.getMsg_sub_type().equals("1")){
						msgTypeTxt = "LMS";
					}else if(item.getMsg_type().equals("4") && (Integer.parseInt(item.getMsg_sub_type()) > 1 && Integer.parseInt(item.getMsg_sub_type()) < 5)){
						msgTypeTxt = "MMS";
					}
				}
				

				//수신건수
				String searchCallRcvCnt = item.getSearch_call_rcv_cnt() == null || item.getSearch_call_rcv_cnt().equals("") ? "0": item.getSearch_call_rcv_cnt();

				itemMap.put("reserveDayTxt"					,reserveDayTxt					);		//발송일
				itemMap.put("reg_dateTxt"					,reg_dateTxt					);		//작성시간
				itemMap.put("reserve_time"					,reserveTimeTxt					);		//발송요청 시간 (년월일시분)
				itemMap.put("reserve_type"					,reserveTypeTxt					);		//발송타입 1:즉시, 2:예약
				itemMap.put("search_call_rcv_cnt_type"		,searchCallRcvCntTypeTxt		);		//추출조건 수신횟수 -1:이하 , 1:이상
				itemMap.put("msg_type"						,msgTypeTxt						);		//SMS 타입
				itemMap.put("search_customerTxt"			,search_customerTxt				);		//동보전송 추출조건 고객타입 1:단골고객, 2:신규고객  -1:미사용  구분자:^
				itemMap.put("resever_statusTxt"				,resever_statusTxt				);		//발송상태

				itemMap.put("search_req_cnt"				,item.getSearch_req_cnt()		);		//발송 요청 건수 (원하는건수)
				itemMap.put("search_month"					,item.getSearch_month()			);		//동보전송 추출조건 추출기간 (월)
				itemMap.put("search_call_rcv_cnt"			,searchCallRcvCnt				);		//추출조건 수신 건수
				itemMap.put("fk_member_id"					,item.getFk_member_id()			);		//로그인ID
				itemMap.put("fk_tel"						,item.getFk_tel()				);		//회신 번호 (보내는번호)
				itemMap.put("pk_group_code"					,item.getPk_group_code()		);		//그룹 코드 (생성)
				itemMap.put("custom_msg_id"					,item.getFk_custom_msg_id()		);		//메시지 ID
				itemMap.put("req_count"						,item.getReq_count()			);		//동보 요청 건수

				smsGroupList.add(itemMap);
			}
		}

		//DB 에서 가져오는 부분 - end

		ModelAndView mav = new ModelAndView();
		mav.setViewName("myInfo/serviceCancel/serviceCancel");
		mav.addObject("smsGroupList",smsGroupList);

		return mav;		
		
	}
	
	

	/**
	 * 내정보 > PWD체크
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/serviceCancel.do", params="proc=pwdCkeck")
	public ModelAndView servicePwdCheck(@RequestParam Map<String, String> map, HttpServletRequest request)
	{

		System.out.println("\n[CALL Controller] MyInfoController.goCancel()");

		String script 	= "";
		String pwd 		= request.getParameter("pwd") == null ? "" : request.getParameter("pwd").trim();

		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		Member member = memberService.get(loginInfo.getPk_member_id());

		HashEncrypt encrypt_old = new HashEncrypt(pwd);
		pwd = encrypt_old.getEncryptData();
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println(" .login member.getFd_member_pwd()="+member.getFd_member_pwd()+"/ pwd="+pwd);
		
		if(member != null && member.getFd_member_pwd().equals(pwd))
		{
			script="parent.goCancel();";
		}else{
			script = "alert('비밀번호를 잘못 입력하셨습니다.');";
		}

		System.out.println("script="+script);

		mav.addObject("script", script);
		mav.setViewName("message/returnScript");

		return mav;	

	}
	

	/**
	 * 내정보 > 서비스 취소 예약문자 삭제처리
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/serviceCancel.do", params="proc=cancel")
	public ModelAndView serviceCancelProc(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		SmsManager smsManager = new SmsManager();
		
		//DB 에서 가져오는 부분 - start
		// tbl_sms_group
		Map<String,String> smsGroupMap = new HashMap<String,String>();
		smsGroupMap.put("fk_member_id", loginInfo.getPk_member_id());
		//getReserve_type

		//예약된 그룹 리스트 취득
		List<BoardEvent> smsGroupArray = smsManagerService.getSmsReserveGroupList(smsGroupMap);

		String[] group_code = new String[smsGroupArray.size()];
		
		if(smsGroupArray.size() > 0){
			System.out.println("smsGroupArray.size() = "+smsGroupArray.get(0).getSearch_week());

			int i=0;
			for(BoardEvent item : smsGroupArray){
				group_code[i] = item.getPk_group_code();
				i++;
			}
			
			smsManager.setGroup_code_arry(group_code);
		}
		
		//-------------------
		
		System.out.println("map : " + map);

		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		String nowDate = ft.format(new Date());		
		String rtn_code = "";
		
		smsManager.setFk_member_id(loginInfo.getPk_member_id());
		smsManager.setCancel_time(nowDate);
		smsManager.setCancel_yn("Y");
		
		if(smsManager.getGroup_code_arry()!=null){
			try {
				//코인취소
				rtn_code = smsManagerService.cancelationSms(smsManager);
			} catch (Exception e) {
				// TODO: handle exception
				rtn_code = "301";
				e.printStackTrace();
			}
		}
		
		System.out.println("B1");
		//if(rtn_code.equals("200")){
			try {
				//SMS취소
				rtn_code = smsManagerService.gotoLog(loginInfo.getPk_member_id());
			} catch (Exception e) {
				// TODO: handle exception
				rtn_code = "302";
				e.printStackTrace();
			}
		//}
	
		try {
			//회원 테이블 업데이트
			Member member = new Member();
			member.setPk_member_id(loginInfo.getPk_member_id());
			
			member.setFd_member_state("1302");
			member.setFd_member_end_date(nowDate);
			memberService.modify(member);

		} catch (Exception e) {

			// TODO: handle exception
			rtn_code = "303";
			e.printStackTrace();
		}
		
		String script = "parent.ProcOk("+rtn_code+");";
		ModelAndView mav = new ModelAndView();
		mav.addObject("script", script);
		mav.setViewName("message/returnScript");

		return mav;	

	}
}

