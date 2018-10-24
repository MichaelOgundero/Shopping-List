package com.sexybeast.michael.shoppinglist;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class newList extends AppCompatActivity {
    private static  EditText name;
    private static EditText est_price;
    private static EditText description;
    private static Spinner type;
    public static ImageView  editIcon;
    private static ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<String> categories = new ArrayList<String>();
        categories.add("Toy");
        categories.add("Games");
        categories.add("Shoes");
        categories.add("Shirt");
        categories.add("Trousers");
        categories.add("Underwear");
        categories.add("Fruits");
        categories.add("Dairy Product");
        categories.add("Paper Goods");
        categories.add("Movies");
        categories.add("Cleaners");
        categories.add("Canned");
        categories.add("Beverage");
        categories.add("Meat");
        categories.add("Frozen Food");
        categories.add("Electronics");
        categories.add("Books");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);

        constraintLayout = (ConstraintLayout) findViewById(R.id.activity_new) ;

        name = (EditText) findViewById(R.id.addName);
        est_price = (EditText) findViewById(R.id.addPrice);
        type = (Spinner) findViewById(R.id.spinner);
        description = (EditText) findViewById(R.id.addDescription);
        editIcon = (ImageView) findViewById(R.id.editImage);

        Button addButton = (Button) findViewById(R.id.saveButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(est_price.getText().toString()) &&TextUtils.isEmpty(description.getText().toString())){
                    name.setError("Field can't be empty");
                    est_price.setError("Field can't be empty");
                    description.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(est_price.getText().toString())){
                    name.setError("Field can't be empty");
                    est_price.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(description.getText().toString())){
                    name.setError("Field can't be empty");
                    description.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(est_price.getText().toString()) &&TextUtils.isEmpty(description.getText().toString())){
                    description.setError("Field can't be empty");
                    est_price.setError("Field can't be empty");
                } else if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(description.getText().toString())){
                    description.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(est_price.getText().toString())){
                    est_price.setError("Field can't be empty");
                }else {
                    addItem();
                    finish();
                }
            }
        });
        //changes the icon when a new item in the spinner is selected
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editIcons();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }



    public static void addItem(){

       // Item item = new Item(type.getSelectedItem().toString(),name.getText().toString(), est_price.getText().toString(), description.getText().toString());
            Realm.getInstance(MainActivity.config).beginTransaction();
            Item item = Realm.getInstance(MainActivity.config).createObject(Item.class, UUID.randomUUID().toString());
            item.setType(type.getSelectedItem().toString());
            item.setDescription(description.getText().toString());
            item.setName(name.getText().toString());
            item.setEst_price(Integer.parseInt(est_price.getText().toString()));

            Realm.getInstance(MainActivity.config).commitTransaction();
            MainActivity.shoppingList.add(item);

            //save
            RealmHelper helper = new RealmHelper(MainActivity.realm);
            helper.save(item);


            //refresh
            MainActivity.shoppingList = helper.retrieve();
            MainActivity.iAdapter = new ItemsAdapter(MainActivity.shoppingList, MainActivity.context);
            MainActivity.recyclerView.setAdapter(MainActivity.iAdapter);
            MainActivity.iAdapter.notifyDataSetChanged();

    }

    public void editIcons(){
        if(type.getSelectedItem().toString().equals("Toy")) {
            editIcon.setImageResource(R.drawable.toy);
        }
        if(type.getSelectedItem().toString().equals("Games")) {
            editIcon.setImageResource(R.drawable.games);
        }
        if(type.getSelectedItem().toString().equals("Shoes")) {
            editIcon.setImageResource(R.drawable.shoes);
        }
        if(type.getSelectedItem().toString().equals("Shirt")) {
            editIcon.setImageResource(R.drawable.shirt);
        }
        if(type.getSelectedItem().toString().equals("Trousers")) {
            editIcon.setImageResource(R.drawable.trousers);
        }
        if(type.getSelectedItem().toString().equals("Underwear")) {
            editIcon.setImageResource(R.drawable.underwear);
        }
        if(type.getSelectedItem().toString().equals("Fruits")) {
            editIcon.setImageResource(R.drawable.fruits);
        }
        if(type.getSelectedItem().toString().equals("Dairy Product")) {
            editIcon.setImageResource(R.drawable.dairy);
        }
        if(type.getSelectedItem().toString().equals("Paper Goods")) {
            editIcon.setImageResource(R.drawable.paper);
        }
        if(type.getSelectedItem().toString().equals("Movies")) {
            editIcon.setImageResource(R.drawable.movies);
        }
        if(type.getSelectedItem().toString().equals("Cleaners")) {
            editIcon.setImageResource(R.drawable.soap);
        }
        if(type.getSelectedItem().toString().equals("Canned")) {
            editIcon.setImageResource(R.drawable.canned);
        }
        if(type.getSelectedItem().toString().equals("Beverage")) {
            editIcon.setImageResource(R.drawable.beverages);
        }
        if(type.getSelectedItem().toString().equals("Meat")) {
            editIcon.setImageResource(R.drawable.meat);
        }
        if(type.getSelectedItem().toString().equals("Frozen Food")) {
            editIcon.setImageResource(R.drawable.frozen);
        }
        if(type.getSelectedItem().toString().equals("Electronics")) {
            editIcon.setImageResource(R.drawable.electronics);
        }
        if(type.getSelectedItem().toString().equals("Books")) {
            editIcon.setImageResource(R.drawable.books);
        }


    }

    public static void setType(Spinner type) {
        newList.type = type;
    }

    public static void setEst_price(EditText est_price) {
        newList.est_price = est_price;
    }

    public static void setName(EditText name) {
        newList.name = name;
    }



    public static void setDescription(EditText description) {
        newList.description = description;
    }

    public static EditText getName() {
        return name;
    }

    public static Spinner getType() {
        return type;
    }

    public static EditText getEst_price() {
        return est_price;
    }

    public static EditText getDescription() {
        return description;
    }

}
