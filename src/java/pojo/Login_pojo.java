/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name="Login")
public class Login_pojo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="email",unique=true)
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Column(name="contact")
    private long contact;
    
    @Column(name="name")
    private String name;
    
    @Column(name="username")
    private String uname;  
      
    @Column(name="domain")
    private String domain;
    
    @Column(name="gstin")
    private String gstin;
    
    @Column(name="lastlogin")
    private java.sql.Timestamp date;  
    
    @Column(name="type")
    private String type;   
    
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
    
    @Column(name="warehouseid")
    private int warehouseid;
    
    //priviledges
    @Column(name="privilegescreate")
    private String privcr;
    
    @Column(name="privilegesdelete")
    private String privdel;
    
    @Column(name="privilegesview")
    private String privvi;
    //last viewed
    @Column(name="lastdashboardviewed")
    private java.sql.Timestamp lastdashview;
    
    @Column(name="lastsalesviewed")
    private java.sql.Timestamp lastsaleview;
    
    @Column(name="lastpaymentsviewed")
    private java.sql.Timestamp lastpayview;
    
    @Column(name="lastaccountsviewed")
    private java.sql.Timestamp lastaccview;
    
    @Column(name="lastcontactsviewed")
    private java.sql.Timestamp lastconview;
    
    @Column(name="lastitemsviewed")
    private java.sql.Timestamp lastitemview;
    
    @Column(name="lastreportsviewed")
    private java.sql.Timestamp lastrepview;
    //serial
    @Column(name="proformainvoice")
    private String prin;
    
    @Column(name="estimates")
    private String estimates;
    
    @Column(name="salesinvoice")
    private String sin;
    
    @Column(name = "exportinvoice")
    private String ein;
    
    @Column(name="purchaseinvoice")
    private String puin;
    
    @Column(name="paymentvoucher")
    private String pvoucher;
    
    @Column(name = "creditnote")
    private String cnote;
    
    @Column(name = "debitnote")
    private String dnote;
    
    @Column(name = "advancereceipt")
    private String arec;    
    
    @Column(name = "refundvoucher")
    private String rvoucher;
    
    @Column(name = "billsofsupply")
    private String bofsupply;
    
    @Column(name = "deliverychallan")
    private String deliverychallan;
    
    @Column(name = "purchaseorder")
    private String purchaseorder;
    
    @Column(name = "advancepayment")
    private String advancepay;
    
    @Column(name = "onlineorders")
    private String onorders;
    
    public int getid(){
        return id;
    }
    public String getemail(){
        return email;
    }
    public String getpassword(){
        return password;
    }
    public long getcontact(){
        return contact;
    }
    public String getgstin(){
        return gstin;
    }
    public String getname(){
        return name;
    }
    public String getuname(){
        return uname;
    }
    public String getdomain(){
        return domain;
    }
    public String  getprivc(){
        return privcr;
    }
    public String  getprivv(){
        return privvi;
    }
    public String  getprivd(){
        return privdel;
    }
    public String getcity(){
        return city;
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
    public String gettype(){
        return type;
    }
    public int getwarehouse(){
        return warehouseid;
    }
    public String getpriv_v(){
        return privvi;
    }
    public String getpriv_c(){
        return privcr;
    }
    public String getpriv_d(){
        return privdel;
    }
    public java.sql.Timestamp  getlastlogin(){
        return date;
    }
    public java.sql.Timestamp  getlastdashview(){
        return lastdashview;
    }
    public java.sql.Timestamp  getlastsaleview(){
        return lastsaleview;
    }
    public java.sql.Timestamp  getlastitemview(){
        return lastitemview;
    }
    public java.sql.Timestamp  getlastpayview(){
        return lastpayview;
    }
    public java.sql.Timestamp  getlastaccview(){
        return lastaccview;
    }
    public java.sql.Timestamp  getlastconview(){
        return lastconview;
    }
    public java.sql.Timestamp  getlastrepview(){
        return lastrepview;
    }
    public String getsalesin(){
        return sin;
    }
    public String getproformain(){
        return prin;
    }
    public String getestimatein(){
        return estimates;
    }
    public String getexportin(){
        return ein;
    }
    public String getpurchasein(){
        return puin;
    }
    public String getpurchasevoucher(){
        return pvoucher;
    }
    public String getpurchaseorder(){
        return purchaseorder;
    }
    public String getrefundvoucher(){
        return rvoucher;
    }
    public String getbosin(){
        return bofsupply;
    }
    public String getadvancerec(){
        return arec;
    }
    public String getcreditnote(){
        return cnote;
    }
    public String getdebitnote(){
        return dnote;
    }
    public String getdeliverychallan(){
        return deliverychallan;
    }
    public String getadvancepay(){
        return advancepay;
    }
    public String getonorder(){
        return onorders;
    }
    
    
    public void setemail(String email){
        this.email = email;
    }
    public void setpassword(String password){
        this.password = password; 
    }
    public void setcontact(long contact){
        this.contact = contact;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setuname(String uname){
        this.uname = uname;
    }

    public void setdomain(String domain){
        this.domain = domain;
    }
    public void setlastlogin(java.sql.Timestamp date){
        this.date = date;
    }
    public void setgstin(String gstin){
        this.gstin = gstin;
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
    public void setwarehouse(int warehouseid){
        this.warehouseid = warehouseid;
    }
    public void setaddress(String address){
        this.address = address;
    }
    public void setpriv_c(String privcr){
        this.privcr = privcr;
    }
    public void setpriv_d(String privdel){
        this.privdel = privdel;
    }
    public void setpriv_v(String privvi){
        this.privvi = privvi;
    }
    public void settype(String type){
        this.type = type;
    }
    public void setlastdashview(java.sql.Timestamp lastdashview){
        this.lastdashview = lastdashview;
    }
    public void setlastsaleview(java.sql.Timestamp lastsaleview){
        this.lastsaleview = lastsaleview;
    }
    public void setlastpayview(java.sql.Timestamp lastpayview){
        this.lastpayview = lastpayview;
    }
    public void setlastaccview(java.sql.Timestamp lastaccview){
        this.lastaccview = lastaccview;
    }
    public void setlastitemview(java.sql.Timestamp lastitemview){
        this.lastitemview = lastitemview;
    }
    public void setlastrepview(java.sql.Timestamp lastrepview){
        this.lastrepview = lastrepview;
    }
    public void setlastconview(java.sql.Timestamp lastconview){
        this.lastconview = lastconview;
    }
    
    public void setsalesin(String sin){
        this.sin = sin;
    }
    public void setproformain(String prin){
        this.prin = prin;
    }
    public void setestimatein(String estimates){
        this.estimates = estimates;
    }
    public void setexportin(String ein){
        this.ein = ein;
    }
    public void setpurchasein(String puin){
        this.puin = puin;
    }
    public void setpurchasevoucher(String pvoucher){
        this.pvoucher = pvoucher;
    }
    public void setpurchaseorder(String purchaseeorder){
        this.purchaseorder = purchaseorder;
    }
    public void setrefundvoucher(String rvoucher){
        this.rvoucher = rvoucher;
    }
    public void setbosin(String bofsupply){
        this.bofsupply = bofsupply;
    }
    public void setadvancerec(String arec){
        this.arec = arec;
    }
    public void setadvancepay(String advancepay){
        this.advancepay = advancepay;
    }
    public void setcreditnote(String cnote){
        this.cnote = cnote;
    }
    public void setdebitnote(String dnote){
        this.dnote = dnote;
    }
    public void setdeliverychallan(String deliverychallan){
        this.deliverychallan = deliverychallan;
    }
    public void setonorder(String onorders){
        this.onorders = onorders;
    }
    
}
