package com.ben.p_01_sqlite;

import static com.ben.p_01_sqlite.data.DataSource.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ben.p_01_sqlite.adapter.SourceDataAdapter;
import com.ben.p_01_sqlite.bean.SQLMember;
import com.ben.p_01_sqlite.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final String DB_NAME = "SQLMember.db";

    //private static int VERSION = 1;

    //1.新增一张表
    public static final String CREATE_MEMBER_INFO = "create table member_info ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "address text, "
            + "contact text)";

    private Button showDataSource;
    private Button createDB;
    private Button newTable;
    private Button addToDB;
    private Button updateValue;
    private Button deleteValue;
    private Button queryAllValue;
    private EditText contentAdded;

    private List<SQLMember> list;

    private RecyclerView recyclerviewDataSource;
    private SourceDataAdapter mAdapter;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
    }

    private void findView() {
        showDataSource = findViewById(R.id.show_data_source);
        showDataSource.setOnClickListener(this);
        createDB = findViewById(R.id.create_db);
        createDB.setOnClickListener(this);
        newTable = findViewById(R.id.new_table);
        newTable.setOnClickListener(this);
        recyclerviewDataSource = findViewById(R.id.recyclerview_data_source);
        addToDB = findViewById(R.id.add_to_db);
        addToDB.setOnClickListener(this);
        updateValue = findViewById(R.id.update_value);
        updateValue.setOnClickListener(this);
        deleteValue = findViewById(R.id.delete_value);
        deleteValue.setOnClickListener(this);
        queryAllValue = findViewById(R.id.query_all_value);
        queryAllValue.setOnClickListener(this);
        contentAdded = findViewById(R.id.content_added);
    }

    private void initData() {
        dbHelper = new DBHelper(this, DB_NAME, null, 2);
    }

    private void getData() {
        list = new ArrayList<>();
        if (SQL_MEMBER_LIST == null) {
            return;
        }
        for (int i = 0; i < SQL_MEMBER_LIST.size(); i++) {
            String name = SQL_MEMBER_LIST.get(i).get(NAME);

            int id = Integer.parseInt(SQL_MEMBER_LIST.get(i).get(ID));
            boolean gender = Boolean.parseBoolean(SQL_MEMBER_LIST.get(i).get(GENDER));
            SQLMember member = new SQLMember(name, id, gender);
            list.add(member);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_data_source:
                show();
                break;
            case R.id.create_db:
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.getVersion();
                break;
            case R.id.new_table:
                dbHelper = new DBHelper(this, DB_NAME, null, 2);
                dbHelper.getWritableDatabase();
                break;
            case R.id.add_to_db:
                add();
                break;
            case R.id.update_value:
                update();
                break;
            case R.id.delete_value:
                delete();
                break;
            case R.id.query_all_value:
                query();
                break;
            default:
                break;
        }
    }

    private void query() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d(TAG, "query()");
        //查询sql_member表中所有数据
        Cursor cursor = database.query("sql_member", null, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int nameColumnIndex = cursor.getColumnIndex("name");
                String name = cursor.getString(nameColumnIndex);

                int idColumnIndex = cursor.getColumnIndex("member_id");
                int id = cursor.getInt(idColumnIndex);

                int genderColumnIndex = cursor.getColumnIndex("gender");
                String genderStr = cursor.getString(genderColumnIndex);
                boolean gender = Boolean.parseBoolean(genderStr);

                Log.d(TAG, "query() name = " + name + ", id = " + id + ", gender = " + gender + "\n");

            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    private void delete() {
        int ret = queryGrep();
        if (ret == -1) {
            return;
        }
        String name = SQL_MEMBER_LIST.get(ret).get(NAME);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("sql_member", "name = ?", new String[]{name});
        Toast.makeText(this, name + "删除成功！", Toast.LENGTH_SHORT).show();
    }

    private void update() {
        int ret = queryGrep();
        if (ret == -1) {
            return;
        }
        String name = SQL_MEMBER_LIST.get(ret).get(NAME);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("member_id", 1850);
        database.update("sql_member", values, "name = ?", new String[]{name});
        Toast.makeText(this, name + "更新成功！", Toast.LENGTH_SHORT).show();

    }

    private void add() {
        int ret = queryGrep();
        if (ret == -1) {
            return;
        }
        String name = SQL_MEMBER_LIST.get(ret).get(NAME);
        String id = SQL_MEMBER_LIST.get(ret).get(ID);
        String gender = SQL_MEMBER_LIST.get(ret).get(GENDER);

        addToDB(name, id, gender);

    }

    private void addToDB(String name, String id, String gender) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        //装入数据
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("member_id", id);
        values.put("gender", gender);
        database.insert("sql_member", null, values);
        Toast.makeText(this, name + "添加成功！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据用户输入，过滤地添加到database
     */
    private int queryGrep() {
        //获取输入
        String content = contentAdded.getText().toString();
        if ("".equals(content)) {
            Toast.makeText(this, "内容为空，添加/修改失败！", Toast.LENGTH_LONG).show();
            return -1;
        }
        Log.d(TAG, "content = " + content);
        //遍历
        for (int i = 0; i < SQL_MEMBER_LIST.size(); i++) {
            Log.d(TAG, "NAME = " + SQL_MEMBER_LIST.get(i).get(NAME));
            if (content.equals(SQL_MEMBER_LIST.get(i).get(NAME))) {
                return i;
            }
        }
        //没有找到对应内容
        Toast.makeText(this, "您输入的内容不存在，添加/修改失败！", Toast.LENGTH_LONG).show();
        return -1;
    }

    /**
     * TODO 显示数据源
     */
    private void show() {
        if (mAdapter == null) {
            getData();
            mAdapter = new SourceDataAdapter(list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerviewDataSource.setAdapter(mAdapter);
            recyclerviewDataSource.setLayoutManager(layoutManager);
        }
    }
}