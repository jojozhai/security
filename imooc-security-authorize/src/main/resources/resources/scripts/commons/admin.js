'use strict';

/**
 * @ngdoc overview
 * @name testApp
 * @description
 * # testApp
 *
 * Main module of the application.
 */
//应用主模块
angular.module('admin', [
	//angular官方模块                           
	'ngAnimate', 'ngCookies', 'ngResource', 'ngSanitize',  
	//第三方模块
	'ui.router', 'ui.bootstrap', 'treeControl', 'ui.uploader', 'ui.tinymce', 'ui.select',
	//自定义模块,所有开发人员自己编写的模块需要在这里注册.
	'common', 'platform'
//常量配置
])
//应用配置	
.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
	//路由定义
	$urlRouterProvider.otherwise("/index");
	$stateProvider.state('index', {
		url: "/index",
		templateUrl: "views/main.html"
	}).state('index.manage', {
		url: "/manage",
		templateUrl: "views/manage.html"
	});
	$httpProvider.interceptors.push(function($q){
		return {
			responseError: function(response){
				if(response.status == 500) {
					toastr["error"](response.data.errorMsg, "系统异常");
				}
				return $q.reject(response);;
			}
		};
	});
//应用主控制器，所有控制器的父，定义整个应用公用的作用域
}).controller('mainCtrl', function($scope, $uibModal, $state, resourceRestService, adminRestService) {
	
	$scope.tinymceOptions = { 
		plugins: [
          "advlist autolink autosave link image lists charmap print preview hr anchor pagebreak spellchecker",
          "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
          "table contextmenu directionality emoticons template textcolor paste fullpage textcolor colorpicker textpattern"
        ],
        toolbar: 'fontsizeselect bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | forecolor backcolor | link ',
        menubar: false,
        toolbar_items_size: 'small'
	};
	
	$scope.isConditionCollapsed = true;
	
	$scope.panelHeight = 400;
	
	$scope.currentAdmin = adminRestService.getCurrentAdmin();
	
	resourceRestService.query().$promise.then(function(result){
		 $scope.menus = result.children;
	});
	
	$scope.clickSubMenu = function(submenu) {
		$scope.currentSubmenu = submenu;
	}
	
	$scope.clickMenu = function(menu) {
		for (var i = 0; i < $scope.menus.length; i++) {
			if(menu.id != $scope.menus[i].id) {
				$scope.menus[i].open = false;
			}
		}
		menu.open = !menu.open;
		if(menu.icon == null || menu.icon == ''){
			$scope.currentMenu = menu;
		}
		if(menu.link != null && menu.link != ''){
			$state.go("index."+menu.link);
		}
	}
//登录控制器
}).controller('signinCtrl', function($scope, $http) {
	$scope.signin = function() {
		$http.post("/auth?username="+$scope.user.username+"&password="+$scope.user.password).success(function(response){
			window.location.href = "index.html";
		});
	}
//消息控制器,消息窗口的控制器,配合主控制器,为所有页面提供消息提示服务.
}).controller('confirmCtrl', function($scope, $uibModalInstance, title, message) {
	$scope.title = title;
	$scope.message = message;
	$scope.ok = function() {
		$uibModalInstance.close();
	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss();
	};
});

toastr.options = {
	"closeButton" : false,
	"debug" : false,
	"newestOnTop" : true,
	"progressBar" : false,
	"positionClass" : "toast-top-right",
	"preventDuplicates" : false,
	"onclick" : null,
	"showDuration" : "300",
	"hideDuration" : "1000",
	"timeOut" : "3000",
	"extendedTimeOut" : "1000",
	"showEasing" : "swing",
	"hideEasing" : "linear",
	"showMethod" : "fadeIn",
	"hideMethod" : "fadeOut"
}