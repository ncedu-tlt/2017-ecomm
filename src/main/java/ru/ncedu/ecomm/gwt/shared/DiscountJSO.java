package ru.ncedu.ecomm.gwt.shared;

import com.google.gwt.core.client.JavaScriptObject;

public class DiscountJSO extends JavaScriptObject {

    protected DiscountJSO() {
    }

    public final native double getDiscountId() /*-{ return this.discountId; }-*/;

    public final native String getName() /*-{ return this.name; }-*/;

    public final native int getvalue() /*-{ return this.value; }-*/;
}
