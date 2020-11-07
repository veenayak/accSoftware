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
@Table(name="test_Contacts")
public class Contact_pojo {
    @Column(name="name")
    private String name;
    
    @Column(name="email")
    private String email;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="password")
    private String password;
    
    @Column(name="companyName")
    private String comp;
    
    @Column(name="phoneNo")
    private int phone;
    
    @Column(name="address")
    private String address;
    
    @Column(name="city")
    private String city;
    
    @Column(name="pan_no")
    private String pno;
    
    @Column(name="state")
    private String state;
    
    @Column(name="state_code")
    private String scode;
    
    @Column(name="pin_code")
    private int pcode;
    
    @Column(name="acc_no")
    private String accno;
    
    @Column(name="branch_ifsc")
    private String bifsc;
    
    @Column(name="last_login")
    private java.sql.Timestamp llogin;
    
    @Column(name="info")
    private Boolean info;
    
    @Column(name="date")
    private java.sql.Timestamp date;
    
    @Column(name="type")
    private String type;
    
    @Column(name="salary")
    private int salary;
    
    @Column(name="gstin")
    private String gstin;
    
    @Column(name="cgstin")
    private String cgstin;

        
    public String getname(){
        return name;
    }
    public int getid(){
        return id;
    }
    public String getemail(){
        return email;
    }
    public String getcomp(){
        return comp;
    }
    public String getpassw(){
        return password;
    }
    public int getphone(){
        return phone;
    }
    public String getaddress(){
        return address;
    }
    public String getcity(){
        return city;
    }
    public String getpanno(){
        return pno;
    }
    public String getstate(){
        return state;
    }
    public String getstatecode(){
        return scode;
    }
    public int getpincode(){
        return pcode;
    }
    public String getacc_no(){
        return accno;
    }
    public String getbifsc(){
        return bifsc;
    }
    public java.sql.Timestamp getllogin(){
        return llogin;
    }
    public boolean getinfo(){
        return info;
    }
    public java.sql.Timestamp getdate(){
        return date;
    }
    public String gettype(){
        return type;
    }
    public int getsalary(){
        return salary;
    }
    public String getgstin(){
        return gstin;
    }
    public String getcgstin(){
        return cgstin;
    }
    public String getpassword(){
        return password;
    }
    
    
    public void setgstin(String gstin){
        this.gstin = gstin;
    }
    public void setcgstin(String cgstin){
        this.cgstin = cgstin;
    }
    public void setname(String name){
        this.name =  name;
    }
    public void setpassw(String password){
        this.password =  password;
    }
    public void setemail(String email){
        this.email = email;
    }
    public void setcomp(String comp){
        this.comp = comp;
    }
    public void setphone(int phone){
        this.phone =  phone;
    }
    
    public void setaddress(String address){
        this.address =  address;
    }
    public void setcity(String city){
        this.city =  city;
    }
    public void setpanno(String pno){
        this.pno =  pno;
    }
    public void setstate(String state){
        this.state =  state;
    }
    public void setstatecode(String scode){
        this.scode = scode;
    }
    public void setpincode(int pcode){
        this.pcode = pcode;
    }
    public void setacc_no(String accno){
        this.accno = accno;
    }
    public void setbifsc(String bifsc){
        this.bifsc =  bifsc;
    }
    public void setllogin(java.sql.Timestamp llogin){
        this.llogin =  llogin;
    }
    public void setinfo(Boolean info){
        this.info =  info;
    }
    public void setdate(java.sql.Timestamp date){
        this.date =  date;
    }
    public void settype(String type){
        this.type =  type;
    }
    public void setsalary(int salary){
        this.salary =  salary;
    }
    public void setpassword(String password){
        this.password =  password;
    }
    
}
