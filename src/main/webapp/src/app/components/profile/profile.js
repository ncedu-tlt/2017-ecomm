(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PROFILE_FORM: '.jsProfileForm',
        PASSWORD_FIELDS: '.jsPasswordFields',
        SEND_PROFILE_BTN: '.jsSendFormProfileBtn',
        PASSWORD_BTN: '.jsPasswordShowBtn',
        CLOSE_MESSAGE_WITH_ICON: '.jsMessageFromServlet .jsIconCloseMessageFromServlet',
        CLOSE_MESSAGE: '.jsMessageFromServlet',
        DIMMER: '.jsDimmerProfile'
    };

    var TRANSITION = {
        HIDE: 'hide',
        FLIP: 'vertical flip',
        FADE: 'fade',
        SHOW: 'show'
    };

    var EVENTS = {
        CLICK: 'click'
    };

    var DIMMER = {
        SHOW: 'show'
    };

    var CONDITIONS = {
        IS_VALID: 'is valid'
    };

    var ProfileComponent = frm.inheritance.inherits(frm.components.Component, {
        init: function () {
            this.content.find(ELEMENTS.PROFILE_FORM).form({
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
                    phone: {
                        identifier: 'phone',
                        optional: true,
                        rules: [{
                            type: 'regExp[/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/]'
                        }]
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
            this.content.find(ELEMENTS.SEND_PROFILE_BTN)
                .on(EVENTS.CLICK, this.showDimmerIfFormValid.bind(this));

            this.content.find(ELEMENTS.PASSWORD_BTN)
                .on(EVENTS.CLICK, this.showPasswordField.bind(this));

            var jsMessage = this.content.find(ELEMENTS.CLOSE_MESSAGE_WITH_ICON);
            this.content.find(jsMessage)
                .on(EVENTS.CLICK, this.closeMessage.bind(jsMessage));
        },
        showDimmerIfFormValid: function () {
            var isValid = this.content.find(ELEMENTS.PROFILE_FORM)
                .form(CONDITIONS.IS_VALID);
            if (isValid) {
                var jsDimmer = this.content.find(ELEMENTS.DIMMER);
                this.dimmerConfig(jsDimmer);
                jsDimmer.dimmer(DIMMER.SHOW);
            }
            else
                this.content.find(ELEMENTS.PASSWORD_FIELDS)
                    .transition(TRANSITION.SHOW);
        },
        dimmerConfig: function (jsDimmer) {
            jsDimmer.dimmer({
                closable: false
            });
        },
        showPasswordField: function () {
            this.content.find(ELEMENTS.PASSWORD_FIELDS)
                .transition(TRANSITION.FLIP);
        },
        closeMessage: function () {
            $(this)
                .closest(ELEMENTS.CLOSE_MESSAGE)
                .transition(TRANSITION.FADE);
        }
    });

    frm.components.register('ProfileComponent', ProfileComponent);

})(jQuery, window);