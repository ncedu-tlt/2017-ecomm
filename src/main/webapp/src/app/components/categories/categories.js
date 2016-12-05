(function ($, window) {

    var frm = window.frm;

    var CategoryComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.ui.container').popup({
                    popup: '.popup',
                    hoverable: true
                });
        }

    });

    frm.components.register('CategoryComponent', CategoryComponent);

})(jQuery, window);
