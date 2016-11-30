(function ($, window) {

    window.frm = window.frm || {};

    var frm = window.frm;
    frm.inheritance = frm.inheritance || {};

    frm.inheritance.inherits = function (Parent, ChildPrototype)
    {
        var Child = function ()
        {
        };

        var F = function ()
        {
        };
        F.prototype = Parent.prototype;
        Child.prototype = new F();
        Child.prototype.constructor = Child;
        Child.superclass = Parent.prototype;

        $.extend(Child.prototype, ChildPrototype);

        return Child;
    };

})(jQuery, window);
