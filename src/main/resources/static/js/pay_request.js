'use strict';

let token = document.querySelector('meta[name="_csrf"]').content;

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.orderId = null;
        // $scope.lblMsg = null;
        $scope.payment = function (orderId) {
            // console.log(object);
            let object = { "orderId": orderId }
            console.log(object);
            $http({
                method: "POST",
                url: "/api/orders/payment",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                },
                data: JSON.stringify(object)
            }).then(function (response) {
                if (response.data) {
                    console.log("good");
                    $scope.msg = "Post Data Submitted Successfully!";
                    location.replace("/basket")
                }
            }, function (response) {
                console.log(response.status);
                $scope.msg = "Cannot get payment";
                $scope.statusval = response.status;
                $scope.statustext = response.statusText;
                $scope.headers = response.headers();
            });
        };

    }]);
