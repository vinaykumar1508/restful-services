function getFriends(){
		
		var profileName= sessionStorage.profileName;
		
		var getFriendsURL='rest/profiles/'+profileName+'/friends';
		//alert(getFriendsURL);
		$.ajax(
		
			{
				type:'GET',
				url:getFriendsURL,
				dataType:'json',
				contentType:"application/json; charset=utf-8",
				success:function(data, textStatus, jqXHR)
				{	
					//alert(jqXHR.status+':'+textStatus+':'+data);
					if($.trim(data))
					{
						for (var i=0;i<data.length;++i){
							
							$("#getFriends").append(data[i]+"<br>");
							
						}
					}else{
						$("#getFriends").append("<b>You have got No friends.</b>");
					}
				},
				error: function (xhr,status,text) {
					alert(status)
	                  //alert('error inside getComment method');
					$("#getFriends").append("<b>You have got No friends.</b>");
                }	
			}	
				
		);
	}

function searchFriends(){
	
	var searchString = document.getElementById("serachFriend").value;
	alert(searchString);
}

