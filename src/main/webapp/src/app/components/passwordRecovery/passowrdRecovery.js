(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        RECOVERY_FORM: '.jsPasswordRecoveryForm',
        SEND_TO_EMAIL_BTN: '.jsSendToEmailBtn',
        DIMMER: '.jsDimmerPasswordRecovery'
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
        }
    });

    frm.components.register('RecoveryComponent', RecoveryComponent);

})(jQuery, window);