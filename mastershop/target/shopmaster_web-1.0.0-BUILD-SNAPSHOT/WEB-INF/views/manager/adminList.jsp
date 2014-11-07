<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>

<layout:common>
    <jsp:attribute name="javascript">
    	<script langauge="javascript">
    		var searchColumn = '${param.searchColumn}' == '' ? 'aName' : '${param.searchColumn}';
    	
    		$(document).ready
    		(
    			function()
    			{
    				$('#searchColumn').val(searchColumn);
    				
    				$('#btnSearch').click(goSearch);    				
    				$('#btnRegister').click(function(){ location.href = '/manager/adminRegister.do';});
    			}	
    		);
    	
    		function goSearch()
    		{
    			var form = document.form;
    			form.submit();
    		}
    		
    		function goView(admin_id)
    		{
    			var form = document.listForm;
    			form.action = '/manager/adminModify.do'; 
    			form.pk_admin_id.value = admin_id;
    			form.submit();
    		}
    		
    	</script>
    	
    	${pageNavi.script }
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
									<div class="form-group">
										<form name="form" method="get">						
											<table class="table table-bordered">
												<tbody>
													<tr>
														<th align="center">검색</th>
														<td>														
															<div class="col-sm-2">
																<select class="form-control" id="searchColumn" name="searchColumn">
																	<option  value="aId">아이디</option>
																	<option  value="aName">이름</option>
																</select>
															</div>
															<div class="col-sm-3">
																<input class="form-control" placeholder="검색어 입력." type="text" name="searchString" value="${param.searchString }">
															</div>
															<div class="col-sm-2">	
																<button class="btn btn-default" type="button">검색</button>
															</div>														
														</td>												
													</tr>	
												</tbody>
											</table>
										</form>
									</div>									
								</div>
								<div class="table-responsive">
									<div class="form-group">
										<form id="listForm" name="listForm" method="post">
											<input type="hidden" name="pk_admin_id" value="" />
											<input type="hidden" name="page" value="${pageHelper.nowPage}" />
											<input type="hidden" name="searchColumn" value="${pageHelper.searchColumn}" />
											<input type="hidden" name="searchString" value="${pageHelper.searchString}" />									
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>번호</th>
														<th>이름</th>
														<th>아이디</th>
														<th>전화번호</th>
														<th>휴대폰</th>			
														<th>이메일</th>			
														<th>상태</th>			
														<th>권한</th>			
													</tr>
												</thead>
												<tbody>
													<!-- DYNAMIC AREA 'list' -->
													<c:if test="${!empty adminList}">
														<c:forEach var="item" items="${adminList}" varStatus="status" >														
														<tr height="25"> 	
															<td align="center">&nbsp;${pageNavi.totalCount - ((pageNavi.nowPage - 1) * pageNavi.pageSize + status.index) }</td>
															<td align="center">&nbsp;${item.fd_admin_name}</td>
															<td align="center">&nbsp;<a href="#" onclick="goView('${item.pk_admin_id}');">${item.pk_admin_id}</a></td>
															<td align="center">&nbsp;${item.fd_phone}</td>
															<td align="center">&nbsp;${item.fd_mobile}</td>
															<td align="center">&nbsp;${item.fd_email}</td>
															<td align="center">&nbsp;${admin_status_cd[status.index].fd_name}</td>
															<td align="center">&nbsp;${admin_level_cd[status.index].fd_name}</td>
														</tr>
														</c:forEach>
													</c:if>
													<c:if test="${empty adminList}">														
														<tr> 	
															<td class="col-sm-8">&nbsp;검색된 데이타가 없습니다</td>
														</tr>
													</c:if>				
													<!-- DYNAMIC AREA 'list' -->
												</tbody>
											</table>
										</form>
									</div>
									<table class="table table-bordered">
										<tr height="30"> 	
											<td align="center" class="col-sm-8">
												${pageNavi.navi}
											</td>
										</tr>
									</table>
								</div>
								<div class="form-actions">
												<div class="row">
													<div class="col-md-12">														
														<button class="btn btn-primary" id="btnRegister"  type="button"><i class="fa fa-save"></i>등록</button>
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
			<form name="form" method="get">
			<tr height="0">
				<td width="15%"></td>
				<td width="85%"></td>
			</tr>
			<tr height="30">
				<td class="item_title_border">검색</td>
				<td class="item_input">
					<select id="searchColumn" name="searchColumn" style="width:128px;">
						<option  value="aId">아이디</option>
						<option  value="aName">이름</option>
					</select>		
					<input  type="text" id="searchString" name="searchString" value="${param.searchString }">		
					<input type="button" id="btnSearch" class="button" value="검색" style="width:80px" />							
				</td>				
			</tr>				
			</form>
		</table>			
		<br>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #EDCE9B 1px solid; padding:0 0 0 0">
			<form id="listForm" name="listForm" method="post">
				<input type="hidden" name="pk_admin_id" value="" />
				<input type="hidden" name="page" value="${pageHelper.nowPage}" />
				<input type="hidden" name="searchColumn" value="${pageHelper.searchColumn}" />
				<input type="hidden" name="searchString" value="${pageHelper.searchString}" />
			</form>
			<tr height="0">
				<td width="12.5%"></td>
				<td width="12.5%"></td>
				<td width="12.5%"></td>
				<td width="12.5%"></td>
				<td width="12.5%"></td>
				<td width="12.5%"></td>
				<td width="12.5%"></td>
				<td width="12.5%"></td>
			</tr>
			<tr height="25">
				<td class="item_title_border">번호</td>
				<td class="item_title_border">이름</td>
				<td class="item_title_border">아이디</td>
				<td class="item_title_border">전화번호</td>
				<td class="item_title_border">휴대폰</td>			
				<td class="item_title_border">이메일</td>			
				<td class="item_title_border">상태</td>			
				<td class="item_title">권한</td>			
			</tr>	

			<!-- DYNAMIC AREA 'list' -->
			<c:if test="${!empty adminList}">
				<c:forEach var="item" items="${adminList}" varStatus="status" >
				<tr height="1"><td colspan="8" bgcolor="F2DAB4"></td></tr>
				<tr height="25" onmouseover="this.style.backgroundColor='#f7f7f7'" onmouseout="this.style.backgroundColor=''" bgcolor="white"> 	
					<td class="item_input_border" align="center">&nbsp;${pageNavi.totalCount - ((pageNavi.nowPage - 1) * pageNavi.pageSize + status.index) }</td>
					<td class="item_input_border" align="center">&nbsp;${item.fd_admin_name}</td>
					<td class="item_input_border" align="center">&nbsp;<a href="#" onclick="goView('${item.pk_admin_id}');">${item.pk_admin_id}</a></td>
					<td class="item_input_border" align="center">&nbsp;${item.fd_phone}</td>
					<td class="item_input_border" align="center">&nbsp;${item.fd_mobile}</td>
					<td class="item_input_border" align="center">&nbsp;${item.fd_email}</td>
					<td class="item_input_border" align="center">&nbsp;${admin_status_cd[status.index].fd_name}</td>
					<td class="item_input" align="center">&nbsp;${admin_level_cd[status.index].fd_name}</td>
				</tr>
				</c:forEach>
			</c:if>			

			<c:if test="${empty adminList}">
				<tr height="1"><td colspan="8" bgcolor="F2DAB4"></td></tr>
				<tr height="60" bgcolor="white"> 	
					<td class="item_input" align="center" colspan="8">&nbsp;검색된 데이타가 없습니다</td>
				</tr>
			</c:if>				
			<!-- DYNAMIC AREA 'list' -->

			<tr height="1"><td colspan="8" bgcolor="F2DAB4"></td></tr>
			<tr height="30"> 	
				<td align="center" colspan="8">
					${pageNavi.navi}
				</td>
			</tr>
		</table>        
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr height="50">
				<td style="text-align:center;padding:10px" >
					<input type="button" id="btnRegister" class="button" value="등록" style="width:80px" />
				</td>
			</tr>
		</table>
		 --%>			
    </jsp:body>
</layout:common>