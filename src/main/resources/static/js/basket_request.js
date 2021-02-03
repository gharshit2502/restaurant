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
                    $scope.dishes = data.data;
                },
                function (error) {
                    console.log("error");
                }
            );
        }

        $scope.postdata = function () {
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
                    $scope.msg = "Post Data Submitted Successfully!";
                    alert("Order successfully created.")
                    // window.location.replace("/payment");
                }
            }, function (response) {
                // alert("Basket is empty!");
                alert(response.data.message);

                $scope.msg = response.data.message;
                $scope.statusval = response.status;
                $scope.statustext = response.statusText;
                $scope.headers = response.headers();
            });
        };

        $scope.delete = function (event) {
            let id = event.currentTarget.getAttribute('id');
            console.log(id);
            let object = { "itemId": id }
            console.log(object);
            $http({
                method: "DELETE",
                url: "/api/basket/delete",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                },
                data: JSON.stringify(object)
            }).then(function (response) {
                console.log(response);
                window.location.reload();
            }, function (response) {
                // $scope.msg = "Cannot delete all";
                alert(response.data.message);

                $scope.msg = response.data.message;
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
                console.log(response);
                window.location.reload();
            }, function (response) {
                // $scope.msg = "Cannot delete all";
                alert(response.data.message);

                $scope.msg = response.data.message;
                $scope.statusval = response.status;
                $scope.statustext = response.statusText;
                $scope.headers = response.headers();
            });
        };

    }]);
