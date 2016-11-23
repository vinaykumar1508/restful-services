

function doRegistration(){
	
	var profileName=document.getElementById("profileName").value;
	var firstName=document.getElementById("firstName").value;
	var lastName=document.getElementById("lastName").value;
	var emailId=document.getElementById("emailId").value;
	var password=document.getElementById("password").value;
	//alert("profileName"+profileName);
	var authRequest = {"profileName":profileName,"firstName":firstName,"lastName":lastName,"emailId":emailId,"password":password};
	
	//alert("comment:"+comment);
	
 	var postRegistrationURL="rest/profiles";
 	
	$.ajax(
	{
			type:'POST',
			url:postRegistrationURL,
			data:JSON.stringify(authRequest),
			dataType:'json',
			contentType:"application/json; charset=utf-8",
			success:function(data,textStatus,jqXHR)
			{		
				//alert(jqXHR.status+':'+textStatus+':'+data.profileName);	
				
			/*	$.each(data,function(index){
					alert($(data.profileName));
				});
			*/	
				
				/*for(var i=0;i < data.length;++i)
				{
				  var item = data[i];
				  alert(item);
				}*/
				
				if(jqXHR.status==200){
					alert('200');
					var profileName=data.profileName;
					autoLogin(profileName);
				}else{
					$("#errorMsg").append(data);
				}
			},
			error: function (error) {
                  alert('error inside addComment method');
            }	
		}	
			
	);
 
}

function autoLogin(profileName){
	
	//var profileName = sessionStorage.profileName;
	
	sessionStorage.profileName=profileName;
	//location.href="registration.jsp";
	location.href="default.jsp";
}