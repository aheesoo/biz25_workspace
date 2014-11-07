<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 메뉴 이동용 & jquey 쿠키 -->

	<script src="/resources/js/plugins/cookie/jquery.cookie.js"></script>
	<script type="text/javascript">
		$(document).ready(function (){
			var active = ($.cookie('topMenuActive') == 'undefined') ? 0 : $.cookie('topMenuActive');

			//alert("active : " + active);
			if(active){
				var link =  document.location.href;
				var link_array = link.split("/");
				var old_active = active;
				var imgNum = "";
				if(link_array[3] != "home"){
						
					//alert(link_array[3] + " : " + active);
					if(link_array[3] != active){

						if( active  == "cs_manager"){					
							imgNum = "1";
						}else if( active == "tel_manager"){
							imgNum = "2";
						}else if( active == "sms_manager"){
							imgNum = "3";
						}else if( active == "cs_center"){
							imgNum = "5";
						}else if( active == "my_info"){
							imgNum = "6";
						}else{					
							imgNum = "4";
						}
						
						if( link_array[3] == "csManager"){
							active = "cs_manager";					
						}else if( link_array[3] == "telManager"){
							active = "tel_manager";					
						}else if( link_array[3] == "smsManager"){
							active = "sms_manager";
						}else if( link_array[3] == "csCenter"){
							active = "cs_center";
						}else if( link_array[3] == "myInfo"){					
							active = "my_info";
						}else{
							active = link_array[3];
						}

						$('#'+old_active+'_menu').attr("class","sub_menu");
						$('#'+old_active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu" + imgNum + ".png");
						
						//alert("active2 : " + active );
						
						
						$('#'+active+'_menu').attr("class","sub_menu_now");
						switch(active) {
							case "cs_manager": 	$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu1_on.png"); break;
							case "tel_manager": 	$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu2_on.png"); break;
							case "sms_manager": 	$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu3_on.png"); break;
							case "report": 			$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu4_on.png"); break;
							case "cs_center":		$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu5_on.png"); break;
							case "my_info":			$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu6_on.png"); break;
						}
						$.removeCookie('topMenuActive', { path: '/' });	
						$.cookie('topMenuActive', active, { path: '/' });
						
					}
				}else{	
					$.removeCookie('topMenuActive', { path: '/' });
				}
				
				
			}
			
		});
	

		${script}
		
	
		function moveMenu(url, active){
			$('body').append("<form id='mainLayoutForm' name='mainLayoutForm' method='post'></form>");

			
			$.removeCookie('topMenuActive', { path: '/' });
			$.cookie('topMenuActive', active, { path: '/' });
				
			var form = document.mainLayoutForm;
			form.action = url;
			form.submit();
		}

		function coinPop(){
			var w = 975;
			var h = 600;
			var x = ( screen.width - w)/2 - 10;
			var y = ( screen.height - h)/2 - 10;
			var url = "/coin/popCharge.do";
			var exp = "width=" + w + ",height=" + h + ",top=" + y + ",left=" + x + ",status=no,resizable=no,toolbar=no,scrollbars=no";
			window.open(url, "coinPop", exp);
		}
	</script>


	<!-- leftMemu start -->
	<!-- Open API 로그인 처리 -->
	<c:import url="/common/openApiLogin.do" charEncoding="UTF-8"></c:import>
	<%-- <jsp:include page="/WEB-INF/views/common/openApiLogin.jsp">--%>
	<!-- Open API 로그인 처리 -->
	
	<div class="ml_bottom"><!-- 문자_예외처리_신청서.pdf -->
	
		<!-- 코인, 정액제 충전 시작 -->
		<!-- 기존 페이지
	    <div><a href="/home/coinCharge.do"><img src="/resources/images/shopmaster/btn_point.gif" border="0"/></a></div>
	     -->
	     <div><a href="javascript:coinPop();" ><img src="/resources/images/shopmaster/btn_point.gif" border="0"/></a></div>
	    <!-- 코인, 정액제 충전 끝 -->
	    

	    <div style="margin-top:5px;"><img src="/resources/images/shopmaster/btn_cs.gif" border="0"/></div>
	    <div style="margin-top:14px;"><img src="/resources/images/shopmaster/left_time.gif" border="0"/></div>
	    <div style="margin-top:15px;"><a href="/resources/down/SmsOverForm.pdf" target="_blank"><img src="/resources/images/shopmaster/btn_down_pdf.gif" border="0"/></a></div>
	    <!-- 
	    <div style="height:57px; text-align:center;padding-top:38px;"><img src="/resources/images/shopmaster/bottom_logo.png" border="0"/></div>
	    -->
	</div>
	<!-- leftMemu end -->
        
