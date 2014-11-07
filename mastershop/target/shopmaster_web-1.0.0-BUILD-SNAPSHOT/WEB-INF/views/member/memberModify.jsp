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
	
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #F2DAB4 1px solid; padding:0 0 0 0">

			<tr height="0">
				<th width="130px"></th>
				<th width="*"></th>
				<th width="130px"></th>
				<th width="*"></th>
			</tr>

			<tr height="30">
				<td class="item_title_border">청약 ID</td>
				<td class="item_input_border">&nbsp;&nbsp; revenue</td>
				<td class="item_title_border">업종 *</td>
				<td class="item_input">&nbsp;&nbsp; <input type="text" id="1" name="1" value="서비스업" /></td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">상호(성명)*</td>
				<td class="item_input">&nbsp;&nbsp; <input type="text" id="2" name="2" value="디벨로퍼스헤븐" /> </td>
				<td class="item_title_border">상태</td>
				<td class="item_input">&nbsp;&nbsp; 가입</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">사업자번호*</td>
				<td class="item_input_border">&nbsp;&nbsp;
					<input type="text" id="" name="" value="" maxlength="3" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
					-
					<input type="text" id="" name="" value="" maxlength="2" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
					-
					<input type="text" id="" name="" value="" maxlength="5" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
				</td>
				<td class="item_title_border">주민(법인)번호업종 *</td>
				<td class="item_input">&nbsp;&nbsp;
					<input type="text" id="" name="" value="" maxlength="6" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
					-
					<input type="text" id="" name="" value="" maxlength="7" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
				</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">일반전화번호*</td>
				<td class="item_input_border">&nbsp;&nbsp;
					<input type="text" id="" name="" value="" maxlength="3" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
					-
					<input type="text" id="" name="" value="" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
					-
					<input type="text" id="" name="" value="" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
				</td>
				<td class="item_title_border">휴대전화번호*</td>
				<td class="item_input">&nbsp;&nbsp;
					<input type="text" id="" name="" value="" maxlength="3" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
					-
					<input type="text" id="" name="" value="" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
					-
					<input type="text" id="" name="" value="" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" />
				</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">주소*</td>
				<td class="item_input_border" colspan="3">&nbsp;&nbsp;
					<input type="text" id="member_jobzip1" name="member_jobzip1" value="${fn:substring(member.member_jobzip, 0, 3)}" maxlength="3" ispattern="zipcode" span="2"  glue="-"  onkeypress="onlyNumber(this);" style="width:60px" class="ipt_tx" /> 
					-
					<input type="text" id="member_jobzip2" name="member_jobzip2" value="${fn:substring(member.member_jobzip, 3, 6)}" maxlength="3" onkeypress="onlyNumber(this);"  style="width:60px"  class="ipt_tx"/>
					<input id="btnZip" type="button" class="button" value="우편번호찾기" onclick="openzipPopup('C');">
					<br />
					<input type="text" value="서울특별시...." style="width:40%" class="ipt_tx"/>
					<input type="text" value="서울특별시...." style="width:40%"/>
					
				</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">청약 증빙자료*</td>
				<td class="item_input_border" colspan="3">
					<table>
						<tr>
							<td><input type="button" class="button" value="녹음파일 가져오기"></td>
							<td><a href="">revenue.avi</a></td>
							<td><input id="btnZip" type="button" class="button" value="x"></td>
						</tr>
						<tr>
							<td><input type="button" class="button" value="문서파일 가져오기"></td>
							<td><a href="">신청서.pdf</a></td>
							<td><input id="btnZip" type="button" class="button" value="x"></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">회원징계</td>
				<td class="item_input_border" colspan="3">
					<table>
						<tr>
							<td>
								<span>상태</span>&nbsp;&nbsp;
								<span>
									<select name="" id="">
										<option>비성화</option>
										<option>활성화</option>
									</select>
								</span>&nbsp;&nbsp;
								<span>비활성 설정 시 반드시 사유를 작성해주세요.</span>
							</td>
						</tr>
						<tr>
							<td>
								<textarea rows="5" cols="140">
								</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%">
			<tr>
				<td width="*"></td>
				<td width="10px"><input type="button" value="확인"></td>
				<td width="10px"><input type="button" value="취소"></td>
			</tr>
		</table>
	</div>
</jsp:body>
</layout:common>