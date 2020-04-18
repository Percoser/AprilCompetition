package com.example.demo.Models;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.ResultSet;

@Entity
@Table(name = "exchange")
public class Item {
    @Id
    @Column (name = "id")
    public String id;
    @Column(name = "price")
    public double price;
    @Column(name = "timestamp")
    public String timestamp;

    public Item() {
    }

    public Item(String id,double price, String timestamp) {

        this.id = id;
        this.price = price;
        this.timestamp = timestamp;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {return price;}

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTimestamp(){return timestamp;}

    public void setTimestamp(String timestamp){this.timestamp=timestamp;}


}
