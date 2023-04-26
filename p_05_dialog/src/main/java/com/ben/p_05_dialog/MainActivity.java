package com.ben.p_05_dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button alertDialog = findViewById(R.id.alert_dialog);
        alertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_Theme);
                dialog.show(getSupportFragmentManager(), "MyDialog");
            }
        });
        Button alertBGDialog = findViewById(R.id.alert_bg_dialog);
        alertBGDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundDialog dialog = new BackgroundDialog();
                dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_Theme2);
                dialog.show(getSupportFragmentManager(), "BackgroundDialog");
            }
        });

        Button alertAndroidDialog = findViewById(R.id.alert_android_dialog);
        alertAndroidDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
//        AlertDialog dialog = new AlertDialog.Builder(this, R.layout.alert_dialog).create();
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.width = 600;
//        layoutParams.height = 400;
//        window.setBackgroundDrawableResource(R.drawable.bg_alert_dialog);
//        window.setAttributes(layoutParams);
//        dialog.setMessage("saasdasdas");
//        dialog.show();


        new AlertDialog.Builder(this).setTitle("警告！！！")
                .setMessage("确认继续？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}