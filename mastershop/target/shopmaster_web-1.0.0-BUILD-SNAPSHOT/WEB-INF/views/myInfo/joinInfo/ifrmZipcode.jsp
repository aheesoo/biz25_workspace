<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:noFrame> 

	<jsp:attribute name="stylesheet">		
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	
		<script langauge="text/javascript">	

			${script}
			
		
			//ID 값으로 검색하여 창 숨김처리
			function openPopDiv(dName){
				$('#'+dName).show();
			}
			
			//ID 값으로 검색하여 창 숨김처리
			function closePopDiv(dName){
				$('#'+dName).hide();
			}

			
			//수정화면 이동
			function changeInfo(){

				$("#mainForm").submit();
				
			}

		
			function showZipDoro(){
				$('#zipGibun').hide();
				$('#zipDoro').show();
				$('#doList').show();
				$("#searchGubun").val("DORO");
			}
			
			function showZipGibun(){
				$('#zipDoro').hide();
				$('#doList').hide();
				$('#zipGibun').show();
				$("#searchGubun").val("JEBUN");
			}
			
			function searchZipCode(){
				$("#postForm").submit();
			}
			
			$(document).ready(function(){
				
				if( $("#searchGubun").val() == "DORO"){
					showZipDoro();
					
				}else if( $("#searchGubun").val() == "JEBUN"){
					showZipGibun();
				}
			});
			
		</script>

	</jsp:attribute>
	
	<jsp:body>

		<form name="postForm" id="postForm" action="/myInfo/zipSearch.do" method="post">
			<input type="hidden" name="searchGubun" id="searchGubun" value="${searchGubun}">
			<div id="popup" style="height:500px;">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:parent.closePopDiv('popZipcode')"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">우편번호 검색</div>
			    </div>
			    <div class="pop_txt">
			    	<div>
			    	<input name="searchValue" id="searchValue" value="${searchValue }" type="text" class="fild_st" style="width:200px;">&nbsp;
			    	
	                <select id="doList" name="doList" style="display:none" class="listmenu_st" style="width:90px;">
						<option value="seoul" <c:if test="${doList}=='seoul'}">selected</c:if> >서울특별시</option>
						<option value="busan" <c:if test="${doList}=='busan'}">selected</c:if> >부산광역시</option>
						<option value="incheon" <c:if test="${doList}=='incheon'}">selected</c:if> >인천광역시</option>
						<option value="daegu" <c:if test="${doList}=='daegu'}">selected</c:if> >대구광역시</option>
						<option value="daejeon" <c:if test="${doList}=='daejeon'}">selected</c:if> >대전광역시</option>
						<option value="gwangju" <c:if test="${doList}=='gwangju'}">selected</c:if> >광주광역시</option>
						<option value="ulsan" <c:if test="${doList}=='ulsan'}">selected</c:if> >울산광역시</option>
						<option value="sejong" <c:if test="${doList}=='sejong'}">selected</c:if> >세종특별자치시</option>
						<option value="gyeonggi" <c:if test="${doList}=='gyeonggi'}">selected</c:if> >경기도</option>
						<option value="gyeongsangbuk" <c:if test="${doList}=='gyeongsangbuk'}">selected</c:if> >경상북도</option>
						<option value="gyeongsangnam" <c:if test="${doList}=='gyeongsangnam'}">selected</c:if> >경상남도</option>
						<option value="jeollabuk" <c:if test="${doList}=='jeollabuk'}">selected</c:if> >전라북도</option>
						<option value="jeollanam" <c:if test="${doList}=='jeollanam'}">selected</c:if> >전라남도</option>
						<option value="chungcheongbuk" <c:if test="${doList}=='chungcheongbuk'}">selected</c:if> >충청북도</option>
						<option value="chungcheongnam" <c:if test="${doList}=='chungcheongnam'}">selected</c:if> >충청남도</option>
						<option value="jeju" <c:if test="${doList}=='jeju'}">selected</c:if> >제주특별자치도</option>
	                </select>
			    		<a href="javascript:searchZipCode();"><img src="/resources/images/shopmaster/btn2_search.gif" border="0" align="absmiddle" /></a></div>
			    	<div style="padding-top:8px;">지번(동,읍,면,리)　 ex) 당산동, 동평</div>
			    	<div style="padding-top:5px;">도로명(새주소)　　 ex) 신대방길</div>
			        
			        <!-- 지번주소 활성화시 -->
			        <div id="zipGibun" style="display:block;padding-top:20px;">
			        	<div class="tab_left1">지번주소</div>
			        	<div class="tab_right1"><a href="javascript:showZipDoro();">도로명주소</a></div>
			        	<div class="tab_con">
			            	<table width="420" border="0" cellspacing="0" cellpadding="0" style="margin:20px;">
			            	
								<c:if test="${!empty addrList}">
								<c:forEach var="addr" items="${addrList}" varStatus="status" >
									<tr>
			                    		<td class="pop_tb_nor_l"><font style="font-size:11px">
			                    			<a href="javascript:parent.setPostNum('${addr.addr1} ${addr.addr2} ${addr.addr3} ${addr.addr4} ${addr.addr5}','${addr.post}');">${addr.addr1} ${addr.addr2} ${addr.addr3} ${addr.addr4} ${addr.addr5} ${addr.bunji}</a>
			                    			</font></td>
			                    		<td width="55" class="pop_tb_nor_l" align="right"><font style="font-size:11px">${addr.post}</font></td>
			                  		</tr>
								</c:forEach>
								</c:if>
								<c:if test="${!empty searchValue && empty addrList}">
									<tr>
			                    		<td colspan="2" class="pop_tb_nor_l"><font style="font-size:11px">검색 결과가 없습니다.</font></td>
			                  		</tr>
								</c:if>
								
							</table>
			            </div>
			        </div>
			        <!-- 도로명주소 활성화시 -->
			        <div id="zipDoro" style="display:none;padding-top:20px;">
			        	<div class="tab_left2"><a href="javascript:showZipGibun();">지번주소</a></div>
			        	<div class="tab_right2">도로명주소</div>
			        	<div class="tab_con">
			            	<table width="420" border="0" cellspacing="0" cellpadding="0" style="margin:20px;">
			                  
								<c:if test="${!empty addrDoroList}">
								<c:forEach var="addrDoro" items="${addrDoroList}" varStatus="status" >
									<tr>
			                    		<td class="pop_tb_nor_l"><font style="font-size:11px">
			                    			<a href="javascript:parent.setPostNum('${addrDoro.si_do} ${addrDoro.si_gun_gu} ${addrDoro.street_name} ${addrDoro.building1}','${addrDoro.post_num}');">${addrDoro.si_do} ${addrDoro.si_gun_gu} ${addrDoro.street_name} ${addrDoro.building1} (${addrDoro.beopjeong})</a>
			                    			</font></td>
			                    		<td width="55" class="pop_tb_nor_l" align="right"><font style="font-size:11px">${addrDoro.post_num}</font></td>
			                  		</tr>
								</c:forEach>
								</c:if>
								<c:if test="${!empty searchValue && empty addrDoroList}">
									<tr>
			                    		<td colspan="2" class="pop_tb_nor_l"><font style="font-size:11px">검색 결과가 없습니다.</font></td>
			                  		</tr>
								</c:if>
			                </table>
			            </div>
			        </div>

			    </div>
			</div>
			</form>
			
			
	</jsp:body>
</layout:noFrame>