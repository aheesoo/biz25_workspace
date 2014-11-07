<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>
	<jsp:attribute name="stylesheet">		
	</jsp:attribute>

	<jsp:attribute name="javascript">		
		<script src="/resources/js/validate.js"></script>
		   
		<script langauge="text/javascript">	
			${script}

			/*우편번호 검색 창 열기*/
			function openzipPopup() {
				window.open('/member/zippopup.do?ifrm=-1', '','scrollbars=yes,width=520, height=500');
			}

			$(document).ready(function() {
				
				// DO NOT REMOVE : GLOBAL FUNCTIONS!
				pageSetUp();
			});
			
			
			/*아이디 중복체크*/
			function openCheckId() {
				var form = document.form;
				str = form.pk_member_id.value;
				str = str.trim();
				if (!str) {
					alert("\n아이디를 입력하세요..");
					form.pk_member_id.focus();
					return;
				}
		
				window.open('/member/checkId.do?userId=' + str, '','scrollbars=yes,width=300, height=160');
			}
			
			function goSubmit(){
				
				if (validate(document.form)) {
					//form.submit();
					$("#form").submit();
				}
			}
		</script>
	</jsp:attribute>
	

<jsp:body>
	<div id="content">
		<!-- widget grid -->
		<section id="widget-grid" class="">
			<!-- row -->
			<div class="row">
				<!-- NEW WIDGET START -->
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<!-- Widget ID (each widget will need unique ID)-->
					<div class="jarviswidget" id="wid-id-0" 
							data-widget-colorbutton="false"
							data-widget-editbutton="false"
							data-widget-togglebutton="false"
							data-widget-deletebutton="false"
							data-widget-fullscreenbutton="false"
							data-widget-custombutton="false"
							data-widget-collapsed="false"									
							data-widget-sortable="false"
							>
							
						<header>
							<span class="widget-icon"> <i class="fa fa-table"></i> </span>
							<h2>회원 등록</h2>
						</header>
						
						<!-- widget div-->
						<div>
							<!-- widget edit box -->
							<div class="jarviswidget-editbox">
								<!-- This area used as dropdown edit box -->
							</div>
							<!-- end widget edit box -->

							<!-- widget content -->
							<div class="widget-body">
								<p>&nbsp;</p>
								<!--
									<p>Adds borders to any table row within <code>&lt;table&gt;</code> by adding the <code>.table-bordered</code> with the base class</p>
								-->
								<div class="table-responsive">									


		<form id="form" name="form" method="post" action="/member/memberRegisterProc.do" enctype="multipart/form-data">
		<input type="hidden" name="idCheck" value="false" />

		<table class="table table-bordered">
			<tbody>
			<tr height="30">
				<td width="130px">청약 ID *</td>
				<td width="320px">&nbsp;
					<div class="col-sm-8">
						<input type="text" id="pk_member_id" name="pk_member_id" class="form-control" placeholder="청약 ID 입력." >
					</div> 
					<div class="col-sm-2">
						<button class="btn btn-default" type="button">체크</button>
					</div>
				</td>
				
				<td width="130px">비밀번호 *</td>
				<td width="*">
					<div class="col-sm-5"><input type="password" id="fd_member_pwd" name="fd_member_pwd" class="form-control" ></div>
					<div class="col-sm-5"><input type="password" id="fd_member_pwd2" name="fd_member_pwd2" class="form-control" ></div>(확인)
				</td>
			</tr>
			<tr height="30">
				<td>상호 (성명) *</td>
				<td>
					<div class="col-sm-8"><input type="text" id="fd_user_name" name="fd_user_name" class="form-control" /></div>
				</td>

				<td width="130px">업종 *</td>
				<td width="*">&nbsp;<div class="col-sm-6"><input type="text" id="fd_business_type" name="fd_business_type" class="form-control" /></div></td>
			</tr>
			<tr height="30">
				<td >사업자번호 *</td>
				<td >
					<div class="col-sm-4"><input type="text" id="fd_corp_regist_num1" name="fd_corp_regist_num1" class="form-control" /></div>
					<div class="col-sm-4"><input type="text" id="fd_corp_regist_num2" name="fd_corp_regist_num1" class="form-control" /></div>
				</td>
				
				<td >주민(법인)번호 *</td>
				<td >
					<div class="col-sm-3"><input type="text" id="fd_regist_num2" name="fd_regist_num2" class="form-control" /></div> 
					<div class="col-sm-3"><input type="text" id="fd_regist_num2" name="fd_regist_num2" class="form-control" /></div>
				</td>
			</tr>
			<tr height="30">
				<td >일반전화번호 *</td>
				<td >
					<div class="col-sm-3"><input type="text" id="fd_tel1" name="fd_tel1" class="form-control" ></div>
					<div class="col-sm-3"><input type="text" id="fd_tel2" name="fd_tel2" class="form-control" ></div>
					<div class="col-sm-3"><input type="text" id="fd_tel3" name="fd_tel3" class="form-control" ></div>
				</td>
				<td >휴대전화번호 *</td>
				<td >&nbsp;
					<div class="col-sm-2"><input type="text" id="fd_mobile1" name="fd_mobile1" class="form-control" ></div>
					<div class="col-sm-2"><input type="text" id="fd_mobile2" name="fd_mobile2" class="form-control" ></div>
					<div class="col-sm-2"><input type="text" id="fd_mobile3" name="fd_mobile3" class="form-control" ></div>
				</td>
			</tr>
			<tr height="30">
				<td >주소 *</td>
				<td  colspan="3">
					우편번호 &nbsp;
					<div class="col-sm-1"><input type="text" id="fd_post_num1" name="fd_post_num1" class="form-control" /></div>
					<div class="col-sm-1"><input type="text" id="fd_post_num2" name="fd_post_num2" class="form-control" /></div>
					<button class="btn btn-default" type="button" onclick="openzipPopup()">검색</button><br>
					<div class="col-sm-7"><input type="text" id="fd_addr" name="fd_addr" class="form-control" /></div>&nbsp;<br>
					&nbsp;(주소 상세) <div class="col-sm-7"><input type="text" id="fd_addr_detail" name="fd_addr_detail" class="form-control" /></div>
				</td>
			</tr>
			<tr height="30">
				<td >청약 증빙자료 *</td>
				<td  colspan="3">
					<fieldset>
						<div class="form-group">														
							<div class="col-md-10">
								<input type="file" class="btn btn-default" id="fd_file1" name="fd_file1">
								<p class="help-block"> </p>
							</div>
						</div>
						<br/>
						<div class="form-group">															
							<div class="col-md-10">
								<input type="file" class="btn btn-default" id="fd_file2" name="fd_file2">
								<p class="help-block"> </p>
							</div>
						</div>
					</fieldset>
				</td>
			</tr>
			
			<tr height="30">
				<td >회원징계</td>
				<td  colspan="3">
					<div class="row">
						<section class="col col-5">
							<label class="select">
								&nbsp;&nbsp;&nbsp;&nbsp;상태 
								<select name="fd_user_access" id="fd_user_access">
									<option value="N" selected="" disabled="">비활성</option>
									<option value="Y">활성</option>
								</select> 비활성 설정시 반드시 사유를 작성해주세요. 
								<i></i> 
							</label>
						</section>
					</div>
					<br>

					<textarea name="fd_user_access_cont" id="fd_user_access_cont" rows="3" cols="100" style="width:70%"></textarea>
					<br><br>

					<fieldset>
						<div class="form-group">													
							<div class="col-md-10">
								<input type="file" class="btn btn-default" id="fd_user_access_file1" name="fd_user_access_file1">
								<p class="help-block"></p>
							</div>
						</div>
					</fieldset>
				</td>
			</tr>
			</tbody>	
		</table>
		</form>	

								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">														
											<button class="btn btn-primary" type="submit" onclick="goSubmit();"><i class="fa fa-save"></i>확인</button>
											<button class="btn btn-default" type="submit">취소</button>
										</div>
									</div>
								</div>
							</div>
							<!-- end widget content -->
						</div>
						<!-- end widget div -->
					</div>				
				</article>
			</div>
		</section>
	</div>
</jsp:body>
</layout:common>