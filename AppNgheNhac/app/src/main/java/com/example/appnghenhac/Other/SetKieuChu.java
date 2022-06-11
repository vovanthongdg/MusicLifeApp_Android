package com.example.appnghenhac.Other;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetKieuChu {
    String arr[] = new String[]{"UVNVan_B.TTF"};

    public void set(Context ct, TextView t) {
        t.setTypeface(Typeface.createFromAsset(ct.getAssets(), arr[0]));
    }
    public void set(Context ct, Button t) {
        t.setTypeface(Typeface.createFromAsset(ct.getAssets(), arr[0]));
    }
    public void set(Context ct, EditText t) {
        t.setTypeface(Typeface.createFromAsset(ct.getAssets(), arr[0]));
    }
}
