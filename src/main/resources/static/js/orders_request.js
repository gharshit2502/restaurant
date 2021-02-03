'use strict';

let token = document.querySelector('meta[name="_csrf"]').content;

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.orders = [];

        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "/api/orders/get",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);
                    $scope.orders = data.data;
                },
                function (error) {
                    console.log("error");
                }
            );
        }

        $scope.getAllItems = function () {
            $http({
                method: "GET",
                url: "/api/orders/getAll",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);
                    // $scope.items_transactions = data.data;
                    $scope.orders = data.data;
                    // main.dishes = data.data.dishes;
                    // console.log(data.data.dishes);
                },
                function (error) {
                    console.log("error");
                }
            );
        }
        $scope.itemId = null;
        $scope.confirmAction = function (itemId) {
            let object = { "itemId": itemId }
            console.log(object);
            $http({
                method: "PUT",
                url: "/api/orders/confirm",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                },
                data: JSON.stringify(object)
            }).then(function (response) {
                if (response.data) {
                    console.log("good");
                    $scope.msg = "Post Data Submitted Successfully!";
                    location.reload();
                }
            }, function (response) {
                console.log(response.status);
                $scope.msg = "Order doesn't exists, also you cannot confirm NEW or DONE orders";
                $scope.statusval = response.status;
                $scope.statustext = response.statusText;
                $scope.headers = response.headers();
            });
        };

    }]);
