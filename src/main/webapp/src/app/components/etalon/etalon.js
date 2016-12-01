(function ($, window) {

    var frm = window.frm;

    var EtalonComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.ui.checkbox').checkbox();
            this.content.find('.ui.dropdown').dropdown();
            this.content.find('.jsPrint').on('click', this.onButtonClick.bind(this));
        },

        /**
         * Executed on document readiness
         */
        onDocumentReady: function () {
            this.printRoles();
        },

        /**
         * Prints roles retrieved from request
         */
        printRoles: function () {
            console.log('Roles:');
            this.params.roles.forEach(function (role) {
                console.log(role.id + ' - ' + role.name);
            });
        },

        onButtonClick: function () {
            var input = this.content.find('.jsInput').val();
            alert(input);
        }

    });

    frm.components.register('EtalonComponent', EtalonComponent);

})(jQuery, window);
