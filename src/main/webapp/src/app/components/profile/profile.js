(function ($, window) {

    var frm = window.frm;

    var profileComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            showPasswordConfirm();
            showApplyButton();
            this.content.find('.jsProfileForm')
                .form({
                    fields: {
                        firstName:{
                            identifier: 'firstName',
                            rules:[
                                {
                                    type: 'empty',
                                    prompt: 'Please enter first name'
                                }
                            ]
                        },
                        lastName:{
                            identifier: 'lastName',
                            rules:[
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
                                    prompt: 'Password must be at least 4 characters in length'
                                }
                            ]
                        },
                        password: {
                            identifier: 'password',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: 'Please enter password'
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

    function showPasswordConfirm(){
        $('input[type="password"]').click(function () {
            $('.jsPasswordConfirm').css({'display': 'block'});
        });
        $('input[type="text"]').click(function () {
            if ($('input[name="passwordConfirm"]').val() === "") {
                $('.jsPasswordConfirm').css({'display': 'none'});
            }
        });
    }

    function showApplyButton(){
        if(! $('#jsSendFormProfileBtn').is(':visible')){
            var input = $('input[type="text"]');
            input.keyup(function(){
                $('#jsSendFormProfileBtn').css({'display' : 'block'});
            });
        }
    }

    frm.components.register('profileComponent', profileComponent);

})(jQuery, window);