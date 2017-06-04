package ru.ncedu.ecomm.gwt.shared;

import com.google.gwt.core.client.JavaScriptObject;

public class CharacteristicJSO extends JavaScriptObject {

    protected CharacteristicJSO() {
    }

    public final native String getName() /*-{ return this.name; }-*/;

    public final native String getValue() /*-{ return this.value; }-*/;
}
