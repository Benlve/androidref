package com.ben.p_01_sqlite.data;

import android.util.Log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO 数据源，随着类加载数据被加载到内存
 * @create 2023-03-20 10:23
 */
public class DataSource {

    private static final String TAG = "DataSource";

    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String GENDER = "gender";

    public static final List<Map<String, String>> SQL_MEMBER_LIST;

    static {
        SQL_MEMBER_LIST = new ArrayList<>();


        //装载数据
        Map<String, String> map1 = new HashMap<>();
        map1.put(NAME, "张三");
        map1.put(ID, "1263");
        map1.put(GENDER, "true");
        SQL_MEMBER_LIST.add(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put(NAME, "李四");
        map2.put(ID, "1153");
        map2.put(GENDER, "true");
        SQL_MEMBER_LIST.add(map2);

        Map<String, String> map3 = new HashMap<>();
        map3.put(NAME, "王五");
        map3.put(ID, "1098");
        map3.put(GENDER, "true");
        SQL_MEMBER_LIST.add(map3);

        Map<String, String> map4 = new HashMap<>();
        map4.put(NAME, "Candy");
        map4.put(ID, "7068");
        map4.put(GENDER, "false");
        SQL_MEMBER_LIST.add(map4);

        Map<String, String> map5 = new HashMap<>();
        map5.put(NAME, "Lucy");
        map5.put(ID, "2023");
        map5.put(GENDER, "false");
        SQL_MEMBER_LIST.add(map5);

        Map<String, String> map6 = new HashMap<>();
        map6.put(NAME, "Mia");
        map6.put(ID, "2000");
        map6.put(GENDER, "false");
        SQL_MEMBER_LIST.add(map6);

        Log.d(TAG, "SQL_MEMBER_LIST = " + SQL_MEMBER_LIST);

    }


}
