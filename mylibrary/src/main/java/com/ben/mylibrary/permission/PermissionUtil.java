package com.ben.mylibrary.permission;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-22 14:11
 */
public class PermissionUtil {

    //1.检查权限是否申请
    public static boolean permissionCheck(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity,
                permission) == PackageManager.PERMISSION_GRANTED;
    }

    //2.再次确认权限是否申请
    public static boolean permissionCheck(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }


}
