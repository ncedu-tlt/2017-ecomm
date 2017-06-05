package ru.ncedu.ecomm.gwt.shared;

import com.google.gwt.core.client.JavaScriptObject;

public class CharacteristicJSO extends JavaScriptObject {

    protected CharacteristicJSO() {
    }

    public static native CharacteristicJSO create(double id, double productId, String value)
        /*-{ return {characteristicId : id,
                     productId : productId,
                     characteristicValue : value} }-*/;

    public final native double getId() /*-{ return this.characteristicId; }-*/;

    public final native String getName() /*-{ return this.name; }-*/;

    public final native String getValue() /*-{ return this.value; }-*/;
}
