package com.ben.p_03_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 透明activity
 */
public class    TransparentActivity extends AppCompatActivity {

    private static final String TAG = "TransparentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);

        Button alertDialog = findViewById(R.id.alert_dialog);
        alertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动一个DialogFragment
                MyDialog dialog = new MyDialog(TransparentActivity.this);
                dialog.show(getSupportFragmentManager(),"tag");
                Log.d(TAG, "onClick()");
            }
        });


    }


}