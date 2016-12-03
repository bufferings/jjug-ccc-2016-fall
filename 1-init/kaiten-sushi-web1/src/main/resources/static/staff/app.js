$(".button-collapse").sideNav();

angular.module('staffApp', [ 'ngRoute', 'ngAnimate' ])

//
.filter('numberFixedLen', function() {
  return function(n, len) {
    var num = parseInt(n, 10);
    len = parseInt(len, 10);
    if (isNaN(num) || isNaN(len)) {
      return n;
    }
    num = '' + num;
    while (num.length < len) {
      num = '0' + num;
    }
    return num;
  };
})

//
.config(function($routeProvider) {
  $routeProvider.when('/', {
    controller : 'OrderController',
    templateUrl : 'view/orders.html',
    resolve : {
      orders : function($http) {
        return $http.get('/staff/api/orders/waiting');
      }
    }
  }).when('/checkout', {
    controller : 'CheckoutController',
    templateUrl : 'view/checkout.html',
    resolve : {
      billRequests : function($http, $route) {
        return $http.get('/staff/api/order-groups/checkout/');
      }
    }
  }).otherwise({
    redirectTo : '/'
  });
})

//
.controller('OrderController', function($scope, $http, $location, orders) {
  $scope.orders = orders.data;
  $scope.deliver = function(orderItemId) {
    $http({
      method : 'POST',
      url : '/staff/api/orders/' + orderItemId + '/deliver',
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
      }
    }).success(function(data, status, headers, config) {
      for (var i = 0, len = $scope.orders.length; i < len; i++) {
        if ($scope.orders[i].orderItemId === orderItemId) {
          $scope.orders.splice(i, 1);
          break;
        }
      }
    }).error(function(data, status, headers, config) {
    });
  };
})

//
.controller('CheckoutController', function($scope, $http, billRequests) {
  $scope.billRequests = billRequests.data;
  $scope.close = function(orderGroupId) {
    $http({
      method : 'POST',
      url : '/staff/api/order-groups/' + orderGroupId + '/close',
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
      }
    }).success(function(data, status, headers, config) {
      for (var i = 0, len = $scope.billRequests.length; i < len; i++) {
        if ($scope.billRequests[i].orderGroupId === orderGroupId) {
          $scope.billRequests.splice(i, 1);
          break;
        }
      }
    }).error(function(data, status, headers, config) {
    });
  };
});
