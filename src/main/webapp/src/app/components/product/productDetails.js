(function ($, window) {

    var frm = window.frm;
    var ProductComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find('.rating').rating({initialRating: 2, maxRating: 5}).rating('disable');
            this.content.find('.slideShowPic').on('click', function (e) {
                e.preventDefault();

                var
                    $this = $(this),
                    item = $this.closest('.slideShowItem'),
                    container = $this.closest('.slideShow'),
                    display = container.find('.showDisplay'),
                    path = item.find('img').attr('src'),
                    duration = 300;

                if (!item.hasClass('active')) {
                    item.addClass('active').siblings().removeClass('active');

                    display.find('img').fadeOut(duration, function () {
                        $(this).attr('src', path).fadeIn(duration);
                    });
                }
            });
        }


    });

    frm.components.register('productDetails', ProductComponent);

})(jQuery, window);
