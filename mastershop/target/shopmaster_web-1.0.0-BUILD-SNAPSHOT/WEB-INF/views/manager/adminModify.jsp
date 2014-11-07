<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>
    <jsp:attribute name="stylesheet">	
    </jsp:attribute>
    
    <jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>   	

	   	<script src="/resources/js/addAdminPopup.js"></script>
    	<script type="text/javascript"> 				
    	${script}
    	
 		$(document).ready
 		(
 			function()
 			{
   				$('#go_submitBtn').click
   				(
   					function()
   					{ 
   						var form = document.form;
						if(validate(form))
						{
							var fd_admin_pw = form.fd_admin_pw.value;
							if(fd_admin_pw.length >= 8 || fd_admin_pw.length == 0)
							{
								$('#go_submitBtn').unbind("click");		
								go_submit();
							}
							else
							{
								alert('비밀번호는 최소 8자 이상이여야 합니다.');
								form.fd_admin_pw.focus();
							}
						} 
   					}
   				);

   				$('#go_listBtn').click
   				(
   					function()
   					{
   						var form = document.transForm;
   						form.action = '/manager/adminList.do';
   						form.submit();
   					}
   				);
   				
   				$('#go_delBtn').click(
					function(){
						if(confirm('해당 관리자를 삭제 하시겠습니까?')){
							go_del();
						}
					}
				);

				<c:forEach var="mainMenu" items="${cmsMenuList}" varStatus="status" >
				<c:if test="${mainMenu.fd_group_code == '0000' }"> 
					$('input[id="mainMenu${status.index}"]').click(function(){
						$('input[id*="subMenu${status.index}_"]').prop("checked",$('input[id="mainMenu${status.index}"]').is(":checked"));
					});
				</c:if>
				</c:forEach>
   				
 			}
 		);
 		

 		function go_list(){
			location.href = '/manager/adminList.do';
 		}
 		
		function go_submit(){
			var form = document.form;
			form.event.value="modify";
			form.action="/manager/adminModify.do";
			form.submit();
		}
		
		function go_del(){
			if(confirm("정말로 삭제 하시겠습니까?"))
			{
				var form = document.form;
				form.event.value="remove";
				form.action="/manager/adminModify.do";
				form.submit();
			}
		}
	//-->

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
							<h2>운영자 수정</h2>
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
								<form id="transForm" name="transForm">
									<input type="hidden" id="event" name="event" />
									<input type="hidden" id="page" name="page" value="${pageHelper.nowPage }" />
									<input type="hidden" id="searchColumn" name="searchColumn" value="${pageHelper.searchColumn }" />
									<input type="hidden" id="searchString" name="searchString" value="${pageHelper.searchString }" />		
								</form>
								<div class="table-responsive">
									<form id="form" name="form" method="post" action="/manager/adminModify.do">
										<input type="hidden" id="event" name="event" />
										<input type="hidden" id="pk_admin_id" name="${admin.pk_admin_id}" />		
										<input type="hidden" id="page" name="page" value="${pageHelper.nowPage }" />
										<input type="hidden" id="searchColumn" name="searchColumn" value="${pageHelper.searchColumn }" />
										<input type="hidden" id="searchString" name="searchString" value="${pageHelper.searchString }" />									
										<table class="table table-bordered">
											<tbody>
												<tr height="30">
													<th>이름</th>
													<td>
														<input  type="text" id="fd_admin_name" name="fd_admin_name" maxlength="30" require="true" minbytes="2" msg="이름은 최소 2자 이상 입력하셔야 합니다." value="${admin.fd_admin_name}"/>
													</td>				
												</tr>												
												<tr height="30">
													<th>아이디</th>
													<td>
														<input   type="text" id="pk_admin_id" name="pk_admin_id"  maxlength="16" require="true" minbytes="4" msg="아이디는 최소 4자 이상이여야 합니다." class="ipt_tx" value="${admin.pk_admin_id}" readonly/>
													</td>				
												</tr>												
												<tr height="30">
													<th>부서명</th>
													<td>
														<input   type="text" id="fd_team" name="fd_team" maxlength="30" minbytes="4" msg="부서명/직급은 최소 4자 이상 입력하셔야 합니다." class="ipt_tx" value="${admin.fd_team}" />
													</td>				
												</tr>	
												<tr height="30">
													<th>비밀번호</th>
													<td>
														<input   type="password" id="fd_admin_pw" name="fd_admin_pw"  maxlength="16" match="re_fd_admin_pw" hname="비밀번호" class="ipt_tx"/>
														비밀번호 변경을 원하시는 경우에만 새로운 비밀번호를 입력 하십시오.
													</td>				
												</tr>	
												<tr height="30">
													<th>비밀번호 확인</th>
													<td>
														<input   type="password" id="re_fd_admin_pw" name="re_fd_admin_pw" hname="비밀번호 확인" class="ipt_tx"/>
													</td>				
												</tr>
												<tr height="30">
													<th>연락처</th>
													<td class="item_input">
														<input   type="text" id="phone1" name="phone1" maxlength="3" require="true" onkeypress="onlyNumber(this);" ispattern="phone" span="3" glue="-" msg="연락처 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${splitInfo.phone1}" />
														-
														<input   type="text" id="phone2" name="phone2" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" value="${splitInfo.phone2}" />
														-
														<input   type="text" id="phone3" name="phone3" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" value="${splitInfo.phone3}" />
													</td>				
												</tr>
												<tr height="30">
													<th>휴대폰</th>
													<td>
														<input   type="text" id="mobile1" name="mobile1" maxlength="3" require="true" onkeypress="onlyNumber(this);" ispattern="hp" span="3" glue="-" msg="모바일 번호 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${splitInfo.mobile1}" />
														-
														<input   type="text" id="mobile2" name="mobile2" maxlength="4" onkeypress="onlyNumber(this);" style="width:50px" class="ipt_tx" value="${splitInfo.mobile2}" />
														-
														<input   type="text" id="mobile3" name="mobile3" maxlength="4" onkeypress="onlyNumber(this);" style="width:50px" class="ipt_tx" value="${splitInfo.mobile3}" />
														
													</td>				
												</tr>
												<tr height="30">
													<th>이메일</th>
													<td>
														<input   type="text" id="email1" name="email1" maxlength="15" require="true" ispattern="email" span="2" glue="@" style="width:80px" class="ipt_tx" value="${splitInfo.email1}" /> 
														@ 
														<input   type="text" id="email2" name="email2" maxlength="20" class="ipt_tx" value="${splitInfo.email2}"/>
													</td>				
												</tr>												
												<tr>
													<th>관리자 권한</th>
													<td>				
														<div class="form-group">
															<fieldset>
																<table class="table table-bordered">															
																	<tbody>
									<c:forEach var="mainMenu" items="${cmsMenuList}" varStatus="status" >
										<c:if test="${mainMenu.fd_group_code == '0000' }">
											<tr height="30">
												<!-- 대분류 -->
												<td width="15%">																						
													<c:set value="" var="check" />
													<c:forEach var="aclMainMenu" items="${aclMenuList }">
														<c:if test="${mainMenu.pk_url_code == aclMainMenu.pk_url_code }"><c:set value="checked='checked'" var="check" /></c:if> 
													</c:forEach>																
													<div class="checkbox">
														<label>
															<input type="checkbox" id="mainMenu${status.index}"  name="permissions" value="${mainMenu.pk_url_code }" ${check }>
															<i></i>${mainMenu.fd_name }
														</label>																																													
													</div>
												</td>
												<!-- 중분류 -->
												<td width="85%">																						
													<div class="col-4">
													<c:forEach var="subMenu" items="${cmsMenuList}" varStatus="statussub">																																														
														<c:if test="${subMenu.fd_menu_yn == 'Y'}">			
															<c:if test="${mainMenu.pk_url_code == subMenu.fd_group_code }">																							
																<c:set value="" var="check" />
																<c:forEach var="aclSubMenu" items="${aclMenuList }">
																	<c:if test="${subMenu.pk_url_code == aclSubMenu.pk_url_code }"><c:set value="checked='checked'" var="check" /></c:if> 
																</c:forEach>
																<div class="checkbox col col-4">																																																
																	<label>
																		<input type="checkbox" id="subMenu${status.index}_${statussub.index}" name="permissions" value="${subMenu.pk_url_code }" ${check} }>
																		<i></i>${subMenu.fd_name }
																	</label>
																</div>																				
															</c:if>		
														</c:if>																						
													</c:forEach>
													</div>																						
												</td>
											</tr>
										</c:if>
									</c:forEach>
																	</tbody>
																</table>
															</fieldset>
														</div>
													</td>
												</tr>												
												<tr height="30">
													<th>관리자 등급</th>
													<td>
														<select  id="fd_admin_level_cd" name="fd_admin_level_cd">
														<c:forEach var="item" items="${codeLevels}" varStatus="status" >
															<option value="${item.pk_code}" <c:if test="${admin.fd_admin_level_cd == item.pk_code}">selected="selected"</c:if>>${item.fd_name}</option>
														</c:forEach>
														</select>
													</td>
												</tr>												
												<tr height="30">
													<th>회원 상태</th>
													<td>
														<select id="fd_admin_status_cd" name="fd_admin_status_cd">
														<c:forEach var="item" items="${codeStatus}" varStatus="status" >
															<option value="${item.pk_code}" <c:if test="${admin.fd_admin_status_cd == item.pk_code}">selected="selected"</c:if>>${item.fd_name}</option>
														</c:forEach>
														</select>
													</td>
												</tr>
																
											</tbody>
										</table>
									</form>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">
											<c:choose>
												<c:when test="${admin.pk_admin_id != null}">
													<button class="btn bg-color-red txt-color-white"  id="go_delBtn" type="button">삭제하기</button>	
													<button class="btn btn-primary"  id="go_submitBtn" type="button">수정하기</button>
												</c:when>
												<c:otherwise>
													<button class="btn btn-primary" type="button">	<i class="fa fa-save"></i>저장하기</button>													
												</c:otherwise>
											</c:choose>												
												<button class="btn btn-default" id="go_listBtn" type="button">목록보기</button>
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
	
	<%-- 
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #EDCE9B 1px solid; padding:0 0 0 0">
		<form id="transForm" name="transForm">
		<input type="hidden" id="event" name="event" />
		<input type="hidden" id="page" name="page" value="${pageHelper.nowPage }" />
		<input type="hidden" id="searchColumn" name="searchColumn" value="${pageHelper.searchColumn }" />
		<input type="hidden" id="searchString" name="searchString" value="${pageHelper.searchString }" />		
		</form>
		<form id="form" name="form" method="post" action="/manager/adminModify.do">
		<input type="hidden" id="event" name="event" />
		<input type="hidden" id="pk_admin_id" name="${admin.pk_admin_id}" />		
		<input type="hidden" id="page" name="page" value="${pageHelper.nowPage }" />
		<input type="hidden" id="searchColumn" name="searchColumn" value="${pageHelper.searchColumn }" />
		<input type="hidden" id="searchString" name="searchString" value="${pageHelper.searchString }" />
		<tr height="0">
			<td width="150px"></td>
			<td width="*"></td>
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">이름</td>
			<td class="item_input">
				<input  type="text" id="fd_admin_name" name="fd_admin_name" maxlength="30" require="true" minbytes="2" msg="이름은 최소 2자 이상 입력하셔야 합니다." value="${admin.fd_admin_name}"/>
			</td>				
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">아이디</td>
			<td class="item_input">
				<input   type="text" id="pk_admin_id" name="pk_admin_id"  maxlength="16" require="true" minbytes="4" msg="아이디는 최소 4자 이상이여야 합니다." class="ipt_tx" value="${admin.pk_admin_id}" readonly/>
			</td>				
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">부서명</td>
			<td class="item_input">
				<input   type="text" id="fd_team" name="fd_team" maxlength="30" minbytes="4" msg="부서명/직급은 최소 4자 이상 입력하셔야 합니다." class="ipt_tx" value="${admin.fd_team}" />
			</td>				
		</tr>				

		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">비밀번호</td>
			<td class="item_input">
				<input   type="password" id="fd_admin_pw" name="fd_admin_pw"  maxlength="16" match="re_fd_admin_pw" hname="비밀번호" class="ipt_tx"/>
				비밀번호 변경을 원하시는 경우에만 새로운 비밀번호를 입력 하십시오.
			</td>				
		</tr>		
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>		
		<tr height="30">
			<td class="item_title_border">비밀번호 확인</td>
			<td class="item_input">
				<input   type="password" id="re_fd_admin_pw" name="re_fd_admin_pw" hname="비밀번호 확인" class="ipt_tx"/>
			</td>				
		</tr>												
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">연락처</td>
			<td class="item_input">
				<input   type="text" id="phone1" name="phone1" maxlength="3" require="true" onkeypress="onlyNumber(this);" ispattern="phone" span="3" glue="-" msg="연락처 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${splitInfo.phone1}" />
				-
				<input   type="text" id="phone2" name="phone2" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" value="${splitInfo.phone2}" />
				-
				<input   type="text" id="phone3" name="phone3" maxlength="4" onkeypress="onlyNumber(this);" class="ipt_tx" style="width:50px" value="${splitInfo.phone3}" />
			</td>				
		</tr>				
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">휴대폰</td>
			<td class="item_input">
				<input   type="text" id="mobile1" name="mobile1" maxlength="3" require="true" onkeypress="onlyNumber(this);" ispattern="hp" span="3" glue="-" msg="모바일 번호 형식이 잘못되었습니다." style="width:50px" class="ipt_tx" value="${splitInfo.mobile1}" />
				-
				<input   type="text" id="mobile2" name="mobile2" maxlength="4" onkeypress="onlyNumber(this);" style="width:50px" class="ipt_tx" value="${splitInfo.mobile2}" />
				-
				<input   type="text" id="mobile3" name="mobile3" maxlength="4" onkeypress="onlyNumber(this);" style="width:50px" class="ipt_tx" value="${splitInfo.mobile3}" />
				
			</td>				
		</tr>				
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">이메일</td>
			<td class="item_input">
				<input   type="text" id="email1" name="email1" maxlength="15" require="true" ispattern="email" span="2" glue="@" style="width:80px" class="ipt_tx" value="${splitInfo.email1}" /> 
				@ 
				<input   type="text" id="email2" name="email2" maxlength="20" class="ipt_tx" value="${splitInfo.email2}"/>
			</td>				
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr>
			<td class="item_title_border">관리자 권한</td>
			<td class="item_input">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding:0 0 0 0">
					<tr height="0">
						<td width="12%"></td>
						<td width="88%"></td>
					</tr>
					<c:forEach var="mainMenu" items="${cmsMenuList}" varStatus="status" >
						<c:if test="${mainMenu.fd_group_code == '0000' }"> 

							<tr height="30">
								<td>
									<c:set value="" var="check" />
									<c:forEach var="aclMainMenu" items="${aclMenuList }">
										<c:if test="${mainMenu.pk_url_code == aclMainMenu.pk_url_code }"><c:set value="checked='checked'" var="check" /></c:if> 
									</c:forEach>
									<label><input type="checkbox" id="mainMenu${status.index}" name="permissions" value="${mainMenu.pk_url_code }" ${check }>${mainMenu.fd_name }</label>
								</td>
								<td>
									<c:forEach var="subMenu" items="${cmsMenuList}" varStatus="statussub">
										<c:if test="${mainMenu.pk_url_code == subMenu.fd_group_code }">
											<c:set value="" var="check" />
											<c:forEach var="aclSubMenu" items="${aclMenuList }">
												<c:if test="${subMenu.pk_url_code == aclSubMenu.pk_url_code }"><c:set value="checked='checked'" var="check" /></c:if> 
											</c:forEach>
											<label><input type="checkbox" id="subMenu${status.index}_${statussub.index}" name="permissions" value="${subMenu.pk_url_code }" ${check} }>${subMenu.fd_name }&nbsp;</label>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">관리자 등급</td>
			<td class="item_input">
				<select  id="fd_admin_level_cd" name="fd_admin_level_cd">
				<c:forEach var="item" items="${codeLevels}" varStatus="status" >
					<option value="${item.pk_code}" <c:if test="${admin.fd_admin_level_cd == item.pk_code}">selected="selected"</c:if>>${item.fd_name}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
		<tr height="30">
			<td class="item_title_border">회원 상태</td>
			<td class="item_input">
				<select id="fd_admin_status_cd" name="fd_admin_status_cd">
				<c:forEach var="item" items="${codeStatus}" varStatus="status" >
					<option value="${item.pk_code}" <c:if test="${admin.fd_admin_status_cd == item.pk_code}">selected="selected"</c:if>>${item.fd_name}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		</form>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="50">
			<td style="text-align:center;padding:10px" >
				<c:choose>
				<c:when test="${admin.pk_admin_id != null}">
					<input type="button" id="go_delBtn" class="button" value="삭제하기" style="width:100px" />
					<input type="button" id="go_submitBtn" class="button" value="수정하기" style="width:100px" />
				</c:when>
				<c:otherwise>
					<input type="button" id="go_submitBtn" class="button" value="저장하기" style="width:100px" />
				</c:otherwise>
				</c:choose>
				</a>
				<input type="button" id="go_listBtn" class="button" value="목록보기" style="width:100px" />
			</td>
		</tr>
	</table>	
	--%>
</jsp:body>
</layout:common>