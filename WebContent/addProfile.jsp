<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Profile</title>


	<!--  http://localhost:8081/WebServices/addProfile.jsp -->
	
<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

	function addProfile(){
		
		var profileName=document.getElementById("profilename").value;
		var firstName=document.getElementById("firstname").value;
		var lastName=document.getElementById("lastname").value;
		var emailId=document.getElementById("emailId").value;
		var password=document.getElementById("password").value;
		//alert("profileName"+profileName);
		var authRequest = {"profileName":profileName,"firstName":firstName,"lastName":lastName,"emailId":emailId,"password":password};
		
		
		$.ajax(
				{
					type:'POST',
					url:'rest/profiles',   ////http://localhost:8081/WebServices/rest/profiles/
					data:JSON.stringify(authRequest),
					dataType:'json',
					contentType: "application/json; charset=utf-8",
					success:function(data)
					{
						$("#result").html(data);
					}
				}
				
		);
		
	
			
	}
	
</script>


</head>
<body>
	
	<form method="post" onsubmit="addProfile(); return false;">
	
	ProfileName: <input type="text" name="profilename" id="profilename"/>
	FirstName: <input type="text" name="firstname" id="firstname"/>
	LastName: <input type="text" name="lastname" id="lastname"/>
	<input name="Submit" type="submit" value="submit"/>
	
	</form>
	
</body>



</html>