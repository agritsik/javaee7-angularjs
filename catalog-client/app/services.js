var baseUrl = 'http://192.168.59.103:8080/app/resources';

var services = angular.module('catalogServices', ['ngResource'])

services.factory('PlayerResource', ['$resource', function ($resource) {
    return $resource(baseUrl + '/players/:id', {id: '@id'}, {'update': {method: 'PUT'}});
}]);

services.factory('CountryResource', ['$resource', function ($resource) {
    return $resource(baseUrl + '/countries/:id', {id: '@id'}, {'update': {method: 'PUT'}});
}]);

services.factory('ClubResource', ['$resource', function ($resource) {
    return $resource(baseUrl + '/clubs/:id', {id: '@id'}, {'update': {method: 'PUT'}});
}]);


services.factory('CareerResource', ['$resource', function ($resource) {
    return $resource(baseUrl + '/careers/:id', {id: '@id'});
}]);
