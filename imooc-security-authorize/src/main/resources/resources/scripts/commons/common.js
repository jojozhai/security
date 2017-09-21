//消息提示控件的默认配置
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
angular.module('common', ['ui.bootstrap', 'ui.uploader']).service("commonService", function($location, $uibModal,uiUploader){
	
	this.uploadImage = function(files, scope, callback){
		uiUploader.addFiles(files);
		uiUploader.startUpload({
            url: 'image/upload',
            onCompleted: function(file, response) {
            	callback(angular.fromJson(response).content);
            	uiUploader.removeAll();
            	scope.$apply();
            }
        });
	}
	
	/*
	 * 获取url，截#前面的部分，和传入的参数组装后返回
	 * */
	this.getDomainUrl = function(url){
		var prefix = $location.absUrl().substring(0, $location.absUrl().indexOf("#"));
		return prefix + "#" + url;
	}
	
	this.getDomain = function(url){
		var prefix = $location.absUrl().substring(0, $location.absUrl().indexOf("#"));
		return prefix + url;
	}
	
	this.getShareLink = function(link){
		var url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
		"appid=" + weixinAppId +
		"&redirect_uri=" + oauthCallbackUrl +
		"&response_type=code" +
		"&scope=snsapi_userinfo" +
		"&state=" + encodeURIComponent(link) + 
		"#wechat_redirect";
		console.log(url);
		return url;
	}
	
	var DefaultRestSetting = function() {
		this.create = {method: "POST"};
		this.save = {method: "PUT"};
		this.query = {isArray: false};
	}
	var DefaultPageSetting = function(){
		this.totalElements = 0;
		this.totalPages = 0;
		this.maxSize = 10;
		this.page = 1;
		this.size = (typeof defaultPageSize === 'undefined')?10:defaultPageSize;
		this.sort = "createdTime,desc";
	}
	
	var TinymceOptions = function(){ 
		this.plugins = [
          "advlist autolink autosave link image imagetools lists charmap print preview hr anchor pagebreak spellchecker",
          "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
          "table contextmenu directionality emoticons template textcolor paste fullpage textcolor colorpicker textpattern"
        ];
        this.toolbar = 'fontsizeselect bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | forecolor backcolor | link image fullscreen ';
        this.menubar = false;
        this.toolbar_items_size = 'small';
	};
	
	var DefaultTreeSetting = function() {
		this.injectClasses = {
			"iExpanded" : "fa fa-folder-open-o m-right-xs folder-icon",
			"iCollapsed" : "fa fa-folder-o m-right-xs folder-icon",
			"iLeaf" : "fa fa-file-o m-right-xs",
			"labelSelected" : "menuLabelBorder"
		}
	}
	
	this.getDefaultTinymceOptions = function() {
		return new TinymceOptions();
	}
	this.getDefaultPageSetting = function() {
		return new DefaultPageSetting();
	}
	this.getDefaultRestSetting = function() {
		return new DefaultRestSetting();
	}
	this.getDefaultTreeSetting = function() {
		return new DefaultTreeSetting();
	}
	
	/*
	 * 显示一个确认消息.参数是要显示的消息标题和附加的信息(可以不传),返回一个$uibModalInstance实例。
	 * 本方法用来让用户确认信息,消息提示框中有确认和取消两个按钮，当用户点击确定时会回调uibModalInstance指定的方法，示例如下:
	 * $scope.showConfirm("您确认要xxxxx?").result.then(function() {
	 * 		do something 用户点击确定时会调用此方法.	
	 * });
	 */
	this.showConfirm = function(title, message) {
		return $uibModal.open({
			templateUrl : 'views/commons/confirm.html',
			controller: 'confirmCtrl',
			resolve: {
				title : function() {
		        	return title;
				},
		        message : function() {
		        	return message;
				}
			}
		});
	};
	/*
	 * 用于显示一个消息给用户。
	 * 注意：用这个方法显示的消息都是操作成功的消息，如果服务器抛出异常，系统会自动显示错误消息，不需要开发人员处理
	 */
	this.showMessage = function(message) {
		toastr["success"](message);
	};
	
	this.showInfo = function(message, title) {
		toastr["info"](message, title);
	};
	
	this.showError = function(message) {
		toastr["error"](message);
	};
	
	this.showWarning = function(message) {
		toastr["warning"](message);
	};
	
	/*
	 * 向树控件中添加一个节点
	 */
	this.addNode = function(nodes, parentNode, node) {
		if(angular.isDefined(parentNode)){
			if(angular.isDefined(nodes) && angular.isArray(nodes)) {
				for (var i = 0; i < nodes.length; i++) {
					if(nodes[i].id == parentNode.id) {
						nodes[i].children.push(node);
						return;
					}else{
						this.addNode(nodes[i].children, parentNode, node);
					}
				}
			}
		}else{
			nodes.push(node);
		}
	}
	/*
	 * 从树控件中移除一个节点
	 */
	this.removeNode = function(nodes, id) {
		if(angular.isDefined(nodes) && angular.isArray(nodes)) {
			for (var i = 0; i < nodes.length; i++) {
				if(nodes[i].id == id) {
					nodes.splice(i, 1);
					return;
				}else{
					this.removeNode(nodes[i].children, id);
				}
			}
		}
	}
	
	/*
	 * 从一级节点开始，递归获取所有节点并返回
	 */
	this.getAllNode = function(result, nodes) {
		if(angular.isDefined(nodes) && angular.isArray(nodes)) {
			for (var i = 0; i < nodes.length; i++) {
				result.push(nodes[i]);
				this.getAllNode(result, nodes[i].children);
			}
		}
		return result;
	}
	/*
	 * 遍历树的所有节点并执行回调
	 */
	this.forEachNode = function(nodes, callback) {
		for (var i = 0; i < nodes.length; i++) {
			callback(nodes[i]);
			this.forEachNode(nodes[i].children, callback);
		}
	}
	/*
	 * 创建包含分页信息的查询条件
	 */
	this.buildPageCondition = function(condition, pageInfo) {
		var result = angular.isDefined(condition)?condition:{}
		result.page = pageInfo.page > 0 ? pageInfo.page-1 : 0;
		result.size = pageInfo.size;
		result.sort = pageInfo.sort;
		return result;
	}
	
	var moveArrayItem = function(items, target, direction) {
		var index1 = 0;
		for (var i = 0; i < items.length; i++) {
			if(items[i].id == target.id){
				index1 = i;
				break;
			}
		}
		if(direction == "up"){
			if(index1 == 0){
				return items;
			}else{
				items[index1] = items.splice(index1-1, 1, items[index1])[0];
				return items;
			}
		}
		
		if(direction == "down"){
			if(index1 == items.length -1){
				return items;
			}else{
				items[index1] = items.splice(index1+1, 1, items[index1])[0];
				return items;
			}
		}
	}
	
	this.moveArrayItem = moveArrayItem;
	
	this.moveNode = function(root, parentId, target, direction) {
		this.forEachNode([root], function(node){
			if(node.id == parentId) {
				return moveArrayItem(node.children, target, direction);
			}
		});
	}
	
}).filter("toHtml", function($sce){
	return function (text) {  
        return $sce.trustAsHtml(text);  
    }
}).filter("yesOrNo", function(){
	return function (text) {
		if(text == 'true'){
			return "是";
		}else if(text == 'false'){
			return "-";
		}else{
			if(text){
				return "是";
			}else{
				return "-";
			}
		}
    }
}).filter("dateType", function(){
	return function (text) {
		if(text == 'YEAR'){
			return "年";
		}else if(text == 'MONTH'){
			return "月";
		}else if(text == 'DAY'){
			return "日";
		}
    }
}).filter("sex", function(){
	return function (text) {
		if(text == '0'){
			return "未知";
		}else if(text == '1'){
			return "男";
		}else if(text == '2'){
			return "女";
		}
        return "未知";
    }
}).filter("dateDesc", function(){
	return function (date, postfix) {
		
		var value = new Date().getTime() - new Date(date).getTime();
		
		if(value < 0) {
			value = new Date(date).getTime() - new Date().getTime();
		}
		
		var secondMil = 1000;
		var minuteMil = secondMil*60;
		var hourMil = minuteMil*60;
		var dayMil = hourMil*24;
		var weekMil = dayMil*7;
		var monthMil = dayMil*30;
		var yearMil = dayMil*365;
		
		var year= Math.floor(value/yearMil);
		var month=Math.floor(value/monthMil);
		var week=Math.floor(value/weekMil);
		var day=Math.floor(value/dayMil);
		var hour=Math.floor(value/hourMil);
		var min=Math.floor(value/minuteMil);
		var s=Math.floor(value/secondMil);
		
		var result = "";
		if(year > 0){
			result=year+"年";
		}else if(month > 0){
			result=month+"月";
		}else if(week > 0){
			result=week+"周";
		}else if(day > 0){
			result=day+"天";
		}else if(hour > 0 ) {
			result=hour+"小时";
		}else if(min > 0 ){
			result=min+"分钟";
		}else{
			if(s == 0){
				return "刚刚";
			}else{
				result=s+"秒";
			}
		}
		return result + (postfix != null?postfix:"前");
		
    }
}).directive('pzUploader', [ function() {
		return {
			restrict : 'A',
			scope: {
				doUpload : "&action"
			},
			link : function(scope, element, attrs) {
				element.bind('change', function(e) {
					scope.doUpload({files : e.target.files});
				});
			}
		}
	}
]).directive('imageloaded', [ function() {
		return {
			restrict : 'A',
			link : function(scope, element, attrs) {
				var cssClass = attrs.loadedclass;
				element.bind('load', function(e) {
					angular.element(element).addClass(cssClass);
				});
			}
		}
	}
]).directive('setFocus', function(){
    return function(scope, element){
        element[0].focus();
      };
}).directive('pzInput', function(){
    return function(scope, element){
    	var cleaner = $("<span class='emptybtn'></span>");
    	cleaner.on("click", function(){
    		element.val("");
    		$(".emptybtn").hide();
    		element.focus();
    		scope.$emit('conditionClean');
    	});
    	element.after(cleaner);
    	element.on("focus",function(){
    		if(element.val()!=""){
    			$(".emptybtn").show();
    		}else{
    			$(".emptybtn").hide();
    		}
    	});
    	element.on("input",function(){
    		if(element.val()!=""){
    			$(".emptybtn").show();
    		}else{
    			$(".emptybtn").hide();
    		}
    	});
    };
}).directive('backButton', function($location){
    return {
        restrict: 'A',

        link: function(scope, element, attrs) {
        	element.bind('click', goBack);

        	function goBack() { 
				if (history.length > 0) {
					history.back();
					scope.$apply();
				}else{
					var backUrl = attrs["backButton"];
					$location.url(backUrl);
					scope.$apply();
				}
        	}
        }
    }
});

function isEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
		return true;
	} else {
		return false;
	}
}

Date.prototype.format =function(format)
{
var o = {
"M+" : this.getMonth()+1, // month
"d+" : this.getDate(), //day
"h+" : this.getHours(), //hour
"m+" : this.getMinutes(), //minute
"s+" : this.getSeconds(), //second
"q+" : Math.floor((this.getMonth()+3)/3), //quarter
"S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
(this.getFullYear()+"").substr(4- RegExp.$1.length));
for(var k in o)if(new RegExp("("+ k +")").test(format))
format = format.replace(RegExp.$1,
RegExp.$1.length==1? o[k] :
("00"+ o[k]).substr((""+ o[k]).length));
return format;
}