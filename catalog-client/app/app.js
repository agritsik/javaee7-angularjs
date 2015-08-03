var catalogApp = angular.module('catalogApp', [
    'ngRoute',
    'restangular',
    "checklist-model",
    //'angularjs-dropdown-multiselect',
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
            when('/categories/:pid/properties/', {
                templateUrl: 'app/properties/list.html',
                controller: 'ListPropertiesCtrl'
            }).
            when('/categories/:pid/properties/new', {
                templateUrl: 'app/properties/create.html',
                controller: 'CreatePropertiesCtrl'
            }).
            when('/categories/:pid/properties/:id', {
                templateUrl: 'app/properties/edit.html',
                controller: 'EditPropertiesCtrl'
            }).



            otherwise({
                redirectTo: '/items'
            });
    }]);

catalogApp.config(function(RestangularProvider) {
    RestangularProvider.setBaseUrl('http://192.168.59.103:8080/app/resources');
    RestangularProvider.setRequestInterceptor(function(elem, operation) {
        if (operation === "remove") {
            return undefined;
        }
        return elem;
    });
});