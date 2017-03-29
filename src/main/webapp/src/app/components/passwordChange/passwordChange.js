(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PASSWORD_CHANGE_FORM: '.jsPasswordChangeForm',
        SEND_PASSWORD_BTN: '.jsPasswordChangeBtn',
        DIMMER: '.jsDimmerPasswordChange',
        CLOSE_MESSAGE: '.jsMessageFromServlet .jsCloseMessageFromServlet'
    };

    var changePasswordComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find(ELEMENTS.PASSWORD_CHANGE_FORM)
                .form({
                    inline: true,
                    on: 'blur',
                    fields: {
                        password: {
                            identifier: 'password',
                            rules: [
                                {
                                    type: 'length[4]',
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
                });
            this.content.find(ELEMENTS.SEND_PASSWORD_BTN).on('click', this.showDimmerIfFormValid.bind(this));
            var jsMessage = this.content.find(ELEMENTS.CLOSE_MESSAGE);
            this.content.find(jsMessage).on('click', this.closeMessage.bind(jsMessage));
        },
        showDimmerIfFormValid: function () {
            var isValid = this.content.find(ELEMENTS.PASSWORD_CHANGE_FORM).form('is valid');
            if (isValid) {
                var jsDimmer = this.content.find(ELEMENTS.DIMMER);
                this.dimmerConfig(jsDimmer);
                jsDimmer.dimmer('show');
            }
        },
        dimmerConfig: function (jsDimmer) {
            jsDimmer.dimmer({
                closable: false
            });
        },
        closeMessage: function () {
            $(this)
                .closest('.jsMessageFromServlet')
                .transition('fade');
        }
    });

    frm.components.register('changePasswordComponent', changePasswordComponent);

})(jQuery, window);