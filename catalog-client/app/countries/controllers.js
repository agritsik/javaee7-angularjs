var controllers = angular.module('countriesControllers', []);

controllers.controller('ListCountryCtrl', ['$scope', 'CountryResource', '$routeParams', '$location', '$route',
    function ($scope, CountryResource, $routeParams, $location, $route) {

        $scope.rows = CountryResource.query();

        $scope.delete = function (row) {
            row.$delete().then(function () {
                $route.reload();
            })
        }

    }]);

controllers.controller('CreateCountryCtrl', ['$scope', 'CountryResource', '$routeParams', '$location', '$route',
    function ($scope, CountryResource, $routeParams, $location, $route) {

        $scope.row = new CountryResource();

        $scope.submit = function () {
            $scope.row.$save().then(function () {
                $location.path("/countries");
            });
        }

    }]);

controllers.controller('EditCountryCtrl', ['$scope', 'CountryResource', '$routeParams', '$location', '$route',
    function ($scope, CountryResource, $routeParams, $location, $route) {


        $scope.row = CountryResource.get({id: $routeParams.id});

        $scope.submit = function () {
            $scope.row.$update().then(function () {
                $location.path("/countries");
            });
        }

    }]);

