<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALL PROFILES</title>

<!-- http://localhost:8081/WebServices/getAllProfiles.jsp -->
<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

	function getAllProfiles(){
		alert("inside getAllProfiles");
		
		$.ajax(
				{
					type:'GET',
					url:'rest/profiles', 
					dataType:'json',
					contentType: "application/json; charset=utf-8",
					success:function(data)
					{
							for (var i=0;i<data.length;++i){
								var responseData=JSON.stringify(data);
								/* $("#authResponse").append("<b>profileName:</b>"+data[i].profileName);
								$("#authResponse").append("<b>firstName:</b>"+data[i].firstName);
								$("#authResponse").append("<b>lastName:</b>"+data[i].lastName+ "<br/>"); */
								$("#authResponse").append("<br/>"+'<input type="submit"   value="'+data[i].profileName+'"onClick="getProfile(\''+data[i].profileName+'\');return false;">'+"<br/>");
							}
					}
				}
		);
	}	
	
	 function getProfile(profile){
		 alert("test");
		var profileName;/* 
		for(i=0;i<arguments.length;i++){
			profileName=arguments[i];
		} */
		
		var url="rest/profiles/"+profile;
		alert(profile.profileName);
		
		$.ajax(
				{
					type:'GET',
					url:url, 
					dataType:'json',
					contentType: "application/json; charset=utf-8",
					success:function(data)
					{
								$("#getprofile").append("<b>profileName:</b>"+data.profileName);
							 	$("#getprofile").append("<b>firstName:</b>"+data.firstName);
							 	$("#getprofile").append("<b>lastName:</b>"+data.lastName+ "<br/>");
							 	$("#authResponse").append("<br/>"+'<input type="submit"   value="'+data[i].profileName+'"onClick="getProfile();return false;">'+"<br/>");
					}
				}
		);
	}	 
	
</script>

</head>
<body>

	<input name="Submit" type="submit" value="Get Profiles" onClick="getAllProfiles();return false;" />
	
	<div id="allprofiles">
		<div id="authResponse"></div>
		
		<div id="getprofile"></div>
	</div>
	
</body>
</html>