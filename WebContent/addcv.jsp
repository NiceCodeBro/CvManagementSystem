<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:simple_layout title="CV Ekle">

	<jsp:attribute name="head_area">

<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery-easywizard.js"></script>
<script src="js/photoUpload.js"></script> 
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
<script>
$(function () {
	  $("#datepicker").datepicker({ 
	        autoclose: true, 
	        todayHighlight: true
	  }).datepicker('update', new Date());;
	});
</script>

</jsp:attribute>

	<jsp:attribute name="body_area">
	<div class="container">
  
<div class="stepwizard col-md-offset-3">
    <div class="stepwizard-row setup-panel">
      <div class="stepwizard-step">
        <a href="#step-1" type="button"
							class="btn btn-primary btn-circle">1</a>
        <p>Personal Info</p>
      </div>
      <div class="stepwizard-step">
        <a href="#step-2" type="button"
							class="btn btn-default btn-circle">2</a>
        <p>Jop Experience</p>
      </div>
      <div class="stepwizard-step">
        <a href="#step-3" type="button"
							class="btn btn-default btn-circle">3</a>
        <p>Step 3</p>
      </div>
    </div>
  </div>
  
  <form role="form" action="upload" method="post" enctype="multipart/form-data">
    <div class="row setup-content" id="step-1">
      <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12">
          <h3> Personal Info</h3>
          <div class="form-group">
            <label class="control-label">Name</label>
            <input maxlength="45" type="text" 
									class="form-control" placeholder="Enter Name" /> 
          </div>
            <div class="form-group">
              <label class="control-label">Title</label>
            <input maxlength="45" type="text" class="form-control"
									placeholder="Enter Your Title. (Like Software Developer)" /> 
          </div>
          <div class="form-group">
            <label class="control-label">Objectives</label>
            <textarea  class="form-control"
									placeholder="An objective is a short statement that clarifies your goals."></textarea>
          </div>
          
           <div class="form-group">
           
              <label class="control-label">Date Of Birth</label>
            	<div id="datepicker" class="input-group date" data-date-format="mm-dd-yyyy">
		    		<input class="form-control" type="text" maxlength="10"/>
		    		<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
          </div>
          
          <div class="form-group">
            <label class="control-label">Cell Phone</label>
            <input max="11" type="text" class="form-control" placeholder="Enter your cell phone."></textarea>
          </div>
          
         <div class="form-group">
            <label class="control-label">Office Phone</label>
            <input class="form-control" placeholder="Enter your office phone."></textarea>
          </div>
          
           <div class="form-group">
            <label class="control-label">Adress</label>
            <textarea class="form-control"
									placeholder="Enter your adress."></textarea>
          </div>
     
          
          	<div> 
          	<label class="control-label">Medeni Durum</label>
				<label class="custom-control radio">
			  <input id="radio1" name="radio" type="radio" class="custom-control-input">
			  <span class="custom-control-indicator"></span>
			  <span class="custom-control-description">Evli</span>
			</label>
			<label class="custom-control radio">
			  <input id="radio2" name="radio" type="radio" class="custom-control-input">
			  <span class="custom-control-indicator"></span>
			  <span class="custom-control-description">Bekar</span>
			</label>
			</div>
                    <!-- Photo upload section -->
          
			             <!-- image-preview-filename input [CUT FROM HERE]-->
           <div class="form-group">
            <label class="control-label">Profil Resmi</label>
             <input type="file" name="file" class="form-control"/>
          </div>
	

	
          <button class="btn btn-primary nextBtn btn-lg pull-right"
								type="button">Next</button>
        </div>
      </div>
    </div>
    <div class="row setup-content" id="step-2">
      <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12">
          <h3> Jop Experience </h3>
          <div class="form-group">
            <label class="control-label">Company Name</label>
            <input maxlength="45" type="text" 
									class="form-control" placeholder="Enter Company Name" />
          </div>
          
          <div class="form-group">
            <label class="control-label">Title</label>
            <input maxlength="45" type="text" 
									class="form-control" placeholder="Enter Company Address" />
          </div>
          
           <div class="form-group">
            <label class="control-label">Working Date</label>
            <input maxlength="45" type="text"
									class="form-control" placeholder="Enter Working Date" />
          </div>
          
          <!-- BURAYA TExT EDİTOR GELECEK  -->
          
          
          <button class="btn btn-primary nextBtn btn-lg pull-right"
								type="button">Next</button>
        </div>
      </div>
      
    </div>
    

	 
	  
    <div class="row setup-content" id="step-3">
      <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12">
          <h3> Step 3</h3>
          <input class="btn btn-success btn-lg pull-right"
								type="submit">Submit</input>
        </div>
      </div>
    </div>
    

	  
	  
  </form>
  

		
</div>


</jsp:attribute>

</t:simple_layout>