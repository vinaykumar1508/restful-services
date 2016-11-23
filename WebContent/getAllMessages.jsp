<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Messages</title>

<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
		
	function getMessages(){	
		$.ajax(
				{
					type:'GET',
					url:'rest/messages', 
					dataType:'json',
					contentType: "application/json; charset=utf-8",
					success:function(data)
					{
							for (var i=0;i<data.length;++i){
							var responseData=JSON.stringify(data);
							//alert(data.comment);
							
							 	$("#authResponse").append("<b>MessageId:</b>"+data[i].id);
								$("#authResponse").append("<b>Message:</b>"+data[i].message);
							 	$("#authResponse").append("<b>Author:</b>"+data[i].author+ "<br/>");
							 	$("#authResponse").append("<br/>"+'<input type="submit"   value="View comment" onClick="getComment(\''+data[i].id+'\');return false;">'+"<br/>");
							 	$("#authResponse").append("<br/>"+'<input type="submit"   value="Add comment" onClick="addComment(\''+data[i].id+'\');return false;">'+"<br/>");
							 	
							}
					}
				}
				
		);
	}
	
	
	function getComment(messageId){
		//alert("inside comment");
		
		var getCommentURL="rest/messages/"+messageId+"/comments";
		alert(getCommentURL);
		$.ajax(
		
			{
				type:'GET',
				url:getCommentURL,
				dataType:'json',
				contentType:"application/json; charset=utf-8",
				success:function(data)
				{	
					if($.trim(data))
					{
						for (var i=0;i<data.length;++i){
							$("#getComments").append("<b>Id:</b>"+data[i].id);
							$("#getComments").append("<b>Comment :</b>"+data[i].message);
							$("#getComments").append("<b>Author :</b>"+data[i].author);
						}
					}else{
						alert("No comments are added for this message.!!")
					}
				},
				error: function (error) {
	                  alert('error inside getComment method');
                }	
			}	
				
		);
	}
	
	
	function addComment(messageId){
		alert("inside add comment")
		var postCommentURL="rest/messages/"+messageId+"/comments";
		alert(getCommentURL);
		$.ajax(
		
			{
				type:'POST',
				url:getCommentURL,
				dataType:'json',
				contentType:"application/json; charset=utf-8",
				success:function(data)
				{	
					if($.trim(data))
					{
						for (var i=0;i<data.length;++i){
							$("#getComments").append("<b>Id:</b>"+data[i].id);
							$("#getComments").append("<b>Comment :</b>"+data[i].message);
							$("#getComments").append("<b>Author :</b>"+data[i].author);
						}
					}else{
						alert("No comments are added for this message.!!")
					}
				},
				error: function (error) {
	                  alert('error inside getComment method');
                }	
			}	
				
		);
	}
	
	</script>

</head>
<body>
	
	
	<input name="Submit" type="submit" value="Get Messages" onClick="getMessages();return false;" />
	
	<div id="Messages">
		<div id="authResponse"></div>
		<div id="getMessage"></div>
		<div id="getComments"></div>
	</div>
</body>
</html>