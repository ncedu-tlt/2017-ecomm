/**
 * Created by Alexander on 26.12.2016.
 */
(function ($, window) {

    var frm = window.frm;

    var changePasswordComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.ui.form')
                .form({
                    fields: {
                        password: {
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'Enter new password.'
                                }
                            ]
                        },
                        passwordRepeat:{
                            rules:[
                                {
                                    type : 'empty',
                                    prompt : 'Enter repeat of new password.'
                                }
                            ]
                        }
                    }
                })
            ;
        }
    });

    frm.components.register('changePasswordComponent', changePasswordComponent);

})(jQuery, window);