(function ($, window) {

    var frm = window.frm;

    var ProfileComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            this.showPasswordConfirm();
            this.showApplyButton();
            this.content.find('.jsProfileForm')
                .form({
                    fields: {
                        firstName: {
                            identifier: 'firstName',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: 'Please enter first name'
                                }
                            ]
                        },
                        lastName: {
                            identifier: 'lastName',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: 'Please enter last name'
                                }
                            ]
                        },
                        email: {
                            identifier: 'email',
                            rules: [
                                {
                                    type: 'email',
                                    prompt: 'Please enter a valid e-mail'
                                }
                            ]
                        },
                        password: {
                            identifier: 'password',
                            rules: [
                                {
                                    type: 'length[4]',
                                    prompt: 'Please enter password(Must be at least 4 characters in length)'
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
                });
            this.content.find('#jsSendFormProfileBtn').click(function () {
                $(this).addClass('loading');
            });
            this.content.find('.jsProfileTextParam').keyup(function () {
                $('#jsSendFormProfileBtn').removeClass('loading');
            });
        },
        showPasswordConfirm: function () {
            var passwordConfirmInput = this.content.find('.jsPasswordConfirm');
            this.content.find('.jsProfileTextParam:not([type="password"])').click(function () {
                passwordConfirmInput.hide();
            });
            this.content.find('.jsPassword').click(function () {
                passwordConfirmInput.transition('pulse');
            });
        },
        showApplyButton: function () {
            if (!this.content.find('#jsSendFormProfileBtn').is(':visible')) {
                var inputs = this.content.find('.jsProfileTextParam');
                inputs.keyup(function () {
                    $('#jsSendFormProfileBtn').show();
                });
            }
        }
    });


    frm.components.register('ProfileComponent', ProfileComponent);

})(jQuery, window);