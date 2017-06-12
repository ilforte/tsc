<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true"  type="java.lang.String"%>
<%@ attribute name="type" required="true"  type="java.lang.String"%>
<%@ attribute name="cssClass" required="true"  type="java.lang.String"%>

<input type="${type}" class="${cssClass}" id="${id}" name="${id}" >
