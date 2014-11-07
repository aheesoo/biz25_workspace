<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common> 

	<jsp:attribute name="stylesheet">		
		<style type="text/css">
			.img_format img {width:100%;height:100%;}
		</style>
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
		
		<script langauge="text/javascript">
			$(document).ready(function() {
				//예약구분 : 1:즉시 2:예약
				var hTemp = '${smsManager.reserve_time}'.substring(8, 10);
				var mTemp = '${smsManager.reserve_time}'.substring(10, 12);
				$('#hourColumn').empty();
				switch('${smsManager.reserve_type}'){
					case "1":	$('#send_type_1').prop("checked", true);
									//예약 시간 세팅
									for(var i = 0; i <= 23; i++){
										dStr = (i < 10) ? '0' + i : i;
										$('#hourColumn').append('<option value="' + i + '">' + dStr + '시</option>');
									}
									$('#hourColumn').val(nDate.getHours());	
									$('#minuteColumn').val(nowDate.getMinutes());
									break;
					case "2":	$('#send_type_2').prop("checked", true); 
									$('#hourColumn').empty();
									//예약 시간 세팅
									for(var i = 8; i <= 20; i++){
										dStr = (i < 10) ? '0' + i : i;
										$('#hourColumn').append('<option value="' + i + '">' + dStr + '시</option>');
									}
									
									hTemp = (hTemp < 10) ? hTemp.substring(1, 2) : hTemp;
									$('#hourColumn').val(hTemp);
									
									mTemp = (mTemp < 10) ? mTemp.substring(1, 2) : mTemp;
									$('#minuteColumn').val(mTemp);
									break;
					default :
				}
			
				//발송건수
				$('#req_count').val('${smsManager.req_count}');
				
				//문자 내용
				/*
				$("#send_content").val('${smsManager.send_content}');
				*/
				
				//타입, 소요코인
				switch('${smsManager.msg_type}'){
					case "1": 	$('#sms_type').val('1'); 
									$("#subject_div").hide();
									$("#use_coin_text").text('${smsManager.req_count}'*2 + ' Coin');
									$("#sms_lms_div").show();
									$("#send_subject").val('${smsManager.send_subject}');
									checkByte("send_content", "SMS", 80);
									break;
					case "4": 	if('${smsManager.msg_sub_type}' == '1'){ 
										$('#sms_type').val('2');
										$("#subject_div").show();
										$("#use_coin_text").text('${smsManager.req_count}'*5 + ' Coin');
										$("#sms_lms_div").show();
										$("#send_subject").val('${smsManager.send_subject}');
										checkByte("send_content", "LMS", 2000);
									} else { 
										$('#sms_type').val('3');
										$("#use_coin_text").text('${smsManager.req_count}'*20 + ' Coin');
										$("#mms_div").show();
										$("#send_mms_subject").val('${smsManager.send_subject}');
										ifrm_submit();
									} break;
					default : 
				}
				
				//발송대상 조건 고객타입
				switch('${smsManager.search_customer}'){
					case "1":	$('#customer_all').prop("checked", true);break;
					case "2":	$('#customer_regular').prop("checked", true); break;
					case "3":	$('#customer_new').prop("checked", true); break;
					case "4":	$('#customer_phone_book').prop("checked", true); $("#customerType").hide(); break;
					default : 
				}
				
				//기간 개월
				switch('${smsManager.search_month}'){
					case "1":	$('#radioMonth_1').prop("checked", true); break;
					case "3":	$('#radioMonth_3').prop("checked", true); break;
					case "6":	$('#radioMonth_6').prop("checked", true); break;
					default :
				}
				
				//기간 요일
				var week_arry = '${smsManager.search_week}'.split('^');
				for ( var i in week_arry ) {
					switch(week_arry[i]){
						case "1":	$('#check_day_1').prop("checked", true); break;
						case "2":	$('#check_day_2').prop("checked", true); break;
						case "3":	$('#check_day_3').prop("checked", true); break;
						case "4":	$('#check_day_4').prop("checked", true); break;
						case "5":	$('#check_day_5').prop("checked", true); break;
						case "6":	$('#check_day_6').prop("checked", true); break;
						case "7":	$('#check_day_7').prop("checked", true); break;
					default : 
					}
			    }
				
				//기간 시간
				if('${smsManager.search_time}'.length > 0){
					//하나이상 시간대 정보가 있을때
					$('#check_time_yn').prop("checked", true);
					
					//전체 시간일때
					if('${smsManager.search_time}'.length == 13){
						$('#check_time_all').prop("checked", true);
					}
				}
				var time_arry = '${smsManager.search_time}'.split('^');
				for ( var i in time_arry ) {
					switch(time_arry[i]){
						case "A":	$('#check_time_A').prop("checked", true); break;
						case "B":	$('#check_time_B').prop("checked", true); break;
						case "C":	$('#check_time_C').prop("checked", true); break;
						case "D":	$('#check_time_D').prop("checked", true); break;
						case "E":	$('#check_time_E').prop("checked", true); break;
						case "F":	$('#check_time_F').prop("checked", true); break;
						case "G":	$('#check_time_G').prop("checked", true); break;
						default : 
					}
			    }
				
				//통화횟수
				var call_cnt = '${smsManager.search_call_rcv_cnt}';
				if(call_cnt > 0){
					$('#send_count_yn').prop("checked", true);
				}
				$('#search_call_rcv_cnt').val('${smsManager.search_call_rcv_cnt}');
				$('#search_call_rcv_cnt_type').val('${smsManager.search_call_rcv_cnt_type}');
				
				//발송가능건수
				var req_cnt = '${smsManager.search_req_cnt}'=='' ? '0' : '${smsManager.search_req_cnt}';
				$("#send_count_text").html(" <a href=\"#\" onclick=\"ifrmPopupSmsTelList();\" >발송가능건수 : " + req_cnt + " 건</a>");
				
				//발신번호
				$("#fk_tel").val('${smsManager.fk_tel}');
				
				//추천문구
				recommendMsg();				
				/**********************************/
				
				/* $("#yearColumn").attr("disabled", true);
				$("#monthColumn").attr("disabled", true);
				$("#dayColumn").attr("disabled", true);
				$("#hourColumn").attr("disabled", true);
				$("#minuteColumn").attr("disabled", true);
				$("#customer_all").prop("checked", true);
				$("#allDay").prop("checked", true);
				$('input[name="check_day"]').prop("checked", true);
				$("#check_time_yn").prop("checked", true);
				$("#check_time_all").prop("checked", true);
				$('input[name="check_time"]').prop("checked", true);
				$("#search_call_rcv_cnt").attr("disabled", true);
				$("#search_call_rcv_cnt_type").attr("disabled", true);
				$("#sms_lms_div").show(); */
				
				var nDate = new Date();
				nDate.setTime(nDate.getTime() + 600000);
				var dStr = '';
				
				//발송 > 즉시 클릭시 관련사항 체크or해제
				$("#send_type_1").click(function(){ 
					$("#yearColumn").attr("disabled", true);
					$("#monthColumn").attr("disabled", true);
					$("#dayColumn").attr("disabled", true);
					$("#hourColumn").attr("disabled", true);
					$("#minuteColumn").attr("disabled", true);
					
					$('#hourColumn').empty();
					//예약 시간 세팅
					for(var i = 0; i <= 23; i++){
						dStr = (i < 10) ? '0' + i : i;
						$('#hourColumn').append('<option value="' + i + '">' + dStr + '시</option>');
					}
					$('#hourColumn').val(nDate.getHours());	
					$('#minuteColumn').val(nowDate.getMinutes());
				});
				
				//발송 > 예약 클릭시 관련사항 체크or해제
				$("#send_type_2").click(function(){ 
					$("#yearColumn").attr("disabled", false);
					$("#monthColumn").attr("disabled", false);
					$("#dayColumn").attr("disabled", false);
					$("#hourColumn").attr("disabled", false);
					$("#minuteColumn").attr("disabled", false);
					
					$('#hourColumn').empty();
					//예약 시간 세팅
					for(var i = 8; i <= 20; i++){
						dStr = (i < 10) ? '0' + i : i;
						$('#hourColumn').append('<option value="' + i + '">' + dStr + '시</option>');
					}
					var hTemp = '${smsManager.reserve_time}'.substring(8, 10);
					hTemp = (hTemp < 10) ? hTemp.substring(1, 2) : hTemp;
					$('#hourColumn').val(hTemp);
					var mTemp = '${smsManager.reserve_time}'.substring(10, 12);
					mTemp = (mTemp < 10) ? mTemp.substring(1, 2) : mTemp;
					$('#minuteColumn').val(mTemp);
				});				
				
				//년도가 바뀔때 해당 년월에 맞춰서 몇일까지 있는지 세팅. ex)2월은 28일까지
				$("#yearColumn").change(function(){
					var year = jQuery("select[name=yearColumn]").val();
					var month = jQuery("select[name=monthColumn]").val();
					var last_day = getDate(year + '-' + month + '-01');
					$("#dayColumn").empty().data('options');
					for(var i = 1; i <= last_day; i++){
						dateStr = (i < 10) ? '0' + i : i;
						$('#dayColumn').append('<option value="' + i + '">' + dateStr + '일</option>');
					}
				});
			
				//월이 바뀔때 해당 년월에 맞춰서 몇일까지 있는지 세팅. ex)2월은 28일까지
				$("#monthColumn").change(function(){
					var year = jQuery("select[name=yearColumn]").val();
					var month = jQuery("select[name=monthColumn]").val();
					var last_day = getDate(year + '-' + month + '-01');
					$("#dayColumn").empty().data('options');
					for(var i = 1; i <= last_day; i++){
						dateStr = (i < 10) ? '0' + i : i;
						$('#dayColumn').append('<option value="' + i + '">' + dateStr + '일</option>');
					}
				});
				
				//예약 년도 세팅
				var nowDate = new Date();
				var dateStr = '';
				for(var i = 0; i <= 10; i++){
					dateStr = nowDate.getFullYear() + i;
					$('#yearColumn').append('<option value="' + dateStr + '">' + dateStr + '년</option>');
				}
				var yearTemp = '${smsManager.reserve_time}'.substring(0, 4);
				$('#yearColumn').val(yearTemp);

				//예약 월 세팅
				for(var i = 1; i <= 12; i++){
					dateStr = (i < 10) ? '0' + i : i;
					$('#monthColumn').append('<option value="' + i + '">' + dateStr + '월</option>');
				}
				var monthTemp = '${smsManager.reserve_time}'.substring(4, 6);
				monthTemp = (monthTemp < 10) ? monthTemp.substring(1, 2) : monthTemp;
				$('#monthColumn').val(monthTemp);

				//예약 일 세팅
				var last_day = getDate(nowDate.getFullYear() + '-' + nowDate.getMonth() + '-' + nowDate.getDate());
				for(var i = 1; i <= last_day; i++){
					dateStr = (i < 10) ? '0' + i : i;
					$('#dayColumn').append('<option value="' + i + '">' + dateStr + '일</option>');
				}
				var dayTemp = '${smsManager.reserve_time}'.substring(6, 8);
				dayTemp = (dayTemp < 10) ? dayTemp.substring(1, 2) : dayTemp;
				$('#dayColumn').val(dayTemp);
				
				//예약 시간 세팅
				/* for(var i = 0; i <= 23; i++){
					dateStr = (i < 10) ? '0' + i : i;
					$('#hourColumn').append('<option value="' + i + '">' + dateStr + '시</option>');
				}
				var hourTemp = '${smsManager.reserve_time}'.substring(8, 10);
				hourTemp = (hourTemp < 10) ? hourTemp.substring(1, 2) : hourTemp;
				$('#hourColumn').val(hourTemp); */
				
				//예약 분 세팅
				for(var i = 0; i <= 59; i++){
					dateStr = (i < 10) ? '0' + i : i;
					$('#minuteColumn').append('<option value="' + i + '">' + dateStr + '분</option>');
				}
				var minuteTemp = '${smsManager.reserve_time}'.substring(10, 12);
				minuteTemp = (minuteTemp < 10) ? minuteTemp.substring(1, 2) : minuteTemp;
				$('#minuteColumn').val(minuteTemp);
				
				//타입을 선택했을 때
				$("#sms_type").change(function(){
					document.modify_form.send_content.value = "";
					document.modify_form.send_subject.value = "";
					document.modify_form.send_mms_subject.value = "";
					strCheck();
					recommendMsg();
					var req_temp = $("#req_count").val();
					if($("#sms_type").val() == "1"){
						$("#mms_div").hide();
						$("#subject_div").hide();
						$("#sms_lms_div").show();
						$("#use_coin_text").text(req_temp*2 + ' Coin');
					}else if($("#sms_type").val() == "2"){
						$("#mms_div").hide();
						$("#subject_div").show();
						$("#sms_lms_div").show();
						$("#use_coin_text").text(req_temp*5 + ' Coin');
					}else{
						$("#sms_lms_div").hide();
						$("#mms_div").show();
						mms_ifrm.location.href = "/smsManager/ifrmUploadMMS.do";
						$("#use_coin_text").text(req_temp*20 + ' Coin');
					}
				});
				
				//추천문구 라디오버튼 클릭시 문자 내용 입력.
				$("#sample_contents_1_chk").click(function(){
					$("#sample_contents_2_chk").prop("checked", false);
					$("#sample_contents_3_chk").prop("checked", false);
					choice_content('1');
				});
				
				$("#sample_contents_2_chk").click(function(){
					$("#sample_contents_1_chk").prop("checked", false);
					$("#sample_contents_3_chk").prop("checked", false);
					choice_content('2');
				});
				
				$("#sample_contents_3_chk").click(function(){
					$("#sample_contents_1_chk").prop("checked", false);
					$("#sample_contents_2_chk").prop("checked", false);
					choice_content('3');
				});
				
				//통화횟수 조건 변경시 관련사항 체크or해제
				$("#search_call_rcv_cnt_type").change(function(){
					notChange('search_call_rcv_cnt_type');
				});
			});
			
			//현재시간 함수
			function now_date(){
				var checkDate = new Date();
				//checkDate.setTime(checkDate.getTime() + (minute * 60000));
				var getYear = checkDate.getFullYear();
				var getMonth = (checkDate.getMonth()+1 < 10) ? '0'+(checkDate.getMonth()+1) : checkDate.getMonth()+1;
				var getDay = (checkDate.getDate() < 10) ? '0'+checkDate.getDate() : checkDate.getDate();
				var getHour = (checkDate.getHours() < 10) ? '0'+checkDate.getHours() : checkDate.getHours();
				var getMinute = (checkDate.getMinutes() < 10) ? '0'+checkDate.getMinutes() : checkDate.getMinutes();
				//var getSec = (checkDate.getSeconds() < 10) ? '0'+checkDate.getSeconds() : checkDate.getSeconds();
				var dateTime = getYear + ''  + getMonth + '' + getDay + '' + getHour + '' + getMinute;
				return dateTime;
			}
			
			//설정한 시간 합치는 함수
			function set_date(){
				var getYear = $("#yearColumn").val();
				var getMonth = $("#monthColumn").val() < 10 ? '0'+$("#monthColumn").val() : $("#monthColumn").val();
				var getDay = $("#dayColumn").val() < 10 ? '0'+$("#dayColumn").val() : $("#dayColumn").val();
				var getHour = $("#hourColumn").val() < 10 ? '0'+$("#hourColumn").val() : $("#hourColumn").val();
				var getMinute = $("#minuteColumn").val() < 10 ? '0'+$("#minuteColumn").val() : $("#minuteColumn").val();
				var dateTime = getYear + getMonth + getDay + getHour + getMinute;
				return dateTime;
			}
			
			function getDate(objDate) {
				//(objDate)YYYY-MM-DD 타입으로 입력하세요.
		  		//해당 년월일의 월마지막일,일자,월의 몇번째주,요일 등을 확인해 낸다.
				var arrDate = objDate.split("-");
				//입력일의 요일.( 0:일요일, 1:월요일, 2:화요일, 3:수요일, 4:목요일, 5:금요일, 6:토요일 )
				//thisDate =new Date(arrDate[0],arrDate[1]-1,arrDate[2]);
				//var thisWeek = thisDate.getDay();
				//입력일의  마지막일자
				lastDate =new Date(arrDate[0],arrDate[1],0);
				//last2Date =new Date(arrDate[0],arrDate[1]-1,1);
				var lastDays = lastDate.getDate();
				//var monthSWeek = last2Date.getDay();
				//alert(monthSWeek);
				//var nWeek = parseInt((parseInt(arrDate[2]) + monthSWeek - 2)/7)+1;
				//alertStr
				/* var alertStr = "//입력일자 : " + arrDate[2];
				alertStr+= "//입력요일 : " + thisWeek;
				alertStr+= "//마지막일 : " + lastDays;
				alertStr+= "//몇주차? : " + nWeek;
				alert(alertStr); */
				return lastDays;
			}
			
			function goList() {
				location.href = '/smsManager/smsManagerMain' + '${sPageType}' + '.do?&sCalYear=' + '${sCalYear}' + '&sCalMonth=' + '${sCalMonth}' + '&searchTel=' + '${searchTel}';
			}
			
			function checkModify(){
				goModify();
			}
			
			//수정
			function goModify() {
				//수정중 전송이 된경우 수정안되게
				var str = document.getElementById("send_content").value;
			    var length = str.length;
			    
			  //예약일때만 날짜시간 체크
			    if($("#send_type_2").is(':checked')){
				    var nDate = now_date();
					var cDate = set_date();
					//alert(nDate + '>=' + cDate);
					if(nDate >= cDate){
						//alert('발송 날짜(시간)은 현재 시간에서 5분 이후로 설정이 가능합니다.');
						alertArea(300, 50, 'center', '알 림', '날짜 선택 시, <br><br> 오늘 보다 이전 날짜는 선택할 수 없습니다.');
						return false;
					}
			    }
			  
				//문자 입력이 없을 시 수정안되게
				if(length == 0) {
			    	//alert('문자를 입력을 해주세요.');
					alertArea(300, 35, 'center', '알 림', '문자를 입력을 해주세요.');
					return false;
			    }
			    	
		    	//MMS 문자 입력 내용 바이트 체크
				if($("#sms_type").val() == '3'){
					if(!mms_ifrm.mms_checkByte("mms_send_content", "MMS", 4000)){
						alertArea(300, 35, 'center', '알 림', 'MMS는 4,000byte 이하만 가능합니다.');
						return false;
					}
				}
				//문자 내용 체크
				if(strCheck()){
					if($("#req_count").val() > 0){
						document.modify_form.proc.value = "modify";
		    			$.ajax({   
		    				url : '/smsManager/smsManagerModify.do',
		    				type : 'post',
		    				cache : false,
		    				data : $("#modify_form").serialize(),
		    				//data : form.serialize(),
		    				dataType : 'json',
		    			   	success:function(data){
		    				   //alert(data.customer_count);
		    				   if(data.rtn_code == '200'){
		    					   alert('수정이 완료되었습니다.');
		    					   //alertArea(300, 35, 'center', '알 림', '수정이 완료되었습니다.');
		    					   location.href = '/smsManager/smsManagerMain' + '${sPageType}' + '.do?&sCalYear=' + '${sCalYear}' + '&sCalMonth=' + '${sCalMonth}' + '&searchTel=' + '${searchTel}';
		    				   }else{
		    					   //alert('수정중 오류가 발생하였습니다.' + data.rtn_code);
		    					   if(data.rtn_code == '303'){
		    							alertArea(300, 50, 'center', '알 림', '전송 되었거나 전송 진행중인 문자정보는 <br><br> 수정 할 수 없습니다.');
		    						}else if(data.rtn_code == '305'){
		    						   	alertArea(350, 50, 'center', '알 림', '코인이 부족합니다. 충전하시겠습니까? <br><br> 충전을 원하지 않으실 경우 발송 건수를 수정하시면 됩니다.');
		    					   }else if(data.rtn_code == '307'){
		    						   alertArea(300, 35, 'center', '알 림', data.fd_memo);
		    					   }else if(data.rtn_code == '308'){
		    						   alertArea(300, 35, 'center', '알 림', '3개월 이후 날짜는 발송 예약이 불가능합니다.');
		    					   }/* else if(data.rtn_code == '309'){
		    						   alertArea(300, 50, 'center', '알 림', '문자 예약은 오전 8시 이후부터 <br><br> 오후 21시 이전까지만 가능합니다.');
		    					   } */
		    				   }
		    			   	}
		    			});
					}else{
						//alert('발송건수는 1건 이상이어야 합니다.');
						alertArea(300, 35, 'center', '알 림', '발송건수는 1건 이상이어야 합니다.');
					}
				}else{
					switch($("#sms_type").val()){
						case "1": 	//alert('SMS는 80byte 이하만 가능합니다.'); break;
										alertArea(300, 35, 'center', '알 림', 'SMS는 80byte 이하만 가능합니다.'); break;
						case "2":	//alert('LMS는 2,000byte 이하만 가능합니다.'); break;
										alertArea(300, 35, 'center', '알 림', 'LMS는 2,000byte 이하만 가능합니다.'); break;
						case "3":	return false; break;
						default : return false;
					}
				}
			}
			
			function ifrmPopupSmsTelList() {
				popShow('popSmsTelList');
				//ifrm.location.href = "/smsManager/ifrmSmsTelList.do";
				var form = document.modify_form;
				form.action = "/smsManager/ifrmSmsTelList.do";
    			form.target = "tel_list_ifrm";
    			form.submit();
			}
			
			$(function(){
				$('#tel_list_ifrm').load(function(){
					   $('#tel_list_ifrm').attr("height",tel_list_ifrm.document.body.scrollHeight);
				});
				$('#mms_ifrm').load(function(){
					   $('#mms_ifrm').attr("height",mms_ifrm.document.body.scrollHeight);
				});
			});
			
			//문자 타입 변경시 해당 추천 문구 갖고오기.
			function recommendMsg() {
				document.modify_form.proc.value = "recommendmsg";
    			$.ajax({   
    				url : '/smsManager/smsManagerRegister.do',
    				type : 'post',
    				cache : false,
    				data : $("#modify_form").serialize(),
    				//data : form.serialize(),
    				dataType : 'json',
    			   	success:function(data){
    			   		if(data.rtn_code == '200'){
    			   			var emoticon_list = data.emoticon_list;
    			   			
    			   			if(data.rtn_sms_type == 'MMS'){
    			   				for(var i=0 ; i<emoticon_list.length ; i++){
           			   				$("#recommend_img" + (i + 1)).attr('src', emoticon_list[i].fd_file_path + '/' + emoticon_list[i].fd_file_name);
           			   				//$("#recommend_img" + (i + 1)).val(emoticon_list[i].fd_content);
           			   				$("#sample_mms_contents_" + (i + 1)).val(emoticon_list[i].fd_content);
           			   			}
    			   			}else{
    			   				$("#sample_contents_1").val('');
           			   			$("#sample_contents_2").val('');
           			   			$("#sample_contents_3").val('');
           			   			
           			   			
           			   			for(var i=0 ; i<emoticon_list.length ; i++){
           			   				$("#sample_contents_" + (i + 1)).val(emoticon_list[i].fd_content);
           			   			}
    			   			}
    			   		}
    			   	}
    			});
			}
			
			//MMS 아이프레임에 선택한 이미지 src 보내줌.
			function img_change(num){
				mms_ifrm.img_change(document.getElementById("recommend_img"+num).src, document.getElementById("sample_mms_contents_"+num).value);
				//mms_ifrm.img_change(document.getElementById(id).src, document.getElementById(id).value);
			}
			
			//추천문구 선택을 했을때 문자내용 에 넣기
			function choice_content(index){
				if(confirm("선택하신 추천 문구를 사용 하시겠습니까?")){
					//$(":radio[name='sample_contents_chk']:radio[value='" + index + "']").attr("checked", true);
					switch(index){
						case "1": 	$("#sample_contents_1_chk").prop("checked", true);
										$("#sample_contents_2_chk").prop("checked", false);
										$("#sample_contents_3_chk").prop("checked", false);
										break;
						case "2": 	$("#sample_contents_1_chk").prop("checked", false);
										$("#sample_contents_2_chk").prop("checked", true);
										$("#sample_contents_3_chk").prop("checked", false);
										break;
						case "3": 	$("#sample_contents_1_chk").prop("checked", false);
										$("#sample_contents_2_chk").prop("checked", false);
										$("#sample_contents_3_chk").prop("checked", true);
										break;
					}
					$("#send_content").val($("#sample_contents_" + index).val());
					strCheck();
				}else{
					$("#sample_contents_1_chk").prop("checked", false);
					$("#sample_contents_2_chk").prop("checked", false);
					$("#sample_contents_3_chk").prop("checked", false);
				}
			}
			
			//문자 byte값 구하기. 
			function strCheck(){
				switch($("#sms_type").val()){
					case "1": 	return checkByte("send_content", "SMS", 80); break;
					case "2":	return checkByte("send_content", "LMS", 2000); break;
					case "3":	return true; break;
					default : 	return false;
				}
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
			  		switch($("#sms_type").val()){
						case "1": 	$("#byte_text").text(tcount + "/80 byte"); break;
						case "2":	$("#byte_text").text(tcount + "/2000 byte"); break;
						case "3":	break;
						default : 
					}
			      	return true;
			    }
		  	}
			
			//레이어팝업 숨김
			function popHide(dName) {
				$('#'+dName).hide();
			}
			
			//레이어팝업 돌출
			function popShow(dName) {
				$('#'+dName).show();
			}
			//ID 값으로 검색하여 창 숨김처리
			function openPopDiv(dName){
				if(dName == 'popEmoticon'){

					var cho_sms = $("#sms_type").val();
					
					if(cho_sms=='1'){
						$('#emoticonIfrm').contents().find("[id='search_type']").val('S');						
					}else if(cho_sms=='2'){
						$('#emoticonIfrm').contents().find("[id='search_type']").val('L');
					}if(cho_sms=='3'){
						$('#emoticonIfrm').contents().find("[id='search_type']").val('M');
					}
					
					$('#emoticonIfrm').contents().find("[id='emoticonForm']").submit();
					$('#'+dName).show();
					
				}else{
					$('#'+dName).show();
				}
			}

			// 코인 충전시 LNB 변경 작업
			function childPopCoin(total, recharge, base){
				if(total !=''){
					$('#charge_total_coin').html(total);
				}
				if(recharge !=''){
					$('#charge_recharge_coin').html(recharge);
				}
				if(base !=''){
					$('#charge_base_coin').html(base);
				}
			}
			
			//ID 값으로 검색하여 창 숨김처리
			function closePopDiv(dName){

				if(dName == 'popCoin'){
					
					$('#coinIfrm').contents().find("[id='chargeBasePoint']").val('0');
					$('#coinIfrm').contents().find("[id='chargeBonusPoint']").val('0');
					$('#coinIfrm').contents().find("[id='chargeMoney']").val('0');
					$('#coinIfrm').contents().find("[id='chargeMoney']").val('0');
					$('#coinIfrm').contents().find("[name='pointChoice']").removeAttr("checked");

					$('#coinIfrm').contents().find("[id='chargeTotalPoint']").html('0');
					$('#coinIfrm').contents().find("[id='chargeTotalMoney']").html('0');
					
				}
				
				$('#'+dName).hide();
			}

			// SMS 템플릿
			function setMsg(src, id, smsType){
				if(smsType!='M'){
					var msg = $('#emoticonIfrm').contents().find("[id='"+id+"']").html();
					//alert(msg);
				   $("#send_content").val(msg);
					strCheck();	
				}else{
					//var msg_src = $('#emoticonIfrm').contents().find("[id='"+strMsg+"']").src;
					//var msg_src = emoticonIfrm.document.getElementById('previewImg_1').src;
					var msg = emoticonIfrm.document.getElementById(id).value;
					var msg_src = src;
					mms_ifrm.img_change(msg_src, msg);
					//alert("MMS");
					//mms_ifrm.img_change(document.getElementById("recommend_img"+num).src, document.getElementById("sample_mms_contents_"+num).value);
				}
				closePopDiv('popEmoticon');
			}
			
			function cancelProc(){
				if(now_date() > '${smsManager.reserve_time}'){
					//alert('전송 완료었거나 전송이 진행중인 문자 정보는 삭제 할 수 없습니다.');
					alertArea(350, 50, 'center', '알 림', '전송 완료었거나 전송이 진행중인 문자 정보는<br><br>삭제 할 수 없습니다.');
				}else{
					if(confirm("문자 발송을 삭제 하시겠습니까?")){
						$.ajax({   
		    				url : '/smsManager/smsManagerMainList.do',
		    				type : 'post',
		    				cache : false,
		    				data : "proc=cancel" + "&check=" + '${smsManager.group_code}' + "&event=modify",
		    				dataType : 'json',
		    				success: function (data) {
		    					alert('해당 문자 예약 발송이 삭제 되었습니다.');
		    					//alertArea(300, 35, 'center', '알 림', '해당 문자 예약 발송이 삭제 되었습니다.');
		    					location.href = '/smsManager/smsManagerMain' + '${sPageType}' + '.do?&sCalYear=' + '${sCalYear}' + '&sCalMonth=' + '${sCalMonth}' + '&searchTel=' + '${searchTel}';
					      	},
					      	error: function (data) {
					      		//alert('문자 발송을 취소하는 도중 오류가 발생하였습니다. 다시 시도해주세요.');
					      		alertArea(350, 50, 'center', '오 류', '문자 발송을 삭제하는 도중 오류가 발생하였습니다.<br><br>다시 시도해주세요.');
					      	}
		    			});
					}
				}
			}
			
			function notChange(id){
				//alert('발송대상과 발송건수는 수정이 불가능 합니다.');
				alertArea(300, 35, 'center', '알 림', '발송대상과 발송건수는 수정이 불가능 합니다.');
				
				if(id=='radioMonth_1' | id=='radioMonth_3' | id=='radioMonth_6'){
					switch('${smsManager.search_month}'){
						case "1":	$('#radioMonth_1').prop("checked", true); break;
						case "3":	$('#radioMonth_3').prop("checked", true); break;
						case "6":	$('#radioMonth_6').prop("checked", true); break;
						default :
					}
				}else if(id=='search_call_rcv_cnt_type') {
					$('#search_call_rcv_cnt_type').val('${smsManager.search_call_rcv_cnt_type}');
				}else{
					if($("#" + id).is(':checked')) {
						$("#" + id).prop("checked", false);
					}else{
						$("#" + id).prop("checked", true);
					}
				}
				
			}
			
			function ifrm_submit(){
				var form = document.modify_form;
				form.target = "mms_ifrm";
				form.action = "/smsManager/ifrmUploadMMS.do";
				form.method = "post";
				form.submit();
			}

			/* 코인 충전 팝업 */
			function coinPop(){
				var w = 975;
				var h = 600;
				var x = ( screen.width - w)/2 - 10;
				var y = ( screen.height - h)/2 - 10;
				var url = "/coin/popCharge.do";
				var exp = "width=" + w + ",height=" + h + ",top=" + y + ",left=" + x + ",status=no,resizable=no,toolbar=no,scrollbars=no";
				window.open(url, "coinPop", exp);
			}
			
			//특수문자 체크
			function fn_filterChk(name){
				var chktext = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
				//정규식 구문
				var obj = document.getElementsByName(name)[0];
				if(chktext.test(obj.value)) {
					//alert("특수문자는 입력하실 수 없습니다.");
					alertArea(300, 35, 'center', '알 림', '특수문자는 입력하실 수 없습니다.');
					jQuery("#"+name).val("");
					jQuery("#"+name).focus();
					return false;
				}
				return true;
			}
			
		</script>
	</jsp:attribute>
	
	<jsp:body>
		<!-- 발송건수 알림 레이어 팝업 -->
		<!-- <div id="popSendLimitMsg" style="display:none;position:absolute;top:170px;left:20px;width:95%;height:1px;">
			<div id="popup" style="height:240px;">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:popHide('popSendCountMsg');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">알 림</div>
			    </div>
			    <div class="pop_txt">
			        <table width="470" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td colspan="2" height="2" bgcolor="#222222"></td>
				          </tr>
				          <tr>
				            <td width="320" class="pop_tb_nor_l"><div style="float:left; margin-left:140px;"><b>일 500건 이상</b>의 문자 발송시,</div></td>
				          </tr>
				          <tr>
				            <td width="320" class="pop_tb_nor_l"><div style="float:left; margin-left:70px;">통신망 법에 의거하여 별도에 신청서를 작성하여야 합니다.</div></td>
				          </tr>
				          <tr>
				            <td width="320" class="pop_tb_nor_l"><div style="float:left; margin-left:110px;">고객센터(1566-1111)로 문의 부탁드립니다.</div></td>
				          </tr>
			        </table>
			    </div>
			    <div class="pop_btn"><a href="#" onclick="javascript:popHide('popSendLimitMsg');"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a></div>
			</div>
		</div> -->
		
		<!-- 코인부족 알림 레이어 팝업 -->
		<!-- <div id="popCoinSortageMsg" style="display:none;position:absolute;top:170px;left:20px;width:95%;height:1px;">
			<div id="popup" style="height:200px;">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:popHide('popSendCountMsg');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">알 림</div>
			    </div>
			    <div class="pop_txt">
			        <table width="470" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td colspan="2" height="2" bgcolor="#222222"></td>
				          </tr>
				          <tr>
				            <td width="320" class="pop_tb_nor_l"><div style="float:left; margin-left:140px;">코인이 부족합니다. 충전하시겠습니까?</div></td>
				          </tr>
				          <tr>
				            <td width="320" class="pop_tb_nor_l"><div style="float:left; margin-left:70px;">충전을 원하지 않으실 경우 발송 건수를 수정하시면 됩니다.</div></td>
				          </tr>
			        </table>
			    </div>
			    <div class="pop_btn"><a href="#" onclick="javascript:popHide('popCoinSortageMsg');"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a></div>
			</div>
		</div> -->
		
		<!-- 발송가능건수보다 설정건수가 작을경우 알림 레이어 팝업 -->
		<div id="popSendCountChkMsg" style="display:none;position:absolute;top:170px;left:20px;width:95%;height:1px;">
			<div id="popup" style="height:200px;">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:popHide('popSendCountMsg');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">알 림</div>
			    </div>
			    <div class="pop_txt">
			        <table width="470" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td colspan="2" height="2" bgcolor="#222222"></td>
				          </tr>
				          <tr>
				            <td width="320" class="pop_tb_nor_l"><div style="float:left; margin-left:65px;">발송 가능 건수는 
				            	<font id="text_send_count" style="font-weight:bold;color:#222222;"></font>
				            	이나 설정하신 
				            	<font id="text_req_count" style="font-weight:bold;color:#222222;"></font>
				            	만 발송하시겠습니까?</div></td>
				          </tr>
				          <tr>
				            <td width="320" class="pop_tb_nor_l"><div style="float:left; margin-left:70px;">발송 대상은 마스터샵 고객 컨설팅 시스템을 통해 선정됩니다.</div></td>
				          </tr>
			        </table>
			    </div>
			    <div class="pop_btn"><a href="#" onclick="javascript:popHide('popSendCountChkMsg');javascript:goModify();"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>&nbsp;<a href="javascript:popHide('popSendCountChkMsg');"><img src="/resources/images/shopmaster/btn_cancel.gif" border="0"/></a></div>
			</div>
		</div>
		
		<!-- 발송 가능 건수 연락처 리스트 팝업 -->
		<div id="popSmsTelList" style="display:none;position:absolute;top:170px;left:20px;width:95%;height:1px;">
			<div id="popup">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:popHide('popSmsTelList');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
					<div class="pop_tit">대상 전화번호 리스트</div>
				</div>
				<div class="pop_txt">
					<iframe name="tel_list_ifrm" id="tel_list_ifrm" width="100%" height="100%" frameborder="0" src=""></iframe>
				</div>
			</div>
		</div>
		
		
		<div id="main_r">
			<div class="contents_area">
	        	<div class="sub_tit">
	            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_message_admin.gif" border="0"/></div>
  	            	<div class="sub_now">홈 &gt; 문자관리 &gt; <font class="sub_now_t">문자발송 수정 </font></div>
	            </div>
	            <div class="sub_tit_dot"></div>
	            <div class="sub_contents">
                	<div class="tb_top"></div>
                	<form id="modify_form" name="modify_form">
                		<input type="hidden" id="proc" name="proc" value="" />
						<input type="hidden" id="search_req_cnt" name="search_req_cnt" value="${smsManager.search_req_cnt}" />
						<input type="hidden" id="attachment_path" name="attachment_path" value="${smsManager.attachment_path}" />
						<input type="hidden" id="attachment_file_size" name="attachment_file_size" value="${smsManager.attachment_file_size}" />
						<input type="hidden" id="reg_date" name="reg_date" value="${smsManager.reg_date}" />
						<input type="hidden" id="group_code" name="group_code" value="${smsManager.group_code}" />
						<input type="hidden" id="org_reserve_time" name="org_reserve_time" value="${smsManager.reserve_time}" />
						<%-- <input type="hidden" id="send_content" name="send_content" value="${smsManager.send_content}" /> --%>
	                	<table width="930" border="0" cellspacing="0" cellpadding="0">
							
							<tr height="0">
								<td width="120"></td>
								<td width="170"></td>
								<td width="120"></td>				
								<td width="170"></td>
								<td width="120"></td>
								<td width="170"></td>
							</tr>	
		
							<tr>
								<td class="tb2_tit_c">등록일</td>
								<td class="tb2_nor_l">
									<fmt:parseDate value="${smsManager.reg_date}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
									<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd HH:mm:ss"/>
								</td>
								<td class="tb2_tit_c">발송</td>
								<td class="tb2_nor_l" colspan="3">
									<div style="float:left;"><input id="send_type_1" name="send_type" type="radio" value="1" checked="checked" /></div>
	                            	<div style="float:left; margin:1px 0px 0px 2px;">즉시</div>
	                            	<div style="float:left;"><input id="send_type_2" name="send_type" type="radio" value="2" /></div>
	                            	<div style="float:left; margin:1px 0px 0px 2px;">예약</div>
								</td>
							</tr>	
						
							<tr>
								<td class="tb2_tit_c">발송날짜</td>
								<td class="tb2_nor_l" colspan="5">
									<div style="float:left;"><select id="yearColumn" name="yearColumn" class="listmenu_st" style="width:70px;"></select></div>
									<div style="float:left; margin-left:5px;"><select id="monthColumn" name="monthColumn" class="listmenu_st" style="width:65px;"></select></div>
		                        	<div style="float:left; margin-left:5px;"><select id="dayColumn" name="dayColumn" class="listmenu_st" style="width:65px;"></select></div>
		                        	<div style="float:left; margin-left:5px;"><select id="hourColumn" name="hourColumn" class="listmenu_st" style="width:65px;"></select></div>
		                        	<div style="float:left; margin-left:5px;"><select id="minuteColumn" name="minuteColumn" class="listmenu_st" style="width:65px;"></select></div>
								</td>
							</tr>
							
							<tr>
								<td class="tb2_tit_c">발송건수</td>
								<td class="tb2_nor_l">
	                            	<div style="float:left; margin-left:5px;"><input id="req_count" name="req_count" value="0" type="text" class="fild_st" style="width:50px;" readonly="readonly" ></div>
	                            	<div style="float:left; margin:1px 0px 0px 2px;">건</div>
	                            	<div style="float:left; margin-left:15px;"><input id="req_count_not_limit" name="req_count" type="checkbox" value="1"  onclick="notChange('req_count_not_limit');" /></div>
	                            	<div style="float:left; margin:1px 0px 0px 2px;">제한 없음</div>
								</td>
								<td class="tb2_tit_c">타입</td>
								<td class="tb2_nor_l">
									<div style="float:left;"><select id="sms_type" name="sms_type" class="listmenu_st" style="width:65px;">
		                                <option value="1">SMS</option>
										<option value="2" selected="selected">LMS</option>
										<option value="3">MMS</option>
	                                </select></div>
								</td>
								<td class="tb2_tit_c">소요 코인</td>
								<td class="tb2_nor_l">
									<font id="use_coin_text" style="font-weight:bold;color:#222222;">0 Coin</font>
									 <a href="javascript:coinPop();" ><img src="/resources/images/shopmaster/btn_coinsave.gif" onclick="javascript:openPopDiv('popCoin')" border="0" align="absmiddle" /></a> 
									 <!-- <a href="#"><img src="/resources/images/shopmaster/btn_coinsave.gif" onclick="alert('개발중입니다.');" border="0" align="absmiddle" /></a>  -->
									<!-- 코인 PG 호출전 레이어 -->										
									<!-- <div id="popCoinInfo" style="display:none;position:absolute;top:170px;left:20px;width:98%;height:0px;">
										<iframe id="coinInfoIfrm" name="coinInfoIfrm" src="/coin/coin_chargeInfo.do" frameborder="0" style="display:block;position:absolute;top:0px;left:300px;width:520px;height:390px;"></iframe>
									</div> -->
									<!-- 코인 레이어 -->
									<!-- 
									<div id="popCoin" style="display:none;position:absolute;top:170px;left:20px;width:98%;height:0px;">
										<iframe id="coinIfrm" name="coinIfrm" src="/coin/charge.do" frameborder="0" style="display:block;position:absolute;top:0px;left:300px;width:995px;height:265px;"></iframe>
									</div>
									 -->
								</td>
							</tr>
							<tr>
								<td class="tb2_tit_c" rowspan="2" >발송대상</td>
								<td class="tb2_nor_l" colspan="5">
									<div style="width:600px; height:24px;">
										<div style="float:left; width:80px;"><font style="font-weight:bold;color:#222222;">조건</font></div>
										<div style="float:left;"><input type="checkbox" id="customer_all" name="customer_type" value="1"  onclick="notChange('customer_all');" /></div>
			                            <div style="float:left; margin:1px 0px 0px 2px;">전체 고객</div>
			                            <div style="float:left; margin-left:15px;"><input type="checkbox" id="customer_regular" name="customer_type" value="2"  onclick="notChange('customer_regular');" /></div>
			                            <div style="float:left; margin:1px 0px 0px 2px;">단골 고객</div>
			                            <div style="float:left; margin-left:15px;"><input type="checkbox" id="customer_new" name="customer_type" value="3"  onclick="notChange('customer_new');" /></div>
			                            <div style="float:left; margin:1px 0px 0px 2px;">신규 고객</div>
			                            <div style="float:left; margin-left:15px;"><input type="checkbox" id="customer_phone_book" name="customer_type" value="4" onclick="notChange('customer_phone_book');" /></div>
			                            <div style="float:left; margin:1px 0px 0px 2px;">주소록 전체 고객</div>
			                            <div style="float:left; margin:1px 0px 0px 30px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tb2_nor_l" colspan="5">
									<div id="customerType" style="display:block;">
										<div style="width:600px; height:24px;">
											<div style="float:left; width:80px;"><font style="font-weight:bold;color:#222222;">기간</font></div>
											<div style="float:left;"><input type="radio" id="radioMonth_1" name="radioMonth" value="1" onclick="notChange('radioMonth_1');"  /></div>
				                            <div style="float:left; margin:1px 0px 0px 2px;">1개월</div>
				                            <div style="float:left; margin-left:15px;"><input type="radio" id="radioMonth_3" name="radioMonth" value="3" onclick="notChange('radioMonth_3');"  /></div>
				                            <div style="float:left; margin:1px 0px 0px 2px;">3개월</div>
				                            <div style="float:left; margin-left:15px;"><input type="radio" id="radioMonth_6" name="radioMonth" value="6" onclick="notChange('radioMonth_6');" /></div>
				                            <div style="float:left; margin:1px 0px 0px 2px;">6개월</div>
										</div>
										<div style="width:680px; margin-left:75px;">
											<div class="message_oc_c">
												<div style="float:left; width:80px; text-align:right;"><font style="font-weight:bold;color:#222222;">요일</font></div>
												<div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="allDay" name="allDay" onclick="notChange('allDay');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">전체</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="weekDay" name="weekDay" onclick="notChange('weekDay');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">주중</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="holiDay" name="holiDay" onclick="notChange('holiDay');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">주말</div>
											</div>
											<div class="message_oc_c">
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 95px;"><input type="checkbox" id="check_day_1" name="check_day" value="1" onclick="notChange('check_day_1');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">일요일</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_day_2" name="check_day" value="2" onclick="notChange('check_day_2');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">월요일</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_day_3" name="check_day" value="3" onclick="notChange('check_day_3');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">화요일</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_day_4" name="check_day" value="4" onclick="notChange('check_day_4');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">수요일</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_day_5" name="check_day" value="5" onclick="notChange('check_day_5');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">목요일</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_day_6" name="check_day" value="6" onclick="notChange('check_day_6');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">금요일</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_day_7" name="check_day" value="7" onclick="notChange('check_day_7');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">토요일</div>
			                                </div>
			                                <div class="message_oc_c">
			                                	<div style="float:left; width:20px;"><input type="checkbox" id="check_time_yn" name="check_time_yn" value="1" onclick="notChange('check_time_yn');" /></div>
			                                    <div style="float:left; width:60px; text-align:right;"><font style="font-weight:bold;color:#222222;">시간설정</font></div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_all" name="check_time_all" value="all" onclick="notChange('check_time_all');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">전체</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_A" name="check_time" value="A" onclick="notChange('check_time_A');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">새벽(00~07)</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_B" name="check_time" value="B" onclick="notChange('check_time_B');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">아침(07~09)</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_C" name="check_time" value="C" onclick="notChange('check_time_C');"  /></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">오전(09~11)</div>
			                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_D" name="check_time" value="D" onclick="notChange('check_time_D');"  /></div>
		                                    	<div style="float:left; margin:1px 0px 0px 2px;">점심(11~14)</div>
		                                   	</div>
		                                   	<div class="message_oc_c">
		                                   		<div style="float:left; width:500px; margin-left:140px;">
				                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_E" name="check_time" value="E" onclick="notChange('check_time_E');"  /></div>
				                                    <div style="float:left; margin:1px 0px 0px 2px;">오후(14~17)</div>
				                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_F" name="check_time" value="F" onclick="notChange('check_time_F');"  /></div>
				                                    <div style="float:left; margin:1px 0px 0px 2px;">저녁(17~21)</div>
				                                    <div style="float:left; width:20px; margin:0px 0px 0px 15px;"><input type="checkbox" id="check_time_G" name="check_time" value="G" onclick="notChange('check_time_G');"  /></div>
				                                    <div style="float:left; margin:1px 0px 0px 2px;">야간(21~24)</div>
			                                    </div>
			                                </div>
			                                <div class="message_oc_c">
			                                	<div style="float:left; width:20px;"><input type="checkbox" id="send_count_yn" name="send_count_yn" value="1" onclick="notChange('send_count_yn');" /></div>
			                                    <div style="float:left; width:60px; text-align:right;"><font style="font-weight:bold;color:#222222;">통화횟수</font></div>
			                                    <div style="float:left; margin:0px 0px 0px 15px;"><input id="search_call_rcv_cnt" name="search_call_rcv_cnt" type="text" class="fild_st" readonly="readonly"  style="width:80px;"></div>
			                                    <div style="float:left; margin:1px 0px 0px 2px;">건</div>
			                                    <div style="float:left; margin:0px 0px 0px 15px;">
			                                    	<select id="search_call_rcv_cnt_type" name="search_call_rcv_cnt_type" class="listmenu_st" style="width:70px;">
			                                        	<option value="-1">이하</option>
														<option value="1">이상</option>
			                                        </select>
			                                    </div>
			                                </div>
			                                <!-- <div class="message_oc_c">
			                                	<div style="float:left; width:570px;"><font id="send_count_text" style="font-weight:bold;color:#222222;"><a href="#" onclick="ifrmPopupSmsTelList();" >발송가능건수 : 0 건</a></font></div>
			                                </div> -->
			                           	</div>
			                     	</div>
			                     	<div style="width:680px; margin-left:75px;">
		                           		<div class="message_oc_c">
		                                	<div style="float:left; width:570px;"><font id="send_count_text" style="font-weight:bold;color:#222222;"><a href="#">발송가능건수 : 0 건</a></font></div>
		                                </div>
		                           	</div>
								</td>
							</tr>
							<tr>
								<td class="tb2_tit_c">발신번호</td>
								<td class="tb2_nor_l" colspan="5">
									<select id=fk_tel name="fk_tel" style="width:150px">
										<c:forEach var="contents" items="${product_tel_list}" varStatus="status" >
											<option value="${contents.fk_tel}">${contents.fk_tel}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
						<br>
						<div class="tb_top" style="margin-top:35px;"></div>
						<div id="sms_lms_div" style="display:none;">
							<div id="subject_div">
								<table width="930" border="0" cellspacing="0" cellpadding="0">
									<tr>
							      		<td width="120" class="tb2_tit_c">제목</td>
							      		<td width="360" class="tb2_nor_l"><input id="send_subject" name="send_subject" type="text" onkeyup="fn_filterChk('send_subject')" maxlength="30" class="fild_st" style="width:100%;"></td>
							      		<td colspan="2" class="tb2_nor_l">※ 제목은 30자까지 입력이 가능하며, 특수문자는 사용하실 수 없습니다.</td>
							      </tr>
								</table>
							</div>
						    <table width="930" border="0" cellspacing="0" cellpadding="0">
						      <tr>
						        <td width="120" rowspan="2" class="tb2_tit_c">내용</td>
						        <td width="200" rowspan="2" class="tb2_nor_l" style="border-right:1px solid #ddd;">
						            <div class="message_tbg" style="margin-left:10px;">
						                <div class="message_top"></div>
						            	<div class="message_t_lms">
						            		<textarea class="message_f2" name="send_content" id="send_content" onkeyup="strCheck();" rows="" >${smsManager.send_content}</textarea>
						              	</div>
						                <div class="message_bottom"></div>
						            </div>
						            <div style="width: 100px; text-align: right; margin-top: 5px; margin-left: 71px;"><font id="byte_text" style="font-weight:bold;color:#222222;">0/2000 byte</font></div>
						        </td>
						        <td height="15" colspan="3" class="tb2_nor_l">
						            <div style="float:left;"><font style="font-weight:bold;color:#222222;">추천문구</font></div>
						            <div style="float:right;"><!-- 620 x 730 --><a href="javascript:openPopDiv('popEmoticon')">더보기 &gt;</a></div>
						        </td>
						      </tr>
						      <tr>
						        <td width="180" class="tb2_nor_l">                          
						            <div class="message_tbg" style="margin-left:10px;">
						                <div class="message_top"></div>
						              	<div class="message_t_lms">
						              		<textarea class="message_f2" name="sample_contents_1" id="sample_contents_1" onclick="choice_content('1');" rows="" readonly="readonly"></textarea>
						              	</div>
						                <div class="message_bottom"></div>
						            </div>
						            <div style="width:161px; text-align:center;margin-left:10px;"><input type="checkbox" id="sample_contents_1_chk" name="sample_contents_chk" value="1"></div>
						        </td>
						        <td width="180" class="tb2_nor_l">
						            <div class="message_tbg" style="margin-left:10px;">
						                <div class="message_top"></div>
						              	<div class="message_t_lms">
						              		<textarea class="message_f2" name="sample_contents_2" id="sample_contents_2" onclick="choice_content('2');" rows="" readonly="readonly"></textarea>
						              	</div>
						                <div class="message_bottom"></div>
						            </div>
						            <div style="width:161px; text-align:center;margin-left:10px;"><input type="checkbox" id="sample_contents_2_chk" name="sample_contents_chk" value="2"></div>
						        </td>
						        <td class="tb2_nor_l">
						            <div class="message_tbg" style="margin-left:10px;">
						                <div class="message_top"></div>
						              	<div class="message_t_lms">
						              		<textarea class="message_f2" name="sample_contents_3" id="sample_contents_3" onclick="choice_content('3');" rows="" readonly="readonly"></textarea>
						              	</div>
						                <div class="message_bottom"></div>
						            </div>
						            <div style="width:161px; text-align:center;margin-left:10px;"><input type="checkbox" id="sample_contents_3_chk" name="sample_contents_chk" value="3"></div>
						        </td>
						      </tr>
						    </table>      
						</div>
						<!-- 이모티콘 레이어 -->										
						<div id="popEmoticon" style="display:none;position:absolute;top:170px;left:20px;width:98%;height:0px;">
							<iframe id="emoticonIfrm" name="emoticonIfrm" src="/emoticon/list.do" frameborder="0" style="overflow-x:hidden; overflow-y:hidden; display:block;position:absolute;top:0px;left:300px;width:620px;height:735px;"></iframe>
						</div>
						
						<div id="mms_div" style="display:none;">
							<table width="930" border="0" cellspacing="0" cellpadding="0">
									<tr>
							      		<td width="120" class="tb2_tit_c">제목</td>
							      		<td width="360" class="tb2_nor_l"><input id="send_mms_subject" name="send_mms_subject" type="text" onkeyup="fn_filterChk('send_mms_subject')" maxlength="30" class="fild_st" style="width:100%;"></td>
							      		<td colspan="2" class="tb2_nor_l">※ 제목은 30자까지 입력이 가능하며, 특수문자는 사용하실 수 없습니다.</td>
							      </tr>
							</table>
						    <table width="930" border="0" cellspacing="0" cellpadding="0">
						      <tr>
						        <td width="120" class="tb2_tit_c">내용</td>
						        <td width="320" class="tb2_nor_l" style="border-right:1px solid #ddd;"> 
						        	<div style="width: 330px; height: 370px; overflow-x:hidden; overflow-y:hidden;">
						            	<iframe name="mms_ifrm" id="mms_ifrm" width="100%" height="100%" frameborder="0" scrolling="no"  maginwidth="0" marginheight="0" frameborder="0" src=""></iframe>
						            </div>  
						        </td> 
						        <td valign="top" class="tb2_nor_l">
						        	<div style="margin:10px;"><div style="width:370px; float:left;"><font style="font-weight:bold;color:#222222;">추천이미지</font></div><div><!-- 620 x 730 --><a href="javascript:openPopDiv('popEmoticon')">더보기 &gt;</a></div></div>
						        	
						            <div style="margin-left:5px;">
						            	<a href="javascript:img_change('1');"><img id="recommend_img1" src="" width="132" height="202" /></a>&nbsp;&nbsp;
						            	<a href="javascript:img_change('2');"><img id="recommend_img2" src="" width="132" height="202" /></a>&nbsp;&nbsp;
						            	<a href="javascript:img_change('3');"><img id="recommend_img3" src="" width="132" height="202" /></a>
						            </div>
						            
						            <div style="margin-left:5px;">
						            	<textarea class="message_f2" name="sample_mms_contents_1" id="sample_mms_contents_1" rows="" width="132" readonly="readonly"></textarea>&nbsp;&nbsp;
						            	<textarea class="message_f2" name="sample_mms_contents_2" id="sample_mms_contents_2" rows="" width="132" readonly="readonly"></textarea>&nbsp;&nbsp;
						            	<textarea class="message_f2" name="sample_mms_contents_3" id="sample_mms_contents_3" rows="" width="132" readonly="readonly"></textarea>
						            </div>
						        </td>
						      </tr>
						    </table>      
						</div>
						<div style="width:930px; margin-top:10px;"><font class="ca_txt11_999999">※ 특정일 (명절, 연말연시 등)에 발송이 지연될 수 있습니다.</font></div>
		            	<div style="width:930px; height:30px; margin-top:25px;">
		               		<div style="float:left;"><a href="javascript:goList();"><img src="/resources/images/shopmaster/btn_list.gif" border="0"/></a></div>
		               		<c:if test="${modifyYN == 'Y'}">
			                	<div style="float:left; margin-left:5px;"><a href="javascript:cancelProc();"><img src="/resources/images/shopmaster/btn_del_sms.gif" border="0"/></a></div>
			                	<div style="float:right; margin-left:5px;"><a href="javascript:goList();"><img src="/resources/images/shopmaster/btn_cancel.gif" border="0"/></a></div>
			                    <div style="float:right;"><a href="javascript:checkModify();"><img src="/resources/images/shopmaster/btn_edit.gif" border="0"/></a></div>
			              	</c:if>
		              	</div>
					</form>
					<br><br>
	            </div>
	        </div>
	        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	    </div>
	    
	</jsp:body>
</layout:common>