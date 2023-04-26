package com.ben.p_07_io;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ben.mylibrary.permission.PermissionActivity;

import java.util.Properties;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "props_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applyPermissions();

        Properties props = PropertiesUtil.getProps(getApplicationContext(), PropertiesUtil.propsAcctPath);
        Log.d(TAG, "account = " + props.get("account"));
        Log.d(TAG, "password = " + props.get("password"));
        props = PropertiesUtil.getProps(getApplicationContext(), PropertiesUtil.propsProjectPath);
        Set<Object> objects = props.keySet();
        for (Object obj : objects) {
            Log.d(TAG, "obj = " + props.get(obj));
        }

        findViewById(R.id.skip_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExternalRWActivity.class));
            }
        });

    }

    private void applyPermissions() {
        PermissionActivity.transactApply(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, 1898);
    }
}