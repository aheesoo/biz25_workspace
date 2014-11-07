<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:noFrame>
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

			$(document).ready(
				function () {
						$('.button').button();
					}
				);
				
				//검색
				function sendIt(zipcode, addr){
					
					var form = document.zipForm;
					str = form.dong.value;
					str = str.trim();
					if(!str){
						alert("\n검색할 동을 입력하세요..");
						form.dong.focus();
						return;
					}
					form.dong.value = str;
					
					form.method = "post";
					form.action = '/member/zipcode.do';
					form.submit();
				}
				
				//우편번호 세팅
				function setZipcode(zipcode, addr){

					var zip1 = "";
					var zip2 = "";
					
					if(zipcode.length == 6){
						zip1 = zipcode.substring(0,3);
						zip2 = zipcode.substring(3,6);
					}
					
					opener.document.form.fd_post_num1.value		= zip1;
					opener.document.form.fd_post_num2.value		= zip2;
					opener.document.form.fd_addr.value	= addr;
					opener.document.form.fd_addr_detail.focus();
					self.close();
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
						<!-- widget div-->
						<div>
							<!-- widget edit box -->
							<div class="jarviswidget-editbox">
								<!-- This area used as dropdown edit box -->
							</div>
							<!-- end widget edit box -->

							<!-- widget content -->
							<div class="widget-body">
<!-- 내용 -->
								<div class="table-responsive">
									<div class="form-group">

<div>
	<form action="/member/zipcode.do" name="zipForm" method="post">
	<input type="hidden" name="hc_flag" value="${hc_flag}">
	<table class="table table-bordered" style="padding:0px">
		<colgroup>
			<col width="100px"/>
			<col width="90px"/>
			<col width="*"/>
			<col width="80px"/>
		</colgroup>
	<thead>
		<tr height="30px">
			<th colspan="4">
				<b>우편번호 찾기</b>
			</th>
		</tr>
	</thead>
	<tbody>
		<tr height="50">
			<td colspan="4" >
				찾고자하는 지역의 동/읍/면/리/건물명을 입력하세요<br/>
				서울시 강서구 화곡동이라면 <strong>화곡</strong>
				또는 <strong>화곡동</strong>이라고 입력하시면 됩니다.
			</td>
		</tr>
		
		<tr height="30">
			<th style="padding-left:5px;padding-right:5px;text-align:center">
				우편번호 검색
			</th>
			<td style="padding-left:5px;padding-right:5px;text-align:center">
				지역명
			</td>
			<td style="padding-left:5px;padding-right:5px;text-align:center">
				<div class="col-sm-2"><input type="text" name="dong" maxlength="30" minbytes="2" msg="동이름은 최소 2자 이상 입력하셔야 합니다." /></div>
			</td>
			<td style="padding-left:5px;padding-right:5px;text-align:center">
				<div class="col-sm-2">
					<button class="btn btn-primary" type="button" id="btnSearch" onclick="sendIt()">검색</button>
				</div>
			</td>
		</tr>
		<tr height="40px">
			<td colspan="4" align="center">
				검색 후 아래에서 해당주소를 클릭하시면 자동입력됩니다.
			</td>
		</tr>
	</tbody>
	</table>
	</form>
	<br>

	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: F2DAB4 1px solid; padding:0 0 0 0">
	<thead>
		<tr height="30">
			<th width="100px">우편번호</th>
			<th width="*" class="item_title">주 소</th>
		</tr>
	</thead>
	<tbody>
		
		<c:if test="${!empty zipcodeList}">
			<c:forEach var="item" items="${zipcodeList}" varStatus="status" >
				<tr height="25" onmouseover="this.style.backgroundColor='#F2DAB4'" onmouseout="this.style.backgroundColor=''" style="cursor:pointer;" onclick="setZipcode('${item.post}', '${item.addr1}' +' '+ '${item.addr2}' +' '+ '${item.addr3}' +' '+ '${item.addr4}' +' '+ '${item.addr5}' +' '+ '${item.bunji}')">
					<td class="item_input_border" align="center">
						<c:set var="post1" value="${item.post.substring(0,3)}"/>
						<c:set var="post2" value="${item.post.substring(3,6)}"/>
						${post1} - ${post2}
					</td>
					<td class="item_input_border" align="center">
						${item.addr1}&nbsp;${item.addr2}&nbsp;${item.addr3}&nbsp;${item.addr4}&nbsp;${item.addr5}&nbsp;${item.bunji}
				</td>
			</tr>
			</c:forEach>
		</c:if>
	
	
		<c:if test="${empty zipcodeList}">
			<tr height="1"><td colspan="2" bgcolor="#F2DAB4"></td></tr>
			<tr height="80" >
				<td class="item_input" align="center" colspan="2">&nbsp;검색된 데이타가 없습니다</td>
			</tr>
		</c:if>
	</tbody>
	</table>

	<table width="100%">
		<tr height="50">
			<td colspan="2" align="center">
				<button class="btn btn-primary" type="button" id="btnClose" onclick="self.close()">닫기</button>
			</td>
		</tr>
	</table>
	
</div>


									</div>
								</div>
<!-- 내용 -->
							</div>
						</div>
					</div>
						<!-- end widget div -->
				</article>
			</div>
		</section>
	</div>

</jsp:body>
</layout:noFrame>