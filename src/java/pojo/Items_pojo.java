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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
 
/**
 *
 * @author winayak
 */
@Entity
@Table(name="test_Items")
public class Items_pojo {    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name="itemname")
    private String name;
    
    @Column(name="sellingprice")
    private float sp;
    
    @Column(name="costprice")
    private float cp;
    
    @Column(name="taxinclusive")
    private Boolean taxinc;
    
    @Column(name="code")
    private String code;
    
    @Column(name="hsncode")
    private int hsncode;
    
    @Column(name="description")
    private String desc;
    
    @Column(name="category")
    private int cat;
    
    @Column(name="subcategory")
    private int scat;
    
    @Column(name="itemssold")
    private int isold;
    
    @Column(name="alertlimit")
    private int limit;
    
    @Column(name="warehouse")
    private int warehouse;
    
    @Column(name="warestock")
    private int warestock;    
    
    @Column(name="warestockvalue")
    private int warestockvalue;    
    
    @Column(name="size")
    private int size;
    
    @Column(name="unit")
    private int unit;
    
    @Column(name="date")
    private java.sql.Timestamp date;
    
    @Column(name="type")
    private String type;
    
    @Column(name="cgst")
    private float cgst;
    
    @Column(name="cgstamount")
    private float cgstamount;
    
    @Column(name="sgst")
    private float sgst;
    
    @Column(name="sgstamount")
    private float sgstamount;
    
    @Column(name="igst")
    private float igst;
    
    @Column(name="igstamount")
    private float igstamount;
    
    @Column(name="cess")
    private float cess;
    
    @Column(name="cessamount")
    private float cessamount;
    
    @Column(name="gstin")
    private String gstin;
    
    @Column(name="discount")
    private int discount;
    
    @Column(name="discounttype")
    private String discounttype;
    
    @Column(name="imghref")
    private String imghref;
       
    
    public int getid(){
        return id;
    }
    public String getname(){
        return name;
    }
    public String getcode(){
        return code;
    }
    
    public int gethsncode(){
        return hsncode;
    }
    
    public float getsp(){
        return sp;
    }
    public float getcp(){
        return cp;
    }
    public String getdesc(){
        return desc;
    }
    public int getwarestock(){
        return warestock;
    }
    public int getwarestockvalue(){
        return warestockvalue;
    }
    public int getcat(){
        return cat;
    }
    public int getscat(){
        return scat;
    }
    public int getisold(){
        return isold;
    }
    public int getlimit(){
        return limit;
    }
    public int getwarehouse(){
        return warehouse;
    }
    public int getsize(){
        return size;
    }
    public int getunit(){
        return unit;
    }
    public java.sql.Timestamp getdate(){
        return date;
    }
    public String gettype(){
        return type;
    }
    public float getcgst(){
        return cgst;
    }
    public float getcgstamount(){
        return cgstamount;
    }
    public float getsgst(){
        return sgst;
    }
    public float getsgstamount(){
        return sgstamount;
    }
    public float getigst(){
        return igst;
    }
    public float getigstamount(){
        return igstamount;
    }
    public float getcess(){
        return cess;
    }
    public float getcessamount(){
        return cessamount;
    }
    public String getgstin(){
        return gstin;
    }
    public int getdiscount(){
        return discount;
    }
    public String getdiscounttype(){
        return discounttype;
    }
    public String getimghref(){
        return imghref;
    }
    public Boolean gettaxinc(){
        return taxinc;
    }

    
    public void setgstin(String gstin){
        this.gstin = gstin;
    }
    public void setname(String name){
        this.name =  name;
    }
    public void setcode(String code){
        this.code =  code;
    }
    public void sethsncode(int hsncode){
        this.hsncode =  hsncode;
    }
    public void setsp(float sp){
        this.sp =  sp;
    }
    public void setcp(float cp){
        this.cp =  cp;
    }
    public void setdesc(String desc){
        this.desc =  desc;
    }
    public void setwarestock(int warestock){
        this.warestock =  warestock;
    }
    public void setwarestockvalue(int warestockvalue){
        this.warestockvalue =  warestockvalue;
    }
    public void setscat(int scat){
        this.scat =  scat;
    }
    public void setcat(int cat){
        this.cat =  cat;
    }
    public void setisold(int isold){
        this.isold =  isold;
    }
    public void setlimit(int limit){
        this.limit =  limit;
    }
    public void setunit(int unit){
        this.unit =  unit;
    }
    public void setsize(int size){
        this.size =  size;
    }  
    public void setwarehouse(int warehouse){
        this.warehouse =  warehouse;
    }
    public void setdate(java.sql.Timestamp date){
        this.date =  date;
    }
    public void settype(String type){
        this.type =  type;
    }  
    public void setcgst(float cgst){
        this.cgst =  cgst;
    }  
    public void setsgst(float sgst){
        this.sgst =  sgst;
    }  
    public void setigst(float igst){
        this.igst = igst;
    }  
    public void setcess(float cess){
        this.cess = cess;
    }  
    public void setcessamount(float cessamount){
        this.cessamount = cessamount;
    }  
    public void setcgstamount(float cgstamount){
        this.cgstamount =  cgstamount;
    }  
    public void setsgstamount(float sgstamount){
        this.sgstamount =  sgstamount;
    }  
    public void setigstamount(float igstamount){
        this.igstamount = igstamount;
    }  
    public void setdiscount(int discount){
        this.discount = discount;
    }  
    public void setdiscounttype(String discounttype){
        this.discounttype = discounttype;
    }  
    public void setimghref(String imghref){
        this.imghref = imghref;
    }  
    public void settaxinc(Boolean taxinc){
        this.taxinc = taxinc;
    }  
    
}
