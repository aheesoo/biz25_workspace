<%@ tag language="java" description="" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>

<%@attribute name="stylesheet" fragment="true" %>
<%@attribute name="javascript" fragment="true" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8, IE=9" /> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">

<title>MasterShop</title>	

	<link rel="stylesheet" type="text/css" href="/resources/css/style.css">

		<jsp:invoke fragment="stylesheet"/><!-- ## -->

		<script src="/resources/js/jquery-1.11.0.js"></script>
		<script src="/resources/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="/resources/js/commonLibrary.js"></script><!-- ## -->
		<script src="/resources/js/validate.js"></script><!-- ## -->

		<jsp:invoke fragment="javascript"/>
</head>

<body background="/resources/images/shopmaster/shopmaster_bg.gif">
	<div id="wrap">
		<div id="top_area">
			<div style="float:left; width:200px;"><a href="/home/mainFrame.do"><img src="/resources/images/shopmaster/shopm_logo.gif" border="0"/></a></div>
			<div style="float:left;"></div>
		</div>
		
		<div id="main_area">
			<div id="main_l">
		
		 		<layout:left_lnb />
		
		    </div>
		    
			<jsp:doBody/><!-- <div id="main_r"></div> -->
			<div class="clear"></div>
		</div>
		
		<div id="main_bottom">
		
	        <div class="mbottom_line"></div>
			<div style="color:#ffffff;" class="mbottom1">
				<div style="float:left; width:50px; height:36px;"><img src="/resources/images/shopmaster/kth_gray.png" border="0"/></div>
				<a href="http://www.kthcorp.com/intro" target="_blank" class="mnotice_a"><strong>회사소개</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/csCenter/agreement.do" class="mbottom_a"><strong>이용약관</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/csCenter/policy.do" class="mbottom_a"><strong>개인정보취급방침</strong></a>
			</div>
	        <div style="color:#888888;" class="mbottom2">
	        	<div style="float:left; width:0px; height:36px;"></div>
	        	<div style="float:left;">케이티하이텔(주)&nbsp;&nbsp;&nbsp;&nbsp;대표이사 오세영 / 사업자등록번호 : 211-81-79649 / 통신판매업신고 : 04-680-176<br />주소 : 서울특별시 동작구 보라매5길 23 대표전화 1899-1431</div>
	        </div>
	        <div style="height:16px;color:#888888; font-size:11px; padding-left:50px;">Copyright(c) 2014 KT Hitel Co., Ltd. all rights reserved.</div>
		</div>
	</div>
</body>
</html>