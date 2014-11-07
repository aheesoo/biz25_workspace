<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MASTER SHOP</title>
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
	<script src="/resources/js/jquery-1.11.0.js"></script>
	<script src="/resources/js/commonLibrary.js"></script>
	
	<SCRIPT language= "JavaScript">
		//window.onload = function() {
		//document.right_frame.location.href = "/manager/login.do";
		//}
		
		//$(document).ready(function(){
		//});
	</SCRIPT>
	
	
</head>

<body class="main">

    <div id="wrapper">
        <div class="toparea"><a href="main.html"><img src="/resources/images/shopmaster/shopm_logo.gif" alt="Master Shop" /></a></div>
        <div class="lnb">
            <dl class="login">
                <dt><span>고객고객</span>님</dt>
                <dd><a href="login.html"><img src="/resources/images/shopmaster/btn_ml_logout.gif" border="0"/></a></dd>
            </dl>
            <dl class="coin total">
                <dt>총코인</dt>
                <dd><span>16,000</span></dd>
            </dl>
            <dl class="coin charge">
                <dt>· 충전코인</dt>
                <dd><span>14,000</span></dd>
            </dl>
            <dl class="coin free">
                <dt>· 무료코인</dt>
                <dd><span>2,000</span></dd>
            </dl>
            <div class="api_login"><a href="#">통화 Open API 로그인 ></a></div>
        </div>
        <div class="guide">
            <ul>
                <li><a href="my_point_save.html"><img src="/resources/images/shopmaster/btn_point.gif" alt="코인충전" /></a></li>
                <li><img src="/resources/images/shopmaster/btn_consult.gif" alt="문자관리 컨설팅 1899-1431" /></li>
                <li><img src="/resources/images/shopmaster/btn_cs.gif" alt="고객센터 1899-1431" /></li>
                <li><img src="/resources/images/shopmaster/left_time.gif" alt="상담시간:09:00~18:00, 점심시간 12:00~13:00 (주말 및 공휴일 제외)" /></li>
                <li><a href="https://www.mastershop.kr/resources/down/SmsOverForm.pdf" target="_blank"><img src="/resources/images/shopmaster/btn_down_pdf.gif" alt="문자메시지 발송량 제한 예외 신청서 다운로드" /></a></li>
            </ul>
        </div>
        <div class="cont_main">
            <ul>
                <li><a href="cs_admin.html"><img src="/resources/images/shopmaster/main_m1.gif" alt="고객관리"></a></li>
                <li><a href="tel_admin.html"><img src="/resources/images/shopmaster/main_m2.gif" alt="통화관리"></a></li>
                <li><a href="my_info.html"><img src="/resources/images/shopmaster/main_m6.gif" alt="내정보"></a></li>
                <li><a href="message_admin.html"><img src="/resources/images/shopmaster/main_m3.gif" alt="문자관리"></a></li>
                <li><a href="report.html"><img src="/resources/images/shopmaster/main_m4.gif" alt="리포트"></a></li>
                <li><a href="cs_notice_list.html"><img src="/resources/images/shopmaster/main_m5.gif" alt="고객센터"></a></li>
            </ul>
            <div class="mnotice mod">
                <div style="float:left;width:90px;"><img src="/resources/images/shopmaster/m_notice_tit.gif"></div>
                <div style="float:left;width:500px; padding-top:8px;"><a href="cs_notice_view.html" class="mnotice_a">시스템 정기점검으로 인한 서비스 일시 중지</a></div>
                <div style="float:left;width:97px; padding-top:8px; text-align:center;"><font color="#bbbbbb">2014.06.19</font></div>
                <div style="float:left;width:24px; padding-top:2px;"><img src="/resources/images/shopmaster/m_notice_up.gif" border="0"></div>
                <div style="float:left;width:30px; padding-top:2px;"><img src="/resources/images/shopmaster/m_notice_down.gif" border="0"></div>
                <div style="float:left;width:49px; padding-top:2px;"><a href="cs_notice_list.html"><img src="/resources/images/shopmaster/m_notice_go.gif" border="0"></a></div>
            </div>
        </div>
    </div>
    <!-- //wrapper -->
    <div class="footer">
        <div class="logo"><img src="https://www.mastershop.kr/resources/images/shopmaster/kth_gray.png" alt="kth"></div>
        <div class="link">
            <a href="http://www.kthcorp.com/intro" target="_blank">회사소개</a>
            <a href="cs_service.html">이용약관</a>
            <a href="cs_privacy.html">개인정보취급방침</a>
        </div>
        <div class="txt">
            <p>케이티하이텔(주)대표이사 오세영 / 사업자등록번호 : 211-81-79649 / 통신판매업신고 : 04-680-176</p>
            <p>주소 : 서울특별시 동작구 보라매5길 23 대표전화 1889-1431</p>
            <p>Copyright(c) 2014 KT Hitel Co., Ltd. all rights reserved.</p>
        </div>
    </div>
    <!-- //footer -->

</body>
</html>