(function ($, window) {

    var frm = window.frm;

    var RegistrationComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
            this.content.find('.jsRegistrationForm')
                .form({
                    fields: {
                        email: {
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: 'Please enter email'
                                },
                                {
                                    type: 'email',
                                    prompt: 'Email is incorrect'
                                }
                            ]
                        },
                        password: {
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: 'Please enter password'
                                }
                            ]
                        },
                        match: {
                            identifier: 'checkPassword',
                            rules: [
                                {
                                    type: 'match[password]',
                                    prompt: 'Confirm password and password dont match'
                                }
                            ]
                        }
                    }
                })
            ;
        }
    });

    frm.components.register('RegistrationComponent', RegistrationComponent);

})(jQuery, window);