<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
	<script src="/resources/js/api_js.js"></script>

	<!-- OPEN API 로그인 -->
	<SCRIPT type="text/javascript" language="JavaScript" src="api_js.js"></script>

	<SCRIPT FOR="KTOpenAPIX" EVENT="EventLogin(nResult)" LANGUAGE="Jscript">
	/**
		로그인 관련 이벤트
	**/ 
		if(nResult==200){
	/**
		로그인 후 작업
	 **/
 		}else if(nResult==300 || nResult==400 || nResult==401){
	/**
		로그아웃 관련 이벤트
		300 : 정상로그아웃
		400 : 강제로그아웃(외부에서 같은아이디로 로그인)
		401 : CP강제 로그아웃(인증키 차단)
	**/
//			alert( "[Event] LogOut");
			location.reload();
		}else{
	/**
		로그인 에러
	 **/
		}
		closeApiDiv('openApiDiv');
	</SCRIPT>


<SCRIPT FOR="KTOpenAPIX" EVENT="EventSyncAddressData( nMode, sAdType, sAdParentSeqno, sAdSeqno, sAdName, sAdMNumber, sAdONumber, sAdHNumber, sAdFNumber, sAdCompany, sAdTeam, sAdTitle, sAdBusiness, sAdZipCode, sAdAddress, sAdEmail, sAdMemo, sAdBirthDay, sAdBirthType, sNgName, sAdMetChance, sAdKeyman, sAdFavorite)" LANGUAGE="Jscript">
/**
 주소록 사용자 통지 이벤트
**/
  var EventSyncAddressData = "";
  EventSyncAddressData = 
    "모드:"     + nMode   +" "+
    
    ", 주소:"    + sAdAddress  +" "+
    ", 생년월일:"   + sAdBirthDay  +" "+
    ", 음력0,양력1:"  + sAdBirthType +" "+
    ", 담당업무:"   + sAdBusiness  +" "+
    ", 회사이름:"   + sAdCompany  +" "+
    ", 이메일주소:"   + sAdEmail  +" "+
    ", 관심사:"    + sAdFavorite  +"\n"+
    ", 팩스번호:"   + sAdFNumber  +" "+
    ", 집전화번호:"   + sAdHNumber  +" "+
    ", 추천인:"    + sAdKeyman  +" "+
    ", 인물메모:"   + sAdMemo   +" "+
    ", 만난상황:"   + sAdMetChance +" "+
    ", 핸드폰번호:"   + sAdMNumber  +" "+
    ", 이름:"    + sAdName   +" "+
    ", 회사전화번호:"  + sAdONumber  +"\n"+
    ", 상위그룹일련번호:" + sAdParentSeqno +" "+
    ", 주소록일련번호:"  + sAdSeqno  +" "+
    ", 부서:"    + sAdTeam   +" "+
    ", 직책:"    + sAdTitle  +" "+
    ", 그룹타입:"   + sAdType   +" "+
    ", 우편번호:"   + sAdZipCode  +" "+
    ", 인맥:"    + sNgName   +"\n\n";
//document.getElementById("EventSyncAddressData").value =EventSyncAddressData;
</SCRIPT>


<SCRIPT FOR="KTOpenAPIX" EVENT="EventCID( sCaller, sCallee, sResult, sClSeqno, sMiSeqno)" LANGUAGE="Jscript">
	 
	/*
		발신정보 수신 통지 이벤트
	*/
	 switch (sResult)
	 {
	 case "200": sResultTxt	= "호처리성공"; break;
	 case "201": sResultTxt	= "호처리중"; break;
	 case "202": sResultTxt	= "부재중"; break;
	 case "203": sResultTxt	= "부재중"; break;
	 case "401": sResultTxt	= "결번"; break;
	 case "404": sResultTxt	= "통화중"; break;
	 case "405": sResultTxt	= "무응답"; break;
	 case "407": sResultTxt	= "발신자 호포기"; break;
	 case "408": sResultTxt	= "착신자 호포기"; break;
	 case "701": sResultTxt	= "SHOW CID 무선 회선 호 연결중"; break;
	 case "700": sResultTxt	= "SHOW CID 무선 회선 호 연결됨"; break;
	 case "904": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
	 case "905": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
	 case "906": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
	 case "907": sResultTxt	= "SHOW CID 무선 회선 부재중"; break;
	 default: sResultTxt	= "Error";
	 }
/* 
	alert(	"발신:"						+	sCaller		+" "+
			", 수신:"				+	sCallee		+" "+
			", 결과(코드):"			+	sResultTxt	+"("+sResult+")"+
			", 통화내역 일련번호:"		+	sClSeqno	+" "+
			", 발신사용자 일련번호  :"	+	sMiSeqno);
 */

 var logOk = "false";
// DB 에 고객정보 로그 저장
	$.ajax({
		// type을 설정합니다.
		type:'POST',
		url:"/common/ajaxBlank.do",
		// 사용자가 입력하여 id로 넘어온 값을 서버로 보냅니다.
		data:{
			"type"			 : "member_log_insert",
			"fd_tel"		 : sCallee,
			"fd_rcv_tel"	 : sCaller,
			"sResult"		 : sResult
		},
		// 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
		success:function(data){
			if(data){
				logOk = data;
			}
		}
	});
//DB 에 고객정보 로그 저장

 
	if(sResult == "201"){	// 전화가 올때
		// 고객정보 등록창 초기화  (고객정보 열린 상태에서 다시 전화가 오면...)
		$("#memberName").val("");
		$("#memberTel1").val("");
		$("#memberTel2").val("");
		$("#memberTel3").val("");
		$("#memberAddr").val("");
		$("#memberMemo").val("");
		$('input:radio[name="memberViewChk"]:input[value="Y"]').attr("checked", true);
		$('input:radio[name="memberViewChk"]:input[value="N"]').attr("checked", false);
		// 고객정보 등록창 초기화 

		//전화번호 자르기&넣기
		telSplit(sCaller);

		var memberViewChk	 = "Y";							// DB에서 가져온 보기옵션 값
		var memberName		 = "";							// 고객이름
		var memberTel1		 = $("#memberTel1").val();		// 고객전화번호1
		var memberTel2		 = $("#memberTel2").val();		// 고객전화번호2
		var memberTel3		 = $("#memberTel3").val();		// 고객전화번호3
		var memberAddr		 = "";							// 고객주소
		var memberMemo		 = "";							// 고객메모

		$('#sCallee').val(sCallee);			// 수신
		$('#sResult').val(sResultTxt);		// 코드
		$('#sResultTxt').val(sResultTxt);	// 결과
		$('#sClSeqno').val(sClSeqno);		// 통화내역 일련번호
		$('#sMiSeqno').val(sMiSeqno);		// 발신사용자 일련번호

		// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리
		$.ajax({
			// type을 설정합니다.
			type:'POST',
			url:"/common/ajaxBlank.do",
			// 사용자가 입력하여 id로 넘어온 값을 서버로 보냅니다.
			dataType : "json",
			data:{
				"type"			 : "member_select"
				, "memberTel1"	 : memberTel1
				, "memberTel2"	 : memberTel2
				, "memberTel3"	 : memberTel3
			},
			// 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
			success:function(data){
				if(data){
					$("#memberName").val(data.fd_user_name);
					$("#memberAddr").val(data.fd_addr);
					$("#memberMemo").val(data.fd_memo);
					if(data.fd_view_flag == "Y"){
						$('input:radio[name="memberViewChk"]:input[value="Y"]').attr("checked", true);
						$('input:radio[name="memberViewChk"]:input[value="N"]').attr("checked", false);
					}else{
						$('input:radio[name="memberViewChk"]:input[value="Y"]').attr("checked", false);
						$('input:radio[name="memberViewChk"]:input[value="N"]').attr("checked", true);
					}
				}
			},
			error:function(data){
		});
		// DB 에서 고객이름, 고객주소, 고객메모, 보기옵션 가져오는 처리

		openApiMemberAddOn();									//고객 정보창 오픈
	}

 </SCRIPT>

	<SCRIPT FOR="KTOpenAPIX" EVENT="EventCTC ( sCaller, sCallee, sResult, sMiSeqno)" LANGUAGE="Jscript">
	/**
		CTC이벤트 - 전화걸기 요청 함수
	**/
		var CTC ="";
		CTC   =	"발신:"		+	sCaller		+" "+
					", 수신:"	+	sCallee		+" "+
					", 결과:"	+	sResult		+" "+
					", ID  :"	+	sMiSeqno	+ "\n";
		document.getElementById("CTC").value =CTC;
	</SCRIPT>

	<SCRIPT FOR="KTOpenAPIX" EVENT="EventCON( sCaller, sCallee, sCallBackID, sResult)" LANGUAGE="Jscript">
	/**
		CON이벤트 - 전화를 받거나 끊을때(다자간 통화) 
	**/
	
		var CON ="";
		if(sResult=="200"){sResult="성공"}
		
		CON   =	"발신:"			+	sCaller		+" "+
					", 수신:"		+	sCallee		+" "+
					", 결과:"		+	sResult		+" "+
					", 방번호  :"	+	sCallBackID +"\n";
	</SCRIPT>

	<script type="text/javascript">
		${script}
		
		$(document).ready(function(){
		});

		//api 로그인창 오픈
		function openApiLoginOn(){
			//로그인창 오픈
			$('#openApiDiv').show();
		}
		//api 로그인창 로그인처리
		function openApiLogin(){
			//로그인 처리
			$('#loginID').val($('#apiId').val());				// api아이디
			$('#loginPWD').val($('#apiPwd').val());				// api패스워드

			Login();
		}
		
		//api 고객정보 등록창 오픈 / 등록
		function openApiMemberAddOn(){
			//고객정보창 오픈
			$('#openApiMemberDiv').show();
		}

		//api 고객정보 등록창 등록
		function openApiMemberAdd(){
			//고객정보창 등록(확인)
			
			// DB 에 고객정보 로그 저장
			var memberName = $("#memberName").val();		//이름    
			var memberTel1 = $("#memberTel1").val();		//전화번호  
			var memberTel2 = $("#memberTel2").val();		//전화번호  
			var memberTel3 = $("#memberTel3").val();		//전화번호  
			var memberAddr = $("#memberAddr").val();		//주소    
			var memberMemo = $("#memberMemo").val();		//메모    
			var memberViewChk = $("input[name='memberViewChk']:checked").val();		//옵션
			
			$.ajax({
				// type을 설정합니다.
				type:'POST',
				url:"/common/ajaxBlank.do",
				data:{
					"type"				 : "member_insert"
					, "memberName"		 : memberName
					, "memberTel1"		 : memberTel1
					, "memberTel2"		 : memberTel2
					, "memberTel3"		 : memberTel3
					, "memberAddr"		 : memberAddr
					, "memberMemo"		 : memberMemo
					, "memberViewChk"	 : memberViewChk
				},
				// 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
				success:function(data){
					closeApiDiv('openApiMemberDiv');
				}
			});
		}
		//api 고객정보 등록창 등록
		
		function telSplit(sCaller){
			var sCallerStr;
			var sCallerTot;
			var tel1_2;
			var tel1_2_len;
			var tel1_len;
			var memberTel1;
			var memberTel2;
			var memberTel3;
			
			sCallerStr	 = String(sCaller);
			sCallerTot	 = sCallerStr.length;
			tel1_2_len	 = sCallerTot-4;
			
			var memberTel3	 = sCallerStr.substr(tel1_2_len,4);

			tel1_2		 = sCallerStr.substr(0,tel1_2_len);

			tel1_len	 = 2;
			memberTel1	 = tel1_2.substr(0,2);
			if(memberTel1!="02"){
				tel1_len	 = 3;
				memberTel1	 = tel1_2.substr(0,3);
			}
			memberTel2	 = tel1_2.substring(tel1_len,tel1_2.length);

			$('#memberTel1').val(memberTel1);				// 고객전화번호1
			$('#memberTel2').val(memberTel2);				// 고객전화번호2
			$('#memberTel3').val(memberTel3);				// 고객전화번호3
		}
		
		//ID 값으로 검색하여 창 숨김처리
		function closeApiDiv(dName){
			$('#'+dName).hide();
			//고객정보창 닫을때 초기화
			if(dName == "openApiMemberDiv"){
				$("#memberName").val("");
				$("#memberTel1").val("");
				$("#memberTel2").val("");
				$("#memberTel3").val("");
				$("#memberAddr").val("");
				$("#memberMemo").val("");
				$('input:radio[name="memberViewChk"]:input[value="Y"]').attr("checked", true);
				$('input:radio[name="memberViewChk"]:input[value="N"]').attr("checked", false);
			}
		}
		function closeApiDivChk(dName,chkTxt){
			if(confirm(chkTxt)){
				$('#'+dName).hide();
			}
		}
	</script>
	<!-- 로그인시 가져가는 값들 (id 값으로 가져감) -->
	<form id="openApiLoginForm" name="openApiLoginForm" method="post">
		<input type="hidden" id="authkey" name="authkey" value="241a208cf5eea66d4045917ca845abbaef5a2923" />
		<input type="hidden" id="loginID" name="loginID" value="" />
		<input type="hidden" id="loginPWD" name="loginPWD" value="" />
		<input type="hidden" id="sSendDate" name="sSendDate" value="" />
		<input type="hidden" id="server" name="server" value="0" />					<!-- 서버 종류선택 개발의 경우 0 , 사용의경우 1 -->
	</form>
	<!-- Open Api 사용을 위해 불러옴 -->
	<OBJECT ID="KTOpenAPIX" CLASSID="CLSID:16AB1B2A-A22E-4FAC-92CB-84102DF5CE3D"></OBJECT>

<div class="ml_info">
	<!-- 로그인 현황창(회원id, 월정액, 보너스, api로그인버튼 -->
	<div style="float:left; width:126px;"><font color="#49adeb">${loginInfo.fd_user_name}</font> 님</div>
	<div style="float:left; width:50px;"><a href="#"><img src="/resources/images/shopmaster/btn_ml_logout.gif" border="0"/></a></div>
	<div style="float:left; width:50px; margin-top:15px;">월정액</div>
	<div style="float:left; width:126px; text-align:right; margin-top:15px;"><font color="#49adeb">12</font> 일</div>
	<div style="float:left; width:50px; margin-top:6px">포인트</div>
	<div style="float:left; width:126px; text-align:right; margin-top:6px;"><font color="#49adeb">14,000</font> coin</div>
	<div style="float:left; width:50px; margin-top:6px;">보너스</div>
	<div style="float:left; width:126px; text-align:right; margin-top:6px;"><font color="#49adeb">2,000</font> coin</div>
	<div style="float:left; width:176px; text-align:right; margin-top:15px;"><a href="javascript:openApiLoginOn()" class="ml_a">통화 Open API 로그인 ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:15px;"><a href="javascript:LineJoin()" class="ml_a">회선청약 (운영, 개발 업체) ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:15px;"><a href="javascript:UserJoinEX()" class="ml_a">회원가입 (회원) ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:15px;"><a href="javascript:SetMyInfo()" class="ml_a">환경설정 ></a></div>
	<div style="float:left; width:176px; text-align:right; margin-top:15px;"><a href="javascript:FindPasswdEX()" class="ml_a">비밀번호 찾기 ></a></div>
	<!-- 로그인 현황창(회원id, 월정액, 보너스, api로그인버튼 -->


	<!-- api로그인창 (on/off) -->
	<div id="openApiDiv" style="display:none;position:absolute;text-align:center;width:100%;height:1px;">
		<div style="margin:auto;width:250px;border:1px solid #c7c7c7;">
			<table>
				<tfoot>
					<tr>
						<td colspan="2" align="center">
							<button type="button" style="padding:5px" id="apiLogin" onclick="openApiLogin()">로그인</button>
							&nbsp;&nbsp;
							<button type="button" style="padding:5px" onclick="closeApiDiv('openApiDiv')">닫기</button>
						</td>
					</tr>
				</tfoot>
				<thead>
					<tr>
						<th colspan="2">통화 Open API 로그인</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right">아이디 : </th>
						<td><input type="text" id="apiId" name="apiId" value="parispk@include.co.kr"/></td>
					</tr>
					<tr>
						<th align="right">비밀번호 : </th>
						<td><input type="text" id="apiPwd" name="apiPwd" value="parispk1122"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- api로그인창 (on/off) -->

	<!-- api고객정보 등록창 (on/off) -->
	<form>
		<input type="hidden" id="sCallee" name="sCallee" />				<!-- //수신전화번호 -->
		<input type="hidden" id="sResult" name="sResult" />				<!-- //결과코드 -->
		<input type="hidden" id="sResultTxt" name="sResultTxt" />		<!-- //결과텍스트 -->
		<input type="hidden" id="sClSeqno" name="sClSeqno" />			<!-- //통화 일련번호 -->
		<input type="hidden" id="sMiSeqno" name="sMiSeqno" />			<!-- //발신사용자 일련번호 -->
		
	</form>
	<form id="customerForm" name="customerForm" method="post">
	<div id="openApiMemberDiv" style="display:none;position:absolute;text-align:center;width:100%;height:1px;">
		<div style="margin:auto;width:350px;border:1px solid #c7c7c7;">
			<table style="padding:3px">
				<colgroup>
					<col style="width:150px;" />
					<col style="width:*;" />
				<colgroup>
				
				</colgroup>
				<tfoot>
					<tr>
						<td colspan="2" align="center">
							<button type="button" style="padding:5px" id="apiLogin" onclick="openApiMemberAdd()">확인</button>
							&nbsp;&nbsp;
							<button type="button" style="padding:5px" onclick="closeApiDivChk('openApiMemberDiv','고객정보 등록을 취소하고 팝업으로 닫으시겠습니까?')">닫기</button>
						</td>
					</tr>
				</tfoot>
				<thead>
					<tr>
						<th colspan="2">고객정보 등록</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="left">이름*</th>
						<td><input type="text" id="memberName" name="memberName" value="" style="width:95%"/></td>
					</tr>
					<tr>
						<th align="left">전화번호*</th>
						<td>
							<input type="text" id="memberTel1" name="memberTel1" value="" style="width:20%"/>&nbsp;-&nbsp;
							<input type="text" id="memberTel2" name="memberTel2" value="" style="width:25%"/>&nbsp;-&nbsp;
							<input type="text" id="memberTel3" name="memberTel3" value="" style="width:25%"/>
						</td>
					</tr>
					<tr>
						<th align="left">주소</th>
						<td><input type="text" id="memberAddr" name="memberAddr" value="" style="width:95%"/></td>
					</tr>
					<tr>
						<th align="left">메모</th>
						<td><textarea id="memberMemo" name="memberMemo" style="width:95%" rows="4" cols=""></textarea></td>
					</tr>
					<tr>
						<th align="left">보기옵션</th>
						<td>
							<label><input type="radio" id="memberViewChkY" name="memberViewChk" value="Y" checked="checked"/>보기</label>
							<label><input type="radio" id="memberViewChkN" name="memberViewChk" value="N"/>숨김</label>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</form>
	<!-- api고객정보 등록창 (on/off) -->


						<input type="button" value='로그아웃' onClick="Logout()">
						<input type="button" value='도움말' onClick="HelpEX()">
</div>



<input value="1" id="testText"/>
<script>
	function testadd(){
		var htmlTxt = "<tr><td>2014.07.03</td><td>정수익"+$('#testText').val()+"</td><td>010-9876-5432</td></tr>";
		$('#testTable > tbody').prepend(htmlTxt);
		$('#testText').val(($('#testText').val()*1)+1);
	}
	function testdel(){
		$('#testTable > tbody > tr:last-of-type').remove();
	}
	function viewtest(){
		alert($('#testTable').html());
	}
</script>
<div style="position:absolute"></div>
<table id="testTable">
	<thead>
		<tr>
			<th>수신일시</th>
			<th>이름</th>
			<th>수신번호</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>2014.07.03 15:32:43</td>
			<td>정수익</td>
			<td>010-9876-5432</td>
		</tr>
	</tbody>
</table>
<span onclick="testadd()">add</span>
<span onclick="testdel()">del</span>
