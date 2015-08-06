var controllers = angular.module('propertiesControllers', []);

controllers.controller('ListPropertiesCtrl', ['$scope', 'Property', '$routeParams', '$location', '$route',
    function ($scope, Property, $routeParams, $location, $route) {

        $scope.rows = Property.query();

        $scope.delete = function (row) {
            row.$delete().then(function(){
                $route.reload();
            })
        }

    }]);

controllers.controller('CreatePropertiesCtrl', ['$scope', 'Category', 'Property', '$routeParams', '$location', '$route',
    function ($scope, Category, Property, $routeParams, $location, $route) {

        $scope.categories = Category.query();
        $scope.row = new Property();

        // select first
        $scope.categories.$promise.then(function () {
            $scope.selected = $scope.categories[0];
        });

        $scope.submit = function () {
            $scope.row.category = $scope.selected;

            //console.log($scope.row);
            $scope.row.$save().then(function () {
                $location.path("/properties");
            })
        }

    }]);

controllers.controller('EditPropertiesCtrl', ['$scope', 'Category', 'Property', '$routeParams', '$location', '$route',
    function ($scope, Category, Property, $routeParams, $location, $route) {

        $scope.categories = Category.query();
        $scope.row = new Property.get({id: $routeParams.id});

        // preselect
        $scope.row.$promise.then(function () {
            $scope.selected = $scope.row.category;
        });

        $scope.submit = function () {
            $scope.row.category = $scope.selected;

            $scope.row.$update().then(function () {
                $location.path("/properties");
            })
        }


    }]);

