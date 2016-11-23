var leftPanel=angular.module('searchFrnds1',['searchFrndsService']);

leftPanel.controller('leftPanelCtrl',['$scope','friendsResult','sendFriendRequest','searchProfile','viewProfileDetails',function($scope,friendsResult,sendFriendRequest,searchProfile,viewProfileDetails){
	$scope.serachData={};		
		
			$scope.loadFriends=function(){
				friendsResult.query(function(data){
					alert("leftPanelCtrl");
					var res=data;
					console.log(res);
					for (var int = 0; int < res.length; int++) {
						console.log("friends:"+res[int]);
					}
					$scope.res=res;
				})
			};
			//$scope.sendFriendRequest="{'requester': 'Saimon', 'accepter': 'saimon@devdactic.com','status':'0'}";
			
			$scope.searchPTest=function(value){
				alert("seraching friends with searchPTest "+value);
								
				$scope.data=searchProfile.get({searchedString:value});
								
				$scope.data.$promise.then(function(data){
					console.log(data);
					
					alert("data sdsdsd::"+data.profileName);
					
					serachData=data;
					
				})
				location.href="/WebServices/searchFriends.html";
				
				
				
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
					location.href="/WebServices/friend.html";
				};
				
				
} ]);















/*sampleApp .config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/addOrder', {
        templateUrl: 'templates/add-order.html',
        controller: 'AddOrderController'
      }).
      when('/showOrders', {
        templateUrl: 'templates/show-orders.html',
        controller: 'ShowOrdersController'
      }).
      otherwise({
        redirectTo: '/addOrder'
      });
  }]);*/




















	