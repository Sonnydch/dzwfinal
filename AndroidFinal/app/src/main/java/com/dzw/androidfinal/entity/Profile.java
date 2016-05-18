package com.dzw.androidfinal.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by sonny on 16-5-18.
 */
public class Profile extends DataSupport {
    @Column(nullable = false)
    private int id;
    private String hobby;
    private String name;
    private Date birth;
    private int accountId;


    public Profile(int accountId, Date birth, String hobby, int id, String name) {
        this.accountId = accountId;
        this.birth = birth;
        this.hobby = hobby;
        this.id = id;
        this.name = name;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
