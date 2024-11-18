var app= angular.module('myApp', []);

app.controller('SelectionController', function($scope, $http) {
    
    $scope.chargerSelection = function() {
        $scope.listeSelection = [];

        $http.get('http://localhost:8080/talenta/resultat-entretient?idPoste=' + $scope.idPoste)
            .then(function(response) {
                $scope.listeSelection = response.data;
                console.log(response.data);
            }, function(error) {
                console.error('Erreur lors du chargement des cv selectionnes ', error);
            });
    };
});