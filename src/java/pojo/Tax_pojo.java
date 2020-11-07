///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pojo;
//
//import java.io.Serializable;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
///**
// *
// * @author winayak
// */
//@Entity
//@Table(name="test_tax")
//public class Tax_pojo {
//    
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private int id;
//    
//    @Column(name="taxname")
//    private String taxname;
//    
//    @Column(name="rate")
//    private float rate;
//    
//    @Column(name="date")
//    private java.sql.Timestamp date;
//    
//    @Column(name="gstin")
//    private String gstin;
//    
//          
//    public int getid(){
//        return id;
//    }
//    public String gettaxname(){
//        return taxname;
//    }
//    public Float gettaxrate(){
//        return rate;
//    }
//    public java.sql.Timestamp  getdate(){
//        return date;
//    }
// 
//    public String getgstin(){
//        return gstin;
//    }
//
//    
//    public void setgstin(String gstin){
//        this.gstin = gstin;
//    }
//    public void settaxname(String taxname){
//        this.taxname =  taxname;
//    }
//    public void settaxrate(float rate){
//        this.rate =  rate;
//    }
//    public void setdate(java.sql.Timestamp date){
//        this.date = date;
//    }
//}
