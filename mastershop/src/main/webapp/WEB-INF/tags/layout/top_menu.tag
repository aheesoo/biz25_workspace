<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

	//MENU Controll Start
	//<![CDATA[
	$(document).ready
	(
		function ()
		{
			var active = ($.cookie('topMenuActive') == 'undefined') ? 0 : $.cookie('topMenuActive');

			if(active){
				$('#'+active+'_menu').attr("class","sub_menu_now");
				switch(active) {
					case "cs_manager": 		$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu1_on.png"); break;
					case "tel_manager": 	$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu2_on.png"); break;
					case "sms_manager": 	$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu3_on.png"); break;
					case "report": 			$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu4_on.png"); break;
					case "cs_center":		$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu5_on.png"); break;
					case "my_info":			$('#'+active+'_menu > img').attr("src","/resources/images/shopmaster/top_menu6_on.png"); break;
				}
			}
		}
	);
		
	function viewMenu(idName, imgNum){
		$('#'+idName+'_menu').attr("class","sub_menu_now");
		$('#'+idName+'_menu > img').attr("src","/resources/images/shopmaster/top_menu" + imgNum + "_on.png");
		$('#'+idName+'_over').show();
	}

	function hiddenMenu(idName, imgNum){
		if(idName != $.cookie('topMenuActive')){
			$('#'+idName+'_menu').attr("class","sub_menu");
			$('#'+idName+'_menu > img').attr("src","/resources/images/shopmaster/top_menu" + imgNum + ".png");
		}else{
			$('#'+idName+'_menu > img').attr("src","/resources/images/shopmaster/top_menu" + imgNum + "_on.png");
		}
		$('#'+idName+'_over').hide();
	}

	//]]>
	//MENU Controll End
</script>


<!-- 고객관리 -->
<a href="javascript:moveMenu('/csManager/csManagerMain.do', 'cs_manager')"><div id="cs_manager_menu" class="sub_menu" onmouseover="viewMenu('cs_manager', '1')" onmouseout="hiddenMenu('cs_manager', '1')"><img src="/resources/images/shopmaster/top_menu1.png" border="0"/></div></a>
<div class="sub_menu_bar"></div>
<!-- 통화관리 -->
<a href="javascript:moveMenu('/telManager/telManagerMain.do', 'tel_manager')"><div id="tel_manager_menu" class="sub_menu" onmouseover="viewMenu('tel_manager', '2')" onmouseout="hiddenMenu('tel_manager', '2')"><img src="/resources/images/shopmaster/top_menu2.png" border="0"/></div></a>
<div class="sub_menu_bar"></div>
<!-- 문자관리 -->
<a href="javascript:moveMenu('/smsManager/smsManagerMain.do', 'sms_manager')"><div id="sms_manager_menu" class="sub_menu" onmouseover="viewMenu('sms_manager', '3')" onmouseout="hiddenMenu('sms_manager', '3')"><img src="/resources/images/shopmaster/top_menu3.png" border="0"/></div></a>
<div class="sub_menu_bar"></div>
<!-- 리포트 -->
<a href="javascript:moveMenu('/report/reportMain.do', 'report')"><div id="report_menu" class="sub_menu" onmouseover="viewMenu('report', '4')" onmouseout="hiddenMenu('report', '4')"><img src="/resources/images/shopmaster/top_menu4.png" border="0"/></div></a>
<div class="sub_menu_bar"></div>
<!-- 고객센터 -->
<a href="javascript:moveMenu('/csCenter/noticeList.do', 'cs_center')"><div id="cs_center_menu" class="sub_menu" onmouseover="viewMenu('cs_center', '5')" onmouseout="hiddenMenu('cs_center', '5')"><img src="/resources/images/shopmaster/top_menu5.png" border="0"/></div></a>
<div class="sub_menu_bar"></div>
<!-- 내정보 -->
<a href="javascript:moveMenu('/myInfo/joinInfo.do', 'my_info')"><div id="my_info_menu" class="sub_menu" onmouseover="viewMenu('my_info', '6')" onmouseout="hiddenMenu('my_info', '6')"><img src="/resources/images/shopmaster/top_menu6.png" border="0"/></div></a>
<div class="sub_menu_bar"></div>



<!-- 고객센터 메뉴 롤오버 시 -->
<div id="cs_center_over" class="cs_center_over" onmouseover="viewMenu('cs_center', '5')" onmouseout="hiddenMenu('cs_center', '5')" style="display:none">
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/csCenter/noticeList.do', 'cs_center')"><div class="sub_menu_over">공지사항</div></a>
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/csCenter/productInfo.do', 'cs_center')"><div class="sub_menu_over">상품안내</div></a>
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/csCenter/faqList.do', 'cs_center')"><div class="sub_menu_over">FAQ</div></a>
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/csCenter/agreement.do', 'cs_center')"><div class="sub_menu_over">이용약관</div></a>
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/csCenter/policy.do', 'cs_center')"><div class="sub_menu_over">개인정보취급방침</div></a>
    <div class="sub_menu_garobar"></div>
</div>

<!-- 내정보 메뉴 롤오버 시 -->
<div id="my_info_over" class="my_info_over" onmouseover="viewMenu('my_info', '6')" onmouseout="hiddenMenu('my_info', '6')" style="display:none">
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/myInfo/joinInfo.do', 'my_info')"><div class="sub_menu_over">가입정보</div></a>
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/myInfo/myPointCharge.do', 'my_info')"><div class="sub_menu_over">코인 충전내역</div></a>
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/myInfo/myPointUse.do', 'my_info')"><div class="sub_menu_over">코인 사용내역</div></a>
    <div class="sub_menu_garobar"></div>
	<a href="javascript:moveMenu('/myInfo/serviceCancel.do', 'my_info')"><div class="sub_menu_over">서비스 해지</div></a>
    <div class="sub_menu_garobar"></div>
</div>
