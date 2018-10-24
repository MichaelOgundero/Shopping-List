package com.sexybeast.michael.shoppinglist;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Item extends RealmObject{

    @PrimaryKey
    @Required
    private String itemID;

    private String name;
    private String description;
    private int est_price;

    private String type;


    public Item(){}

    public Item(String type, String name, int est_price, String description){
        this.type = type;

        this.name = name;
        this.est_price = est_price;
        this.description = description;
    }




    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setEst_price(int est_price) {
        this.est_price = est_price;
    }

    public int getEst_price() {
        return est_price;
    }


    public String getItemID() {
        return itemID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
