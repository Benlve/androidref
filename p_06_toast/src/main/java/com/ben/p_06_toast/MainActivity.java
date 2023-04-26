package com.ben.p_06_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * TODO 自定义toast样式，可以调节位置，可以设置大小，自定义内容
 */
public class MainActivity extends AppCompatActivity {

    ToastFormat mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toast = findViewById(R.id.toast);
        Button bottom = findViewById(R.id.bottom);

        mToast = new ToastFormat(getApplicationContext());


        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.showCustomToast("你好！Toast!");
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.showCustomDirection("偏下居中");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mToast != null) {
            mToast = null;
        }
    }
}