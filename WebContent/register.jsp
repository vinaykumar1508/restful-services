<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<style type="text/css">
	div {
    display: block;
}
#wrapper {
    width: 400px;
    height:400px;
}

#first {
    float:left;
    width: 100%;
    height: 100%;
    background: silver;
}
</style>
<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript" > </script>
<script>
	function showHint(){
		console.log("Inside showHint!");
		var userId=$("#userId").val();
		var firstName=$("#fname").val();
		//var lastName=$("#lname").val();
		var emailId=$("#emailId").val();
		var password=$("#password").val();
		var gender=$("input[name=sex]:checked").val();
		var date=$("#day").val();
		
		
		var JSONObj = {"userId":userId,"firstName":firstName};
		
		$.ajax(
				{
			
					type:'POST',
					url:'rest/register/user',
					data:JSON.stringify(JSONObj),
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
<!-- action="rest/register/user" method="POST" -->

</head>
<body>
<div id="wrapper">
<div id ="result"></div>
<div id="first">
	 <form name ="register" action="javascript:showHint();" method="POST" >  
	 <table width="500">
		 <tr>
	 		<td>User ID:</td>
	 		<td><input type="text" name="userId" id= "userId" /></td>
	 	</tr>
	 	<tr>
	 		<td>First Name:</td>
	 		<td><input type="text" name="fname" id= "fname" /></td>
	 	</tr>
	 	<tr>
	 		<!-- <td>Last Name:</td>
	 		<td><input type="text" name="lname" id="lname"/></td>
	 	</tr>
	 	<tr>
	 		<td>Email id:</td>
	 		<td><input type="text" name="emailId"/></td>
	 	</tr> -->
	 	<tr>
	 		<td>Password:</td>
	 		<td><input type="password" name="password"/></td>
	 	</tr>
	 	<tr>
	 		<td>Retype Password:</td>
	 		<td><input type="password" name="confirmPassword"/></td>
	 	</tr>
	 	<!-- <tr>
	 		<td>Address:</td>
	 		<td><input type="text" name="address"/></td>
	 	</tr> -->
	 	<tr>
	 		<td>Date of Birth:</td>
	 		<td>
		 		<select id ="day">
				  <option value="1">1</option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				</select>
			
				<select name="month">
					<option	value="Month">Mon</option>
					<option	value="Jan">Jan</option>
					<option	value="Feb">Feb</option>
					<option	value="Mar">Mar</option>
					<option	value="Apr">Apr</option>
					<option	value="May">May</option>
				</select>
				
				<select name="Year">
					<option	value="Year">Year</option>
					<option	value="1991">1991</option>
					<option	value="1992">1992</option>
					<option	value="1993">1993</option>
					<option	value="1994">1994</option>
				</select>
			</td>
		</tr>
		<!-- <tr>
			<td>Gender:</td>
			<td><input type="radio" name="sex" value="male">Male 
			<input type="radio" name="sex" value="female"> Female
			</td>
		</tr>
			
 -->	 </table>
		
		<center><input type="submit" value="submit"/></center>
		 
	</form> 
	<div id ="Suggestions"></div>
	
	</div>
	</div>
</body>
</html>