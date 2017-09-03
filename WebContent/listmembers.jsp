<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<t:simple_layout title="Member List">
 
<jsp:attribute name="head_area">




</jsp:attribute>


<jsp:attribute name="body_area">
<div class="container">
	<div class="row">

		<div class="col-md-2 col-lg-2"></div>
		
		<div class="col-md-2 col-md-2">
			<form action = "MembersList" method = "post"> 	
			<input type = "hidden" name = "action" value = "addNewMember">
	 	  	 	<button type="button" class="btn btn-primary active" data-toggle="modal" data-target="#myModal" >Add New Member</button>
	  	 					<!-- Modal -->
				<div id="myModal" class="modal fade" role="dialog">
				  <div class="modal-dialog">
				
				    <!-- Modal content-->
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				        <h4 class="modal-title">Add New Member</h4>
				      </div>
				      <div class="modal-body">
				        			<label for="usr">User Name:</label> 
								 	<input type="text"  class="form-control" name="memberName" required/>
				        			<label for="usr">Member Password:</label> 
				        			<input type="text"  class="form-control" name="memberPassword" required/>
				        			
		        					<label for="usr">Member Role:</label> 
		        					<select name="memberRole" class="selectpicker">
									  <option>Member</option>
									  <option>Manager</option>
									</select>
								
								  <div class="modal-footer">
										<input type = "submit" class = "btn btn-default" id = "updateMember2" value = "Add" >
				      			</div>
				      </div>
				    
				    </div>
				
				  </div>
				</div>
	  	 	
	  	 	</form>
		
		</div>
		

		<form action = "MembersList" method = "post"> 	
					<input type = "hidden" name = "action" value = "updateMember">
		
 	  	 	<button type="button" class="btn btn-primary active" data-toggle="modal" data-target="#myModal2">Update Member</button>
  	 					<!-- Modal -->
			<div id="myModal2" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Update Member</h4>
			      </div>
			      <div class="modal-body">
							 	<label for="usr">Member Id:</label>
							 	<input type="number"  class="form-control" name="memberId" required/>
							 	<label for="usr">Member Username:</label>
							 	<input type="text"  class="form-control" name="memberName" required/>
			        			<label for="usr">Member Password:</label> 
			        			<input type="text"  class="form-control" name="memberPassword" required/>
			        		
	        					<label for="usr">Member Role:</label> 
	        					<select name="memberRole" class="selectpicker">
								  <option>Member</option>
								  <option>Manager</option>
								</select>
							
							  <div class="modal-footer">
								  <div class="form-group">
									<input type = "submit" class = "btn btn-default" id = "updateMember" value = "Update" >
								</div>
			      			</div>
			      </div>
			    
			    </div>
			
			  </div>
			</div>
  	 	
  	 	</form>

	</div>
	<div class="row">	
		<div class="col-md-8 col-lg-8">
			<table  id="cvTable" class="table table-striped table-bordered" width="100%" cellspacing="0">
				<thead>
					<tr>
					<td align="center">Member Id</td>
					<td align="center">Username</td>
					<td align="center">Role</td>
					<td align="center">Delete</td>
					</tr>
				</thead>
				<tfoot>
					<tr>
					<td align="center">Member Id</td>
					<td align="center">Username</td>
					<td align="center">Role</td>
					<td align="center">Delete</td>
					</tr>
				</tfoot>
				<tbody>
				

			    	<c:forEach items="${listOfMember}" var="member">
				    	<tr>
				    		<td align="center" class="col-md-3"> ${member.idMember}</td>
				    		<td align="center" class="col-md-3"> ${member.memberName}</td>
				    		<td align="center" class="col-md-3"> ${member.role}</td>
				    		<td align="center" class="col-md-3">
				    			<form action="MembersList" method = "post" >
				    				<input type = "hidden" name = "action" value = "deleteUser">
				    				<input type = "hidden" name = "willDeletedUserId" value = "${member.idMember}" >
				    				<button type = "submit" class = "btn btn-block btn-danger" id = "deleteMember" value = "Delete" onclick="return confirm('Are you sure you want to delete all user information?')">
				    					<span class="glyphicon glyphicon-trash"></span>
				    				</button>	
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