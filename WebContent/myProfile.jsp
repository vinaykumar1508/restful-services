<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>profile page</title>

<script src="/WebServices/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	
	function showProfile(){
		
		var profileName = sessionStorage.profileName;
		var url="rest/profiles/"+profileName;
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
							 	$("#getprofile").append("<b>lastName:</b>"+data.lastName+ "<br/><br/>");
							 	$("#getprofile").append('<input type="text" name="addNewMessage" id="addNewMessage'+profileName+'" style="color:#888;" value="Whats on your mind" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>');
							 	$("#getprofile").append("&nbsp;&nbsp;&nbsp;&nbsp;"+'<input type="submit"   value="Add Message" onClick="addMessage(\''+profileName+'\');return false;">'+"<br/>");
							 	$("#getprofile").append("<br/><br/>"+'<a href="#" onclick=getMessages(\''+data.profileName+'\');>My Messages</a>');
							 	
							 	
							 	
					}
				}
		);

	}	
	
	
	function getMessages(profileName){	
		
		var url='rest/profiles/'+profileName+'/messages';
		$.ajax(
				{
					type:'GET',
					url:url,
					dataType:'json',
					contentType: "application/json; charset=utf-8",
					success:function(data)
					{		
							if($.trim(data)){
								
								for (var i=0;i<data.length;++i){
									
								 	$("#authResponse").append("<br/>"+"<b>MessageId:</b>"+data[i].id);
									$("#authResponse").append("<b>Message:</b>"+data[i].message);
								 	$("#authResponse").append("<b>Author:</b>"+data[i].author+ "<br/>");
								 	var id= 'like_'+data[i].id;
								 	//alert("iiddddddd:"+id);
								 	//$("#authResponse").append("<br/>"+'<a  href="javascript:buttonvalue_like(id)" id="like_'+data[i].id+'" onclick=likeMessage(\''+data[i].id+'\');>Like</a>');
								 	$("#authResponse").append("<br/>"+'<a   id="like_'+data[i].id+'" href="javascript:buttonvalue_like(\''+id+'\')" onclick=likeMessage(\''+data[i].id+'\');>Like</a>');
								 	$("#authResponse").append("<br/>"+'<a href="#" onclick=getComment(\''+data[i].id+'\');>view Comments</a>'+"<br/>"+"<br/>");
								 	$("#authResponse").append('<input type="text" name="newComment" id="newComment'+data[i].id+'"/>');
								 	$("#authResponse").append("<br/>"+'<input type="submit"   value="Add comment" id="data[i].id" onClick="addComment(\''+data[i].id+'\');return false;">'+"<br/>");
								}
							}else{
								$("#authResponse").append("Sorry No Messages.!!");
							}
							
					}
				}
				
		);
	}
	
	
	function getComment(messageId){
		
		var getCommentURL="rest/messages/"+messageId+"/comments";
		//alert(getCommentURL);
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
							$("#getComments").append("<b>Author :</b>"+data[i].author+"<br/>");
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
	
	
function likeMessage(messageId){
	
	var profileName = sessionStorage.profileName;
	var getLikeURL="rest/messages/"+messageId+"/likes?profileName="+profileName+"&action=like";
		//alert(getLikeURL);
		$.ajax(
		
			{
				type:'GET',
				url:getLikeURL,
				dataType:'text',
				contentType:"application/json; charset=utf-8",
				success:function(data)
				{	
					
					if($.trim(data)=='true')
					{	buttonvalue_like('like_'+messageId);
						alert("you liked the message.!!")
					}else{
						alert("you did not liked the message.!!")
					}
				},
				error: function (error) {
	                  alert('error inside likeMessage method');
                }	
			}	
				
		);
	 }

 function buttonvalue_like(idtest){
	 alert("inside buttonvalue_like function.!!"+idtest);
	 var messageId=idtest.substring(5);
	 alert("messageId:"+messageId);
	 //document.getElementById(id).innerHTML = "<a href='javascript:buttonvalue_unlike("+id+")'>Unlike</a>";
	 //document.getElementById(idtest).innerHTML = "<a href='javascript:buttonvalue_unlike("+idtest+")'>Unlike</a>";
	 document.getElementById(idtest).innerHTML = '<a   id="'+idtest+'" href="javascript:buttonvalue_unlike(\''+idtest+'\')" onclick=likeMessage(\''+messageId+'\');>Unlike</a>';
	 //'<a   id="like_'+data[i].id+'" href="javascript:buttonvalue_like(\''+id+'\')" onclick=likeMessage(\''+data[i].id+'\');>Like</a>'
 }
 
 function buttonvalue_unlike(idtest){
	 alert("inside buttonvalue_unlike function.!!"+idtest);
	 var messageId=idtest.substring(5);
	 //document.getElementById(idtest).innerHTML = "<a href='javascript:buttonvalue_like("+idtest+")'>Like</a>";
	 document.getElementById(idtest).innerHTML = '<a   id="'+idtest+'" href="javascript:buttonvalue_like(\''+idtest+'\')" onclick=unlikeMessage(\''+messageId+'\');>Like</a>';
	 
 }	
 
 function unlikeMessage(messageId){
		
		var profileName = sessionStorage.profileName;
		var getLikeURL="rest/messages/"+messageId+"/likes?profileName="+profileName+"&action=unlike";
			//alert(getLikeURL);
			$.ajax(
			
				{
					type:'GET',
					url:getLikeURL,
					dataType:'text',
					contentType:"application/json; charset=utf-8",
					success:function(data)
					{	
						
						if($.trim(data)=='true')
						{	buttonvalue_like('like_'+messageId);
							alert("you liked the message.!!")
						}else{
							alert("you did not liked the message.!!")
						}
					},
					error: function (error) {
		                  alert('error inside likeMessage method');
	                }	
				}	
					
			);
		 }
 
 
/* 	{
    	"message": "comment1",
		"created": null,
		"author": "author1"
} */
	function addComment(messageId){
		
		
		var id= "newComment"+messageId;
		var comment= document.getElementById(id).value;
		//alert("comment:"+comment);
	 	var postCommentURL="rest/messages/"+messageId+"/comments";
	 	var authRequest = {"message":comment,"author":"system"};
	 	
		$.ajax(
		{
				type:'POST',
				url:postCommentURL,
				data:JSON.stringify(authRequest),
				dataType:'json',
				contentType:"application/json; charset=utf-8",
				success:function(data)
				{		
					alert(data);	
					if($.trim(data)){
						$("#addcommentResult").append("comment has been added successfully.!!");
					}else{
						$("#addcommentResult").append("Comment is not added.!!");
					}
				},
				error: function (error) {
	                  alert('error inside addComment method');
                }	
			}	
				
		);
	 
	}	
	
	
function addMessage(profileName){
	
	var id= "addNewMessage"+profileName;
	var newMessage= document.getElementById(id).value;
	alert("newMessage:"+newMessage);
	var url='rest/profiles/'+profileName+'/messages';
 	var authRequest = {"message":newMessage,"author":profileName};
 	
	$.ajax(
	{
			type:'POST',
			url:url,
			data:JSON.stringify(authRequest),
			dataType:'json',
			contentType:"application/json; charset=utf-8",
			success:function(data)
			{		
				if($.trim(data)){
					$("#addMessageResult").append("Message has been added successfully.!!");
				}else{
					$("#addMessageResult").append("Message is not added.!!");
				}
			},
			error: function (error) {
                  alert('error inside addMessage method');
            }	
		}	
			
	); 
 
}	

function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}
function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#888"; }
}	
	
</script>	


</head>
<body onload="showProfile();">
	
<!-- http://localhost:8081/WebServices/myProfile.jsp -->

	
	My Profile<br/><br/>
	<div id = getprofile></div>
	<div id = addMessageResult></div>
	<div id = authResponse></div>
	<div id = getComments></div>
	<div id = addcommentResult></div>
	
	 
</body>
</html>