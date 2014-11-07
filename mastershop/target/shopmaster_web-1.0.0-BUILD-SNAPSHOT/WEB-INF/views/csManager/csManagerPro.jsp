<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>

	<jsp:attribute name="stylesheet">
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	<script type="text/javascript">
		${script}
	</script>
	</jsp:attribute>
	
	<jsp:body>
	
    <div id="main_r">

    </div>
	    
	</jsp:body>
</layout:common>