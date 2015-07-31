var baseUrl = 'http://192.168.59.103:8080/app/resources';

var services = angular.module('catalogServices', ['ngResource'])

services.factory('Item', ['$resource', function ($resource) {
    return $resource(baseUrl + '/items/:id', {id: '@id'}, {'update': {method: 'PUT'}});
}]);

services.factory('Category', ['$resource', function ($resource) {
    return $resource(baseUrl + '/categories/:id', {id: '@id'}, {'update': {method: 'PUT'}});
}]);

// deprecated
services.factory('Property', ['$resource', function ($resource) {
    return $resource(baseUrl + '/properties/:id');
}]);

// deprecated
services.factory('CategoryProperty', ['$resource', function ($resource) {
    return $resource(baseUrl + '/categories/:pid/properties/:id', {pid: '@pid',id: '@id'});
}]);
