/**
 * Created by Alexander on 25.12.2016.
 */
(function ($, window) {

    var frm = window.frm;

    var RecoveryComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.ui.form')
                .form({
                    fields: {
                        email: {
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'Enter email!'
                                }
                            ]
                        }
                    }
                })
            ;
        }
    });

    frm.components.register('RecoveryComponent', RecoveryComponent);

})(jQuery, window);