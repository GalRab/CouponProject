/**
 * 
 */

var app = angular.module('appLogIn', []);
	app.controller('ctlLogIn', function($scope, $http) {
		
		
		$scope.restURL = "";
		
		$scope.LogINAdmin = function(userType){
			$scope.LogIN("AdminFacade");
		}
		
		$scope.LogINCompany = function(userType){
			$scope.LogIN("CompanyFacade");
		}
		
		$scope.LogINCustomer = function(userType){
			$scope.LogIN("CustomerFacade");
		}
		
		$scope.LogIN = function(userType){
			url = $scope.restURL;					
			$http.post(url, {"userName" : $scope.userName, "password" : $scope.password, "clientType" : userType })
			.then(function(response) {
				$scope.errorDetails ="";				
			}, $scope.handleError);
		}		

	
	
	$scope.handleError = function(response){		
		
		if (response.error != null){
			$scope.errorDetails = "Error: " + response.error;
		}if (response.data != null && response.data.error != null){
			$scope.errorDetails = "Error: " + response.data.error;
		}else{
			$scope.errorDetails = "Error: " + response;
		}
	};
	
}); //end of 
	

