var controllers = angular.module('playerControllers', []);

controllers.controller('ListCtrl', ['$scope', 'PlayerResource', '$routeParams', '$location', '$route',
    function ($scope, PlayerResource, $routeParams, $location, $route) {

        $scope.rows = PlayerResource.query();

        $scope.delete = function (row) {
            row.$delete().then(function () {
                $route.reload();
            })
        }

    }]);

controllers.controller('CreateCtrl', ['$scope', 'PlayerResource', '$routeParams', '$location', '$route',
    function ($scope, PlayerResource, $routeParams, $location, $route) {

        $scope.row = new PlayerResource();

        $scope.submit = function () {
            $scope.row.$save().then(function () {
                $location.path("/players");
            });
        }

    }]);

controllers.controller('EditCtrl', ['$scope', 'PlayerResource', '$routeParams', '$location', '$route',
    function ($scope, PlayerResource, $routeParams, $location, $route) {

        $scope.row = PlayerResource.get({id: $routeParams.id})

        $scope.submit = function () {
            $scope.row.$update().then(function () {
                $location.path("/players");
            });
        }

    }]);


controllers.controller('CareerCtrl', ['$scope', 'PlayerResource', 'ClubResource', 'CareerResource', '$routeParams', '$location', '$route',
    function ($scope, PlayerResource, ClubResource, CareerResource, $routeParams, $location, $route) {

        // get current player
        var player = PlayerResource.get({id: $routeParams.id});

        // get all clubs
        $scope.rows = ClubResource.query();

        // selected club
        $scope.rows.$promise.then(function () {
            $scope.selected = $scope.rows[0];
        });

        // get existed configurations
        $scope.careers = CareerResource.query({player_id: $routeParams.id});

        $scope.submit = function () {
            var c = new CareerResource({
                player: player,
                club: $scope.selected
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