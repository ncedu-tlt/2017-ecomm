(function ($, window) {

    var frm = window.frm;

    var ProductListComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find();
        }

    });

    frm.components.register('ProductListComponent', ProductListComponent);

})(jQuery, window);
