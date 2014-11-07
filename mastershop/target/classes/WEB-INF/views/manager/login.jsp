<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:noFrame>
	<jsp:attribute name="stylesheet">
		<style>
			#adbox{margin:0px 0 0 20px;}
			#adbox .nowrap{white-space: nowrap; margin:0; padding:0;}
			#img02, #img03, #img04{display:none;}
		</style>
	</jsp:attribute>
		
	<jsp:attribute name="javascript">

		<script language="javascript">
			${script}
			
			//alert(document.location.protocol);
			
			//if(document.location.protocol = "http:"){
			//	document.location.href = document.location.href.replace("http:","https:");
			//}
			
			
			setCookie("AUTO", "T", 3000); 			//3000시간
			setCookie("OPEN_LOGIN","F", 30000); 	//30000시간
			setCookie("LEFT_LOAD_CNT", 0, 30000);	//LEFT_LOAD_CNT 쿠키값 0 초기화

			var thisimg = 1;
			var maximg = 4;
			var boxfocus = false;
			var pwdClear = false;
			
			$(document).ready(function() {
				redirectToS();
				setInterval(imgrotation, 3000);
			});

			function redirectToS() 
			{
			   var httpURL = window.location.hostname + window.location.pathname; 
			   var httpsURL = "https://" + httpURL; 

			   //alert(document.location.protocol);
			   //alert("httpURL="+httpURL);
			   //alert("httpsURL="+httpsURL);
			   
			   if(httpURL.indexOf("localhost") > -1){
				   readySecond();
			   }else if(document.location.protocol == "http:"){
					location.href = httpsURL; 
			   }else{
				   readySecond();
			   }
			} 
			
			function readySecond()
			{
				//alert("function");
				
				
				$('#id').focus();
				$('#pwd').keydown
				(
					function(event)
					{
						if (event.keyCode == 13 )
						{
							if(validate(document.forms[0])){									
									 login();
							}
						}
					}
				);

 				$('#btnLogin').click
 				(
 					function() { 
	 					if(validate(document.forms[0])){
			 					login();
	 					}
	 				}
 				);

 				$('#btnReqAccount').click(function(){ reqAccount(); });
 				
 				
 				//자동 로그인
 				if(pwdClear == false){
					if( ( $('#id').val() != "" ) &&
		 					( $('#pwd').val() != "" ) &&
		 					( $('#autoLogin').is(":checked") ) ){
	
						login();
		 			}
 				}
			}
			
			
			function clearPwd(){
				pwdClear = true;
			}
			
			//cookie 처리
			function setCookie( name, value, expirehours ) { 
				var todayDate = new Date(); 
				todayDate.setHours( todayDate.getHours() + expirehours ); 
				document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";";
			} 
			
			function login()
			{
				var form = document.loginForm;
				form.event.value = 'loginProc';
				form.submit();
			}

			function reqAccount()
			{
				var w = 400;
				var h = 350;
				var x = ( screen.width - w)/2 - 10;
				var y = ( screen.height - h)/2 - 10;
				var url = "/manager/reqAccount.do";
				var exp = "width=" + w + ",height=" + h + ",top=" + y + ",left=" + x + ",status=no,resizable=no,toolbar=no,scrollbars=no";
				window.open(url, "reqAccount", exp);
			}

			/* 아이디 찾기 */
			function reqSearchId(){
				var w = 519;
				var h = 320;
				var x = ( screen.width - w)/2 - 10;
				var y = ( screen.height - h)/2 - 10;
				var url = "/manager/searchId.do";
				var exp = "width=" + w + ",height=" + h + ",top=" + y + ",left=" + x + ",status=no,resizable=no,toolbar=no,scrollbars=no";
				window.open(url, "searchId", exp);
			}

			/* 비밀번호 찾기 */
			function reqSearchPw(){
				var w = 519;
				var h = 270;
				var x = ( screen.width - w)/2 - 10;
				var y = ( screen.height - h)/2 - 10;
				var url = "/manager/searchPw.do";
				var exp = "width=" + w + ",height=" + h + ",top=" + y + ",left=" + x + ",status=no,resizable=no,toolbar=no,scrollbars=no";
				window.open(url, "searchPw", exp);
			}

			/* 메인 롤링 */
			function getnextimg(ix) {
				if(ix + 1 > maximg) return 1;
				return ix+1;
			}
			/* 메인 롤링*/
			function imgrotation() {
				$("#img0"+thisimg).attr("src","/resources/images/shopmaster/main_banner/intro_01_0"+thisimg+".jpg");
				$("#img0"+thisimg).hide();
				nextimg = getnextimg(thisimg);
				$("#img0"+nextimg).attr("src","/resources/images/shopmaster/main_banner/intro_01_0"+nextimg+".jpg");
				$("#img0"+nextimg).fadeIn('slow');
				thisimg = nextimg;
			}
			
			//최초 로그인 이용약관 동의
			function openAgreement(){
				$('#alertPersonal').hide();
				$('#alertAgreement').show();
			}
			
			//이용 약관 동의 처리
			function closeAgreement(){
				$('#alertAgreement').hide();
			}
			
			//최초 로그인 이용약관 동의
			function openPersonal(){
				$('#alertAgreement').hide();
				$('#alertPersonal').show();
			}
			
			//이용 약관 동의 처리
			function closePersonal(){
				$('#alertPersonal').hide();
			}
			
			function addFavorite(){
				var title = document.title; //현재 보고 있는 페이지의 Title
				var url = location.href; //현재 보고 있는 페이지의 Url
				window.external.AddFavorite('https://www.mastershop.kr/', title);
			}
			/*
			function addBookmark(){
				//var title = "[Viewit] 통신, IT, 모바일, 스마트폰 앱스 블로그";
				//var url = "http://viewit.kr";
				var title = document.title; //현재 보고 있는 페이지의 Title
				var url = location.href; //현재 보고 있는 페이지의 Url
				alert(document.all);
				if(window.sidebar && window.sidebar.addPanel){ // Firefox
					window.sidebar.addPanel(title, url,"");
				}
				else if(window.opera && window.print){ // Opera
					var elem = document.createElement('a');
					elem.setAttribute('href',url);
					elem.setAttribute('title',title);
					elem.setAttribute('rel','sidebar');
					elem.click();
				}
				else if(document.all){ // Internet Explorer
					window.external.AddFavorite( url, title);
				}
				else{
					alert("이용하시는 웹 브라우저는 기능이 지원되지 않습니다.\n\nCtrl+D 키를 누르시면 즐겨찾기에 추가하실 수 있습니다.");
				}
			}
			*/
		</script>
	</jsp:attribute>

<jsp:body>
<div id="wrap">


	<!-- 약관동의 START -->
	<div id="alertAgreement" style="display:none;position:absolute; top:0; left:0; width:100%; height:100%;z-index:9999;">
		<div id="popup" style="margin:0 auto;margin-top:50px;width:700px;scrolling;">
			<div class="pop_top">
				<div class="pop_x" style="margin-left:100px"></div>
				<div class="pop_tit">이용 약관 및 개인정보취급방침</div>
			</div>
			
			<div style="margin:0 auto; margin-top:10px;margin-left:15px;"><b>이용약관</b></div>
			<div id="alertTxt1" style="overflow:auto; margin:0 auto;margin-top:0px;width:660px;height:500px; ">
				<jsp:include page="/WEB-INF/views/common/ifrmAgreement.jsp"/>
			</div>
			<br><br>

		<div style="margin:0 auto;width:150px;height:30px">
			<a href="javascript:closeAgreement();"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>
		</div>
		</div>
	</div>
	<!-- 약관동의 END -->

	<!-- 개인정보 취급방침 START -->
	<div id="alertPersonal" style="display:none;position:absolute; top:0; left:0; width:100%; height:100%;z-index:9999;">
		<div id="popup" style="margin:0 auto;margin-top:50px;width:700px;scrolling;">
			<div class="pop_top">
				<div class="pop_x" style="margin-left:100px"></div>
				<div class="pop_tit">이용 약관 및 개인정보취급방침</div>
			</div>
			
			<div style="margin:0 auto; margin-top:10px;margin-left:15px;"><b>개인정보취급방침</b></div>
			<div id="alertTxt1" style="overflow:auto; margin:0 auto;margin-top:0px;width:660px;height:500px; ">
				<jsp:include page="/WEB-INF/views/common/ifrmPersonal.jsp"/>
			</div>
			<br><br>

		<div style="margin:0 auto;width:150px;height:30px">
			<a href="javascript:closePersonal();"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>
		</div>
		</div>
	</div>
	<!-- 개인정보 취급방침 END -->
	
	<div id="intro_contents">
		<div id="intro_area">
			<div class="intro_img">
			<!-- 
		        <div class="intro_arr"><a href="#"><img src="/resources/images/shopmaster/intro_arr_l.png" border="0"/></a></div>
		        <div class="intro_arr" style="margin-left:1060px;"><a href="#"><img src="/resources/images/shopmaster/intro_arr_r.png" border="0"/></a></div>
		        <div class="intro_imgnum_area">
		        	<div class="intro_imgnum"><img src="/resources/images/shopmaster/intro_img_on.png" border="0"/></div>
		        	<div class="intro_imgnum"><img src="/resources/images/shopmaster/intro_img_off.png" border="0"/></div>
		        	<div class="intro_imgnum"><img src="/resources/images/shopmaster/intro_img_off.png" border="0"/></div>
		        	<div class="intro_imgnum"><img src="/resources/images/shopmaster/intro_img_off.png" border="0"/></div>
		        	<div class="intro_imgnum"><img src="/resources/images/shopmaster/intro_img_off.png" border="0"/></div>
		        </div>
		        <div><img src="/resources/images/shopmaster/intro_01.jpg" /></div>
		        <div><img src="/resources/images/shopmaster/intro_01_01.jpg" /><div>
		    -->
			    <div id="adbox">
				    <div class="nowrap">
				    	<img id='img01' src="/resources/images/shopmaster/main_banner/intro_01_01.jpg" alt="1" />
				    	<img id='img02' src="/resources/images/shopmaster/main_banner/intro_01_02.jpg" alt="2" />
				    	<img id='img03' src="/resources/images/shopmaster/main_banner/intro_01_03.jpg" alt="3" />
				    	<img id='img04' src="/resources/images/shopmaster/main_banner/intro_01_04.jpg" alt="4" />
				    </div>
			    </div>
		    </div>
		    <div class="intro_btn" ><!-- <a style="font-size:14px;" href="#" class="intro_t">서비스 소개</a> / --> <!-- <a style="font-size:14px;" href="#" class="intro_t">바로가기</a> /  --><a style="font-size:14px;" href="javascript:addFavorite()" class="intro_t">마스터샵 즐겨찾기 추가하기</a></div>
			<div class="intro_login">
				<form id="loginForm" name="loginForm" method="post">
					<div style="padding-top:50px;">
						<input type="hidden" name="event" />
						<input type="text" id="id" name="id" value="${id }" class="intro_fild" maxlength="50" minbytes="4" msg="아이디의 길이가 4자 이상이여야 합니다."/>
					</div>
					<div style="padding-top:5px;">
						<input type="password" class="intro_fild" id="pwd" name="pwd" value='${pwd }' maxlength="50" minbytes="6" msg="비밀번호의 길이가 6자 이상이여야 합니다."/>
					</div>
			    	<div style="padding-top:5px;"><a href="#" id="btnLogin"><img src="/resources/images/shopmaster/intro_login_btn.gif" border="0" /></a></div>
					<div style="text-align:left; padding-top:5px;">
			        	<div style="float:left;">
			        		<%-- 2014.09.30 QC 요청 처음 로그인시 자동 체크
			        		<input id="autoLogin" name="autoLogin" type="checkbox" value="TRUE"  ${check == "TRUE" ? "CHECKED" : ""} />
			        		 --%>
			        		 <input id="autoLogin" name="autoLogin" type="checkbox" value="TRUE" checked="checked" />
			        	</div>
						<div style="float:left;padding-top:3px;">자동로그인</div>
			        	<div style="float:right;padding-top:3px;"><!-- 520 x 165 -->
			        		<a href="javascript:reqSearchId();">아이디</a>/<a href="javascript:reqSearchPw();">비밀번호찾기</a>
			        	</div>
					</div>
				</form>
				<div class="clear"></div>
			</div>
		</div>
		

		<div class="intro_bottom">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		            <td colspan="2" height="2" background="/resources/images/shopmaster/bottom_line2.png"></td>
		        </tr>
		        <tr>
		          <td width="100" rowspan="4" align="center"><img src="/resources/images/shopmaster/bottom_logo.gif" /></td>
		          <td height="30" align="left">
		          	<font style="color:#666666;">
			          	<a href="http://www.kthcorp.com/intro" target="_blank" class="mbottom_a"><strong>회사소개</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			          	<a href="#" onclick="openAgreement();" class="mbottom_a"><strong>이용약관</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			          	<a href="#" onclick="openPersonal();" class="mbottom_a"><strong>개인정보취급방침</strong></a>
		          	</font>
		          </td>
		        </tr>
		        <tr>
		            <td height="18" align="left" style="padding-top:8px; line-height:20px;">
		            케이티하이텔(주) 대표이사 오세영 / 사업자등록번호 : 211-81-79649 / 통신판매업신고 : 04-680-176<br> 
					주소 : 서울특별시 동작구 보라매5길 23 대표전화 1899-1431</td>
		        </tr>
		        <tr>
		            <td height="16" align="left"><font style="color:#777777; font-size:11px;">Copyright(c) 2014 KT Hitel Co., Ltd. all rights reserved.</font></td>
		        </tr>
		    </table>
		</div>
	</div>
	
	
</div>

</jsp:body>
</layout:noFrame>