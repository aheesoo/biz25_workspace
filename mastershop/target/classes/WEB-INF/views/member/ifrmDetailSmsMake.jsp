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
			.alignC{text-align:center;}
			.alignR{text-align:right;}
			.send_area{
				position:absolute;
				top:10px;
				width:90%;
				padding:10px;
				background-Color:#b4b4b4;
				z-index:1000;
			 	display:none;
			}
			.send_area > div{
				background-Color:#fff;
			}
		</style>
	</jsp:attribute>

	<jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>
		<script src="/resources/js/jquery.ui.datepicker-ko.js"></script>
		<script langauge="text/javascript">	
			${script}

			function openTeb(tabName){
				
			}

			function area_view(area){
				if($('#'+area).css("display") != "none"){
					pop_background("off");
					$('#'+area).hide();
				}else{
					$('#'+area).show();
					pop_background("on");
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
				<td class="item_title_border">업종</td>
				<td class="item_input">&nbsp;&nbsp; 서비스업</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">상호(성명)</td>
				<td class="item_input">&nbsp;&nbsp; 디벨로퍼스헤븐</td>
				<td class="item_title_border">상태</td>
				<td class="item_input">&nbsp;&nbsp; 가입</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">사업자번호</td>
				<td class="item_input_border">&nbsp;&nbsp;220-88-22149</td>
				<td class="item_title_border">주민(법인)번호</td>
				<td class="item_input">&nbsp;&nbsp;1110111-4652297</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">일반전화번호</td>
				<td class="item_input_border">&nbsp;&nbsp;070-8802-0718</td>
				<td class="item_title_border">휴대전화번호</td>
				<td class="item_input">&nbsp;&nbsp;010-8625-8749</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">주소</td>
				<td class="item_input_border" colspan="3">
					&nbsp;&nbsp;135-555<br>
					&nbsp;&nbsp;서울 특별시...
				</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">청약 증빙자료</td>
				<td class="item_input_border" colspan="3">
					&nbsp;&nbsp; <a href="">revenue.avi</a><br>
					&nbsp;&nbsp; <a href="">신청서.pdf</a>
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
								<span>비성화</span>&nbsp;&nbsp;
								<span>(admin | 2014.06.28 14:52:32)</span>
							</td>
						</tr>
						<tr>
							<td>
								<textarea rows="5" cols="140">음란성 컨텐츠 배포로 인한 3회....</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%">
			<tr>
				<td width="10px"><input type="button" value="이전"></td>
				<td width="*"></td>
				<td width="10px"><input type="button" value="수정"></td>
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
				<td class="item_input_border alignC">Shop Master 월정액<br><span>~2014.07.19)</span></td>
				<td class="item_input_border alignC">1.000 Coin</td>
				<td class="item_input">청약</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2</td>
				<td class="item_input_border alignC">070-8802-0718</td>
				<td class="item_input_border alignC">2014-06-20</td>
				<td class="item_input_border alignC">Shop Master 월정액<br><span>~2014.07.19)</span></td>
				<td class="item_input_border alignC">1.000 Coin</td>
				<td class="item_input">청약</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">1</td>
				<td class="item_input_border alignC">070-8802-0718</td>
				<td class="item_input_border alignC">2014-06-20</td>
				<td class="item_input_border alignC">Shop Master 월정액<br><span>~2014.07.19)</span></td>
				<td class="item_input_border alignC">1.000 Coin</td>
				<td class="item_input">청약</td>
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

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
			<tr height="0">
				<th width="*"></th>
			</tr>
			<tr height="30">
				<td>
					<input type="button" onclick="openTeb('divTab1')" value="고객 컨설팅" />
					<input type="button" onclick="openTeb('divTab2')" value="문자 사용내역" />
					<input type="button" onclick="openTeb('divTab3')" value="포인트 충전내역" />
					<input type="button" onclick="openTeb('divTab4')" value="요금 명세서" />
				</td>
				<td></td>
			</tr>
		</table>

<br>

		<table width="100%">
			<tr>
				<td width="50%">
					<select>
						<option value="2014-06">2014년 06월</option>
					</select>
				</td>
				<td class="alignR" width="50%">
					<input type="button" class="button" onclick="area_view('send_area')" value="문자 제작"/>
				</td>
			</tr>
		</table>

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">등록일</td>
				<td class="item_title_border">발송(예약)일</td>
				<td class="item_title_border">타입</td>
				<td class="item_title_border">구분</td>
				<td class="item_title_border">발송건수</td>
				<td class="item_title_border">성공건수</td>
				<td class="item_title_border">실패건수</td>
				<td class="item_title_border">소진 포인트</td>
				<td class="item_title">문자발송</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2014.06.02</td>
				<td class="item_input_border alignC">2014.06.18</td>
				<td class="item_input_border alignC">SMS</td>
				<td class="item_input_border alignC">예약</td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input alignC">취소하기</td>
			</tr>


			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2014.06.02</td>
				<td class="item_input_border alignC">2014.06.18</td>
				<td class="item_input_border alignC">SMS</td>
				<td class="item_input_border alignC">예약</td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input alignC">취소하기</td>
			</tr>


			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2014.06.02</td>
				<td class="item_input_border alignC">2014.06.18</td>
				<td class="item_input_border alignC">SMS</td>
				<td class="item_input_border alignC">예약</td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">45</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">15</span></td>
				<td class="item_input alignC">취소하기</td>
			</tr>
			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
		</table>

<br>

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
			</tr>

			<tr height="1"><td colspan="8" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">총 발송건수</td>
				<td class="item_input_border alignC">648</td>
				<td class="item_title_border">총 성공건수</td>
				<td class="item_input_border alignC">644</td>
				<td class="item_title_border">총 실패건수</td>
				<td class="item_input_border alignC">4</td>
				<td class="item_title_border">총 소진 포인트</td>
				<td class="item_input alignC">5,794</td>
			</tr>
			<tr height="1"><td colspan="8" bgcolor="#F2DAB4"></td></tr>
		</table>

<br>
<br>

<!--  POPUP 영역 - 시작  -->
<div id="send_area" class="send_area">
	<div>


		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="130px"></th>
				<th width="130px"></th>
				<th width="130px"></th>
				<th width="130px"></th>
				<th width="*%"></th>
				<th width="130px"></th>
				<th width="*%"></th>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border">발송날짜</td>
				<td class="item_input" colspan="3">
					<select>
						<option value="2014">2014년</option>
					</select>
					<select>
						<option value="07">07월</option>
					</select>
					<select>
						<option value="01">01일</option>
					</select>
				</td>
				<td class="item_input" colspan='3'>
					<label><input type="checkbox" />반복 &nbsp; &nbsp; 매월 <span>01</span>일</label>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border" rowspan="15">대상추출</td>
				<td class="item_input">추출기간</td>
				<td class="item_input" colspan="5">
					<label><input type="radio"> 1개월</label>&nbsp;&nbsp;&nbsp;
					<label><input type="radio"> 3개월</label>&nbsp;&nbsp;&nbsp;
					<label><input type="radio"> 6개월</label>
				</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input" rowspan="11">추출조건</td>
				<td class="item_input" rowspan="3">요일</td>
				<td class="item_input" colspan="4" colspan="4">
					<label><input type="checkbox" />전체</label>
					<label><input type="checkbox" />주중</label>
					<label><input type="checkbox" />주말</label>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input" colspan="4">
					<label><input type="checkbox" />월요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />화요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />수요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />목요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />금요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />토요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />일요일</label>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">시간대</td>
				<td class="item_input" colspan="5">
					<label><input type="checkbox" />전체</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />새벽</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />아침</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />점심</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />저녁</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />밤</label>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">수신횟수</td>
				<td class="item_input" colspan="4">
					<input type="text" />건 &nbsp;&nbsp;&nbsp;
					<select>
						<option>이하</option>
					</select>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">발송횟수</td>
				<td class="item_input" colspan="4">
					<input type="text" />건 &nbsp;&nbsp;&nbsp;
					<select>
						<option>이하</option>
					</select>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">발송건수</td>
				<td class="item_input" colspan="4"><input type="text" />건</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td></td>
				<td>
				145건
				</td>
				<td class="item_input" colspan="4"><input type="button" class="button" value="인쇄"/>&nbsp;&nbsp;<input type="button" class="button" value="엑셀저장"/></td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border">상담 메모</td>
				<td class="item_input" colspan="6">
					상담 말머리 &nbsp;&nbsp;<input type="text"/><br/>
					<textarea rows="5" cols="60"></textarea>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border">발신번호</td>
				<td class="item_input" colspan="6">
					<select>
						<option>02-561-1511</option>
					</select>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="10px"><td colspan="7"></td></tr>
			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>

			<tr height="30">
				<td class="item_title_border">타입</td>
				<td class="item_input" colspan="2">
					<select>
						<option>SMS</option>
					</select>
				</td>
				<td colspan="4">
					<textarea rows="5" cols="60"></textarea> &nbsp;&nbsp;<input type="button" class="button" value="추천문구"/>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="10px"><td colspan="7"></td></tr>
			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>

			<tr height="30">
				<td class="item_title_border">포인트</td>
				<td class="item_input" colspan="6">286Coin(예정)</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="10px"><td colspan="7"></td></tr>

			<tr height="30">
				<td class="item_input alignR" width="10px"colspan="7">
					<input type="button" class="button" value="등록하기"/>
					<input type="button" class="button" onclick="area_view('send_area');" value="취소"/>
				</td>
			</tr>
		</table>

	</div>
</div>
<!--  POPUP 영역 - 끝  -->

	</div>
</jsp:body>
</layout:common>