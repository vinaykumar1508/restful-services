<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Default Page</title>

<style>
#header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}

#nav {
    line-height:30px;
    background-color:#eeeeee;
    height:300px;
    width:100px;
    float:left;
    padding:5px;	      
}
#section {
    width:500px;
    float:left;
    padding:10px;	 	 
}
#footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}

#section2 {
    width:300px;
    float:left;
    padding:10px;
    background-color:#eeeeee;
    line-height:30px;
    height:300px;	 	 
}

input{
	border:1px solid #0000FF;
	height:30px;
	
	
}

</style>

<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src= "/WebServices/js/showProfile.js"></script>
<script type="text/javascript" src= "/WebServices/js/messages.js"></script>
<script type="text/javascript" src= "/WebServices/js/comments.js"></script>
<script type="text/javascript" src= "/WebServices/js/register.js"></script>

<script type="text/javascript">
function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}

function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#888"; }
}

function makePasswordHidden(i){
	if(i.value==i.defaultValue){
		i.value='';
		i.setAttribute("type","password");	
	}
	
}

function makePasswordshown(i){
	if(i.value==""){
		i.setAttribute("type","text");
		i.value=i.defaultValue;	
	}
	
}

</script>

</head>
<body >
		
<div id="header">
<h1>Try This Out</h1>
</div>
		
<div id="nav">

</div>

<div id="section"> 
<br>
<div id="errorMsg"></div>
<form name="registration">	
<center>
<h1>Sign Up</h1><br>
<input type="text" name="profileName" id="profileName" style="color:#888;" size="50"  value="Profile Name" onfocus="inputFocus(this)" onblur="inputBlur(this)"/><br><br>
<input type="text" name="firstName" id="firstName" style="color:#888;" value="First Name" size="50" onfocus="inputFocus(this)" onblur="inputBlur(this)"/><br><br>
<input type="text" name="lastName" id="lastName" style="color:#888;" value="Last Name" size="50" onfocus="inputFocus(this)" onblur="inputBlur(this)"/><br><br>
<input type="text" name="emailId" id="emailId" style="color:#888;" value="Email Id" size="50" onfocus="inputFocus(this)" onblur="inputBlur(this)"/><br><br>
<input type="text" name="password" id="password" style="color:#888;" value="Password" size="50" onfocus="makePasswordHidden(this)" onblur="makePasswordshown(this)"/><br><br>
<input type="text" name="rePassword" id="rePassword" style="color:#888;" value="Reenter password" size="50" onfocus="makePasswordHidden(this)" onblur="makePasswordshown(this)"/><br><br>

<input type="button" value='Submit' onclick="doRegistration();">
</center>



</form>	
	
</div>		
	
<div id="section2">


</div>		
</body>
</html>