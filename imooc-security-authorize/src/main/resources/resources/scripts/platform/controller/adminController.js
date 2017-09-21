'use strict';
angular.module('platform').controller('adminManageCtrl', function($scope, $uibModal, adminRestService, roleRestService, commonService) {
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.isConditionCollapsed = true;
	
	$scope.queryRole = function() {
		$scope.roles = roleRestService.query();
	}
	
	$scope.queryAdmin = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		adminRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.admins = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(admin) {
		$scope.save(admin);
	}
	
	$scope.save = function(admin){
		$uibModal.open({
			templateUrl : 'views/platform/adminForm.html',
			controller: 'adminFormCtrl',
			resolve: {
		        admin : function() {return admin;},
		        roles : function() {return $scope.roles;}
			}
		}).result.then(function(formAdmin){
			if(formAdmin.id){
				new adminRestService(formAdmin).$save().then(function(){
					commonService.showMessage("修改管理员信息成功");
				},function(response){
					for (var i = 0; i < $scope.admins.length; i++) {
						if(formAdmin.id == $scope.admins[i].id) {
							$scope.admins[i] = adminRestService.get({id:formAdmin.id});
							break;
						}
					}
				});
			}else{
				new adminRestService(formAdmin).$create().then(function(admin){
					$scope.admins.unshift(admin);
					commonService.showMessage("新建管理员成功");
				});
			}
		});
	}
	
	$scope.remove = function(admin) {
		commonService.showConfirm("您确认要删除此管理员?").result.then(function() {
			adminRestService.remove({id:admin.id});
		}).then(function(){
			commonService.showMessage("删除管理员成功");
			$scope.admins.splice($scope.admins.indexOf(admin), 1);
			if($scope.admins.length == 0){
				$scope.pageInfo.page = $scope.pageInfo.page - 1;
				$scope.query();
			}
		});
	} 
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.queryAdmin();
	}
	
	$scope.changePage = function() {
		$scope.queryAdmin();
	}
	
	$scope.queryAdmin();
	$scope.queryRole();
	
	
}).controller('adminFormCtrl',function ($scope, $uibModalInstance, admin, roles) {

	$scope.admin = admin;
	$scope.roles = roles;
	
	$scope.save = function(admin) {
		$uibModalInstance.close(admin);
	};
	
});