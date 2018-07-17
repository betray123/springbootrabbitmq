package com.zk.entity;

import java.io.Serializable;

/**
 * Created by zk on 18/7/17.
 */
public class Book implements Serializable{
    private static final long serialVersionUID = -2164058270260403154L;

    private String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.id.toString() +"   "+ this.name.toString();
    }
}
