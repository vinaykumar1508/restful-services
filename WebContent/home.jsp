<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>homePage</title>

<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script type="text/javascript">
	
	function loadSignUpPage(){
	
		location.href="registration.jsp";
	}
	
	function loginCheck(){
		var profileName=document.getElementById("profilename").value;
		var password=document.getElementById("password").value;
		var authRequest = {"profileName":profileName,"password":password};
		
		var loginUrl='rest/profiles/'+profileName;
		$.ajax(
				{
					type:'GET',
					url:loginUrl,   //http://localhost:8081/WebServices/rest/profiles/
					data:JSON.stringify(authRequest),
					dataType:'json',
					contentType: "application/json; charset=utf-8",
					success:function(data)
					{
						if($.trim(data))
						{
							//window.location.href='';
							sessionStorage.profileName=profileName;
							//location.href="myProfile.jsp";
							location.href="default.jsp";
						}else{
							
							$("#inValidLogin").html("profile Name or Password does not match..!!");
						}
					},
					error: function (error) {
		                  alert('error inside getComment method');
	                }
					
				}
				
		);
		
		
	}
	
function getFacebookAuthentication(){
		
	var appId='1683886785159795';
	var redirectUrl = 'http://localhost:8081/WebServices/default.jsp';
	var returnValue = "https://www.facebook.com/dialog/oauth?client_id="+ appId + "&redirect_uri=" + redirectUrl+ "&scope=email";
	
	 
		
		window.location=returnValue;
		
	}

</script>

</head>
<body>
	
	<form method="post" onsubmit="loginCheck(); return false;">
	<h3>Log In</h3>
		ProfileName: <input type="text" name="profilename" id="profilename"/>
		Password: <input type="password" name="password" id="password"/>
		
	<input name="Submit" type="submit" value="submit"/>
	<br/>
	<br/>
	<br/>
	<br/>
	</form>
	<button type="button" value="FaceBook Login" onclick="getFacebookAuthentication();">Facebook Connect</button>
	<br>
	<div id="inValidLogin"></div>
	<br><br>
	<input name="signup" type="submit" value="Sign Up" onClick="loadSignUpPage();return false;" />
	
	
	<fb:login-button autologoutlink="true" onlogin="OnRequestPermission();">
</fb:login-button>
<script language="javascript" type="text/javascript">
    FB.init({
        appId: 'Your_Application_ID',
        status: true, 
        cookie: true, 
        xfbml: true
    });    
</script>


</body>
</html>