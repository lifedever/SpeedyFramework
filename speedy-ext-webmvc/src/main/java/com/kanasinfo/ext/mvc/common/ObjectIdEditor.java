package com.kanasinfo.ext.mvc.common;

import com.kanasinfo.utils.StringKit;
import org.bson.types.ObjectId;

import java.beans.PropertyEditorSupport;

public class ObjectIdEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        setValue(StringKit.isBlank(text) ? null : new ObjectId(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
