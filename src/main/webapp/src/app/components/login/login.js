(function ($, window) {

    var frm = window.frm;

    var LoginComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

        }
    });

    frm.components.register('LoginComponent', LoginComponent);

})(jQuery, window);
