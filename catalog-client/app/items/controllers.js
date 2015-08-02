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
            $scope.item.$update().then(function () {
                $location.path("/items");
            });
        }

    }]);


controllers.controller('ConfigurationCtrl', ['$scope', 'Restangular', 'Configuration', '$routeParams', '$location', '$route',
    function ($scope, Restangular, Configuration, $routeParams, $location, $route) {

        // get current item
        var item = Restangular.one("items", $routeParams.id).get().$object;

        // get all properties
        $scope.rows = Restangular.all("properties").getList().$object;

        // selected property
        $scope.selected;

        // get existed configurations
        $scope.configurations = Restangular.all("configuration").getList({"item_id": $routeParams.id}).$object;

        $scope.submit = function () {

            var c = {
                item: item,
                property: $scope.selected
            };

            Restangular.all("configuration").post(c).then(function(){
                $route.reload();
            });

        };

        $scope.delete = function (c) {
            var c = new Configuration({id: c.id});
            c.$delete().then(function(){
                $route.reload();
            })
        };

    }])
;