(function () {
	'use strict';

	angular.module('todoListApp')
		.controller('ColleaguesController', ['$scope', '$rootScope', 'colleagueService',
			function($scope, $rootScope, colleagueService) {
			$scope.colleagues = [];

			var guidOfUser = '46ff4f70-061f-11e6-89e7-7c050741ff67';

			var syncModelWithServer = function () {
				var promise = colleagueService.getAllColleaguesAsUsers(guidOfUser);

				promise.success(function(data, status, headers, config) {
					$scope.colleagues = data;
				}).error(function(data, status, headers, config) {
					alert(status);
				});
			};
			syncModelWithServer();

			$scope.deleteOneColleague = function (colleague) {
				var promise = colleagueService.deleteOneColleague(guidOfUser, colleague.guid);

				promise.success(function(data, status, headers, config) {
					syncModelWithServer();
					$rootScope.$emit('colleagues::updated');
				}).error(function(data, status, headers, config) {
					alert(status);
				});
			};

			// для реагирования на кнопку "добавить в коллеги" из контроллера "usersController"
			$rootScope.$on('users::updated', function (event, data) {
				syncModelWithServer();
			});
		}]);
})();