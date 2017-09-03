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
					<c:if test="${role != null}">
						<td align="center">Owner</td>
						<td align="center">Role</td>
					</c:if>
					<td align="center">Cv Name</td>
					<td align="center">Download</td>
					<td align="center">Edit</td>
					<td align="center">Delete</td>
					<td align="center">Last Edit Date</td>
					</tr>
				</thead>
				<tfoot>
					<tr>
					<c:if test="${role != null}">
						<td align="center">Owner</td>
						<td align="center">Owner Role</td>
					</c:if>
					<td align="center">Name</td>
					<td align="center">Download</td>
					<td align="center">Edit</td>
					<td align="center">Delete</td>
					<td align="center">Last Edit Date</td>
					</tr>
				</tfoot>
				<tbody>
			    	<c:forEach items="${listOfCv}" var="cv">
				    	<tr>
				    	<c:if test="${role != null}">
				    		<td align="center" class="col-md-2"> ${cv.ownerUsername}</td>
				    		<td align="center" class="col-md-2"> ${cv.ownerRole}</td>
				    	</c:if>
				    		<td align="center" class="col-md-3"> ${cv.cvName}</td>
				    		<td align="center" class="col-md-1">
				    			 <form action="index.jsp" method = "get"> 
				    				 <input type = "hidden" name = "action" value = "viewCv">
				    				<input type = "hidden" name = "willViewedCvId" value = "${cv.idCv}" >
				    				<button type = "submit" class = "btn btn-block btn-success" value = "Edit">
				    					 <span class="glyphicon glyphicon-download"></span>
				    				</button>				    			
				    		 	</form>
				    		</td>
				    		<td align="center" class="col-md-1">
				    			<form action="Edit" method = "post">
				    				<input type = "hidden" name = "action" value = "updateCv">
				    				<input type = "hidden" name = "updateCvId" value = "${cv.idCv}" >
				    				<button type = "submit" class = "btn btn-block btn-info" value = "Edit">
				    					 <span class="glyphicon glyphicon-edit"></span>
				    				</button>
				    			</form>
				    		</td>
				    		<td align="center" class="col-md-1">
				    			<form action="index.jsp" method = "get">
				    				<input type = "hidden" name = "action" value = "deleteCv">
				    				<input type = "hidden" name = "willDeletedCvId" value = "${cv.idCv}" >
				    				<button type = "submit" class = "btn btn-block btn-danger" value = "Delete" onclick="return confirm('Are you sure you want to delete Cv?')">
				    					 <span class="glyphicon glyphicon-trash"></span>
				    				</button>
				    			</form>
				    		</td>
				    		
				    		<td align="center" class="col-md-2"> ${cv.addDate}</td>

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