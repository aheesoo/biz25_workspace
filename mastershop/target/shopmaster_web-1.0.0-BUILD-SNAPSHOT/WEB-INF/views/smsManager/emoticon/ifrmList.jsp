<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<layout:noFrame>
	<jsp:attribute name="stylesheet">
	</jsp:attribute>
	<jsp:attribute name="javascript">
		<script type="text/javascript">
			$(document).ready(function() {
				
			});		
			
			//ID 값으로 검색하여 창 숨김처리
			function openPopDiv(dName){
				$('#'+dName).show();
			}
			
			//ID 값으로 검색하여 창 숨김처리
			function closePopDiv(dName){
				$('#'+dName).hide();
			}

			function callPage(searchColumn){

			
				$("#searchColumn").val(searchColumn);
				$("#searchString").val('');
				$("#page").val('1');
				$("#emoticonForm").submit();				
			}

			function searchPage(){				
				$("#page").val('1');
				$("#emoticonForm").submit();				
			}
		
		</script>
		${pageNavi.script }
	</jsp:attribute>
	<jsp:body>
		<div id="popup3" style="height:730px;">
			<div class="pop_top">
				<div class="pop_x4"><a href="javascript:parent.closePopDiv('popEmoticon')">
					<img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a>
				</div>
				<div class="pop_tit">발송 샘플</div>
			</div>
			<div class="pop_txt">
				<form name="emoticonForm" id="emoticonForm" action="/emoticon/list.do" method="post">
				<input type="hidden" name="searchColumn" id="searchColumn" value="${searchColumn }">
				<input type="hidden" name="page" id="page" value="${page }">
				<input type="hidden" name="search_type" id="search_type" value="${search_type }">
				<c:if test="${search_type=='M' }">
					<div>
						<c:choose>
							<c:when test="${searchColumn == 'U'}">
								<c:set var="view_status" value="1"/>								
							</c:when>
							<c:otherwise>
								<c:set var="view_status" value="2"/>
							</c:otherwise>
						</c:choose>
						<div class="tab_left${view_status }"><a href="javascript:callPage('U');">발송문구</a></div>
						<div class="tab_right${view_status }"><a href="javascript:callPage('R');">추천문구</a></div>
						<div class="tab_search">
							검색&nbsp;&nbsp;<input name="searchString" id="searchString" type="text" class="fild_st" style="width:105px;" value="${searchString}" onKeyPress="if (event.keyCode==13){ searchPage();event.returnValue=false}">
						</div>

						<div class="tab_con2" align="center">	
							<c:if test="${!empty emoticonList}">																
								<c:forEach var="item" items="${emoticonList}" varStatus="status" >
									<c:choose>
										<c:when test="${searchColumn eq 'U' }">
											 <div style="float:left; width:180px;">
												<div class="message_tbg">
													<div class="message_top"></div> 
													<div style="width:132px; height:230px; margin-left:1px; color:line-height:10px; overflow-x:hidden; overflow-y:hidden">
														<%-- <a href="#"  onclick="javascript:parent.setMsg('${status.count}','M')"  style="text-decoration:none"> --%>
														<a href="#"  onclick="javascript:parent.setMsg('${item.attachment_path }','mms_send_content_${status.count}','M')"  style="text-decoration:none">
									        				<img class="img_format" src="${item.attachment_path }" id="previewImg_${status.count}" width="128px" height="130px" />
									        				<textarea class="message_f2" name="mms_send_content" id="mms_send_content_${status.count}" rows="" readonly="readonly" >${item.send_content }</textarea>
									        			</a>
									        		</div>
									        		<div class="message_bottom"></div>
									    		</div>
											</div>
										</c:when>
										<c:when test="${searchColumn eq 'R' }">
											<div style="float:left; width:180px;">
												<div class="message_tbg">
													<div class="message_top"></div> 
													<div style="width:132px; height:230px; margin-left:1px; color:line-height:10px; overflow-x:hidden; overflow-y:hidden">
														<a href="#"  onclick="javascript:parent.setMsg('${item.fd_file_path }/${item.fd_file_name}','mms_send_content_${status.count}','M')"  style="text-decoration:none">
										        			<img class="img_format" src="${item.fd_file_path }/${item.fd_file_name}" id="previewImg_${status.count}" width="128px" height="130px" />
										        			<textarea class="message_f2" name="mms_send_content" id="mms_send_content_${status.count}" rows="" readonly="readonly" >${item.fd_content }</textarea>
										        		</a>
									        		</div>
									        		<div class="message_bottom"></div>
									    		</div>
											</div>
										
											<%--
											<div style="float:left; width:185px;">										
												<div style="width:161px; height:20px; padding-top:10px;">
													<a href="#"  onclick="javascript:parent.setMsg('recom_sms_msg_tmp_${status.count}')"  style="text-decoration:none">
														${item.fd_title }
													</a>
													</div>
												<div class="message_tbg">
												<div class="message_top"></div>
													
													<div class="message_t_sms" style="overflow-y:scroll;overflow-x:hidden;">
														<a href="#"  onclick="javascript:parent.setMsg('recom_sms_msg_tmp_${status.count}')"  style="text-decoration:none">
															<div  id="recom_sms_msg_tmp_${status.count}">
															<img src="http://www.mastershop.kr${item.fd_file_path }/${item.fd_file_name}" style="max-width: 100%; height: auto;"/>
															</div>
														</a>
													</div>
													<div class="message_bottom"></div>
												</div>											 
											</div>
											--%>
										</c:when>
										<c:otherwise>
											
										</c:otherwise>
									</c:choose>
								</c:forEach>								
							</c:if>		
							<div style="width:550px; text-align:center; padding-top:20px; clear:both;">
							 	${pageNavi.navi}
							</div> 
						</div>
					</div>
				</c:if>
				<c:if test="${search_type!='M' }">				
					<div>
						<c:choose>
							<c:when test="${searchColumn == 'U'}">
								<c:set var="view_status" value="1"/>								
							</c:when>
							<c:otherwise>
								<c:set var="view_status" value="2"/>
							</c:otherwise>
						</c:choose>
						<div class="tab_left${view_status }"><a href="javascript:callPage('U');">발송문구</a></div>
						<div class="tab_right${view_status }"><a href="javascript:callPage('R');">추천문구</a></div>
						<div class="tab_search">
							검색&nbsp;&nbsp;<input name="searchString" id="searchString" type="text" class="fild_st" style="width:105px;" value="${searchString}" onKeyPress="if (event.keyCode==13){ searchPage();event.returnValue=false}">
						</div>

						<div class="tab_con2">	
							<c:if test="${!empty emoticonList}">																
								<c:forEach var="item" items="${emoticonList}" varStatus="status" >
									<c:choose>
										<c:when test="${searchColumn eq 'U' }">
											<div style="float:left; width:185px;">
												<div style="width:161px; height:20px; padding-top:10px;">&nbsp;</div>
												<div class="message_tbg">
												<div class="message_top"></div>
													<div class="message_t_sms" style="overflow-y:scroll;overflow-x:hidden;">
														<a href="#"  onclick="javascript:parent.setMsg('','recom_sms_msg_tmp_${status.count}','S')"  style="text-decoration:none">
															<div  id="recom_sms_msg_tmp_${status.count}">${item.send_content }</div>
														</a>
													</div>
													<div class="message_bottom"></div>
												</div>
											</div>
										</c:when>
										<c:when test="${searchColumn eq 'R' }">
											<div style="float:left; width:185px;">										
												<div style="width:161px; height:20px; padding-top:10px;">
													<a href="#"  onclick="javascript:parent.setMsg('','recom_sms_msg_tmp_${status.count}','S')"  style="text-decoration:none">
														${item.fd_title }
													</a>
													</div>
												<div class="message_tbg">
												<div class="message_top"></div>
													<div class="message_t_sms" style="overflow-y:scroll;overflow-x:hidden;">
														<%--
														<a href="javascript:parent.setMsg('${item.fd_content }')" style="text-decoration:none" onclick="javascript:alert(''))">
														--%>
														<a href="#"  onclick="javascript:parent.setMsg('','recom_sms_msg_tmp_${status.count}','S')"  style="text-decoration:none">
															<div  id="recom_sms_msg_tmp_${status.count}">${item.fd_content }</div>
														</a>
													</div>
													<div class="message_bottom"></div>
												</div>
												
											</div>
										</c:when>
										<c:otherwise>
											
										</c:otherwise>
									</c:choose>
								</c:forEach>								
							</c:if>		
							<div style="width:550px; text-align:center; padding-top:20px; clear:both;">
							 	${pageNavi.navi}
							</div> 
						</div>
					</div>
					</c:if>
				</form>
				
				<%--
				<div id="recommendContents" style="display:none;"><!-- id sendContents는 추천 리스트 -->
					<div class="tab_left2"><a href="javascript:showSendContents();">발송문구</a></div>
					<div class="tab_right2">추천문구</div>
					<div class="tab_search">검색&nbsp;
						<input name="searchString" id="searchString" type="text" class="fild_st" style="width:105px;" value=${searchString }>
					</div>
					<div class="tab_con2">	
						<c:if test="${!empty recommendMsgList}">																
							<c:forEach var="re_item" items="${recommendMsgList}" varStatus="status" >
								<c:if test="${status.index mod 2 == 0 }">
									<c:set var="conStyle" value="float:left; width:285px;"/>
								</c:if>	
								<c:if test="${status.index mod 2 == 1 }">
									<c:set var="conStyle" value="float:left; width:270px;"/>
								</c:if>										
								<div style="${conStyle}">
									<div style="width:250px; height:20px; margin-left:20px; padding-top:10px;">${re_item.fd_title}</div>
									<div class="message_tbg">
									<div class="message_top"></div>
										<div class="message_t2">${re_item.fd_content }</div>
										<div class="message_bottom"></div>
									</div>
								</div>
							</c:forEach>								
						</c:if>		
						<div style="width:550px; text-align:center; padding-top:20px; clear:both;">
						 	${pageNavi.navi}
						</div> 
					</div>					
				</div>
				
				<!-- 추천문구 활성화시 
					<div style="padding-top:20px;">
					<div class="tab_left2"><a href="#">발송문구</a></div>
					<div class="tab_right2">추천문구</div>
					<div class="tab_search">검색&nbsp;&nbsp;<input name="" type="text" class="fild_st" style="width:115px;"></div>
					<div class="tab_con2"></div>
					</div>
					-->
				 --%>
			</div>
		</div>
	</jsp:body>
</layout:noFrame>