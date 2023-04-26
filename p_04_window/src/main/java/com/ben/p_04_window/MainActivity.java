package com.ben.p_04_window;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.ben.p_04_window.permission_lib.PermissionActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button showWindow;
    private Button dismissWindow;

    private WindowManager windowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionActivity.transactApply(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 1864);

        showWindow = findViewById(R.id.show_window);
        dismissWindow = findViewById(R.id.dismiss_window);

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        showWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick() showWindow");
                showWindow();
            }
        });

        dismissWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick() dismissWindow");
                dismissWindow();
            }
        });

    }

    protected View view;

    @SuppressLint("WrongConstant")
    private void showWindow() {
        if (view == null) {
            view = LayoutInflater.from(this).inflate(R.layout.layout_window, null, true);
        }
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = 480;
        params.height = 480;
        //params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        //params.type = 2024;
        //params.x = 100;
//        params.y = -1200;
        windowManager.addView(view, params);

        dismissWindow.postDelayed(() -> {
            if (view != null) {
                windowManager.removeView(view);
            }
        }, 3000);

    }

    private void dismissWindow() {

    }

}