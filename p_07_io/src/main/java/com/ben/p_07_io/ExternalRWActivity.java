package com.ben.p_07_io;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class ExternalRWActivity extends AppCompatActivity {

    private static final String TAG = "ExternalRWActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_rw_activity);

        findViewById(R.id.test_rw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExternalStorageWritable()) {
                    Toast.makeText(ExternalRWActivity.this, "外部存储器可读写!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ExternalRWActivity.this, "外部存储器不可读写!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.show_main_dir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainDir();
            }
        });

        findViewById(R.id.show_standard_dir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStandardDir();
            }
        });

        findViewById(R.id.show_private_dir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrivateDir();
            }
        });

        findViewById(R.id.write_to_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeStrToFile("数据数据数据数据数据~\n");
            }
        });

        findViewById(R.id.write_to_emulated).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToEmulated();
            }
        });

        findViewById(R.id.delete_apk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTest();
            }
        });
    }



    private void deleteTest() {
        File emulatedFile = Environment.getExternalStorageDirectory();

        File file = new File(emulatedFile, "app-debug.apk");

        if (file.exists()) {
            Log.d(TAG, "exists()");
            file.delete();
        }
    }

    private void writeToEmulated() {
        File emulatedFile = Environment.getExternalStorageDirectory();

        File file = new File(emulatedFile, "data.txt");

        if (file.exists()) {
            file.delete();
        }
        //用流写出
        BufferedOutputStream bos = null;
        try {
            file.createNewFile();
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write("data data data data data data data data data data\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void showPrivateDir() {
        String fileDir = getExternalFilesDir(null).getAbsolutePath();

        String movieDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath();
        String documentDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        Toast.makeText(this, fileDir + "\n" +
                movieDir + "\n" + documentDir + "\n", Toast.LENGTH_SHORT).show();
    }

    private void showStandardDir() {
        /*
         * DIRECTORY_MUSIC, //目录_音乐
         *DIRECTORY_PODCASTS,//目录_播客 博客 精采广播
         *DIRECTORY_RINGTONES,//目录_来电铃声 手机铃声 随机铃声
         *DIRECTORY_ALARMS,//目录_警告 闹钟铃声 闹钟
         *DIRECTORY_NOTIFICATIONS,//目录_通知
         *DIRECTORY_PICTURES,//目录_相关图片
         *DIRECTORY_MOVIES,//目录_电影
         *DIRECTORY_DOWNLOADS,//目录_下载
         *DIRECTORY_DCIM,//目录_数据中心基础设施管理
         *DIRECTORY_DOCUMENTS//目录_单据 证件 文件
         */
        String dcimDir = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DCIM).getAbsolutePath();

        String pictureDir = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES).getAbsolutePath();

        String musicDir = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_MUSIC).getAbsolutePath();

        String downloadDir = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

        Toast.makeText(this, dcimDir + "\n" + pictureDir +
                "\n" + musicDir + "\n" + downloadDir + "\n", Toast.LENGTH_SHORT).show();

    }

    private void showMainDir() {
        String mainDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(this, mainDir, Toast.LENGTH_SHORT).show();
    }

    /* Checks if external storage is available for read and write */
    /* 检查外部存储器是否可用于读和写 */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        Log.e(TAG, "isExternalStorageWritable: " + state);
        return false;
    }


    private void writeStrToFile(String str) {
        if (!isExternalStorageWritable()) return;

        //允许写入操作
        File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        //字符串写出路径
        Log.d(TAG, "dir path : " + dir.getAbsolutePath());

        if (!dir.exists()) {
            dir.mkdirs();
        }

        //创建一个文件
        File file = new File(dir, "str.txt");

        if (file.exists()) {
            file.delete();
        }

        //用流写出
//        BufferedOutputStream bos = null;
//        try {
//            file.createNewFile();
//            bos = new BufferedOutputStream(new FileOutputStream(file));
//            bos.write(str.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        //用NIO写出
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        try (FileChannel channel = new FileOutputStream(file).getChannel()) {
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}