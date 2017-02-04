(function ($, window) {

    var frm = window.frm;

    var RecoveryComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.jsPasswordRecoveryForm')
                .form({
                    fields: {
                        email: {
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: 'Enter email!'
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