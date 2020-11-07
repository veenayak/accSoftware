/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.Column;

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
@Table(name="test_accounts")
public class Accounts_pojo{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="accountname")
    private String accname;
    
    @Column(name="balance")
    private int balance;
    
    @Column(name="accounttype")
    private String acctype; 
       
    @Column(name="date")
    private java.sql.Timestamp date;
    
    @Column(name="gstin")
    private String gstin;
    
    
    public int gettid(){
        return id;
    }
    public String getacctype(){
        return acctype;
    }
    public String getaccname(){
        return accname;
    }
    public int getbalance(){
        return balance;
    }
    public java.sql.Timestamp  getdate(){
        return date;
    }    
    public String getgstin(){
        return gstin;
    }

    public void setgstin(String gstin){
        this.gstin = gstin;
    }
    public void setacctype(String type){
        this.acctype = type; 
    }
    public void setaccname(String name){
        this.accname = name; 
    }
    public void setdate(java.sql.Timestamp date){
        this.date = date;
    }
    public void setbalance(int balance){
        this.balance = balance;
    }    
}
