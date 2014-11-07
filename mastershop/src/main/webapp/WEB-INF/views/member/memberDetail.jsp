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

			label.rintiantta {color:Red;}
			.alignC{text-align:center;}
			.alignC{text-align:center;}
			.alignR{text-align:right;}
			.report_area{
				position:absolute;
				top:10px;
				width:90%;
				padding:10px;
				background-Color:#b4b4b4;
				z-index:1000;
			 	display:none;
			}
			.report_area > div{
				background-Color:#fff;
			}
			.sendDay{color:red;}
		</style>
	</jsp:attribute>

	<jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>
		<script src="/resources/js/jquery.ui.datepicker-ko.js"></script>
		<script langauge="text/javascript">	
			${script}

			function openTeb(tabName){
				
				if(tabName=='divTab1'){
					//고객 컨설팅
					ifrm.location.href = "/member/ifrmDetailConsult.do";
				}else if(tabName=='divTab2'){
					//문사 사용내역
					ifrm.location.href = "/member/ifrmDetailSms.do";
				}else if(tabName=='divTab3'){
					//포인트 충전내역
					ifrm.location.href = "/member/ifrmDetailPoint.do";
				}else if(tabName=='divTab4'){
					//요금 명세서
					ifrm.location.href = "/member/ifrmDetailPrice.do";
				}
			}

			function area_view(area){
				if($('#'+area).css("display") != "none"){
					if(area!="result_area"){
						pop_background("off");
					}
					$('#'+area).hide();
				}else{
					if(area!="result_area"){
						pop_background("on");
					}
					$('#'+area).show();
				}
			}

			function pop_background(type){
				var $div = $('<div id="progressDiv_z" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50); z-index:999;"></div>');
 				if(type=="on"){
					$('#content').append($div);
 				}else{
 					$('#progressDiv_z').remove();﻿﻿ 
 				}
 			}
			
			
			
		</script>
		<script type='text/javascript'>
		// iframe resize
		
		function getInternetExplorerVersion() {
		    var rv = -1; // Return value assumes failure.
		    if (navigator.appName == 'Microsoft Internet Explorer') {
		        var ua = navigator.userAgent;
		        var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		        if (re.exec(ua) != null)
		            rv = parseFloat(RegExp.$1);
		    }
		    return rv;
		}
		
		
		//iframe autoresize();
		$(function(){
			$('#ifrm').load(function(){
				   $('#ifrm').attr("height",ifrm.document.body.scrollHeight+100);
			});
		});
			
		
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
				<td class="item_input_border">&nbsp;revenue</td>
				<td class="item_title_border">업종</td>
				<td class="item_input">&nbsp; 서비스업</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">상호(성명)</td>
				<td class="item_input">커피 모모(분당점)</td>
				<td class="item_title_border">상태</td>
				<td class="item_input">&nbsp; 가입</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">사업자번호</td>
				<td class="item_input_border">&nbsp;220-88-22149</td>
				<td class="item_title_border">주민(법인)번호</td>
				<td class="item_input">&nbsp;1110111-4652297</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">일반전화번호</td>
				<td class="item_input_border">&nbsp;070-8802-0718</td>
				<td class="item_title_border">휴대전화번호</td>
				<td class="item_input">&nbsp;010-8625-8749</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">주소</td>
				<td class="item_input_border" colspan="3">
					&nbsp;135-555<br>
					&nbsp;경기도 분당구 정자동 213-4
				</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">청약 증빙자료</td>
				<td class="item_input_border" colspan="3">
					&nbsp; <a href="">revenue.avi</a><br>
					&nbsp; <a href="">신청서.pdf</a>
				</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">회원징계</td>
				<td class="item_input_border" colspan="3">
					<span>상태</span>&nbsp;<span>비성화</span>&nbsp;<span>(admin | 2014.06.28 14:52:32)</span><br>
					<textarea style="width:90%" rows="3" cols="100">음란성 컨텐츠 배포로 인한 3회....</textarea>
				</td>
			</tr>
		</table>
		
		
		
		<table width="100%">
			<tr>
				<td width="10px"><input type="button" class="button" value="이전"></td>
				<td width="*"></td>
				<td width="10px"><input type="button" class="button" value="수정"></td>
			</tr>
		</table>

		<br>
		<br>

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #F2DAB4 1px solid; padding:0 0 0 0">

			<tr height="0">
				<th width="10%"></th>
				<th width="18%"></th>
				<th width="18%"></th>
				<th width="18%"></th>
				<th width="18%"></th>
				<th width="18%"></th>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">No.</td>
				<td class="item_title_border">서비스번호</td>
				<td class="item_title_border">가입일자</td>
				<td class="item_title_border">가입상품</td>
				<td class="item_title_border">포인트</td>
				<td class="item_title">가입상태</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">3</td>
				<td class="item_input_border alignC">070-8802-0718</td>
				<td class="item_input_border alignC">2014-06-20</td>
				<td class="item_input_border alignC">Shop Master 월정액<br><span>(2014.07.19~)</span></td>
				<td class="item_input_border alignC">1.000 Coin</td>
				<td class="item_input">청약</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2</td>
				<td class="item_input_border alignC">070-8802-0718</td>
				<td class="item_input_border alignC">2014-06-20</td>
				<td class="item_input_border alignC">Shop Master 월정액<br><span>(2014.07.19~)</span></td>
				<td class="item_input_border alignC">1.000 Coin</td>
				<td class="item_input">청약</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">1</td>
				<td class="item_input_border alignC">070-8802-0718</td>
				<td class="item_input_border alignC">2014-06-20</td>
				<td class="item_input_border alignC">Shop Master 월정액<br><span>(~2014.07.19)</span></td>
				<td class="item_input_border alignC">1.000 Coin</td>
				<td class="item_input">해지</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="10"><td colspan="6"></td></tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border" colspan="2">통화 Open API 가입상태</td>
				<td class="item_input" colspan="4">가입 기간(2014-06-02)</td>
			</tr>

		</table>

<br>
<br>


		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #F2DAB4 1px solid; padding:0 0 0 0">
			<tr height="0">
				<th width="25%"></th>
				<th width="25%"></th>
				<th width="25%"></th>
				<th width="25%"></th>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border"><a href="#" onclick="openTeb('divTab1')">고객 컨설팅</a></td>
				<td class="item_title_border"><a href="#" onclick="openTeb('divTab2')">문자 사용내역</a></td>
				<td class="item_title_border"><a href="#" onclick="openTeb('divTab3')">포인트 충전내역</a></td>
				<td class="item_title_border"><a href="#" onclick="openTeb('divTab4')">요금 명세서</a></td>
			</tr>
		</table>
		<iframe name="ifrm" id="ifrm" width="100%" height="100%" frameborder="0" src=""></iframe>

	</div>
</jsp:body>
</layout:common>