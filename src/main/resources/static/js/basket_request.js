'use strict';

let token = document.querySelector('meta[name="_csrf"]').content;

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.dishes = [];

        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "/api/basket/get",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);
                    // $scope.items_transactions = data.data;
                    $scope.dishes = data.data;
                    // main.dishes = data.data.dishes;
                    // console.log(data.data.dishes);
                },
                function (error) {
                    console.log("error");
                }
            );
        }
        $scope.items = null;
        // $scope.lblMsg = null;
        $scope.postdata = function () {
            // console.log(object);
            $http({
                method: "POST",
                url: "/api/orders/create",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(function (response) {
                if (response.data) {
                    $scope.items = response.data;
                    console.log("good, url: " + $scope.items);
                    $scope.msg = "Post Data Submitted Successfully!";
                    window.location.replace("/payment");
                }
            }, function (response) {
                alert("Basket is empty!");
                $scope.msg = "Service not Exists";
                $scope.statusval = response.status;
                $scope.statustext = response.statusText;
                $scope.headers = response.headers();
            });
        };

        $scope.deleteAll = function () {
            $http({
                method: "DELETE",
                url: "/api/basket/deleteAll",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(function (response) {
                // if (response.data)
                //     $scope.items = response.data;
                // console.log("good, url: " + $scope.items);
                // $scope.msg = "Post Data Submitted Successfully!";
                window.location.reload();
            }, function (response) {
                $scope.msg = "Basket is empty!";
                $scope.statusval = response.status;
                $scope.statustext = response.statusText;
                $scope.headers = response.headers();
            });
        };

    }]);
