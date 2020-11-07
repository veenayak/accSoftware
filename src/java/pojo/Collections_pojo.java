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
@Table(name="test_collections")
public class Collections_pojo {  
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="date")
    private java.sql.Timestamp date;
    
    @Column(name="collections")
    private String collections;  
    
    @Column(name="items")
    private String items;  
          
    @Column(name="gstin")
    private String gstin;
    
    
    public int getid(){
        return id;
    }
    public java.sql.Timestamp getdate(){
        return date;
    }
    public String getcollections(){
        return collections;
    }
    public String getitems(){
        return items;
    }
    
    public String getgstin(){
        return gstin;
    }

    
    public void setgstin(String gstin){
        this.gstin = gstin;
    }
    public void setdate(java.sql.Timestamp date){
        this.date =  date;
    }
    public void setcollections(String collections){
        this.collections =  collections;
    }
    public void setitems(String items){
        this.items =  items;
    }

    
}
