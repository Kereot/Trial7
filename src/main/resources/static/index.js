angular.module('app', []).controller('studentController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadStudents = function () {
        $http.get(contextPath + '/students')
            .then(function (response) {
                $scope.StudentsList = response.data;
            });
    };

    $scope.deleteStudent = function (id) {
        $http.delete(contextPath + '/students/' + id)
            .then(function () {
                $scope.loadStudents();
            });
    }

    $scope.addStudent = function() {
        $http.post(contextPath + '/students', $scope.newStudent)
            .then(function () {
                $scope.loadStudents();
                $scope.newStudent = null;
            });
    }

    $scope.updateStudent = function(id, name, age) {
        $http.put(contextPath + '/students', {
            id: id,
            name: name,
            age: age
        })
            .then(function () {
                $scope.loadStudents();
            });
    }

    $scope.loadStudents();

});