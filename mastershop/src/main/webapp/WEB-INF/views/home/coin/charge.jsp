<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:left_common>
	<jsp:attribute name="stylesheet">		
	</jsp:attribute>

	<jsp:attribute name="javascript">
		<script type="text/javascript">
			var proc_type = "F";
			$(document).ready(function(){
				
				$("input[name=pointChoice]").change(function() {
					var choice = parseInt($(this).val());
					$("#chargeTotalPoint").html('');
					$("#chargeTotalMoney").html('');

					var chargeBasePoint = 0;
					var chargeBonusPoint = 0;
					var chargeMoney = 0;	
					var productName = "";		
					switch (choice){
						case 3701 :
							$("#chargeTotalPoint").html('1,000');
							$("#chargeTotalMoney").html('11,000');
							chargeBasePoint = 1000;
							chargeBonusPoint = 0;
							chargeMoney = 11000;
							proc_type= 'T';
							productName = "마스터샵 충전 A형";
							break;
						case 3702 :
							$("#chargeTotalPoint").html('3,030');
							$("#chargeTotalMoney").html('33,000');
							chargeBasePoint = 3000;
							chargeBonusPoint = 30;
							chargeMoney = 33000;
							proc_type= 'T';
							productName = "마스터샵 충전 B형";
								
							break;
						case 3703 :
							$("#chargeTotalPoint").html('5,100');
							$("#chargeTotalMoney").html('55,000');
							chargeBasePoint = 5000;
							chargeBonusPoint = 100;
							chargeMoney = 55000;
							proc_type= 'T';
							productName = "마스터샵 충전 C형";
							break;
						case 3704 :
							$("#chargeTotalPoint").html('10,500');
							$("#chargeTotalMoney").html('110,000');
							chargeBasePoint = 10000;
							chargeBonusPoint = 500;
							chargeMoney = 110000;
							proc_type= 'T';
							productName = "마스터샵 충전 D형";
							
							break;
						default :
							$("#chargeTotalPoint").html('0');
							$("#chargeTotalMoney").html('0');
							

							proc_type = 'F';
							chargeBasePoint = 0;
							chargeBonusPoint = 0;
							chargeMoney = 0;
					}

					$("#chargeBasePoint").val(chargeBasePoint);
					$("#chargeBonusPoint").val(chargeBonusPoint);
					$("#chargeMoney").val(chargeMoney);
					$("#goodname").val(productName);					
					$("#call_page").val("coin");
					$("#product_code").val(choice);
					
					<%--
					$('#coinInfoIfrm').contents().find('#chargeBasePoint').val(chargeBasePoint);
					$('#coinInfoIfrm').contents().find('#chargeBonusPoint').val(chargeBonusPoint);
					$('#coinInfoIfrm').contents().find('#chargeMoney').val(chargeMoney);
					$('#coinInfoIfrm').contents().find('#goodname').val(choice);
					$('#coinInfoIfrm').contents().find('#call_page').val("coin");
					--%>
				});	

				$("#chargeOpenPop").click(function(){

	
					if(proc_type =='F'){					
						alert('충전 포인트를 확인해 주세요.');
						return;						
					}else{

						
						//$("#popCoinInfo").show();	
					}
					
					
				});

									
			});

			function reqPointCharge(){
				if(proc_type =='F'){					
					alert('충전 포인트를 확인해 주세요.');
					return;						
				}else{

					var frm = document.coinFrm;

					var w = 520;
					var h = 390;
					var x = ( screen.width - w)/2 - 10;
					var y = ( screen.height - h)/2 - 10;
					var url = "/coin/coin_chargeInfo.do";
					var exp = "width=" + w + ",height=" + h + ",top=" + y + ",left=" + x + ",status=no,resizable=yes,toolbar=no,scrollbars=yes";
					var title = "chargePop";
					
					window.open(url, title, exp);
					
					frm.target = title;
					frm.action = url;
					frm.method = "post";
					frm.submit();
				}
				
				
				<%--
				if(basePoint!=0 || money!=0){					
					$.ajax({   
	    				url : '/home/coinCharge.do',
	    				type : 'post',
	    				cache : false,
	    				data : $("#coinFrm").serialize(),
	    				//data : form.serialize(),
	    				dataType : 'json',
	    			   	success:function(data){
	    				   //alert(data.customer_count);
	    				   if(data.rtn_code == '200'){
	    					   alert('코인 충전이 완료 되었습니다.');
	    					   location.href = '/myInfo/myPointCharge.do';
	    				   }else{	    					
	    						alert("충전 중 오류가 발생하였습니다. 다시 시도해 주세요("+data.rtn_code+")");
	    				   }
	    			   	}
	    			});
				}else{
					alert('충전 포인트를 확인해 주세요.');
					return;
				}
				--%>
			}

			//ID 값으로 검색하여 창 숨김처리
			function openPopDiv(dName){
				$('#'+dName).show();
			}
			
			//ID 값으로 검색하여 창 숨김처리
			function closePopDiv(dName){				
				$('#'+dName).hide();
			}

		</script>
	</jsp:attribute>

	<jsp:body>
	<div id="main_r">
		<div class="contents_area">
			<div class="sub_tit">
				<div style="float:left;"><img src="/resources/images/shopmaster/tit_savepoint.gif" border="0"/></div>
				<div class="sub_now">홈 &gt; <font class="sub_now_t">포인트 충전</font></div>
			</div>
			<div class="sub_tit_dot"></div>
			<div class="sub_contents">
				
				<form id="coinFrm" name="coinFrm" action="">
					<input type="hidden" name="event" id="event"value="proc"/>
					<input type="hidden" name="chargeBasePoint" id="chargeBasePoint"value="0"/>
					<input type="hidden" name="chargeBonusPoint" id="chargeBonusPoint"value="0"/>
					<input type="hidden" name="chargeMoney" id="chargeMoney"value="0"/>
					<input type="hidden" name="goodname" id="goodname"value="1"/>
					<input type="hidden" name="call_page" id="call_page"value="1"/>
					<input type="hidden" name="product_code" id="product_code"value=""/>
				</form>
				
				<div class="tb_top"></div>
				<%--
				<!-- 코인 PG 호출전 레이어 -->										
				<div id="popCoinInfo" style="display:none;position:absolute;top:170px;left:20px;width:98%;height:0px;">
					<iframe id="coinInfoIfrm" name="coinInfoIfrm" src="/coin/coin_chargeInfo.do" frameborder="0" style="display:block;position:absolute;top:0px;left:300px;width:520px;height:390px;"></iframe>
				</div>
				 --%>				
				<div>
					<table width="930" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="60" class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3701" /></td>
							<td width="100" class="tb_nor_l40"><font class="point_txt13">10,000 원</font></td>
							<td width="300" class="tb_nor_l40">1,000 Coin</td>
							<td width="150" class="tb_nor_r40">1,000 Coin</td>
							<td rowspan="4" class="tb_nor_l40">
								<div class="point_box">
									<div class="point_ttxt">충전될 포인트</div>
									<div class="point_strong">
										<font color="#FF0000"><span id="chargeTotalPoint">0</span></font> Coin
									</div>
									<div class="point_ttxt">결제금액</div>
									<div class="point_strong">
										<span id="chargeTotalMoney">0</span>  원
									</div>
									<div class="point_btn"><!--520x360-->
										<%--										
										<a href="javascript:reqPointCharge();"><img src="/resources/images/shopmaster/btn_save_ok.gif" border="0"/></a>
										<a href="javascript:javascript:openPopDiv('popCoinInfo');">
										<a href="#" id="chargeOpenPop" >
											<img src="/resources/images/shopmaster/btn_save_ok.gif" border="0"/>
										</a>
										 --%>
										 
										 <a href="javascript:reqPointCharge();"><img src="/resources/images/shopmaster/btn_save_ok.gif" border="0"/></a>
										
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3702" /></td>
							<td class="tb_nor_l40"><font class="point_txt13">30,000 원</font></td>
							<td class="tb_nor_l40">3,000 Coin <font color="#FF0000">+ 보너스 30 Coin</font></td>
							<td class="tb_nor_r40">3,030 Coin</td>
						</tr>
						<tr>
							<td class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3703" /></td>
							<td class="tb_nor_l40"><font class="point_txt13">50,000 원</font></td>
							<td class="tb_nor_l40">5,000 Coin <font color="#FF0000">+ 보너스 100 Coin</font></td>
							<td class="tb_nor_r40">5,100 Coin</td>
						</tr>
						<tr>
							<td class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3704"/></td>
							<td class="tb_nor_l40"><font class="point_txt13">100,000 원</font></td>
							<td class="tb_nor_l40">10,000 Coin <font color="#FF0000">+ 보너스 500 Coin</font></td>
							<td class="tb_nor_r40">10,500 Coin</td>
						</tr>
					</table>
				</div>
				<div style="height:28px; margin-top:35px;"><font style="font-size:14px; color:#222222; font-weight:bold">이용안내</font></div>
				<div style="height:16px;">
					<div style="float:left;padding-left:5px;">·&nbsp;포인트 제도</div>
					<div style="float:right;"><font class="ca_txt11_999999">부가세 별도</font></div>
				</div>
				<div class="tb_top"></div>
				<div>
					<table width="930" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="200" class="tb_tit_c">구분</td>
							<td width="150" class="tb_tit_c">SMS</td>
							<td width="150" class="tb_tit_c">LMS</td>
							<td width="150" class="tb_tit_c">MMS</td>
							<td class="tb_tit_c">비고</td>
						</tr>
						<tr>
							<td class="tb_nor_c">포인트</td>
							<td class="tb_nor_c">2 Coin</td>
							<td class="tb_nor_c">5 Coin</td>
							<td class="tb_nor_c">20 Coin</td>
							<td rowspan="2" class="tb_nor_c">1 Coin = 10 원</td>
						</tr>
						<tr>
							<td class="tb_nor_c">실제 비용</td>
							<td class="tb_nor_c">20 원</td>
							<td class="tb_nor_c">50 원</td>
							<td class="tb_nor_c">200 원</td>
						</tr>
					</table>
				</div>
				<div style="margin:15px 0px 0px 5px; color:#666666;float:left;">
					<div style="margin-bottom:5px;">·&nbsp;모든 이용권 금액은 부가세 10%별도입니다.</div>
					<div style="margin-bottom:5px;">·&nbsp;잔여 포인트는 다음 달로 자동 이월됩니다.</div>
					<div style="margin-bottom:5px;">·&nbsp;월정액 상품의 사용기간은 대한민국표준시간을 기준으로 합니다.</div>
					<div style="margin-bottom:5px;">·&nbsp;정기 및 임시 점검시간에는 서비스가 중지될 수 있습니다.</div>
					<div style="margin-bottom:5px;">·&nbsp;이용약관에 명시된 이용용도가 아닌 다른 용도로 사용할 경우 서비스가 제한될 수 있습니다.</div>
				</div>
				<div style="margin:15px 0px 0px 5px; color:#666666;float:right;">
					<a href="/home/main.do"><img src="/resources/images/shopmaster/btn_close.gif" border="0px" /></a>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
	</jsp:body>
</layout:left_common>