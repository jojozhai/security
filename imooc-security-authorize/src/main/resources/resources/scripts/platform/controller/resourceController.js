'use strict';
angular.module('platform').controller('resourceManageCtrl', function($scope, $uibModal, resourceRestService, commonService) {

	$scope.treeOptions = commonService.getDefaultTreeSetting();
	
	$scope.treedata = resourceRestService.query();
	
	$scope.expandedNodes = [];
	
	$scope.create = function() {
		$scope.save({type: 'MENU'});
	}
	
	$scope.update = function(resource) {
		$scope.save(resource);
	}
	
	$scope.selecteMenu = function(node, selected) {
		if(selected) {
			$scope.expandedNodes.push(node);
		}else{
			for (var i = 0; i < $scope.expandedNodes.length; i++) {
				if($scope.expandedNodes[i].id == node.id) {
					$scope.expandedNodes.splice($scope.expandedNodes.indexOf(node), 1);
				}
			}
		}
		$scope.selected = selected;
	}
	
	$scope.save = function(resource){
		$uibModal.open({
			templateUrl : 'views/platform/resourceForm.html',
			controller: 'resourceFormCtrl',
			resolve: {
		        resource : function() {return resource;}
			}
		}).result.then(function(formInfo){
			if($scope.selected){
				formInfo.parentId = $scope.resource.id;
			}
			if(formInfo.id){
				new resourceRestService(formInfo).$save().then(function(){
					commonService.showMessage("修改菜单信息成功");
				});
			}else{
				new resourceRestService(formInfo).$create().then(function(resource){
					commonService.addNode($scope.treedata.children, $scope.resource, resource);
					commonService.showMessage("新建菜单成功");
				});
			}
		});
	}
	
	$scope.remove = function(resource) {
		commonService.showConfirm("您确认要删除此菜单?", "所有的子菜单会被一并删除").result.then(function() {
			resourceRestService.remove({id:resource.id});
		}).then(function(){
			commonService.removeNode($scope.treedata.children, resource.id);
			$scope.resource = {};
			$scope.selecteMenu(resource, false);
			commonService.showMessage("删除菜单项成功");
		});
	} 
	
	$scope.moveUp = function(resource) {
		resourceRestService.moveUp({id:resource.id}).$promise.then(function(result){
			commonService.moveNode($scope.treedata, result.content, resource, "up");
		});
	}
	
	$scope.moveDown = function(resource) {
		resourceRestService.moveDown({id:resource.id}).$promise.then(function(result){
			commonService.moveNode($scope.treedata, result.content, resource, "down");
		});
	}
	
}).controller('resourceFormCtrl',function ($scope, $uibModalInstance, resource) {
	$scope.resource = resource;
	
	$scope.types = [{name:'菜单', value:'MENU'}, {name:'按钮', value:'BUTTON'}];
	
	$scope.save = function(resource) {
		$uibModalInstance.close(resource);
	};
});
