
var catalogApp = angular.module('catalogApp', [
    'ngRoute',
    'catalogServices',
    'itemControllers'
]);

catalogApp.config(['$routeProvider',
    function($routeProvider) {
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
            otherwise({
                redirectTo: '/items'
            });
    }]);