package com.ben.p_06_toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO toast
 * @create 2023-03-24 09:44
 */
public class ToastFormat {

    private Context mContext;
    private Toast mToast;

    public ToastFormat(@NonNull Context appContext) {
        this.mContext = appContext;
    }

    public void showCustomToast(String tips) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.toast_format, null);
        TextView tipsText = view.findViewById(R.id.tips);
        tipsText.setText(tips);

        if (mToast != null) {
            mToast.cancel();//防止多次点击，一直弹出
        }
        mToast = new Toast(mContext);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(view);

        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();

    }

    public void showCustomDirection(String tips) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.toast_format, null);
        TextView tipsText = view.findViewById(R.id.tips);
        tipsText.setText(tips);

        if (mToast != null) {
            mToast.cancel();//防止多次点击，一直弹出
        }
        mToast = new Toast(mContext);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(view);

        mToast.setGravity(Gravity.BOTTOM, 0, 400);
        mToast.show();

    }
}
