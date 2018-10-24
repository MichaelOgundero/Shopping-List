package com.sexybeast.michael.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MainActivity extends AppCompatActivity {
    public static List<Item>  shoppingList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static ItemsAdapter iAdapter;
    private FloatingActionButton plus;
    private static ConstraintLayout constraintLayout;
    public static Realm realm;
    public static RealmConfiguration config;
    public static Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager iLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(iLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //sets the divider/separator
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));

        //for the snackbar
        constraintLayout = (ConstraintLayout) findViewById(R.id.activity_main);

        //floating action button
        plus = (FloatingActionButton) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity();
            }
        });

        //delete all button
        Button btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setDeleteAllSnackBar();
               deleteAll();
            }
        });




        //setting up Realm
        Realm.init(this);
        config = new RealmConfiguration.Builder()
                .name("shoppingListRealm.realm")
                .build();
       realm = Realm.getInstance(config);

                //retrieve
        RealmHelper helper = new RealmHelper(realm);
        shoppingList = helper.retrieve();

        iAdapter = new ItemsAdapter(shoppingList, this);
        recyclerView.setAdapter(iAdapter);
        context = this;




    }



    @Override
    protected void onPause() {
        super.onPause();

        Realm.getInstance(config).close();
    }

    public void switchActivity(){
        Intent intent = new Intent(this, newList.class);
        startActivity(intent);
    }

    public static void deleteItem(int position){
        Realm.getInstance(config).beginTransaction();
        shoppingList.get(position).deleteFromRealm();
        Realm.getInstance(config).commitTransaction();
        shoppingList.remove(position);
        iAdapter.notifyItemRemoved(position);
    }

    public static void deleteAll(){
        Realm.getInstance(config).beginTransaction();
        for(int i=0;i<shoppingList.size();i++){
            shoppingList.get(i).deleteFromRealm();
        }
        Realm.getInstance(config).commitTransaction();
        shoppingList.clear();
        iAdapter.notifyDataSetChanged();
    }



    public static void setSnackbar(int position){
        Snackbar.make(constraintLayout, shoppingList.get(position).getDescription(), Snackbar.LENGTH_LONG).show();
    }

    public static void setDeleteSnackBar(int position){
        Snackbar.make(constraintLayout, shoppingList.get(position).getName()+"  deleted!", Snackbar.LENGTH_LONG).show();
    }

    public static void setDeleteAllSnackBar(){
        Snackbar.make(constraintLayout, "All Items Deleted!", Snackbar.LENGTH_LONG).show();
    }

    public static Item getItem(int position){
        return shoppingList.get(position);
    }


}






//    private void initializeShoppingList() {
//
//        Item item = new Item("Toy", "Lego Ninjago", "$200", "A Ninjago Lego set");
//        shoppingList.add(item);
//
//        item = new Item("Games", "Spiderman", "$60", "The spiderman game for the ps4");
//        shoppingList.add(item);
//
//        item = new Item("Shoes", "Vans", "$100", "A medium sized Vans");
//        shoppingList.add(item);
//
//        item = new Item("Shirt", "Flannel (Medium)", "$20", "A medium sized flannel shirt");
//        shoppingList.add(item);
//
//        item = new Item("Trousers", "NN07", "$100", "a pair of trousers");
//        shoppingList.add(item);
//
//        item = new Item("Underwear", "Tommy Hilfiger", "$20", "A couple of undees");
//        shoppingList.add(item);
//
//        item = new Item("Fruits", "Apple", "$1", "get a bunch of apples");
//        shoppingList.add(item);
//
//        item = new Item("Dairy Product", "Milk", "$5", "2 cartons of milk");
//        shoppingList.add(item);
//
//        item = new Item("Paper Goods", "Toiler Paper", "$5", "get a bunch of toilet paper");
//        shoppingList.add(item);
//
//        item = new Item("Movies", "Man of Steel", "$45", "Get the greatest superhero movie on blu-ray");
//        shoppingList.add(item);
//
//        item = new Item("Cleaners", "Laundary Detergent", "$10", "get some detergent");
//        shoppingList.add(item);
//
//        item = new Item("Canned", "Tuna Fish", "$5", "canned tuna fish");
//        shoppingList.add(item);
//
//        item = new Item("Beverage", "Monster", "$4", "Get your power fuel");
//        shoppingList.add(item);
//
//        item = new Item("Meat", "Ham", "$10","get ham for a sandwich");
//        shoppingList.add(item);
//
//        item = new Item("Frozen Food", "Ice-Cream", "$10", "Its summer you need some");
//        shoppingList.add(item);
//
//        item = new Item("Electronics", "Playstation 4", "$300", "Need to play spiderman");
//        shoppingList.add(item);
//
//
//        item = new Item("Books", "Call Of The Wild", "$20", "A timeless classic");
//        shoppingList.add(item);
//
//
//        iAdapter.notifyDataSetChanged();
//    }
