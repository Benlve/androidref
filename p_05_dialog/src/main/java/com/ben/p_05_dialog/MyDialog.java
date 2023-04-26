package com.ben.p_05_dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO 自定义样式dialog，可调节背景颜色值
 * @create 2023-03-23 15:31
 */
public class MyDialog extends DialogFragment {

    private static final String TAG = "MyDialog";

    private Context mContext;

    public MyDialog() {
        Log.d(TAG, "MyDialog()");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "MyDialog()");
        mContext = context;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局，findView
        View view = inflater.inflate(R.layout.dialog_my, container);
        //控件初始化
        Button confirm,cancel;
        confirm = view.findViewById(R.id.confirm);
        cancel = view.findViewById(R.id.cancel);

        View outsideDismiss = view.findViewById(R.id.outside_dismiss);

        LinearLayout dialogContent = view.findViewById(R.id.dialog_content);
        dialogContent.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(@SuppressLint("ClickableViewAccessibility") View v, MotionEvent event) {
                return true;
            }
        });

        outsideDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });

        return view;
    }

    private Dialog mDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //1.创建dialog
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_my);
        //2.设置窗口属性
        Window window = mDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND
                | WindowManager.LayoutParams.FLAG_DIM_BEHIND);//清除背景阴影
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.x = 100;
        layoutParams.y = 100;
        window.setAttributes(layoutParams);

        window.getDecorView().setBackgroundResource(R.drawable.bg_dialog);//设置背景透明

        return mDialog;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
