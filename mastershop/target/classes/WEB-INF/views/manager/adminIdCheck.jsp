<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MasterShop</title>	
	<link rel="stylesheet" type="text/css" href="/resources/css/admin.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/plugins/layout/layout-default-latest.css">
    <style type="text/css">
    	.btnArea{text-align:center;padding:5px 0}
    </style>
    <script type="text/javascript">
    	function useId(){
    		opener.document.getElementById('returnChk').value="true";
    		opener.document.getElementById('admin_id').readOnly="true";
    		self.close();
    	}
    </script>
</head>
<body>
	<table cellpadding="0" cellspacing="0" style="width:100%;border:solid 2px #dedede">
		<tr>
			<td style="text-align:center">
				<div style="width:100%;height:100px;padding:20px 0">
					<c:if test="${chkId=='t'}">
						입력하신 아이디 ${admin_id} 는<br/>사용 가능한 아이디 입니다.<br/><br/>사용하시겠습니까?
						</div>
						<div class="btnArea" style="width:100%;height:50px">
						<div class="btnArea">
							<input type="button" class="button" value="완료" style="width:50px" onclick="useId();" />
							<input type="button" class="button" value="닫기" style="width:50px" onclick="self.close()" />
						</div>

					</c:if>
					<c:if test="${chkId=='f'}">
						입력하신 아이디 ${admin_id} 는<br/>이미 사용중 또는 부적합한 아이디 입니다.<br/>
						</div>
						<div class="btnArea" style="width:100%;height:50px">
						<div class="btnArea">
							<input type="button" class="button" value="닫기" style="width:50px" onclick="self.close()" />
						</div>
					</c:if>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>