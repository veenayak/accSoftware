/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author winayak
 */
@Entity
@Table(name="test_warehouse")
public class Warehouse_pojo {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;    
    
    @Column(name="warehousename")
    private String warename;      
    
    @Column(name="date")
    private java.sql.Timestamp date;
    
    @Column(name="pincode")
    private int pincode;   
    
    @Column(name="address")
    private String address; 
    
    @Column(name="state")
    private String state;   
    
    @Column(name="statecode")
    private String statecode;   
    
    @Column(name="city")
    private String city;  
    
    @Column(name="info")
    private boolean info;
             
    @Column(name="gstin")
    private String gstin;
    
    public int getid(){
        return id;
    }
    public String getwarename(){
        return warename;
    }
    public java.sql.Timestamp getdate(){
        return date;
    }
    public String getstate(){
        return state;
    }
    public String getstatecode(){
        return statecode;
    }
    public int getpincode(){
        return pincode;
    }
    public String getaddress(){
        return address;
    }
    public String getcity(){
        return city;
    }
    public boolean getinfo(){
        return info;
    }
    
    public String getgstin(){
        return gstin;
    }
    
    
    public void setgstin(String gstin){
        this.gstin = gstin;
    }
    public void setwarename(String warename){
        this.warename =  warename;
    }
    public void setdate(java.sql.Timestamp date){
        this.date =  date;
    }
    public void setpincode(int pin){
        this.pincode = pin;
    }
    public void setstate(String state){
        this.state = state;
    }
    public void setstatecode(String statecode){
        this.statecode = statecode;
    }
    public void setcity(String city){
        this.city = city;
    }
    public void setaddress(String address){
        this.address = address;
    }
    public void setinfo(boolean info){
        this.info = info;
    }  
    
}
