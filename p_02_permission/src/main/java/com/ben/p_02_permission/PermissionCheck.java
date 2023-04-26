package com.ben.p_02_permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-21 13:20
 */
public class PermissionCheck {
    public static boolean applyPermission(Activity activity, String permission, int REQUEST_CODE) {
        if (ContextCompat.checkSelfPermission(activity,
                permission) != PackageManager.PERMISSION_GRANTED) {
            //没有申请对应权限，申请对应权限
            ActivityCompat.requestPermissions(activity, new String[]
                    {permission}, REQUEST_CODE);
            return true;
        } else {
            return false;
        }
    }
}
