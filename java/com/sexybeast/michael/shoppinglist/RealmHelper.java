package com.sexybeast.michael.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }


    //Write
    public void save(final Item item){
        realm.executeTransaction(new Realm.Transaction() {
                                     @Override
                                     public void execute(Realm realm) {
                                         Item s = realm.copyToRealm(item);
                                     }
                                 }
        );
    }

    //Read
    public List<Item> retrieve(){
       List<Item> itemsObj = new ArrayList<>();
        RealmResults<Item> items = realm.where(Item.class).findAll();

        for(Item i:items)
        {
           itemsObj.add(i);
        }

        return itemsObj;
    }
}
