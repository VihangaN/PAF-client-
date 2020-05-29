$(document).on("click", ".btnUpdate", function(event) { 
	
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidstaffId').val());
	$("#staffName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#StaffAge").val($(this).closest("tr").find('td:eq(2)').text());
	$("#StaffGender").val($(this).closest("tr").find('td:eq(3)').text());
	$("#StaffNic").val($(this).closest("tr").find('td:eq(4)').text());
	$("#StaffAddress").val($(this).closest("tr").find('td:eq(5)').text());
	$("#StaffEmail").val($(this).closest("tr").find('td:eq(6)').text());
	$("#StaffType").val($(this).closest("tr").find('#hidstafftype').val());

	
	$("#alertSuccess").text().trim() == "Data Retrived"

});



$(document).ready(function () {
	 document.forms['form'].reset();
	 
});

$(document).ready(function() { 

	if($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	$("#alertError").hide(); 
	
});

$(document).on("click", "#btnSave", function(event) { 
	
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text(""); 
	$("#alertError").hide(); 
	
	var status = validateItemForm(); 
	
	if (status != true)  {  
		$("#alertError").text(status);  
		$("#alertError").show();  
		return; 	
		} 
	
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( { 
		url : "StaffAPI", 
		type : type,  
		data : $("#detailform").serialize(),  
		dataType : "text",  
		complete : function(response, status) 
		{   
			onItemSaveComplete(response.responseText, status);  
		
		} 
	}); 
	
});

function onItemSaveComplete(response, status) {  
	console.log(response);
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully saved.");  $("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 
		
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	 }
	
	$("#hidItemIDSave").val(""); 
	$("#form")[0].reset(); 
}


$(document).on("click", ".btnRemove", function(event) { 
	
	console.log(event);
	$.ajax( { 
		url : "StaffAPI",   
		type : "DELETE",   
		data : "StaffId=" + $(this).val(),   
		dataType : "text",   
		complete : function(response, status) 
		{   
			onItemDeleteComplete(response.responseText, status);  
		
		} 
	}); 
	
});

function onItemDeleteComplete(response, status) {  
	
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully deleted."); 
		$("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 
		
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while deletingdeleting.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 }
	
}



	
	

function validateItemForm() {  
	
	
	
	
	
	
	 
	
	
	if ($("#staffName").val().trim() == "") {  

		return "Please Enter your name";
	} 
	
	var age =$("#StaffAge").val().trim();
	if (age == "") {  

	return "Please Enter your age";
	}
	
	if ($("#StaffGender").val().trim() == 0) {  
		 
		return "Please Select Gender";
	} 
	
if ($("#StaffNic").val().trim() == "") {  
		
		return "Please enter Nic";
	} 

if ($("#StaffAddress").val().trim() == "") {  
	 
	return "Please Enter addres";
} 


if ($("#StaffEmail").val().trim() == "") {  

	return "Please enter email";
} 

	
	if ($("#StaffType").val().trim() == 0) {  
		 
		return "Please Select type";
	} 
	
	return true; 
	 
	}
