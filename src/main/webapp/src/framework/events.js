(function ($, window) {

    window.frm = window.frm || {};

    var frm = window.frm;
    frm.events = frm.events || {};

    var registry = {};

    frm.events.on = function (event, callback) {
        if (!registry[event]) {
            registry[event] = $.Callbacks();
        }
        registry[event].add(callback);
    };

    frm.events.fire = function (event, params) {
        if (registry[event]) {
            registry[event].fire(params);
        }
    };

    frm.events.remove = function (event, callback) {
        if (registry[event]) {
            registry[event].remove(callback);
        }
    };

})(jQuery, window);