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


function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}

function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#888"; }
}