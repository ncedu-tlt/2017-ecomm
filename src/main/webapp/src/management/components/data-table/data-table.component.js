(function () {

    angular
        .module('management')
        .component('ncDataTable', {
            templateUrl: 'data-table.html',
            controller: DataTableController,
            bindings: {
                model: '<',
                onSelect: '&'
            }
        });

    function DataTableController() {

        var ctrl = this;

        ctrl.getValue = function (row, keyString) {
            var keys = keyString.split('.');
            var value = row;

            keys.forEach(function (key) {
                value = value[key];
            });

            return value;
        };

    }

})();