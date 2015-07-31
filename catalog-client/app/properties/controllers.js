var controllers = angular.module('propertiesControllers', []);

controllers.controller('ListPropertiesCtrl', ['$scope', 'Restangular', '$routeParams', '$location', '$route',
    function ($scope, Restangular, $routeParams, $location, $route) {

        $scope.category = Restangular.one("categories", $routeParams.pid).get().$object;
        $scope.rows = Restangular.one("categories", $routeParams.pid).all("properties").getList().$object;

    }]);

controllers.controller('CreatePropertiesCtrl', ['$scope', 'Restangular', '$routeParams', '$location', '$route',
    function ($scope, Restangular, $routeParams, $location, $route) {

        $scope.row = null;

        $scope.submit = function () {
            Restangular.one("categories", $routeParams.pid).post("properties", $scope.row).then(function () {
                $location.path("/categories/" + $routeParams.pid + "/properties");
            });
        }

    }]);

controllers.controller('EditPropertiesCtrl', ['$scope', 'Restangular', '$routeParams', '$location', '$route',
    function ($scope, Restangular, $routeParams, $location, $route) {

        $scope.row = Restangular.one("categories", $routeParams.pid).one("properties", $routeParams.id).get().$object;

        $scope.submit = function () {
            Restangular.one("categories", $routeParams.pid).one("properties", $routeParams.id).customPUT($scope.row).then(function () {
                $location.path("/categories/" + $routeParams.pid + "/properties");
            });
        }


    }]);

