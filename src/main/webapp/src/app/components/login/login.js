(function ($, window) {

    var frm = window.frm;

    var LoginComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.ui.equal.dividing.width.form')
                .form({
                    fields: {
                        email: {
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: ''
                                }
                            ]
                        },
                        password: {
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: ''
                                }
                            ]
                        }
                    }
                })
            ;
        }
    });

    frm.components.register('LoginComponent', LoginComponent);

})(jQuery, window);
