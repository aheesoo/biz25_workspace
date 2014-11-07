<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<!-- <script src="/resources/js/api_js.js"></script> -->
	<script src="/resources/js/jquery-ui-1.10.3.custom.js"></script>


	<!-- OPEN API 로그인 -->
	<!-- <SCRIPT type="text/javascript" language="JavaScript" src="api_js.js"></script> -->

	<!-- <SCRIPT FOR="KTOpenAPIX" EVENT="EventLogin(nResult)" LANGUAGE="Jscript"> -->

	<script language="javascript">
	
	//메인 페이지 접근시 무조건 통화OpenAPI를 재로그인 시켜야 함.
	var var_reload = "F";
	
	
	function APIEventLogin(nResult){

		//로그인 성공시 : 200
		//로그아웃시 300
		
		
		/**
		로그인 관련 이벤트
		**/ 
		//로그인 후 작업(정상)
		if(nResult==200){
	
			//$('#openApiDiv').hide();
			$("#id_link_openapi_login").hide();
			$("#id_link_openapi_logout").show();
	
			//히든값도 업데이트
			//var check = $("#chk_autologin").is(":checked");
			var check = $('input[name="chk_autologin"]').is(":checked");
			
			if(check){
				$('#fd_openapi_autologin').val("T");
			}else{
				$('#fd_openapi_autologin').val("F");
			}
	
			//open_api 로그인중인지 체크를 위한 쿠키
			setCookie("OPEN_LOGIN","T", 30000); //30000시간
			setCookie("AUTO","F", 30000); 		//30000시간

			//openapi user info 업데이트
			var frm = document.openApiLoginForm;
			frm.action = "/member/ifrmOpenApiUpdInfo.do";
			frm.target = "left_ifrm";
			frm.submit();
	
		}else{
	
			/**
			로그아웃 관련 이벤트
			300 : 정상로그아웃
			400 : 강제로그아웃(외부에서 같은아이디로 로그인)
			401 : CP강제 로그아웃(인증키 차단)
			**/
			//alert( "[Event] LogOut");
			//location.reload();
			
			$("#id_link_openapi_login").show();
			$("#id_link_openapi_logout").hide();
			
			//로그인창
			$('#openApiDiv').show();
			
		}
		
	
		if(nResult==200){
		/**
			로그인 후 작업
		 **/
				}else if(nResult==300 || nResult==400 || nResult==401){
		/**
			로그아웃 관련 이벤트
			300 : 정상로그아웃
			400 : 강제로그아웃(외부에서 같은아이디로 로그인)
			401 : CP강제 로그아웃(인증키 차단)
		**/
		//		alert( "[Event] LogOut");
				location.reload();
			}else{
		/**
			로그인 에러
		 **/
		}
		
		
		//closeApiDiv('openApiDiv');
	}
	</SCRIPT>




<!-- <SCRIPT FOR="KTOpenAPIX" EVENT="EventCID( sCaller, sCallee, sResult, sClSeqno, sMiSeqno)" LANGUAGE="Jscript"> -->
<script language="javascript">

function APIEventCID( sCaller, sCallee, sResult, sClSeqno, sMiSeqno){
	/*
		발신정보 수신 통지 이벤트
		전화벨이 울리면 201
		받으면 200
		안받으면 407
	*/
	
	//case "201": sResultTxt	= "호처리중"; chkOpen('', sCaller, sClSeqno); break;  //전화수신 사용자 팝업 호출(리스트형 or 등록형팝업)
	
	 switch (sResult)
	 {
		 case "200": sResultTxt	= "호처리성공"; break;
		 case "201": sResultTxt	= "호처리중"; chkOpen('', sCaller, sClSeqno); break;  //전화수신 사용자 팝업 호출(리스트형 or 등록형팝업)
		 case "202": sResultTxt	= "부재중"; break;
		 case "203": sResultTxt	= "부재중"; break;
		 case "401": sResultTxt	= "결번"; break;
		 case "404": sResultTxt	= "통화중"; break;
		 case "405": sResultTxt	= "무응답"; break;
		 case "407": sResultTxt	= "발신자 호포기"; callNoRcv(sCallee, sCaller, sResult, sClSeqno); break;
		 case "408": sResultTxt	= "착신자 호포기"; break;
		 case "701": sResultTxt	= "SHOW CID 무선 회선 호 연결중"; break;
		 case "700": sResultTxt	= "SHOW CID 무선 회선 호 연결됨"; break;
		 case "904": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
		 case "905": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
		 case "906": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
		 case "907": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
		 default: sResultTxt	= "Error";
	 }

	/*
	alert(	"발신:"						+	sCaller		+" "+
			", 수신:"				+	sCallee		+" "+
			", 결과(코드):"			+	sResultTxt	+"("+sResult+")"+
			", 통화내역 일련번호:"		+	sClSeqno	+" "+
			", 발신사용자 일련번호  :"	+	sMiSeqno);
	*/
	// DB에서 로그조회 후, 리스트 출력
	//logSaveList(sCallee,sCaller,sResult,sClSeqno);
}
</SCRIPT>

<script language="javascript">
	function openApiloginResult(nResult){
		
		switch (nResult)
		{
			case 200:break;
			case 301:alert("다른 PC에 로그인되어 있습니다.");break;
			case 401:alert("미등록 아이디로 로그인"); break;
			case 402:alert("비밀번호 오류 횟수 초과 (5회제한)"); break;
			case 403: alert("임시비밀호 로그인");break;
			case 404:alert("임시비밀번호 설정"); break;
			case 405:alert("비밀번호를 확인하여 주세요"); break;
			case 407:alert("접속 IP 오류"); break;
			case 408: alert("미등록 PC");break;
			case 500: alert("기타(HTTPS/HTTP 요청 실패)");break;
			case 1000:alert("이미 로그인중"); break;
			case 1001:alert("서버 타입 에러"); break;
			case 1502:alert("협정 만료일이 지났음"); break;
			case 1503: alert("인증키 유효기간이 지났음");break;
			case 1504:alert("인증키 비활성"); break;
			case 1505: alert("인증키 타입이 틀릴 경우");break;
			case 1506: alert("개발 서버이나 상용 인증키, 상용 Flag일 경우");break;
			case 1507: alert("상용 서버이나 개발 인증키, 개발 Flag일 경우");break;
			case 1700: alert("API 환경 정보 얻지 못함(실행되는 경로)");break;
			case 1701: alert("KTA_API.dat / KTD_API.dat등의 data 파일 초기화 에러 파일이 존재해야 함"); break;	 
			case 1702: alert("PC 메모리 부족(API 생성 에러)");break;
			default: alert("고객센터로 문의 주세요. \n (openApi Error code="+nResult+")");
		}

	}
</script>

<script language="javascript">
function APIEventCTC(sCaller, sCallee, sResult, sMiSeqno){
	/**
		CTC이벤트 - 전화걸기 요청 함수
	**/
	var CTC ="";
	CTC   =	"발신:"		+	sCaller		+" "+
				", 수신:"	+	sCallee		+" "+
				", 결과:"	+	sResult		+" "+
				", ID  :"	+	sMiSeqno	+ "\n";
	document.getElementById("CTC").value =CTC;
}

function APIEventCON( sCaller, sCallee, sCallBackID, sResult){
	/**
		CON이벤트 - 전화를 받거나 끊을때(다자간 통화) 
	**/

	var CON ="";
	if(sResult=="200"){sResult="성공"}
	
	CON   =	"발신:"			+	sCaller		+" "+
				", 수신:"		+	sCallee		+" "+
				", 결과:"		+	sResult		+" "+
				", 방번호  :"	+	sCallBackID +"\n";
}
</SCRIPT>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		//페이지 리로딩시
		$("#openApiDiv").draggable();
		$("#openApiMemberDivList").draggable();
		$("#openApiMemberDivMod").draggable();

		
		//통화오픈api 체크박스 체크시
		$("#openapi_chk_agree").on("click",function() {	
			var check = $(this).is(":checked");
			if(check){
				$("#openApiLoginBtn").show();
			}else{
				$("#openApiLoginBtn").hide();
			}
		});
		

		//left 통화오픈api 로그인상태에 따른 노출
		var cookiedata = document.cookie;    
		if(cookiedata.indexOf("OPEN_LOGIN=T")>=0)
		{
			//로그인 상태
			$("#id_link_openapi_login").hide();
			$("#id_link_openapi_logout").show();
		}else{
			//로그아웃 상태
			$("#id_link_openapi_logout").hide();
			$("#id_link_openapi_login").show();
		}
		

		//자동 로그인 처리를 위한 값 세팅
		var opana_id 	= $("#apiId").val();
		var opana_pwd 	= $("#apiPwd").val();


		//setCookie("AUTO","F", 30000);			//30000시간
		//setCookie("OPEN_LOGIN","F", 30000);	//30000시간
		
		//자동 로그인
		var cookiedata = document.cookie;    
		if(		$("#fd_openapi_autologin").val()=="T" && 
				$("#apiId").val().length>0 && 
				$("#apiPwd").val().length>0 &&
				cookiedata.indexOf("AUTO=T")>=0 &&			//자동로그인플래그가 T이고 (최초한번)
				cookiedata.indexOf("OPEN_LOGIN=T")<0 		//로그인중이 아니고
				)
		{

			openApiLogin();
			//한번로그인하면 계속 로그인처리 안되도록
			//$("#fd_openapi_autologin").val('F');
		}else{

			//리로딩시 left프레림이 리로딩 되었는지 체크
			top.left_frame.checkReload();
		}

	});

	function mainCallReLogin(){
		
		//메인 호출시 재로그인 
		var cookiedata = document.cookie;
		
/* 		alert($("#fd_openapi_autologin").val());
		alert($("#apiId").val());
		alert($("#apiPwd").val());
		alert(cookiedata.indexOf("AUTO=T"));
		alert(cookiedata.indexOf("OPEN_LOGIN=T")); */
		
		if(		$("#fd_openapi_autologin").val()=="T" && 
				$("#apiId").val().length>0 && 
				$("#apiPwd").val().length>0 &&
				cookiedata.indexOf("AUTO=T")<0 &&			//자동로그인플래그가 T이고 (최초한번) ==> 최초한번 이후에만
				cookiedata.indexOf("OPEN_LOGIN=T")>=0 		//로그인 중이면 
				)
		{
			top.left_frame.ReLogin();
		}
		
	}
	

	// DB 에 고객정보 로그 리스트 출력 -> 전화 왔을시
	function openCalling(sCallee,sCaller,sResult,sClSeqno){
		
		//alert("openCalling sCallee="+sCallee+"/sCaller="+sCaller);
		
		$.ajax({
			// type을 설정합니다.
			type:'POST',
			url:"/common/ajaxBlank.do",
			// 사용자가 입력하여 id로 넘어온 값을 서버로 보냅니다.
			dataType : "json",
			data:{
				"type"			 : "member_log_select",
				"fd_tel"		 : sCallee,
				"fd_rcv_tel"	 : sCaller,
				"sResult"		 : sResult,
				"sClSeqno"		 : sClSeqno
			},
			// 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
			success:function(listdata){
				if(listdata){

					var sResult = listdata.sResult;
					if(sResult == "201"){	// 전화가 올때
						// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리
						
						$("#apiRcvList > tbody").html("");
						
						var cnt = listdata.list.length;

						// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리
						//chkOpen('"+chkFlag+"','"+fk_tel+"','"+fd_seqno+"')
						chkOpen("List", "");	//고객 정보창 오픈
					}
				}
			}
		});
	}
	// DB 에 고객정보 로그 저장 & 로그 리스트 출력 - Ehttp://ahnlab.tagtree.co.kr/track.click?zone=32&ad=103
	
	
	// DB 에 고객정보 로그 리스트 출력 -> 부재중 내역으로 변경
	//function logSaveList(sCallee,sCaller,sResult,sClSeqno){
	function callNoRcv(sCallee, sCaller, sResult, sClSeqno){
		
		$.ajax({

			type:'POST',
			url:"/common/ajaxBlank.do",
			dataType : "json",
			data:{
				"type"			 : "member_log_select",
				"fd_tel"		 : sCallee,
				"fd_rcv_tel"	 : sCaller,
				"sResult"		 : sResult,
				"sClSeqno"		 : sClSeqno
			},

			// 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
			success:function(listdata){

				if(listdata){

					var sResult = listdata.sResult;

					// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리
					
					$("#apiRcvList > tbody").html("");
					
					var cnt = listdata.list.length;
					if(cnt>0){
						for(var i=0; i<cnt; i++){

							var fd_call_date	= listdata.list[i].pk_date;
							var fd_user_name	= listdata.list[i].fd_user_name;
							var fk_tel			= listdata.list[i].fk_tel;
							var chkFlag			= listdata.list[i].chkData;
							var fd_seqno		= listdata.list[i].fd_seqno;
							
							//var htmlS = "<tr style='cursor:pointer' onclick=\"chkOpen('"+chkFlag+"','"+fk_tel+"','"+fd_seqno+"')\">"
							var htmlS = "<tr>"
								+"<td class=\"pop_tb_nor_l\">"+fd_call_date+"</td>"
								+"<td class=\"pop_tb_nor_l\">"+fd_user_name+"</td>"
								+"<td class=\"pop_tb_nor_l\">"+fk_tel+"</td></tr>";
							//고객리스트 출력
							//("+fd_seqno+")
							$("#apiRcvList > tbody").html($("#apiRcvList > tbody").html() + htmlS);
						}
					}
					// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리
					//chkOpen("List", "", sClSeqno);	//고객 정보창 오픈
					$('#openApiMemberDivList').show();

				}
			}
		});
	}
	// DB 에 고객정보 로그 저장 & 로그 리스트 출력 - E

	
	
	//api 로그인창 오픈
	function openApiLoginOn(){
		//로그인창 오픈
		$('#openApiDiv').show();
		
		//api 로그인창을 열때는 기본값으로 자동로그인 체크를 기본
		$('input:checkbox[id="chk_autologin"]').attr("checked", true);
		
	}

	//api 로그인처리
	function openApiLogin(){
		//로그인 처리
		$('#loginID').val($('#apiId').val());				// api아이디
		$('#loginPWD').val($('#apiPwd').val());				// api패스워드
		$('#openApiDiv').hide();
		top.left_frame.APILogin();
	}
	
	//api 로그아웃처리
	function APILogout(){
		top.left_frame.Logout();
	}
	
	//고객창 오픈(list/insert/update) : 리스트 상세창을 열지, 빈 입력창을 열지 체크 후, 레이어 오픈요청
	function chkOpen(opMod, fk_tel, fd_seqno){
		//alert(opMod+", "+fk_tel+", "+fd_seqno);

		//$('#openApiMemberDiv'+opMod).show();				// 전화 수신리스트 오픈  (투명레이어)
		if(opMod != "List"){
			customerFormDiv("", fk_tel ,fd_seqno);			// 고객정보 저장/수정창 OPEN
			
			//openCalling(sCallee,sCaller,sResult,sClSeqno){
		}
	}
	
	// 고객정보 저장/수정 DIV OPEN : 
	function customerFormDiv(opMod, fk_tel, myfd_seqno){

		// ajax
		$.ajax({
			// type을 설정합니다.
			type:'POST',
			url:"/common/ajaxBlank.do",
			// 사용자가 입력하여 id로 넘어온 값을 서버로 보냅니다.
			dataType : "json",
			data:{
				"type"			 : "member_listView",
				"fd_tel"		 : fk_tel,
				"opMod"			 : opMod
			},
			// 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
			success:function(listdata){
				if(listdata){
					// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리
					var cnt				= listdata.list.length;
					var opMod			= listdata.opMod;
					var fk_tel			= listdata.fk_tel;
					var call_date		= listdata.call_date;
					
					var fd_call_date	= "";
					var fd_user_name	= "";
					var chkFlag			= "";
					var fd_seqno		= "";
					
					var pop_sttxt		= "";
					var tb_start_date	= "";

					$("#apiMemberList > tbody").html("");

					if(cnt>0){
						for(var i=0; i<cnt; i++){
							var fd_call_date	= listdata.list[i].pk_date;
							var fd_user_name	= listdata.list[i].fd_name;
							var fk_tel			= listdata.list[i].fk_tel;
							var fd_seqno		= listdata.list[i].fd_seqno;
							var fd_memo			= listdata.list[i].fd_memo;
							var fd_addr			= listdata.list[i].fd_addr;

							if(opMod == "Ins"){  //2번
								
								$('#openApiMemberDivIns').show();				// 전화 수신리스트 오픈  (투명레이어)

								if(i == 0){
									// 고객정보 저장(신규)
									$('.pop_sttxt').html(fk_tel);
									$('#memberTel'+opMod).val(fk_tel.replace(/\-/g,""));
									$('#memberSeqno'+opMod).val(fd_seqno);
									$('.pop_datetxt').html(call_date);
								}
							}else if(opMod == 'Mod'){	//1번
								
								$('#openApiMemberDivMod').show();				// 전화 수신리스트 오픈  (투명레이어)
								
								//내역 클릭시 상단 수정부분 표시
								$('.pop_sttxt').html(fd_user_name+"("+fk_tel+")");
								$('#memberName'+opMod).val(fd_user_name);
								$('#memberAddr'+opMod).val(fd_addr);
								$('#memberMemo'+opMod).val(fd_memo);
								$('#memberTel'+opMod).val(fk_tel.replace(/\-/g,""));
								$('#memberSeqno'+opMod).val(fd_seqno);
								$('.pop_datetxt').html(call_date);
								
								var htmlS = "<tr>"
									+"<td class='pop_tb_nor_l'>"+fd_call_date+"</td>"
									+"<td class='pop_tb_nor_l'>"+fd_memo+"</td></tr>";
								$("#apiMemberList > tbody").html($("#apiMemberList > tbody").html() + htmlS);
							}
							
						}
					}
					// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리

					// 등록창 오픈
					$('#openApiMemberDiv'+opMod).show();

				}
			}
		});
	// ajax
	}
	// 고객정보 저장/수정창 OPEN

	//ID 값으로 검색하여 창 숨김처리
	function closeApiDiv(dName){
		$('#'+dName).hide();
	}
	function closeApiDivChk(dName,chkTxt){
		if(confirm(chkTxt)){
			$('#'+dName).hide();
		}
	}
	
	//api 고객정보 등록창 등록/수정
	function openApiMemberAdd(opMod,btnType){
		
		//type -> Mod : 수정, Ins : 저장
		// DB 에 고객정보 로그 저장
		var memberName		 = $("#memberName"+opMod).val();	//이름
		var memberTel		 = $("#memberTel"+opMod).val();		//전화번호  
		var memberAddr		 = $("#memberAddr"+opMod).val();	//주소
		var memberMemo		 = $("#memberMemo"+opMod).val();	//메모
		var memberSeqno		 = $('#memberSeqno'+opMod).val();	//옵션
		var memberViewChk	 = $('#memberViewChk'+opMod).val();	//자동저장체크

		if(opMod=="Ins" && $('#memberViewChk'+opMod).attr("checked") == "checked" && memberViewChk=='1' && btnType=='close'){
			closeApiDivChk('openApiMemberDivIns','자동저장하지 않기를 선택 하셨습니다.');
			$('#memberViewChk'+opMod).attr("checked",false);
		}else{
			$.ajax({
				// type을 설정합니다.
				type:'POST',
				url:"/common/ajaxBlank.do",
				data:{
					"type"				 : "memberPro"
					, "memberName"		 : memberName
					, "memberTel"		 : memberTel
					, "memberAddr"		 : memberAddr
					, "memberMemo"		 : memberMemo
					, "memberSeqno"		 : memberSeqno
					, "memberViewChk"	 : memberViewChk
					, "opMod"			 : opMod
				},
				// 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
				success:function(data){
					if(data){
						closeApiDiv('openApiMemberDiv'+opMod);
					}
				}
			});
		}
	}
	//api 고객정보 등록창 등록
	
	//전화번호 자르기
	function telSplit(sCaller,sptxt){
		var sCallerStr;
		var sCallerTot;
		var tel1_2;
		var tel1_2_len;
		var tel1_len;
		var memberTel1;
		var memberTel2;
		var memberTel3;
		
		sCallerStr	 = String(sCaller);
		sCallerTot	 = sCallerStr.length;
		tel1_2_len	 = sCallerTot-4;
		
		var memberTel3	 = sCallerStr.substr(tel1_2_len,4);

		tel1_2		 = sCallerStr.substr(0,tel1_2_len);

		tel1_len	 = 2;
		memberTel1	 = tel1_2.substr(0,2);
		if(memberTel1!="02"){
			tel1_len	 = 3;
			memberTel1	 = tel1_2.substr(0,3);
		}
		memberTel2	 = tel1_2.substring(tel1_len,tel1_2.length);

		return memberTel1+sptxt+memberTel2+sptxt+memberTel3;
	}
	

	// 팝업공통 - alert
	function alertArea(w, h, align, tit, txt){
		//alertArea(40,40,'test','test dd<br>asdfasd')
		//w		 = width 100 이하이면 100으로 초기화(택스트영역)
		//h		 = height 30 이하이면 30으로 초기화(택스트영역)
		//align     = text-align (left, center, right)
		//tit	 = 타이틀
		//txt	 = 택스트(내용)
		var area = "alertPop";
		var w, h, wOut, tit;
		if(w < 100){
			w = 100;
		}
		if(h < 30){
			h = 30;
		}
		wOut = w+20;
		var lmargin = w - 10;

		var $div = $('<div id="progressDiv_z" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50); z-index:999;"></div>');

		var div1		 = '<div id="alertInArea" style="position:fixed; top:0; left:0; width:100%; height:100%;z-index:9999;"><div id="popup" style="margin:0 auto;margin-top:150px;width:'+wOut+'px;">';
		var divTitle	 = '<div class="pop_top"><div class="pop_x" style="margin-left:'+lmargin+'px"><a href="javascript:alertArea(\'0\',\'0\',\'\',\'\');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div><div class="pop_tit">'+tit+'</div></div>';
		var divTxt		 = '<div id="alertTxt" style=" margin:0 auto;margin-top:10px;width:'+w+'px;height:'+h+'px; text-align:'+align+'; ">'+txt+'</div>';
		var divClose	 = '<div style="margin:0 auto;width:50px;height:30px"><a href="javascript:alertArea(\'0\',\'0\',\'\',\'\');"><img src="/resources/images/shopmaster/btn_close.gif" border="0"/></a></div></div></div>';
		
		var $div1;
			$div1 = div1+divTitle+divTxt+divClose;
			
		if($('#'+area).css("display") != "none"){
			$('#progressDiv_z').remove();
			$('#alertInArea').remove();
			$('#'+area).hide();
		}else{
			$("body").append($div);
			$("body").append($div1);
			//$('#alertTxt').html(txt);
			$('#'+area).show();
		}
	}
	
	
	// 팝업공통 - alert
	function alertAgree(w, h, align, tit, txt){
		//alertArea(40,40,'test','test dd<br>asdfasd')
		//w		 = width 100 이하이면 100으로 초기화(택스트영역)
		//h		 = height 30 이하이면 30으로 초기화(택스트영역)
		//align     = text-align (left, center, right)
		//tit	 = 타이틀
		//txt	 = 택스트(내용)
		var area = "alertPop";
		var w, h, wOut, tit;
		if(w < 100){
			w = 100;
		}
		if(h < 30){
			h = 30;
		}
		wOut = w+20;
		var lmargin = w - 10;

		var $div = $('<div id="progressDiv_z" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50); z-index:999;"></div>');

		var div1		 = '<div id="alertInArea" style="position:fixed; top:0; left:0; width:100%; height:100%;z-index:9999;"><div id="popup" style="margin:0 auto;margin-top:150px;width:'+wOut+'px;">';
		var divTitle	 = '<div class="pop_top"><div class="pop_x" style="margin-left:'+lmargin+'px"><a href="javascript:alertArea(\'0\',\'0\',\'\',\'\');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div><div class="pop_tit">'+tit+'</div></div>';
		var divTxt		 = '<div id="alertTxt" style=" margin:0 auto;margin-top:10px;width:'+w+'px;height:'+h+'px; text-align:'+align+'; "></div>';
		var divClose	 = '<div style="margin:0 auto;width:50px;height:30px"><a href="javascript:alertArea(\'0\',\'0\',\'\',\'\');"><img src="/resources/images/shopmaster/btn_close.gif" border="0"/></a></div></div></div>';
		
		var $div1;
			$div1 = div1 + divTitle + divTxt + divClose;
		if($('#'+area).css("display") != "none"){
			$('#progressDiv_z').remove();
			$('#alertInArea').remove();
			$('#'+area).hide();
		}else{
			$("body").append($div);
			$("body").append($div1);
			$('#alertTxt').html(txt);
			$('#'+area).show();
		}
	}
	
	//최초 로그인 이용약관 동의
	function openAgreement(){
		$('#background_gray').show();
		$('#alertAgreement').show();
	}
	
	//이용 약관 동의 처리
	function closeAgreement(){
		
		if( $('#mastershop_agreement1').attr("checked") && $('#mastershop_agreement2').attr("checked")){
			
			//통화오픈api 로그아웃클릭시 DB도 자동로그인 풀림업데이트
			//openapi user info 업데이트
			var frm = document.openApiLoginForm;
			frm.action = "/member/ifrmAgreementUpdInfo.do";
			frm.target = "left_ifrm";
			frm.submit();
			
			$('#background_gray').remove();
			$('#alertAgreement').remove();
			
		}else{
			alert("약관에 및 개인정보 취급방침에 동의 하셔야 서비스 이용이 가능합니다.");
		}

	}
	
	//이용 약관 업데이트 후 오픈api 창 보여주기
	function afterAgree(){
		
		if( $("#apiId").val().length>0 ){
			//로그인창
			$('#openApiDiv').show();
		}
	}
	
	//이용 약관 동의 처리
	function logoutAgreement(){
		
		alert("서비스 이용약관 및 개인정보취급방침을 모두 동의하셔야 서비스 가입 및 이용이 가능합니다");
		top.location.href = "/manager/logout.do";

	}
	
	//미납요금 알림
	function alertPayPrice(){
		var msg = "고객님 미납요금(xxx원)이 발생하여 알려드립니다.<br>요금 미납이 발생하셔서 무료문자가 모두 자동 소진되었습니다.<br>"; 
			msg = msg + "자동 소멸된 문자는 요금 납입 시에도 충전되지 않습니다.<br>2개월 체납 시 문자 발송을 하실 수 없습니다.<br>";
			msg = msg + "더 자세한 사항은 고객센터(1899-1431)으로 문의 하세요.";
		alertArea(300, 150, "left", "웹 메시지", msg);
	}
	
	function goLogout(){
		top.location.href = "/manager/logout.do";
	}
</script>
<%-- 로그저장 --%>
<script type="text/javascript">
	function call_log(sCaller, sCallee, sResult, sClSeqno, sMiSeqno){

		var obj_d, rtn, yyyy, mm, dd, hh, ii, ss, ms	= "";	// 변수 초기화.
		obj_d	= new Date();				// Date 객체선언.
	
		yyyy	= obj_d.getFullYear();														// 년
		if(yyyy <= 1900) yyyy+=1900;														// 년
		mm		= obj_d.getMonth()<9	? "0"+(obj_d.getMonth()+1)	: obj_d.getMonth()+1;	// 월
		dd		= obj_d.getDate()<10	? "0"+(obj_d.getDate())		: obj_d.getDate();		// 일
		hh		= obj_d.getHours()<10	? "0"+(obj_d.getHours())	: obj_d.getHours();		// 시
		ii		= obj_d.getMinutes()<10 ? "0"+(obj_d.getMinutes())	: obj_d.getMinutes();	// 분
		ss		= obj_d.getSeconds()<10 ? "0"+(obj_d.getSeconds())	: obj_d.getSeconds();	// 초
//		ms		= obj_d.getMs()<10		? "0"+(obj_d.getMs())		: obj_d.getMs();		// 밀리초
	
		sBand	= get_sband(hh);
	
		//히든 input 값
		$("#sCaller").val(sCaller);
		$("#sCallee").val(sCallee);
		$("#sResult").val(sResult);
		$("#sClSeqno").val(sClSeqno);
		$("#sMiSeqno").val(sMiSeqno);
	
		$("#sDate").val(yyyy +""+ mm +""+ dd +""+ hh +""+ ii +""+ ss);
		$("#sBand").val(sBand);
	
		$("#yyyy").val(yyyy);
		$("#mm").val(mm);
		$("#dd").val(dd);
		$("#hh").val(hh);
		$("#ii").val(ii);
		$("#ss").val(ss);
	
		//로그저장
		$("#formCallLogSvr").submit();
	}
//통화내역 시간 대역
	function get_sband(time_hour){
		var time_band;
	
		switch (time_hour) {
	//새벽 : 00~07	= A
			case "00":	time_band = "A"; break;
			case "01":	time_band = "A"; break;
			case "02":	time_band = "A"; break;
			case "03":	time_band = "A"; break;
			case "04":	time_band = "A"; break;
			case "05":	time_band = "A"; break;
			case "06":	time_band = "A"; break;
	//아침 : 07~09	= B
			case "07":	time_band = "B"; break;
			case "08":	time_band = "B"; break;
	//오전 : 09~11	= C
			case "09":	time_band = "C"; break;
			case "10":	time_band = "C"; break;
	//점심 : 11~14	= D
			case "11":	time_band = "D"; break;
			case "12":	time_band = "D"; break;
			case "13":	time_band = "D"; break;
	//오후 : 14~17	= E
			case "14":	time_band = "E"; break;
			case "15":	time_band = "E"; break;
			case "16":	time_band = "E"; break;
	//저녁 : 17~21	= F
			case "17":	time_band = "F"; break;
			case "18":	time_band = "F"; break;
			case "19":	time_band = "F"; break;
			case "20":	time_band = "F"; break;
	//야간 : 21~24	= G
			case "21":	time_band = "G"; break;
			case "22":	time_band = "G"; break;
			case "23":	time_band = "G"; break;
	//기본값	= D
			default :	time_band = "D"; break;
		}
		return time_band;
	}

</script>
<%-- 로그저장 --%>

	<%-- <a href="javascript:alertArea(240,40,'test','test dd<br>asdfasd');">test</a> --%>
	<div id="alertPop" style="display:none"></div>


	<!-- 약관동의 START -->
	<div id="background_gray" style="display:none;position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50); z-index:999;"></div>
	<div id="alertAgreement" style="display:none;position:absolute; top:0; left:0; width:100%; height:100%;z-index:9999;">
		<div id="popup" style="margin:0 auto;margin-top:50px;width:700px;scrolling;">
			<div class="pop_top">
				<div class="pop_x" style="margin-left:100px"></div>
				<div class="pop_tit">이용 약관 및 개인정보취급방침 동의</div>
			</div>
			
			<div style="margin:0 auto; margin-top:10px;margin-left:15px;"><b>이용약관</b></div>
			<div id="alertTxt1" style="overflow:auto; margin:0 auto;margin-top:0px;width:660px;height:200px; ">
				<jsp:include page="/WEB-INF/views/common/ifrmAgreement.jsp"/>
			</div>
			<br><br>
			
			<div style="margin:0 auto; margin-top:10px;margin-left:15px;"><b>개인정보 취급방침</b></div>
			<div id="alertTxt2" style="overflow:auto; margin:0 auto;margin-top:0px;width:660px;height:200px; ">
				<jsp:include page="/WEB-INF/views/common/ifrmPersonal.jsp"/>
			</div>
		<div style="margin:0 auto;margin-top:5px;margin-left:200px;width:550px;height:30px">
			<input id="mastershop_agreement1" name="mastershop_agreement1" type="checkbox" value="T" /> 서비스 이용약관 동의
			<input id="mastershop_agreement2" name="mastershop_agreement2" type="checkbox" value="T" /> 개인정보 취급방침 동의
		</div>
		<div style="margin:0 auto;width:150px;height:30px">
			<a href="javascript:closeAgreement();"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>
			<a href="javascript:logoutAgreement();"><img src="/resources/images/shopmaster/btn_close.gif" border="0"/></a>
		</div>
		</div>
	</div>
	<!-- 약관동의 END -->


<!-- Open Api ActiveX -->
<!-- <OBJECT style="width:0px;height:0px" ID="KTOpenAPIX" CLASSID="CLSID:16AB1B2A-A22E-4FAC-92CB-84102DF5CE3D"></OBJECT> -->

	<!-- 로그인시 가져가는 값 (id 값으로 가져감) -->
	<form class="openApiLoginForm" 			name="openApiLoginForm" method="post">
		<input type="hidden" id="authkey" 	name="authkey" 		value="${openapi_authkey }" />
		<input type="hidden" id="loginID" 	name="loginID" 		value="" />
		<input type="hidden" id="loginPWD" 	name="loginPWD" 	value="" />
		<input type="hidden" id="sSendDate" name="sSendDate" 	value="" />
		<input type="hidden" id="server" 	name="server" 		value="${openapi_server }" /><!-- 서버 종류선택 개발의 경우 0 , 상용의경우 1 -->
		<input type="hidden" id="fd_openapi_autologin" name="fd_openapi_autologin" value="${loginInfo.fd_openapi_autologin }" /><!-- 자동로그인여부 -->
	</form>

	<!-- api고객정보 -->
<%-- 로그저장 --%>
	<form id="formCallLogSvr" method="get" target="iframeCallLogSvr" action="/callLog/take_log.do">
		<input type="hidden" id="sCaller"	 name="sCaller"		 value=""/>				<%-- //발신전화번호 --%>
		<input type="hidden" id="sCallee"	 name="sCallee"		 value=""/>				<%-- //수신전화번호 --%>
		<input type="hidden" id="sResult"	 name="sResult"		 value=""/>				<%-- //결과코드 --%>
		<input type="hidden" id="sResultTxt" name="sResultTxt"	 value=""/>				<%-- //결과텍스트 --%>
		<input type="hidden" id="sClSeqno"	 name="sClSeqno"	 value=""/>				<%-- //통화 일련번호 --%>
		<input type="hidden" id="sMiSeqno"	 name="sMiSeqno"	 value=""/>				<%-- //발신사용자 일련번호 --%>

		<input type="hidden" id="sDate"		name="sDate"	value="">
		<input type="hidden" id="sBand"		name="sBand"	value="">

		<input type="hidden" id="yyyy"		name="yyyy"		value="">
		<input type="hidden" id="mm"		name="mm"		value="">
		<input type="hidden" id="dd"		name="dd"		value="">
		<input type="hidden" id="hh"		name="hh"		value="">
		<input type="hidden" id="ii"		name="ii"		value="">
		<input type="hidden" id="ss"		name="ss"		value="">
	</form>
<%-- 로그저장 --%>

	<!-- TEST용 -->
<%-- <div onclick='logSaveList("0226962520","01027331985","201","12345")'>TEST</div> --%>


<!-- POPUP 영역 - E -->

	<!-- 통화오픈API 로그인창 (on/off) -->
	<div id="openApiDiv" style="display:none;position:absolute;text-align:center;width:100%;height:1px;z-index:10000;">
		<div id="popup" style="height:250px;">
			<div class="pop_top2">
				<div class="pop_x"><a href="javascript:closeApiDiv('openApiDiv')"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
		    	<div class="pop_tit2">통화 Open API 로그인</div>
		    </div>
		    <div class="pop_txt" style="height:124px;">
		    	<div class="pop_loginbox">
		        	<div style="float:left; height:22px; width:50px; padding-top:3px;">아이디</div>
		        	<div style="float:left; height:25px; width:135px;"><input type="text" class="fild_st" style="width:120px;" id="apiId" name="apiId" value="${loginInfo.fd_openapi_member_id }"></div>
		        	<div style="float:left; height:22px; width:50px; padding-top:3px;">패스워드</div>
		        	<div style="float:left; height:25px; width:135px;"><input type="password" class="fild_st" style="width:120px;" id="apiPwd" name="apiPwd" value="${loginInfo.fd_openapi_member_pwd }"></div>
		        </div>
		    	<div style="float:right;width:275px;">
		        	<div class="pop_api" style="text-align:left">고객님께서는 KT 부가상품인 ‘통화 Open API’ 상품에 가입되어 있습니다.</div>
		            <div class="pop_api" style="text-align:left">‘통화 Open API’ 상품에서 제공하는 CDR 데이터의 저장 및 가공을 ‘샵 마스터’ 서비스에 위임하시겠습니까?</div>
		            <div class="pop_api" style="text-align:left">(동의하지 않을 경우, 샵 마스터 서비스에서 제공하는 통화 수신내역, 상담 및 컨설팅 등의 이용이 제한될 수 있습니다.)</div>
		            <div class="pop_api" style="margin-top:2px;">
		            	<div style="float:left;"><input id="openapi_chk_agree" name="openapi_chk_agree" type="checkbox" value="T" /></div>
		                <div style="float:left; padding-top:3px; ">동의합니다</div>
		            </div>
		        </div>
		    </div>
			<div class="pop_btn2">
		        <div style="float:left; padding-top:1px;"><input id="chk_autologin" name="chk_autologin" type="checkbox" value="T" ${loginInfo.fd_openapi_autologin == "T" ? "CHECKED" : ""} /></div>
		        <div style="float:left; padding-top:5px;">자동 로그인</div>
		        <div style="float:right;">
		        	<span id="openApiLoginBtn" style="display:none;">
		        		<a href="javascript:openApiLogin()"><img src="/resources/images/shopmaster/btn_login.gif" border="0"/></a>&nbsp;
		        	</span>
		        	<a href="javascript:closeApiDiv('openApiDiv')"><img src="/resources/images/shopmaster/btn_close.gif" border="0"/></a>
		        </div>
			</div>
		</div>
	</div>
	<!-- api로그인창 (on/off) -->


	
	<form id="customerForm" name="customerForm" method="post">
		<div id="openApiMemberDiv" style="position:absolute;width:100%;height:0px;z-index:10000;">
	
			<!-- 회윈리스트 -->
			<div id="openApiMemberDivList" style="display:none;position:absolute;width:100%;height:1px;">
	
				<div id="popup">
					<div class="pop_top2">
						<div class="pop_x"><a href="javascript:closeApiDiv('openApiMemberDivList')"><img src="/resources/images/shopmaster/btn_popx.gif" title="알림닫기" border="0"/></a></div>
						<div class="pop_tit2">부재 중 수신내역</div>
					</div>
					<div class="pop_txt">
						<div style="padding-top:20px;">최근 부재 중 수신내역</div>
						<div style="padding-top:8px;">
						<table id="apiRcvList" width="470" border="0" cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<td colspan="3" height="2" bgcolor="#222222"></td>
								</tr>
								<tr>
									<th width="165" class="pop_tb_tit_l">수신일시</th>
									<th width="140" class="pop_tb_tit_l">이름</th>
									<th width="165" class="pop_tb_tit_l">수신번호</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						</div>
					</div>
					<div class="pop_btn">
						<a href="javascript:closeApiDiv('openApiMemberDivList')"><img src="/resources/images/shopmaster/btn_close.gif" alt="닫기" border="0"/></a>
					</div>
				</div>
			</div>
			<!-- 회윈리스트 -->
	
	
			<!-- 회윈수정 : 최근 수신내역 리스트 존재 -->
			<div id="openApiMemberDivMod" style="display:none;position:absolute;top:47px;left:20px;width:100%;height:1px;">
				<div id="popup" style="height:470px;">
					<div class="pop_top2">
						<div class="pop_x2"><a href="javascript:closeApiDiv('openApiMemberDivMod')"><img src="/resources/images/shopmaster/btn_popx.gif" title="수정닫기" border="0"/></a></div>
						<div class="pop_tit2">전화수신 알림</div>
					</div>
					<div class="pop_txt">
						<input type="hidden" id="memberNameMod" name="memberNameMod"/>
						<input type="hidden" id="memberTelMod" name="memberTelMod"/>
						<input type="hidden" id="memberSeqnoMod" name="memberSeqnoMod"/>
						<input type="hidden" id="memberViewChkMod" name="memberViewChkMod"/>
	
					  <div><font style="color:#999999;" class="pop_datetxt"></font></div>
					  <div class="pop_txt2"><font class="pop_sttxt"></font>님으로 부터 전화를 수신하였습니다.</div>
					  <div style="padding-top:20px;">주소　<input id="memberAddrMod" name="memberAddrMod" type="text" class="fild_st" style="width:420px;"></div>
					  <div style="padding-top:8px;">메모　<input id="memberMemoMod" name="memberMemoMod" type="text" class="fild_st" style="width:420px;"></div>
						<div style="padding-top:20px;">최근 수신내역</div>
						<div style="padding-top:8px;">
							<table width="470" border="0" cellspacing="0" cellpadding="0" id="apiMemberList">
								<thead>
									<tr>
										<td colspan="2" height="2" bgcolor="#222222"></td>
									</tr>
									<tr>
										<th width="140" class="pop_tb_tit_l">수신일시</th>
										<th width="330" class="pop_tb_tit_l">메모</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="pop_btn">
						<a href="javascript:openApiMemberAdd('Mod','save')"><img src="/resources/images/shopmaster/btn_save.gif" border="0"/></a>&nbsp;
						<a href="javascript:closeApiDiv('openApiMemberDivMod')"><img src="/resources/images/shopmaster/btn_close.gif" border="0"/></a>
					</div>
				</div>
			</div>
			<!-- 회윈수정 -->
	
	
	
			<!-- 회원등록 : 리스트가 없는 전화번호만 표기 -->
			<div id="openApiMemberDivIns" style="display:none;position:absolute;top:47px;left:20px;width:100%;height:1px;">
				<div id="popup" style="height:270px;">
					<div class="pop_top2">
						<div class="pop_x2"><a href="javascript:openApiMemberAdd('Ins','close')"><img src="/resources/images/shopmaster/btn_popx.gif" title="등록닫기" border="0"/></a></div>
				    	<div class="pop_tit2">전화수신 알림</div>
				    </div>
				    <div class="pop_txt">
						<input type="hidden" id="memberTelIns" name="memberTelIns" />
						<input type="hidden" id="memberSeqnoIns" name="memberSeqnoIns" />
	
				        <div><font style="color:#999999;">&nbsp;</font></div>
				        <div class="pop_txt2"><font class="pop_sttxt"> </font>님으로 부터 전화를 수신하였습니다.</div>
				        <div style="padding-top:20px;">이름　<input id="memberNameIns" name="memberNameIns" type="text" class="fild_st" style="width:100px;"></div>
				        <div style="padding-top:8px;">주소　<input id="memberAddrIns" name="memberAddrIns" type="text" class="fild_st" style="width:420px;"></div>
				        <div style="padding-top:8px;">메모　<input id="memberMemoIns" name="memberMemoIns" type="text" class="fild_st" style="width:420px;"></div>
				        <div style="padding-top:12px;">
				      		<div style="float:left;"><input id="memberViewChkIns" name="memberViewChkIns" type="checkbox" value="1" /></div>
							<div style="float:left; padding-top:3px;">회원정보에 자동 저장하지 않기</div>
				       </div>
				    </div>
					<div class="pop_btn">
						<a href="javascript:openApiMemberAdd('Ins','save')"><img src="/resources/images/shopmaster/btn_save.gif" border="0"/></a>&nbsp;
						<a href="javascript:openApiMemberAdd('Ins','close')"><img src="/resources/images/shopmaster/btn_close.gif" border="0"/></a>
					</div>
				</div>
			</div>
			<!-- 회원등록 -->

		</div>
	</form>
	<!-- api고객정보 -->
<!-- POPUP 영역 - E -->


<!-- LNB 보여지는영역 - S -->
<div class="ml_info">

	<!-- 로그인 현황창(회원id, 월정액, 보너스, api로그인버튼 -->
	<div style="float:left; width:126px;"><font color="#49adeb">${loginInfo.fd_user_name}</font> 님</div>
	<div style="float:left; height:16px; width:50px;"><a href="#" onclick="goLogout();"><img src="/resources/images/shopmaster/btn_ml_logout.gif" border="0"/></a></div>
	<div style="float:left; width:50px; margin-top:15px;">총 코인</div>
	<div style="float:left; width:126px; text-align:right; margin-top:15px;">
		<font color="#49adeb">
			<span id="charge_total_coin">
				<fmt:formatNumber value="${coin.fd_total_coin }" pattern="#,###"/>
			</span>
		</font> coin
	</div>
	<div style="float:left; width:70px; margin-top:10px; font-size:13px;">&nbsp;· 충전코인</div>
	<div style="float:left; width:106px; text-align:right; margin-top:10px; font-size:13px;">
		<font color="#49adeb">
			<span id="charge_recharge_coin">
				<fmt:formatNumber value="${coin.fd_recharge_coin + coin.fd_bonus_coin}" pattern="#,###"/>
			</span>
		</font> coin
	</div>
	<div style="float:left; width:70px; margin-top:7px; font-size:13px;">&nbsp;· 무료코인</div>
	<div style="float:left; width:106px; text-align:right; margin-top:7px; font-size:13px;">
		<font color="#49adeb">
			<span id="charge_base_coin">
				<fmt:formatNumber value="${coin.fd_base_coin }" pattern="#,###"/>
			</span>
		</font> coin
	</div>
	
	<span id="id_link_openapi_login"  style="float:left; width:176px; text-align:right; margin-top:20px;"><a href="javascript:openApiLoginOn()" class="ml_a">통화 Open API 로그인 ></a></span>
	<span id="id_link_openapi_logout" style="display:none;float:left; width:176px; text-align:right; margin-top:20px;"><a href="javascript:APILogout();" class="ml_a">통화 Open API 로그아웃 ></a></span>
	<!--<div style="float:left; width:176px; text-align:right; margin-top:10px;"><a href="javascript:top.left_frame.LineJoin()" class="ml_a">회선청약 (운영, 개발 업체) ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:10px;"><a href="javascript:top.left_frame.UserJoinEX()" class="ml_a">회원가입 (회원) ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:10px;"><a href="javascript:top.left_frame.SetMyInfo()" class="ml_a">환경설정 ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:10px;"><a href="javascript:top.left_frame.FindPasswdEX()" class="ml_a">비밀번호 찾기 ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:10px;"><a href="javascript:top.left_frame.HelpEX()" class="ml_a">도움말 ></a></div> -->
	<!-- 로그인 현황창(회원id, 월정액, 보너스, api로그인버튼 -->
</div>
<!-- LNB 보여지는영역 - E -->



<!-- 통화오픈API 로그인 완료후 업데이트처리 -->
<iframe id="left_ifrm" name="left_ifrm" width="0px" height="0px" frameborder="0" src=""></iframe>
<iframe id="iframeCallLogSvr" name="iframeCallLogSvr" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" style="width:0px; height:0px;"></iframe>