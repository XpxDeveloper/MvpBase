package com.xpxcoder.libcore.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xpxcoder.libcore.R;

import butterknife.ButterKnife;

/**************************************************************
 * @author: xpxCoder.
 * CreateTime: 2020-07-25 22:38(星期六)
 * Description:
 * ************************************************************/
public class LoadingProgressDialog extends Dialog {

    TextView tvLoadDesc;

    public LoadingProgressDialog(@NonNull Context context) {
        this(context, R.style.BaseDialog);
    }

    public LoadingProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initDialog();
    }

    private void initDialog() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        tvLoadDesc = contentView.findViewById(R.id.tv_load_desc);
        ButterKnife.bind(this, contentView);
        setContentView(contentView);
        getWindow().setGravity(Gravity.CENTER);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void show(String text) {
        tvLoadDesc.setText(text);
        show();
    }

}

