<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<layout:common>
    <jsp:attribute name="stylesheet">
	    <style type="text/css">
	    	.btnArea{width:255px;margin:0 auto;text-align:center;padding:5px 0}
	    	.btnArea a{display:inline-block; padding:5px 13px 3px 13px}
	    </style>
    </jsp:attribute>
    
    <jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>   	
    	<script langauge="text/javascript">	
    	
    		${script}
    	
			$(document).ready
			(
				function()
				{
					$('#btnModify').click
					(
						function()
						{
							var form = document.form;
 							if(validate(form))
							{
 								var fd_admin_pw = form.fd_admin_pw.value;
 								if(fd_admin_pw.length >= 8 || fd_admin_pw.length == 0)
								{
 									form.submit();
								}
 								else
 								{
 									alert('비밀번호는 최소 8자 이상이여야 합니다.');
 									form.fd_admin_pw.focus();
 								}
							} 
						}
					);
				}
			);

   		</script>		    
    </jsp:attribute>
    
    <jsp:body>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
		<form id="form" name="form" method="post" action="/manager/myInfo.do">
		<input type="hidden" id="event" name="event" value="myInfoModify" />
		<tr height="0">
			<td width="100"></td>
			<td width="350"></td>
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">관리자명</td>
			<td class="item_input_border">
				<input type="text" id="fd_admin_name" name="fd_admin_name" maxlength="30" require="true" minbytes="2" msg="이름은 최소 2자 이상 입력하셔야 합니다." value="${admin.fd_admin_name}"/>
			</td>				
		</tr>
			
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">부서명</td>
			<td class="item_input_border">
				<input type="text" id="fd_team" name="fd_team" maxlength="30" minbytes="4" msg="부서명/직급은 최소 4자 이상 입력하셔야 합니다." class="ipt_tx" value="${admin.fd_team}" />
			</td>				
		</tr>				
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">아이디</td>
			<td class="item_input_border">
				<input type="text" id="pk_admin_id" name="pk_admin_id"  maxlength="16" require="true" minbytes="4" msg="아이디는 최소 4자 이상이여야 합니다." class="ipt_tx" value="${admin.pk_admin_id}" readonly/>
			</td>				
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">비밀번호</td>
			<td class="item_input_border">
				<input type="password" id="fd_admin_pw" name="fd_admin_pw"  maxlength="16" match="re_fd_admin_pw" hname="비밀번호" class="ipt_tx"/>
				비밀번호 변경을 원하시는 경우에만 새로운 비밀번호를 입력 하십시오.
			</td>				
		</tr>			
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">비밀번호 확인</td>
			<td class="item_input_border">
				<input type="password" id="re_fd_admin_pw" name="re_fd_admin_pw"  maxlength="16" hname="비밀번호 확인" class="ipt_tx"/>
			</td>				
		</tr>											
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">연락처</td>
			<td class="item_input_border">
				<input type="text" id="phone1" name="phone1" maxlength="3" require="true" onkeypress="onlyNumber();" ispattern="phone" span="3" glue="-" msg="연락처 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${splitInfo.phone1}" />
				-
				<input type="text" id="phone2" name="phone2" maxlength="4" onkeypress="onlyNumber();" class="ipt_tx" style="width:50px" value="${splitInfo.phone2}" />
				-
				<input type="text" id="phone3" name="phone3" maxlength="4" onkeypress="onlyNumber();" class="ipt_tx" style="width:50px" value="${splitInfo.phone3}" />
			</td>				
		</tr>				
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">모바일</td>
			<td class="item_input_border">
				<input type="text" id="mobile1" name="mobile1" maxlength="3" require="true" onkeypress="onlyNumber();" ispattern="hp" span="3" glue="-" msg="모바일 번호 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${splitInfo.mobile1}" />
				-
				<input type="text" id="mobile2" name="mobile2" maxlength="4" onkeypress="onlyNumber();" style="width:50px" class="ipt_tx" value="${splitInfo.mobile2}" />
				-
				<input type="text" id="mobile3" name="mobile3" maxlength="4" onkeypress="onlyNumber();" style="width:50px" class="ipt_tx" value="${splitInfo.mobile3}" />
				
			</td>				
		</tr>				
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">이메일</td>
			<td class="item_input_border">
				<input type="text" id="email1" name="email1" maxlength="15" require="true" ispattern="email" span="2" glue="@" style="width:80px" class="ipt_tx" value="${splitInfo.email1}" /> 
				@ 
				<input type="text" id="email2" name="email2" maxlength="20" class="ipt_tx" value="${splitInfo.email2}"/>
			</td>				
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
		<tr height="30">
			<td class="item_title_border">등급사항</td>
			<td class="item_input_border">${fd_admin_level_cd }</td>
		</tr>
		</form>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="50">
			<td style="text-align:center;padding:10px" >
				<input type="button" id="btnModify" class="button" value="수정하기" style="width:100px" />
			</td>
		</tr>
	</table>
    </jsp:body>
</layout:common>    