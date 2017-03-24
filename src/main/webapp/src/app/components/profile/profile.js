(function ($, window) {

    var frm = window.frm;

    //noinspection JSAnnotator
    var ProfileComponent = frm.inheritance.inherits(frm.components.Component, {
        showDimmerIfFormValid: function () {
            var isValid = this.content.find('.jsProfileForm').form('is valid');
            if (isValid) {
                var jsDimmer = this.content.find('.jsDimmerProfile');
                this.dimmerConfig(jsDimmer);
                jsDimmer.dimmer('show');
            }
            else {
                this.content.find('.jsPasswordField').transition('show');
            }
        },
        dimmerConfig: function (jsDimmer) {
            jsDimmer.dimmer({
                closable: false
            });
        },
        showPasswordField: function () {
            this.content.find('.jsPasswordField')
                .transition('vertical flip');
        },
        showLabels: function () {
            this.content.find('.jsLabelProfile')
                .transition('fade up');
        },
        init: function () {
            this.content.find('.jsLabelProfile').transition('hide');
            this.content.find('.jsPasswordField').transition('hide');
            this.content.find('.jsProfileForm').form({
                inline: true,
                on: 'blur',
                fields: {
                    firstName: {
                        identifier: 'firstName',
                        optional: true,
                        rules: [
                            {
                                type: 'minLength[2]',
                                prompt: 'Your first name must be at least {ruleValue} characters'
                            }
                        ]
                    },
                    lastName: {
                        identifier: 'lastName',
                        optional: true,
                        rules: [
                            {
                                type: 'minLength[2]',
                                prompt: 'Your last name must be at least {ruleValue} characters'
                            }
                        ]
                    },
                    email: {
                        identifier: 'email',
                        optional: true,
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
            //listeners
            this.content.find('.jsSendFormProfileBtn').on('click', this.showDimmerIfFormValid.bind(this));
            this.content.find('.jsPasswordCall').on('click', this.showPasswordField.bind(this));
            this.content.find('.jsVisible').on('click', this.showLabels.bind(this));
            this.content.find('.jsMessageFromServlet .jsCloseMessageFromServlet')
                .on('click', function () {
                    $(this)
                        .closest('.jsMessageFromServlet')
                        .transition('fade');
                });
        }
    });

    frm.components.register('ProfileComponent', ProfileComponent);

})(jQuery, window);