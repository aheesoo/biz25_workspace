<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>
	<jsp:attribute name="stylesheet">		
	</jsp:attribute>

	<jsp:attribute name="javascript">		
		<script langauge="text/javascript">	
			${script}

			$(document).ready(function() {
				
				// DO NOT REMOVE : GLOBAL FUNCTIONS!
				pageSetUp();
			});
			
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
									<table class="table table-bordered">
										<tbody>
											<tr height="30">
												<th>청약 ID *</th>
												<td>&nbsp;&nbsp;<input type="text" id="" name=""/>
													<button class="btn btn-default" type="button">조회</button>
												</td>
												</tr>		
											<tr height="30">
												<th>식별 번호 *</th>
												<td>111111 - 
													<input type="text" value="" style="width:80px" />
													<button class="btn btn-default" type="button">인증</button>
												</td>
											</tr>
										</tbody>
									</table>										
								
									
									<table class="table table-bordered">
										<tbody>
											<tr height="30">
												<th width="20%">회선 번호</th>
												<td width="30%">02-561-1511</td>
												<th width="20%">업종</th>
												<td width="30%">서비스업</td>
											</tr>		
											<tr height="30">
												<th>상호(성명)</th>
												<td>OO분식</td>
												<th>가입 상태</th>
												<td>가입</td>
											</tr>
											<tr height="30">
												<th>상품 타입</th>
												<td>월 정액/[선불충전] A Type</td>
												<th>통화 OpenAPI 상태</th>
												<td>가입</td>
											</tr>
										<tbody>
									</table>										
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