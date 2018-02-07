var app = angular.module("myApp", []);
app.controller('myController', function($scope,$http) {
	var url=basePath+'/datastandard/getList.do';
	$http.post(url).then(function(json) {
		 console.log(json);
		 json=eval(json);
		 alert(json)
		 $scope.rows = json.rows; 
		 $scope.total = json.total; 
		 $scope.success=10;
	});
});