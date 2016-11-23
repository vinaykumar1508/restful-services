var myapp=angular.module('searchFrnds',['searchFrndsService','loginService']);

myapp.controller('searchfrndsCtrl',['$scope','friendsResult','sendFriendRequest','searchProfile','viewProfileDetails',function($scope,friendsResult,sendFriendRequest,searchProfile,viewProfileDetails){
			var searchData={};
			$scope.loadFriends=function(){
				friendsResult.query(function(data){
					alert("data::"+data);
					var res=data;
					console.log(res);
					for (var int = 0; int < res.length; int++) {
						console.log("friends:"+res[int]);
					}
					$scope.res=res;
				})
			};
			
			
			$scope.searchProfile1=function(value){
				alert("seraching friends "+value);
								
				$scope.data=searchProfile.get({searchedString:value});
				$scope.data.$promise.then(function(data){
					console.log(data);
					var searchedFriendResult=data;
					alert("data::"+data.profileName);
					searchData=data;	
				})
				location.href="/WebServices/searchFriends.html";
			};
			$scope.searchData=searchData;
			
			$scope.testme=function(){
				alert("$scope.searchData::"+$scope.searchData);
				console.log("scope data firstName::"+$scope.searchData.firstName);
				$scope.searchData=$scope.searchData;
			};
			
			$scope.sendFriendRequestUI=function(value){
					alert("inside adduser..."+value);
					sendFriendRequest.save({'requester': 'profile8', 'accepter': value,'status':'0'});
			};
				
			$scope.testfunction=function(){
					//alert("inside test funtion");
					//console.log("inside test funtion");
					viewProfileDetails.get(function(data){
						//alert("inside getProfileDetails funtion':"+data.firstName);
						$scope.profileDetails=data;
						console.log("test me:: "+$scope.profileDetails);
						
					})
					
			};
				
				
			$scope.getProfileDetails=function(){
					location.href="/WebServices/personalInfo.html";
					console.log("inside getProfileDetails funtion");

			};
				
				//location.href="/WebServices/personalInfo.html";
				
			$scope.myFriends=function(){
					alert("myFriends is clicked.");
					location.href="/WebServices/friend.html";
			};
				
				
} ]);






















	