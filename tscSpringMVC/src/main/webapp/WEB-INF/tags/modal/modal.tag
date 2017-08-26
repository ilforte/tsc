<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true"  type="java.lang.String"%>
<%@ attribute name="title" required="true"  type="java.lang.String"%>
<%@ attribute name="text" required="true"  type="java.lang.String"%>
<%@ attribute name="button_close" required="true"  type="java.lang.String"%>
<%@ attribute name="button_send" required="true"  type="java.lang.String"%>
<%@ attribute name="callback_function" required="true"  type="java.lang.String"%>
<%@ attribute name="formModal" required="true"  type="java.lang.Boolean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:doBody var="modalBody"/>


<div id="${id}" class="modal fade" role="dialog">
 <div class="modal-dialog">
  <div class="modal-content">
   <div class="modal-header">
    <h4 class="modal-title">${title}</h4>
   </div>
   <div class="modal-body">
    <p>${text}</p>
   </div>
   <div class="modal-footer">
	<c:choose>
	  <c:when test="${formModal}">
	  	<button type="button" class="btn btn-default" data-dismiss="${id}">${button_close}</button>
	  </c:when>
	  <c:otherwise>
		<button type="button" class="btn btn-default" data-dismiss="modal">${button_close}</button>
	  </c:otherwise>
	</c:choose>
    <button type="button" class="btn btn-primary" onclick="${callback_function}" >${button_send}</button>
   </div>
   ${modalBody}
  </div>
 </div>
</div>