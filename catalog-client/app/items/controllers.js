var controllers = angular.module('itemControllers', []);

controllers.controller('ListCtrl', ['$scope', 'Item', '$routeParams', '$location', '$route',
    function ($scope, Item, $routeParams, $location, $route) {

        $scope.items = Item.query();

        $scope.delete = function (item) {
            item.$delete().then(function () {
                $route.reload();
            })
        }

    }]);

controllers.controller('CreateCtrl', ['$scope', 'Item', '$routeParams', '$location', '$route',
    function ($scope, Item, $routeParams, $location, $route) {

        $scope.item = new Item();

        $scope.submit = function () {
            $scope.item.$save().then(function () {
                $location.path("/items");
            });
        }

    }]);

controllers.controller('EditCtrl', ['$scope', 'Item', '$routeParams', '$location', '$route',
    function ($scope, Item, $routeParams, $location, $route) {

        $scope.item = Item.get({id: $routeParams.id})

        $scope.submit = function () {
            //Item.update({id:id}, item).$promise.then(function () {
            //        $location.path("/items");
            //    });

            $scope.item.$update().then(function () {
                $location.path("/items");
            });
        }

    }]);