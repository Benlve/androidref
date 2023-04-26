package com.ben.p_01_sqlite.helper;

import static com.ben.p_01_sqlite.MainActivity.CREATE_MEMBER_INFO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-20 11:45
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private int version;

    private Context mContext;

    //创建SQLMember表
    public static final String CREATE_SQL_MEMBER = "create table sql_member ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "member_id integer, "
            + "gender integer)";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
        this.version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate()");
        db.execSQL(CREATE_SQL_MEMBER);
        db.execSQL(CREATE_MEMBER_INFO);
        if(version == 1) {
            Toast.makeText(mContext, "创建数据库成功！", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, "新增表格成功！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade()");
        db.execSQL("drop table if exists sql_member");
        db.execSQL("drop table if exists member_info");
        onCreate(db);
        this.version = newVersion;
    }
}
