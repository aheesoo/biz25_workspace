<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

	<script langauge="text/javascript">	
		${script}
		
		//1개월 이상 체납시
		function payMsg(fd_price, pay_price){
			var noPayPrice = fd_price - pay_price;
			alert("고객님 미납요금("+commaNum(noPayPrice)+"원)이 발생하여 알려드립니다.\n\n문자 관리 서비스를 이용하실 수 없습니다.\n\n더 자세한 사항은 고객센터(1899-1431)으로 문의 하세요");
			history.back(-1);
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
