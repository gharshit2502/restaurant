'use strict';

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.dishes = [];
        // $scope.items_wallets = [];
        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "/api/get",
                headers: {
                    "Content-Type": "application/json",
                    // 'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);
                    // $scope.items_transactions = data.data;
                    $scope.dishes = data.data.dishes;
                    // $scope.categories = data.data.categories;
                    // main.dishes = data.data.dishes;
                    // console.log(data.data.dishes);
                },
                function (error) {
                    console.log("error");
                }
            );
        }
    }]);

angular.module("get_form1", [])
    .controller("GetController1", ["$scope", "$http", function ($scope, $http) {
        $scope.categories = [];
        // $scope.items_wallets = [];
        $scope.getItems1 = function () {
            $http({
                method: "GET",
                url: "/api/getCat",
                headers: {
                    "Content-Type": "application/json",
                    // 'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);
                    // $scope.items_transactions = data.data;
                    $scope.categories = data.data.categories;
                    // main.dishes = data.data.dishes;
                    // console.log(data.data.dishes);
                },
                function (error) {
                    console.log("error");
                }
            );
        }
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

