package com.buu.se.s55160026.mysqlite_student;

/**
 * Created by thamrongs on 2/3/15 AD.
 */
import java.io.Serializable;

/**
 * Created by aekapop on 2/3/15 AD.
 */
public class Student implements Serializable{
    private int id;
    private String name;
    private String mail;
    private String phone;
    private String memo;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return name;
    }
}
