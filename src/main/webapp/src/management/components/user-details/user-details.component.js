(function () {

    angular
        .module('management')
        .component('ncUserDetails', {
            templateUrl: 'user-details.html',
            controller: UserDetailsController
        });

    function UserDetailsController(usersService) {
        'ngInject';

        var ctrl = this;

        ctrl.$routerOnActivate = function (next) {
            var id = next.params.id;
            usersService.getUser(id).then(function (response) {
                ctrl.user = response.data;
            });
        };
    }

})();