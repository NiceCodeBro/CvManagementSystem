$(document).ready(function () {
	$("#addCheckboxJob").click(function () {
		$("#ayracJob").parent().find('div#ayracJob:last').after('<div class="row"> <div class="col-md-12"> <div class="form-group"> <div class="col-md-offset-11"> <button href="javascript:;" class="remove btn btn-danger"><span class="glyphicon glyphicon-minus"></span></button> </div> </div> <div id="isDeneyimi"> <div class="form-group"> <label class="control-label">Company Name <span class="required-star"></span></label> <input name="jobCompanyName" required maxlength="45" type="text" class="form-control" placeholder="Enter Company Name" /> </div> <div class="form-group"> <label class="control-label">Title <span class="required-star"></span></label> <input name="jobTitle" required maxlength="45" type="text" class="form-control" placeholder="Enter Title" /> </div> <div class="form-group"> <label class="control-label">Working Date <span class="required-star"></span></label> <div class="input-group"> <input name="jobStartDate" required type="text" class="form-control" value=""> <div class="input-group-addon">to</div> <input name="jobEndDate" required type="text" class="form-control" value=""> </div> </div> <div class="form-group"> <label class="control-label">Description <span class="required-star"></span></label> <textarea name="jobDescription" required class="form-control" placeholder="Description." ></textarea> </div> </div> </div> </div> <div id="ayracJob"></div> ');
	});
	$("#addCheckboxEdu").click(function () {
		$("#ayracEdu").parent().find('div#ayracEdu:last').after('<div class="row"> <div class="col-md-12"> <div class="form-group"> <div class="col-md-offset-11"> <button href="javascript:;" class="remove btn btn-danger"><span class="glyphicon glyphicon-minus"></span></button> </div> </div> <div id="educationInf"> <div class="form-group"> <label class="control-label">School Name <span class="required-star"></span></label> <input name="eduSchoolName" required maxlength="45" type="text" class="form-control" placeholder="Enter School Name" /> </div> <div class="form-group"> <label class="control-label">School Departman <span class="required-star"></span></label> <input name="eduSchoolDepartman" required maxlength="45" type="text" class="form-control" placeholder="Enter Title" /> </div> <div class="form-group"> <label class="control-label">Education Date <span class="required-star"></span></label> <div class="input-group"> <input name="eduStartDate" required type="text" class="form-control" value=""> <div class="input-group-addon">to</div> <input name="eduEndDate" required type="text" class="form-control" value=""> </div> </div> <div class="form-group"> <label class="control-label">Description <span class="required-star"></span></label> <textarea name="eduDescription" required class="form-control" placeholder="Enter description" ></textarea> </div> </div> </div> </div> <div id="ayracEdu"></div>');  });
	$("#addCheckboxForeign").click(function () {
		$("#ayracForeign").parent().find('div#ayracForeign:last').after('<div class="row"> <div class="col-md-12"> <div class="form-group"> <div class="col-md-offset-11"> <button href="javascript:;" class="remove btn btn-danger"><span class="glyphicon glyphicon-minus"></span></button> </div> </div> <!-- ekleme butonu --> <div class="col-md-6"> <div class="form-group"> <label class="control-label">Language <span class="required-star"></span></label> <input name="foreignName" required maxlength="45" type="text" class="form-control" placeholder="Enter language you know." /> </div> </div> <div class="col-md-6"> <div class="form-group"> <label class="control-label">Level <span class="required-star"></span></label> <select class="form-control" required name="foreignLevel"> <option>Beginning</option> <option>Intermediate</option> <option>Good</option> <option>Advanced</option> </select> </div> </div> </div> </div> <div id="ayracForeign"></div>'); });

	$('body').on('click', '.remove', function(){
		$(this).closest('.col-md-12').remove();
	});

	$(function() {
		$(".input-daterange input").datepicker({
			autoclose : true,
			todayHighlight : true
		}).datepicker('update', new Date());
		;
	});


	/*$(function() {
		$("#datepicker").datepicker({
			autoclose : true,
			todayHighlight : true
		}).datepicker('update', new Date());
		;
	});*/

	$(document).on("keypress", ":input:not(textarea)", function(event) {
	    return event.keyCode != 13;
	});
	
});			
