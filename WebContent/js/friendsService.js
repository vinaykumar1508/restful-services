var searchFrndsService= angular.module('searchFrndsService',['ngResource']);



searchFrndsService.factory('friendsResult',['$resource',function($resource){
	alert("calling friendsResult. ")
	return $resource('http://localhost:8081/WebServices/rest/profiles/profile8/friends',{},
			{ query:{ method : 'GET', isArray : true   }
			  	
			}
	);
	
}]);


searchFrndsService.factory('sendFriendRequest',
		['$resource',function($resource)
		 			 {
							//alert("ssssssss");
							//var myParam1='{"requester": "Saimon", "accepter": "saimon@devdactic.com","status":"0"}';
							//var myParam=JSON.stringify(myParam1);
		//	alert("inside factory");
							
							return $resource('http://localhost:8081/WebServices/rest/profiles/profile8/friends/addFriend');
							
						
		 			 }
		]
);

searchFrndsService.factory('searchProfile',
		['$resource',function($resource)
		 			 {
									return $resource('http://localhost:8081/WebServices/rest/profiles/profile8/friends/search?searchedString=:searchedString');
							
						
		 			 }
		]
);

searchFrndsService.factory('viewProfileDetails',
		['$resource',function($resource)
		 			 {
							//alert("ssssssss");
							//var myParam1='{"requester": "Saimon", "accepter": "saimon@devdactic.com","status":"0"}';
							//var myParam=JSON.stringify(myParam1);
		//	alert("inside factory");
							
							return $resource('http://localhost:8081/WebServices/rest/profiles/test/');
							
						
		 			 }
		]
);

