package com.dzw.androidfinal.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by sonny on 16-5-18.
 */
public class Account extends DataSupport {
    @Column(nullable = false)
    private String name;
    private String pass;
    private int id;

    public Account(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
