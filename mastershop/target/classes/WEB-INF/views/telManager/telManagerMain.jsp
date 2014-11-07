<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common> 

	<jsp:attribute name="stylesheet">
	<!-- JQ-PLOT의 CSS를 설정 -->
	<link class="include" rel="stylesheet" type="text/css" href="/resources/js/jqplot/jquery.jqplot.min.css" />
	
	<style type="text/css" media="screen">
		.jqplot-axis {
		  	font-size: 1.0em;
		  	z-index: 10000;
		}
		.no-border { border:0px; }
	</style>
		
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="/resources/js/jqplot/excanvas.min.js"></script><![endif]-->
	
	<!-- JQ-PLOT의 기본 설정 -->
    <script class="include" type="text/javascript" src="/resources/js/jqplot/jquery.jqplot.min.js"></script>

	<!-- Highlighter(마우스 접근시 데이터정보 표시) 설정 -->
	<script class="include" type="text/javascript" src="/resources/js/jqplot/plugins/jqplot.highlighter.min.js"></script>
		
	<script type="text/javascript">

		//해당월의 일수 반환
		function CntDayOfMonth(year, month)	{
			return 32 - new Date(year, month-1, 32).getDate();//month는 0부터 시작
		}
	
	    $(document).ready(function () {
	
			//날짜 갯수
			var ticks 	= new Array();
			var cnt		= CntDayOfMonth('${year}','${month*1}');
			for(i=1; i<=cnt; i++){
				ticks.push(i);
			}

			var g_maxCnt = ${maxCnt};

			if(g_maxCnt<4){
				g_maxCnt = 4;
			}
			
			//선그래프(통화건수)
			var s1 = [
					<c:if test="${!empty chartArray}">
						<c:forEach var="item" items="${chartArray}" varStatus="status" >
							<c:if test="${status.index != 0}">
									,
							</c:if>
							['${item.cMonth}', '${item.cMonthCnt}']
						</c:forEach>
					</c:if>
					<c:if test="${empty chartArray}">
						['', '']
					</c:if>
			];
	
	        plot1 = $.jqplot("chart_div", [s1], {// 그래프 입력값
	            animate			: true,
	            animateReplot	: true,
				seriesDefaults	:{		// 그래프 종류
		            fill: false,		// 그래프 채우기
		            showMarker: true	// 입력값 포인트 표시
				},
				seriesColors	: [
		   			"blue"
		   			,"red"
		   		],
				legend: {
					show		:false,		// 범주표시 : true/false
					location	: 'nw'		// 범주위치 (좌상단) 	// ewsn (동서남북 중복허용)
					//placement	: 'outsideGrid',			//범주위치(그래프 in/out)
					//size 		: '3px',
				},
	            cursor: {
	                show		: true,
	                zoom		: false,
	                looseZoom	: false,
	                showTooltip	: true
	            },
	            series:[
    				{
    					label			: '&nbsp;통화수&nbsp;',
    					pointLabels	: {
    						show	: true
    					},
    					renderer	: $.jqplot.BarRenderer,
    					showHighlight	: true,
    					rendererOptions	: {
    						animation	: {
    							speed	: 2000
    						},
    						highlightMouseOver: true
    					},
    					highlighter: {
    						show			: true,
    						showLabel		: true,
    						tooltipAxes		: 'xy',
    						sizeAdjust		: 7.5 ,
    						formatString	:
	   							 '<table class="jqplot-highlighter"><tr><td><b><font size="2px">'
	   							 +'<center><font color="black">%s일 건수</font><center><br>'
	   							 +'<font color="blue">통화</font> <font color="black">%s 건</font>'
	   							 +'</font></b></td></tr></table>',
    						tooltipLocation : 'ne'// ewsn (동서남북 중복허용)
    					}
    				}
    			],
	            axesDefaults: {
	                pad: 0
	            },
	            axes: {
	                // These options will set up the x axis like a category axis.
	                xaxis: {	//cell 값(가로값)
	//					label				: '날 짜',
						ticks				:ticks,
	                    tickInterval		: 1,
	                    drawMajorGridlines	: false,
	                    drawMinorGridlines	: true,
	                    drawMajorTickMarks	: false,
	                    rendererOptions		: {
							tickInset		: 0.5,//앞뒤패딩
							minorTicks		: 1,
							tickOptions		: {
								formatString: "%'d"
							}
						}
	                },
	                yaxis: {	//통화건수(세로값)
	//					label				: '건 수',
						min:0,			//row 최소값 (세로값)
	                	max:g_maxCnt,
	                    tickOptions			: {
	                        formatString	: "%'d"
	                    },
	                    rendererOptions		: {
	                    	tickInset		: 0.3,//상하패딩
	                        forceTickAt0	: true
	                    }
	                }
	            },
	            highlighter: {
	                show			: true,
	                showLabel		: true,
	                tooltipAxes		: 'y',
	                sizeAdjust		: 7.5 ,
					tooltipLocation : 'ne'// ewsn (동서남북 중복허용)
	            }
	
	        });
	
	    });
	</script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('.bgchange').mouseover(function(){
				$(this).find('td').css('background','#e0e6ec');
			}).mouseout(function(){
				$(this).find('td').css('background','');
			});
		});
	
		function goOrder(colName){
			//타이틀 정렬
			var frm = document.goPageForm;
			frm.target = "";

			if(frm.orderColumn.value == colName){
				if(frm.orderTyp.value==""){
					frm.orderTyp.value="d";
				}else{
					frm.orderTyp.value="";
				}
			}else{
				frm.orderColumn.value=colName;
				frm.orderTyp.value="d";
			}
			goSearch('o');
		}
		
		function goSearch(type){
			
			
			var frm = document.goPageForm;
			frm.target = "";
			
			if(type=="b"){
				if(frm.searchString.value==''){
					alert("검색어를 입력해 주세요.");
					frm.searchString.focus();
					return;				
				}
			}

			if(type=="a"){//월 증가
				if($('#sMonth').val()==12){
					$('#sYear').val($('#sYear').val()*1+1);
					$('#sMonth').val('0');
				}
				$('#sMonth').val($('#sMonth').val()*1+1);
			}else if(type=="m"){//월 감소
				if($('#sMonth').val()==1){
					$('#sYear').val($('#sYear').val()*1-1);
					$('#sMonth').val('13');
				}
				$('#sMonth').val($('#sMonth').val()*1-1);
			}
			
			if($('#sMonth').val().length == 1){
				$('#sMonth').val("0"+$('#sMonth').val());
			}
			
			var nowDate = new Date();
			var n_year = nowDate.getFullYear();  
			var n_month = nowDate.getMonth()+1;
			n_month = (n_month < 10) ? '0' + n_month : n_month;
			var n_date = n_year + '' + n_month;
			
			var s_year = $('#sYear').val();
			var s_month = $('#sMonth').val();
			var s_date = s_year + '' + s_month;
			
/*
			alert(n_date);
			alert(s_date);
*/

			frm.action = "/telManager/telManagerMain.do";
			frm.page.value = '';
			frm.fd_rcv_tel.value = '';
			
			if(n_date >= s_date){
				if(($('#searchColumn').val() == '') && (type == 'b')){
					alert('검색 조건을 선택해주세요.');
				}else {
					if(s_date > 201407){
						frm.submit();
					}
				}
			}else{
				$('#sYear').val(n_year);
				$('#sMonth').val(n_month);
			}
		}
		
		function saveExl(){
			var frm = document.goPageForm;
			frm.target = "";
			frm.action = "/telManager/telManagerListExcel.do";
			frm.page.value = '';
			frm.fd_rcv_tel.value = '';
			frm.submit();
		}

		function goView(fd_member_id,fd_rcv_tel){
			var frm = document.goPageForm;
			frm.action = "/telManager/telManagerDetail.do";
			frm.target = "detailIfrm";
			frm.page.value = '${param.page}';
			frm.fd_rcv_tel.value = fd_rcv_tel;
			frm.submit();
			divOn("detailDiv");
		}
		
		function chkDel(){
			if($("input[name=check]:checked").length > 0){
				if(confirm($("input[name=check]:checked").length+"개의 고객정보를 삭제 하시겠습니까?")){
					var frm = document.goPageForm;
					frm.target = "";
					frm.action = "/telManager/telManagerPro.do";
					frm.method = "post";
					frm.page.value = '${param.page}';
					frm.goType.value = 'del';
					frm.fd_rcv_tel.value = '';
					frm.submit();
				}
			}else{
				alert("선택된 항목이 없습니다.");
			}
		}
		
		function divOn(dName){
			$('#'+dName).show();
		}
		function divOnOff(dName){
			$('#'+dName).toggle();
		}
		function SendCTCJava(sCaller, sCallee){ 
			document.all.sCaller.value = sCaller;
			document.all.sCallee.value = sCallee;
			top.left_frame.SendCTC();
//			document.all.sCaller.value = sCaller;
//			document.all.sCallee.value = sCallee;
		}

	</script>
	${pageNavi.script }
	</jsp:attribute>
	
	<jsp:body>
	
<div id="main_r">
		<div class="contents_area">
        	<div class="sub_tit">
            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_tel_admin.gif" border="0"/></div>
            	<div class="sub_now">홈 > <font class="sub_now_t">통화관리</font></div>
            </div>
			<form id="goPageForm" name="goPageForm" method="get">
				<input type="hidden" id="sYear" name="sYear" value="${year}"/>	<!-- 년도 -->
				<input type="hidden" id="sMonth" name="sMonth" value="${month}"/>	<!-- 월 -->
				<input type="hidden" id="orderColumn" name="orderColumn" value="${param.orderColumn}"/> <!-- 타이틀 정렬컬럼명 -->
				<input type="hidden" id="orderTyp" name="orderTyp" value="${param.orderTyp}"/> <!-- 타이틀 정렬방법 -->
				<input type="hidden" id="page" name="page" value="${page}">
				<input type="hidden" id="fd_rcv_tel" name="fd_rcv_tel">
				<input type="hidden" id="goType" name="goType"/>

	            <div class="sub_tit_dot"></div>
	            <div class="sub_contents">
	            	<div style="height:31px;">
	                    <div style="float:left; width:28px;"><a href="javascript:goSearch('m')"><img src="/resources/images/shopmaster/list_priv.gif" border="0"/></a></div>
	                    <div style="float:left; width:30px;"><a href="javascript:goSearch('a')"><img src="/resources/images/shopmaster/list_next.gif" border="0"/></a></div>
	                    <div style="float:left; margin-top:6px;">${year}년 ${month}월 &nbsp;&nbsp;&nbsp;&nbsp; 총 누적 통화 건수 : ${tot_month}건</div>
	                    <div style="float:right;"><img src="/resources/images/shopmaster/date_month.gif" border="0"/></div>
					</div>
	                <div class="tb_top"></div>
	                <div class="tel_tit">통화 수신 통계</div>
	                <div class="tel_graph" style="width:100%;height:260px;" id="chart_div"></div>

<div id="detailDiv" style="display:none;position:absolute;top:540px;left:170px;width:990px;height:0px;">
<!-- 팝업 view -->
	<iframe name="detailIfrm" id="detailIfrm" src="" style="display:block;position:absolute;top:0px;left:50px;width:100%;height:670px;scrolling:no;z-index:1;" frameborder="0"></iframe>
</div>

	                <div style="height:27px; margin-top:35px;">
	                    <div style="float:right;"><a href="javascript:goSearch('b')"><img src="/resources/images/shopmaster/btn2_search.gif" border="0"/></a></div>
	                    <div style="float:right; width:145px;"><input id="searchString" name="searchString" type="text" class="fild_st" style="width:137px;" value="${param.searchString}"></div>
	                    <div style="float:right; width:85px;">
							<select id="searchColumn" name="searchColumn" class="listmenu_st" style="width:80px;">
							<option value="">선택</option>
							<option value="user_name" <c:if test="${param.searchColumn=='user_name'}">selected='selected'</c:if>>이름</option>
							<option value="user_tel" <c:if test="${param.searchColumn=='user_tel'}">selected='selected'</c:if>>수신번호</option>
							<option value="member_tel" <c:if test="${param.searchColumn=='member_tel'}">selected='selected'</c:if>>발신번호</option>
							<option value="user_addr" <c:if test="${param.searchColumn=='user_addr'}">selected='selected'</c:if>>주소</option>
							</select>
						</div>
	                </div>
	                <div class="tb_top"></div>
	                <div id="">
	                <c:if test="${view_name_flag != 0}"><c:set var="nameChk" value="display:none;" /></c:if>
	                
	                	<table width="930" border="0" cellspacing="0" cellpadding="0">
		                  <!-- tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
	                      <tr>
	                        <td width="95" class="tb_tit_c"><a href="javascript:goOrder('fd_tel')" class="tbt_a" style="text-decoration:none;">발신번호</a></td>
	                        <td width="95" class="tb_tit_c" style="${nameChk}"><font color="#000000"><a href="javascript:goOrder('fd_user_name')" style="text-decoration:none;">이름</a></font></td>
	                        <td class="tb_tit_c"><a href="javascript:goOrder('fd_addr')" class="tbt_a" style="text-decoration:none;">주소</a></td>
	                        <td width="95" class="tb_tit_c"><a href="javascript:goOrder('fd_rcv_tel')" class="tdt_a" style="text-decoration:none;">수신번호</a></td>
	                        <td width="120" class="tb_tit_c"><a href="javascript:goOrder('fd_reg_date')" class="tbt_a" style="text-decoration:none;">통화 일시</a></td>
	                        <td width="50" class="tb_tit_c">비고</td>
	                        <td width="55" class="tb_tit_c">전화걸기</td>
	                      </tr>
	
						<!-- DYNAMIC AREA 'list' -->
						<c:if test="${!empty mArray}">
							<c:forEach var="item" items="${mArray}" varStatus="status" >
							<c:set var="clickEv" value="onclick=\"goView('${item.fd_member_id}','${item.fd_rcv_tel_org}')\""/>
		                      <tr class="bgchange">
		                      
		                        <%-- <td ${clickEv} class="tb_nor_c">${item.fd_tel}</td> --%>
		                        <td ${clickEv} class="tb_nor_c">${item.fd_rcv_tel}</td>
		                        
		                        <%-- <td ${clickEv} class="tb_nor_c"><font color="#000000">${item.fd_user_name}</font></td> --%>
		                        <td ${clickEv} class="tb_nor_c" style="cursor:pointer;${nameChk}"><font color="#000000">${item.fd_user_name}</font></td>
		                        <td ${clickEv} class="tb_nor_l" style="cursor:pointer;">${item.fd_addr}</td> 
		                        
		                        <%-- <td ${clickEv} class="tb_nor_c" style="cursor:pointer;">${item.fd_rcv_tel}</td> --%>
		                        <td ${clickEv} class="tb_nor_c" style="cursor:pointer;">${item.fd_tel}</td>
		                        
		                        <td ${clickEv} class="tb_nor_c" style="cursor:pointer;">${item.fd_reg_yy}.${item.fd_reg_mm}.${item.fd_reg_dd} ${item.fd_reg_hh}:${item.fd_reg_mi}:${item.fd_reg_ss}</td>
		                        <td ${clickEv} class="tb_nor_c" style="cursor:pointer;">
		                        	<c:if test="${item.fd_openapi_rc_code == '200'}"></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '401'}">결번</c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '405'}">무응답</c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '701'}">연결중</c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '201'}"><font color="blue">통화중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '404'}"><font color="blue">통화중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '700'}"><font color="blue">통화중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '202'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '203'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '407'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '408'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '904'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '905'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '906'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '907'}"><font color="red">부재중</font></c:if>
								</td>
								<td class="tb_nor_c" style="cursor:pointer;"><a href="javascript:SendCTCJava('${item.fd_tel}', '${item.fd_rcv_tel}')">통화</a></td>
		                      </tr>
		                    </c:forEach>
		                </c:if>
						<c:if test="${empty mArray}">
							<tr>
								<td class="tb_nor_c" colspan="6">검색된 데이타가 없습니다</td>
							</tr>
						</c:if>
						<!-- DYNAMIC AREA 'list' -->
	                    </table>
					</div>


					<!-- DYNAMIC AREA 'print' -->
	                <div id="print_div" style="display:none">
	                <c:if test="${view_name_flag != 0}"><c:set var="nameChk" value="display:none;" /></c:if>
	                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		                  <!-- tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
	                      <tr>
	                        <td width="10%" class="tb_tit_c"><a href="javascript:goOrder('fd_tel')" class="tbt_a" style="text-decoration:none;">발신번호</a></td>
	                        <td width="10%" class="tb_tit_c" style="${nameChk}"><font color="#000000"><a href="javascript:goOrder('fd_user_name')" style="text-decoration:none;">이름</a></font></td>
	                        <td width="*%"class="tb_tit_c"><a href="javascript:goOrder('fd_addr')" class="tbt_a" style="text-decoration:none;">주소</a></td>
	                        <td width="10%" class="tb_tit_c"><a href="javascript:goOrder('fd_rcv_tel')" class="tdt_a" style="text-decoration:none;">수신번호</a></td>
	                        <td width="14%" class="tb_tit_c"><a href="javascript:goOrder('fd_reg_date')" class="tbt_a" style="text-decoration:none;">통화 일시</a></td>
	                        <td width="12%" class="tb_tit_c">비고</td>
	                      </tr>
	
						<c:if test="${!empty mArray}">
							<c:forEach var="item" items="${mArray}" varStatus="status" >
		                      <tr class="bgchange">
		                        <td class="tb_nor_c">${item.fd_tel}</td>
		                        <%-- <td ${clickEv} class="tb_nor_c"><font color="#000000">${item.fd_user_name}</font></td> --%>
		                        <td class="tb_nor_c" style="cursor:pointer;${nameChk}"><font color="#000000">${item.fd_user_name}</font></td>
		                        <td class="tb_nor_l" style="cursor:pointer;">${item.fd_addr}</td>
		                        <td class="tb_nor_c" style="cursor:pointer;">${item.fd_rcv_tel}</td>
		                        <td class="tb_nor_c" style="cursor:pointer;">${item.fd_reg_yy}.${item.fd_reg_mm}.${item.fd_reg_dd} ${item.fd_reg_hh}:${item.fd_reg_mi}:${item.fd_reg_ss}</td>
		                        <td class="tb_nor_c" style="cursor:pointer;">
		                        	<c:if test="${item.fd_openapi_rc_code == '200'}"></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '401'}">결번</c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '405'}">무응답</c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '701'}">연결중</c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '201'}"><font color="blue">통화중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '404'}"><font color="blue">통화중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '700'}"><font color="blue">통화중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '202'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '203'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '407'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '408'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '904'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '905'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '906'}"><font color="red">부재중</font></c:if>
		                        	<c:if test="${item.fd_openapi_rc_code == '907'}"><font color="red">부재중</font></c:if>
								</td>
		                      </tr>
		                    </c:forEach>
		                </c:if>

						<c:if test="${empty mArray}">
							<tr>
								<td class="tb_nor_c" colspan="6">검색된 데이타가 없습니다</td>
							</tr>
						</c:if>
						<!-- DYNAMIC AREA 'list' -->
	                    </table>
					</div>


					</form>
	                <div style="width:930px; height:24px; margin-top:15px;">
	               	  <div style="float:left;width:105px;padding-top:2px;"></div>
	                	<div style="float:left;width:720px; text-align:center;">
							${pageNavi.navi}
	                	</div>
	                	<div style="float:right;width:50px;padding-top:2px;"><a href="javascript:saveExl()"><img src="/resources/images/shopmaster/btn_excel.gif" border="0"/></a></div>
	                	<div style="float:right;width:50px;padding:2px 4px 0px 0px;"><a href="javascript:printArea()"><img src="/resources/images/shopmaster/btn_print.gif" border="0"/></a></div>
	                </div>
				</div>

			<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        </div>
    </div>
 <iframe id="print_iframe" name="print_iframe" style="display:none; width:500px; height:200px" src="/telManager/ifrmPrintPage.do"></iframe>


<script type="text/javascript">
<!--
function printArea() {
	$("#print_iframe").css("display","block");
	$("#print_iframe").contents().find("body").html($("#print_div").html());
	document.print_iframe.focus();
	document.print_iframe.window.print();
//	top.parent.focus();
	$("#print_iframe").contents().find("body").html("");
	$("#print_iframe").css("display","none");
}

-->
</script>

	</jsp:body>
</layout:common>