package com.example.Pos2023.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name",length = 50, nullable = false)
    private String itemName;

    @Column(name = "item_brand",length = 50, nullable = false)
    private String itemBrand;

    @Column(name = "item_description",length = 200, nullable = false)
    private String itemDescription;

    @Column(name = "item_price",length = 50, nullable = false)
    private double itemPrice;

    @Column(name = "item_expire_date",length = 50)
    private Date itemExdate;

    //constructors
    public Item() {
    }

    public Item(int itemId, String itemName, String itemBrand, String itemDescription, double itemPrice, Date itemExdate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemExdate = itemExdate;
    }

    //Getters

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public Date getItemExdate() {
        return itemExdate;
    }

    //setters

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemExdate(Date itemExdate) {
        this.itemExdate = itemExdate;
    }

    //toString

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemBrand='" + itemBrand + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemExdate=" + itemExdate +
                '}';
    }
}
