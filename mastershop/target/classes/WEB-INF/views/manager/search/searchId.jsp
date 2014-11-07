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
							
				$("#searchType").change(function(){
					var type = $(this).val();

					if(type =='3'){
						$("#search_12").hide();
						$("#search_3").show();
					}else{
						$("#search_3").hide();
						$("#search_12").show();
					}
										
				});
			});

			function searchChk(){

				var frm = document.searchForm;

				if(frm.searchName.value ==''){
					alert("이름을 입력 해주세요.");
					frm.searchName.focus();
					return;
				}else if(frm.searchTel1.value ==''){
					alert("회선 전화 번호 입력해 주세요.");
					frm.searchTel1.focus();
					return;
				}else if(frm.searchTel2.value ==''){
					alert("회선 전화 번호 입력해 주세요.");
					frm.searchTel2.focus();
					return;
				}else if(frm.searchTel3.value ==''){
					alert("회선 전화 번호 입력해 주세요.");
					frm.searchTel3.focus();
					return;
				}else{

					if(frm.searchType.value =='1'){
						if(frm.searchInfo.value ==''){
							alert("법인 번호를 입력해 주세요.");
							frm.searchInfo.focus();
							return;
						}
						
					}else if(frm.searchType.value =='2'){
						if(frm.searchInfo.value ==''){
							alert("사업자 번호를 입력해 주세요.");
							frm.searchInfo.focus();
							return;
						}
						
					}else if(frm.searchType.value =='3'){
						if(frm.searchYear.value ==''){
							alert("생년월일의 년도를 선택해 주세요.");
							return;
						}else if(frm.searchMonth.value ==''){
							alert("생년월일의 월을 선택해 주세요.");
							return;
						}else if(frm.searchDay.value ==''){
							alert("생년월일의 일을 선택해 주세요.");
							return;
						}else{							
							  if(!$(':input:radio[name=searchGender]:checked').val()) {
	    						alert("성별을 선택해 주세요.");
	    						 return;
	    					  }
						}			
					}
					
					$.ajax({   
	    				url : '/manager/searchId.do',
	    				type : 'post',
	    				cache : false,
	    				data : $("#searchForm").serialize(),
	    				//data : form.serialize(),
	    				dataType : 'json',
	    			   	success:function(data){
	    				   //alert(data.customer_count);
	    				   if(data.rtn_code == '200'){
	    					   
	    					   	if($("#event").val()=='search'){
										$("#chkAuth").show();
				
									  	$("#searchName").attr("readonly",true);
				 					   	$("#searchTel1").attr("readonly",true);
				 					   	$("#searchTel2").attr("readonly",true);
				 					   	$("#searchTel3").attr("readonly",true);
				 					   	$("#searchInfo").attr("readonly",true);
				 					   	$("#searchType option").not(":selected").attr("disabled", "disabled");
				 					   	$("#searchYear option").not(":selected").attr("disabled", "disabled");
				 					   	$("#searchMonth option").not(":selected").attr("disabled", "disabled");	    					   
				 					   	$("#searchDay option").not(":selected").attr("disabled", "disabled");
				 					  	 
				 					   	$("#event").val("proc");

				 						var chkRadio = $(':input:radio[name=searchGender]:checked').attr("id");

										if(chkRadio=='searchGender1'){
											$(':input:radio[id=searchGender2]').attr("disabled", "disabled");
			    						}else{
			    							$(':input:radio[id=searchGender1]').attr("disabled", "disabled");
				    					}
				    					
 					   			}else{
 					   				
 	 					   			$("#idInfo").html(data.member_id);
 	 					   			$("#view_type_1").hide();
 	 					   			$("#view_type_2").show();
 	 					   		}
		    				   
	    					   //location.href = '/home/coinCharge.do';
	    				   }else{	   
	    					   if(data.rtn_code == '201'){
		    					   alert("일치하는 회원 정보가 없습니다. 확인 부탁드립니다.");
			    			   }else if(data.rtn_code == '202'){
		    					   alert("인증번호를  수신할 이동 전화번호가 없습니다.");
			    			   }else if(data.rtn_code == '203'){
		    					   alert("인증번호가 정확하지 않습니다. 다시 확인 부탁드립니다.");
			    			   }else{
			    				   alert("다시 시도해 주세요("+data.rtn_code+")");
				    		}					
	    						
	    				   }
	    			   	}
	    			});
					//					frm.submit();
				}
			}
			/* function disabledExceptNum(){
				var key = window.event ? event.keyCode : event.which;  
				
			     if ((event.shiftKey == false) && ((key  > 47 && key  < 60) || (key  > 95 && key  < 106)
			    		    || key  == 35 || key  == 36 || key  == 37 || key  == 39  // 방향키 좌우,home,end  
			    		    || key  == 8  || key  == 46 ) // del, back space
			    		    ){
			         event.returnValue=true;
			     } else {				     
			        event.returnValue=false;
			     }
			 } */

			// 숫자 확인  
			/*인풋 text에 추가
			*onblur="this.value=this.value.only_number();"
			*/
			
		  	String.prototype.only_number = function(){
		   		return this.replace(/[^0-9]/gi, '');
		  	}
			
		</script>
	</jsp:attribute>
	<jsp:body>
		<div id="view_type_1" >
			<div id="popup" style="height:330px;">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:window.open('about:blank', '_self').close();"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">아이디 찾기</div>
			    </div>
			    <div class="pop_txt">
			    	<form id="searchForm" name="searchForm" action="/manager/searchId.do" method="post">
			    	<input type="hidden" name="event" id="event" value="search">
			        <table width="470" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td colspan="2" height="2" bgcolor="#222222"></td>
			          </tr>
			          <tr>
			            <td width="120" class="pop_tb_tit_l">이름 <font color="#ff9933">*</font></td>
			            <td width="350" class="pop_tb_nor_l">
			            	<input name="searchName" id="searchName" type="text" class="fild_st" style="width:110px;" value="">
			            </td>
			          </tr>
			          <tr>
			            <td class="pop_tb_tit_l">회선전화번호 <font color="#ff9933">*</font></td>
			            <td class="pop_tb_nor_l">
				            <input name="searchTel1" id="searchTel1" type="text" onblur="this.value=this.value.only_number();" class="fild_st" style="width:80px;" maxlength="4" style="ime-mode:disabled" value=""/> - 
				            <input name="searchTel2" id="searchTel2" type="text" onblur="this.value=this.value.only_number();" class="fild_st" style="width:80px;" maxlength="4" style="ime-mode:disabled" value=""/> - 
				            <input name="searchTel3" id="searchTel3" type="text" onblur="this.value=this.value.only_number();" class="fild_st" style="width:80px;" maxlength="4" style="ime-mode:disabled" value=""/>
			            </td>
			          </tr>
			          <tr>
			            <td class="pop_tb_tit_l">
			            	<select name="searchType" id="searchType" >
			            		<option value="1">법인번호</option>
			            		<option value="2">사업자 등록번호</option>
			            		<option value="3">생년월일</option>            		
			            	</select>
			            </td>
			            <td class="pop_tb_nor_l">
			            	<div id="search_12">
			            		<input name="searchInfo" id="searchInfo" type="text" class="fild_st" style="width:98%;" value=""/>
			            	</div>
			            	<div id="search_3" style="display:none;">
			            		<select name="searchYear" id="searchYear" style="width: 80px">
			            			<option value="">년</option>            	
			            			<c:forEach var="i" begin="1940" end="${year}" step="1" varStatus="status" >
			            				<option value="${year-status.count}">${year-status.count}</option>
			            			</c:forEach>
			            		</select>&nbsp;
			            		<select name="searchMonth" id="searchMonth" style="width: 50px">
			            			<option value="">월</option>
			            			<c:forEach var="month" begin="1" end="12" step="1" varStatus="status" >
			            				<c:if test="${month<10 }">
			            					<option value="0${month}">0${month}</option>
			            				</c:if>
			            				<c:if test="${month>=10 }">
			            					<option value="${month}">${month}</option>
			            				</c:if>
			            			</c:forEach>
			            		</select>&nbsp;
			            		<select name="searchDay" id="searchDay" style="width: 50px">
			            			<option value="">일</option>
			            			<c:forEach var="day" begin="1" end="31" step="1" varStatus="status" >
			            				<c:if test="${day<10 }">
			            					<option value="0${day}">0${day}</option>
			            				</c:if>
			            				<c:if test="${day>=10 }">
			            					<option value="${day}">${day}</option>
			            				</c:if>
			            			</c:forEach>
			            		</select>
			            		<br/>
			            		<label id="searchGender1" > 남<input type="radio" name="searchGender" id="searchGender1" value="M"/></label> &nbsp;
			            		<label id="searchGender2" >여<input type="radio" name="searchGender" id="searchGender2" value="F"/></label>
			            	</div>
			            </td>
			          </tr>
			          <tr id="chkAuth" style="display:none;">
			            <td class="pop_tb_tit_l">인증번호</td>
			            <td class="pop_tb_nor_l">            
			            <input name="chkNum" id="chkNum" type="text" class="fild_st" style="width:50%;" >
			            </td>
			          </tr>
			          <tr>
			          	<td class="pop_tb_nor_l" colspan="2">
			          		* 등록된 이동전화번호로 인증번호를 발송합니다.          	
			          	</td>
			          </tr>
			        </table>
			        </form>
			    </div>
			    <div class="pop_btn">
			    	<a href="javascript:searchChk();">
			    		<img src="/resources/images/shopmaster/btn_ok.gif" border="0"/>
			    	</a>
			    </div>
			</div>
		</div>
		<div id="view_type_2" style="display:none;">
			<div id="popup" style="height:145px;">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:window.open('about:blank', '_self').close();"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">아이디 찾기</div>
			    </div>
			    <div class="pop_txt" style="text-align:center;">
    				<div>고객님의 아이디는 <b><span id="idInfo"></span></b> 입니다.</div>
    			</div>			    
			    <div class="pop_btn">
			    	<a href="javascript:window.open('about:blank', '_self').close();">
			    		<img src="/resources/images/shopmaster/btn_ok.gif" border="0"/>
			    	</a>
			    </div>
			</div>
		</div>
	</jsp:body>
</layout:noFrame>