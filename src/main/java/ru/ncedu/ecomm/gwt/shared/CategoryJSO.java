package ru.ncedu.ecomm.gwt.shared;

import com.google.gwt.core.client.JavaScriptObject;

public class CategoryJSO extends JavaScriptObject {

    protected CategoryJSO() {
    }

    public final native double getCategoryId() /*-{ return this.categoryId; }-*/;

    public final native double getParentId() /*-{ return this.parentId; }-*/;

    public final native String getName() /*-{ return this.name; }-*/;

}
