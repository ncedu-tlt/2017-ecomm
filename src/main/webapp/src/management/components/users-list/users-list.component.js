(function () {

    angular
        .module('management')
        .component('ncUsersList', {
            templateUrl: 'users-list.html',
            controller: UsersListController,
            bindings: {
                $router: '<'
            }
        });

    function UsersListController(usersService) {
        'ngInject';

        var ctrl = this;

        ctrl.model = {
            data: [],
            columns: [
                {
                    key: 'id',
                    name: 'ID'
                },
                {
                    key: 'email',
                    name: 'Email'
                }
            ]
        };

        ctrl.$onInit = function () {
            usersService.getUsers().then(function (response) {
                ctrl.model.data = response.data;
            });
        };

        ctrl.onSelect = function (user) {
            ctrl.$router.navigate(['UserDetails', { id: user.id }]);
        };
    }

})();