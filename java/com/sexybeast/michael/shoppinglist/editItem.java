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

import io.realm.Realm;

public class editItem extends AppCompatActivity {


    private static EditText editName;
    private static EditText editPrice;
    private static EditText editDescription;
    private static Spinner editType;
    public static ImageView editIcon2;
    public static int position;
    public static Item item;
    public static List<Item>  editShoppingList = new ArrayList<>();
    public static ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Spinner spinner = (Spinner) findViewById(R.id.editSpinner);

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

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(dataAdapter);

        editShoppingList = MainActivity.shoppingList;



        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);

        editType= (Spinner) findViewById(R.id.editSpinner);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editIcon2 = (ImageView) findViewById(R.id.editImage2);

        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editName.getText().toString()) && TextUtils.isEmpty(editPrice.getText().toString()) &&TextUtils.isEmpty(editDescription.getText().toString())){
                    editName.setError("Field can't be empty");
                    editPrice.setError("Field can't be empty");
                    editDescription.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(editName.getText().toString()) && TextUtils.isEmpty(editPrice.getText().toString())){
                    editName.setError("Field can't be empty");
                    editPrice.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(editName.getText().toString()) && TextUtils.isEmpty(editDescription.getText().toString())){
                    editName.setError("Field can't be empty");
                    editDescription.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(editPrice.getText().toString()) &&TextUtils.isEmpty(editDescription.getText().toString())){
                    editDescription.setError("Field can't be empty");
                    editPrice.setError("Field can't be empty");
                } else if(TextUtils.isEmpty(editName.getText().toString())){
                    editName.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(editDescription.getText().toString())){
                    editDescription.setError("Field can't be empty");
                }else if(TextUtils.isEmpty(editPrice.getText().toString())){
                    editPrice.setError("Field can't be empty");
                }else{
                    saveItem();
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

    assignFields();

      overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out);

    }

    public static void assignFields(){
        editName.setText(MainActivity.getItem(position).getName());
        editDescription.setText(MainActivity.getItem(position).getDescription());
        editPrice.setText(String.valueOf(MainActivity.getItem(position).getEst_price()));

        editType.setSelection(dataAdapter.getPosition(item.getType()));
    }

    public void saveItem(){
        Realm.getInstance(MainActivity.config).beginTransaction();

        MainActivity.getItem(position).setType(editType.getSelectedItem().toString());
        MainActivity.getItem(position).setName(editName.getText().toString());
        MainActivity.getItem(position).setEst_price(Integer.parseInt(editPrice.getText().toString()));
        MainActivity.getItem(position).setDescription(editDescription.getText().toString());
        Realm.getInstance(MainActivity.config).commitTransaction();



        MainActivity.iAdapter.notifyDataSetChanged();
    }

    public void editIcons(){
        if(editType.getSelectedItem().toString().equals("Toy")) {
            editIcon2.setImageResource(R.drawable.toy);
        }
        if(editType.getSelectedItem().toString().equals("Games")) {
            editIcon2.setImageResource(R.drawable.games);
        }
        if(editType.getSelectedItem().toString().equals("Shoes")) {
            editIcon2.setImageResource(R.drawable.shoes);
        }
        if(editType.getSelectedItem().toString().equals("Shirt")) {
            editIcon2.setImageResource(R.drawable.shirt);
        }
        if(editType.getSelectedItem().toString().equals("Trousers")) {
            editIcon2.setImageResource(R.drawable.trousers);
        }
        if(editType.getSelectedItem().toString().equals("Underwear")) {
            editIcon2.setImageResource(R.drawable.underwear);
        }
        if(editType.getSelectedItem().toString().equals("Fruits")) {
            editIcon2.setImageResource(R.drawable.fruits);
        }
        if(editType.getSelectedItem().toString().equals("Dairy Product")) {
            editIcon2.setImageResource(R.drawable.dairy);
        }
        if(editType.getSelectedItem().toString().equals("Paper Goods")) {
            editIcon2.setImageResource(R.drawable.paper);
        }
        if(editType.getSelectedItem().toString().equals("Movies")) {
            editIcon2.setImageResource(R.drawable.movies);
        }
        if(editType.getSelectedItem().toString().equals("Cleaners")) {
            editIcon2.setImageResource(R.drawable.soap);
        }
        if(editType.getSelectedItem().toString().equals("Canned")) {
            editIcon2.setImageResource(R.drawable.canned);
        }
        if(editType.getSelectedItem().toString().equals("Beverage")) {
            editIcon2.setImageResource(R.drawable.beverages);
        }
        if(editType.getSelectedItem().toString().equals("Meat")) {
            editIcon2.setImageResource(R.drawable.meat);
        }
        if(editType.getSelectedItem().toString().equals("Frozen Food")) {
            editIcon2.setImageResource(R.drawable.frozen);
        }
        if(editType.getSelectedItem().toString().equals("Electronics")) {
            editIcon2.setImageResource(R.drawable.electronics);
        }
        if(editType.getSelectedItem().toString().equals("Books")) {
            editIcon2.setImageResource(R.drawable.books);
        }


    }

}
