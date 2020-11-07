/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;  

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author winayak
 */
@Entity
@Table(name="test_purchases")
public class Purchases_pojo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name = "invoiceno")
    private String invoiceno;
    
    @Column(name = "invoicesetting")
    private String invoicesetting;
    
    @Column(name = "linkedinvoice")
    private String linkedinvoice;

    @Column(name = "cancelled")
    private boolean cancelled;
    
    @Column(name = "reference")
    private String reference;

    //customer
    @Column(name="customerid")
    private int cid;
    
    @Column(name="customername")
    private String name;
    
    @Column(name="customergstin")
    private String cgstin;    
    
    @Column(name="placeofsupply")
    private String pos;
    
    //dates
    @Column(name="date")
    private java.sql.Timestamp date;
    
    @Column(name="deliveryduedate")
    private Date delduedate;
    
    @Column(name="paymentduedate")
    private Date payduedate;
    
    @Column(name="invoicedate")
    private Date indate;
    
    @Column(name="orderdate")
    private Date odate;
    
    @Column(name="paidon")
    private Date paidon;
    
    @Column(name="deliverydate")
    private Date deldate;
    
    @Column(name="returnmonth")
    private String returnmonth;
    
    @Column(name="returnquarter")
    private String returnquarter;
        
    //amount
    @Column(name="amount")
    private float amount;     
    
    @Column(name="discount")
    private String discount;
    
    @Column(name="transportationcharge")
    private int transcharge;
    
    @Column(name="discounttype")
    private String discounttype;
    
    //accounts
    //@Column(name="bankaccount")
    //private int bankacc;
    
//    @Column(name="accountid")
//    private int account;
//    
    //products
    @Column(name="productsid")
    private String productsid;
    
    @Column(name="productsname")
    private String productsname;
    
    @Column(name="productsqty")
    private String productsqty;
    
    @Column(name="productscost")
    private String productscost;
    
    @Column(name="productsprice")
    private String productsprice;
    
    @Column(name="productsamount")
    private String productsamount;

    //status
    @Column(name="paymentstatus")
    private String paystatus;
    
    @Column(name="deliverystatus")
    private String delstatus;        
    
    //tax
    @Column(name="taxrate")
    private String taxrate;
    
    @Column(name="cgst")
    private String cgst;
    
    @Column(name="cgstamount")
    private String cgstamount;
    
    @Column(name="sgst")
    private String sgst;    
    
    @Column(name="sgstamount")
    private String sgstamount;
    
    @Column(name="igst")
    private String igst;    
    
    @Column(name="igstamount")
    private String igstamount;
    
    @Column(name="cessamount")
    private String cessamount;
    
    @Column(name="cess")
    private String cess;         
    
    //
    @Column(name="warehouse")
    private String warehouse;
    
    @Column(name="warehouseid")
    private int warehouseid;
    
    @Column(name="gstin")
    private String gstin;

    @Column(name="type")
    private String type;
 
    @Column(name="roundoff")
    private Boolean roundoff;
            
        
    public int getid(){
        return id;
    }
    public String getinvoiceno(){
        return invoiceno;
    }
    public String getinvoicesetting(){
        return invoicesetting;
    }
    public String getlinkedinvoice(){
        return linkedinvoice;
    }
    public boolean getcancelled(){
        return cancelled;
    }
    public String getreference(){
        return reference;
    }
    //customer
    public int getcid(){
        return cid;
    }
    public String getcname(){
        return name;
    }
    public String getcustomergstin(){
        return cgstin;
    }
    public String getpos(){
        return pos;
    }
    //dates
    public java.sql.Timestamp getdate(){
        return date;
    }
    public Date getdelduedate(){
        return delduedate;
    }
    public Date getpayduedate(){
        return payduedate;
    }
    public Date getpaidon(){
        return paidon;
    }
    public Date getdeliverydate(){
        return deldate;
    }
    public Date getinvoicedate(){
        return indate;
    }
    public Date getorderdate(){
        return odate;
    }
    public String getreturnmonth(){
        return returnmonth;
    }
    public String getreturnquarter(){
        return returnquarter;
    }
    //status
    public String getpstatus(){
        return paystatus;
    }
    public String getdelstatus(){
        return delstatus;
    }
    //amount
    public float getamount(){
        return amount;
    }
    public String getdiscount(){
        return discount;
    }
    public int gettranscharge(){
        return transcharge;
    }
    public String getdiscounttype(){
        return discounttype;
    }
    
    //account
//    public int getacc(){
//        return account;
//    }
//    public int getbankacc(){
//        return bankaccount;
//    }
    //products
    public String getproductsid(){
        return productsid;
    }
    public String getproductsname(){
        return productsname;
    }
    public String getproductsqty(){
        return productsqty;
    }
    public String getproductscost(){
        return productscost;
    }
    public String getproductsprice(){
        return productsprice;
    }
    public String getproductsamount(){
        return productsamount;
    }
    //tax
    public String getcess(){
        return cess;
    }  
    public String getcessamount(){
        return cessamount;
    }  
    public String getcgst(){
        return cgst;
    }  
    public String getcgstamount(){
        return cgstamount;
    }  
    public String getsgst(){
        return sgst;
    }  
    public String getsgstamount(){
        return sgstamount;
    }  
    public String getigst(){
        return igst;
    }  
    public String getigstamount(){
        return igstamount;
    }  
    public String gettaxrate(){
        return taxrate;
    }    
    //  
    public String gettype(){
        return type;
    }
    public String getwarehouse(){
        return warehouse;
    }        
    public int getwarehouseid(){
        return warehouseid;
    }        
    public String getgstin(){
        return gstin;
    }
    public Boolean getroundoff(){
        return roundoff;
    }

  
    
    public void setinvoiceno(String invoiceno){
        this.invoiceno =  invoiceno;
    }    
    public void setinvoicesetting(String invoicesetting){
        this.invoicesetting =  invoicesetting;
    }     
    public void setlinkedinvoice(String linkedinvoice){
        this.linkedinvoice =  linkedinvoice;
    }   
    public void setcancelled(boolean cancelled){
        this.cancelled =  cancelled;
    }  
    public void setreference(String reference){
        this.reference =  reference;
    }  
    //customer
    public void setcid(int cid){
        this.cid =  cid;
    }
    public void setcname(String cname){
        this.name =  cname;
    }
    public void setpos(String pos){
        this.pos =  pos;
    }
    public void setcustomergstin(String cgstin){
        this.cgstin =  cgstin;
    }
    //date
    public void setdate(java.sql.Timestamp date){
        this.date =  date;
    }
    public void setdelduedate(Date delduedate){
        this.delduedate=  delduedate;
    }
    public void setpayduedate(Date payduedate){
        this.payduedate =  payduedate;
    }
    public void setpaidon(Date paidon){
        this.paidon =  paidon;
    }
    public void setorderdate(Date odate){
        this.odate =  odate;
    }
    public void setdeliverydate(Date deldate){
        this.deldate =  deldate;
    }
    public void setinvoicedate(Date indate){
        this.indate =  indate;
    }  
    public void setreturnmonth(String returnmonth){
        this.returnmonth =  returnmonth;
    }   
    public void setreturnquarter(String returnquarter){
        this.returnquarter =  returnquarter;
    }   
    //status
    public void setpaystatus(String paystatus){
        this.paystatus =  paystatus;
    }
    public void setdelstatus(String delstatus){
        this.delstatus =  delstatus;
    }
    //amount
    public void setamount(float amount){
        this.amount =  amount;
    }
    public void setdiscount(String discount){
        this.discount =  discount;
    }
    public void settranscharge(int transcharge){
        this.transcharge =  transcharge;
    }
    public void setdiscounttype(String discounttype){
        this.discounttype =  discounttype;
    }
    //products
    public void setproductsid(String productsid){
        this.productsid =  productsid;
    }
    public void setproductsname(String productsname){
        this.productsname =  productsname;
    }
    public void setproductsqty(String productsqty){
        this.productsqty =  productsqty;
    }
    public void setproductsprice(String productsprice){
        this.productsprice =  productsprice;
    }
    public void setproductscost(String productscost){
        this.productscost =  productscost;
    }
    public void setproductsamount(String productsamount){
        this.productsamount =  productsamount;
    }

    //tax
    public void setcess(String cess){
        this.cess =  cess;
    }
    public void setcessamount(String cessamount){
        this.cessamount =  cessamount;
    }
    public void setcgst(String cgst){
        this.cgst =  cgst;
    }
    public void setcgstamount(String cgstamount){
        this.cgstamount =  cgstamount;
    }
    public void setsgstamount(String sgstamount){
        this.sgstamount =  sgstamount;
    }
    public void setsgst(String sgst){
        this.sgst =  sgst;
    }
    public void setigstamount(String igstamount){
        this.igstamount =  igstamount;
    }
    public void setigst(String igst){
        this.igst =  igst;
    }
    public void settaxrate(String taxrate){
        this.taxrate =  taxrate;
    }
    //account
//    public void setbankacc(int bankacc){
//        this.bankacc =  bankacc;
//    }
//    public void setacc(int account){
//        this.account =  account;
//    }
//    //
    public void setgstin(String gstin){
        this.gstin = gstin;
    }
    public void setwarehouse(String warehouse){
        this.warehouse =  warehouse;
    }
    public void settype(String type){
        this.type =  type;
    }  
    public void setwarehouseid(int warehouseid){
        this.warehouseid =  warehouseid;
    }
    public void setroundoff(Boolean roundoff){
        this.roundoff = roundoff;
    }

}
