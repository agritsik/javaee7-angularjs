var baseUrl = 'http://192.168.59.103:8080/app/resources';

var services = angular.module('catalogServices', ['ngResource'])

services.factory('Item', ['$resource', function ($resource) {
    return $resource(baseUrl + '/items/:id', {id: '@id'}, {'update': {method: 'PUT'}});
}]);
