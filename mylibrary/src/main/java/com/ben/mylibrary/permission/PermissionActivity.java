package com.ben.mylibrary.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.ben.mylibrary.R;


/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-21 14:19
 */
public class PermissionActivity extends Activity {

    private static final String TAG = "PermissionActivity";

    private static final String PERMISSION = "permission";
    private static final String REQUEST_CODE = "request_code";
    private static final int REQUEST_CODE_DEFAULT = 1980;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transparent);

        Intent intent = getIntent();
        String permission = intent.getStringExtra(PERMISSION);
        int requestCode = intent.getIntExtra(REQUEST_CODE, REQUEST_CODE_DEFAULT);


        //1.检查权限是否申请
        boolean isApply = PermissionUtil.permissionCheck(this, permission);
        if (isApply) {
            //已经申请
            finish();
            return;
        }

        //申请权限
        ActivityCompat.requestPermissions(this, new String[]
                {permission}, requestCode);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean check = PermissionUtil.permissionCheck(grantResults);

        if (check) {
            Toast.makeText(this, "你已经获得该权限！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "你已拒绝，请到应用界面申请权限！", Toast.LENGTH_SHORT).show();
        }

        finish();

    }

    public static void transactApply(Activity activity, String permission, int requestCode) {
        Intent intent = new Intent(activity, PermissionActivity.class);
        intent.putExtra(PERMISSION, permission);
        intent.putExtra(REQUEST_CODE, requestCode);
        activity.startActivity(intent);
    }
}
