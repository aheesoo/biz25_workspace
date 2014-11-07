<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

	<script src="/resources/js/jquery-1.11.0.js"></script>
	<script src="/resources/js/api_js.js"></script>
	<script src="/resources/js/commonLibrary.js"></script>

	<!-- OPEN API 로그인 -->
<!-- 	<SCRIPT type="text/javascript" language="JavaScript" src="api_js.js"></script> -->

	<SCRIPT FOR="KTOpenAPIX" EVENT="EventLogin(nResult)" LANGUAGE="Jscript">
	//로그인 이벤트
		top.right_frame.APIEventLogin(nResult);
	
		var frm = document.leftForm;
		frm.checkApi.value = "LOGIN"+nResult;
		
	</SCRIPT>

	<SCRIPT FOR="KTOpenAPIX" EVENT="EventCID( sCaller, sCallee, sResult, sClSeqno, sMiSeqno)" LANGUAGE="Jscript">
	//전화 수신 통지 이벤트
		top.right_frame.call_log(sCaller, sCallee, sResult, sClSeqno, sMiSeqno);
		top.right_frame.APIEventCID( sCaller, sCallee, sResult, sClSeqno, sMiSeqno);
	</SCRIPT>
	
	<SCRIPT FOR="KTOpenAPIX" EVENT="EventCTC(sCaller, sCallee, sResult, sMiSeqno)" LANGUAGE="Jscript">
	//전화 걸기 이벤트
		top.right_frame.APIEventCTC(sCaller, sCallee, sResult, sMiSeqno);
	</SCRIPT>
	
	<SCRIPT FOR="KTOpenAPIX" EVENT="EventCON( sCaller, sCallee, sCallBackID, sResult)" LANGUAGE="Jscript">
	//전화걸기한 호 종료 응답 이벤트
		top.right_frame.APIEventCON( sCaller, sCallee, sCallBackID, sResult);
	</SCRIPT>


	<script type="text/javascript">
	
		
		$(document).ready(function(){
			
			
			if( getCookie("LEFT_LOAD_CNT")!="" && getCookie("LEFT_LOAD_CNT")!="NaN"){
				var cnt = parseInt(getCookie("LEFT_LOAD_CNT"), "10");
				setCookie("LEFT_LOAD_CNT", cnt+1, 30000);
				
				//alert("openApiObject ready cnt+1="+cnt+1);
				
			}else{
				//최초접속시 아무것도 쿠키값도 없을경우 : LEFT_LOAD_CNT=0
				setCookie("LEFT_LOAD_CNT", 0, 30000);
			}

		});
	
		//right_frame 이 리로딩시마다 호출
		function checkReload(){
			
			setTimeout(function(){
				
				var cnt = parseInt(getCookie("LEFT_LOAD_CNT"), "10");

				//최초접속시 아무것도 쿠키값도 없을경우 : LEFT_LOAD_CNT=0
				//alert("checkReload cnt="+cnt);
				
				if(cnt >= 2){

					//left_frame이 리로딩 된것임
					setCookie("LEFT_LOAD_CNT", 0, 30000);

					var frm = document.leftForm;
					
					//left 통화오픈api 로그인상태에 따른 노출
					var cookiedata = document.cookie;    
					if(cookiedata.indexOf("OPEN_LOGIN=T")>=0)
					{
						//로그인 상태(쿠키값) 에서 리로딩 되었기 때문에 통화 오픈api 로그아웃과 다시 로그인이 필요

						//페이지 리로드시 로그인 상태면 재 로그인 처리
						top.left_frame.QuickLogout();
						
						setTimeout(function(){
							try{
								top.right_frame.openApiLogin();
							}catch(e){}
						}, 1000)
					}
				}
			}, 1000)
		}
		
		
		function APILogin(){
			Login();
		}
	</script>

</head>
<body>

<!-- Open Api ActiveX -->
<OBJECT style="width:0px;height:0px" ID="KTOpenAPIX" CLASSID="CLSID:16AB1B2A-A22E-4FAC-92CB-84102DF5CE3D"></OBJECT>

<!-- ActiveX Reloading 시 액티브X 기능 실행안됨 -->
<form name="leftForm">
	<input type="text" name="checkApi" value="">
</form> 
</body>
</html>





