/**
 * Created by Alexander on 26.12.2016.
 */
(function ($, window) {

    var frm = window.frm;

    var profileComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.ui.form')
                .form({
                    fields: {
                        password: {
                            identifier: 'password',
                            optional: true,
                            rules: [
                                {
                                    type: 'length[' + 4 + ']',
                                    prompt: 'Password must be at least 4 characters in length'
                                }
                            ]
                        },
                        passwordConfirm: {
                            identifier: 'passwordConfirm',
                            rules: [
                                {
                                    type: 'match[password]',
                                    prompt: 'Mismatched password'
                                }
                            ]
                        }
                    }
                })
            ;
        }
    });

    frm.components.register('profileComponent', profileComponent);

})(jQuery, window);