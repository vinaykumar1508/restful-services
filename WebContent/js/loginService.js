var loginService=angular.module('loginService',['ngResource']);

loginService.factory('loginFactory',['$resource',function($resource){
	
		return $resource('http://localhost:8081/WebServices/rest/Authentication?userName=:username'+'&password=:password',
				{
				query:	{'method': 'GET', 'params': {}, isArray: true}
				
				});
}]);

loginService.factory('profileDetails',['$resource',function($resource){
	
	  return $resource('http://localhost:8081/WebServices/rest/profiles/:username/messages',
		{
			query:	{'method': 'GET', 'params': {}, isArray: false}
	
		});
		
	
}]);

loginService.factory('addComment',['$resource',function($resource){
	
	/*var url="rest/profiles/"+profileName;*/
	/*rest/profiles/'+profileName+'/messages'
	 * http://localhost:8081/WebServices/rest/messages/1/comments/3 ;
*/		return $resource('http://localhost:8081/WebServices/rest/messages/:messageId/comments');
		
	
}]);
	
