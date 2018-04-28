package com.seeker.dozer;

import java.util.Date;

public class NotSameAttributeB {
    private long id;
    private String value;
    private Date date;

    // 省略getter/setter

    @Override
    public String toString() {
        return "NotSameAttributeB{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}