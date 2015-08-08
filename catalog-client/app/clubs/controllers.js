var controllers = angular.module('clubsControllers', []);

controllers.controller('ListClubCtrl', ['$scope', 'ClubResource', '$routeParams', '$location', '$route',
    function ($scope, ClubResource, $routeParams, $location, $route) {

        $scope.rows = ClubResource.query();

        $scope.delete = function (row) {
            row.$delete().then(function(){
                $route.reload();
            })
        }

    }]);

controllers.controller('CreateClubCtrl', ['$scope', 'CountryResource', 'ClubResource', '$routeParams', '$location', '$route',
    function ($scope, CountryResource, ClubResource, $routeParams, $location, $route) {

        $scope.countries = CountryResource.query();
        $scope.row = new ClubResource();

        // select first
        $scope.countries.$promise.then(function () {
            $scope.row.country = $scope.countries[0];
        });

        $scope.submit = function () {
            $scope.row.$save().then(function () {
                $location.path("/clubs");
            })
        }

    }]);

controllers.controller('EditClubCtrl', ['$scope', 'CountryResource', 'ClubResource', '$routeParams', '$location', '$route',
    function ($scope, CountryResource, ClubResource, $routeParams, $location, $route) {

        $scope.countries = CountryResource.query();
        $scope.row = new ClubResource.get({id: $routeParams.id});

        $scope.submit = function () {
            $scope.row.$update().then(function () {
                $location.path("/clubs");
            })
        }


    }]);

