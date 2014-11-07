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
		  	z-index: 10;
		}
		.no-border { border:0px; }
	</style>
	
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="/resources/js/jqplot/excanvas.min.js"></script><![endif]-->
	
	<!-- JQ-PLOT의 기본 설정 -->
    <script class="include" type="text/javascript" src="/resources/js/jqplot/jquery.jqplot.min.js"></script>

	<!-- 선그래프를 바그래프로 변환 -->
	<script class="include" type="text/javascript" src="/resources/js/jqplot/plugins/jqplot.barRenderer.min.js"></script>

	<!-- Highlighter(마우스 접근시 데이터정보 표시) 설정 -->
	<script class="include" type="text/javascript" src="/resources/js/jqplot/plugins/jqplot.highlighter.min.js"></script>
		
	<script type="text/javascript">

		//해당월의 일수 반환
		function CntDayOfMonth(year, month)	{
			return 32 - new Date(year, month-1, 32).getDate();//month는 0부터 시작
		}
	
	    $(document).ready(function () {
	    	
	    	<c:forEach var="item" items="${date_list}" varStatus="status" >
	    		var dateStr = '${item}';
	    		$('#searchDate').append('<option value="' + dateStr.substring(0, 4) + dateStr.substring(5, 7) + '">' + dateStr.substring(0, 4) + '년 ' + dateStr.substring(5, 7) + '월</option>');
			</c:forEach>
			
			$('#searchDate').val('${searchDate}');
			$('#fk_tel').val('${fk_tel}');
	
			//날짜 갯수
			var date = '${searchDate}';
			var year = date.substring(0, 4);
			var month = date.substring(4, 6);
			var ticks 	= new Array();
			var cnt		= CntDayOfMonth(year,month);
			for(i=1; i<=cnt; i++){
				ticks.push(i);
			}
	
			//중요! 응답건수 통화건수의 max값
			var s1_max		= 173;
			var s2_max		= 18;
			var yaxis_max	= s1_max > s2_max ? s1_max : s2_max;
	
			//선그래프(통화건수)
			var s1 = [
				<c:if test="${!empty graphDataList}">
					<c:forEach var="item" items="${graphDataList}" varStatus="status" >
						<c:if test="${status.index != 0}">
								,
						</c:if>
						[Number('${item.day_number}'), Number('${item.req_count}')]
					</c:forEach>
				</c:if>
				<c:if test="${empty graphDataList}">
				['', '']
				</c:if>
			];
	       /* var s1 = [
				[1	, 112],
				[2	, 122],
				[3	, 104],
				[4	, 99],
				[5	, 121],
				[6	, 148],
				[7	, 114],
				[8	, 133],
				[9	, 161],
				[10	, 173],
				[11	, 112],
				[12	, 122],
				[13	, 104],
				[14	, 99],
				[15	, 121],
				[16	, 148],
				[17	, 114],
				[18	, 133],
				[19	, 161],
				[20	, 173],
				[21	, 112],
				[22	, 122],
				[23	, 104],
				[24	, 99],
				[25	, 121],
				[26	, 148],
				[27	, 114],
				[28	, 133],
				[29	, 161],				[30	, 161],
				[31	, 173]
			]; */
	
			//막대그래프(응답건수)
			var s2 = [
				<c:if test="${!empty graphDataList}">
					<c:forEach var="item" items="${graphDataList}" varStatus="status" >
						<c:if test="${status.index != 0}">
								,
						</c:if>
						[Number('${item.day_number}'), Number('${item.call_count}')]
					</c:forEach>
				</c:if>
				<c:if test="${empty graphDataList}">
				['', '']
				</c:if>
			]; 
	        /* var s2 = [
				[1	, 62],
				[2	, 62],
				[3	, 64],
				[4	, 59],
				[5	, 41],
				[6	, 78],
				[7	, 94],
				[8	, 33],
				[9	, 51],
				[10	, 63],
				[11	, 72],
				[12	, 42],
				[13	, 64],
				[14	, 34],
				[15	, 71],
				[16	, 88],
				[17	, 54],
				[18	, 43],
				[19	, 51],
				[20	, 63],
				[21	, 52],
				[22	, 72],
				[23	, 44],
				[24	, 89],
				[25	, 91],
				[26	, 58],
				[27	, 64],
				[28	, 73],
				[29	, 51],				[30	, 51],
				[31	, 83]
			]; */
	
	        plot1 = $.jqplot("chart_div", [s2, s1], {// 그래프 입력값
	            animate			: true,
	            animateReplot	: true,
				seriesDefaults	:{		// 그래프 종류
		            fill: false,		// 그래프 채우기
		            showMarker: true	// 입력값 포인트 표시
				},
				seriesColors	: [
		   			"red"
		   			,"blue"
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
    					label		: '&nbsp;응답건수&nbsp;',
    					pointLabels	: {
    						show	: true
    					},
    					renderer	: $.jqplot.BarRenderer,
    					showHighlight: true,
//                        yaxis		: 'y2axis',	//우측 Y축 라벨
    					rendererOptions	: {
    						animation	: {
    							speed	: 2500
    						},
    						barWidth	: 10,
    						barPadding	: -10,
    						barMargin	: 0,
    						highlightMouseOver: false
    					},
    					highlighter: {
    						show			: true,
    						showLabel		: true,
    						tooltipAxes		: 'xy',
    						sizeAdjust		: 7.5 ,
    						yvalues			: 4,
    						formatString	:
    							 '<table class="jqplot-highlighter"><tr><td><b><font size="2px">'
	   							 +'<center><font color="black">%s일 건수</font><center><br>'
    							 +'<font color="red">응답</font> <font color="black">%s 건</font>'
    							 +'</font></b></td></tr></table>',
    						tooltipLocation : 'ne'// ewsn (동서남북 중복허용)
    					}
    				},
    				{
    					label		: '&nbsp;통화건수&nbsp;',
    					rendererOptions	: {
    						animation	: {
    							speed	: 2000
    						}
    					},
    					highlighter: {
    						show			: true,
    						showLabel		: true,
    						tooltipAxes		: 'xy',
    						sizeAdjust		: 7.5 ,
    						yvalues			: 4,
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
//						label				: '날 짜',
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
//						label				: '건<br><br>수',
						min:0,			//row 최소값 (세로값)
						max:${maxCnt},
	                    tickOptions			: {
	                        formatString	: "%'d"
	                    },
	                    rendererOptions		: {
	                        forceTickAt0	: true
	                    },
	                    rendererOptions		: {
							tickInset		: 0.3,//상하패딩
						}
	                },
	                y2axis: {	//응답건수(세로값)
						min:0,			//row 최소값 (세로값)
	                	max:${maxCnt},
//						max:yaxis_max,	//row 최대값
	                    tickOptions			: {
	                        formatString	: "%'d"
	                    },
	                    rendererOptions		: {
	                    	tickInset		: 0.3,//상하패딩
	                        alignTicks		: true,
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
	    
	    $(function(){
			$('#report_ifrm').load(function(){
				   $('#report_ifrm').attr("height",report_ifrm.document.body.scrollHeight);
			});
		});
	    
	    function goSearch(){ 
        	var form = document.reportForm;
        	form.submit();
        }
	    
	 	 //레이어팝업 숨김
		function popHide(dName) {
			$('#'+dName).hide();
		}
		
		//레이어팝업 돌출
		function popShow(dName) {
			$('#'+dName).show();
		}
		
		function popDetail(group_code, call_count){
			report_ifrm.location.href = "/report/ifrmReportDetail.do?&group_code=" + group_code + "&call_count=" + call_count;
			popShow('popDetailList');
		}
	    
	</script>
	${pageNavi.script }
	</jsp:attribute>
	
	<jsp:body>
		<div id="popDetailList" name="popDetailList" style="display:none;position:absolute;top:500px;left:20px;width:95%;height:530px;z-index:1">
			<div style="width:615px; height:510px; border: 1px solid #cbcbcb; padding:1px; margin:0 auto; background:#fff">
				<div class="pop_top">
					<div class="pop_x4"><a href="javascript:popHide('popDetailList');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">상세정보</div>
			    </div>
			    <iframe name="report_ifrm" id="report_ifrm" width="100%" height="100%" frameborder="0" scrolling="no"  maginwidth="0" marginheight="0" frameborder="0" src=""></iframe>
		    </div>
		</div>
	    <div id="main_r">
			<div class="contents_area">
	        	<div class="sub_tit">
	            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_report.gif" border="0"/></div>
	            	<div class="sub_now">홈 &gt; <font class="sub_now_t">리포트</font></div>
	            </div>
	            <div class="sub_tit_dot"></div>
	            <div class="sub_contents">
	            	<form name="reportForm" id="reportForm" action="/report/reportMain.do" method="post">
		            	<div style="height:26px;">
		                	<div style="float:left; width:105px;">
		                    	<select id="searchDate" name="searchDate" class="listmenu_st" style="width:100px;"></select>
		                  	</div>
		                  	<div style="float:left; width:125px;">
		                        <select id=fk_tel name="fk_tel" style="width:120px">
		                        	<option value="">전체</option>
									<c:forEach var="contents" items="${tel_list}" varStatus="status" >
										<option value="${contents.fk_tel}">${contents.fk_tel}</option>
									</c:forEach>
								</select>
		                    </div>
		                  	<div style="float:left;"><a href="javascript:goSearch()"><img src="/resources/images/shopmaster/btn2_search.gif" border="0"/></a></div>
						</div>
					</form>
	                <div class="tb_top"></div>
	              	<div class="report_graph" id="chart_div" style="width:100%;height:260px;border:1px"></div>
	              	<div style="height:40px; margin-top:15px;">
	                	<div style="float:left; color:blue; font-weight:bold;">● 통화건수</div>
	                    <div style="float:left; margin-left:15px; color:red; font-weight:bold;">● 응답건수</div>
	                </div>
	                <div class="tb_top"></div>
	                <div>
	                	<table width="930" border="0" cellspacing="0" cellpadding="0">
		                  	<!-- tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
	                    	<tr>
		                        <td width="90" class="tb_tit_c">날짜</td>
		                        <td width="100" class="tb_tit_c">타입</td>
		                        <td class="tb_tit_c">추출</td>
		                        <td width="100" class="tb_tit_c">발송건수</td>
		                        <td width="100" class="tb_tit_c">성공건수</td>
		                        <td width="100" class="tb_tit_c">실패건수</td>
		                        <td width="100" class="tb_tit_c">응답건수</td>
	                      	</tr>
		                   	<c:if test="${!empty report_list}">
								<c:forEach var="item" items="${report_list}" varStatus="status" >
									<tr onclick="popDetail(	'${item.group_code}', '${item.call_count}')" style="cursor:pointer;">
									
										<c:set var="reserve_time" value="${item.reserve_time}"/>
										<td class="tb_nor_c">${fn:substring(reserve_time, 6, 8)}일</td>
				                    
				                    	<c:choose>
											<c:when test="${item.msg_type == '1'}">
												<td class="tb_nor_c">SMS</td>
											</c:when>
											<c:when test="${item.msg_type == '4'}">
												<c:if test="${item.msg_sub_type == '1'}">
													<td class="tb_nor_c">LMS</td>
												</c:if>
												<c:if test="${item.msg_sub_type != '1'}">
													<td class="tb_nor_c">MMS</td>
												</c:if>
											</c:when>
											<c:otherwise><td class="tb_nor_c"></td></c:otherwise>
										</c:choose>	
										
										<c:choose>
											<c:when test="${item.search_month == '1'}">
												<c:if test="${item.search_customer == '2'}">
													<td class="tb_nor_c">최근 1개월 이내의 단골 고객</td>
												</c:if>
												<c:if test="${item.search_customer == '3'}">
													<td class="tb_nor_c">최근 1개월 이내의 신규 고객</td>
												</c:if>
												<c:if test="${item.search_customer != '2' && item.search_customer != '3'}">
													<td class="tb_nor_c">최근 1개월 이내의 전체 고객</td>
												</c:if>
											</c:when>
											<c:when test="${item.search_month == '3'}">
												<c:if test="${item.search_customer == '2'}">
													<td class="tb_nor_c">최근 3개월 이내의 단골 고객</td>
												</c:if>
												<c:if test="${item.search_customer == '3'}">
													<td class="tb_nor_c">최근 3개월 이내의 신규 고객</td>
												</c:if>
												<c:if test="${item.search_customer != '2' && item.search_customer != '3'}">
													<td class="tb_nor_c">최근 3개월 이내의 전체 고객</td>
												</c:if>
											</c:when>
											<c:when test="${item.search_month == '6'}">
												<c:if test="${item.search_customer == '2'}">
													<td class="tb_nor_c">최근 6개월 이내의 단골 고객</td>
												</c:if>
												<c:if test="${item.search_customer == '3'}">
													<td class="tb_nor_c">최근 6개월 이내의 신규 고객</td>
												</c:if>
												<c:if test="${item.search_customer != '2' && item.search_customer != '3'}">
													<td class="tb_nor_c">최근 6개월 이내의 전체 고객</td>
												</c:if>
											</c:when>
											<c:otherwise>
												<td class="tb_nor_c"></td>
											</c:otherwise>
										</c:choose>
				                        <td class="tb_nor_c">${item.req_count}</td>
				                        <td class="tb_nor_c">${item.mcs_res_result_success}</td>
				                        <td class="tb_nor_c">${item.mcs_res_result_fail}</td>
				                        <td class="tb_nor_c">${item.call_count}</td>
		                  			</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty report_list}">
								<tr> 	
									<td class="tb2_nor_l" colspan="7" style="text-align:center">&nbsp;검색된 데이터가 없습니다.</td>
								</tr>
							</c:if>
	                    </table>
					</div>
					<div style="width:930px; text-align:center; padding-top:20px; clear:both;">
					 	${pageNavi.navi}
					</div>
	                <div style="width:930px; height:20px; margin-top:15px;"><font class="ca_txt11_999999">※ 응답건수 및 응답률은 문자발송 14일 까지 리포트에 포함 됩니다.</font></div>
	                <div style="width:930px; height:20px;"><font class="ca_txt11_999999">※ 통신망 법의 의거하여 통화와 문자 이력은 6개월까지 조회 가능합니다. </font></div>
			  </div>
        </div>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>
	    
	</jsp:body>
</layout:common>