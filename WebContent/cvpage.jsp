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
	
		<div class="col-md-2 col-lg-2"></div>
		<div class="col-md-8 col-lg-8">
			<table  id="cvTable" class="table table-striped table-bordered" width="100%" cellspacing="0">
				<thead>
					<tr>
					<td align="center">Owner</td>
					<td align="center">Owner Role</td>
					<td align="center">Owner Name</td>
					<td align="center">View Cv</td>
					<td align="center">Edit Cv</td>
					<td align="center">Delete Cv</td>
					<td align="center">Cv Add or Update Date </td>
					</tr>
				</thead>
				<tfoot>
					<tr>
					<td align="center">Owner</td>
					<td align="center">Owner Role</td>
					<td align="center">Owner Name</td>
					<td align="center">View Cv</td>
					<td align="center">Edit Cv</td>
					<td align="center">Delete Cv</td>
					<td align="center">Cv Add or Update Date </td>
					</tr>
				</tfoot>
				<tbody>
			    	<c:forEach items="${listOfCv}" var="cv">
				    	<tr>
				    		<td class="col-md-2"> ${cv.ownerUsername}</td>
				    		<td class="col-md-1"> ${cv.ownerRole}</td>
				    		<td class="col-md-2"> ${cv.cvName}</td>
				    		<td class="col-md-2">
				    			 <form action="index.jsp" method = "get"> 
				    				 <input type = "hidden" name = "action" value = "viewCv">
				    				<input type = "hidden" name = "willViewedCvId" value = "${cv.idCv}" >
				    				<input type = "submit" class = "btn btn-block btn-primary" value = "Görüntüle"> 				    			
				    		 	</form>
				    		</td>
				    		<td class="col-md-2">
				    			<form action="Edit" method = "post">
				    				<input type = "hidden" name = "action" value = "updateCv">
				    				<input type = "hidden" name = "updateCvId" value = "${cv.idCv}" >
				    				<input type = "submit" class = "btn btn-block btn-primary" value = "Düzenle">
				    			</form>
				    		</td>
				    		<td class="col-md-1">
				    			<form action="index.jsp" method = "get">
				    				<input type = "hidden" name = "action" value = "deleteCv">
				    				<input type = "hidden" name = "willDeletedCvId" value = "${cv.idCv}" >
				    				<input type = "submit" class = "btn btn-block btn-danger" value = "Sil">
				    			</form>
				    		</td>
				    		
				    		<td class="col-md-2"> ${cv.addDate}</td>

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