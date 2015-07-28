var catalogApp = angular.module('catalogApp', [
    'ngRoute',
    'catalogServices',

    // controllers:
    'itemControllers',
    'categoriesControllers'
]);

catalogApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.

            when('/items', {
                templateUrl: 'app/items/list.html',
                controller: 'ListCtrl'
            }).
            when('/items/new', {
                templateUrl: 'app/items/create.html',
                controller: 'CreateCtrl'
            }).
            when('/items/:id', {
                templateUrl: 'app/items/edit.html',
                controller: 'EditCtrl'
            }).

            when('/categories', {
                templateUrl: 'app/categories/list.html',
                controller: 'ListCategoriesCtrl'
            }).
            when('/categories/new', {
                templateUrl: 'app/categories/create.html',
                controller: 'CreateCategoriesCtrl'
            }).
            when('/categories/:id', {
                templateUrl: 'app/categories/edit.html',
                controller: 'EditCategoriesCtrl'
            }).

            otherwise({
                redirectTo: '/items'
            });
    }]);
