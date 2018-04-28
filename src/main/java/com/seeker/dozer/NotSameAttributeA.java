package com.seeker.dozer;

import java.util.Date;

public class NotSameAttributeA {
    private long id;
    private String name;
    private Date date;

    // 省略getter/setter

    @Override
    public String toString() {
        return "NotSameAttributeA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}