package com.ben.p_07_io;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-30 11:18
 */
public class PropertiesUtil {
    private static final String TAG = "PropertiesUtil";
    public static final String propsAcctPath = "account.properties";
    public static final String propsProjectPath = "project_build.properties";

    public static Properties getProps(Context context,String src) {
        Properties props = new Properties();

        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(src);
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return props;
    }

}
