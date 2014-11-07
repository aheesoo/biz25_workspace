<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:noFrame>
	<jsp:attribute name="stylesheet">
		<style type="text/css">
			.img_format img {width:100%;height:100%;}
		</style>
	</jsp:attribute>

	<jsp:attribute name="stylesheet">

	</jsp:attribute>

	<jsp:attribute name="javascript">
		<script langauge="text/javascript">	
			$(document).ready(function() {
				mms_checkByte("mms_send_content", "MMS", 4000);
				//alert('${rtn_code}');
				/* $("#mms_send_content").change(function(){
					for(var i=0 ; i<parent.document.forms.length ; i++){
						if(parent.document.forms[i].id == 'register_form'){
							parent.register_form.attachment_path.value = '${attachment_path}';
							parent.register_form.attachment_file_size.value = '${attachment_file_size}';
							parent.register_form.send_content.value = $('#mms_send_content').val();
						}else if(parent.document.forms[i].id == 'modify_form'){
							parent.modify_form.attachment_path.value = '${attachment_path}';
							parent.modify_form.attachment_file_size.value = '${attachment_file_size}';
							parent.modify_form.send_content.value = $('#mms_send_content').val();
						}
					}
				}); */
				
				$("#mms_send_content").keyup(function(){
					for(var i=0 ; i<parent.document.forms.length ; i++){
						if(parent.document.forms[i].id == 'register_form'){
							parent.register_form.send_content.value = $('#mms_send_content').val();
						}else if(parent.document.forms[i].id == 'modify_form'){
							parent.modify_form.send_content.value = $('#mms_send_content').val();
						}
					}
				});
				
				if('${rtn_code}' == '200'){
					for(var i=0 ; i<parent.document.forms.length ; i++){
						if(parent.document.forms[i].id == 'register_form'){
							parent.register_form.attachment_path.value = '${attachment_path}';
							parent.register_form.attachment_file_size.value = '${attachment_file_size}';
							parent.register_form.send_content.value = $('#mms_send_content').val();
						}else if(parent.document.forms[i].id == 'modify_form'){
							parent.modify_form.attachment_path.value = '${attachment_path}';
							parent.modify_form.attachment_file_size.value = '${attachment_file_size}';
							parent.modify_form.send_content.value = $('#mms_send_content').val();
						}
					}
				}else if('${rtn_code}' == '301'){
					//alert('이미지 등록처리중 오류가 발생하였습니다.');
					parent.alertArea(300, 35, 'center', '오 류', '이미지 등록처리중 오류가 발생하였습니다.');
				}else if('${rtn_code}' == '302'){
					//alert('이미지 용량은 300kb 이하만 등록가능합니다.');
					parent.alertArea(300, 35, 'center', '알 림', '이미지 용량은 300kb 이하만 등록가능합니다.');
				}else if('${rtn_code}' == '303'){
					//alert('이미지 등록은 "jpg"만 가능합니다.');
					parent.alertArea(300, 35, 'center', '알 림', '이미지 등록은 "jpg"만 가능합니다.');
				}
				
				
				$("#fileImg").change(function(){
					if(mms_img_check()){
						upload_mms();
						//$("#upload_form").submit();
						
						readURL(this);
					}else{
						//alert('이미지 등록은 확장자가 "jpg"만 가능합니다."');
						parent.alertArea(300, 35, 'center', '알 림', '이미지 등록은 확장자가 "jpg"만 가능합니다.');
					}
				});
			});
			
			function upload_mms(){
				var frm = document.upload_form;
				frm.submit();
				//$("#upload_form").submit();
			}
			
			function readURL(input) { 
            	if (input.files && input.files[0]) {
					var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
                    reader.onload = function (e) { 
                    //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                        $('#previewImg').attr('src', e.target.result);
                        //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                        //(아래 코드에서 읽어들인 dataURL형식)
                    }                    
                    reader.readAsDataURL(input.files[0]);
                    //File내용을 읽어 dataURL형식의 문자열로 저장
                }
            }
            
          //jpg 인지 체크.
			function mms_img_check() {
				var file_ext = $('#fileImg').val();
				file_ext = file_ext.slice(file_ext.indexOf(".") + 1).toLowerCase();
				//alert(file_ext);
				if(file_ext == "jpg"){ return true; }else{ return false; }
			}
          
			//이미지 삭제
			function deleteMMS() {
				var deletePath = '';
				for(var i=0 ; i<parent.document.forms.length ; i++){
					if(parent.document.forms[i].id == 'register_form'){
						deletePath = parent.register_form.attachment_path.value;
					}else if(parent.document.forms[i].id == 'modify_form'){
						deletePath = parent.modify_form.attachment_path.value;
					}
				}
				
				if(deletePath == ''){
					return false;
				}
				
				if(!confirm("이미지를 삭제 하시겠습니까?")){
					return false;
				}
				
				//추천이미지일 경우 미리보기 이미지 경로와 이미지 명만 지운다.
				var split_str = deletePath.split('/');
				/* alert(deletePath);
				alert(split_str[2] + split_str[3] + split_str[4]); */
				if(split_str.length >= 4) {
					if(split_str[4] == 'recommend'){
						$('#previewImg').attr('src', '');
			   			$('#imgName').val('');
			   			for(var i=0 ; i<parent.document.forms.length ; i++){
							if(parent.document.forms[i].id == 'register_form'){
								parent.register_form.attachment_path.value = '';
								parent.register_form.attachment_file_size.value = '0';
							}else if(parent.document.forms[i].id == 'modify_form'){
								parent.modify_form.attachment_path.value = '';
								parent.modify_form.attachment_file_size.value = '0';
							}
						}
			   			//alert('이미지가 삭제되었습니다.');
			   			parent.alertArea(300, 35, 'center', '알 림', '이미지가 삭제되었습니다.');
			   			return false;
					}
				}
				$.ajax({   
    				url : '/smsManager/uploadMMS.do',
    				type : 'post',
    				cache : false,
    				data : "proc=delete&" + "deletePath=" + deletePath,
    				//data : form.serialize(),
    				dataType : 'json',
    			   	success:function(data){
    			   		if(data.rtn_code == '200'){
    			   			$('#previewImg').attr('src', '');
    			   			$('#imgName').val('');
    			   			for(var i=0 ; i<parent.document.forms.length ; i++){
    							if(parent.document.forms[i].id == 'register_form'){
    								parent.register_form.attachment_path.value = '';
    								parent.register_form.attachment_file_size.value = '0';
    							}else if(parent.document.forms[i].id == 'modify_form'){
    								parent.modify_form.attachment_path.value = '';
    								parent.modify_form.attachment_file_size.value = '0';
    							}
    						}
    			   			//alert('이미지가 삭제되었습니다.');
    			   			parent.alertArea(300, 35, 'center', '알 림', '이미지가 삭제되었습니다.');
    			   		}else if(data.rtn_code == '301'){
    			   			//alert('이미지 삭제도중 오류가 발생하였습니다.');
    			   			parent.alertArea(300, 35, 'center', '오 류', '이미지 삭제도중 오류가 발생하였습니다.');
    			   		}
    			   	}
    			});
			}
			
			//추천 이미지 선택시
			function img_change(src, value){
				if(confirm("선택하신 추천 이미지를 사용 하시겠습니까?")){
					//alert(src);
					var sp_str = src.split('/');
					
					$("#previewImg").attr('src', src);
					$("#imgName").val(sp_str[sp_str.length-1]);
					$('#mms_send_content').val(value);
					mms_strCheck();
					for(var i=0 ; i<parent.document.forms.length ; i++){
						if(parent.document.forms[i].id == 'register_form'){
							parent.register_form.attachment_path.value = '/resources/upload/mcs_upload/recommend/' + sp_str[sp_str.length-1];
							parent.register_form.attachment_file_size.value = '153600'; //150kb
							parent.register_form.send_content.value = value;
						}else if(parent.document.forms[i].id == 'modify_form'){
							parent.modify_form.attachment_path.value = '/resources/upload/mcs_upload/recommend/' + sp_str[sp_str.length-1];
							parent.modify_form.attachment_file_size.value = '153600'; //150kb
							parent.modify_form.send_content.value = value;
						}
					}
				}
			}
			
			//문자 byte값 구하기. 
			function mms_strCheck(){
				mms_checkByte("mms_send_content", "MMS", 4000);
			}
			
			//문자 byte값 연산 함수
			function mms_checkByte (strId, strName, maxLength){ 
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
				    $("#byte_mms_text").css("color", "#FF0000");
			   		$("#byte_mms_text").text(tcount + "/" + maxLength + " byte");
			      	return false;
			    }else{
			  		//document.getElementById("div_view").innerHTML = tcount +"/"+ maxLength+ " byte";
			  		$("#byte_mms_text").css("color", "#222222");
			  		$("#byte_mms_text").text(tcount + "/4000 byte");
			      	return true;
			    }
		  	}
			
		</script>
		${pageNavi.script}
	</jsp:attribute>
	
	<jsp:body>
		<div style="float:left; width:180px;">
			<div class="message_tbg">
				<div class="message_top"></div> 
				<div style="width:132px; height:300px; margin-left:15px; color:line-height:18px; overflow-x:hidden; overflow-y:hidden">
        			<img class="img_format" src="${attachment_path}" id="previewImg" width="132" height="180" />
        			<textarea class="message_f2" name="mms_send_content" id="mms_send_content" onkeyup="mms_strCheck();" rows="" >${mms_send_content}</textarea>
        		</div>
        		<div class="message_bottom"></div>
    		</div>
    		<div style="width: 100px; text-align: right; margin-top: 10px; margin-left: 60px;"><font id="byte_mms_text" style="font-weight:bold;color:#222222;">0/4000 byte</font></div>
		</div>
		<div style="float:left;width:150px; height:208px; margin-top:24px; position:relative;">
			<div style="float:left; width:90px;">
   				<form id="upload_form" name="upload_form" method="post" action="/smsManager/uploadMMS.do" enctype="multipart/form-data">
   					<input type="file" id="fileImg" name="fileImg" style="cursor:pointer; width:115px; height:45px; opacity:.0; filter:alpha(opacity=0); position:absolute; top:0px; left:0px" value="" >
   				</form>
   			</div>
   			<div style="float:left; ">
   				<input type="text" id="imgName" name="imgName" value="${filename}" style="width:110px" readonly="readonly" >
   			</div>
   			<div style="float:left; margin-left:5px;"><img src="/resources/images/shopmaster/btn_popx.gif" onclick="deleteMMS();" style="cursor:pointer;"/></div>
   			<div style="float:left; margin-top:5px;"><img src="/resources/images/shopmaster/btn_imgup.gif" style="cursor:pointer;"/></div>
   			<div style="float:left;width:250px; height:30px; margin-top: 15px"><font class="ca_txt11_999999">※ 파일용량:300kbyte 이하</font></div>
			<!-- <div style="float:left;width:250px; height:30px;"><a href="#"><img src="/resources/images/shopmaster/btn_mms_sample.gif" border="0"/></a></div> -->
		</div>
	</jsp:body>
</layout:noFrame>