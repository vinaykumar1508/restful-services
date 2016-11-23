var myapp=angular.module('elasticSearchDo',['elasticsearch','chart.js']);


myapp.service('client', function (esFactory) {
		return esFactory({
				host: 'http://localhost:9200/'
});
});


myapp.controller('elasticSearchController', function($scope, client) {

	$scope.test = function(){
		client.ping({
			  requestTimeout: 4000,
			  hello: "elasticsearch!"
			}, function (error) {
			  if (error) {
			    console.error('elasticsearch cluster is down!');
			  } else {
			    console.log('All is well');
			  }
			})
	};
	
	setTimeout($scope.test, 3000);
	var statsResult=[];
	
	//loadUniqueCounts();
	
	//loadFailureCounts();
	
	$scope.search=function(interval){
	//alert('searching'+interval);
	console.log('interval:'+interval);
	//var interval='day';
	client.search({
	    //index: 'twitter',
	    size: 50,
	    body: {
	    
	    		"query":{
	    		
	    		  "bool": {
	    		     "must": [
	    		       {"match": { "entry.useCase": "xml_validation" }},  
	    		       {"match": { "context.market": "test" }}
	    		     ],"filter" : {
	    		    	 "range": { "@timestamp": { "gte": "now-d/d" , "lte" : "now/d" }}
	    		    	 
	    		    }
	    		  }
	    		},"aggs":
		        {
			    	   "distinct_value" :{
			    		   "date_histogram":{
			    			   "field" :"@timestamp",
			    			   "format":"dd-MM-yyyy",
			    			   "interval":"day"
			    			   
			    		   }
			    	   }
			    		
			    	}
	    	
				
		}
	    }).then(function (response) 
	    		{
	    	console.log (response);
	    	/*
	    	
	    	
	    	
	     // $scope.hits = response.aggs.distinct_date.value;
	      var value=response.aggregations.distinct_value.buckets.length;
	      alert('bucket count:'+value);
	      $scope.hits = response.aggregations.distinct_value.buckets;
	      var dataCount=[];
	      
	      for (var i=0;i<value;i++){
	    	  var count=response.aggregations.distinct_value.buckets[i].doc_count;
	    	  dataCount.push(count);
	    	 // alert('doc count:'+count);
	    	  
	      }
	      $scope.data=[dataCount];
	      
	      
	      //alert('test length:'+dataCount.length);
	      var labelMap=[];
	      for(var i=0;i<value;i++){
	    	  labelMap.push($scope.hits[i].key_as_string);
	    	  //alert('range:'+$scope.hits[i].key_as_string);
	    	  
	      }
	      $scope.labels=labelMap;
	      
	    */});
	}
	
	
	
      
      
      $scope.labels = ['2006', '2007'];
      $scope.series = ['Series A'];
      $scope.listOfOptions = ['hour', 'day', 'month','week','quarter','year'];
      $scope.listOfRangeCount = ['Today', 'Yesterday', 'Day before yesterday','Previous week'];
      
      
      $scope.loadUniqueCounts= function(){
    	  console.log("loadUniqueCounts:");
    	 
    	  client.search({
  			body:{
  		    	
  		    	
  		    	"aggs" : {
  		            "per_xml_count" : {
  		                "filter" : { "match": { "entry.useCase": "XML_VALIDATION" } },
  		                "aggs" : {
  		                    "distinct_count" : { "terms" : { "field" : "context.xml_type" } }
  		                }
  		            }
  		        }
  		    	
  		    	
  		    }
  		}).then(function(response){
  			
  			console.log(response);
  			/*var buckets=response.aggregations.per_xml_count.distinct_count.buckets;
  			var len=buckets.length;
  			
  			for(var i=0;i<len;i++){
  				var xml_type=buckets[i].key;
  				var count=buckets[i].doc_count;
  				
  				statsResult.push({ "xml_type":xml_type,"total_count":count });
  			}
  			*/
  			//loadYesterdayCounts();
  		});
    	  
      }	 
      
      function loadYesterdayCounts(){
      	console.log("loadYesterdayCounts");
      	 client.search({
     		  size:10,
     		  body:{
     							"query": 
     							{
     					    		
     					    		  "bool": {
     					    		     "must": {
     					    		       "match": { "entry.useCase": "cnj-ris" }
     					    		     },"filter" : {
     					    		    	 "range": { "@timestamp": { "gt": "now-2d/d" , "lte" : "now-d/d" }}
     					    		    	 
     					    		    }
     					    		  }
     					    		
     					    	
     					    	},"aggs":{
     								"distinct_count":{
     									"terms" : { "field" : "context.xml_type" }
     								}
     							}
     					
     			
     			}
     	  }).then(function(response){
     		 //console.log(response);
     			//console.log(statsResult);
     			var buckets=response.aggregations.distinct_count.buckets;
     			var len_buckets=buckets.length;
     			//console.log("len_buckets:"+len_buckets);
     			for(var i in statsResult ){
     					var xml_type=statsResult[i].xml_type;
     					for(var j in buckets){
	     					if(xml_type == buckets[j].key){
	     						//console.log('found:statsResult[i].xml_type:'+statsResult[i].xml_type+":buckets[j].key:"+buckets[j].key);
	     						statsResult[i]['yesterday_count']=buckets[j].doc_count;
	     					}
	     					if( statsResult[i].yesterday_count==undefined){	
	     						statsResult[i]['yesterday_count']=0;
		     				}
	     				}
	     		}
     						
     			for(var i in statsResult ){
     				//console.log(statsResult[i]);
     			}
     			
     			loadPreviousDayCounts();
     			
     		}
     			  
     	  
     	  )
       };
      
       function loadPreviousDayCounts(){

       	console.log("loadPreviousdayCounts");
       	 client.search({
      		  size:50,
      		  body:{
      							"query": 
      							{
     					    	  "bool": {
   					    		     "must": {
   					    		       "match": { "entry.useCase": "cnj-ris" }
   					    		     },"filter" : {
   					    		    	 "range": { "@timestamp": { "gt": "now-3d/d" , "lte" : "now-2d/d" }}
   					    		    }
   					    		  }
   					    		},
      							"aggs":{
      								"distinct_count":{
      									"terms" : { "field" : "context.xml_type" }
      								}
      							}
      			}
      	  }).then(function(response){
      		  	//console.log(response);
      		  	var buckets=response.aggregations.distinct_count.buckets;
      			var len_buckets=buckets.length;
      			
      			for(var i in statsResult ){
      				for(var j in buckets){
      					if(statsResult[i].xml_type == buckets[j].key){
      						//console.log("statsResult[i].xml_type :"+statsResult[i].xml_type+":buckets[j].key:"+buckets[j].key);
      						statsResult[i]['previousDay_count']=buckets[j].doc_count;
      					}
      					if( statsResult[i].previousDay_count==undefined){	
     						statsResult[i]['previousDay_count']=0;
	     				}
      				}
      				
      			}
      						
      			for(var i in statsResult ){
      				//console.log(statsResult[i]);
      			}
      			
      			loadWeeklyAvarege();
      		}
      			  
      	  
      	  )
        
     }
      
      function loadWeeklyAvarege(){
    	  console.log("loadWeeklyAvarege");
    	  client.search({
    		  
    		  index:'twitter',
    		  size:50,
    		  body:{
    				"query": 
    				{
				    	  "bool": {
				    		     "must": {
				    		       "match": { "entry.useCase": "cnj-ris" }
				    		     },"filter" : {
				    		    	 "range": { "@timestamp": { "gt": "now-7d/d" , "lte" : "now/d" }}
				    		    }
				    		  }
				    },"aggs":
				    {
    						"distinct_count":
    						{
    							"terms" : { "field" : "context.xml_type" }
    						}
    				}
    			}
    	  }).then(function(response){
    		  console.log(response);
    		  var buckets=response.aggregations.distinct_count.buckets;
  			  var len_buckets=buckets.length;
  			
  			for(var i in statsResult ){
  				for(var j in buckets){
  					if(statsResult[i].xml_type == buckets[j].key){
  						//console.log("statsResult[i].xml_type :"+statsResult[i].xml_type+":buckets[j].key:"+buckets[j].key);
  						statsResult[i]['last7daysAverage']=Math.round(((buckets[j].doc_count/7))*100)/100;
  					}
  					if( statsResult[i].last7daysAverage==undefined){	
 						statsResult[i]['last7daysAverage']=0;
     				}
  				}
  			}
  						
  			for(var i in statsResult ){
  				//console.log(statsResult[i]);
  			}
  			
  			loadFailureCounts();
    	  })
      };
      
      
      
     function loadFailureCounts(){
    	console.log('loadFailureCounts');
    	 client.search({
    		 
    		 body:{
    			 
    			 "query":
    			 {
    				 "bool":
    				 {
    					 "should":
    					  [
    				       	{
    				    	   "match":{ "entry.useCase": "cnj-ris" }
    				       	},
    				       	{
    				    	   "match":{ "entry.result": "failure" }
    				       	}
    				      ],
    				      "minimum_should_match" : 2,
    				      "boost" : 1.0
    				 }
    			 },
    			 "aggs":{
    				 "distinct_count":{
    					 "terms" : { "field" : "context.xml_type" }
    				 }
    			 }
    		 }
    		 
    	 }).then(function(response){
    		 	//console.log(response);
    			var buckets=response.aggregations.distinct_count.buckets;
    			var len_buckets=buckets.length;
    			
    			for(var i in statsResult ){
    				for(var j in buckets){
    					if(statsResult[i].xml_type == buckets[j].key){
    						console.log("statsResult[i].xml_type :"+statsResult[i].xml_type+":buckets[j].key:"+buckets[j].key);
    						statsResult[i]['failure_count']=buckets[j].doc_count;
    					}
    					if( statsResult[i].failure_count==undefined){	
     						statsResult[i]['failure_count']=0;
         				}
    				}
    				
    			}
    						
    			for(var i in statsResult ){
    				console.log(statsResult[i]);
    			}
    			
    			
    		
    	 })
     };
     
     $scope.statsResult=statsResult;
});

