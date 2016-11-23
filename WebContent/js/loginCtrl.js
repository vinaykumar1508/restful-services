var logintest=angular.module('loginDo',['ngRoute','ngCookies','loginService']);
	
logintest.config(['$routeProvider', function($routeProvider){
	$routeProvider
		.when('/register' , { 
			templateUrl:'registration_ang.html',
			controller: 'loginCtrl'
		})
		.when('/login' , { 
			templateUrl:'login.html',
			controller: 'loginCtrl'
		})
		.when('/profilePage/:username' , { 
			templateUrl:'default_ang.html',
			controller: 'loginCtrl'
		})
		.otherwise({
			redirectTo : '/login'
		})
		;
		
}]);


logintest.controller('loginCtrl',['$scope','$routeParams','$cookies','loginFactory','profileDetails','addComment',function($scope,$routeParams,$cookies,loginFactory,profileDetails,addComment){
	
	var tt;
	$scope.getProfileDetails=function(){
		alert("inside getProfileDetails");
		var username = $routeParams.username;
		console.log("profileName.."+username);
		$scope.username=username;
		var obj={'username':username};
		$scope.data=profileDetails.get(obj,function(data){
			alert('user details:'+data);
			
		});
		
	}
	
	$scope.login=function(){
	
		console.log("inside loginCtrl : login function");
		var username=$scope.username;
		var password=$scope.password;
		var obj={'username':username,'password':password};
		$scope.data=loginFactory.get(obj,function(data){
			alert("response received:"+data.resultCode)
			if(data.resultCode){
				$cookies.put('profileName',username);
				alert('login success:');
				tt=$cookies.get('profileName');
				
				location.href="/WebServices/#/profilePage/"+username;
				
			}else{
				alert('login failure.');
				$scope.errorMessage="Invalid credentials. Try again.!!";
			}
		});
		
		
	};
	
	$scope.username=tt;
	
	$scope.addComment=function(data,comment){
		alert('comment::'+comment);
		var username=$scope.username;
		var messageId=data;
		var obj={'messageId':messageId};
		$scope.commentResult=addComment.save(obj,comment,function(data){
			alert('comment added/.');
			if(data!=null){
				alert('redirecting');
				location.href="/WebServices/#/profilePage/"+username;
				
				alert('doneeeeeee');
				
				
			}
		});
	};
	
	function test(){
		//var profileName = $cookies.get('profileName');
		alert('inside test::');
	}
}

]
);

