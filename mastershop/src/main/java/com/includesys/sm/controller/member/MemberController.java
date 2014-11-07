package com.includesys.sm.controller.member;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Code;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.member.Zipcode;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.util.HashEncrypt;
import com.includesys.sm.util.OpenapiAesSecure;
import com.includesys.sm.webUtil.PageNavi;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	
	/**
	 * 회원등록 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/memberRegister.do" )
	public ModelAndView memberRegister(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MemberController.memberRegister()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberRegister");
		List<Code> gubun = memberService.getGubun(); //회원상태 구분	
		mav.addObject("gubun", gubun);		
		return mav;		
	}
	
	/**
	 * 회원 조회
	 * @param request
	 * @return
	 */
	@RequestMapping("/memberSearch.do")
	public ModelAndView memberSearch(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.memberSearch()");
		
		//수정OK
		int page, pageSize		= 10;
		String searchColumn		= request.getParameter("searchColumn")	!= null ? request.getParameter("searchColumn").trim() : "";
		String searchString		= request.getParameter("searchString")	!= null ? request.getParameter("searchString").trim() : "";
		page					= Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page").trim() : "1");
		
		String state 			= request.getParameter("state") 		!= null ? request.getParameter("state").trim() : "";
		String start_ymd 		= request.getParameter("start_ymd") 	!= null ? request.getParameter("start_ymd").trim().replace("-", "") : "";
		String finish_ymd 		= request.getParameter("finish_ymd") 	!= null ? request.getParameter("finish_ymd").trim().replace("-", "") : "";
		String start_hour		= request.getParameter("start_hour") 	!= null ? request.getParameter("start_hour").trim() : "";
		String finish_hour		= request.getParameter("finish_hour") 	!= null ? request.getParameter("finish_hour").trim() : "";
		String start_minute		= request.getParameter("start_minute") 	!= null ? request.getParameter("start_minute").trim() : "";
		String finish_minute	= request.getParameter("finish_minute") != null ? request.getParameter("finish_minute").trim() : "";
		String start_date		= start_ymd + start_hour + start_minute;
		String finish_date		= finish_ymd + finish_hour + finish_minute;
		
		ModelAndView mav = new ModelAndView();
		List<Code> gubun = memberService.getGubun(); //회원상태 구분 (뎁스표시 필요)
		mav.addObject("gubun", gubun);	
		mav.setViewName("/member/memberSearch");

		return mav;		
	}
	
	

	/**
	 * 회원 상세 (가입정보 메인)
	 * @param request
	 * @return
	 */
	@RequestMapping("/memberDetail.do")
	public ModelAndView memberDetail(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.memberDetail()");
		
		//수정OK
		int page, pageSize		= 10;
		String searchColumn		= request.getParameter("searchColumn")	!= null ? request.getParameter("searchColumn").trim() : "";
		String searchString		= request.getParameter("searchString")	!= null ? request.getParameter("searchString").trim() : "";
		page					= Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page").trim() : "1");
		
		String state 			= request.getParameter("state") 		!= null ? request.getParameter("state").trim() : "";
		String start_ymd 		= request.getParameter("start_ymd") 	!= null ? request.getParameter("start_ymd").trim().replace("-", "") : "";
		String finish_ymd 		= request.getParameter("finish_ymd") 	!= null ? request.getParameter("finish_ymd").trim().replace("-", "") : "";
		String start_hour		= request.getParameter("start_hour") 	!= null ? request.getParameter("start_hour").trim() : "";
		String finish_hour		= request.getParameter("finish_hour") 	!= null ? request.getParameter("finish_hour").trim() : "";
		String start_minute		= request.getParameter("start_minute") 	!= null ? request.getParameter("start_minute").trim() : "";
		String finish_minute	= request.getParameter("finish_minute") != null ? request.getParameter("finish_minute").trim() : "";
		String start_date		= start_ymd + start_hour + start_minute;
		String finish_date		= finish_ymd + finish_hour + finish_minute;
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberDetail");

		return mav;		
	}
	
	/**
	 * 내정보 > 가입정보
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/memberJoinInfo.do")
	public ModelAndView getMemberJoinInfo(HttpServletRequest request, @RequestParam Map<String, Object> map){
		System.out.println("[CALL Member Controller ] getMemberJoinInfo");
		
		boolean skip_process = true;
		String script = "";
		
		Member contents = null;
		LoginInfo loginInfo = null;
		
		try {
			loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		} catch (Exception e) {
			e.printStackTrace();
			skip_process = false;
		}
		
		if(skip_process){
			try {
				contents = memberService.getJoinInfo(loginInfo.getPk_member_id());
			} catch (Exception e) {
				e.printStackTrace();
				skip_process = false;
			}
		}
		
		
		if(skip_process == false){
			script = "alert('가입정보를 가지고 오는 도중 에러가 발생 하였습니다.');";
		}
		
		ModelAndView mav = new ModelAndView("/member/memberJoinInfo");
		mav.addObject("contents", contents);
		mav.addObject("script", script);
		return mav;
	}
	
	/**
	 * 내정보 > 가입정보 > 수정
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/memberJoinInfo.do", params="event=modify")
	public ModelAndView getMemberJoinInfoModify(HttpServletRequest request, @RequestParam Map<String, Object> map){
		System.out.println("[CALL Member Controller ] getMemberJoinInfoModify");
		
		boolean skip_process = true;
		String script = "";
		
		Member contents = null;
		LoginInfo loginInfo = null;
		
		try {
			loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		} catch (Exception e) {
			e.printStackTrace();
			skip_process = false;
			script = "alert('가입정보를 가지고 오는 도중 에러가 발생 하였습니다.');";
		}
		
		if(skip_process){
			try {
				contents = memberService.getJoinInfo(loginInfo.getPk_member_id());
			} catch (Exception e) {
				e.printStackTrace();
				skip_process = false;
				script = "alert('가입정보를 가지고 오는 도중 에러가 발생 하였습니다.');";
			}
		}
		
		ModelAndView mav = new ModelAndView("/member/memberJoinInfoModify");
		mav.addObject("contents", contents);
		mav.addObject("script", script);
		return mav;
	}
	
	/**
	 * 내정보 > 포인트 충전 내역
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/memberPointChargeLog.do")
	public ModelAndView getMemberPointChargeLog(HttpServletRequest request, @RequestParam Map<String, Object> map){
		System.out.println("[CALL Member Controller ] getMemberPointChargeLog");
		
		boolean skip_process = true;
		String script = "";
		
		List<Map<String, Object>> contents_list = null;
		LoginInfo loginInfo = null;
		
		try {
			loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		} catch (Exception e) {
			e.printStackTrace();
			skip_process = false;
			script = "alert('포인트 충전내역을 가지고 오는 도중 에러가 발생 하였습니다.');";
		}
		
		if(skip_process){
			try {
				contents_list = memberService.getPointChargeLogList(loginInfo.getPk_member_id());
			} catch (Exception e) {
				e.printStackTrace();
				skip_process = false;
				script = "alert('포인트 충전내역을 가지고 오는 도중 에러가 발생 하였습니다.');";
			}
		}
		
		ModelAndView mav = new ModelAndView("/member/memberPointChargeLog");
		mav.addObject("script", script);
		mav.addObject("contents_list", contents_list);
		return mav;
	}
	

	/**
	 * 고객 컨설팅 (iframe)
	 * @param request
	 * @return
	 */
	@RequestMapping("/ifrmDetailConsult.do")
	public ModelAndView ifrmDetailConsult(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.ifrmDetailConsult()");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/ifrmDetailConsult");

		return mav;		
	}
	
	/**
	 * 문사 사용내역 (iframe)
	 * @param request
	 * @return
	 */
	@RequestMapping("/ifrmDetailSms.do")
	public ModelAndView ifrmDetailSms(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.ifrmDetailSms()");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/ifrmDetailSms");

		return mav;		
	}
	
	/**
	 * 포인트 충전내역 (iframe)
	 * @param request
	 * @return
	 */
	@RequestMapping("/ifrmDetailPoint.do")
	public ModelAndView ifrmDetailPoint(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.ifrmDetailPoint()");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/ifrmDetailPoint");

		return mav;		
	}
	
	/**
	 * 요금 명세서 (iframe)
	 * @param request
	 * @return
	 */
	@RequestMapping("/ifrmDetailPrice.do")
	public ModelAndView ifrmDetailPrice(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.ifrmDetailPrice()");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/ifrmDetailPrice");

		return mav;		
	}
	
	

	/**
	 * 회원 등록 처리
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/memberRegisterProc.do" )
	public ModelAndView memberRegisterProc(HttpServletRequest request, 
										HttpServletResponse response,
										@RequestParam("fd_file1") MultipartFile mFd_file1,
										@RequestParam("fd_file2") MultipartFile mFd_file2,
										@RequestParam("fd_user_access_file1") MultipartFile mFd_file3) {
		
		System.out.println("\n[CALL Controller] MemberController.memberRegister()");

		//parameter
		String pk_member_id		   		= request.getParameter("pk_member_id") != null			? request.getParameter("pk_member_id") : ""; 		//아이디
        String fd_member_pwd            = request.getParameter("fd_member_pwd") != null			? request.getParameter("fd_member_pwd") : ""; 		//비밀번호

		String fd_tel1					= request.getParameter("fd_tel1") != null 				? request.getParameter("fd_tel1").trim() +"-": "";
		String fd_tel2					= request.getParameter("fd_tel2") != null 				? request.getParameter("fd_tel2").trim() +"-" : "";
		String fd_tel3					= request.getParameter("fd_tel3") != null 				? request.getParameter("fd_tel3").trim() : "";
		String fd_tel					= fd_tel1+fd_tel2+fd_tel3;
        
		String fd_mobile1				= request.getParameter("fd_mobile1") != null 			? request.getParameter("fd_mobile1").trim() +"-" : "";
		String fd_mobile2				= request.getParameter("fd_mobile2") != null 			? request.getParameter("fd_mobile2").trim() +"-" : "";
		String fd_mobile3				= request.getParameter("fd_mobile3") != null 			? request.getParameter("fd_mobile3").trim() : "";
		String fd_mobile				= fd_mobile1+fd_mobile2+fd_mobile3;
		
        String fd_user_name             = request.getParameter("fd_user_name") != null			? request.getParameter("fd_user_name") : ""; 		//상호.성명
        String fd_business_type         = request.getParameter("fd_business_type") != null 		? request.getParameter("fd_business_type") : ""; 	//업종
        String fd_shop_name             = request.getParameter("fd_shop_name") != null			? request.getParameter("fd_shop_name") : ""; 		//상호.성명
        String fd_addr                  = request.getParameter("fd_addr") != null				? request.getParameter("fd_addr") : ""; 			//주소
        String fd_addr_detail           = request.getParameter("fd_addr_detail") != null 		? request.getParameter("fd_addr_detail") : ""; 		//주소 상세
		String fd_post_num1				= request.getParameter("fd_post_num1") != null 			? request.getParameter("fd_post_num1").trim() : "";
		String fd_post_num2				= request.getParameter("fd_post_num2") != null 			? request.getParameter("fd_post_num2").trim() : "";
		String fd_post_num				= fd_post_num1 + fd_post_num2;
	    
		String fd_regist_num1            = request.getParameter("fd_regist_num1") != null 		? request.getParameter("fd_regist_num1") : ""; 		//주민(법인)등록번호
		String fd_regist_num2            = request.getParameter("fd_regist_num2") != null 		? request.getParameter("fd_regist_num2") : ""; 		//주민(법인)등록번호
		String fd_regist_num			= fd_regist_num1 +"-"+ fd_regist_num2;
        
	    String fd_corp_regist_num1       = request.getParameter("fd_corp_regist_num1") != null 	? request.getParameter("fd_corp_regist_num1") : ""; 	//사업자등록번호
	    String fd_corp_regist_num2       = request.getParameter("fd_corp_regist_num2") != null 	? request.getParameter("fd_corp_regist_num2") : ""; 	//사업자등록번호
	    String fd_corp_regist_num       = fd_corp_regist_num1+"-"+fd_corp_regist_num2;
	    
        String fd_user_access           = request.getParameter("fd_user_access") != null		? request.getParameter("fd_user_access") : ""; 		//회원징계상태 활성/비활성
        String fd_user_access_cont      = request.getParameter("fd_user_access_cont") != null	? request.getParameter("fd_user_access_cont") : ""; //징계내용
        String fd_openapi_use           = request.getParameter("fd_openapi_use") != null		? request.getParameter("fd_openapi_use") : ""; 			//통화openapi 가입상태
        String fd_openapi_reg_date      = request.getParameter("fd_openapi_reg_date") != null	? request.getParameter("fd_openapi_reg_date") : ""; 	//통화오픈api 가입일
        String fd_openapi_cancel_date   = request.getParameter("fd_openapi_cancel_date") != null ? request.getParameter("fd_openapi_cancel_date") : ""; //통화오픈api 탈퇴일
        String fd_openapi_upd_date      = request.getParameter("fd_openapi_upd_date") != null	? request.getParameter("fd_openapi_upd_date") : ""; 	//통화오픈api 갱신일자(매일 갱신)

		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");		
		String fd_reg_date =  ft.format(new Date( ));

		Member member = new Member();
		member.setFd_addr(fd_addr);
		member.setFd_addr_detail(fd_addr_detail);
		member.setFd_business_type(fd_business_type);
		member.setFd_corp_regist_num(fd_corp_regist_num);
		member.setFd_mobile(fd_mobile);
		member.setFd_post_num(fd_post_num);
		member.setFd_reg_date(fd_reg_date);
		member.setFd_regist_num(fd_regist_num);
		member.setFd_shop_name(fd_shop_name);
		member.setFd_tel(fd_tel);
		member.setFd_user_access(fd_user_access);
		member.setFd_user_access_cont(fd_user_access_cont);
		member.setFd_user_name(fd_user_name);
		member.setPk_member_id(pk_member_id);
		member.setFd_member_pwd(fd_member_pwd);
		
		
		//========================================
		///// 1.전송된 파일을 서버에 업로드 (파일복사)
		//----------------------------------------
		//실제 서버의 경로 얻어오기 (절대경로)
		String path = request.getSession().getServletContext().getRealPath("/resources/upload/profile/");

		//전송된 파일명 취득
		String orgfilename1 = mFd_file1.getOriginalFilename();
		String ext1 = orgfilename1.substring(orgfilename1.lastIndexOf(".")+1,orgfilename1.length());
		
		String orgfilename2 = mFd_file2.getOriginalFilename();
		String ext2 = orgfilename2.substring(orgfilename2.lastIndexOf(".")+1,orgfilename2.length());
		
		String orgfilename3 = mFd_file3.getOriginalFilename();
		String ext3 = orgfilename3.substring(orgfilename3.lastIndexOf(".")+1,orgfilename3.length());
		

		//중복되지 않는 파일명 생성하기
		String randomUUID = ""+UUID.randomUUID();
		String savefilename1 = randomUUID+"_A1_"+pk_member_id;
		savefilename1 = savefilename1+"."+ext1;
		
		String savefilename2 = randomUUID+"_A2_"+pk_member_id;
		savefilename2 = savefilename2+"."+ext2;
		
		String savefilename3 = randomUUID+"_B1_"+pk_member_id;
		savefilename3 = savefilename3+"."+ext3;
		
		
//		@RequestParam("fd_file1") MultipartFile mFd_file1,
//		@RequestParam("fd_file2") MultipartFile mFd_file2,
//		@RequestParam("fd_user_access_file1") MultipartFile mFd_user_access_file1) {

		try {
			//전송된 파일 서버에 복사하기
			InputStream is = mFd_file1.getInputStream();
			//서버에 복사(출력)하기 위한 스트림 객체
			FileOutputStream fos = new FileOutputStream(path + "/"+ savefilename1);
			//파일 복사하기
			FileCopyUtils.copy(is, fos);
			is.close();
			fos.close();
			System.out.println("FileUpload1 Success! "+orgfilename1+" / "+savefilename1);
		} catch (Exception e) {
			System.out.println("FileUpload1 Fail! "+e.getMessage());
		}
		
		try {
			//전송된 파일 서버에 복사하기
			InputStream is = mFd_file2.getInputStream();
			//서버에 복사(출력)하기 위한 스트림 객체
			FileOutputStream fos = new FileOutputStream(path + "/"+ savefilename2);
			//파일 복사하기
			FileCopyUtils.copy(is, fos);
			is.close();
			fos.close();
			System.out.println("FileUpload2 Success! "+orgfilename2+" / "+savefilename2);
		} catch (Exception e) {
			System.out.println("FileUpload2 Fail! "+e.getMessage());
		}
		
		try {
			//전송된 파일 서버에 복사하기
			InputStream is = mFd_file3.getInputStream();
			//서버에 복사(출력)하기 위한 스트림 객체
			FileOutputStream fos = new FileOutputStream(path + "/"+ savefilename3);
			//파일 복사하기
			FileCopyUtils.copy(is, fos);
			is.close();
			fos.close();
			System.out.println("FileUpload3 Success! "+orgfilename1+" / "+savefilename3);
		} catch (Exception e) {
			System.out.println("FileUpload3 Fail! "+e.getMessage());
		}
		
		member.setFd_file1(savefilename1);
		member.setFd_file2(savefilename2);
		member.setFd_user_access_file1(savefilename3);
		
		//File file = new File(path + File.separator + savefilename1); //File.separator --> \ or / 디렉토리 구분자들어감
		//long filesize = file.length(); //파일사이즈

		String script = "";
		
		try	{ 
			
			System.out.println("Before register");
			memberService.register(member);	//등록
			System.out.println("After register");
			
			script="alert('회원정보 등록이 완료 되었습니다.');";
			script += "location.href='/member/memberRegister.do;";
			
		}catch(Exception ex){
			
			System.out.println("Exception");
			logger.error(ex.getMessage());					
			script="alert('회원정보 등록이 되지 않았습니다.\n\n관리자에게 문의 바랍니다.');";
			script+="location.href='/member/memberRegister.do;";
		}

		System.out.println("MemberController.memberRegister 1");
		ModelAndView mav = new ModelAndView();
		System.out.println("MemberController.memberRegister 2");
		mav.setViewName("member/memberRegister");		
		mav.addObject("script",script);
		System.out.println("MemberController.memberRegister 3");
		return mav;
	}
	
	

	/**
	 * 통화오픈api 사용자 id, pwd업데이트 처리 (정상 로그인시)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ifrmOpenApiUpdInfo.do")
	public ModelAndView openApiUpdInfo(HttpServletRequest request, @RequestParam Map<String, Object> map){
		System.out.println("[CALL Member Controller ] openApiUpdInfo");
		
		String script = "";
		
		String fd_openapi_member_id		= request.getParameter("loginID") == null ? "" : request.getParameter("loginID").trim();
		String fd_openapi_member_pwd	= request.getParameter("loginPWD") == null ? "" : request.getParameter("loginPWD").trim();
		String fd_openapi_autologin		= request.getParameter("fd_openapi_autologin") == null ? "" : request.getParameter("fd_openapi_autologin").trim();
		if(!fd_openapi_autologin.equals("T")){
			fd_openapi_autologin = "F";
		}
		
		//openapi pwd 암호화
		OpenapiAesSecure openapiAes = new OpenapiAesSecure();
		try {
			fd_openapi_member_pwd = openapiAes.aesEncode(fd_openapi_member_pwd);
		} catch (Exception e) {
			System.out.println("=======================");
			System.out.println("ERROR : "+e.getMessage());
			System.out.println("-----------------------");
			e.printStackTrace();
		}
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		
		System.out.println("[CALL Member Controller ] openApiUpdInfo fd_openapi_autologin  ="+fd_openapi_autologin);
		System.out.println("[CALL Member Controller ] openApiUpdInfo fd_openapi_member_id  ="+fd_openapi_member_id);
		System.out.println("[CALL Member Controller ] openApiUpdInfo fd_openapi_member_pwd ="+fd_openapi_member_pwd);
		
		Member member = new Member();
		member.setPk_member_id(loginInfo.getPk_member_id());
		member.setFd_openapi_autologin(fd_openapi_autologin);
		member.setFd_openapi_member_id(fd_openapi_member_id);
		member.setFd_openapi_member_pwd(fd_openapi_member_pwd);
		
		try	{
			
			memberService.modify(member);

		} catch(Exception ex) {
			logger.error(ex.getMessage());
			script="alert('오픈API 정보 갱신에 실패하였습니다.');";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("script",script);
		mav.setViewName("message/returnScript");		
		
		return mav;
		
	}
	

	/**
	 * 약관동의 업데이트
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ifrmAgreementUpdInfo.do")
	public ModelAndView ifrmAgreementUpdInfo(HttpServletRequest request, @RequestParam Map<String, Object> map){
		System.out.println("[CALL Member Controller ] ifrmAgreementUpdInfo");
		
		String script = "";
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		try	{
			
			memberService.insAgreement(loginInfo.getPk_member_id());
			script="parent.afterAgree();";
			
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			script="alert('약관동의 정보 갱신에 실패하였습니다.');";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("script",script);
		mav.setViewName("message/returnScript");		
		
		return mav;
		
	}

	
	
	@RequestMapping("/memberList.do")
	public ModelAndView memberList(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.memberList()");
		
		//수정OK
		int page, pageSize		= 10;
		String searchColumn		= request.getParameter("searchColumn")	!= null ? request.getParameter("searchColumn").trim() : "";
		String searchString		= request.getParameter("searchString")	!= null ? request.getParameter("searchString").trim() : "";
		page					= Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page").trim() : "1");
		
		String state 			= request.getParameter("state") 		!= null ? request.getParameter("state").trim() : "";
		String start_ymd 		= request.getParameter("start_ymd") 	!= null ? request.getParameter("start_ymd").trim().replace("-", "") : "";
		String finish_ymd 		= request.getParameter("finish_ymd") 	!= null ? request.getParameter("finish_ymd").trim().replace("-", "") : "";
		String start_hour		= request.getParameter("start_hour") 	!= null ? request.getParameter("start_hour").trim() : "";
		String finish_hour		= request.getParameter("finish_hour") 	!= null ? request.getParameter("finish_hour").trim() : "";
		String start_minute		= request.getParameter("start_minute") 	!= null ? request.getParameter("start_minute").trim() : "";
		String finish_minute	= request.getParameter("finish_minute") != null ? request.getParameter("finish_minute").trim() : "";
		String start_date		= start_ymd + start_hour + start_minute;
		String finish_date		= finish_ymd + finish_hour + finish_minute;
		
		//Query 조건
		PageHelper pageHelper 	= new PageHelper(page, (page-1)*10, pageSize, searchColumn, searchString, state, start_date, finish_date);				
		
		//Sql Query
		int totalCount 			= memberService.getCount(pageHelper);
		List<Member> memberList = memberService.getList(pageHelper);
		List<Code> stateList 	= memberService.getGubun(); //회원상태	
		
		//PageNavagation
		PageNavi pageNavi = new PageNavi();
		pageNavi.setPageHelper("/member/memberList.do", totalCount, pageHelper);
		pageNavi.make();
		/*
		pageNavi.setNowPage(page); 			//현재 페이지
		pageNavi.setPageSize(pageSize); 	//1페이지당 표시될 갯수
		pageNavi.setTotalCount(totalCount);
		pageNavi.setParameters("searchColumn", searchColumn);
		pageNavi.setParameters("searchString", searchString);
		pageNavi.setParameters("state", state);
		pageNavi.setParameters("start_date", start_date);
		pageNavi.setParameters("finish_date", finish_date);
		*/
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberList");
		mav.addObject("stateList", stateList);
		mav.addObject("memberList", memberList);
		mav.addObject("pageNavi", pageNavi);
		mav.addObject("pageHelper", pageHelper);
		
		return mav;		
	}
	

	
	
	
	
	@RequestMapping("/checkId.do")
	public ModelAndView memberCheckId(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MemberController.memberCheckId()");
		
		String userId = request.getParameter("userId") != null ? request.getParameter("userId").trim() : "";
		System.out.println(userId);
		String chkId = memberService.checkId(userId);	
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("member/checkId");
		
		mav.addObject("userId", userId);
		mav.addObject("chkId", chkId);
		return mav;		
	}
	
	@RequestMapping("/zippopup.do")
	public ModelAndView memberZippopup(HttpServletRequest request)	{
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("member/zippopup");
		return mav;		
	}
	
	@RequestMapping("/zipcode.do")
	public ModelAndView memberZip(HttpServletRequest request){				
		String searchDong = request.getParameter("dong") != null ? request.getParameter("dong").trim() : "";			
		List<Zipcode> zipcodeList	= new ArrayList<Zipcode>();
		zipcodeList = memberService.searchZipcode(searchDong);
		
		//우편번호 목록			
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("member/zippopup");
		mav.addObject("zipcodeList", zipcodeList);	
		
		return mav;		
	}
	

	
	@RequestMapping("/memberModifyForm.do")
	public ModelAndView memberModifyForm(HttpServletRequest request) {
		
		System.out.println("\n[CALL Controller] MemberController.memberModifyForm()");
		
		int page = 1 ;				
		String searchColumn = request.getParameter("searchColumn") == null ? "" : request.getParameter("searchColumn").trim();
		String searchString = request.getParameter("searchString") == null ? "" : request.getParameter("searchString").trim();
		String pageStr = request.getParameter("page") == null ? "" : request.getParameter("page").trim();
		page = pageStr.equals("") ? page : Integer.parseInt(pageStr);
		
		String pk_member_id = request.getParameter("pk_member_id") == null ? "" : request.getParameter("pk_member_id").trim();			
		
		PageHelper pageHelper = new PageHelper(page, 0, 0, searchColumn, searchString);
		List<Code> gubun = memberService.getGubun(); //회원 상태  구분	
		Member member = memberService.get(pk_member_id);		

		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberModifyForm");		
		mav.addObject("pageHelper", pageHelper);
		mav.addObject("gubun", gubun);	
		mav.addObject("member", member);		
		return mav;
	}
	
	/**
	 * 수정 처리
	 * @param request
	 * @return
	 */
	@RequestMapping("/memberModify.do")
	public ModelAndView memberModify(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.memberModify()");
		
		/*search 조건 parameter*/
		String searchColumn			= request.getParameter("searchColumn") != null ? request.getParameter("searchColumn").trim() : ""; // 검색 select조건
		String searchString			= request.getParameter("searchString") != null ? request.getParameter("searchString").trim() : ""; // 검색어
		String page					= request.getParameter("page") != null ? request.getParameter("page").trim() : "";			// 페이지
		String searchParam			= "?page="+page+"&searchColumn="+searchColumn+"&searchString="+searchString;
		
		//parameter
		String pk_member_id			= request.getParameter("pk_member_id") != null 		? request.getParameter("pk_member_id").trim() : "";
		String fd_zipcode1			= request.getParameter("fd_zipcode1") != null 		? request.getParameter("fd_zipcode1").trim() : "";
		String fd_zipcode2			= request.getParameter("fd_zipcode2") != null 		? request.getParameter("fd_zipcode2").trim() : "";
		String fd_zipcode			= fd_zipcode1 + fd_zipcode2;
		String fd_phone1			= request.getParameter("fd_phone1") != null 		? request.getParameter("fd_phone1").trim() +"-": "";
		String fd_phone2			= request.getParameter("fd_phone2") != null 		? request.getParameter("fd_phone2").trim() +"-" : "";
		String fd_phone3			= request.getParameter("fd_phone3") != null 		? request.getParameter("fd_phone3").trim() : "";
		String fd_phone				= fd_phone1+fd_phone2+fd_phone3;
		String fd_mobile1			= request.getParameter("fd_mobile1") != null 		? request.getParameter("fd_mobile1").trim() +"-" : "";
		String fd_mobile2			= request.getParameter("fd_mobile2") != null 		? request.getParameter("fd_mobile2").trim() +"-" : "";
		String fd_mobile3			= request.getParameter("fd_mobile3") != null 		? request.getParameter("fd_mobile3").trim() : "";
		String fd_mobile			= fd_mobile1+fd_mobile2+fd_mobile3;
		String fd_member_status_cd	= request.getParameter("fd_member_status_cd") != null 	? request.getParameter("fd_member_status_cd").trim() : "";
		String fd_member_name		= request.getParameter("fd_member_name") != null		? request.getParameter("fd_member_name").trim() : "";
		String fd_gender			= request.getParameter("fd_gender") != null 		? request.getParameter("fd_gender").trim().toUpperCase() : "";
		String fd_birthday			= request.getParameter("fd_birthday") != null 		? request.getParameter("fd_birthday").toString().replace("-", "") : "";
		String fd_email1			= request.getParameter("fd_email1") != null 		? request.getParameter("fd_email1").trim() +"@": ""; 
		String fd_email2			= request.getParameter("fd_email2") != null 		? request.getParameter("fd_email2").trim() : "";
		String fd_email 			= fd_email1 + fd_email2; 
		String fd_address_detail	= request.getParameter("fd_address_detail") != null ? request.getParameter("fd_address_detail").trim() : "";
		String fd_address			= request.getParameter("fd_address") != null 		? request.getParameter("fd_address").trim() : "";
		String fd_sms_yn			= request.getParameter("fd_sms_yn") != null 		? request.getParameter("fd_sms_yn").trim().toUpperCase() : "";
		String fd_email_yn			= request.getParameter("fd_email_yn") != null 		? request.getParameter("fd_email_yn").trim().toUpperCase() : "";
		String fd_member_level		= request.getParameter("fd_member_level") != null 	? request.getParameter("fd_member_level").trim() : "";
		String fd_img 				= request.getParameter("fd_img") != null 			? request.getParameter("fd_img").trim() : "";
		String fd_cafe_name			= request.getParameter("fd_cafe_name") != null 		? request.getParameter("fd_cafe_name").trim() : "";
		String fd_job_code			= request.getParameter("fd_job_code") != null 		? request.getParameter("fd_job_code").trim() : "";
		String fd_job_etc			= request.getParameter("fd_job_etc") != null 		? request.getParameter("fd_job_etc").trim() : "";

		
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");		
		String now_date =  ft.format(new Date( ));
		Member pre_member = memberService.get(pk_member_id);// 변경전 맴버 정보
		
		Member member = new Member();	
		/*		member.setFd_address(fd_address);
		member.setFd_address_detail(fd_address_detail);
		member.setFd_birthday(fd_birthday);
		//member.setFd_change_pw_date(fd_change_pw_date);
		member.setFd_email(fd_email);
		member.setFd_email_yn(fd_email_yn);
		member.setFd_gender(fd_gender);
		//member.setFd_login_date(fd_login_date);
		member.setFd_member_level(fd_member_level);
		//member.setFd_member_mod_date(fd_member_mod_date);
		member.setFd_member_name(fd_member_name);
		member.setFd_member_status_cd(fd_member_status_cd);
		if(!fd_member_status_cd.equals(pre_member.getFd_member_status_cd())){
			member.setFd_member_status_date(now_date); // 오늘 날짜로 변경
		}
		member.setFd_mobile(fd_mobile);
		//member.setFd_mod_date(fd_mod_date);
		member.setFd_phone(fd_phone);
		//member.setFd_reg_date(fd_reg_date);
		member.setFd_sms_yn(fd_sms_yn);
		member.setFd_zipcode(fd_zipcode);
		member.setPk_member_id(pk_member_id);
		member.setFd_img(fd_img);
		member.setFd_cafe_name(fd_cafe_name);
		member.setFd_job_code(fd_job_code);
		member.setFd_job_etc(fd_job_etc);*/
		
		String script="";
		try	{ 
			memberService.modify(member);
			script="alert('회원정보 수정이 완료 되었습니다.');";
			script+="location.href='/member/memberList.do"+searchParam+"';";
		} catch(Exception ex) {
			logger.error(ex.getMessage());
			script="alert('회원정보 수정에 실패 하셨습니다.다시 시도해주세요');";
			script+="location.href='/member/memberModifyForm.do"+searchParam+"&pk_member_id="+pk_member_id+"';";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("script",script);
		mav.setViewName("member/memberModifyForm");		
		
		return mav;
	}
	
	
	@RequestMapping("/changePwForm.do")
	public ModelAndView membercheckPw(HttpServletRequest request) {
		
		System.out.println("\n[CALL Controller] MemberController.membercheckPw()");
		
		String pk_member_id = request.getParameter("aa") != null ? request.getParameter("aa").trim() : "";
		//String chkPw = memberService.checkPw(pk_member_id);
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("member/changePwForm");		
		mav.addObject("pk_member_id", pk_member_id);
	
		return mav;	
	}
	
	
	@RequestMapping(value="/changePwForm.do", params="chkUpPW=Update")
	public ModelAndView memberChangePw(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] MemberController.memberChangePw()");
		
		ModelAndView mav = new ModelAndView();		
		
		String pk_member_id = request.getParameter("userid") != null ? request.getParameter("userid").trim() : "";
		//String past_fd_member_pw = request.getParameter("past") != null ? request.getParameter("past").trim() : "";
		String fd_member_pw = request.getParameter("chpw") != null ? request.getParameter("chpw").trim() : "";
		
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");		
		String fd_change_pw_date =  ft.format(new Date( ));			

		HashEncrypt encrypt = new HashEncrypt(fd_member_pw);
		fd_member_pw = encrypt.getEncryptData();
		
		Member member = new Member();
		member.setPk_member_id(pk_member_id);
		//member.setFd_member_pw(fd_member_pw);

		String script="";
		try	{ 
			memberService.changePw(member);
			script="alert('비밀번호가 정상적로 변경되었습니다.');";
			script+="self.close();";
		} catch(Exception ex){
			logger.error(ex.getMessage());
			script="alert('비밀번호 변경이 실패 하셨습니다.');";
			script+="location.href='/member/changePwForm.do';";
		}		

		/*비밀번호가 잘 바뀌었나 비밀번호 다시 조회해서 확인		
		String checkPw =""; //변경된 비밀번호
		try{
			checkPw = memberService.checkPw(pk_member_id);
			//System.out.println("변경된 비밀번호는"+checkPw);		
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		*/
//		mav.setViewName("redirect:member/changePwForm.do");		
		mav.addObject("script", script);
	
		return mav;	
	}
	
	
	@RequestMapping("/memberRemove.do")
	public ModelAndView memberRemove(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MemberController.memberRemove()");
		
		/*search 조건 parameter*/
		String searchColumn=request.getParameter("searchColumn") != null ? request.getParameter("searchColumn").trim() : ""; // 검색 select조건
		String searchString=request.getParameter("searchString") != null ? request.getParameter("searchString").trim() : ""; // 검색어
		String page=request.getParameter("page") != null ? request.getParameter("page").trim() : "";			// 페이지
		String searchParam ="?page="+page+"&searchColumn="+searchColumn+"&searchString="+searchString;

		/*db에 등록할 parameter*/		
		String pk_member_id = request.getParameter("pk_member_id") != null ? request.getParameter("pk_member_id").trim() : "";
		
		String script="";
		try	{ 
			memberService.remove(pk_member_id);
			script="alert('회원정보 삭제가 완료 되었습니다.');";
			script+="location.href='/member/memberList.do"+searchParam+"';";
		} catch(Exception ex){
			logger.error(ex.getMessage());
			script="alert('회원정보 삭제에 실패 하셨습니다.\n다시 시도해주세요');";
			script+="location.href='/member/memberModifyForm.do"+searchParam+"&pk_member_id="+pk_member_id+"';";
		}		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberList");		
		mav.addObject("script",script);
		return mav;
		
	}
	
	/*
	@RequestMapping(value="/insert.do", method=RequestMethod.GET)
	public String insertForm(){
		return "member/insert";
	}
	
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public ModelAndView insert(Member dto){
		boolean result = membersService.insert(dto);
		ModelAndView mv = new ModelAndView();
		String msg = "";
		if(result){
			msg = "success";
		}else{
			msg = "fail";
		}
		mv.addObject("msg", msg);
		mv.setViewName("member/result");
		return mv;
	}
	
	@RequestMapping(value="/list.do")
	public ModelAndView getList(){
		List<Member> list = membersService.getList();
		//생성자에서 받은 문자열은 뷰이름을 의미
		ModelAndView mv = new ModelAndView("member/list");
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(@RequestParam("id") String id){
		boolean result = membersService.delete(id);
		if(result){
			//리다이렉트 방식으로 페이지 이동
			return "redirect:/list.do";
		}else{
			return "member/error";
		}
	}
	
	@RequestMapping(value="/getInfo.do")
	public ModelAndView getList(@RequestParam("id") String id){
		MemberDto member = membersService.getInfo(id);
		
		//생성자에서 받은 문자열은 뷰이름을 의미
		ModelAndView mv = new ModelAndView("member/view");
		mv.addObject("member", member);
		return mv;
	}
	
	@RequestMapping(value="/getInfo.do")
	public String getInfo(String id, Model model){
		Member member = membersService.getInfo(id);
		model.addAttribute("member", member);
		return "member/view";
	}
	*/
	
}
