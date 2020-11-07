/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.net.PasswordAuthentication;
import pojo.Items_pojo;
import pojo.Login_pojo;
import pojo.Contact_pojo;
import pojo.Purchases_pojo;
import pojo.Sales_pojo;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pojo.Accounts_pojo;
import pojo.Activity_pojo;
import pojo.Category_pojo;
import pojo.Collections_pojo;
import pojo.Size_pojo;
import pojo.Subcategory_pojo;
//import pojo.Tax_pojo;
import pojo.Warehouse_pojo;
import pojo.Transaction_pojo;
import pojo.Unit_pojo;

import java.util.*;  
import javax.mail.*;
import javax.activation.*;  
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author winayak
 */
public class App{
    /*login validation*/
    public String[] login_validate(String email, String password){
     
            Login_pojo obj = new Login_pojo();

            Session session = Hibernateutil.getSession();
            Transaction tx = session.beginTransaction();

        try{
            Query qry = session.createQuery("from Login_pojo e where e.email= :email and e.password= :pwd")
                    .setParameter("email", email).setParameter("pwd", password);
            
            List l =qry.list();
            System.out.println("sdadasdadasdassdassd");
            String[] res = {"","","","","","","","",""};
            if(l.size() == 1){
                obj = (Login_pojo)l.get(0);
                res[0] = obj.getemail();
                res[1] = obj.getlastlogin().toString();
                res[2] = obj.getgstin();
                res[3] = Integer.toString(obj.getwarehouse());
                res[4] = obj.gettype();
                res[5] = obj.getprivc();
                res[6] = obj.getprivd();
                res[7] = obj.getprivv();
                res[8] = Integer.toString(obj.getid());
            }
            else{
                res[0] = "Invalid email/password";
            }
            tx.commit();
            return res;

        }
        catch(Exception e){
            tx.rollback();
            String[] res = {"","","","","","","","",""};
            res[0] = e.toString();
            e.printStackTrace();
            return res;
        }
        finally{
            Hibernateutil.closeSession();
            
        }
    }
    /*insert login details*/
    public String insert_login(String name,String type,String pwd,String email,int contact,String domain,
            String gstin,String state,String city,String address,int warehouse,
            String priv_v,String priv_c,String priv_d){

        Login_pojo obj = new Login_pojo();

        Session session = Hibernateutil.getSession();

        Transaction tx = session.beginTransaction();            

        try{          
            Query qry = session.createQuery("from Login_pojo e where e.email= :email").setParameter("email", email);
            List l  = qry.list();
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
            String res= "";
            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            if(l.size()==0){
                if(type.equals("sub")){
                    obj.setuname("");
                    obj.setname(name);
                    obj.setemail(email);
                    obj.setpassword(pwd);
                    obj.setcontact(contact);
                    obj.setdomain(domain);
                    obj.setgstin(gstin);
                    obj.settype("sub");
                    obj.setaddress(address);
                    obj.setcity(city);
                    obj.setstate(state);
                    obj.setwarehouse(warehouse);
                    
                    obj.setpriv_c(priv_c);
                    obj.setpriv_v(priv_v);
                    obj.setpriv_d(priv_d);
                    obj.setlastlogin(ts);
                    obj.setlastaccview(ts);
                    obj.setlastconview(ts);
                    obj.setlastdashview(ts);
                    obj.setlastitemview(ts);
                    obj.setlastpayview(ts);
                    obj.setlastrepview(ts);
                    obj.setlastsaleview(ts);
                    session.save(obj);
                }              
                res =  "success";
            }
            else{
                res =  "email already exists";
            }
            tx.commit();
            return res;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*fetch user details from login*/
    public List fetch_login(String email,String gstin,String type){
             
        Session session = Hibernateutil.getSession();
        
        Transaction tx = session.beginTransaction();
        try{
            List l = null;
            if(type.equals("admin")){
                Query qry = session.createQuery("from Login_pojo e where e.email= :email")
                        .setParameter("email", email);
                l =qry.list();  
                if(l.size()==0){
                    l.add("0");
                }
                return l;
            }
            else if(type.equals("sub")){
                Query qry = session.createQuery("from Login_pojo e where e.type= :type and gstin=:gstin")
                        .setParameter("type", type).setParameter("gstin", gstin);
                l =qry.list();  
                if(l.size()==0){
                    l.add("0");
                }
            }
            else{
                l = new ArrayList();
                l.add("0");
            }
            tx.commit();
            return l;
        }
        catch(Exception e){
            tx.rollback();
            String error = e.toString();
            List l = new ArrayList();
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
            Hibernateutil.closeSession();

        }
    }
    /*update login details*/
    public String update_login(int id,String email,String name,String password,long contact,String domain,
        String gstin,String type,String address,String city,String state,int warehouse,
        String privc,String privv,String privd,String salesin){
     
            Session session = Hibernateutil.getSession();

            Transaction tx = session.beginTransaction();
        try{   
            Query qry = session.createQuery("from Login_pojo e where e.email= :email").setParameter("email", email);
            List l  = qry.list();        
            String msg = "";
            int flag = 0;
            if(l.size() == 1){
                 Login_pojo obj = (Login_pojo)l.get(0);
                 if(obj.getid()==id){
                    flag = 1;
                 }
            }
            if(l.size()==0 || flag==1){
                if(type.equals("sub")){
                    qry = session.createQuery("update Login_pojo set email= :email,password= :password,name= :name,gstin= :gstin,"
                            + "contact= :contact,address= :address,state= :state,city= :city,"
                            + "privilegescreate= :privc,privilegesview= :privv,privilegesdelete = :privd where id= :id");
//                    qry.setParameter("username","");
                    qry.setParameter("name",name);
                    qry.setParameter("id",id);
                    qry.setParameter("email",email);
                    qry.setParameter("password",password);
                    qry.setParameter("gstin",gstin);
                    qry.setParameter("contact",contact);
                    qry.setParameter("address",address);
                    qry.setParameter("city",city);
                    qry.setParameter("state",state);
                    qry.setParameter("privc",privc);
                    qry.setParameter("privv",privv);
                    qry.setParameter("privd",privd);
                
                    qry.executeUpdate();
                }
                else if(type.equals("multi")||type.equals("admin")){
                    qry = session.createQuery("update Login_pojo set contact= :contact,"
                            + "address= :address,state= :state,city= :city,domain= :domain,name= :name where id= :id");
                    qry.setParameter("name",name);
//                    qry.setParameter("email",email);
//                    qry.setParameter("password",password);
                    qry.setParameter("contact",contact);
                    qry.setParameter("domain",domain);
                    qry.setParameter("address",address);
                    qry.setParameter("city",city);
                    qry.setParameter("state",state);
                    qry.setParameter("id",id);
                    qry.executeUpdate();
                }
                msg = "success";
            }
            else{
                msg = "Email already exists";
            }
            tx.commit();
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*update last login detail*/
    public String update_invoice_serial(int id,String sis2,String pis2,String ess2,String prs2
            ,String bos2,String exs2,String ars2,String pos2,String dcs2,String cns2,String dns2,
            String rvs2,String pvs2,String aps2,String oos2){
        try{
            Session session = Hibernateutil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = null;
            query = session.createQuery("update Login_pojo set proformainvoice= :prs2,estimates= :ess2,salesinvoice= :sis2"
                    + "exportinvoice= :exs2,purchaseinvoice= :pis2,purchasevoucher= :pvs2,creditnote= :cns,debitnote= :dns2,"
                    + "advancereceipt= :ars2,refundvoucher= :rvs2,billsofsupply= :bos2,"
                    + "deliverychallan= :dcs2,purchaseorder= :pos2,advancepayment= :aps2 where id= :id")
                    .setParameter("id", id).setParameter("prs2", prs2).setParameter("sis2", sis2).setParameter("ess2", ess2)
                    .setParameter("exs2", exs2).setParameter("pis2", pis2).setParameter("pvs2", pvs2)
                    .setParameter("cns2", cns2).setParameter("dns2", dns2).setParameter("ars2", ars2)
                    .setParameter("rvs2", rvs2).setParameter("bos2", bos2)
                    .setParameter("dcs2", dcs2).setParameter("pos2", pos2).setParameter("aps2", aps2);
            int res = query.executeUpdate();
            return "Success"+res;
        }
        catch(Exception e){
            return e.toString();
        }
    }
    public int setllogin(int id,String type){
            Session session = Hibernateutil.getSession();

            Transaction tx = session.beginTransaction();
        try{
            Query qry = null;
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            if(type.equals("account")){
                qry = session.createQuery("update Login_pojo set lastaccountsviewed= :time where id= :id");
                qry.setParameter("time",ts);
            }
            if(type.equals("contact")){
                qry = session.createQuery("update Login_pojo set lastcontactsviewed= :time where id= :id");
                qry.setParameter("time",ts);
            }
            if(type.equals("item")){
                qry = session.createQuery("update Login_pojo set lastcontactsviewed= :time where id= :id");
                qry.setParameter("time",ts);
            }
            if(type.equals("dashboard")){
                qry = session.createQuery("update Login_pojo set lastdashboardviewed= :time where id= :id");
                qry.setParameter("time",ts);
            }
            if(type.equals("rep")){
                qry = session.createQuery("update Login_pojo set lastreportsviewed= :time where id= :id");
                qry.setParameter("lastrepview",ts);
            }
            if(type.equals("sale")){
                qry = session.createQuery("update Login_pojo set lastsalesviewed= :time where id= :id");
                qry.setParameter("lastsaleview",ts);
            }
            if(type.equals("pay")){
                qry = session.createQuery("update Login_pojo set lastpaymentsviewed= :time where id= :id");
                qry.setParameter("lastpayview",ts);
            }
            if(type.equals("lastlogin")){
                qry = session.createQuery("update Login_pojo set lastlogin= :time where id= :id");           
                qry.setParameter("time",ts);
                qry.setParameter("id",id);
            }
            int res = qry.executeUpdate();
            tx.commit();
            return res;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return 999;
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*insert saved details*/
     public String insert_saved(String gstin,String statecode,String type,String warehouse,String collections,
             int pincode,String address,String state,String city,String unit,String size,
             String cat,String scat,String items){     

            Session session = Hibernateutil.getSession();

            Transaction tx = session.beginTransaction();
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            String msg = "";
        try{  
            if(type.equals("category")){
                Query qry = session.createQuery("from Category_pojo where category= :category and gstin=:gstin")
                        .setParameter("category", cat).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    Category_pojo obj = new Category_pojo();
                    obj.setcatname(cat);
                    obj.setgstin(gstin);
                    obj.setdate(ts);                
                    session.save(obj);
                    msg = "success";
                }
                else{
                    msg = "category alrady exists";
                }
            }
            else if(type.equals("subcategory")){
                Query qry = session.createQuery("from Subcategory_pojo where subcategory= :scat and gstin=:gstin")
                        .setParameter("scat", scat).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    Subcategory_pojo obj = new Subcategory_pojo();
                    obj.setscat(scat); 
                    obj.setgstin(gstin);
                    obj.setdate(ts);
                    session.save(obj);  
                    msg = "success";
                }
                else{
                    msg = "subcategory alrady exists";
                }
            }
            else if(type.equals("size")){
                Query qry = session.createQuery("from Size_pojo where size= :size and gstin=:gstin")
                        .setParameter("size", size).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    Size_pojo obj = new Size_pojo();
                    obj.setsize(size);
                    obj.setgstin(gstin);
                    obj.setdate(ts);
                    session.save(obj);   
                    msg = "success";
                }
                else{
                    msg = "subcategory alrady exists";
                }
            }
//            else if(type.equals("tax")){
//                Tax_pojo obj = new Tax_pojo();
//                obj.settaxname(taxname);
//                obj.settaxrate(rate);
//                obj.setgstin(gstin);
//                session.save(obj);             
//            }
            else if(type.equals("unit")){
                Query qry = session.createQuery("from Unit_pojo where unit= :unit and gstin=:gstin")
                        .setParameter("unit", unit).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    Unit_pojo obj = new Unit_pojo();
                    obj.setunit(unit);
                    obj.setgstin(gstin);
                    obj.setdate(ts);
                    session.save(obj);
                    msg = "success";
                }
                else{
                    msg = "unit alrady exists";
                }
            }
            else if(type.equals("collections")){
                Query qry = session.createQuery("from Collections_pojo where collections= :collections and gstin=:gstin")
                        .setParameter("collections", collections).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    Collections_pojo obj = new Collections_pojo();
                    obj.setcollections(collections);
                    obj.setitems(items);
                    obj.setgstin(gstin);
                    obj.setdate(ts);
                    session.save(obj);
                    msg = "success";
                }
                else{
                    msg = "collections alrady exists";
                }
            }
            else if(type.equals("warehouse")){
                Query qry = session.createQuery("from Warehouse_pojo where warehousename= :warehouse and gstin=:gstin")
                        .setParameter("warehouse", warehouse).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    Warehouse_pojo obj = new Warehouse_pojo();
                    obj.setwarename(warehouse);
                    obj.setpincode(pincode);
                    obj.setaddress(address);
                    obj.setstate(state);
                    obj.setstatecode(statecode);
                    obj.setcity(city);
                    obj.setgstin(gstin);
                    obj.setdate(ts);
                    session.save(obj);
                    msg = "success";
                }
                else{
                    msg = "warehouse alrady exists";
                }
            }        
            tx.commit();
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    } 
    /*update saved details*/
    public String update_saved(String gstin,String statecode,String type,int id,String warehouse,String collections,int pincode,
            String address,String state,String city,String unit,String size,String cat,
            String scat,String items){
        
            Session session = Hibernateutil.getSession();

            Transaction tx = session.beginTransaction();
            String msg = "";
            Query qry = null;
        try{
            if(type.equals("category")){
                qry = session.createQuery("from Category_pojo where category= :category and gstin=:gstin")
                        .setParameter("category", cat).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    qry = session.createQuery("update Category_pojo set category= :cat where id= :id")
                            .setParameter("cat",cat).setParameter("id",id);
                    msg = "success";
                }
                else if(l.size()==1){
                    Category_pojo obj = (Category_pojo)l.get(0);
                    if(obj.getid()==id){
                        qry = session.createQuery("update Category_pojo set category= :cat where id= :id");
                        qry.setParameter("cat",cat).setParameter("id",id);
                        msg = "success";
                    }
                    else{
                        msg = "category already exists!!";
                    }
                }
            }
            else if(type.equals("subcategory")){
                qry = session.createQuery("from Subcategory_pojo where subcategory= :scat and gstin= :gstin")
                    .setParameter("scat", scat).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    qry = session.createQuery("update Subcategory_pojo set subcategory= :scat where id= :id")
                    .setParameter("scat",scat).setParameter("id",id);
                    msg = "success";
                }
                else if(l.size()==1){
                    Subcategory_pojo obj = (Subcategory_pojo)l.get(0);
                    if(obj.getid()==id){
                        qry = session.createQuery("update Subcategory_pojo set subcategory= :scat where id= :id")
                        .setParameter("scat",scat).setParameter("id",id);
                        msg = "success";
                    }
                    else{
                        msg = "subcategory already exists!!";
                    }
                }
            }
            else if(type.equals("size")){
                qry = session.createQuery("from Size_pojo where size= :size and gstin=:gstin")
                        .setParameter("category", cat).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    qry = session.createQuery("update Size_pojo set size= :size where id= :id")
                    .setParameter("size",size).setParameter("id",id);
                    msg = "success";
                }
                else if(l.size()==1){
                    Size_pojo obj = (Size_pojo)l.get(0);
                    if(obj.getid()==id){
                        qry = session.createQuery("update Size_pojo set size= :size where id= :id")
                        .setParameter("size",size).setParameter("id",id);
                        msg = "success";
                    }
                    else{
                        msg = "size already exists!!";
                    }
                }
            }
//            else if(type.equals("tax")){
//                Tax_pojo obj = new Tax_pojo();
//                qry = session.createQuery("update Tax_pojo set taxname= :taxname,rate= :rate where id= :id");
//                qry.setParameter("taxname",taxname);
//                qry.setParameter("rate",rate);
//            }
            else if(type.equals("unit")){
                qry = session.createQuery("from Unit_pojo where unit= :unit and gstin=:gstin")
                        .setParameter("category", cat).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    qry = session.createQuery("update Unit_pojo set unit = :unit where id= :id")
                    .setParameter("unit",unit).setParameter("id",id);;
                    msg = "success";
                }
                else if(l.size()==1){
                    Unit_pojo obj = (Unit_pojo)l.get(0);
                    if(obj.getid()==id){
                        qry = session.createQuery("update Unit_pojo set unit = :unit where id= :id")
                        .setParameter("unit",unit).setParameter("id",id);
                        msg = "success";
                    }
                    else{
                        msg = "Unit already exists!!";
                    }
                }
            }
            else if(type.equals("collections")){
                qry = session.createQuery("from Collections_pojo where collections= :categcollectionsory and gstin=:gstin")
                        .setParameter("category", cat).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    qry = session.createQuery("update Collections_pojo set collections= :collections,items= :items where id= :id")
                    .setParameter("collections",collections)
                    .setParameter("items",items).setParameter("id",id);
                    msg = "success";                    
                }
                else if(l.size()==1){
                    Collections_pojo obj = (Collections_pojo)l.get(0);
                    if(obj.getid()==id){
                        qry = session.createQuery("update Collections_pojo set collections= :collections,items= :items where id= :id")
                        .setParameter("collections",collections)
                        .setParameter("items",items).setParameter("id",id);
                        msg = "success";
                    }
                    else{
                        msg = "collection already exists!!";
                    }
                }
            }
            else if(type.equals("warehouse")){
                qry = session.createQuery("from Warehouse_pojo where warehousename= :warehousename and gstin=:gstin")
                        .setParameter("warehousename", warehouse).setParameter("gstin", gstin);
                List l = qry.list();  
                if(l.size()==0){
                    qry = session.createQuery("update Warehouse_pojo set statecode= :statecode,warehousename= :warehouse,"
                            + "pincode= :pincode,address= :address,state= :state,city = :city where id= :id")
                    .setParameter("warehouse",warehouse)
                    .setParameter("pincode",pincode)
                    .setParameter("address",address)
                    .setParameter("state",state)
                    .setParameter("city",city)
                    .setParameter("statecode",statecode)
                    .setParameter("id",id);
                    msg = "success";
                }
                else if(l.size()==1){
                    Warehouse_pojo obj = (Warehouse_pojo)l.get(0);
                    if(obj.getid()==id){
                        qry = session.createQuery("update Warehouse_pojo set statecode= :statecode,warehousename= :warehouse,"
                            + "pincode= :pincode,address= :address,state= :state,city = :city where id= :id")
                        .setParameter("warehouse",warehouse)
                        .setParameter("pincode",pincode)
                        .setParameter("address",address)
                        .setParameter("state",state)
                        .setParameter("city",city)
                        .setParameter("statecode",statecode)
                        .setParameter("id",id);
                        msg = "success";
                    }
                    else{
                        msg = "warehouse already exists!!";
                    }
                }
            }                         
            int res = qry.executeUpdate();
            tx.commit();
            return msg;
        }
        catch(Exception e){
            e.printStackTrace();
            tx.rollback();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    } 
    /*list saved details*/
    public List fetch_saved(String type,String gstin,int id){

        Session session = Hibernateutil.getSession();
        Transaction tx = session.beginTransaction();
        
        Query qry = null;
        try{
            if(type.equals("category")){
                Category_pojo obj = new Category_pojo();
                if(id==0)
                    qry = session.createQuery("from Category_pojo where gstin = :gstin").setParameter("gstin", gstin);
                else
                    qry = session.createQuery("from Category_pojo where id = :id").setParameter("id", id);
            }
            else if(type.equals("subcategory")){
                Subcategory_pojo obj = new Subcategory_pojo();
                if(id==0)
                    qry = session.createQuery("from Subcategory_pojo where gstin = :gstin").setParameter("gstin", gstin);
                else
                    qry = session.createQuery("from Subcategory_pojo where id = :id").setParameter("id", id);
            }
            else if(type.equals("size")){
                Size_pojo obj = new Size_pojo();
                if(id==0)
                    qry = session.createQuery("from Size_pojo where gstin = :gstin").setParameter("gstin", gstin);
                else
                    qry = session.createQuery("from Size_pojo where id = :id").setParameter("id", id);
            }
//            else if(type.equals("tax")){
//                Tax_pojo obj = new Tax_pojo();
//                if(id==0)
//                    qry = session.createQuery("from Tax_pojo where gstin = :gstin").setParameter("gstin", gstin);
//                else
//                    qry = session.createQuery("from Tax_pojo where id = :id").setParameter("id", id);
//            }
            else if(type.equals("unit")){
                Unit_pojo obj = new Unit_pojo();
                if(id==0)
                    qry = session.createQuery("from Unit_pojo where gstin = :gstin").setParameter("gstin", gstin);
                else
                    qry = session.createQuery("from Unit_pojo where id = :id").setParameter("id", id);
            }
            else if(type.equals("collections")){
                Collections_pojo obj = new Collections_pojo();
                if(id==0)
                    qry = session.createQuery("from Collections_pojo where gstin = :gstin").setParameter("gstin", gstin);
                else
                    qry = session.createQuery("from Collections_pojo where id = :id").setParameter("id", id);
            }
            else if(type.equals("warehouse")){
                Warehouse_pojo obj = new Warehouse_pojo();
                if(id==0)
                    qry = session.createQuery("from Warehouse_pojo where gstin = :gstin").setParameter("gstin", gstin);
                else
                    qry = session.createQuery("from Warehouse_pojo where id = :id").setParameter("id", id);
            }
            
            List l =qry.list(); 
            if(l.size()==0){
                l.add("0");
            }
            tx.commit();
            return l;
        }
        catch(Exception e){
            tx.rollback();
            String error = e.toString();
            List l = new ArrayList();
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
            Hibernateutil.closeSession();
        }
    }         
    
    /*add items to inventory*/
    public String insert_items(String gstin,int limit,String name, float cp,float sp,String code,int hsncode,
            int warestock,int warehouse,String desc,int cat,int scat,int size,int unit,
            String type,float cgst,float sgst,float igst,float cess,float cgstamount,float sgstamount,float igstamount,
            float cessamount,int discount,String discounttype){
     
        Items_pojo obj = new Items_pojo();
        
        Session session = Hibernateutil.getSession();
        
        Transaction tx = session.beginTransaction();
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String msg = "";
        try{
            Query qry = session.createQuery("from Items_pojo where name= :name and gstin= :gstin and warehouse= :warehouse")
                    .setParameter("name", name).setParameter("gstin", gstin).setParameter("warehouse", warehouse);
            List l = qry.list();
            if(l.size()==0){
                obj.setname(name);
                obj.setsp(sp);
                obj.setcp(cp);
                obj.setcode(code);
                obj.sethsncode(hsncode);
                obj.setwarestock(warestock);
                obj.setwarehouse(warehouse);
                obj.setsize(size);
                obj.setunit(unit);
                obj.setdate(ts);
//                obj.settax(tax);
//                obj.settaxrate(taxrate);
                obj.settype(type);
                obj.setdesc(desc);
                obj.setcat(cat);
                obj.setscat(scat);
                obj.setisold(0);
                obj.setlimit(limit);
                obj.settype(type);
                obj.setcgst(cgst);
                obj.setsgst(sgst);
                obj.setigst(igst);
                obj.setcess(cess);
                int value = (int)(warestock*cp);
                obj.setwarestockvalue(value);
                obj.setcgstamount(cgstamount);
                obj.setsgstamount(sgstamount);
                obj.setigstamount(igstamount);
                obj.setcessamount(cessamount);
                obj.setgstin(gstin);
                obj.setdiscount(discount);
                obj.setdiscounttype(discounttype);

                session.save(obj);
                msg =  "success";
            }
            else{
                msg = "Try another name.";
            }
            tx.commit();
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*update inventory items*/
    public String update_items(String gstin,int id,String name, float sp,float cp,String code,int hsncode,int warestock,
            int warehouse,String desc,int cat,int scat,int isold,int limit,int size,
            int unit,String type,float cgst,float sgst,float igst,
            float cess,float cgstamount,float sgstamount,float igstamount,float cessamount,
            int discount,String discounttype){
             
        Session session = Hibernateutil.getSession();
        
        Transaction tx = session.beginTransaction();
        String msg = "";
        try{            
            Query qry = session.createQuery("from Items_pojo where name= :name and gstin= :gstin and warehouse = :warehouse")
                    .setParameter("name", name)
                    .setParameter("gstin", gstin)
                    .setParameter("warehouse", warehouse);
            int flag = 0;
            List l = qry.list();
            if(l.size() == 1){
                 Items_pojo obj = (Items_pojo)l.get(0);
                 if(obj.getid()==id){
                    flag = 1;
                 }
            }
            if(l.size()==0 || flag==1){
                qry = session.createQuery("update Items_pojo set alertlimit= :limit,name= :name ,"
                        + "sellingprice= :sp,costprice= :cp,code= :code,hsncode= :hsncode,description= :desc,"
                        + "subcategory = :scat,category= :cat,subcategory= :scat,warehouse= :warehouse,warestock= :warestock,size= :size,"
                        + "unit= :unit,type= :type,cgst= :cgst,sgst= :sgst,igst= :igst,cess= :cess,cgstamount= :cgstamount,"
                        + "sgstamount= :sgstamount,igstamount= :igstamount,cessamount= :cessamount,"
                        + "itemssold= :isold ,warestockvalue= :warestockvalue, discount= :discount, discounttype= :discounttype where id= :id");
                qry.setParameter("name",name);
                qry.setParameter("sp",sp);
                qry.setParameter("cp",cp);
                qry.setParameter("code",code);
                qry.setParameter("hsncode",hsncode);
                qry.setParameter("warestock",warestock);
                int value = (int)(warestock*cp);
                qry.setParameter("warestockvalue",value);
                qry.setParameter("warehouse",warehouse);
                qry.setParameter("size",size);
                qry.setParameter("unit",unit);
//                qry.setParameter("tax",tax);
                qry.setParameter("type",type);
                qry.setParameter("desc",desc);
                qry.setParameter("cat",cat);
//                qry.setParameter("taxrate",taxrate);
                qry.setParameter("scat",scat);
                qry.setParameter("isold",isold);
                qry.setParameter("limit",limit);
                qry.setParameter("id",id);
                qry.setParameter("cgst",cgst);
                qry.setParameter("sgst",sgst);
                qry.setParameter("igst",igst);
                qry.setParameter("cess",cess);
                qry.setParameter("cgstamount",cgstamount);
                qry.setParameter("sgstamount",sgstamount);
                qry.setParameter("igstamount",igstamount);
                qry.setParameter("cessamount",cessamount);
                qry.setParameter("discount",discount);
                qry.setParameter("discounttype",discounttype);
                int res = qry.executeUpdate();
                msg =  "success";
            }           
            else{
                msg = "Try another name.";
            }
            tx.commit();
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*update stock*/
    public String update_stock(int id,int warestock,int warehouse){
             
        Session session = Hibernateutil.getSession();
        
        Transaction tx = session.beginTransaction();
        String msg = "";
        try{            
            Query qry = session.createQuery("from Items_pojo where id= :id")
                    .setParameter("id", id);
            List l = qry.list();
            Items_pojo obj = (Items_pojo)l.get(0);
            int stock = obj.getwarestock();
            stock = stock - warestock;
            qry = session.createQuery("update Items_pojo set warestock= :warestock where id= :id and warehouse= :warehouse");
            qry.setParameter("warestock",stock);
            qry.setParameter("warehouse",warehouse);       
            qry.setParameter("id",id);
            int res = qry.executeUpdate();
            msg =  "success";
            tx.commit();
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*fetch items from inventory*/
    public List fetch_items(String gstin,int id){
     
        Items_pojo obj = new Items_pojo();
        
        Session session = Hibernateutil.getSession();
        
        Transaction tx = session.beginTransaction();
        Query qry = null;
        try{
            if(id==0)
                qry = session.createQuery("from Items_pojo where gstin = :gstin").setParameter("gstin", gstin);
            else
                qry = session.createQuery("from Items_pojo where gstin = :gstin and id= :id").setParameter("gstin", gstin).setParameter("id",id);
            List l =qry.list();  
            if(l.size()==0){
                l.add("0");
            }
            tx.commit();
            return l;
        }
        catch(Exception e){
            tx.rollback();
            String error = e.toString();
            List l = new ArrayList();
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
            Hibernateutil.closeSession();

        }
    }
    
//     public String[] check_stock(String products){
//     
//        Items_pojo obj = new Items_pojo();
//
//        Session session = Hibernateutil.getSession();
//        Transaction tx = session.beginTransaction();
//        try{
//            String[] pro = products.split("\\,");
//            String[] co = {"",""};
//            int j = 0;
//            int qt= 0 ;
//            String[] res = {"",""};
//            res[0] = "0";
//            while(j!=pro.length){
//                co = pro[j].split("\\(");
//                String co2 = co[1].replaceAll("[()]", "");
//                
//                Query qry = session.createQuery("from Items_pojo where code='"+co[0]+"'");  
//                
//                List l =qry.list();       
//                Iterator i= l.iterator();
//                Items_pojo obj2 = (Items_pojo)i.next();               
//                qt = obj2.getwarestock()- Integer.parseInt(co2);
//                if(qt<0){ 
//                    res[0] = "1";
//                    if(res[1].equals("")){
//                        res[1]=obj2.getname();
//                    }
//                    else{
//                        res[1]=res[1]+","+obj2.getname();
//                    }
//                }
//                j++;
//            }    
//            j=0;
//            if(res[0].equals("0")){
//                 while(j!=pro.length){
//                    co = pro[j].split("\\(");
//                    String co2 = co[1].replaceAll("[()]", "");
//                    Query qry2 = session.createQuery("update Items_pojo set stock= stock - :stock,items_sold= isold + :isold where code= :code");
//                    qry2.setParameter("code",co[0]);
//                    qry2.setParameter("stock",Integer.parseInt(co2));
//                    qry2.setParameter("isold",Integer.parseInt(co2));
//                    qry2.executeUpdate();
//                    j++;
//                 }
//            }
//            return res;
//        }
//        
//        catch(Exception e){
//            String res[] = {"",""};
//            res[0] = "1";
//            res[1] = e.toString();
//            return res;
//        }
//        finally{
//            tx.commit();
//            Hibernateutil.closeSession();
//        }
//    }
    
    public String insert_contact(String gstin,String name,String email,String comp,int phone,String address,
            String scode,String state,String city,String accno,String ifsc,String pno,String type,int salary,int pincode,String cgstin){
     
        Contact_pojo obj = new Contact_pojo();
        
        Session session = Hibernateutil.getSession(); 
        
        Transaction tx = session.beginTransaction();
        String msg = null;
        try{ 
            Query qry = session.createQuery("from Contact_pojo where email= :email and gstin= :gstin")
                    .setParameter("email", email).setParameter("gstin", gstin);
            
            List l = qry.list();
            if(l.size()==0){
                obj.setname(name);
                obj.setemail(email);
                obj.setcomp(comp);
                obj.setpanno(pno);
                obj.setphone(phone);
                obj.setaddress(address);
                obj.setstatecode(scode);
                obj.setstate(state);
                obj.setcity(city);
                obj.setacc_no(accno);
                obj.setbifsc(ifsc);
                obj.settype(type);
                obj.setsalary(salary);
                obj.setgstin(gstin);
                obj.setcgstin(cgstin);
                obj.setpincode(pincode);
                obj.setinfo(false);
                session.save(obj);
                msg = "success";
            }
            else{
                msg =  "Contact with this email already exists in your contact book.";
            }
            tx.commit();
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    
     public String update_contact(int id,String gstin,String name,String email,String comp,int phone,String address,
             String scode,String state,String city,String accno,String ifsc,String pno,String type,int salary,int pincode,String cgstin){
             
        Session session = Hibernateutil.getSession(); 
        
        Transaction tx = session.beginTransaction();
        try{ 
            Query qry = session.createQuery("from Contact_pojo where email= :email and gstin= :gstin")
                    .setParameter("email", email).setParameter("gstin", gstin);
            int flag = 0;
            List l = qry.list();
            String msg = null;
            if(l.size() == 1){
                 Contact_pojo obj = (Contact_pojo)l.get(0);
                 if(obj.getid()==id){
                    flag = 1;
                 }
            }
            if(l.size()==0 || flag==1){
                Query qry2 = session.createQuery("update Contact_pojo set name= :name,companyName= :comp,"
                        + "acc_no= :accno,address= :address,phoneNo= :phone,city = :city,state= :state,"
                        + "state_code= :scode,branch_ifsc= :ifsc,pan_no= :pno,info = :info,"
                        + "pin_code= :pincode,cgstin= :cgstin,type= :type,email= :email,salary= :salary"
                        + " where id= :id");
                qry2.setParameter("comp",comp);
                qry2.setParameter("phone",phone);
                qry2.setParameter("name",name);
                qry2.setParameter("address",address);
                qry2.setParameter("scode",scode);
                qry2.setParameter("state",state);
                qry2.setParameter("pincode",pincode);
                qry2.setParameter("type",type);
                qry2.setParameter("city",city);
                qry2.setParameter("accno",accno);
                qry2.setParameter("ifsc",ifsc);
                qry2.setParameter("pno",pno);
                qry2.setParameter("cgstin",cgstin);
                qry2.setParameter("email",email);
                qry2.setParameter("salary",salary);
                qry2.setParameter("id",id);
                qry2.setParameter("info",true);

                int res = qry2.executeUpdate();
                msg = "success";
            } 
            else{
                msg = "contact already exists!!";
            }
            tx.commit();
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    
    public List fetch_contact(String gstin){
             
        Session session = Hibernateutil.getSession(); 
        Transaction tx = session.beginTransaction();

  
        try{
            
            Query qry = session.createQuery("from Contact_pojo where gstin = :gstin").setParameter("gstin", gstin);;
            List l =qry.list();  
            if(l.size()==0){
                l.add("0");
            }
            tx.commit();
            return l;
        }
        catch(Exception e){
            tx.rollback();
            String error = e.toString();
            List l = new ArrayList();           
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*insert sales*/
    public String insert_sales(String gstin,String invoiceno,Date invoicedate,String cgstin,String transaddress,
            Date deldate,Date delduedate,Date paydate,
            Date payduedate,Date orderdate,float amount,String paystatus,
            String delstatus,int cid,String name,
            String transstate,String transcity,String transby,int transcharge,String type,
            String invoicesetting,String shipportcode,String shipbillno,String linkedinvoice,
            String pos ,String transstatecode,int transpincode,boolean bill,String billadd,String billstate,String billcity,
            int billpin,String billstatecode,String productsid,String productsqty,String productsname,String productscost,
            Date shipbilldate,String returnmonth,String returnquarter,String taxrate,String cess,String cessamount,String cgst,
            String cgstamount,String sgst,String sgstamount,String igst,String igstamount,int warehouseid,String productsprice,
            String productsamount,String exporttype,String challantype,String reference,String discountype,String discount,Boolean roundoff){
     
        Sales_pojo obj = new Sales_pojo();
        
        Session session = Hibernateutil.getSession();       
        
        Transaction tx = session.beginTransaction();
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String res = "";
        try{
            Query qry = session.createQuery("from Sales_pojo where invoiceno= :invoiceno and type= :type and gstin= :gstin")
                    .setParameter("invoiceno", invoiceno).setParameter("gstin", gstin).setParameter("type", type);            
            List l = qry.list();
            if(l.size()<1){
            //
                obj.setinvoiceno(invoiceno);
                obj.setinvoicesetting(invoicesetting);
                obj.setshipportcode(shipportcode);
                obj.setshipbillno(shipbillno);
                obj.setlinkedinvoice(linkedinvoice);
                obj.setcancelled(false);
                //customer
                obj.setcid(cid);
                obj.setcname(name);
                obj.setcustomergstin(cgstin);
                obj.setpos(pos);
                obj.settransstatecode(transstatecode);
                obj.settranspincode(transpincode);
                obj.settranscity(transcity);
                obj.settransstate(transstate);
                obj.settransaddress(transaddress);
                obj.setbill(bill);
                obj.setbilladd(billadd);
                obj.setbillstate(billstate);
                obj.setbillcity(billcity);
                obj.setbillpincode(billpin);
                obj.setbillstatecode(billstatecode);
                //status
                obj.setpaystatus(paystatus);
                obj.setdelstatus(delstatus);
                //products
                obj.setproductsid(productsid);
                obj.setproductsqty(productsqty);
                obj.setproductsname(productsname);
                obj.setproductscost(productscost);
                obj.setproductsprice(productsprice);
                obj.setproductsamount(productsamount);
                //date
                obj.setdate(ts);
                obj.setorderdate(orderdate); 
                obj.setinvoicedate(invoicedate); 
                obj.setpaidon(paydate);    
                obj.setpayduedate(payduedate);
                obj.setdeliverydate(deldate);
                obj.setdelduedate(delduedate);
                obj.setshipbilldate(shipbilldate);
                obj.setreturnmonth(returnmonth);
                obj.setreturnquarter(returnquarter);
                
                //amount
                obj.setamount(amount);
                //tax
                obj.setcess(cess);
                obj.settaxrate(taxrate);
                obj.setcgst(cgst);
                obj.setsgst(sgst);
                obj.setigst(igst);
                obj.setcessamount(cessamount);
                obj.setsgstamount(sgstamount);
                obj.setcgstamount(cgstamount);
                obj.setigstamount(igstamount);
                //accounts
//                obj.setacc(account);
                //
                obj.settype(type);
//                obj.setwarehouse(warehouse);
                obj.setgstin(gstin);
                obj.settransby(transby);
                obj.settranscharge(transcharge);
                obj.setwarehouseid(warehouseid);
                obj.setexporttype(exporttype);
                obj.setchallantype(challantype);
                
                session.save(obj);
                tx.commit();
                Hibernateutil.closeSession();
                if(type.equals("ar")||type.equals("si")||type.equals("bos")){
                    insert_transaction(obj.getid(), "", "", "", amount, amount, "sale",cid,warehouseid,gstin);   
                    int q = 0;
                    String[] carray = productsid.split(","); 
                    String[] qtarray = productsqty.split(","); 
                    for(q=0;q<carray.length;q++){
                        update_stock(Integer.parseInt(carray[q]), Integer.parseInt(qtarray[q]), warehouseid);
                    }
                }
                res =  "success";
            }
            else{
                res =  "Invoice with this serial no already exists.";
            }
            return res;
        }
        catch(Exception e){
            tx.rollback();
            Hibernateutil.closeSession();
            e.printStackTrace();
            return e.toString();
        }
    }
    /*update sales*/
    public String update_sales(String gstin,int id,String invoiceno,String transaddress,Date invoicedate,
            Date deldate,Date delduedate,Date paydate,
            Date payduedate,Date orderdate,float amount,String paystatus,
            String delstatus,int cid,String name,
            String transstate,String transcity,String transby,int transcharge,
            String type,String invoicesetting,String shipportcode,String shipbillno,String linkedinvoice,
            String pos ,String cgstin,String transstatecode,int transpincode,boolean bill,String billadd,String billstate,String billcity,
            int billpin,String billstatecode,String productsid,String productsqty,String productsname,String productscost,
            Date shipbilldate,String returnmonth,String returnquarter,String taxrate,String cess,String cessamount,String cgst,
            String cgstamount,String sgst,String sgstamount,String igst,String igstamount,int warehouseid,String productsprice,
            String productsamount,String exporttype,String challantype,boolean cancelled,String reference,
            String discounttype,String discount,boolean roundoff){
             
        Session session = Hibernateutil.getSession();       
        
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("from Sales_pojo where invoiceno= :invoiceno and gstin= :gstin and type= :type")
                    .setParameter("invoiceno", invoiceno).setParameter("gstin", gstin).setParameter("type", type);
            int flag = 0;
            List l = qry.list();
            String msg = null;
            String[] qtarray2 = {};
            String[] pidarray2 = {};
            if(l.size() == 1){
                 Sales_pojo obj = (Sales_pojo)l.get(0);
                 qtarray2 = obj.getproductsqty().split(",");
                 pidarray2 = obj.getproductsid().split(",");
                 if(obj.getid()==id){
                    flag = 1;
                 }
            }
            if(l.size()==0 || flag==1){
                Query qry2 = session.createQuery("update Sales_pojo set deliveryduedate= :delduedate,"
                        + "paymentduedate= :payduedate,invoicedate= :invoicedate,productsamount= :productsamount,"
                        + "orderdate= :orderdate,paidon= :paydate,deliverydate= :deldate,paymentstatus= :paystatus,"
                        + "deliverystatus= :delstatus,customerid= :cid,customername= :name,amount= :amount,productsid= :productsid,"
                        + "transportationstate= :transstate,"
                        + "transportationcity= :transcity,transportationaddress= :transaddress,"
                        + "transportedby= :transby,transportationcharge= :transcharge,invoicesetting= :invoicesetting,"
                        + "shipportcode= :shipportcode,shipbillno= :shipbillno,cancelled= :cancelled,customergstin= :cgstin,placeofsupply= :pos,"
                        + "transportationstatecode = :transstatecode,transportationcity= :transcity,transportationaddress= :transaddress,"
                        + "transportationpincode= :transpincode,billing= :bill,billingstate= :billstate,billingcity= :billcity,"
                        + "billingstatecode= :billstatecode,billingaddress= :billadd,billingpincode= :billpin,shippingbilldate= :shipbilldate,"
                        + "returnmonth= :returnmonth,returnquarter= :returnquarter,productsqty= :productsqty,productsprice= :productsprice, "
                        + "cess= :cess,cessamount= :cessamount,cgst= :cgst,productsname= :productsname,productscost= :productscost,"
                        + "cgstamount= :cgstamount,sgst= :sgst,sgstamount= :sgstamount,igst= :igst,igstamount= :igstamount,"
                        + "warehouseid= :warehouseid,exporttype= :exporttype,challantype= :challantype,"
                        + "invoiceno = :invoiceno,cancelled= :cancelled,reference= :reference,discount= :discount,"
                        + "discounttype= :discounttype,taxrate= :taxrate,roundoff= :roundoff,linkedinvoice= :linkedinvoice,"
                        + "type= :type where id= :id");
                qry2.setParameter("id",id);
                qry2.setParameter("name",name);
                qry2.setParameter("invoiceno",invoiceno);
                qry2.setParameter("invoicesetting",invoicesetting);
                qry2.setParameter("shipportcode",shipportcode);
                qry2.setParameter("shipbillno",shipbillno);
                qry2.setParameter("linkedinvoice",linkedinvoice);
                qry2.setParameter("cancelled",cancelled);
                qry2.setParameter("reference",reference);
                //customer
                qry2.setParameter("cid",cid);
                qry2.setParameter("transcity",transcity);
                qry2.setParameter("transaddress",transaddress);
                qry2.setParameter("transstate",transstate);                
                qry2.setParameter("cgstin",cgstin);
                qry2.setParameter("pos",pos);
                qry2.setParameter("transstatecode",transstatecode);
                qry2.setParameter("transpincode",transpincode);
                qry2.setParameter("bill",bill);
                qry2.setParameter("billadd",billadd);
                qry2.setParameter("billstate",billstate);
                qry2.setParameter("billcity",billcity);
                qry2.setParameter("billpin",billpin);
                qry2.setParameter("billstatecode",billstatecode);
                //date
                qry2.setParameter("deldate",deldate);
                qry2.setParameter("delduedate",delduedate);
                qry2.setParameter("paydate",paydate);
                qry2.setParameter("payduedate",payduedate);
                qry2.setParameter("orderdate",orderdate);
                qry2.setParameter("invoicedate",invoicedate);
                qry2.setParameter("shipbilldate",shipbilldate);
                qry2.setParameter("returnmonth",returnmonth);
                qry2.setParameter("returnquarter",returnquarter);
                //status
                qry2.setParameter("paystatus",paystatus);
                qry2.setParameter("delstatus",delstatus);
                //amount            
                qry2.setParameter("amount",amount);
                qry2.setParameter("discount",discount);
                qry2.setParameter("discounttype",discounttype);
                //products
                qry2.setParameter("productsid",productsid);               
                qry2.setParameter("productsqty",productsqty);
                qry2.setParameter("productsname",productsname);
                qry2.setParameter("productscost",productscost);
                qry2.setParameter("productsprice",productsprice);
                qry2.setParameter("productsamount",productsamount);
                //tax
                qry2.setParameter("cess",cess);
                qry2.setParameter("cgst",cgst);
                qry2.setParameter("sgst",sgst);
                qry2.setParameter("igst",igst);
                qry2.setParameter("cessamount",cessamount);
                qry2.setParameter("sgstamount",sgstamount);
                qry2.setParameter("cgstamount",cgstamount);
                qry2.setParameter("igstamount",igstamount);
                qry2.setParameter("taxrate",taxrate);
                //accounts
//                qry2.setParameter("account",accountid);
                //
                qry2.setParameter("transby",transby);
                qry2.setParameter("transcharge",transcharge);
                qry2.setParameter("type",type);
//                qry2.setParameter("warehouse",warehouse);
                qry2.setParameter("warehouseid",warehouseid);
                qry2.setParameter("exporttype",exporttype);
                qry2.setParameter("challantype",challantype);
                qry2.setParameter("roundoff",roundoff);
                int res = qry2.executeUpdate();
                tx.commit();
                if(res>0 &&(type.equals("ar")||type.equals("si")||type.equals("bos"))){
                    qry = session.createQuery("from Transaction_pojo where invoiceid= :invoiceid")
                    .setParameter("invoiceid", id);
                    List l2 = qry.list();
                    Transaction_pojo obj2 = (Transaction_pojo)l2.get(0);
                    float totalamount = obj2.gettotalamount();
                    float dueamount = obj2.getdueamount();
                    dueamount = dueamount + amount - totalamount;
                    Hibernateutil.closeSession();
                    update_transaction(id,obj2.getamount(),obj2.gettransdate(),obj2.getmode(),dueamount,amount,
                            cid,warehouseid,"sale");
                    if(dueamount==0){
                        update_paystatus(id, "paid");
                    }
                    else{
                        update_delstatus(id, "pending");
                    }
                    if(type.equals("ar")||type.equals("si")||type.equals("bos")){
                        int q = 0;
                        String[] carray = productsid.split(","); 
                        String[] qtarray = productsqty.split(","); 
                        for(q=0;q<carray.length;q++){
                            update_stock(Integer.parseInt(pidarray2[q]), -Integer.parseInt(qtarray2[q]), warehouseid);
                        }
                        for(q=0;q<carray.length;q++){
                            update_stock(Integer.parseInt(carray[q]), Integer.parseInt(qtarray[q]), warehouseid);
                        }
                    }
                }
                msg =  "success";
                
            }
            else{
                msg = "Invoice with this serial no already exists.";
            }
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            Hibernateutil.closeSession();
            e.printStackTrace();                
            return e.toString();
        }
    }
    public String update_paystatus(int id,String paystatus){
        Session session = Hibernateutil.getSession();
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("update Sales_pojo set paymentstatus= :paystatus where id= :id")
                    .setParameter("paystatus", paystatus).setParameter("id", id);
            int res = qry.executeUpdate();
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
     public String update_delstatus(int id,String delstatus){
        Session session = Hibernateutil.getSession();
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("update Sales_pojo set deliverystatus= :delstatus where id= :id")
                    .setParameter("delstatus", delstatus).setParameter("id", id);
            int res = qry.executeUpdate();
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
     public String update_paystatus2(int id,String paystatus){
        Session session = Hibernateutil.getSession();
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("update Purchases_pojo set paymentstatus= :paystatus where id= :id")
                    .setParameter("paystatus", paystatus).setParameter("id", id);
            int res = qry.executeUpdate();
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
     public String update_delstatus2(int id,String delstatus){
        Session session = Hibernateutil.getSession();
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("update Purchases_pojo set deliverystatus= :delstatus where id= :id")
                    .setParameter("delstatus", delstatus).setParameter("id", id);
            int res = qry.executeUpdate();
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*fetch from sales*/
    public List fetch_sales(String gstin,String type,int id){
             
        Session session = Hibernateutil.getSession();
        Transaction tx = session.beginTransaction();
        try{
            if(type.equals("all")){
                Query qry = session.createQuery("from Sales_pojo where gstin= :gstin")
                    .setParameter("gstin", gstin);

                List l =qry.list();
                if(l.size()==0){
                    l.add("0");
                }
                tx.commit();
                return l;
            }
            else{
                 Query qry = session.createQuery("from Sales_pojo where id= :id")
                    .setParameter("id", id);

                List l =qry.list();
                if(l.size()==0){
                    l.add("0");
                }
                tx.commit();
                return l;
            }
        }   
        catch(Exception e){
            String error = e.toString();
            List l = new ArrayList();
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*insert purchase*/
    public String insert_purchases(String gstin,String invoiceno,Date invoicedate,String cgstin,
            Date deldate,Date delduedate,Date paydate,
            Date payduedate,Date orderdate,float amount,String paystatus,
            String delstatus,int cid,String name,int transcharge,String type,
            String invoicesetting,String linkedinvoice,
            String pos,String productsid,String productsqty,String productsname,String productscost,
            String returnmonth,String returnquarter,String taxrate,String cess,String cessamount,String cgst,
            String cgstamount,String sgst,String sgstamount,String igst,String igstamount,int warehouseid,String productsprice,
            String productsamount,String reference,String discountype,String discount,Boolean roundoff){
     
        Purchases_pojo obj = new Purchases_pojo();
        
        Session session = Hibernateutil.getSession();       
        
        Transaction tx = session.beginTransaction();
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        Date date= new Date();
        long time = date.getTime();
        String msg = null;
        Timestamp ts = new Timestamp(time);
        try{
            Query qry = session.createQuery("from Purchases_pojo where invoiceno= :invoiceno and type= :type and gstin= :gstin")
                    .setParameter("invoiceno", invoiceno).setParameter("gstin", gstin).setParameter("type", type);
            
            List l = qry.list();
            if(l.size()<1){
            //
                obj.setinvoiceno(invoiceno);
                obj.setinvoicesetting(invoicesetting);
                obj.setlinkedinvoice(linkedinvoice);
                obj.setcancelled(false);
                //customer
                obj.setcid(cid);
                obj.setcname(name);
                obj.setcustomergstin(cgstin);
                obj.setpos(pos);
                //status
                obj.setpaystatus(paystatus);
                obj.setdelstatus(delstatus);
                //products
                obj.setproductsid(productsid);
                obj.setproductsqty(productsqty);
                obj.setproductsname(productsname);
                obj.setproductscost(productscost);
                obj.setproductsprice(productsprice);
                obj.setproductsamount(productsamount);
                //date
                obj.setdate(ts);
                obj.setorderdate(orderdate); 
                obj.setinvoicedate(invoicedate); 
                obj.setpaidon(paydate);    
                obj.setpayduedate(payduedate);
                obj.setdeliverydate(deldate);
                obj.setdelduedate(delduedate);
                obj.setreturnmonth(returnmonth);
                obj.setreturnquarter(returnquarter);                
                //amount
                obj.setamount(amount);
                //tax
                obj.setcess(cess);
                obj.settaxrate(taxrate);
                obj.setcgst(cgst);
                obj.setsgst(sgst);
                obj.setigst(igst);
                obj.setcessamount(cessamount);
                obj.setsgstamount(sgstamount);
                obj.setcgstamount(cgstamount);
                obj.setigstamount(igstamount);
                //accounts
//                obj.setacc(account);
                //
                obj.settype(type);
//                obj.setwarehouse(warehouse);
                obj.setgstin(gstin);
                obj.settranscharge(transcharge);
                obj.setwarehouseid(warehouseid);
                
                session.save(obj);
                tx.commit();
                Hibernateutil.closeSession();
                if(type.equals("ap")||type.equals("pi")||type.equals("bos")){
                    insert_transaction(obj.getid(), "", "", "", amount, amount, "purchase",cid,warehouseid,gstin);   
                    int q = 0;
                    String[] carray = productsid.split(","); 
                    String[] qtarray = productsqty.split(","); 
                    for(q=0;q<carray.length;q++){
                        update_stock(Integer.parseInt(carray[q]), -Integer.parseInt(qtarray[q]), warehouseid);
                    }
                }
                msg =  "success";
            }
            else{
                msg =  "Invoice with this serial no already exists.";
            }
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            Hibernateutil.closeSession();
            e.printStackTrace();
            return e.toString();
        }
    }
    /*update purchase*/
    public String update_purchases(String gstin,int id,String invoiceno,Date invoicedate,
            Date deldate,Date delduedate,Date paydate,
            Date payduedate,Date orderdate,float amount,String paystatus,
            String delstatus,int cid,String name,int transcharge,
            String type,String invoicesetting,String linkedinvoice,String pos ,String cgstin,
            String productsid,String productsqty,String productsname,String productscost,
            String returnmonth,String returnquarter,String taxrate,String cess,String cessamount,String cgst,
            String cgstamount,String sgst,String sgstamount,String igst,String igstamount,int warehouseid,String productsprice,
            String productsamount,boolean cancelled,String reference,
            String discounttype,String discount,boolean roundoff){
             
        Session session = Hibernateutil.getSession();       
        
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("from Purchases_pojo where invoiceno= :invoiceno and gstin= :gstin and type= :type")
                    .setParameter("invoiceno", invoiceno).setParameter("gstin", gstin).setParameter("type", type);
            int flag = 0;
            List l = qry.list();
            String msg = null;
            String[] qtarray2 = {};
            String[] pidarray2 = {};
            
            if(l.size() == 1){
                 Purchases_pojo obj = (Purchases_pojo)l.get(0);
                 qtarray2 = obj.getproductsqty().split(",");
                 pidarray2 = obj.getproductsid().split(",");
                 if(obj.getid()==id){
                    flag = 1;
                 }
            }
            if(l.size()==0 || flag==1){
                Query qry2 = session.createQuery("update Purchases_pojo set deliveryduedate= :delduedate,"
                        + "paymentduedate= :payduedate,invoicedate= :invoicedate,productsamount= :productsamount,"
                        + "orderdate= :orderdate,paidon= :paydate,deliverydate= :deldate,paymentstatus= :paystatus,"
                        + "deliverystatus= :delstatus,customerid= :cid,customername= :name,amount= :amount,productsid= :productsid,"
                        + "invoicesetting= :invoicesetting,cancelled= :cancelled,customergstin= :cgstin,placeofsupply= :pos,"
                        + "returnmonth= :returnmonth,returnquarter= :returnquarter,productsqty= :productsqty,productsprice= :productsprice, "
                        + "cess= :cess,cessamount= :cessamount,cgst= :cgst,productsname= :productsname,productscost= :productscost,"
                        + "cgstamount= :cgstamount,sgst= :sgst,sgstamount= :sgstamount,igst= :igst,igstamount= :igstamount,"
                        + "warehouseid= :warehouseid,invoiceno = :invoiceno,cancelled= :cancelled,reference= :reference,discount= :discount,"
                        + "discounttype= :discounttype,taxrate= :taxrate,roundoff= :roundoff,linkedinvoice= :linkedinvoice,"
                        + "type= :type,transportationcharge= :transcharge where id= :id");
                qry2.setParameter("id",id);
                qry2.setParameter("name",name);
                qry2.setParameter("invoiceno",invoiceno);
                qry2.setParameter("invoicesetting",invoicesetting);
                qry2.setParameter("linkedinvoice",linkedinvoice);
                qry2.setParameter("cancelled",cancelled);
                qry2.setParameter("reference",reference);
                //customer
                qry2.setParameter("cid",cid);
                qry2.setParameter("cgstin",cgstin);
                qry2.setParameter("pos",pos);
                //date
                qry2.setParameter("deldate",deldate);
                qry2.setParameter("delduedate",delduedate);
                qry2.setParameter("paydate",paydate);
                qry2.setParameter("payduedate",payduedate);
                qry2.setParameter("orderdate",orderdate);
                qry2.setParameter("invoicedate",invoicedate);
                qry2.setParameter("returnmonth",returnmonth);
                qry2.setParameter("returnquarter",returnquarter);
                //status
                qry2.setParameter("paystatus",paystatus);
                qry2.setParameter("delstatus",delstatus);
                //amount            
                qry2.setParameter("amount",amount);
                qry2.setParameter("discount",discount);
                qry2.setParameter("discounttype",discounttype);
                //products
                qry2.setParameter("productsid",productsid);               
                qry2.setParameter("productsqty",productsqty);
                qry2.setParameter("productsname",productsname);
                qry2.setParameter("productscost",productscost);
                qry2.setParameter("productsprice",productsprice);
                qry2.setParameter("productsamount",productsamount);
                //tax
                qry2.setParameter("cess",cess);
                qry2.setParameter("cgst",cgst);
                qry2.setParameter("sgst",sgst);
                qry2.setParameter("igst",igst);
                qry2.setParameter("cessamount",cessamount);
                qry2.setParameter("sgstamount",sgstamount);
                qry2.setParameter("cgstamount",cgstamount);
                qry2.setParameter("igstamount",igstamount);
                qry2.setParameter("taxrate",taxrate);
                //accounts
//                qry2.setParameter("account",accountid);
                //
                qry2.setParameter("transcharge",transcharge);
                qry2.setParameter("type",type);
//                qry2.setParameter("warehouse",warehouse);
                qry2.setParameter("warehouseid",warehouseid);
                qry2.setParameter("roundoff",roundoff);
                int res = qry2.executeUpdate();
                tx.commit();
                if(res>0 &&(type.equals("ap")||type.equals("pi")||type.equals("bos"))){
                    qry = session.createQuery("from Transaction_pojo where invoiceid= :invoiceid")
                    .setParameter("invoiceid", id);
                    List l2 = qry.list();
                    Transaction_pojo obj2 = (Transaction_pojo)l2.get(0);
                    float totalamount = obj2.gettotalamount();
                    float dueamount = obj2.getdueamount();
                    dueamount = dueamount + amount - totalamount;
                    Hibernateutil.closeSession();
                    update_transaction(id,obj2.getamount(),obj2.gettransdate(),obj2.getmode(),dueamount,amount,
                            cid,warehouseid,type);
                    if(dueamount==0){
                        update_paystatus2(id, "paid");
                    }
                    else{
                        update_paystatus2(id, "pending");
                    }
                    if(type.equals("ap")||type.equals("pi")||type.equals("bos")){
                        int q = 0;
                        String[] carray = productsid.split(","); 
                        String[] qtarray = productsqty.split(","); 
                        for(q=0;q<carray.length;q++){
                            update_stock(Integer.parseInt(pidarray2[q]), Integer.parseInt(qtarray2[q]), warehouseid);
                        }
                        for(q=0;q<carray.length;q++){
                            update_stock(Integer.parseInt(carray[q]), -Integer.parseInt(qtarray[q]), warehouseid);
                        }
                    }
                }
                msg = "success";
                
            }
            else{
                msg =  "Invoice with this serial no already exists.";
            }
            return msg;
        }
        catch(Exception e){
            tx.rollback();
            Hibernateutil.closeSession();
            e.printStackTrace();                
            return e.toString();
        }
    }
    /*fetch purchases*/
    public List fetch_purchases(String gstin,String type,int id){
             
        Session session = Hibernateutil.getSession();       
        Transaction tx = session.beginTransaction();
        try{
            if(type.equals("all")){
                Query qry = session.createQuery("from Purchases_pojo where gstin= :gstin")
                        .setParameter("gstin", gstin);

                List l =qry.list();   
                if(l.size()==0){
                    l.add("0");
                }
                tx.commit();
                return l;
            }
            else{
                Query qry = session.createQuery("from Purchases_pojo where gstin= :gstin")
                       .setParameter("gstin", gstin);

               List l =qry.list();   
               if(l.size()==0){
                   l.add("0");
               }
               tx.commit();
               return l;
            }
        }
        catch(Exception e){
            tx.rollback();
            String error = e.toString();
            List l = new ArrayList();
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*insert transaction*/
     public String insert_transaction(int invoiceid,String amount,String mode,String idate,float dueamount,
             float tamount,String type,int cid,int warehouseid,String gstin){
     
        Transaction_pojo obj = new Transaction_pojo();

        Session session = Hibernateutil.getSession();       

        Transaction tx = session.beginTransaction();

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        try{
            obj.setinvoiceid(invoiceid);
            obj.setamount(amount);
            obj.setdate(ts);
            obj.setmode(mode);
            obj.setgstin(gstin);
            obj.setdueamount(dueamount);
            obj.settotalamount(tamount);
            obj.setcustomerid(cid);
            obj.setwarehouseid(warehouseid);
            obj.settype(type);
            session.save(obj);
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*update transaction*/
    public String update_transaction(int invoiceid,String amount,String mode,String idate,float dueamount,float tamount,
            int cid,int warehouseid,String type){
             
        Session session = Hibernateutil.getSession();       
        
        Transaction tx = session.beginTransaction();
        try{
            Query qry2 = session.createQuery("update Transaction_pojo set amount= :amount,mode= :mode,"
                    + "transactiondate= :idate,dueamount= :dueamount,totalamount= :tamount,customerid= :cid,"
                    + "warehouseid= :warehouseid where invoiceid= :invoiceid and type= :type");
            qry2.setParameter("invoiceid",invoiceid);
            qry2.setParameter("cid",cid);
            qry2.setParameter("warehouseid",warehouseid);
            qry2.setParameter("amount",amount);
            qry2.setParameter("mode",mode);
            qry2.setParameter("idate",idate);
            qry2.setParameter("dueamount",dueamount);
            qry2.setParameter("tamount",tamount);
            qry2.setParameter("type",type);
            int res = qry2.executeUpdate();
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*fetch transactions*/
    public List fetch_transactions(int id, String gstin,String type){
             
        Session session = Hibernateutil.getSession();   
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("from Transaction_pojo where gstin= :gstin and invoiceid = :id and type= :type")
                    .setParameter("gstin", gstin).setParameter("id", id).setParameter("type", type);
            List l =qry.list();   
            tx.commit();
            return l;
        }
        catch(Exception e){
            tx.rollback();
            String error = e.toString();
            List l = new ArrayList();
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
//            tx.commit();
            Hibernateutil.closeSession();
        }
    }
    /*insert account*/
//     public String insert_account(String gstin,String accname,int balance,String acctype){
//     
//        Accounts_pojo obj = new Accounts_pojo();
//
//        Session session = Hibernateutil.getSession();       
//
//        Transaction tx = session.beginTransaction();
//
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
//        Date date= new Date();
//        long time = date.getTime();
//        Timestamp ts = new Timestamp(time);
//        try{
//            obj.setaccname(accname);
//            obj.setdate(ts);
//            obj.setbalance(balance);
//            obj.setacctype(acctype);
//
//            session.save(obj);
//        }
//        catch(Exception e){
//            return "error";
//        }
//        finally{
//            tx.commit();
//            Hibernateutil.closeSession();
//            return "success";
//        }
//    }
//    /*update account*/
//    public void update_account(String gstin,String accname,int balance,String acctype,int id){
//     
//        Purchases_pojo obj = new Purchases_pojo();
//        
//        Session session = Hibernateutil.getSession();       
//        
//        Transaction tx = session.beginTransaction();
//        try{
//            Query qry2 = session.createQuery("update Accounts_pojo set accountname= :accname,balance= :balance,"
//                    + "accounttype= :acctype where id= :id");
//            qry2.setParameter("accname",accname);
//            qry2.setParameter("balance",balance);
//            qry2.setParameter("id",id);
//            qry2.setParameter("acctype",acctype);
//            session.save(obj);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        finally{
//            tx.commit();
//            Hibernateutil.closeSession();
//        }
//    }
//    /*fetch account*/
//    public List fetch_account(){
//     
//        Purchases_pojo obj = new Purchases_pojo();
//        
//        Session session = Hibernateutil.getSession();       
//        Transaction tx = session.beginTransaction();
//        try{
//            Query qry = session.createQuery("from Accounts_pojo where gstin= :gstin");
//
//            List l =qry.list();   
//            if(l.size()==0){
//                l.add("0");
//            }
//            return l;
//        }
//        catch(Exception e){
//            String error = e.toString();
//            List l = new ArrayList();
//            l.add("0");
//            l.add(error);
//            return l;
//        }
//        finally{
////            tx.commit();
//            Hibernateutil.closeSession();
//        }
//    }
    /*insert activity*/
     public String insert_activity(String gstin,String type,int key,String description,String warehouse,String addedby){
     
        Activity_pojo obj = new Activity_pojo();

        Session session = Hibernateutil.getSession();       

        Transaction tx = session.beginTransaction();

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        try{
            obj.settype(type);
            obj.setkey(key);
            obj.setgstin(gstin);
            obj.setdate(ts);
            obj.setdesc(description);
            obj.setwarehouse(warehouse);
            obj.setaddedby(addedby);
            session.save(obj);
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    }
    /*fetch account*/
    public List fetch_activity(){
     
        Purchases_pojo obj = new Purchases_pojo();
        
        Session session = Hibernateutil.getSession();       
        Transaction tx = session.beginTransaction();
        try{
            Query qry = session.createQuery("from Activity_pojo where gstin = :gstin");

            List l =qry.list();   
            if(l.size()==0){
                l.add("0");
            }
            return l;
        }
        catch(Exception e){
            String error = e.toString();
            List l = new ArrayList();
            l.add("0");
            l.add(error);
            return l;
        }
        finally{
//            tx.commit();
            Hibernateutil.closeSession();
        }
    }
    
    /*delete from table*/
    public String delete_saved(int id,String type){

        Session session = Hibernateutil.getSession();
        Transaction tx = session.beginTransaction();
        Query qry = null;
        try{
            if(type.equals("category")){
                Category_pojo obj = new Category_pojo();
                qry = session.createQuery("delete Category_pojo where id= :id");
            }
            if(type.equals("subcategory")){
                Category_pojo obj = new Category_pojo();
                qry = session.createQuery("delete Subcategory_pojo where id= :id");
            }
            else if(type.equals("size")){
                Size_pojo obj = new Size_pojo();
                qry = session.createQuery("delete Size_pojo where id= :id");
            }
//            else if(type.equals("tax")){
//                Tax_pojo obj = new Tax_pojo();
//                qry = session.createQuery("delete Tax_pojo where id= :id");
//            }
            else if(type.equals("unit")){
                Unit_pojo obj = new Unit_pojo();
                qry = session.createQuery("delete Unit_pojo where id= :id");
            }
            else if(type.equals("collections")){
                Collections_pojo obj = new Collections_pojo();
                qry = session.createQuery("delete Collections_pojo where id= :id");
            }
            else if(type.equals("warehouse")){
                Warehouse_pojo obj = new Warehouse_pojo();
                qry = session.createQuery("delete Warehouse_pojo where id= :id");
            }
            
            else if(type.equals("contact")){
                Contact_pojo obj = new Contact_pojo();
                qry = session.createQuery("delete Contact_pojo where id= :id");
            }
            else if(type.equals("items")){
                Items_pojo obj = new Items_pojo();
                qry = session.createQuery("delete Items_pojo where id= :id");
            }
            else if(type.equals("purachases")){
                Purchases_pojo obj = new Purchases_pojo();
                qry = session.createQuery("delete Purchases_pojo where id= :id");
            }
            else if(type.equals("sales")){
                Sales_pojo obj = new Sales_pojo();
                qry = session.createQuery("delete Sales_pojo where id= :id");
            }
            else if(type.equals("transaction")){
                Transaction_pojo obj = new Transaction_pojo();
                qry = session.createQuery("delete Transaction_pojo where id= :id");
            }
            else if(type.equals("account")){
                Accounts_pojo obj = new Accounts_pojo();
                qry = session.createQuery("delete Accounts_pojo where id= :id");
            }
            
            qry.setParameter("id", id);
            qry.executeUpdate();
            tx.commit();
            return "success";
        }
        catch(Exception e){
            tx.rollback();
            return e.toString();
        }
        finally{
            Hibernateutil.closeSession();
        }
    } 
//public static void main(String args[]) {
//        String to = "dungeon8888@gmail.com";//change accordingly  
//      String from = "dungeon8888@gmail.com";//change accordingly  
//      String host = "localhost";//or IP address  
//  
//     //Get the session object  
//      Properties properties = System.getProperties();  
//      properties.setProperty("mail.smtp.host", host);  
//      javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);  
//  
//     //compose the message  
//      try{  
//         MimeMessage message = new MimeMessage(session);  
//         message.setFrom(new InternetAddress(from));  
//         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
//         message.setSubject("Ping");  
//         message.setText("Hello, this is example of sending email  ");  
//  
//         // Send message  
//         Transport.send(message);  
//         System.out.println("message sent successfully....");  
//  
//      }catch (MessagingException mex) {mex.printStackTrace();}  
//   }  
}
