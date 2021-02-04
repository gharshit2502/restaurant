'use strict';

let token = document.querySelector('meta[name="_csrf"]').content;
let sortField = 'id';
let sortDirection = 'asc';
let pageNo = 1;
let categoryId = 0;

let queryString = window.location.search;
console.log(queryString);
let urlParams = new URLSearchParams(queryString);

if (urlParams.has('pageNo')) {
    pageNo = urlParams.get('pageNo');
    sortField = urlParams.get('sortField');
    sortDirection = urlParams.get('sortDirection');
    categoryId = urlParams.get('categoryId');
}

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.pageable = {};
        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "/api/get/" + pageNo
                    + '?sortField=' + sortField
                    + "&sortDirection=" + sortDirection
                    + "&categoryId=" + categoryId
                ,
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);
                    $scope.dishes = data.data.dishes;
                    $scope.categories = data.data.categories;
                    $scope.pageable.page = data.data.currentPage;
                    $scope.pageable.totalPages = data.data.totalPages;
                    $scope.pageable.sortField = data.data.sortField;
                    $scope.pageable.sortDirection = data.data.sortDirection;
                    $scope.pageable.categoryId = data.data.categoryId;

                },
                function (error) {
                    console.log(error);
                    console.log("error");
                    alert("Incorrect url parameters, reload main page.");
                }
            );
        }
        $scope.itemId = null;
        $scope.postdata = function (itemId) {
            let object = { "itemId": itemId }
            console.log(object);
            $http({
                method: "POST",
                url: "/api/basket/create",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                },
                data: JSON.stringify(object)
            }).then(function (response) {
                if (response.data) {
                    alert("Dish " + itemId + " successfully added.")
                }
            }, function (response) {
                alertErrors(response);
            });
        };

    }]);

let sorting = (field) => {
    sortDirection = (sortDirection === 'asc') ? 'desc' : 'asc';
    sortField = field;
    replace();
}

let replace = () => {
    location.replace(
        '/?pageNo='+ pageNo +
        '&sortField=' + sortField +
        '&sortDirection=' + sortDirection
        + "&categoryId=" + categoryId
    );
}

let init = () => {
    getSelector();
}
window.onload = init;
