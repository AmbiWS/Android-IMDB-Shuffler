package com.ambiwsstudio.movie_shuffler.adapter;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
import timber.log.Timber;

@InverseBindingMethods({@InverseBindingMethod(
        type = CustomImageView.class,
        attribute = "link",
        event = "linkAttrChanged",
        method = "getLink")})
public class CustomImageViewBinder {

    @BindingAdapter(value = "linkAttrChanged")
    public static void setListener(CustomImageView customImageView, final InverseBindingListener linkAttrChanged) {

        Timber.d("CustomImageViewBinder ->" + "Change Caught: " + customImageView.toString() + ", " + linkAttrChanged.toString());

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
