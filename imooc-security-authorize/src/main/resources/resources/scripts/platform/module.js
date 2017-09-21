'use strict';
//平台管理模块的配置
angular.module('platform',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.adminManage', {
		url: "/adminManage",
		controller: "adminManageCtrl",
		templateUrl: "views/platform/adminManage.html",
        resolve: {
            roleList: function (roleRestService) {
                return roleRestService.query();
            },
            adminList: function (adminRestService, commonService) {
                var pageinfo = commonService.getDefaultPageSetting();
                var condition = commonService.buildPageCondition({}, pageinfo);
                return adminRestService.query(condition).$promise.then(function (data) {
                    return data;
                });
            }
        }
	}).state('index.roleManage', {
		url: "/roleManage",
		controller: "roleManageCtrl",
		templateUrl: "views/platform/roleManage.html"
	}).state('index.resourceManage', {
		url: "/resourceManage",
		controller: "resourceManageCtrl",
		templateUrl: "views/platform/resourceManage.html"
	});
//服务配置
}).service("adminRestService", function($resource, commonService){
	var adminRestServiceSetting = commonService.getDefaultRestSetting();
	adminRestServiceSetting.getCurrentAdmin = {method: "GET", url: "admin/me"};
	adminRestServiceSetting.updatePassword = {method: "PUT", url: "admin/password"}
	return $resource("admin/:id", {id:"@id"}, adminRestServiceSetting);
}).service("resourceRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.moveUp = {url:"resource/:id/up", method:"POST"};
	config.moveDown = {url:"resource/:id/down", method:"POST"};
	return $resource("resource/:id", {id:"@id"}, config);
}).service("roleRestService", function($resource, commonService){
	var roleRestServiceSetting = commonService.getDefaultRestSetting();
	roleRestServiceSetting.query = {isArray: true};
	roleRestServiceSetting.updateRoleMenus = {method: "POST", url: "role/:id/resource?ids=:ids"};
	roleRestServiceSetting.getRoleMenus = {method: "GET", url: "role/:id/resource", isArray: true};
	return $resource("role/:id", {id:"@id", ids:"@ids"}, roleRestServiceSetting);
});