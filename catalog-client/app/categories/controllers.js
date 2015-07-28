var controllers = angular.module('categoriesControllers', []);

controllers.controller('ListCategoriesCtrl', ['$scope', 'Category', '$routeParams', '$location', '$route',
    function ($scope, Category, $routeParams, $location, $route) {

        $scope.rows = Category.query();

        $scope.delete = function (row) {
            row.$delete().then(function () {
                $route.reload();
            })
        }

    }]);

controllers.controller('CreateCategoriesCtrl', ['$scope', 'Category', '$routeParams', '$location', '$route',
    function ($scope, Category, $routeParams, $location, $route) {

        $scope.row = new Category();

        $scope.submit = function () {
            $scope.row.$save().then(function () {
                $location.path("/categories");
            });
        }

    }]);

controllers.controller('EditCategoriesCtrl', ['$scope', 'Category', '$routeParams', '$location', '$route',
    function ($scope, Category, $routeParams, $location, $route) {

        $scope.row = Category.get({id: $routeParams.id})

        $scope.submit = function () {
            $scope.row.$update().then(function () {
                $location.path("/categories");
            });
        }

    }]);