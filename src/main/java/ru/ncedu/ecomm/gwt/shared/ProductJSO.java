package ru.ncedu.ecomm.gwt.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;


public class ProductJSO extends JavaScriptObject {

    protected ProductJSO() {
    }

    // JSNI methods to get stock data.
    // http://www.gwtproject.org/doc/latest/tutorial/JSON.html

    public final native double getProductId() /*-{ return this.productId; }-*/;

    public final native String getName() /*-{ return this.name; }-*/;

    public final native double getPrice() /*-{ return this.price; }-*/;

    public final native double getDiscountId() /*-{ return this.discountId; }-*/;

    public final native double getCategoryId() /*-{ return this.categoryId; }-*/;

    public final native String getDescription() /*-{ return this.description; }-*/;

    public final native JsArray<CharacteristicGroupJSO> getCharacteristicGroups() /*-{ return this.characteristicGroups; }-*/;

}
