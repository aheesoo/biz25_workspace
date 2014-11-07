<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

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
<frameset cols="0, *">
	<frame id="left_frame"  name="left_frame"	src="/openApiObject.jsp" noresize frameborder="0"></frame>
	<frame id="right_frame" name="right_frame"	src="/home/mainFrame.do" frameborder="0"></frame>
</frameset>
</html>