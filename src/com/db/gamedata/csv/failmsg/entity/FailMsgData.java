package com.db.gamedata.csv.failmsg.entity;

public class FailMsgData{
    private int id;

    private String msg;

    public void setId(int id) {
        this.id =id;
    }

    public int getId(){
        return this.id;
    }

    public void setMsg(String msg) {
        this.msg =msg;
    }

    public String getMsg(){
        return this.msg;
    }

}