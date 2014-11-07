<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>
	<jsp:attribute name="stylesheet">
		<style type="text/css">
			.btnArea{width:255px;margin:0 auto;text-align:center;padding:5px 0}
			.btnArea a{display:inline-block; padding:5px 13px 3px 13px}
			.ui-datepicker{ font-size: 13px; width: 230px; }
			.ui-datepicker select.ui-datepicker-month{ width:30%; font-size: 11px; }
			.ui-datepicker select.ui-datepicker-year{ width:40%; font-size: 11px; }
		</style>
	</jsp:attribute>

	<jsp:attribute name="stylesheet">
		<style>
			label.rintiantta {color:Red;}
		</style>
	</jsp:attribute>

	<jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>
		<script src="/resources/js/jquery.ui.datepicker-ko.js"></script>
		<script langauge="text/javascript">	
			${script}

		</script>
	</jsp:attribute>

<jsp:body>
	<div id="content">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #EDCE9B 1px solid; padding:0 0 0 0">
				<tr height="0">
					<th width="130px"></th>
					<th width=""></th>
				</tr>
				
				<tr height="30">
					<td class="item_title_border">상태</td>
					<td class="item_input">
						<select id="searchColumn" name="searchColumn" style="width:128px;">
							<option value="">전체</option>
							<option value="join">가입</option>
							<option value="cancel">해지</option>
						</select>
					</td>
				</tr>
				
				<tr height="1"><td colspan="2" bgcolor="#EDCE9B"></td></tr>		
				<tr height="30">
					<td class="item_title_border">등록일</td>
					<td class="item_input" colspan="3">
						<input type="text"  id="start_ymd" name="start_ymd" require="true" ispattern="date" hname="검색 시작일" class="ipt_tx" style="width:80px;text-align:center"/>
						&nbsp;~&nbsp;  					
						<input type="text"  id="finish_ymd" name="finish_ymd" require="true" ispattern="date" hname="검색 종료일" class="ipt_tx" style="width:80px;text-align:center"/>
					</td>
				</tr>
				
				<tr height="1"><td colspan="2" bgcolor="#EDCE9B"></td></tr>
				<tr height="30">
					<td class="item_title_border">통화 Open API<br/>청약상태</td>
					<td class="item_input">
						<select id="searchColumn1" name="searchColumn1" style="width:128px;">
							<option value="">전체</option>
							<option value="join" >가입</option>
							<option value="cancel" >해지</option>
						</select>
					</td>
				</tr>

				<tr height="1"><td colspan="2" bgcolor="#EDCE9B"></td></tr>		
				<tr height="30">
					<td class="item_title_border">검색조건</td>
					<td class="item_input">
						<select id="searchColumn2" name="searchColumn2" style="width:128px;">
							<option value="num">회선번호</option>
							<option value="id" >청약ID</option>
							<option value="name" >회원명</option>
						</select>
						<input type="text" id="searchString" name="searchString" value="${param.searchString}" />
						&nbsp;&nbsp;&nbsp;<input type="button" id="btnSearch" class="button" value="검색" style="width:80px" />
					</td>
				</tr>
			</table>			
		</form>
		<br />
		총 : 532건
		<br />
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #EDCE9B 1px solid; padding:0 0 0 0">
			<tr height="0">
				<td width="5%"></td>
				<td width="10%"></td>
				<td width="10%"></td>
				<td width="12%"></td>
				<td width="12%"></td>
				<td width="14%"></td>
				<td width="12%"></td>
			</tr>
			<tr height="25">
				<td class="item_title_border">청약 ID</td>
				<td class="item_title_border">회워명</td>
				<td class="item_title_border">회선변호</td>
				<td class="item_title_border">식별번호</td>
				<td class="item_title_border">청양등록일</td>
				<td class="item_title_border">상태</td>
				<td class="item_title_border">통화 Open API<br/>청약상태</td>
			</tr>
			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="25" onmouseover="this.style.backgroundColor='#f7f7f7'" onmouseout="this.style.backgroundColor=''" bgcolor="white" onclick="javascript:location.href='${link}';" style="cursor:pointer;">
				<td class="item_input_border" align="center">&nbsp;sf8d65sf8s</td>
				<td class="item_input_border" align="center">&nbsp;가마솥누릉지</td>
				<td class="item_input_border" align="center">&nbsp;02-555-9876</td>
				<td class="item_input_border" align="center">&nbsp;4652297</td>
				<td class="item_input_border" align="center">&nbsp;2014.06.20 13:26:25</td>
				<td class="item_input_border" align="center">&nbsp;가입</td>
				<td class="item_input" align="center">&nbsp;해지</td>
			</tr>

<!-- 검색데이터 없을때 -->
			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="60" bgcolor="white"> 	
				<td class="item_input" align="center" colspan="9">&nbsp;검색된 데이타가 없습니다</td>
			</tr>
<!-- 검색데이터 없을때 -->

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td align="center" colspan="9">
					<div id='paging' class='paginate'>
						<span style='width:44px;display:inline-block;text-align:right;margin-right:6px;'>
							<a class='pre' style='cursor:pointer' onclick='javascript:goPage(1);'><img src='/resources/images/pagenavi/bt_pre01.gif' alt=''></a>
							<a class='pre' style='cursor:pointer'><img src='/resources/images/pagenavi/bt_pre02.gif' alt=''></a>
						</span>
						<strong>1</strong>
						<a href='#' onclick='javascript:goPage(2);'>2</a>
						<a href='#' onclick='javascript:goPage(3);'>3</a>
						<a href='#' onclick='javascript:goPage(4);'>4</a>
						<a href='#' onclick='javascript:goPage(5);'>5</a>
						<a href='#' onclick='javascript:goPage(6);'>6</a>
						<a href='#' onclick='javascript:goPage(7);'>7</a>
						<a href='#' onclick='javascript:goPage(8);'>8</a>
						<a href='#' onclick='javascript:goPage(9);'>9</a>
						<a href='#' onclick='javascript:goPage(10);'>10</a>
						<span style='width:44px;display:inline-block;text-align:left;margin-left:6px;'>
							<a class='next' style='cursor:pointer' onclick='javascript:goPage(11);'><img src='/resources/images/pagenavi/bt_nxt02.gif' alt=''></a>
							<a class='next' style='cursor:pointer' onclick='javascript:goPage(77);'><img src='/resources/images/pagenavi/bt_nxt01.gif' alt=''></a>
						</span>
					</div>
				</td>
			</tr>
		</table>  
		 
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr height="50">
				<td width="*"></td>
				<td width="10px">
					<input type="button" id="btnRegister" class="button" value="등록" style="width:80px" />
				</td>
			</tr>
		</table>
	</div>
</jsp:body>
</layout:common>