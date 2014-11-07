<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>

	<jsp:attribute name="stylesheet">
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	<script type="text/javascript">
		$(document).ready(function(){
			$('.bgchange').mouseover(function(){
				$(this).find('td').css('background','#e0e6ec');
			}).mouseout(function(){
				$(this).find('td').css('background','');
			});
			$("#inputFile").change(function(){
				var frm = document.formFile;
				if(file_check()){
					frm.submit();
				}else{
					//alert('이미지 등록은 확장자가 "xls"만 가능합니다."');
					top.parent.alertArea(300, 35, 'center', '알 림', '"xls"확장자만 가능합니다.');
				}
			});
			/* $("#csTel1").keyup(function(){ 
				var tel1_length = $("#csTel1").val().length;
				if(tel1_length == 3){
					document.insertForm.csTel2.focus();
				}
				
			});
			$("#csTel2").keyup(function(){
				var tel1_length = $("#csTel2").val().length;
				if(tel1_length == 4){
					document.insertForm.csTel3.focus();
				}
				
			});
			$("#csTel3").keyup(function(){
				var tel1_length = $("#csTel3").val().length;
				if(tel1_length == 4){
					document.insertForm.csName.focus();
				}
				
			}); */
		});

		//확장자 체크.
		function file_check() {
			var file_ext = $('#inputFile').val();
			file_ext = file_ext.split(".")[file_ext.split(".").length-1].toLowerCase();
//			alert(file_ext);
			if(file_ext == "xls"){ return true; }else{ return false; }
		}

		function goOrder(colName){
			//타이틀 정렬
			var frm = document.goPageForm;
			if(frm.orderColumn.value == colName){
				//d : desc(기본) , 없을경우 처음에는 desc, 다음번 클릭은 asc
				if(frm.orderTyp.value==""){
					frm.orderTyp.value="d";
				}else{
					frm.orderTyp.value="";
				}
			}else{
				frm.orderColumn.value=colName;
				frm.orderTyp.value="d";
			}
			goSearch('T');
		}
		function goSearch(type){
			var frm = document.goPageForm;

			//type 이 B 일경우 검색조건체크 
			if(type == 'B'){
				if($('#searchColumn').val() == ''){
					alert('검색 조건을 선택해주세요.');
					frm.searchColumn.focus();
					return;
				}
				
				if(frm.searchString.value==''){
					alert("검색어를 입력해 주세요.");
					frm.searchString.focus();
					return;				
				}
			}
			
			frm.action = "/csManager/csManagerMain.do";
			frm.page.value = '';
			frm.pk_customer_tel.value = '';
			frm.submit();
		}
		function goView(fk_member_id,pk_customer_tel){
			//뷰 페이지로 이동
			var frm = document.goPageForm;
			frm.action = "/csManager/csManagerDetail.do";
			frm.page.value = '${param.page}';
			frm.pk_customer_tel.value = pk_customer_tel;
			frm.submit();
		}
		function saveExl(){
			//엑셀파일로 저장
			var frm = document.goPageForm;
			frm.action = "/csManager/csManagerListExcel.do";
			frm.page.value = '';
			frm.pk_customer_tel.value = '';
			frm.submit();
		}
		function chkDel(){
			//선택값들 삭제하러
			if($("input[name=check]:checked").length > 0){
				if(confirm($("input[name=check]:checked").length+"개의 고객정보를 삭제 하시겠습니까?")){
					var frm = document.goPageForm;
					frm.action = "/csManager/csManagerPro.do";
					frm.method = "post";
					frm.page.value = '${param.page}';
					frm.goType.value = 'del';
					frm.pk_customer_tel.value = '';
					frm.submit();
				}
			}else{
				alert("선택된 항목이 없습니다.");
			}
		}
		
		function customerIns(){
			//고객등록(단건)
			if(checkByte("csMemo", "", 2048)){
				var frm = document.insertForm;
				if(frm.csTel1.value==''){
					alert("전화번호는 필수 입력 사항입니다.");
					frm.csTel1.focus();
					return;
				}else if(frm.csTel2.value==''){
					alert("전화번호는 필수 입력 사항입니다.");
					frm.csTel2.focus();
					return;
				}else if(frm.csTel3.value==''){
					alert("전화번호는 필수 입력 사항입니다.");
					frm.csTel3.focus();
					return;
				}else if(frm.csName.value==''){
					alert("이름은 필수 입력 사항입니다.");
					frm.csName.focus();
					return;
				}else{
					frm.action = "/csManager/csManagerPro.do";
					frm.goType.value = 'ins';
					frm.submit();
				}
			}else{
				alert('메모는 2048byte까지 가능합니다.');
			}
		}
		
		function divOnOff(dName){
			//등록팝업 열거나 닫을때 호출
			$('#csTel1').val("");
			$('#csTel2').val("");
			$('#csTel3').val("");
			$('#csName').val("");
			$('#csAddr').val("");
			$('#csMemo').val("");
			
			$('#'+dName).toggle();
		}

		String.prototype.only_number = function(){
			//숫자만 입력
	   		return this.replace(/[^0-9]/gi, '');
	  	}
		
		//문자 byte값 구하기. 
		function strCheck(){
			checkByte("csMemo", "", 2048);
		}
		
		//문자 byte값 연산 함수
		function checkByte (strId, strName, maxLength){ 
		    var tcount = 0;
		    var str = document.getElementById(strId).value;
		    var length = str.length;
		   
		    for(var i = 0; i < length; i++){
		    	var byteStr = str.charAt(i);
		      	if(escape(byteStr).length > 4){
		        	tcount += 2;
		      	}else{
		        	tcount += 1;
		      	}
		    }
		    
		  //지정된 문자 byte 값 초과시
		    if(tcount > maxLength){
			    $("#byte_text").css("color", "#FF0000");
		   		$("#byte_text").text(tcount + "/" + maxLength + " byte");
		      	return false;
		    }else{
		  		//document.getElementById("div_view").innerHTML = tcount +"/"+ maxLength+ " byte";
		  		$("#byte_text").css("color", "#222222");
		  		$("#byte_text").text(tcount + "/" + maxLength + " byte");
		  		return true;
		    }
	  	}
		
		
		
		
	</script>
	${pageNavi.script }
	</jsp:attribute>
	
	<jsp:body>

<div id="insertDiv" style="display:none;position:absolute;text-align:center;width:100%;height:1px;">
<!-- 팝업 insert -->
	<div id="popup" style="height:350px;">
	<form id="insertForm" name="insertForm" method="post">
		<input type="hidden" id="goType" name="goType"/>
		<div class="pop_top">
			<div class="pop_x"><a href="javascript:divOnOff('insertDiv')"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
	    	<div class="pop_tit">고객정보 등록</div>
	    </div>
	    <div class="pop_txt">
	        <table width="470" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td colspan="2" height="2" bgcolor="#222222"></td>
	          </tr>
	          <tr>
	            <td class="pop_tb_tit_l">전화번호 <font color="#ff9933">*</font></td>
	            <td class="pop_tb_nor_l">
	            	<input id="csTel1" name="csTel1" type="text" class="fild_st" style="width:80px;" onblur="this.value=this.value.only_number();" maxlength="3"> - 
	            	<input id="csTel2" name="csTel2" type="text" class="fild_st" style="width:80px;" onblur="this.value=this.value.only_number();" maxlength="4"> - 
	            	<input id="csTel3" name="csTel3" type="text" class="fild_st" style="width:80px;" onblur="this.value=this.value.only_number();" maxlength="4">
	            </td>
	          </tr>
	          <tr>
	            <td width="120" class="pop_tb_tit_l">이름 <font color="#ff9933">*</font></td>
	            <td width="350" class="pop_tb_nor_l"><input id="csName" name="csName" type="text" class="fild_st" style="width:110px;"></td>
	          </tr>
	          <tr>
	            <td class="pop_tb_tit_l">주소</td>
	            <td class="pop_tb_nor_l"><input id="csAddr" name="csAddr" type="text" class="fild_st" style="width:98%;"></td>
	          </tr>
	          <tr>
	            <td class="pop_tb_tit_l">메모</td>
	            <td class="pop_tb_nor_l"><textarea id="csMemo" name="csMemo" cols="" rows="" class="txtarea_st" onkeyup="strCheck();" style="width:98%; height:50px;"></textarea>
	            <div style="width: 100px; text-align: right; margin-top: 10px; margin-left: 245px;"><font id="byte_text" style="color:#222222;">0/2000 byte</font></div></td>
	          </tr>
	          <tr>
	            <td class="pop_tb_tit_l">수신거부</td>
	            <td class="pop_tb_nor_l"><input id="csType1" name="csType" type="radio" value="N" checked="checked"/> 수신&nbsp;&nbsp;&nbsp;<input id="csType2" name="csType" type="radio" value="Y" /> 거부</td>
	          </tr>
	        </table>
	    </div>
	    <div class="pop_btn">
	    	<a href="javascript:customerIns()"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>&nbsp;
	    	<a href="javascript:divOnOff('insertDiv')"><img src="/resources/images/shopmaster/btn_cancel.gif" border="0"/></a>
	    </div>
	</form>
	</div>
<!-- 팝업 insert -->
</div>

    <div id="main_r">
		<div class="contents_area">
        	<div class="sub_tit">
            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_cs_admin.gif" border="0"/></div>
            	<div class="sub_now">홈 &gt; <font class="sub_now_t">고객관리</font></div>
            </div>
            <div class="sub_tit_dot"></div>
            <div class="sub_contents">
				<form id="goPageForm" name="goPageForm" method="get">
					<input type="hidden" id="orderColumn" name="orderColumn" value="${param.orderColumn}"/> <!-- 타이틀 정렬컬럼명 -->
					<input type="hidden" id="orderTyp" name="orderTyp" value="${param.orderTyp}"/> <!-- 타이틀 정렬방법 -->
					<input type="hidden" id="page" name="page">
					<input type="hidden" id="pk_customer_tel" name="pk_customer_tel">
					<input type="hidden" id="goType" name="goType"/>

					<div style="height:27px;">
						<div style="float:left; width:55px; margin:4px 0px 0px 5px;">수신옵션</div>
						<div style="float:left; width:589px;">
							<select id="fd_rev_sms_flag" name="fd_rev_sms_flag" class="listmenu_st" style="width:90px;" onchange="goSearch('F')">
								<option value="">모두</option>
								<option value="N" <c:if test="${param.fd_rev_sms_flag=='N'}">selected='selected'</c:if>>수신</option>
								<option value="Y" <c:if test="${param.fd_rev_sms_flag=='Y'}">selected='selected'</c:if>>거부</option>
							</select></div>
						<div style="float:left; width:85px;">
							<select id="searchColumn" name="searchColumn" class="listmenu_st" style="width:80px;">
							<option value="" <c:if test="${param.searchColumn==''}">selected='selected'</c:if>>선택</option>
							<option value="user_name" <c:if test="${param.searchColumn=='user_name'}">selected='selected'</c:if>>이름</option>
							<option value="user_tel" <c:if test="${param.searchColumn=='user_tel'}">selected='selected'</c:if>>전화번호</option>
							<option value="user_addr" <c:if test="${param.searchColumn=='user_addr'}">selected='selected'</c:if>>주소</option>
							</select>
						</div>
						<div style="float:left; width:145px;"><input id="searchString" name="searchString" type="text" class="fild_st" style="width:137px;" value="${param.searchString}"></div>
						<div style="float:left;"><a href="javascript:goSearch('B')"><img src="/resources/images/shopmaster/btn2_search.gif" border="0"/></a></div>
					</div>
	                <div class="tb_top"></div>
	                <div>
	                <c:if test="${view_name_flag != 0}"><c:set var="nameChk" value="display:none;" /></c:if>
	                	<table width="930" border="0" cellspacing="0" cellpadding="0">
		                  <!-- tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
	                      <tr>
	                        <td width="35" class="tb_tit_c"><input type="checkbox" onclick="$('input[id^=check]').attr('checked',this.checked);"/></td>
	                        <td width="90" class="tb_tit_c" style="${nameChk}"><font color="#000000"><a href="javascript:goOrder('fd_user_name')" style="text-decoration:none;${nameChk}">이름</a></font></td>
	                        <td width="105" class="tb_tit_l"><a href="javascript:goOrder('pk_customer_tel')" class="tbt_a" style="text-decoration:none;padding-left:3px">전화번호</a></td>
	                        <td class="tb_tit_c"><a href="javascript:goOrder('fd_addr')" class="tbt_a" style="text-decoration:none;">주소</a></td>
	                        <td width="110" class="tb_tit_c"><a href="javascript:goOrder('fd_last_date')" class="tbt_a" style="text-decoration:none;">최근 통화일시</a></td>
	                        <td width="80" class="tb_tit_c"><a href="javascript:goOrder('fd_call_cnt')" class="tbt_a" style="text-decoration:none;">총 통화건수</a></td>
	                        <td width="90" class="tb_tit_c"><a href="javascript:goOrder('fd_sms_cnt')" class="tbt_a" style="text-decoration:none;">발송건수</a></td>
	                        <td width="65" class="tb_tit_c"><font color="#000000">수신거부</font></td>
	                      </tr>
					<!-- DYNAMIC AREA 'list' -->
					<c:if test="${!empty customerList}">
						<c:forEach var="item" items="${customerList}" varStatus="status" >
							<c:set var="clickEv" value="onclick=\"goView('${item.fk_member_id}','${item.pk_customer_tel_org}')\""/>
							<tr class="bgchange">
								<td class="tb_nor_c"><input type="checkbox" name="check" id="check_${status.index}" value="${item.pk_customer_tel_org}" /></td>
								<td class="tb_nor_c" ${clickEv} style="cursor:pointer;${nameChk}"><font color="#000000">${item.fd_user_name}</font>&nbsp;</td>
								<td class="tb_nor_l" ${clickEv} style="cursor:pointer;"><b>${item.pk_customer_tel}</b></td>
								<td class="tb_nor_l" ${clickEv} style="cursor:pointer;"><b>${item.fd_addr}</b></td>
								<td class="tb_nor_c" ${clickEv} style="cursor:pointer;">
									<fmt:parseDate value="${item.fd_last_date}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
									<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd HH:mm"/>
								</td>
								<td class="tb_nor_c" ${clickEv} style="cursor:pointer;">${item.fd_call_cnt}</td>
								<td class="tb_nor_c" ${clickEv} style="cursor:pointer;">${item.fd_sms_cnt}</td>
								<td class="tb_nor_c" ${clickEv} style="cursor:pointer;">
									<font color="#000000">
										<c:if test="${item.fd_rev_sms_flag=='Y'}">거부</c:if>
										<c:if test="${!(item.fd_rev_sms_flag=='Y')}">-</c:if>
									</font>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty customerList}">
						<tr>
							<td class="tb_nor_c" colspan="8">등록된 고객이 없습니다.</td>
						</tr>
					</c:if>
					<!-- DYNAMIC AREA 'list' -->
	                    </table>
	                    <!-- 메모아이콘 롤오버시 출력 <div class="sub_memo">김치 추가, 고춧가루 추가</div>-->
					</div>
	
					<div style="width:930px; height:24px; margin-top:15px;">
	                	<div style="float:left;width:50px;padding-top:2px;"><a href="javascript:chkDel()"><img src="/resources/images/shopmaster/btn_delete.gif" border="0"/></a></div>
						<div style="float:left;width:830px; text-align:center;">
						${pageNavi.navi}
						</div>
						<div style="float:right;width:50px;padding-top:2px;"><!-- 520 x 330 --><a href="javascript:divOnOff('insertDiv');"><img src="/resources/images/shopmaster/btn_register.gif" border="0"/></a></div>
					</div>
				</form>
<form id="formFile" name="formFile" method="post" action="csManagerMain.do" enctype="multipart/form-data">
<input type="hidden" id="goType" name="goType" value="exlSave"/>


	                <div class="gray40" style="padding:6px 7px 0px 692px;">
 	                   <div style="float:left;" style=""><a href="/resources/down/seample.xls"><img src="/resources/images/shopmaster/btn_excel_sample.gif" border="0"/></a>&nbsp;</div>
	                    <div style="float:left;"><img src="/resources/images/shopmaster/btn3_sidebar.gif" /></div>
<!-- 
	                    <div style="float:left;"><a href="#"><img src="/resources/images/shopmaster/btn3_print.gif" border="0"/></a></div>
	                    <div style="float:left;"><img src="/resources/images/shopmaster/btn3_middlebar.gif" /></div>
 -->

 	                    <div style="float:left;"><a href="javascript:saveExl()"><img src="/resources/images/shopmaster/btn3_excel.gif" border="0"/></a></div>
	                    <div style="float:left;"><img src="/resources/images/shopmaster/btn3_middlebar.gif" /></div>
	                    <div style="float:left;position:relative"><input id="inputFile" name="inputFile" type="file" style="position:absolute; cursor:pointer; left:-2px;width:66px;height:28px; opacity:0.0; filter:alpha(opacity=0);"><img src="/resources/images/shopmaster/btn3_bigregister.gif" border="0"/></div>
	                    <div style="float:left;"><img src="/resources/images/shopmaster/btn3_sidebar.gif" /></div>
	                </div>
</form>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>
	    
	</jsp:body>
</layout:common>