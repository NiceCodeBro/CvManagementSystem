<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
<t:simple_layout title="Anasayfa">
 
<jsp:attribute name="head_area">

</jsp:attribute>
 
<jsp:attribute name="body_area">
 
<div class="container">
<table class="table table-bordered">
	<thead>
		<tr>
		<td align="center">CV İsmi</td>
		<td align="center">CV Görüntüle</td>
		<td align="center">CV Sil</td>
		</tr>
	</thead>
	<tbody>
    	<c:forEach items="${listOfCv}" var="cv">
	    	<tr>
	    		<td> ${cv.cvName}</td>
	    		
	    	</tr>
   		</c:forEach>
    	</tbody>
</table>

</div>



</jsp:attribute>
 
</t:simple_layout>