<%@ page language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="">
<h3>Chart</h3>
<form:form method="post" action="/DataFeeds/admin/charts/updateChart/${idProject}" commandName="chart">

<form:input path="idFeed" type="hidden" value="${chart.idFeed}"/>
<form:input path="idChart"type="hidden" value="${chart.idChart}"/>
    <table>
    <tr>
        <td><form:label path="type"><spring:message code="label.chart.type"/></form:label></td>
        <td><form:input path="type" value="${chart.type}"/></td> 
    </tr>
    <tr>
        <td><form:label path="uriName"><spring:message code="label.chart.uriName"/></form:label></td>
        <td><form:input path="uriName" value="${chart.uriName}"/></td>
    </tr>
    <tr>
        <td><form:label path="shortName"><spring:message code="label.chart.shortName"/></form:label></td>
        <td><form:input path="shortName" value="${chart.shortName}"/></td>
    </tr>
    <tr>
        <td><form:label path="xMin"><spring:message code="label.chart.xMin"/></form:label></td>
        <td><form:input path="xMin" value="${chart.xMin}"/></td>
    </tr>
    <tr>
        <td><form:label path="xMax"><spring:message code="label.chart.xMax"/></form:label></td>
        <td><form:input path="xMax" value="${chart.xMax}"/></td>
    </tr>
    <tr>
        <td><form:label path="time"><spring:message code="label.chart.time"/></form:label></td>
        <td><form:input path="time" value="${chart.time}"/></td>
    </tr>
    <tr>
        <td><form:label path="minTime"><spring:message code="label.chart.minTime"/></form:label></td>
        <td><form:input path="minTime" value="${chart.minTime}"/></td>
    </tr> 
    <tr>
        <td><form:label path="maxTime"><spring:message code="label.chart.maxTime"/></form:label></td>
        <td><form:input path="maxTime" value="${chart.maxTime}"/></td>
    </tr> 
    <tr>
        <td><form:label path="numValues"><spring:message code="label.chart.numValues"/></form:label></td>
        <td><form:input path="numValues" value="${chart.numValues}"/></td>
    </tr>    
    <tr>
        <td><form:label path="feedColumn"><spring:message code="label.chart.feedColumn"/></form:label></td>
        <td><form:input path="feedColumn" value="${chart.feedColumn}"/></td>
    </tr>    
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.chart.editChart"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
