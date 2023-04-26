package com.ben.p_01_sqlite.bean;

import java.util.Objects;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO bean类 代表一个SQL成员
 * @create 2023-03-20 10:18
 */
public class SQLMember {
    private String name;
    private int id;
    private boolean gender;//true 男 false 女

    public SQLMember(String name, int id, boolean gender) {
        this.name = name;
        this.id = id;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SQLMember sqlMember = (SQLMember) o;
        return id == sqlMember.id && gender == sqlMember.gender && Objects.equals(name, sqlMember.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, gender);
    }

    @Override
    public String toString() {
        return "SQLMember{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", gender=" + gender +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public SQLMember() {
    }
}
