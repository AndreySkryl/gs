todoListApp.controller('usersController', ['$scope', '$rootScope', 'colleagueService', 'userService', function($scope, $rootScope, colleagueService, userService) {
	$scope.users = [];
	var guidOfUser = '46ff4f70-061f-11e6-89e7-7c050741ff67';

	var syncModelWithServer = function () {
		var promise = userService.getAllUsers(guidOfUser);

		promise.success(function(data, status, headers, config) {
			$scope.users = data;
		}).error(function(data, status, headers, config) {
			alert(status);
		});
	};
	syncModelWithServer();

	$scope.newColleague = function (user) {
		var promise = colleagueService.newColleague(guidOfUser, user.guid);

		promise.success(function(data, status, headers, config) {
			syncModelWithServer();
			$rootScope.$emit('users::updated');
		}).error(function(data, status, headers, config) {
			alert(status);
		});
	};

	$rootScope.$on('colleagues::updated', function (event, data) {
		syncModelWithServer();
	});
}]);