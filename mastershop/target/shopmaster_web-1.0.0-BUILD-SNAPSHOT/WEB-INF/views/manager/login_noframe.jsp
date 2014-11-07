<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:noFrame>
	<jsp:attribute name="stylesheet">
	</jsp:attribute>
		
	<jsp:attribute name="javascript">
		<script language="javascript">
			${script}

			$(document).ready
			(
				function()
				{
					$('#id').focus();
					$('#pwd').keydown
					(
						function(event)
						{
							if (event.keyCode == 13 )
							{
								if(validate(document.forms[0])){ login(); }
							}
						}
					);

	 				$('#btnLogin').click
	 				(
	 					function() { if(validate(document.forms[0])){ login(); }}
	 				);

	 				$('#btnReqAccount').click(function(){ reqAccount(); });
				}
			);

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
		</script>
	</jsp:attribute>

<jsp:body>
<div id="intro_area">
	<div class="intro_img">
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
    </div>
	<div class="intro_login">
	<form id="loginForm" name="loginForm" method="post">
		<div style="padding-top:50px;">
			<input type="hidden" name="event" />
			<input type="text" id="id" name="id" class="intro_fild" maxlength="16" minbytes="4" msg="아이디의 길이가 4자 이상이여야 합니다."/>
		</div>
		<div style="padding-top:5px;">
			<input type="password" class="intro_fild" id="pwd" name="pwd" value='' maxlength="16" />
		</div>
    	<div style="padding-top:5px;"><a href="main.html" id="btnLogin"><img src="/resources/images/shopmaster/intro_login_btn.gif" border="0" /></a></div>
		<div style="text-align:left; padding-top:5px;">
        	<div style="float:left;"><input name="" type="checkbox" value="" /></div>
			<div style="float:left;padding-top:3px;">자동로그인</div>
        	<div style="float:right;padding-top:3px;"><!-- 520 x 165 --><a href="popup/intro_idpw.html">아이디/비밀번호찾기</a></div>
		</div>
	</form>
	</div>
</div>
</jsp:body>
</layout:noFrame>