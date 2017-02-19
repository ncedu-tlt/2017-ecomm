(function () {

    angular
        .module('management')
        .component('ncRoot', {
            templateUrl: 'root.html',
            $routeConfig: [
                { path: '/users', name: 'Users', component: 'ncUsersList', useAsDefault: true },
                { path: '/user', name: 'UserDetails', component: 'ncUserDetails' }
            ]
        });

})();