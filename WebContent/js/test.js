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
		
	
	
function addMessage(profileName){
	
	var id= "addNewMessage"+profileName;
	var newMessage= document.getElementById(id).value;
	alert("newMessage:"+newMessage);
	var url='rest/profiles/'+profileName+'/messages';
 	var authRequest = {"message":newMessage,"profileName":profileName};
 	
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