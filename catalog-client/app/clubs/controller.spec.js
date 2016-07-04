describe('Clubs Controller', function(){

    beforeEach(module('clubsControllers'));
    beforeEach(module('catalogServices'));

    var $rootScope, $httpBackend, $controller, ctrl;

    beforeEach(inject(function(_$rootScope_, _$httpBackend_, $controller, ClubResource) {
        $rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;

        ctrl = $controller('ListClubCtrl', {$scope:$rootScope, ClubResource:ClubResource, $routeParams:{}, $location:{}, $route:{}});

        $httpBackend.expectGET('http://localhost:8080/app/resources/clubs').respond([{ name: '1'},{ name: '2'}]);

    }));

    it("should load `clubs` from the server", function(){
        expect($rootScope.rows.length).toBe(0);
        $httpBackend.flush();
        expect($rootScope.rows.length).toBe(2);
    });

});


