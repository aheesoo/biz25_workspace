<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="0">
		<title>MasterShop</title>	
		
		<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
		
		<script src="/resources/js/jquery-1.11.0.js"></script>
		<script src="/resources/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="/resources/js/commonLibrary.js"></script><!-- ## -->
		<script src="/resources/js/validate.js"></script><!-- ## -->	
		<!------------------------------------------------------------------------------- 
		* 웹SITE 가 https를 이용하면 https://plugin.inicis.com/pay61_secunissl_cross.js를 사용 
		* 웹SITE 가 Unicode(UTF-8)를 이용하면 http://plugin.inicis.com/pay61_secuni_cross.js를 사용 
		* 웹SITE 가 https, unicode를 이용하면 https://plugin.inicis.com/pay61_secunissl_cross.js 사용 
		--------------------------------------------------------------------------------> 
		<!---------------------------------------------------------------------------------- 
		※ 주의 ※ 
		 상단 자바스크립트는 지불페이지를 실제 적용하실때 지불페이지 맨위에 위치시켜 
		 적용하여야 만일에 발생할수 있는 플러그인 오류를 미연에 방지할 수 있습니다. 
		 
		  <script language=javascript src="http://plugin.inicis.com/pay61_secuni_cross.js"></script> 
		  <script language=javascript> 
		  StartSmartUpdate();   // 플러그인 설치(확인) 
		  </script> 
		----------------------------------------------------------------------------------->  
		<!-- 플러그인 6.0 위변조 모듈 크로스 브라우징 pay61_secuni_cross.js-->				
		<script language=javascript src="https://plugin.inicis.com/pay61_secunissl_cross.js"></script> 
		
		<script language=javascript>
		
			StartSmartUpdate();
		</script>
		
		<script language=javascript>
			
			var openwin;
			
			function pay(frm)
			{
				// MakePayMessage()를 호출함으로써 플러그인이 화면에 나타나며, Hidden Field
				// 에 값들이 채워지게 됩니다. 일반적인 경우, 플러그인은 결제처리를 직접하는 것이
				// 아니라, 중요한 정보를 암호화 하여 Hidden Field의 값들을 채우고 종료하며,
				// 다음 페이지인 INIsecureresult.php로 데이터가 포스트 되어 결제 처리됨을 유의하시기 바랍니다.

				if(document.ini.clickcontrol.value == "enable")
				{
					
					if(document.ini.goodname.value == "")  // 필수항목 체크 (상품명, 상품가격, 구매자명, 구매자 이메일주소, 구매자 전화번호)
					{
						alert("상품명이 빠졌습니다. 필수항목입니다.");
						return false;
					}					
					else if(( navigator.userAgent.indexOf("MSIE") >= 0 || navigator.appName == 'Microsoft Internet Explorer' ) &&  (document.INIpay == null || document.INIpay.object == null) )  // 플러그인 설치유무 체크
					{
						alert("\n이니페이 플러그인 128이 설치되지 않았습니다. \n\n안전한 결제를 위하여 이니페이 플러그인 128의 설치가 필요합니다. \n\n다시 설치하시려면 Ctrl + F5키를 누르시거나 메뉴의 [보기/새로고침]을 선택하여 주십시오.");
						return false;
					}
					else
					{
						/******
						 * 플러그인이 참조하는 각종 결제옵션을 이곳에서 수행할 수 있습니다.
						 * (자바스크립트를 이용한 동적 옵션처리)
						 */
						
									 
						if (MakePayMessage(frm))
						{
							disable_click();
							openwin = window.open("childwin.do","childwin","width=299,height=149");
							$("#ini").submit();		
							return true;
						}
						else
						{
							if(IsPluginModule()) {
								alert("결제를 취소하셨습니다.");
							}
							return false;
						}
					}
				}
				else
				{
					return false;
				}
			}

			function enable_click()	{
				document.ini.clickcontrol.value = "enable"
			}

			function disable_click(){
				document.ini.clickcontrol.value = "disable"
			}

			function focus_control(){
				if(document.ini.clickcontrol.value == "disable")
					openwin.focus();
			}
		</script>
			
		<script langauge="text/javascript">	
			${script}
			$(document).ready(function() {				
				
				enable_click();

			});

			$(window).load(function() {
				var strWidth;
				var strHeight;
				 
				//innerWidth / innerHeight / outerWidth / outerHeight 지원 브라우저
				if ( window.innerWidth && window.innerHeight && window.outerWidth && window.outerHeight ) {
					strWidth = $('#popup').outerWidth() + (window.outerWidth - window.innerWidth);
				    strHeight = $('#popup').outerHeight() + (window.outerHeight - window.innerHeight);
				}else {
				    var strDocumentWidth = $(document).outerWidth();
				    var strDocumentHeight = $(document).outerHeight();
				    
				    window.resizeTo ( strDocumentWidth, strDocumentHeight );
				    var strMenuWidth = strDocumentWidth - $(window).width();
				    var strMenuHeight = strDocumentHeight - $(window).height();
				    strWidth = $('#popup').outerWidth() + strMenuWidth;
				    strHeight = $('#popup').outerHeight() + strMenuHeight;
				}
				//alert(strWidth + " : " + strHeight );
				  //resize
				window.resizeTo( strWidth, strHeight );
			}); 
	

			function req_payProc(c_type){				
				var pay_type="";
				var frm = document.ini;
				
				
				if(c_type !=''){
					frm.gopaymethod.value=c_type;
					if(c_type=='Card'){
						pay_type = "2";
					}else if(c_type=='HPP'){						
						pay_type = "1";
						alert("현재 신용카드 결제만 가능합니다.  휴대폰 결제 방법은 준비중입니다.");
						return;
					}else if(c_type=='DirectBank'){
						pay_type = "3";
						alert("현재 신용카드 결제만 가능합니다. 계좌이체 결제 방법은 준비중입니다.");
						return;
					}else if(c_type=='VBank'){
						pay_type = "4";
						alert("현재 신용카드 결제만 가능합니다. 무통장 입금 결제 방법은 준비중입니다.");
						return;
					}else{
						alert("프로그램 처리에 문제가 있습니다. 다시 시도해 주세요.");
						return;
					}
					var basePoint =frm.chargeBasePoint.value;
					var bonusPoint =frm.chargeBonusPoint.value;
					var money = frm.chargeMoney.value ;

					$("#pay_type").val(pay_type);

					pay(frm);
					
				}else{
					alert("잘못된 접근입니다.");
					return fasle;
				}
			}

			function commaNum(num) {  
				var len, point, str;  

				num = num + "";  
				point = num.length % 3  
				len = num.length;  

				str = num.substring(0, point);
				while (point < len) {
					if (str != "") str += ",";
					str += num.substring(point, point + 3);
					point += 3;
				}  
				return str;  
			}
			
		</script>
		<script language="JavaScript" type="text/JavaScript">
			<!--
			function MM_reloadPage(init) {  //reloads the window if Nav4 resized
			  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
			    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
			  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
			}
			MM_reloadPage(true);
			
			function MM_jumpMenu(targ,selObj,restore){ //v3.0
			  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
			  if (restore) selObj.selectedIndex=0;
			}
			//-->
		</script>
	</head>
	
	<body>
			<c:if test="${fn:length(rn_resultMsg)==0}">
			<div id="popup" style="height:360px;">
				<form name="ini" id="ini" method="post" action="/coin/coinChargeResult.do">
				<input type="hidden" name="event" id="event"value="proc"/>
				<input type="hidden" id="chargeBasePoint"		name="chargeBasePoint" 		value="${chargeBasePoint}" />
				<input type="hidden" id="chargeBonusPoint" 		name="chargeBonusPoint" 	value="${chargeBonusPoint}" />
				<input type="hidden" id="chargeMoney" 			name="chargeMoney" 			value="${chargeMoney}" />
				<input type="hidden" id="call_page" 				name="call_page" 				value="${call_page}" />	
				<input type="hidden" id="pay_type" 					name="pay_type" 				value="" />
				<input type="hidden" id="product_code" 			name="product_code" 			value="${product_code }" />
			
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:window.close();"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">코인 충전하기</div>
			    </div>
			    <div class="pop_txt">
			    	
			    		<%-- 
			    			gopaymethod : 결제 방법
			    			goodname :  결제 상품		    			
			    		 --%>
			    		<input type="hidden" id="gopaymethod" 	name="gopaymethod" 		value="" />		    		
			    		<input type="hidden" id="goodname" 		name="goodname" 			value="${goodname}" />
			    		
				    	<div class="article" style="line-height:200%; letter-spacing:1px;"><b>신용카드로만 결제가 가능합니다.<br>선택하신 결제금액을 확인하시고 결제하기 버튼을 눌러주세요.</b></div>
				        <table width="470" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td colspan="4" height="2" bgcolor="#222222"></td>
				          </tr>
				          <tr>
				            <td width="25%" class="tb_tit_c">충전 코인</td>
				            <td width="25%" class="tb_tit_c">보너스 코인</td>
				            <td width="25%" class="tb_tit_c">합계 코인</td>
				            <td width="25%" class="tb_tit_c">결제금액</td>
				          </tr>
				          <tr>
				            <td class="tb_nor_c40" style="color:#222222; line-height:16px;">
				            	<strong>
				            		<span id="rechargeCoin">
				            			<fmt:formatNumber value="${chargeBasePoint}" type="number"  groupingUsed="true"/>
				            		</span> 
				            	</strong><br />
				            	<%--
				            	<font class="ca_txt11_999999">Coin</font>
				            	 --%>
				            	 Coin
				            </td>
				            <td class="tb_nor_c40" style="color:#222222; line-height:16px;">
				            	<strong>
				            		<span id="bonusCoin">
				            			<fmt:formatNumber value="${chargeBonusPoint}" type="number"  groupingUsed="true"/>
				            		</span>
				            	</strong><br />
				            	<%--
				            	<font class="ca_txt11_999999">Coin</font>
				            	 --%>
				            	 Coin
				            </td>
				            <td class="tb_nor_c40" style="color:#222222; line-height:16px;">
				            	<strong>
				            		<span id="totalCoin">
				            			<fmt:formatNumber value="${chargeBasePoint+chargeBonusPoint}" type="number"  groupingUsed="true"/>
				            		</span>
				            	</strong><br />
				            	<%--
				            	<font class="ca_txt11_999999">Coin</font>
				            	 --%>
				            	 Coin
				            </td>
				            <td class="tb_nor_c40" style="color:#FF0000; line-height:16px;">
				            	<strong>
				            		<span id="payMoney">
				            			<fmt:formatNumber value="${chargeMoney}" type="number"  groupingUsed="true"/> 원
				            		</span>
				            	</strong><br />
				            	<font class="ca_txt11_999999">(부가세 10%)</font>
				            </td>
				          </tr>
				        </table>
				        <div style="margin-top:35px;text-align:center"><font style="font-size:13px; color:#222222; font-weight:bold"></font></div>
				        <div style="margin-top:10px;width:470px; height:100px;text-align:center;">
				        	<div style="text-align:center;"><a href="javascript:req_payProc('Card');"><img src="/resources/images/shopmaster/btn_save_ok_2.gif" border="0" /></a></div>
				        </div>
				        <%--
				    	<div style="margin-top:35px;text-align:center"><font style="font-size:13px; color:#222222; font-weight:bold">결제방법</font></div>
    					<div style="margin-top:10px;width:470px; height:100px;text-align:center;">
				    	<div style="text-align:center;"><a href="javascript:req_payProc('Card');"><img src="/resources/images/shopmaster/point_save2.gif" border="1" /></a></div>
				    		
				        	<div style="float:left;"><a href="javascript:req_payProc('HPP');"><img src="/resources/images/shopmaster/point_save1.gif" border="0" /></a></div>
				        	<div style="float:left;"><a href="javascript:req_payProc('Card');"><img src="/resources/images/shopmaster/point_save2.gif" border="0" /></a></div>
				        	<div style="float:left;"><a href="javascript:req_payProc('DirectBank');"><img src="/resources/images/shopmaster/point_save3.gif" border="0" /></a></div>
				        	<div style="float:left;"><a href="javascript:req_payProc('VBank');"><img src="/resources/images/shopmaster/point_save4.gif" border="0" /></a></div>
				        	
				        </div>
				         --%>
					
			    </div>
			
	<!-- 기타설정 -->
	<!--
	SKIN : 플러그인 스킨 칼라 변경 기능 - 5가지 칼라(ORIGINAL, GREEN, YELLOW, RED,PURPLE)
	HPP : 컨텐츠 또는 실물 결제 여부에 따라 HPP(1)과 HPP(2)중 선택 적용(HPP(1):컨텐츠, HPP(2):실물).
	Card(0): 신용카드 지불시에 이니시스 대표 가맹점인 경우에 필수적으로 세팅 필요 ( 자체 가맹점인 경우에는 카드사의 계약에 따라 설정) - 자세한 내용은 메뉴얼  참조.
	OCB : OK CASH BAG 가맹점으로 신용카드 결제시에 OK CASH BAG 적립을 적용하시기 원하시면 "OCB" 세팅 필요 그 외에 경우에는 삭제해야 정상적인 결제 이루어짐.
	no_receipt : 은행계좌이체시 현금영수증 발행여부 체크박스 비활성화 (현금영수증 발급 계약이 되어 있어야 사용가능)
	-->
	<input type="hidden" name="acceptmethod" value="SKIN(ORIGINAL):HPP(2)">
	<input type="hidden" name="currency" value="WON">
	
	
	<!--
	상점 주문번호 : 무통장입금 예약(가상계좌 이체),전화결재 관련 필수필드로 반드시 상점의 주문번호를 페이지에 추가해야 합니다.
	결제수단 중에 은행 계좌이체 이용 시에는 주문 번호가 결제결과를 조회하는 기준 필드가 됩니다.
	상점 주문번호는 최대 40 BYTE 길이입니다.
	## 주의:절대 한글값을 입력하시면 안됩니다. ##
	-->
	<input type="hidden" name="oid" size="40" value="orderid_of_merchant">
	
	
	
	<!--
	플러그인 좌측 상단 상점 로고 이미지 사용
	이미지의 크기 : 90 X 34 pixels
	플러그인 좌측 상단에 상점 로고 이미지를 사용하실 수 있으며,
	주석을 풀고 이미지가 있는 URL을 입력하시면 플러그인 상단 부분에 상점 이미지를 삽입할수 있습니다.
	-->
	<!--input type=hidden name=ini_logoimage_url  value="http://[사용할 이미지주소]"-->
	
	<!--
	좌측 결제메뉴 위치에 이미지 추가
	이미지의 크기 : 단일 결제 수단 - 91 X 148 pixels, 신용카드/ISP/계좌이체/가상계좌 - 91 X 96 pixels
	좌측 결제메뉴 위치에 미미지를 추가하시 위해서는 담당 영업대표에게 사용여부 계약을 하신 후
	주석을 풀고 이미지가 있는 URL을 입력하시면 플러그인 좌측 결제메뉴 부분에 이미지를 삽입할수 있습니다.
	-->
	<!--input type=hidden name=ini_menuarea_url value="http://[사용할 이미지주소]"-->
	
	<!--
	플러그인에 의해서 값이 채워지거나, 플러그인이 참조하는 필드들
	삭제/수정 불가
	uid 필드에 절대로 임의의 값을 넣지 않도록 하시기 바랍니다.
	-->
	<input type="hidden" name="ini_encfield" value="${ini_encfield}"/>
	<input type="hidden" name="ini_certid" value="${ini_certid}"/>
	<input type="hidden" name="buyername"  value="${member_name }"/>
	<input type="hidden" name="buyertel" value="${member_tel }"/>
	<input type="hidden" id="buyeremail" name="buyeremail" value="${member_id }"/>
	<input type="hidden" name="quotainterest" value=""/>
	<input type="hidden" name="paymethod" value=""/>
	<input type="hidden" name="cardcode" value=""/>
	<input type="hidden" name="cardquota" value=""/>
	<input type="hidden" name="rbankcode" value=""/>
	<input type="hidden" name="reqsign" value="DONE"/>
	<input type="hidden" name="encrypted" value=""/>
	<input type="hidden" name="sessionkey" value=""/>
	<input type="hidden" name="uid" value=""/> 
	<input type="hidden" name="sid" value=""/>
	<input type="hidden" name="version" value="5000"/>
	<input type="hidden" name="clickcontrol" value=""/>
	</form>
	</div>
	</c:if>
	<c:if test="${fn:length(rn_resultMsg)!=0}">
		모듈 에러
	</c:if>
		
	</body>
</html>