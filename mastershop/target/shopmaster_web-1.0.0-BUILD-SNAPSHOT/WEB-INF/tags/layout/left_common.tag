<%@ tag language="java" description="" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>

<%@attribute name="stylesheet" fragment="true" %>
<%@attribute name="javascript" fragment="true" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
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

<body background="/resources/images/shopmaster/shopmaster_bg2.gif">
<div id="top_area">
	<div style="float:left; width:200px;"><a href="/home/mainFrame.do"><img src="/resources/images/shopmaster/shopm_logo.gif" border="0"/></a></div>
	<div style="float:left;"></div>
</div>

<div id="main_area">
	<div id="main_l">

 		<layout:left_lnb />

    </div>
    
	<jsp:doBody/><!-- <div id="main_r"></div> -->
    
</div>
</body>
</html>