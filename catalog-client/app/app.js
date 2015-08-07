var catalogApp = angular.module('catalogApp', [
    'ngRoute',
    'catalogServices',

    // controllers:
    'itemControllers',
    'categoriesControllers',
    'propertiesControllers'
]);

catalogApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.

            // items
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
            when('/items/:id/configurations', {
                templateUrl: 'app/items/configurations.html',
                controller: 'ConfigurationCtrl'
            }).

            // categories
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

            // properties

            when('/properties/', {
                templateUrl: 'app/properties/list.html',
                controller: 'ListPropertiesCtrl'
            }).
            when('/properties/new', {
                templateUrl: 'app/properties/create.html',
                controller: 'CreatePropertiesCtrl'
            }).
            when('/properties/:id', {
                templateUrl: 'app/properties/edit.html',
                controller: 'EditPropertiesCtrl'
            }).

            otherwise({
                redirectTo: '/items'
            });
    }]);

