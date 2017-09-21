'use strict';
angular.module('platform').controller('contentCtrl', function ($scope, $state, resourceRestService) {
    $scope.isSidebarFold = false;
    //获取菜单列表信息
    resourceRestService.query().$promise.then(function (result) {
        $scope.menus = result.children;
    });

    $scope.isProductNavBarFold = true;

    $scope.collapseProductNavbar = function () {
        $scope.isProductNavBarFold = !$scope.isProductNavBarFold;
    };

    // 设置菜单高亮
    $scope.getMenuItemPropClasses = function (item) {
        return isActive(item) ? ' active' : '';
    };

    // 是否处于激活状态
    function isActive(item) {
        if (!item) return;
        if (item.link === '') {
            var foundActive = false;
            angular.forEach(item.children, function (value) {
                if (isActive(value)) {
                    foundActive = true;
                }
            });
            return foundActive;
        }
        else {
            return $state.is(item.link) || $state.includes(item.link);
        }
    }

    $scope.toggleSidebarStatus = function () {
        $scope.isSidebarFold = !$scope.isSidebarFold;
    };

    $scope.clickSubMenu = function (submenu) {
        $scope.currentSubmenu = submenu;
        if (submenu.link != null && submenu.link != '') {
            $state.go("index." + menu.link);
        }
    }

    $scope.clickMenu = function (menu) {
        /*for (var i = 0; i < $scope.menus.length; i++) {
         if(menu.id != $scope.menus[i].id) {
         $scope.menus[i].open = false;
         }
         }*/
        menu.open = !menu.open;
        if (menu.icon == null || menu.icon == '') {
            $scope.currentMenu = menu;
        }
        if (menu.link != null && menu.link != '') {
            $state.go("index." + menu.link);
        }
    }
});