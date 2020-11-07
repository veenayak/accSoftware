/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author winayak
 */

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_Alerts")
public class Activity_pojo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="description")
    private String desc;
    
    @Column(name="type")
    private String type;
    
    @Column(name="skey")
    private int key;    
    
    @Column(name="date")
    private java.sql.Timestamp date;
    
    @Column(name="addedby")
    private String addedby;
    
    @Column(name="warehouse")
    private String warehouse; 
    
    @Column(name="gstin")
    private String gstin;
    
    
    public String getdesc(){
        return desc;
    }
    public int getid(){
        return id;
    }
    public String gettype(){
        return type;
    }
    public int getskey(){
        return key;
    }
    public java.sql.Timestamp  getdate(){
        return date;
    }
    public String getwarehouse(){
        return warehouse;
    }
    public String getaddedby(){
        return addedby;
    }
    public String getgstin(){
        return gstin;
    }
    
    
    public void setgstin(String gstin){
        this.gstin = gstin;
    }    
    public void setwarehouse(String warehouse){
        this.warehouse =  warehouse;
    }
    public void setdesc(String desc){
        this.desc = desc;
    }
    public void settype(String type){
        this.type = type; 
    }
    public void setkey(int key){
        this.key = key;
    }    
    public void setdate(java.sql.Timestamp date){
        this.date = date;
    }
    public void setaddedby(String addedby){
        this.addedby = addedby;
    }
    
    
}