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