<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MasterShop</title>	
	<link rel="stylesheet" type="text/css" href="/resources/css/admin.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/adminPopup.css">
	<style type="text/css">
    	.btnArea{text-align:center;padding:5px 0}
    </style>
	<script src="/resources/js/jquery-1.11.0.js"></script>
	<script src="/resources/js/commonLibrary.js"></script>
	<script src="/resources/js/validate.js"></script>   	
   	<script langauge="javascript">
   		var success = '${event}';

		$(document).ready
   		(
   			function()
   			{
   				if(success == 'close')   					
   				{
  					window.close();
   				}
   				
   				$('#btnRegister').click
   				(
   					function()
   					{ 
   						if(validate(document.registerForm))
						{
							register();	
						}
   					}
   				);
   				
   				$('#btnClose').click(function(){ window.close();});
   			}
   		);
		
		function register()
		{
			var form = document.registerForm;
			form.action = '/manager/reqAccount.do';
			form.target = "_self";
			form.submit();
		}
	
		function id_chk()
		{
			if($('#returnChk').val() != 'true')
			{
				window.open("","chkId","location=no,menubar=no,titlebar=no,scrollbars=no,width=300px,height=250px");
				var form = document.registerForm;
				
				form.action = '/manager/adminIdCheck.do';
				form.target = 'chkId';
				form.submit();
			}
		}
   	</script>
</head>
<body>
	<table border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
		<form id="registerForm" name="registerForm" method="post">			
		<input type="hidden" id="event" name="event" value="${event}" />
		<input type="hidden" id="returnChk" name="returnChk" />
		<tr height="0">
			<td width="90px"></td>
			<td width="345px"></td>
		</tr>
		
		<tr height="1" border="0"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30" border="0">
			<td colspan="2">
				<img src="/resources/images/common/shopmaster_cms_account.png" width="180px" alt="" />
			</td>				
		</tr>
		
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">관리자명</td>
			<td class="item_input_border">
				<input type="text" id="admin_name" name="admin_name" maxlength="30"  minbytes="2" msg="이름은 최소 2자 이상 입력하셔야 합니다." class="ipt_tx" value="${admin.admin_name}"/>
			</td>				
		</tr>	

		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">부서명</td>
			<td class="item_input_border">
				<input type="text" id="admin_team" name="admin_team" maxlength="30" minbytes="2" msg="부서명/직급은 최소 4자 이상 입력하셔야 합니다." class="ipt_tx" value="${admin.admin_team}"/>
			</td>				
		</tr>				
		
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">아이디</td>
			<td class="item_input_border">
				<input type="text" id="admin_id" name="admin_id"  maxlength="16" require="true" minbytes="4" msg="아이디는 최소 4자 이상이여야 합니다." class="ipt_tx" value="${admin.admin_id}" />
				<a href="javascript:id_chk();" ><span>중복체크</span></a>
			</td>
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">비밀번호</td>
			<td class="item_input_border">
				<input type="password" id="admin_pw" name="admin_pw" maxlength="20" require="true" minbytes="8" msg="비밀번호는 최소 8자 이상이여야 합니다." class="ipt_tx"/>
			</td>				
		</tr>
		
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">연락처</td>
			<td class="item_input_border">
				<input type="text" id="phone1" name="phone1" maxlength="3" require="true" class="numOnly" ispattern="phone" span="3" glue="-" msg="연락처 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${admin.phone1}" />
				-
				<input type="text" id="phone2" name="phone2" maxlength="4" class="numOnly" class="ipt_tx" style="width:50px" value="${admin.phone2}" />
				-
				<input type="text" id="phone3" name="phone3" maxlength="4" class="numOnly" class="ipt_tx" style="width:50px" value="${admin.phone3}" />
			</td>				
		</tr>				
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">모바일</td>
			<td class="item_input_border">
				<input type="text" id="mobile1" name="mobile1" maxlength="3" require="true" class="numOnly" ispattern="hp" span="3" glue="-" msg="모바일 번호 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${admin.mobile1}" />
				-
				<input type="text" id="mobile2" name="mobile2" maxlength="4" class="numOnly" style="width:50px" class="ipt_tx" value="${admin.mobile2}" />
				-
				<input type="text" id="mobile3" name="mobile3" maxlength="4" class="numOnly" style="width:50px" class="ipt_tx" value="${admin.mobile3}" />
			</td>				
		</tr>				
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">이메일</td>
			<td class="item_input_border">
				<input type="text" id="email1" name="email1" maxlength="15" require="true" ispattern="email" span="2" glue="@" style="width:80px" class="ipt_tx" value="${admin.email1}" /> 
				@ 
				<input type="text" id="email2" name="email2" maxlength="20" class="ipt_tx" value="${admin.email2}"/>
			</td>				
		</tr>				
				
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">등급</td>
			<td class="item_input_border">
				<select id="admin_level_cd" name="admin_level_cd">
					<c:forEach var="item" items="${codeLevel}" varStatus="status" >
						<option value="${item.pk_code}" >${item.fd_name}</option>
					</c:forEach>
				</select>
			</td>				
		</tr>				

		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		</form>
	</table>
	<div class="btnArea">
		<input type="button" id="btnRegister" class="button" value="완료" style="width:50px" />
		<input type="button" id="btnClose" class="button" value="닫기" style="width:50px" />
	</div>
</body>
</html>