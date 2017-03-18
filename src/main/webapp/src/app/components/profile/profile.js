(function ($, window) {

    var frm = window.frm;

    //noinspection JSAnnotator
    var ProfileComponent = frm.inheritance.inherits(frm.components.Component, {
        showPasswordField: function () {
            this.content.find('.jsPasswordField')
                .transition('vertical flip');
        },
        init: function () {
            this.content.find('.jsPasswordCall').on('click', this.showPasswordField.bind(this));
            this.content.find('.jsProfileForm').form({
                fields: {
                    email: {
                        identifier: 'email',
                        rules: [
                            {
                                type: 'email',
                                prompt: 'Please enter a valid e-mail'
                            }
                        ]
                    },
                    passwordConfirm: {
                        identifier: 'passwordConfirm',
                        depends: 'password',
                        rules: [
                            {
                                type: 'length[4]',
                                prompt: 'Please enter password(Must be at least 4 characters in length)'
                            },
                            {
                                type: 'match[password]',
                                prompt: 'Mismatched password'
                            }
                        ]
                    }
                }
            });
        }
    });

    frm.components.register('ProfileComponent', ProfileComponent);

})(jQuery, window);