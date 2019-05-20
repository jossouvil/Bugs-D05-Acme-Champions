
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="training/manager/edit.do" modelAttribute="training">
	
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
	<acme:textbox path="startDate" code="training.startDate" obligatory="true" placeholder="yyyy/MM/dd HH:mm"/>
	
	<acme:textbox path="endingDate" code="training.endingDate" obligatory="true" placeholder="yyyy/MM/dd HH:mm"/>
	
	<acme:textbox path="place" code="training.place" obligatory="true"/>
	
	<acme:textbox path="description" code="training.description" obligatory="true"/>	
	
	
	<acme:submit name="save" code="training.save" />	

	<acme:cancel code="training.cancel" url="training/manager/list.do" /><br>
	
	<jstl:if test="${training.id != 0}">
		<acme:delete code="training.delete" />
		
	</jstl:if>	
	

</form:form>   
