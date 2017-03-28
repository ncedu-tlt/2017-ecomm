(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        RECOVERY_FORM: '.jsPasswordRecoveryForm',
        SEND_TO_EMAIL_BTN: '.jsSendToEmailBtn',
        DIMMER: '.jsDimmerPasswordRecovery',
        CLOSE_MESSAGE: '.jsMessageFromServlet .jsCloseMessageFromServlet'
    };

    var RecoveryComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find(ELEMENTS.RECOVERY_FORM)
                .form({
                    inline: true,
                    on: 'blur',
                    fields: {
                        email: {
                            identifier: 'email',
                            rules: [
                                {
                                    type: 'email',
                                    prompt: 'Please enter a valid e-mail'
                                }
                            ]
                        }
                    }
                });
            this.content.find(ELEMENTS.SEND_TO_EMAIL_BTN).on('click', this.showDimmerIfFormValid.bind(this));
            var jsMessage = this.content.find(ELEMENTS.CLOSE_MESSAGE);
            this.content.find(jsMessage).on('click', this.closeMessage.bind(jsMessage));
        },
        showDimmerIfFormValid: function () {
            var isValid = this.content.find(ELEMENTS.RECOVERY_FORM).form('is valid');
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
        closeMessage: function() {
            $(this)
                .closest('.jsMessageFromServlet')
                .transition('fade');
        }
    });

    frm.components.register('RecoveryComponent', RecoveryComponent);

})(jQuery, window);