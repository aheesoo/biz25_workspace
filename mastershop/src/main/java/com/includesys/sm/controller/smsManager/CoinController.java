package com.includesys.sm.controller.smsManager;

import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.common.ProfileBean;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.service.myinfo.CoinService;
import com.inicis.inipay.INIpay50;


@Controller
@RequestMapping("/coin")
public class CoinController {

	private static Logger logger = LoggerFactory.getLogger(CoinController.class);
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CoinService coinService; 
	//private EmoticonService emoticonService;
	
	@Autowired
	private ProfileBean profileBean;
	
	/*private String inipayhome = profileBean.getInipayHome();
	private String adminPw = profileBean.getInipayAdminPw();
	private String mid =profileBean.getInipayMid();*/
	
	//private String inipayhome = "D:\\Project\\Eclipse\\ShopMaster\\Web\\INIpay50";
	//private String inipayhome = "/home/smaster/INIpay50";
	//private String adminPw = "kthms12!";      // 상용 : kthms12!    , 개발 : 1111
	
	//private String adminPw = "1111";      // 상용 : kthms12!    , 개발 : 1111
	//private String mid = "mastershop";   // 상용 : mastershop    , 개발 : kthskytest
	//private String mid = "kthskytest";   // 상용 : mastershop    , 개발 : kthskytest
	
	/**
	 * 코인 화면 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/charge.do" )
	public ModelAndView chargeView(HttpServletRequest request)	{
		
		System.out.println("\n[CoinController] CoinController.chargeView()");
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("smsManager/coin/ifrmCharge");
		
	
		return mav;
	}
	
	/**
	 * 코인 충전 화면 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/coin_chargeInfo.do" )
	public ModelAndView coinChargeInfo(HttpServletRequest request)	{		
		System.out.println("\n[CoinController] CoinController.coinChargeInfo()");
		
		String chargeBasePoint 	= request.getParameter("chargeBasePoint")	!=null ? request.getParameter("chargeBasePoint") : "0";
		String chargeBonusPoint 	= request.getParameter("chargeBonusPoint") 	!=null ? request.getParameter("chargeBonusPoint") : "0";
		String chargeMoney 			= request.getParameter("chargeMoney") 		!=null ? request.getParameter("chargeMoney") : "0";
		String goodname 			= request.getParameter("goodname")				!=null ? request.getParameter("goodname") : "";
		String call_page 				= request.getParameter("call_page") 				!=null ? request.getParameter("call_page") : "";
		String product_code 		= request.getParameter("product_code") 		!=null ? request.getParameter("product_code") : "";
	
		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		String member_name = loginInfo.getFd_user_name();
		
		String member_tel = loginInfo.getFd_tel();			// 결제 요청시 전화번호 필요
		String member_id = loginInfo.getPk_member_id();
				
		
		/****************************************
	     * 2. INIpay 인스턴스 생성             *
	     ***************************************/
	    INIpay50 inipay = new INIpay50();
		
	    /***************************************
	     * 3. 암호화 대상/값 설정              *
	     ***************************************/
	    inipay.SetField("inipayhome", profileBean.getInipayHome());      // 이니페이 홈디렉터리(상점수정 필요)
	    inipay.SetField("admin", profileBean.getInipayAdminPw()); 		               // 키패스워드(상점아이디에 따라 변경)
	    //***********************************************************************************************************
	    //* admin 은 키패스워드 변수명입니다. 수정하시면 안됩니다. 1111의 부분만 수정해서 사용하시기 바랍니다.      *
	    //* 키패스워드는 상점관리자 페이지(https://iniweb.inicis.com)의 비밀번호가 아닙니다. 주의해 주시기 바랍니다.*
	    //* 키패스워드는 숫자 4자리로만 구성됩니다. 이 값은 키파일 발급시 결정됩니다.                               *
	    //* 키패스워드 값을 확인하시려면 상점측에 발급된 키파일 안의 readme.txt 파일을 참조해 주십시오.             *
	    //***********************************************************************************************************
	    inipay.SetField("type", "chkfake");                        // 고정 (절대 수정 불가)
	    
	    inipay.SetField("enctype","asym"); 			                    // 고정 (절대 수정 불가) asym:비대칭, symm:대칭
	    inipay.SetField("checkopt", "false"); 		                  // 고정 (절대 수정 불가) base64함:false, base64안함:true
	    inipay.SetField("debug","true");                            // 로그모드("true"로 설정하면 상세로그가 생성됨.)
	    
	    //필수항목 : mid, price, nointerest, quotabase
	    //추가가능 : INIregno, oid
	    //*주의* : 	추가가능한 항목중 암호화 대상항목에 추가한 필드는 반드시 hidden 필드에선 제거하고 
	    //          SESSION이나 DB를 이용해 다음페이지(INIsecureresult.jsp)로 전달/셋팅되어야 합니다.
	    inipay.SetField("mid", profileBean.getInipayMid());                           //상점아이디
	    inipay.SetField("price", chargeMoney);                               // 가격
	    inipay.SetField("nointerest", "no");                            //무이자여부
	    inipay.SetField("quotabase", "lumpsum:00:02:03:04:05:06:07:08:09:10:11:12");  //할부기간
	    String[] parameters = {"price","nointerest", "quotabase"};
	    inipay.SetField("parameters",parameters);
	    
	    /********************************
	     * 4. 암호화 대상/값을 암호화함 *
	     ********************************/

	    inipay.startAction();

	    /*********************
	     * 5. 암호화 결과    *
	     *********************/
	    String rn_resultMsg = "";
	 		if( inipay.GetResult("ResultCode") != "00" ) 
			{
			    rn_resultMsg = inipay.GetResult("ResultMsg");
			}

	    /*********************
	     * 6. 세션정보 저장  *
	     *********************/
	    session.setAttribute("INI_MID"    , inipay.GetResult("mid"));
	    session.setAttribute("INI_RN"     , inipay.GetResult("rn"));
	    session.setAttribute("INI_ENCTYPE", inipay.GetResult("enctype"));
	    session.setAttribute("INI_PRICE"  , inipay.GetResult("price") );
	    session.setAttribute("admin"      , inipay.GetResult("admin"));

	    /*******************************************
	     * 7. 플러그인 전달 정보, hidden field 설정*
	     *******************************************/
	    String ini_encfield = inipay.GetResult("encfield");
	    String ini_certid   = inipay.GetResult("certid");
	   
	    /*********************
	     * 6. 인스턴스 해제  *
	     *********************/
	    inipay = null;	
	    
	    
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/home/coin/popCoinCharge");
		
		mav.addObject("ini_encfield", ini_encfield);
		mav.addObject("ini_certid", ini_certid);
		
		mav.addObject("chargeBasePoint", chargeBasePoint);
		mav.addObject("chargeBonusPoint", chargeBonusPoint);
		mav.addObject("chargeMoney", chargeMoney);
		mav.addObject("goodname", goodname);
		mav.addObject("call_page", call_page);				
		mav.addObject("rn_resultMsg", rn_resultMsg.trim());
		mav.addObject("product_code", product_code);
		mav.addObject("member_name", member_name);
		mav.addObject("member_tel", member_tel);
		mav.addObject("member_id", member_id);
		
		
		return mav;
	}
	

	/**
	 * PG 충전 처리 중 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/childwin.do" )
	public ModelAndView childWinView(HttpServletRequest request)	{
		
		System.out.println("\n[childWinView] CoinController.childWinView()");
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/home/coin/childwin");
		
	
		return mav;
	}
	
	/**
	 * PG 충전 처리 완료
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/coinChargeResult.do" )
	public ModelAndView coinChargePgResult(HttpServletRequest request)	{		
		System.out.println("\n[coinChargeResult] CoinController.coinChargeResult()");
		
		String req_chargeBasePoint 		= request.getParameter("chargeBasePoint")	!= null ? request.getParameter("chargeBasePoint").trim() : "0";
		String req_chargeBonusPoint 	= request.getParameter("chargeBonusPoint")	!= null ? request.getParameter("chargeBonusPoint").trim() : "0";
		String req_chargeMoney 			= request.getParameter("chargeMoney")			!= null ? request.getParameter("chargeMoney").trim() : "0";
		String req_pay_type	 				= request.getParameter("gopaymethod")		!= null ? request.getParameter("gopaymethod").trim() : "0";
		String req_goodname	 			= request.getParameter("goodname")				!= null ? request.getParameter("goodname").trim() : "";
		String req_product_code	 		= request.getParameter("product_code")			!= null ? request.getParameter("product_code").trim() : "";
		String call_page 						= request.getParameter("call_page") 				!=null ? request.getParameter("call_page") : "";
		
		int fd_base_coin = 0;
		int fd_recharge_coin 	= Integer.parseInt(req_chargeBasePoint);
		int fd_bonus_coin 		= Integer.parseInt(req_chargeBonusPoint);
		int fd_total_coin 			= fd_recharge_coin+fd_bonus_coin;
	
		int fd_pay_mount 		= Integer.parseInt(req_chargeMoney);
		
		Coin param = new Coin();
		
		int fd_pay_type = param.changePayType(req_pay_type);
		
		String fk_member_id = "";		// 결제 아이디 저장을 위해 사용
		
		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		fk_member_id = loginInfo.getPk_member_id();
		
		String rtn_code = "200";
		String rtn_msg	 = "";
		int result_total_coin = 0;
		int result_recharge_coin = 0;
		int result_base_coin = 0;
		
		boolean cancelFlag = false; 			// DB 처리로 인한 취소 및 잘못된 값 취소 처리
		
		
		String background_img = "";
		
		String ResultCode 	= "";		// 결과 코드
		String PayMethod 	= "";		// 결제 방법
		String ResultMsg 	= "";		// 결과 메세지 
		String tid				= "";		// 거래 번호 
		String MOID			= "";		// 주문 번호			(무통장시 상품 주문번호)
		String TotPrice		= "";		// 결제완료 금액
		
		String CARD_Num 		= "";		// 신용카드 번호
		String ApplDate 			= "";			// 승일 날짜				(카드승인, 은행결제 계좌, 핸드폰 결제)
		String ApplTime 			= "";			// 승인 시간				(카드승인, 은행결제 계좌, 핸드폰 결제)
		String ApplNum 			= "";			// 승인 번호
		String CARD_Quota 		= "";		// 할부 개월
		String CARD_Interest 	= "";	// 할부 종류
		String EventCode			= "";			// 이벤트형 할부 종류
		String CARD_Interest_Msg = "";												// 이벤트 할부 메세지
	
		String CARD_Code = "";			// 카드 종류
		String CARD_BankCode = "";// 카드 발급사 
		
		String OrgCurrency = "";			// 통 화 코 드 > 통화 코드
		String ExchangeRate = "";			// 통 화 코 드 > 환율
		
		String OCB_Num 				= "";					// OK CASHBAG 적립 및 사용내역 > 카드번호
		String OCB_SaveApplNum = "";		//  OK CASHBAG 적립 및 사용내역 > 카드 승인 번호
		
		String OCB_PayApplNum 	= "";		//  OK CASHBAG 적립 및 사용내역 > 사용 승인 번호
		String OCB_ApplDate 		= "";				//  OK CASHBAG 적립 및 사용내역 > 승일 일시		
		String OCB_PayPrice			= "";			//  OK CASHBAG 적립 및 사용내역 > 포인트 지불 금액
		
		//--  계좌 입급 --//
		String ACCT_BankCode		= "";		// 은행 코드
		String CSHR_ResultCode	= "";		// 현금 영수증 발급 결과 코드
		String CSHR_Type			= "";				// 현금 영수증 발급 구분 코드  0: 소득공제 , 1 : 지출증빙용
		
		//-- 무통장 --//
		String VACT_Num			= "";				// 입금 계좌 번호
		String VACT_BankCode		= "";		// 입금 은행 코드
		String VACT_Name			= "";			// 예금주명
		String VACT_InputName	= "";		// 송금자명
		String VACT_Date			= "";			// 송금 일자
		String VACT_Time			= "";			// 송금 시간
		
		//-- 핸드폰 결제   --//
		
		String HPP_Num 				= "";		//	휴대폰 번호 
		
		//-- 영수증 신청시 필요 
		String ResultErrorCode		= "";		// 에러 결과 값
		String mid 						= "";				// mid 값
		String goodname				= "";				// 상품명
		
		String price						= "";
		String buyerName			= "";
		String buyertel				= "";
		String buyeremail			= "";
		String HPP_GWCode			= "";
		
		boolean chk_status = param.checkValue(req_product_code, req_chargeMoney);
		if(!chk_status){
			cancelFlag = true;
		}
		
		HashMap<String, Object> hmap = new HashMap<>();
		
		hmap.put("fk_member_id", fk_member_id);
		hmap.put("fd_member_id", fk_member_id);
		
		hmap.put("fd_base_coin", fd_base_coin);
		hmap.put("fd_recharge_coin", fd_recharge_coin);
		hmap.put("fd_bonus_coin", fd_bonus_coin);
		hmap.put("fd_total_coin", fd_total_coin);
		
		hmap.put("fd_pay_mount", fd_pay_mount);
		hmap.put("fd_pay_type", fd_pay_type);
		hmap.put("fd_product_code", req_product_code);
		
		
		/***************************************
		  * 2. INIpay 인스턴스 생성             *
		  ***************************************/
		INIpay50 inipay = new INIpay50();
		 
		if(!cancelFlag){
		  /*********************
		   * 3. 지불 정보 설정 *
		   *********************/
	
		  inipay.SetField("inipayhome", profileBean.getInipayHome());  // 이니페이 홈디렉터리(상점수정 필요)
		  inipay.SetField("type", "securepay");  // 고정 (절대 수정 불가)
		  inipay.SetField("admin", session.getAttribute("admin"));  // 키패스워드(상점아이디에 따라 변경)
		  //***********************************************************************************************************
		  //* admin 은 키패스워드 변수명입니다. 수정하시면 안됩니다. 1111의 부분만 수정해서 사용하시기 바랍니다.      *
		  //* 키패스워드는 상점관리자 페이지(https://iniweb.inicis.com)의 비밀번호가 아닙니다. 주의해 주시기 바랍니다.*
		  //* 키패스워드는 숫자 4자리로만 구성됩니다. 이 값은 키파일 발급시 결정됩니다.                               *
		  //* 키패스워드 값을 확인하시려면 상점측에 발급된 키파일 안의 readme.txt 파일을 참조해 주십시오.             *
		  //***********************************************************************************************************
		  inipay.SetField("debug", "true");  // 로그모드("true"로 설정하면 상세로그가 생성됨.)
			
		  inipay.SetField("uid", request.getParameter("uid") );  // INIpay User ID (절대 수정 불가)
		  inipay.SetField("oid", request.getParameter("oid") );  // 상품명 
		  inipay.SetField("goodname", request.getParameter("goodname") );  // 상품명 
		  inipay.SetField("currency", request.getParameter("currency") );  // 화폐단위
	
		  inipay.SetField("mid", session.getAttribute("INI_MID") );  // 상점아이디
		  inipay.SetField("enctype", session.getAttribute("INI_ENCTYPE") );  //웹페이지 위변조용 암호화 정보
		  inipay.SetField("rn", session.getAttribute("INI_RN") );  //웹페이지 위변조용 RN값
		  inipay.SetField("price", session.getAttribute("INI_PRICE") );  //가격
	
	
		  /**---------------------------------------------------------------------------------------
		   * price 등의 중요데이터는
		   * 브라우저상의 위변조여부를 반드시 확인하셔야 합니다.
		   *
		   * 결제 요청페이지에서 요청된 금액과
		   * 실제 결제가 이루어질 금액을 반드시 비교하여 처리하십시오.
		   *
		   * 설치 메뉴얼 2장의 결제 처리페이지 작성부분의 보안경고 부분을 확인하시기 바랍니다.
		   * 적용참조문서: 이니시스홈페이지->가맹점기술지원자료실->기타자료실 의
		   *              '결제 처리 페이지 상에 결제 금액 변조 유무에 대한 체크' 문서를 참조하시기 바랍니다.
		   * 예제)
		   * 원 상품 가격 변수를 OriginalPrice 하고  원 가격 정보를 리턴하는 함수를 Return_OrgPrice()라 가정하면
		   * 다음 같이 적용하여 원가격과 웹브라우저에서 Post되어 넘어온 가격을 비교 한다.
		   *
				String originalPrice = merchant.getOriginalPrice();
				String postPrice = inipay.GetResult("price"); 
				if ( originalPrice != postPrice )
				{
					//결제 진행을 중단하고  금액 변경 가능성에 대한 메시지 출력 처리
					//처리 종료 
				}
			  ---------------------------------------------------------------------------------------**/
	
			  inipay.SetField("paymethod", request.getParameter("paymethod") );			          // 지불방법 (절대 수정 불가)
			  inipay.SetField("encrypted", request.getParameter("encrypted") );			          // 암호문
			  inipay.SetField("sessionkey",request.getParameter("sessionkey") );			        // 암호문
			  inipay.SetField("buyername", request.getParameter("buyername") );			          // 구매자 명
			  inipay.SetField("buyertel", request.getParameter("buyertel") );			            // 구매자 연락처(휴대폰 번호 또는 유선전화번호)
			  inipay.SetField("buyeremail",request.getParameter("buyeremail") );			        // 구매자 이메일 주소
			  inipay.SetField("url", "www.mastershop.kr" ); 	                      // 실제 서비스되는 상점 SITE URL로 변경할것
			  inipay.SetField("cardcode", request.getParameter("cardcode") ); 	          		// 카드코드 리턴
			  inipay.SetField("parentemail", request.getParameter("parentemail") ); 			    // 보호자 이메일 주소(핸드폰 , 전화결제시에 14세 미만의 고객이 결제하면  부모 이메일로 결제 내용통보 의무, 다른결제 수단 사용시에 삭제 가능)
				
			  /*-----------------------------------------------------------------*
			   * 수취인 정보 *                                                   *
			   *-----------------------------------------------------------------*
			   * 실물배송을 하는 상점의 경우에 사용되는 필드들이며               *
			   * 아래의 값들은 INIsecurestart.jsp 페이지에서 포스트 되도록        *
			   * 필드를 만들어 주도록 하십시요.                                  *
			   * 컨텐츠 제공업체의 경우 삭제하셔도 무방합니다.                   *
			   *-----------------------------------------------------------------*/
			  inipay.SetField("recvname",request.getParameter("recvname") );	// 수취인 명
			  inipay.SetField("recvtel",request.getParameter("recvtel") );		// 수취인 연락처
			  inipay.SetField("recvaddr",request.getParameter("recvaddr") );	// 수취인 주소
			  inipay.SetField("recvpostnum",request.getParameter("recvpostnum") );  // 수취인 우편번호
			  inipay.SetField("recvmsg",request.getParameter("recvmsg") );		// 전달 메세지
				
			  inipay.SetField("joincard",request.getParameter("joincard") );        // 제휴카드코드
			  inipay.SetField("joinexpire",request.getParameter("joinexpire") );    // 제휴카드유효기간
			  inipay.SetField("id_customer",request.getParameter("id_customer") );  // 일반적인 경우 사용하지 않음, user_id
	
	
			  /****************
			   * 4. 지불 요청 *
			   ****************/
			 
			  inipay.startAction();
			 
		}
		if(!cancelFlag){
		  
			  //Get PG Added Entity Sample
			  if(inipay.GetResult("ResultCode").equals("00") ){
				  /*-------------------------------------------------------------------------------------------------------
				   * 결제 방법에 따라 상단 이미지가 변경 된다								*
				   * 	 가. 결제 실패 시에 "/resources/images/shopmaster/pg/spool_top.gif" 이미지 사용						*
				   *       가. 결제 방법에 따라 상단 이미지가 변경							*
				   *       	1. 신용카드 	- 	"/resources/images/shopmaster/pg/card.gif"							*
				   *		2. ISP		-	"/resources/images/shopmaster/pg/card.gif"							*
				   *		3. 은행계좌	-	"/resources/images/shopmaster/pg/bank.gif"							*
				   *		4. 무통장입금	-	"/resources/images/shopmaster/pg/bank.gif"							*
				   *		5. 핸드폰	- 	"/resources/images/shopmaster/pg/hpp.gif"							*
				   *		6. 전화결제 (ars전화 결제)	-	"/resources/images/shopmaster/pg/phone.gif"					*
				   *		7. 전화결제 (받는전화결제)	-	"/resources/images/shopmaster/pg/phone.gif"					*
				   *		8. OK CASH BAG POINT		-	"/resources/images/shopmaster/pg/okcash.gif"				*
				   *		9. 문화상품권		-	"/resources/images/shopmaster/pg/ticket.gif"					*
				   *              10. K-merce 상품권 	- 	"/resources/images/shopmaster/pg/kmerce.gif"                                        *
				   *		11. 틴캐시 결제		- 	"/resources/images/shopmaster/pg/teen_top.gif"                                      *
				   *              12. 게임문화 상품권    -       "/resources/images/shopmaster/pg/dgcl_top.gif"                                       *
				   -------------------------------------------------------------------------------------------------------*/
				  background_img = "";
				 
				  if(inipay.GetResult("ResultCode").equals("01")) {
				  		background_img = "/resources/images/shopmaster/pg/spool_top.gif";
				  }else{
				      Hashtable data_bgrImg = new Hashtable();
				  		background_img = "/resources/images/shopmaster/pg/card.gif";    //default image
	
				      try{
				        data_bgrImg.put("Card","/resources/images/shopmaster/pg/card.gif");  //신용카드
				        data_bgrImg.put("VCard","/resources/images/shopmaster/pg/card.gif"); //ISP
				        data_bgrImg.put("HPP","/resources/images/shopmaster/pg/hpp.gif");  //휴대폰
				        data_bgrImg.put("Ars1588Bill","/resources/images/shopmaster/pg/phone.gif");  //1588
				        data_bgrImg.put("PhoneBill","/resources/images/shopmaster/pg/phone.gif");// 폰빌
				        data_bgrImg.put("OCBPoint","/resources/images/shopmaster/pg/okcash.gif");// OKCASHBAG
				        data_bgrImg.put("DirectBank","/resources/images/shopmaster/pg/bank.gif");// 은행계좌이체
				        data_bgrImg.put("VBank","/resources/images/shopmaster/pg/bank.gif");  // 무통장 입금 서비스
				        data_bgrImg.put("Culture","/resources/images/shopmaster/pg/ticket.gif");// 문화상품권 결제
				        data_bgrImg.put("TEEN","/resources/images/shopmaster/pg/teen_top.gif");// 틴캐시 결제
				        data_bgrImg.put("DGCL","/resources/images/shopmaster/pg/dgcl_top.gif");	// 게임문화 상품권
				        data_bgrImg.put("BCSH","/resources/images/shopmaster/pg/ticket_top.gif");	// 도서문화 상품권
				        
				        Object tmp = data_bgrImg.get(inipay.GetResult("PayMethod"));
				        background_img = ( tmp != null)? (String)tmp:background_img;
				      }catch(Exception ex){
				           // default image
				      }
				 }
				
				 ResultCode 	= inipay.GetResult("ResultCode");		// 결과 코드			 
				 ResultMsg 	= inipay.GetResult("ResultMsg");		// 결과 메세지
				 
				 tid				= inipay.GetResult("tid");					// 거래 번호 
				 MOID			= inipay.GetResult("MOID");				// 주문 번호			(무통장시 상품 주문번호)
				 TotPrice		= inipay.GetResult("TotPrice");			// 결제완료 금액
							 
				 ApplDate 			= inipay.GetResult("ApplDate")	;			// 승일 날짜				(카드승인, 은행결제 계좌, 핸드폰 결제)
				 ApplTime 			= inipay.GetResult("ApplTime");			// 승인 시간				(카드승인, 은행결제 계좌, 핸드폰 결제)
				 ApplNum 			= inipay.GetResult("ApplNum");				// 승인 번호 
				 CARD_Num 		= inipay.GetResult("CARD_Num");			// 신용카드 번호
				 PayMethod 	= inipay.GetResult("PayMethod");		// 결제 방법
							 
				 CARD_Quota 		= inipay.GetResult("CARD_Quota");		// 할부 개월
				 CARD_Interest 	= inipay.GetResult("CARD_Interest");		// 할부 종류
				 EventCode			= inipay.GetResult("EventCode");			// 이벤트형 할부 종류
				 CARD_Interest_Msg = "";												// 이벤트 할부 메세지
			
				 CARD_Code = inipay.GetResult("CARD_Code");					// 카드 종류
				 CARD_BankCode = inipay.GetResult("CARD_BankCode");		// 카드 발급사
				 
				 OrgCurrency = inipay.GetResult("OrgCurrency")	;				// 통 화 코 드 > 통화 코드
				 ExchangeRate = inipay.GetResult("ExchangeRate");			// 통 화 코 드 > 환율
				
				 OCB_Num 				= inipay.GetResult("OCB_Num");					// OK CASHBAG 적립 및 사용내역 > 카드번호
				 OCB_SaveApplNum = inipay.GetResult("OCB_SaveApplNum");		//  OK CASHBAG 적립 및 사용내역 > 카드 승인 번호
				
				 OCB_PayApplNum 	= inipay.GetResult("OCB_PayApplNum");		//  OK CASHBAG 적립 및 사용내역 > 사용 승인 번호
				 OCB_ApplDate 		= inipay.GetResult("OCB_ApplDate");				//  OK CASHBAG 적립 및 사용내역 > 승일 일시		
				 OCB_PayPrice			= inipay.GetResult("OCB_PayPrice");				//  OK CASHBAG 적립 및 사용내역 > 포인트 지불 금액
				
				//--  계좌 입급 --//
				 ACCT_BankCode		= inipay.GetResult("ACCT_BankCode");			// 은행 코드
				 CSHR_ResultCode	= inipay.GetResult("CSHR_ResultCode");		// 현금 영수증 발급 결과 코드
				 CSHR_Type			= inipay.GetResult("CSHR_Type");					// 현금 영수증 발급 구분 코드  0: 소득공제 , 1 : 지출증빙용
				
				//-- 무통장 --//
				 VACT_Num			= inipay.GetResult("VACT_Num");				// 입금 계좌 번호
				 VACT_BankCode		= inipay.GetResult("VACT_BankCode");		// 입금 은행 코드
				 VACT_Name			= inipay.GetResult("VACT_Name");			// 예금주명
				 VACT_InputName	= inipay.GetResult("VACT_InputName");		// 송금자명
				 VACT_Date			= inipay.GetResult("VACT_Date");				// 송금 일자
				 VACT_Time			= inipay.GetResult("VACT_Time");				// 송금 시간
				
				//-- 핸드폰 결제   --//
				
				 HPP_Num 				= inipay.GetResult("HPP_Num");		//	휴대폰 번호 
				
				//-- 영수증 신청시 필요 
				 ResultErrorCode		= inipay.GetResult("ResultErrorCode");		// 에러 결과 값
				 mid 						= inipay.GetResult("MID");						// mid 값
				 goodname				= inipay.GetResult("goodname");				// 상품명
				
				 price						= inipay.GetResult("price");
				 buyerName			= inipay.GetResult("buyerName");		
				 buyertel				= inipay.GetResult("buyertel");
				 buyeremail			= inipay.GetResult("buyeremail");
				 HPP_GWCode			= inipay.GetResult("HPP_GWCode");
				
				
				if(CARD_Interest.equals("1")){
					CARD_Interest_Msg = "무이자";
					
				}else if(EventCode.equals("1")){
					CARD_Interest_Msg = "무이자 (이니시스&카드사부담 일반 무이자 할부 이벤트)";
					
				}else if(EventCode.equals("14")){
					CARD_Interest_Msg = "카드사부담 일반 무이자 + 상점 일반 할인 이벤트";
					
				}else if(EventCode.equals("24")){
					CARD_Interest_Msg = "카드사부담 일반 무이자 + 카드 Prefix별 할인 이벤트";
					
				}else if(EventCode.equals("A1")){
					CARD_Interest_Msg = "상점부담 일반 무이자 할부 이벤트";
					
				}else if(EventCode.equals("A2")){
					CARD_Interest_Msg = "상점 일반 할인 이벤트";
					
				}else if(EventCode.equals("A3")){
					CARD_Interest_Msg = "상점 무이자 + 상점 일반 할인 이벤트";
					
				}else if(EventCode.equals("A4")){
					CARD_Interest_Msg = "상점 무이자 + 카드번호별 할인 이벤트";
					
				}else if(EventCode.equals("A5")){
					CARD_Interest_Msg = "카드번호별 할인 이벤트";
					
				}else if(EventCode.equals("B4")){
					CARD_Interest_Msg = "상점 무이자 + 카드 Prefix별 할인 이벤트";
					
				}else if(EventCode.equals("B5")){
					CARD_Interest_Msg = "카드 Prefix별 할인 이벤트";
					
				}else if(EventCode.equals("C0")){
					CARD_Interest_Msg = "당사&카드사부담 특별 무이자 할부 이벤트";
					
				}else if(EventCode.equals("C1")){
					CARD_Interest_Msg = "상점부담 특별 무이자 할부 이벤트";
					
				}else{
					CARD_Interest_Msg = "일반";
				}
			 
				hmap.put("fd_tid", tid);
				hmap.put("fd_result_code", ResultCode);
				hmap.put("fd_result_msg", ResultMsg);
				hmap.put("fd_moid", MOID);		
				hmap.put("fd_appl_date", ApplDate);
				hmap.put("fd_appl_time", ApplTime);
				hmap.put("fd_appl_num", ApplNum);			
				hmap.put("fd_paymethod", PayMethod);
				hmap.put("fd_tot_price", TotPrice);
				hmap.put("fd_card_event_code", EventCode);
				hmap.put("fd_card_event_code_msg", CARD_Interest_Msg);
				hmap.put("fd_card_num", CARD_Num);
				hmap.put("fd_card_interest", CARD_Interest);
				hmap.put("fd_card_quota", CARD_Quota);
				hmap.put("fd_card_code", CARD_Code);
				hmap.put("fd_card_bank_code", CARD_BankCode);
				hmap.put("fd_card_org_currency", OrgCurrency);
				hmap.put("fd_card_exchangerate", ExchangeRate);
				hmap.put("fd_ocb_num", OCB_Num);
				hmap.put("fd_ocb_svae_appl_num", OCB_SaveApplNum);
				hmap.put("fd_ocb_pay_appl_num", OCB_PayApplNum);
				hmap.put("fd_ocb_appl_date", OCB_ApplDate);
				hmap.put("fd_ocb_pay_price", OCB_PayPrice);
				hmap.put("fd_acct_bank_code", ACCT_BankCode);
				hmap.put("fd_cshr_result_code", CSHR_ResultCode);			 
				hmap.put("fd_cshr_type", CSHR_Type);
				hmap.put("fd_vact_num", VACT_Num);
				hmap.put("fd_vact_bank_code", VACT_BankCode);
				hmap.put("fd_vact_name", VACT_Name);
				hmap.put("fd_vact_input_name", VACT_InputName);			 
				hmap.put("fd_vact_date", VACT_Date);
				hmap.put("fd_vact_time", VACT_Time);
				hmap.put("fd_hpp_num", HPP_Num);
				 
				try{
					int result = coinService.insertCoinChargePgProc(hmap);
							
					if(result>0){
						
						HashMap<String, String> getMap = new HashMap<>();
						getMap.put("member_id", fk_member_id);
						Coin coin = coinService.getCoin(getMap);
						
						if(coin!=null){
							result_total_coin = coin.getFd_total_coin();
							result_recharge_coin = (coin.getFd_recharge_coin() +coin.getFd_bonus_coin());
							result_base_coin = coin.getFd_base_coin();
							
							
						}
						cancelFlag = false;
						
					}else{
						rtn_code = "310";
						rtn_msg = "코인 로그 인서트 정상처리 에러.";
						cancelFlag =true;	// 로그 처리 오류
					}					
				}catch(Exception e){
					rtn_code = "300";
					rtn_msg = "코인 로그 인서트 에러";
					cancelFlag =true;	// 로그 처리 오류
					e.printStackTrace();
					
				}
				
			  }else{
				  cancelFlag =true; // 에러
				  background_img = "/resources/images/shopmaster/pg/spool_top.gif";
				  ResultCode 	= inipay.GetResult("ResultCode");		// 결과 코드			 
				  ResultMsg 	= inipay.GetResult("ResultMsg");		// 결과 메세지
				  tid				= inipay.GetResult("tid");					// 거래 번호 
			  }
		  }
		  /*****************
		   * 5. 결제  결과 *
		   *****************/
		  /*****************************************************************************************************************
		   *  1 모든 결제 수단에 공통되는 결제 결과 데이터
		   * 	거래번호 : inipay.GetResult("tid")
		   * 	결과코드 : inipay.GetResult("ResultCode") ("00"이면 지불 성공)
		   * 	결과내용 : inipay.GetResult("ResultMsg") (지불결과에 대한 설명)
		   * 	지불방법 : inipay.GetResult("PayMethod") (매뉴얼 참조)
		   * 	상점주문번호 : inipay.GetResult("MOID")
		   *	결제완료금액 : inipay.GetResult("TotPrice")
		   * 	이니시스 승인날짜 : inipay.GetResult("ApplDate") (YYYYMMDD)
		   * 	이니시스 승인시각 : inipay.GetResult("ApplTime") (HHMMSS)  
		   *
		   *
		   * 결제 되는 금액 =>원상품가격과  결제결과금액과 비교하여 금액이 동일하지 않다면
		   * 결제 금액의 위변조가 의심됨으로 정상적인 처리가 되지않도록 처리 바랍니다. (해당 거래 취소 처리)
		   *
		   *  2. 일부 결제 수단에만 존재하지 않은 정보,
		   *     OCB Point/VBank 를 제외한 지불수단에 모두 존재.
		   * 	승인번호 : inipay.GetResult("ApplNum") 
		   *
		   *
		   *  3. 신용카드 결제 결과 데이터 (Card, VCard 공통)
		   * 	할부기간 : inipay.GetResult("CARD_Quota")
		   * 	무이자할부 여부 : inipay.GetResult("CARD_Interest") ("1"이면 무이자할부), 
		   *                    또는 inipay.GetResult("EventCode") (무이자/할인 행사적용 여부, 값에 대한 설명은 메뉴얼 참조)
		   * 	신용카드사 코드 : inipay.GetResult("CARD_Code") (매뉴얼 참조)
		   * 	카드발급사 코드 : inipay.GetResult("CARD_BankCode") (매뉴얼 참조)
		   * 	본인인증 수행여부 : inipay.GetResult("CARD_AuthType") ("00"이면 수행)
		   *  각종 이벤트 적용 여부 : inipay.GetResult("EventCode")
		   *
		   *
		   *      ** 달러결제 시 통화코드와  환률 정보 **
		   *	해당 통화코드 : inipay.GetResult("OrgCurrency")
		   *	환율 : inipay.GetResult("ExchangeRate")
		   *
		   *      아래는 "신용카드 및 OK CASH BAG 복합결제" 또는"신용카드 지불시에 OK CASH BAG적립"시에 추가되는 데이터
		   * 	OK Cashbag 적립 승인번호 : inipay.GetResult("OCB_SaveApplNum")
		   * 	OK Cashbag 사용 승인번호 : inipay.GetResult("OCB_PayApplNum")
		   * 	OK Cashbag 승인일시 : inipay.GetResult("OCB_ApplDate") (YYYYMMDDHHMMSS)
		   * 	OCB 카드번호 : inipay.GetResult("OCB_Num")
		   * 	OK Cashbag 복합결재시 신용카드 지불금액 : inipay.GetResult("CARD_ApplPrice")
		   * 	OK Cashbag 복합결재시 포인트 지불금액 : inipay.GetResult("OCB_PayPrice")
		   *
		   * 4. 실시간 계좌이체 결제 결과 데이터
		   *
		   * 	은행코드 : inipay.GetResult("ACCT_BankCode")
		   *	현금영수증 발행결과코드 : inipay.GetResult("CSHR_ResultCode")
		   *	현금영수증 발행구분코드 : inipay.GetResult("CSHR_Type")
		   *
		   * 5. OK CASH BAG 결제수단을 이용시에만  결제 결과 데이터
		   * 	OK Cashbag 적립 승인번호 : inipay.GetResult("OCB_SaveApplNum")
		   * 	OK Cashbag 사용 승인번호 : inipay.GetResult("OCB_PayApplNum")
		   * 	OK Cashbag 승인일시 : inipay.GetResult("OCB_ApplDate") (YYYYMMDDHHMMSS)
		   * 	OCB 카드번호 : inipay.GetResult("OCB_Num")
		   *
		   * 6. 무통장 입금 결제 결과 데이터
		   * 	가상계좌 채번에 사용된 주민번호 : inipay.GetResult("VACT_RegNum")
		   * 	가상계좌 번호 : inipay.GetResult("VACT_Num")
		   * 	입금할 은행 코드 : inipay.GetResult("VACT_BankCode")
		   * 	입금예정일 : inipay.GetResult("VACT_Date") (YYYYMMDD)
		   * 	송금자 명 : inipay.GetResult("VACT_InputName")
		   * 	예금주 명 : inipay.GetResult("VACT_Name")
		   *
		   * 7. 핸드폰, 전화 결제 결과 데이터( "실패 내역 자세히 보기"에서 필요 , 상점에서는 필요없는 정보임)
		   * 	전화결제 사업자 코드 : inipay.GetResult("HPP_GWCode")
		   *
		   * 8. 핸드폰 결제 결과 데이터
		   * 	휴대폰 번호 : inipay.GetResult("HPP_Num") (핸드폰 결제에 사용된 휴대폰번호)
		   *
		   * 9. 전화 결제 결과 데이터
		   * 	전화번호 : inipay.GetResult("ARSB_Num") (전화결제에  사용된 전화번호)
		   *
		   * 10. 문화 상품권 결제 결과 데이터
		   * 	컬쳐 랜드 ID : inipay.GetResult("CULT_UserID")
		   *
		   * 11. 현금영수증 발급 결과코드 (은행계좌이체시에만 리턴)
		   *    inipay.GetResult("CSHR_ResultCode")
		   *
		   * 12.틴캐시 잔액 데이터
		   *    inipay.GetResult("TEEN_Remains")
		   *  틴캐시 ID : inipay.GetResult("TEEN_UserID")
		   *
		   * 13.게임문화 상품권
		   *	사용 카드 갯수 : inipay.GetResult("GAMG_Cnt")
		   *
		   * 14.도서문화 상품권
		   *	사용자 ID : inipay.GetResult("BCSH_UserID")
		   *
		   ****************************************************************************************************************/


		  /*******************************************************************
		   * 7. DB연동 실패 시 강제취소                                      *
		   *                                                                 *
		   * 지불 결과를 DB 등에 저장하거나 기타 작업을 수행하다가 실패하는  *
		   * 경우, 아래의 코드를 참조하여 이미 지불된 거래를 취소하는 코드를 *
		   * 작성합니다.                                                     *
		   *******************************************************************/
		 
		 
		  // cancelFlag를 "ture"로 변경하는 condition 판단은 개별적으로
		  // 수행하여 주십시오.

		  if(cancelFlag){
		    String tmp_TID = inipay.GetResult("tid");
		    inipay.SetField("type", "cancel");         // 고정
		    inipay.SetField("tid", tmp_TID);              // 고정
		    inipay.SetField("cancelmsg", "DB FAIL");   // 취소사유
		    inipay.startAction();
		    
		    try{
		    	
		    	if(rtn_code.equals("200")){
		    		rtn_code = "400";
		    	}
		    	HashMap<String, Object> f_hmap = new HashMap<>();
		    	if(rtn_msg.equals("")){
		    		rtn_msg = ResultMsg;
		    	}
		    	f_hmap.put("fd_member_id" , fk_member_id);
		    	f_hmap.put("fd_result_code" , rtn_code);
		    	f_hmap.put("fd_result_msg" , rtn_msg);
		    	f_hmap.put("fd_tid" , tmp_TID);
		    	
		    	coinService.insertCoinChargePgFailLog(f_hmap);
		    	
		    }catch(Exception e){
		    	rtn_code = "320";
				rtn_msg = "충전 실패";
		    	e.printStackTrace();
		    }
		  }
		 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/home/coin/chargeResult");
		
		mav.addObject("inipay"					, inipay);
		mav.addObject("background_img"		, background_img);
		mav.addObject("ResultCode"				, ResultCode);
		mav.addObject("PayMethod"				, PayMethod);
		mav.addObject("ResultMsg"				, ResultMsg);
		mav.addObject("tid"							, tid);
		mav.addObject("MOID"						, MOID);
		mav.addObject("TotPrice"					, TotPrice);
		
		mav.addObject("CARD_Num"				, CARD_Num);
		mav.addObject("ApplDate"				, ApplDate);
		mav.addObject("ApplTime"				, ApplTime);
		mav.addObject("ApplNum"				, ApplNum);
		mav.addObject("CARD_Quota"			, CARD_Quota);
		mav.addObject("CARD_Interest"		, CARD_Interest);
		mav.addObject("EventCode"				, EventCode);
		mav.addObject("CARD_Interest_Msg"	, CARD_Interest_Msg);
		mav.addObject("CARD_Code"			, CARD_Code);
		mav.addObject("CARD_BankCode"		, CARD_BankCode);
		mav.addObject("OrgCurrency"			, OrgCurrency);
		
		mav.addObject("ExchangeRate"			, ExchangeRate);
		mav.addObject("OCB_Num"				, OCB_Num);
		mav.addObject("OCB_SaveApplNum"	, OCB_SaveApplNum);
		mav.addObject("OCB_PayApplNum"	, OCB_PayApplNum);
		mav.addObject("OCB_ApplDate"		, OCB_ApplDate);
		mav.addObject("OCB_PayPrice"			, OCB_PayPrice);
		
		mav.addObject("ACCT_BankCode"		, ACCT_BankCode);
		mav.addObject("CSHR_ResultCode"	, CSHR_ResultCode);
		mav.addObject("CSHR_Type"				, CSHR_Type);
		
		mav.addObject("VACT_Num"				, VACT_Num);
		mav.addObject("VACT_BankCode"		, VACT_BankCode);
		mav.addObject("VACT_Name"			, VACT_Name);
		mav.addObject("VACT_InputName"	, VACT_InputName);
		mav.addObject("VACT_Date"				, VACT_Date);
		mav.addObject("VACT_Time"				, VACT_Time);
		mav.addObject("HPP_Num"				, HPP_Num);
			
		mav.addObject("result_total_coin"			, result_total_coin);
		mav.addObject("result_recharge_coin"	, result_recharge_coin);
		mav.addObject("result_base_coin"			, result_base_coin);
		mav.addObject("rtn_code"					, rtn_code);
		mav.addObject("ResultErrorCode"			, ResultErrorCode);
		mav.addObject("mid"							, mid);
		mav.addObject("goodname"					, goodname);
		
		mav.addObject("price"							, price);
		mav.addObject("buyerName"					, buyerName);
		mav.addObject("buyertel"						, buyertel);
		mav.addObject("buyeremail"					, buyeremail);
		mav.addObject("HPP_GWCode"				, HPP_GWCode);
		mav.addObject("call_page"					, call_page);
		mav.addObject("result_total_coin"			, result_total_coin);
		mav.addObject("result_recharge_coin"	, result_recharge_coin);
		mav.addObject("result_base_coin"			, result_base_coin);
		return mav;
	}
	
	
	/**
	 * 코인 화면 팝업 요청
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/popCharge.do" )
	public ModelAndView popMainchargeView(HttpServletRequest request)	{
		
		System.out.println("\n[CoinController] CoinController.popMainchargeView()");
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("smsManager/coin/popCoinMain");
		
	
		return mav;
	}
}
