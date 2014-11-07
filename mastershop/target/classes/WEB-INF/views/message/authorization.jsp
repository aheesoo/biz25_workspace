<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language="javascript">
	//alert('인증된 시간이 만료 되었습니다.\n로그인 후, 접근하여 주시기 바랍니다.');
	alert('접속이 만료되었습니다.\n재 로그인 후, 접근하여 주시기 바랍니다.');
	
	top.location.href = '/manager/login.do';
</script>