<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:leftFrame>
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
	<div class="mcontnents">
		<div style="float:left;margin:0px 10px 0px 10px;"><a href="cs_admin.html"><img src="/resources/images/shopmaster/main_m1.gif" border="0"></a></div>
		<div style="float:left;margin:0px 10px 0px 10px;"><a href="tel_admin.html"><img src="/resources/images/shopmaster/main_m2.gif" border="0"></a></div>
		<div style="float:left;margin:0px 10px 0px 10px;"><a href="my_info.html"><img src="/resources/images/shopmaster/main_m6.gif" border="0"></a></div>
		<div style="float:left;margin:20px 10px 0px 10px;"><a href="message_admin.html"><img src="/resources/images/shopmaster/main_m3.gif" border="0"></a></div>
		<div style="float:left;margin:20px 10px 0px 10px;"><a href="report.html"><img src="/resources/images/shopmaster/main_m4.gif" border="0"></a></div>
		<div style="float:left;margin:20px 10px 0px 10px;"><a href="cs_notice_list.html"><img src="/resources/images/shopmaster/main_m5.gif" border="0"></a></div>
	</div>
	<div class="mnotice">
		<div style="float:left;width:90px;"><img src="/resources/images/shopmaster/m_notice_tit.gif"></div>
		<div style="float:left;width:500px; padding-top:8px;"><a href="cs_notice_view.html" class="mnotice_a">시스템 정기점검으로 인한 서비스 일시 중지</a></div>
		<div style="float:left;width:97px; padding-top:8px; text-align:center;"><font color="#bbbbbb">2014.06.19</font></div>
		<div style="float:left;width:24px; padding-top:2px;"><img src="/resources/images/shopmaster/m_notice_up.gif" border="0"></div>
		<div style="float:left;width:30px; padding-top:2px;"><img src="/resources/images/shopmaster/m_notice_down.gif" border="0"></div>
		<div style="float:left;width:49px; padding-top:2px;"><a href="cs_notice_list.html"><img src="/resources/images/shopmaster/m_notice_go.gif" border="0"></a></div>
	</div>
<div class="mnotice_line"></div>
</jsp:body>
</layout:leftFrame>