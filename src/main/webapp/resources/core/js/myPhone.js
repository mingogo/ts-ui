var app = angular.module('myApp', []);

app.controller('formControl', function ($scope, $http) {
    $scope.testAjax = function () {
        $http.get("http://www.mteng-ts-api.elasticbeanstalk.com/api/v1/number/" +
            $scope.phoneNum + "?size=" + $scope.pageSize + "&page=" + $scope.pageNum).then(function (response) {
            $scope.names = response.data.combinations;
            $scope.entriesCount = response.data.count;
            $scope.lastPage = response.data.pagination.lastPage;
            $scope.nextPage = response.data.pagination.nextPage;
            $scope.prevPage = response.data.pagination.previousPage;
        });
    };
    $scope.onlyNumbers = /^(\d{7}|\d{10})$/;
    $scope.pageSize = "10";
    $scope.pageNum = "1";
    $scope.phoneNum = "3612321754";
    $scope.entriesCount = "0";
    $scope.testAjax();
    $scope.goLastPage = function () {
        $scope.pageNum = $scope.lastPage;
        $scope.testAjax();
    };
    $scope.goFirstPage = function () {
        $scope.pageNum = "1";
        $scope.testAjax();
    };
    $scope.goPrevPage = function () {
        $scope.pageNum = $scope.prevPage;
        $scope.testAjax();
    };
    $scope.goNextPage = function () {
        $scope.pageNum = $scope.nextPage;
        $scope.testAjax();
    };
});
