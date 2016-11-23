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
</style>

<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src= "/WebServices/js/showProfile.js"></script>
<script type="text/javascript" src= "/WebServices/js/messages.js"></script>

<script type="text/javascript" src= "/WebServices/js/comments.js"></script>

<!--<script type="text/javascript" src= "/WebServices/js/friends.js"></script> -->

<script type="text/javascript">
function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}

function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#888"; }
}
</script>

</head>
<body onload="showProfile();">
		
<div id="header">
<h1>Try This Out</h1>
</div>
		
<div id="nav">
Home<br>
View Profile<br>
Edit Profile<br>

</div>

<div id="section"> 
	Welcome!!<br/><br/>
	<div id = getprofile></div>
	<div id = addMessageResult></div>
	<div id = authResponse></div>
	<div id = getComments></div>
	<div id = addcommentResult></div>
	
	
</div>		
	
<div id="section2">
<h1>Friends section</h1>
<h3><a href="friends.html" >My Friends</a></h3>

<form id="search">
	<input type="text" name="serachFriend" id="serachFriend" value="find People" onfocus="inputFocus(this);" onblur="inputBlur(this);"/>
	<input type ="button" id="searchFriendButton" value="Find" onclick="searchFriends();"/>
</form>

</div>		
</body>
</html>