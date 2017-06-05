package ru.ncedu.ecomm.gwt.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class CharacteristicGroupCategoryJSO extends JavaScriptObject {

    protected CharacteristicGroupCategoryJSO() {
    }

    public final native double getId() /*-{ return this.characteristicGroupId }-*/;

    public final native String getName() /*-{ return this.characteristicGroupName; }-*/;

    public final native JsArray<CharacteristicCategoryJSO> getCharacteristics() /*-{ return this.characteristics; }-*/;
}
