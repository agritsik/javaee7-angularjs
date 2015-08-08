var catalogApp = angular.module('catalogApp', [
    'ngRoute',
    'catalogServices',

    // controllers:
    'playerControllers',
    'countriesControllers',
    'clubsControllers'
]);

catalogApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.

            // players
            when('/players', {
                templateUrl: 'app/players/list.html',
                controller: 'ListCtrl'
            }).
            when('/players/new', {
                templateUrl: 'app/players/create.html',
                controller: 'CreateCtrl'
            }).
            when('/players/:id', {
                templateUrl: 'app/players/edit.html',
                controller: 'EditCtrl'
            }).
            when('/players/:id/careers', {
                templateUrl: 'app/players/careers.html',
                controller: 'CareerCtrl'
            }).

            // countries
            when('/countries', {
                templateUrl: 'app/countries/list.html',
                controller: 'ListCountryCtrl'
            }).
            when('/countries/new', {
                templateUrl: 'app/countries/create.html',
                controller: 'CreateCountryCtrl'
            }).
            when('/countries/:id', {
                templateUrl: 'app/countries/edit.html',
                controller: 'EditCountryCtrl'
            }).

            // clubs
            when('/clubs/', {
                templateUrl: 'app/clubs/list.html',
                controller: 'ListClubCtrl'
            }).
            when('/clubs/new', {
                templateUrl: 'app/clubs/create.html',
                controller: 'CreateClubCtrl'
            }).
            when('/clubs/:id', {
                templateUrl: 'app/clubs/edit.html',
                controller: 'EditClubCtrl'
            }).

            otherwise({
                redirectTo: '/players'
            });
    }]);

