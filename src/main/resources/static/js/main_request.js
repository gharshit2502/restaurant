'use strict';

let token = document.querySelector('meta[name="_csrf"]').content;
let sortField = 'id';
let sortDirection = 'asc';
let pageNo = 1;

let queryString = window.location.search;
console.log(queryString);
let urlParams = new URLSearchParams(queryString);

if (urlParams.has('pageNo')) {
    pageNo = urlParams.get('pageNo');
    sortField = urlParams.get('sortField');
    sortDirection = urlParams.get('sortDirection');
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
                ,
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);
                    $scope.dishes = data.data.dishes;
                    $scope.pageable.page = data.data.currentPage;
                    $scope.pageable.totalPages = data.data.totalPages;
                    $scope.pageable.sortField = data.data.sortField;
                    $scope.pageable.sortDirection = data.data.sortDirection;

                },
                function (error) {
                    console.log("error");
                }
            );
        }
        $scope.dishId = null;
        $scope.postdata = function (dishId) {
            let object = { "dishId": dishId }
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
                    $scope.msg = "Post Data Submitted Successfully!";
                    alert("Dish " + dishId + " successfully added.")
                }
            }, function (response) {
                console.log(response);
                alert((response.data.message === undefined) ? 'Wrong input.' : response.data.message);

                $scope.msg = response.data.message;
                $scope.statusval = response.status;
                $scope.statustext = response.error;
                $scope.headers = response.headers();
            });
        };

    }]);

let sorting = (field) => {
    sortField = field;
    if (sortDirection === 'asc') {
        sortDirection = 'desc';
    } else {
        sortDirection = 'asc'
    }
    location.replace('/?pageNo='+ pageNo + '&sortField=' + field + '&sortDirection=' + sortDirection);
}

let init = () => {
    getSelector();
}
window.onload = init;
