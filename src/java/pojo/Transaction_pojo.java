/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Date;
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
@Table(name="test_transaction")
public class Transaction_pojo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="invoiceid")
    private int invoiceid;        
    
    @Column(name="customerid")
    private int customerid;   
    
    @Column(name="warehouseid")
    private int warehouseid;   
    
    @Column(name="amount")
    private String amount;   
    
    @Column(name="date")
    private java.sql.Timestamp date; 
    
    @Column(name="transactiondate")
    private String transdate; 
    
    @Column(name="mode")
    private String mode;   
    
    @Column(name="description")
    private String desc;   
    
    @Column(name="gstin")
    private String gstin;
    
    @Column(name="totalamount")
    private float totalamount;
    
    @Column(name="dueamount")
    private float dueamount;
    
    @Column(name="type")
    private String type;
    
    
    public int getid(){
        return id;
    }
    public int getinvoiceid(){
        return invoiceid;
    }
    public int getcustomerid(){
        return customerid;
    }
    public int getwarehouseid(){
        return warehouseid;
    }
    public String getamount(){
        return amount;
    }
    public java.sql.Timestamp getdate(){
        return date;
    }
    public String getmode(){
        return mode;
    }
    public String getdesc(){
        return desc;
    }
    public String getgstin(){
        return gstin;
    }
    public String gettransdate(){
        return transdate;
    }
    public float gettotalamount(){
        return totalamount;
    }
    public float getdueamount(){
        return dueamount;
    }
    public String gettype(){
        return type;
    }
    
    
    public void setgstin(String gstin){
        this.gstin = gstin;
    }
    public void setinvoiceid(int invoiceid){
        this.invoiceid =  invoiceid;
    }
    public void setcustomerid(int customerid){
        this.customerid = customerid;
    }
    public void setwarehouseid(int warehouseid){
        this.warehouseid = warehouseid;
    }
    public void setamount(String amount){
        this.amount =  amount;
    }
    public void setdate(Date date){
        this.transdate =  transdate;
    }
    public void settransdate(String transdate){
        this.transdate =  transdate;
    }
    public void setmode(String mode){
        this.mode =  mode;
    }
    public void setdesc(String desc){
        this.desc =  desc;
    }
    public void settotalamount(float totalamount){
        this.totalamount = totalamount;
    }
    public void setdueamount(float dueamount){
        this.dueamount = dueamount;
    }
    public void settype(String type){
        this.type = type;
    }
    
}
