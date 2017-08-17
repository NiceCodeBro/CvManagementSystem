<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
<t:simple_layout title="Anasayfa">
 
<jsp:attribute name="head_area">

</jsp:attribute>
 
<jsp:attribute name="body_area">
 
<div class="container">
	<div class="row">
	
		<div class="col-md-2 col-lg-2"><h3>${requestScope["message"]}</h3></div>
		<div class="col-md-8 col-lg-8">
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
				    		<td class="col-md-6"> ${cv.cvName}</td>
				    		<td class="col-md-3"><button type="button" class="btn btn-block btn-primary">Görüntüle</button></td>
				    		<td class="col-md-3">
				    			<form action="index.jsp" method = "get">
				    				<input type = "hidden" name = "action" value = "deleteCv">
				    				<input type = "hidden" name = "willDeletedCvId" value = "${cv.idCv}" >
				    				<input type = "submit" class = "btn btn-block btn-danger" value = "Sil">
				    			</form>
				    		</td>
				    	</tr>
			   		</c:forEach>
			    	</tbody>
			</table>
		</div>
		<div class="col-md-2 col-lg-2"></div>
	</div>
</div>



</jsp:attribute>
 
</t:simple_layout>