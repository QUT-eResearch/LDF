<%@ page language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <c:set var="success" value="${param.success}"/>

<div class="listTable">
<p><a class="grayButton" href="${contextPath }/admin/listProjects">Projects</a></p>
<c:if  test="${!empty projectList}">
<table>
<tr>
	<td>Name</td>
	<td>Details</td>
	<td>latitude</td>
	<td>longitude</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<c:forEach items="${projectList}" var="project">
	<tr>
		<td>${project.name}</td>
		<td>${project.details}</td>
		<td>${project.latitude}</td>
		<td>${project.longitude}</td>
		<td><a class="bButton" href="javascript:editProject(${project.idProject},'updateProject')"><spring:message code="label.app.edit"/></a></td>
		<td class="center"><a class="bButton" href="deleteProject/${project.idProject}"><spring:message code="label.app.delete"/></a></td>
		<td class="center"><a class="bButton" href="feeds/listFeeds?idProject=${project.idProject}"><spring:message code="label.app.view"/></a></td>
	</tr>
</c:forEach>
</table>
</c:if>
</div>

<div class="center">
<a class="aButton" href="javascript:dialog('addProject', null)"><spring:message code="label.project.addproject"/> </a>
</div>

<div id="addProject" style="display:none">
<form:form class="currentFormAddProject" method="post" action="" commandName="project">
 
    <table>
    <tr>
        <td><form:label path="name"><spring:message code="label.project.name"/></form:label></td>
        <td><form:input path="name" /></td> 
    </tr>
    <tr>
        <td><form:label path="latitude"><spring:message code="label.project.latitude"/></form:label></td>
        <td><form:input path="latitude" /></td>
    </tr>
    <tr>
        <td><form:label path="longitude"><spring:message code="label.project.longitude"/></form:label></td>
        <td><form:input path="longitude" /></td>
    </tr>
    <tr>
        <td><form:label path="details"><spring:message code="label.project.details"/></form:label></td>
        <td><form:input path="details" /></td>
    </tr>
    <tr>
        <td class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.project.addproject"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
<div id="updateProject" style="display:none">
<form:form class="currentFormAddProject" method="post" action="" commandName="project">
 	<form:input path="idProject" type="hidden"/>
    <table>
    <tr>
        <td><form:label path="name"><spring:message code="label.project.name"/></form:label></td>
        <td><form:input path="name" /></td> 
    </tr>
    <tr>
        <td><form:label path="latitude"><spring:message code="label.project.latitude"/></form:label></td>
        <td><form:input path="latitude" /></td>
    </tr>
    <tr>
        <td><form:label path="longitude"><spring:message code="label.project.longitude"/></form:label></td>
        <td><form:input path="longitude" /></td>
    </tr>
    <tr>
        <td><form:label path="details"><spring:message code="label.project.details"/></form:label></td>
        <td><form:input path="details" /></td>
    </tr>
    <tr>
        <td class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.app.update"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
<div id="error" style="display:none"><p>Could not perform operation, please delete nested elements </p></div>
<script>

var SUCCESS = '<c:out value="${success}"/>';

document.onLoad = getErrors();

function getErrors(){
	if(SUCCESS.localeCompare('false')==0){
		dialog('error', null);
	}
	
}

function dialog(idDiv, data){
	$('.currentFormAddProject').attr("action", '${contextPath}/admin/'+idDiv);
	if(data){
		populate('#'+idDiv, data);
	}
	$( "#"+idDiv ).dialog({
	        title: idDiv,
	        modal: true,
	        close: function(event, ui)
	        {
	        	//$(this).dialog('destroy').remove();
	        },
	        width:'auto'
            		
	});
	
}

function editProject(idProject, idDiv){	
	$.when(
			$.post(CONTEXTPATH+"/admin/editProject/"+idProject)
		   ).done(function(data) {

			   dialog(idDiv, data);
			   }
	);
		   
}

function populate(frm, data) {   
    $.each(data, function(key, value){  
    var $ctrl = $('[name='+key+']', frm);  
    switch($ctrl.attr("type"))  
    {  
        case "text" :   
        case "hidden":  
        case "textarea":  
        $ctrl.val(value);   
        break;   
        case "radio" : case "checkbox":   
        $ctrl.each(function(){
           if($(this).attr('value') == value) {  $(this).attr("checked",value); } });   
        break;  
    }  
    });  
}
</script>