<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
	
<layout:main>


	<jsp:attribute name="stylesheet">
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	
		<script language= "JavaScript">
			var priceAlert = "";

			
			$(document).ready(function(){

				//약관 동의 카운트
				var agreeCount = $("#agreeCount").val();
				if(agreeCount == 0){
					//약관동의가 필요한 경우
					openAgreement();
				}else{
					//약관동의가 이미 된경우 Pass
				}
				//mainCallReLogin(); //메인페이지 호출시마다 통화오픈 api 재로그인
				
				/*if(priceAlert != ""){
					alert(priceAlert);
				}*/
			});
			
			//1개월 이상 체납시
			function payMsg(fd_price, pay_price){				
				var noPayPrice = fd_price - pay_price;
				priceAlert = "고객님 미납요금("+commaNum(noPayPrice)+"원)이 발생하여 알려드립니다.\n\n더 자세한 사항은 고객센터(1899-1431)으로 문의 하세요";
				alert(priceAlert);
				//alertAgree(500, 500, "", "알림", "테스트");
			}

			//2개월 이상 체납시
			function payMsg2(fd_price, pay_price){				
				var noPayPrice = fd_price - pay_price;
				priceAlert = "고객님 미납요금("+commaNum(noPayPrice)+"원)이 발생하여 알려드립니다.\n\n문자 관리 서비스를 이용하실 수 없습니다.\n\n더 자세한 사항은 고객센터(1899-1431)으로 문의 하세요";

				alert(priceAlert);
				//alertAgree(500, 500, "", "알림", "테스트");
			}
			
			
			//3자리 콤마
			function commaNum(num) {  
				var len, point, str;  

				num = num + "";  
				point = num.length % 3  
				len = num.length;  

				str = num.substring(0, point);
				while (point < len) {
					if (str != "") str += ",";
					str += num.substring(point, point + 3);
					point += 3;
				}  
				return str;  
			}
		</script>
	
	</jsp:attribute>
	


	<jsp:body>
	
	<input type="hidden" id="agreeCount" name="agreeCount" value="${agreeCount }" /><!-- 약관동의 여부 -->
	
    <div id="main_r">
    	<div class="mcontnents">
            <div style="float:left;margin:0px 10px 0px 10px;"><a href="javascript:moveMenu('/csManager/csManagerMain.do', 'cs_manager')"><img src="/resources/images/shopmaster/main_m1.gif" border="0"></a></div>
            <div style="float:left;margin:0px 10px 0px 10px;"><a href="javascript:moveMenu('/telManager/telManagerMain.do', 'tel_manager')"><img src="/resources/images/shopmaster/main_m2.gif" border="0"></a></div>
            <div style="float:left;margin:0px 10px 0px 10px;"><a href="javascript:moveMenu('/myInfo/joinInfo.do', 'my_info')"><img src="/resources/images/shopmaster/main_m6.gif" border="0"></a></div>
            <div style="float:left;margin:20px 10px 0px 10px;">
            	<c:choose>
            		<c:when test="${payCnt >= 2}">
            			<a href="javascript:payMsg2(${sum_fd_price }, ${sum_pay_price });">
            				<img src="/resources/images/shopmaster/main_m3.gif" border="0">
            			</a>
            		</c:when>
            		<c:otherwise>
            			<a href="javascript:moveMenu('/smsManager/smsManagerMain.do', 'sms_manager')">
            				<img src="/resources/images/shopmaster/main_m3.gif" border="0">
            			</a>
            		</c:otherwise>
            	</c:choose>
            	
            </div>
            <div style="float:left;margin:20px 10px 0px 10px;"><a href="javascript:moveMenu('/report/reportMain.do', 'report')"><img src="/resources/images/shopmaster/main_m4.gif" border="0"></a></div>
            <div style="float:left;margin:20px 10px 0px 10px;"><a href="javascript:moveMenu('/csCenter/noticeList.do', 'cs_center')"><img src="/resources/images/shopmaster/main_m5.gif" border="0"></a></div>
		</div>
     	<div class="mnotice" >
     		<input type="hidden" id="scrollCount" value="0" />
     		<input type="hidden" id="sType" value="up" />
        	<div style="float:left;width:90px;"><img src="/resources/images/shopmaster/m_notice_tit.gif"></div>
        	<div id="noticeArea" style="float:left;width:597px; height:22px;position:relative; overflow:hidden">
	        	<div id="noticeDiv" style="position:absolute; padding-top:8px; top:0px;">
					<c:if test="${!empty noticeList}">
						<c:forEach var="item" items="${noticeList}" varStatus="status" >
						<div style="float:left; height:22px;">
				        	<div style="float:left;width:500px; height:22px;"><a href="javascript:moveMenu('/csCenter/noticeListView.do?seq=${item.pk_seq}', 'cs_center')" class="mnotice_a">${item.fd_title }</a></div>
				            <div style="float:left;width:97px; height:22px; text-align:center;">
				            	<font color="#bbbbbb">
									<fmt:parseDate value="${item.fd_reg_date}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
									<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd"/>
				            	</font>
				            </div>
				        </div>
						</c:forEach>
					</c:if>
				</div>
				<c:if test="${empty noticeList}">
		        	<div id="noticeDiv" style="position:absolute; padding-top:8px; top:0px;">
			        	<div style="float:left;width:597px;">등록된 내용이 없습니다.</div>
			        </div>
		        </c:if>
        	</div>

            <div style="float:left;width:24px; padding-top:2px;" id="upBt_arrow"><a href="javascript:;"><img src="/resources/images/shopmaster/m_notice_up.gif" border="0"></a></div>
            <div style="float:left;width:30px; padding-top:2px;" id="downBt_arrow"><a href="javascript:;"><img src="/resources/images/shopmaster/m_notice_down.gif" border="0"></a></div>
            <div style="float:left;width:49px; padding-top:2px;"><a href="javascript:moveMenu('/csCenter/noticeList.do', 'cs_center')"><img src="/resources/images/shopmaster/m_notice_go.gif" border="0"></a></div>
        </div>
        <div class="mnotice_line"></div>
        <!--
        <div style="width:910px" id="main_bottom">
        	<div style="color:#ffffff;" class="mbottom1"><a href="http://www.kthcorp.com/intro" target="_blank" class="mnotice_a"><strong>회사소개</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="cs_service.html" class="mnotice_a"><strong>이용약관</strong></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="cs_privacy.html" class="mnotice_a"><strong>개인정보취급방침</strong></a></div>
            <div class="mbottom_line"></div>
            <div style="color:#888888;" class="mbottom2">
            	<div style="float:left;width:95px; height:36px;">케이티하이텔(주)</div>
                <div style="float:left;">대표이사 오세영 / 사업자등록번호 : 211-81-79649 / 통신판매업신고 : 04-680-176<br />주소 : 서울특별시 동작구 보라매5길 23 대표전화 1899-1431</div>
            </div>
			<div style="height:16px;color:#888888; font-size:11px;">Copyright(c) 2014 KT Hitel Co., Ltd. all rights reserved.</div>
		</div>
      -->
        <!-- <a hef="#" onclick="javascript:mainCallReLogin()">TEST</a> -->
    </div>

<script type="text/javascript">
// 공지사항 스크롤
	var $list 	= $('#noticeDiv');					//아이디
	var len 	= $list.children().length;			//개수
	var size 	= $list.children().outerHeight();	//크기
	var speed 	= 3000;								//속도
	var timer 	= null;								
	var auto 	= true;								//자동유무
	var cnt 	= 1;								//처음시작
	var pmFlag	= 1;								//증감 플래그

	$list.css('height',len*size);
	if(auto) timer = setInterval(autoSlide, speed);
	$list.children().bind({
		'mouseenter': function(){
			if(!auto) return false;
			clearInterval(timer);
			auto = false;
		},
		'mouseleave': function(){
			timer = setInterval(autoSlide, speed);
			auto = true;
		}
	})

	
	$('#upBt_arrow').bind({
		'click': function(){
			if(pmFlag == +1){
				pmFlag = -1;
				cnt +=pmFlag-1;
			}else{
				pmFlag = -1;
			}
			autoSlide();
			return false;
		},
		'mouseenter': function(){
			if(!auto) return false;
			clearInterval(timer);
			auto = false;
		},
		'mouseleave': function(){
			timer = setInterval(autoSlide, speed);
			auto = true;
		}
	});
	$('#downBt_arrow').bind({
		'click': function(){
			if(pmFlag == -1){
				pmFlag = +1;
				cnt +=pmFlag+1;
			}else{
				pmFlag = +1;
			}
			autoSlide();
			return false;
		},
		'mouseenter': function(){
			if(!auto) return false;
			clearInterval(timer);
			auto = false;
		},
		'mouseleave': function(){
			timer = setInterval(autoSlide, speed);
			auto = true;
		}
	});

	function autoSlide(){
		if(cnt>len-1){
			cnt = 0;
		}else if(cnt < 0){
			cnt = len-1;
		}
		$list.animate({'top': -(cnt*size)+'px' },'normal');
		cnt+=pmFlag;
	}
</script>
	    
	</jsp:body>
</layout:main>