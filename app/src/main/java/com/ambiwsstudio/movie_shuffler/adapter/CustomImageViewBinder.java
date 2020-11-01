package com.ambiwsstudio.movie_shuffler.adapter;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;

@InverseBindingMethods({@InverseBindingMethod(
        type = CustomImageView.class,
        attribute = "link",
        event = "linkAttrChanged",
        method = "getLink")})
public class CustomImageViewBinder {

    @BindingAdapter(value = "linkAttrChanged")
    public static void setListener(CustomImageView customImageView, final InverseBindingListener linkAttrChanged) {

        // TODO

    }

    @BindingAdapter("link")
    public static void setLink(CustomImageView view, String value) {
        if (value != null && !value.equals(view.getLink()))
            view.setLink(value);
    }

    @InverseBindingAdapter(attribute = "link")
    public static String getLink(CustomImageView customImageView) {
        return customImageView.getLink();
    }

}
