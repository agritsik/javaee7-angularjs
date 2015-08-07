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


controllers.controller('ConfigurationCtrl', ['$scope', 'Item', 'Property', 'Configuration', '$routeParams', '$location', '$route',
    function ($scope, Item, Property, Configuration, $routeParams, $location, $route) {

        // get current item
        var item = Item.get({id: $routeParams.id});

        // get all properties
        $scope.rows = Property.query();

        // selected property
        $scope.rows.$promise.then(function () {
            $scope.selected = $scope.rows[0];
        });

        // get existed configurations
        $scope.configurations = Configuration.query({item_id: $routeParams.id});

        $scope.submit = function () {
            var c = new Configuration({
                item: item,
                property: $scope.selected
            });
            c.$save().then(function () {
                $route.reload();
            });
        };

        $scope.delete = function (c) {
            c.$delete().then(function () {
                $route.reload();
            });
        };

    }])
;