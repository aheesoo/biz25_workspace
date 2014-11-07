<%@ tag language="java" description="" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="stylesheet" fragment="true" %>
<%@attribute name="javascript" fragment="true" %>
<%
	if(request.getCookies() != null)
	{
		for(Cookie c : request.getCookies())
		{
			if(c.getName().equals("ck_smw"))
			{
				String encryptAuth = c.getValue();
				int authExpire = Integer.parseInt(request.getSession().getServletContext().getInitParameter("authExpire")) * 60;
				
				Cookie cookie = new Cookie("ck_smw", encryptAuth);
				cookie.setMaxAge(authExpire);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8, IE=9" /> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<title>MasterShop</title>	
	<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
	<!-- Basic Styles -->

		<jsp:invoke fragment="stylesheet"/>
			
		<!-- ================================================== -->
		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
		<script src="/resources/js/jquery-1.11.0.js"></script>
		<script src="/resources/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>
		
		<script src="/resources/js/plugins/cookie/jquery.cookie.js"></script>

		<script language="javascript">

			$(document).ready(function() {
				var active = ($.cookie('leftMenuActive') == 'undefined') ? 0 : $.cookie('leftMenuActive');

				if(active != 'undefined'){
					$("#subMenu_"+active).attr("class","active");

				}

				// (숫자 가능)
				$('.numOnly').css('imeMode','disabled').keypress(function(event) {
				    if(event.which && (event.which < 48 || event.which > 57) && event.which != 8) {
				        event.preventDefault();
				    }
				}).keyup(function(){
				    if( $(this).val() != null && $(this).val() != '' ) {
				        $(this).val( $(this).val().replace(/[^0-9]/g, '') );
				    }
				});

			});	

			function moveMenu(url, active, sub_active)
			{
				$('body').append("<form name='mainLayoutForm' method='post'></form>");

				var cookie_val = active+"_"+sub_active;
				$.removeCookie('leftMenuActive', { path: '/' });
				$.cookie('leftMenuActive', cookie_val, { path: '/' });

				var form = document.mainLayoutForm;
				form.action = url;
				form.submit();
			}

		</script>


		<jsp:invoke fragment="javascript"/>
</head>
<body>
	<jsp:doBody/>
</body>
</html>