$(".button-collapse").sideNav();

angular.module('seatApp', [ 'ngRoute', 'ngAnimate' ])

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
    controller : 'HomeController',
    templateUrl : 'view/home.html',
    resolve : {
      products : function($http) {
        return $http.get('/seat/api/products');
      }
    }
  }).when('/products/:id', {
    controller : 'ProductController',
    templateUrl : 'view/product.html',
    resolve : {
      product : function($http, $route) {
        return $http.get('/seat/api/products/' + $route.current.params.id);
      }
    }
  }).when('/orders', {
    controller : 'OrderController',
    templateUrl : 'view/orders.html',
    resolve : {
      orders : function($http) {
        return $http.get('/seat/api/orders');
      }
    }
  }).when('/checkout', {
    controller : 'CheckoutController',
    templateUrl : 'view/checkout.html'
  }).otherwise({
    redirectTo : '/'
  });
})

//
.controller('HomeController', function($scope, $location, products) {
  $scope.products = products.data;
  $scope.go = function(path) {
    $location.path(path);
  };
})

//
.controller('ProductController', function($scope, $http, $location, product) {
  $scope.product = product.data;
  $scope.order = function(quantity) {
    var transform = function(data) {
      return $.param(data);
    }
    $http({
      method : 'POST',
      url : '/seat/api/orders/add',
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
      },
      transformRequest : transform,
      data : {
        productId : $scope.product.id.value,
        quantity : quantity
      }
    }).success(function(data, status, headers, config) {
      Materialize.toast('ご注文を承りました。', 4000)
      $location.path("/");
    }).error(function(data, status, headers, config) {
      Materialize.toast('申し訳ございません、品切れです。', 4000)
      $location.path("/");
    });
  };
})

//
.controller('OrderController', function($scope, orders) {
  $scope.orders = orders.data;
})

//
.controller('CheckoutController', function($http) {
  $http({
    method : 'POST',
    url : '/seat/api/checkout'
  });
});
