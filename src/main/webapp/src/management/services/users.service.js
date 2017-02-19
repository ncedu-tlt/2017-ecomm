(function () {

    angular
        .module('management')
        .factory('usersService', UsersService);

    function UsersService($http, $window) {
        'ngInject';

        return {
            getUsers: getUsers,
            getUser: getUser
        };

        function getUsers() {
            return $http.get($window.contextPath + '/rest/ecomm/users');
        }
        
        function getUser(id) {
            return $http.get($window.contextPath + '/rest/ecomm/users/' + id);
        }
    }

})();