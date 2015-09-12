package com.synwing.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_info")
public class Userinfo  implements java.io.Serializable{
    
    private static final long serialVersionUID = 71123123L;
    
    @Id
    @Column(name = "id", nullable = false)
    public int id;
    
    public int uid;
    
    public String info;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
