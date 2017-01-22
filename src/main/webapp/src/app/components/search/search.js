(function ($, window) {

    var frm = window.frm;

    var SearchComponent = frm.inheritance.inherits(frm.components.Component, {

        init: function () {
                $("#searchForm").submit(function () {
                    if ($("#text").val().replace(/^\s+|\s+$/g, '')) {
                        $("#searchForm").attr("action", "/search");
                        $("#searchForm").submit();
                    }
                    return false;
                });
        }


    });

    frm.components.register('SearchComponent', SearchComponent);

})(jQuery, window);
