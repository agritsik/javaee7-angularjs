var controllers = angular.module('propertiesControllers', []);

controllers.controller('ListPropertiesCtrl', ['$scope', 'Property', '$routeParams', '$location', '$route',
    function ($scope, Property, $routeParams, $location, $route) {


        $scope.rows = Property.query();

        //$scope.category = Restangular.one("categories", $routeParams.pid).get().$object;
        //$scope.rows = Restangular.one("categories", $routeParams.pid).all("properties").getList().$object;

    }]);

controllers.controller('CreatePropertiesCtrl', ['$scope', 'Category', 'CategoryProperty', '$routeParams', '$location', '$route',
    function ($scope, Category, CategoryProperty, $routeParams, $location, $route) {

        $scope.categories = Category.query();
        $scope.row = new CategoryProperty();

        $scope.categories.$promise.then(function () {
            $scope.selected = $scope.categories[0];
        });

        $scope.submit = function () {
            console.log($scope.row);
            $scope.row.$save({pid: $scope.selected.id}).then(function () {
                console.log("created!");
            })
        }

    }]);

controllers.controller('EditPropertiesCtrl', ['$scope', 'Category', 'CategoryProperty', '$routeParams', '$location', '$route',
    function ($scope, Category, CategoryProperty, $routeParams, $location, $route) {

        $scope.categories = Category.query();
        $scope.row = new CategoryProperty.get({pid: $routeParams.pid, id: $routeParams.id});

        $scope.row.$promise.then(function () {
            $scope.selected = $scope.row.category;
        });

        $scope.submit = function () {
            console.log($scope.row);
            $scope.row.$update({pid: $scope.selected.id}).then(function () {
                console.log("updated!");
            })
        }


    }]);

