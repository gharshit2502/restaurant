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
        // $scope.dishId = null;
        // $scope.lblMsg = null;
        // $scope.postdata = function (dishId) {
        //     // let formData = new FormData();
        //     // formData.append('dishId', dishId);
        //     // let object = {};
        //     // formData.forEach(function (value, key) {
        //     //     object[key] = value;
        //     // });
        //     let object = { "dishId": dishId }
        //     console.log(object);
        //     $http({
        //         method: "POST",
        //         url: "/api/basket/add",
        //         headers: {
        //             "Content-Type": "application/json",
        //             'X-CSRF-TOKEN': token
        //         },
        //         data: JSON.stringify(object)
        //     }).then(function (response) {
        //         if (response.data)
        //             $scope.msg = "Post Data Submitted Successfully!";
        //     }, function (response) {
        //         $scope.msg = "Service not Exists";
        //         $scope.statusval = response.status;
        //         $scope.statustext = response.statusText;
        //         $scope.headers = response.headers();
        //     });
        // };

    }]);


let sendDeleteAllTransactions = async () => {
    let item = document.querySelector('#select_wallet');
    let id = item.options[item.selectedIndex].getAttribute('wallet_id');
    if (id === '0') {
        alert('invalid wallet');
        return;
    }
    if (!confirm('Delete all transaction in this wallet?')) return;

    let elem = main.wallets[0];
    for (let i in main.wallets) {
        if (main.wallets[i].id === Number.parseInt(id)) {
            elem = main.wallets[i];
            break;
        }
    }
    let formData = new FormData();
    formData.append('id', elem.id);
    formData.append('name', elem.name);
    formData.append('balance', elem.balance);
    formData.append('currency_id', elem.currency.id);
    formData.append('user_id', elem.user.id);

    let jsonString = formToJson(formData);
    console.log(jsonString);

    await send('api/transaction/deleteAllByWallet', jsonString, errorMsg);

}

