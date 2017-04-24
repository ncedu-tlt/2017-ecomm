(function ($, window) {

    var frm = window.frm;

    var TopPanelComponent = frm.inheritance.inherits(frm.components.Component, {


        init: function () {

            this.content.sticky({
                context: 'body'
            });
        }

    });

    frm.components.register('topPanelComponent', TopPanelComponent);

})(jQuery, window);
