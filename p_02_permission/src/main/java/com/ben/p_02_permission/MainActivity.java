package com.ben.p_02_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button camera;
    private static final int REQUEST_CODE_CAMERA = 1980;
    private Button contact;
    private static final int REQUEST_CODE_CONTACT = 4682;
    private Button location;
    private static final int REQUEST_CODE_LOCATION = 4923;
    private Button audio;
    private static final int REQUEST_CODE_AUDIO = 1689;
    private Button phone;
    private static final int REQUEST_CODE_PHONE = 3851;
    private Button sensor;
    private static final int REQUEST_CODE_SENSOR = 2860;

    private Button io;
    private static final int REQUEST_CODE_IO = 7985;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        initData();

    }

    private void findView() {
        camera = findViewById(R.id.camera);
        camera.setOnClickListener(this);
        contact = findViewById(R.id.contact);
        contact.setOnClickListener(this);
        location = findViewById(R.id.location);
        location.setOnClickListener(this);
        audio = findViewById(R.id.audio);
        audio.setOnClickListener(this);
        phone = findViewById(R.id.phone);
        phone.setOnClickListener(this);
        sensor = findViewById(R.id.sensor);
        sensor.setOnClickListener(this);
        io = findViewById(R.id.io);
        io.setOnClickListener(this);

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                boolean success1 = PermissionCheck.applyPermission
                        (MainActivity.this, Manifest.permission.CAMERA, REQUEST_CODE_CAMERA);
                if (!success1) {
                    Toast.makeText(this, "你已获得相机权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.contact:
                boolean success2 = PermissionCheck.applyPermission
                        (MainActivity.this, Manifest.permission.WRITE_CONTACTS, REQUEST_CODE_CONTACT);
                if (!success2) {
                    Toast.makeText(this, "你已获得读写联系人权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.location:
                boolean success3 = PermissionCheck.applyPermission
                        (MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION, REQUEST_CODE_LOCATION);
                if (!success3) {
                    Toast.makeText(this, "你已获得定位权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.audio:
                boolean success4 = PermissionCheck.applyPermission
                        (MainActivity.this, Manifest.permission.RECORD_AUDIO, REQUEST_CODE_AUDIO);
                if (!success4) {
                    Toast.makeText(this, "你已获得录音权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.phone:
                boolean success5 = PermissionCheck.applyPermission
                        (MainActivity.this, Manifest.permission.CALL_PHONE, REQUEST_CODE_PHONE);
                if (!success5) {
                    Toast.makeText(this, "你已获得电话权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sensor:
                boolean success6 = PermissionCheck.applyPermission
                        (MainActivity.this, Manifest.permission.BODY_SENSORS, REQUEST_CODE_SENSOR);
                if (!success6) {
                    Toast.makeText(this, "你已获得传感器权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.io:
                boolean success7 = PermissionCheck.applyPermission
                        (MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_CODE_IO);
                if (!success7) {
                    Toast.makeText(this, "你已获得传感器权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                String permissionStr1 = "相机权限";
                boolean isApplySuccess1 = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!isApplySuccess1) {
                    showConfirmDialog(permissionStr1);
                } else {
                    Toast.makeText(this, "你已获得" + permissionStr1, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_CONTACT:
                String permissionStr2 = "读写联系人权限";
                boolean isApplySuccess2 = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!isApplySuccess2) {
                    showConfirmDialog(permissionStr2);
                } else {
                    Toast.makeText(this, "你已获得" + permissionStr2, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_LOCATION:
                String permissionStr3 = "定位权限";
                boolean isApplySuccess3 = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!isApplySuccess3) {
                    showConfirmDialog(permissionStr3);
                } else {
                    Toast.makeText(this, "你已获得" + permissionStr3, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_AUDIO:
                String permissionStr4 = "录音权限";
                boolean isApplySuccess4 = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!isApplySuccess4) {
                    showConfirmDialog(permissionStr4);
                } else {
                    Toast.makeText(this, "你已获得" + permissionStr4, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_PHONE:
                String permissionStr5 = "电话权限";
                boolean isApplySuccess5 = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!isApplySuccess5) {
                    showConfirmDialog(permissionStr5);
                } else {
                    Toast.makeText(this, "你已获得" + permissionStr5, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_SENSOR:
                String permissionStr6 = "传感器权限";
                boolean isApplySuccess6 = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!isApplySuccess6) {
                    showConfirmDialog(permissionStr6);
                } else {
                    Toast.makeText(this, "你已获得" + permissionStr6, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_IO:
                String permissionStr7 = "读写文件权限";
                boolean isApplySuccess7 = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (!isApplySuccess7) {
                    showConfirmDialog(permissionStr7);
                } else {
                    Toast.makeText(this, "你已获得" + permissionStr7, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showConfirmDialog(String tips) {
        new AlertDialog.Builder(MainActivity.this).setTitle("获得" + tips + "才可使用")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        skipToAppDetailIntent(MainActivity.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void skipToAppDetailIntent(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}