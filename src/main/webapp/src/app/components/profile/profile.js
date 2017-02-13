(function ($, window) {

    var frm = window.frm;

    var profileComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
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
                })
            ;
        },
        showPasswordConfirm: function () {
            this.content.find('.jsPasswordConfirm').click(function () {
                this.show();
            });
            this.content.find('.jsProfileTextParam').click(function () {
                if (this.val().empty()) {
                    $('.jsPasswordConfirm').hide();
                }
            });
        },
        showApplyButton: function () {
            if (!this.content.find('.jsSendFormProfileBtn').is(':visible')) {
                var inputs = $('input');
                inputs.keyup(function () {
                    $('#jsSendFormProfileBtn').show();
                });
            }
        }
    });


    frm.components.register('profileComponent', profileComponent);

})(jQuery, window);