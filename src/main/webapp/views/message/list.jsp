<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="messages" id="row" requestURI="${requestURI }" pagesize="5">

	<spring:message code="dateFormat" var="format"/>
	<spring:message code="timeFormat" var="formatTime"/>

	<acme:column property="sender.name" titleKey="message.sender" value= "${row.sender.name}: "/>
	
	<acme:column property="recipient.name" titleKey="message.recipient" value= "${row.recipient.name}: "/>
	
	
	<display:column titleKey="message.moment"> 
		<fmt:formatDate type="date" value="${row.moment }" pattern="${format}" />
		<fmt:formatDate type="time" value="${row.moment }" pattern="${formatTime}" />
	</display:column>
	
	<acme:column property="subject" titleKey="message.subject" value= "${row.subject}: "/>
	
	<acme:column property="body" titleKey="message.body" value= "${row.body}: "/>
	
	<acme:column property="priority" titleKey="message.priority" value= "${row.priority}: "/>
	
	<acme:column property="tags" titleKey="message.tags" value= "${row.tags}: "/>
	
	<display:column>
			<a href="message/actor/display.do?messageId=${row.id}&boxId=${boxId}"><spring:message code="message.display"/></a>
	</display:column>
	
</display:table>

	<acme:button name="back" code="message.back" onclick="javascript: relativeRedir('box/actor/list.do');" />