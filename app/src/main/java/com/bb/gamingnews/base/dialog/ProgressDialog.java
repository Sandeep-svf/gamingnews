package com.bb.gamingnews.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bb.gamingnews.R;

public class ProgressDialog extends Dialog {
    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.Progress_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progress_dialog);
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(imageView)
                .asGif()
                .load(R.drawable.loading)
                .into(imageView);
    }

    public void setTitle(String text) {

    }

    public void setMessage(String text) {

    }
}
