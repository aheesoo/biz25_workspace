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
				var proc_type = "F";
			$(document).ready(function() {				
				
				$("input[name=pointChoice]").change(function() {
					var choice = parseInt($(this).val());
					$("#chargeTotalPoint").html('');
					$("#chargeTotalMoney").html('');

					var chargeBasePoint = 0;
					var chargeBonusPoint = 0;
					var chargeMoney = 0;				
					switch (choice){
						case 3701 :
							$("#chargeTotalPoint").html('1,000');
							$("#chargeTotalMoney").html('11,000');
							chargeBasePoint = 1000;
							chargeBonusPoint = 0;
							chargeMoney = 11000;
							proc_type= 'T';
							productName = "A 상품";
							/* $('#coinInfoIfrm', parent.document).contents().find('#rechargeCoin').html("1,000");
							$('#coinInfoIfrm', parent.document).contents().find('#bonusCoin').html("0");
							$('#coinInfoIfrm', parent.document).contents().find('#totalCoin').html("1,000");
							$('#coinInfoIfrm', parent.document).contents().find('#payMoney').text("11,000"); */
							break;
						case 3702 :
							$("#chargeTotalPoint").html('3,150');
							$("#chargeTotalMoney").html('33,000');
							chargeBasePoint = 3000;
							chargeBonusPoint = 150;
							chargeMoney = 33000;
							proc_type= 'T';
							productName = "B 상품";
							
							/* $('#coinInfoIfrm', parent.document).contents().find('#rechargeCoin').html("3,000");
							$('#coinInfoIfrm', parent.document).contents().find('#bonusCoin').html("150");
							$('#coinInfoIfrm', parent.document).contents().find('#totalCoin').html("3,150");
							$('#coinInfoIfrm', parent.document).contents().find('#payMoney').text("33,000");
							 */
							break;
						case 3703 :
							$("#chargeTotalPoint").html('5,350');
							$("#chargeTotalMoney").html('55,000');
							chargeBasePoint = 5000;
							chargeBonusPoint = 350;
							chargeMoney = 55000;
							proc_type= 'T';
							productName = "C 상품";

							/* $('#coinInfoIfrm', parent.document).contents().find('#rechargeCoin').html("5,000");
							$('#coinInfoIfrm', parent.document).contents().find('#bonusCoin').html("350");
							$('#coinInfoIfrm', parent.document).contents().find('#totalCoin').html("5,350");
							$('#coinInfoIfrm', parent.document).contents().find('#payMoney').text("55,000");
							 */
							break;
						case 3704 :
							$("#chargeTotalPoint").html('11,000');
							$("#chargeTotalMoney").html('110,000');
							chargeBasePoint = 10000;
							chargeBonusPoint = 1000;
							chargeMoney = 110000;
							proc_type= 'T';
							productName = "D 상품";

							/* $('#coinInfoIfrm', parent.document).contents().find('#rechargeCoin').html("10,000");
							$('#coinInfoIfrm', parent.document).contents().find('#bonusCoin').html("1,000");
							$('#coinInfoIfrm', parent.document).contents().find('#totalCoin').html("11,000");
							$('#coinInfoIfrm', parent.document).contents().find('#payMoney').text("110,000");
							 */
							break;
						default :
							$("#chargeTotalPoint").html('0');
							$("#chargeTotalMoney").html('0');

							/* $('#coinInfoIfrm', parent.document).contents().find('#rechargeCoin').html("0");
							$('#coinInfoIfrm', parent.document).contents().find('#bonusCoin').html("0");
							$('#coinInfoIfrm', parent.document).contents().find('#totalCoin').html("0");
							$('#coinInfoIfrm', parent.document).contents().find('#payMoney').text("0");
 							*/
							proc_type = 'F';							
							chargeBasePoint = 0;
							chargeBonusPoint = 0;
							chargeMoney = 0;
					}

					$("#chargeBasePoint").val(chargeBasePoint);
					$("#chargeBonusPoint").val(chargeBonusPoint);
					$("#chargeMoney").val(chargeMoney);
					$("#goodname").val(productName);					
					$("#call_page").val("sms");
					$("#product_code").val(choice);
					
					/* $('#coinInfoIfrm', parent.document).contents().find('#chargeBasePoint').val(chargeBasePoint);
					$('#coinInfoIfrm', parent.document).contents().find('#chargeBonusPoint').val(chargeBonusPoint);
					$('#coinInfoIfrm', parent.document).contents().find('#chargeMoney').val(chargeMoney);
					$('#coinInfoIfrm', parent.document).contents().find('#goodname').val(choice);
					$('#coinInfoIfrm', parent.document).contents().find('#call_page').val("sms"); */
				});						

				/* $("#chargeOpenPop").click(function(){


					if(proc_type =='F'){					
						alert('충전 포인트를 확인해 주세요.');
						return;						
					}else{

						
						//parent.openPopDiv('popCoinInfo');
						//parent.closePopDiv('popCoin');
						//$("#popCoinInfo").show();
						//	
					}
					
					
				}); */
				
			});
		
			//ID 값으로 검색하여 창 숨김처리
			function openPopDiv(dName){
				proc_type = 'F';
				$('#'+dName).show();
			}
			
			//ID 값으로 검색하여 창 숨김처리
			function closePopDiv(dName){				
				$('#'+dName).hide();
			}

			function resetPage(){				
				proc_type ='F';
			}
			
			function reqPointCharge(){
				var frm = document.coinFrm;

				if(proc_type =='F'){					
					alert('충전 포인트를 확인해 주세요.');
					return;						
				}else{

					var frm = document.coinFrm;

					var w = 540;
					var h = 400;
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

				
				/* var basePoint =frm.chargeBasePoint.value;
				var bonusPoint =frm.chargeBonusPoint.value;
				var money = frm.chargeMoney.value ;

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
	    					   	if(data.result_total_coin !='' && data.result_recharge_coin !='' && data.result_base_coin !=''){

	    							var total_coin = commaNum(data.result_total_coin);
	    							var recharge_coin = commaNum(data.result_recharge_coin);
	    							var base_coin = commaNum(data.result_base_coin);
	    							
		    					   	
	    					   		parent.childPopCoin(total_coin,recharge_coin,base_coin);
								}
	    					   parent.closePopDiv('popCoin');
	    				   }else{	    					
	    						alert("충전 중 오류가 발생하였습니다. 다시 시도해 주세요("+data.rtn_code+")");
	    				   }
	    			   	}
	    			});
				}else{
					alert('충전 포인트를 확인해 주세요.');
					return;
				} */
			}

			function popupMethdProc1(){				
				parent.childPopCoin($("#total_coin").val(),$("#recharge_coin").val(),$("#base_coin").val());
			}
			/* 숫자 콤마 처리*/
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
		<div id="popup99" style="height:250px;">
			<div class="pop_top">
				<div class="pop_x99"><a href="javascript:parent.closePopDiv('popCoin');resetPage();">
					<img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a>
				</div>
				<div class="pop_tit">코인 충전</div>
			</div>
			
			<div style="overflow:hidden">
				
				<form id="coinFrm" name="coinFrm" action="">
					<input type="hidden" name="event" id="event"value="proc"/>
					<input type="hidden" name="chargeBasePoint" id="chargeBasePoint"value="0"/>
					<input type="hidden" name="chargeBonusPoint" id="chargeBonusPoint"value="0"/>
					<input type="hidden" name="chargeMoney" id="chargeMoney"value="0"/>
					<input type="hidden" name="goodname" id="goodname"value="1"/>
					<input type="hidden" name="call_page" id="call_page"value="sms"/>
					<input type="hidden" name="product_code" id="product_code"value=""/>
					<input type="hidden" name="total_coin" id="total_coin" value="0"/>
					<input type="hidden" name="recharge_coin" id="recharge_coin" value="0"/>
					<input type="hidden" name="base_coin" id="base_coin" value="0"/>
					
				</form>					
				 
				 
				<table width="900" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="30" class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3701" /></td>
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
									<a href="javascript:reqPointCharge();">
										<img src="/resources/images/shopmaster/btn_save_ok.gif" border="0"/>
									</a>
									<a href="#" id="chargeOpenPop" >
									--%>
									<a href="javascript:reqPointCharge();">
										<img src="/resources/images/shopmaster/btn_save_ok.gif" border="0"/>
									</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3702" /></td>
						<td class="tb_nor_l40"><font class="point_txt13">30,000 원</font></td>
						<td class="tb_nor_l40">3,000 Coin <font color="#FF0000">+ 보너스 150 Coin</font></td>
						<td class="tb_nor_r40">3,150 Coin</td>
					</tr>
					<tr>
						<td class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3703" /></td>
						<td class="tb_nor_l40"><font class="point_txt13">50,000 원</font></td>
						<td class="tb_nor_l40">5,000 Coin <font color="#FF0000">+ 보너스 350 Coin</font></td>
						<td class="tb_nor_r40">5,350 Coin</td>
					</tr>
					<tr>
						<td class="tb_nor_c40"><input type="radio" name="pointChoice" id="pointChoice" value="3704"/></td>
						<td class="tb_nor_l40"><font class="point_txt13">100,000 원</font></td>
						<td class="tb_nor_l40">10,000 Coin <font color="#FF0000">+ 보너스 1,000 Coin</font></td>
						<td class="tb_nor_r40">11,000 Coin</td>
					</tr>
				</table>
			</div>	
		</div>
	</jsp:body>
</layout:noFrame>