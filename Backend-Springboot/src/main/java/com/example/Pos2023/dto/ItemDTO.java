package com.example.Pos2023.dto;

import javax.persistence.Column;
import java.util.Date;

public class ItemDTO {

    private int itemId;
    private String itemName;
    private String itemBrand;
    private String itemDescription;
    private double itemPrice;
    private Date itemExdate;

    public ItemDTO() {
    }

    public ItemDTO(int itemId, String itemName, String itemBrand, String itemDescription, double itemPrice, Date itemExdate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemExdate = itemExdate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Date getItemExdate() {
        return itemExdate;
    }

    public void setItemExdate(Date itemExdate) {
        this.itemExdate = itemExdate;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemBrand='" + itemBrand + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemExdate=" + itemExdate +
                '}';
    }
}
