(function() {
  var app = angular.module('gemStore', ["ngRoute"]);
  
      app.config(function($routeProvider){
        $routeProvider.when("/", {
                            templateUrl:"usersListTemplate.html", 
                            controller:"UsersListController"
                        })
                      .when('/new', {
                          templateUrl:"editTemplate.html",
                          controller:"EditUserController"
                      })
                      .when("/edit/:id",{
                          templateUrl:"editTemplate.html",
                          controller:"EditUserController"
                      }).otherwise({
                          redirectTo: "/"
                      });        

      });


  app.controller('UsersListController',['$http', '$scope', '$location', function($http, $scope, $location){
    $scope.users = [];
    var srv = "http://"+$location.host()+":"+$location.port()+"/api/users/";
    
    $http.get(srv).success(function(data){
       $scope.users = data;
    });
    
  }]);


  app.controller("EditUserController", ['$http', '$scope', '$routeParams', '$location', 
                                function($http, $scope, $routeParams, $location){
        var id = $routeParams.id;
        var srv = "http://"+$location.host()+":"+$location.port()+"/api/users/"+id;
        
        if (id !=undefined || id != '') {
            $scope.id = id;
            
            $http.get(srv).success(function(data){
               $scope.user = data;
            });          
        }   

        $scope.submit = function(){
            var srvPost = "http://"+$location.host()+":"+$location.port()+"/api/users/";            
                 
            if (id) {
                $http.put(srv, $scope.user).success(function(data){
                    console.log(data);
                    $location.path( "/" );
                });  
            } else 
                $http.post(srvPost, $scope.user).success(function(data){
                    console.log(data);
                    $location.path( "/" );
                });              
            
        };

        $scope.deleteClick = function(){
            $http.delete(srv).success(function(data){
               console.log(data);    
               $location.path( "/" );
            });              
        };
  }]);

})();
