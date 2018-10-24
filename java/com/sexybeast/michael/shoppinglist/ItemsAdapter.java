package com.sexybeast.michael.shoppinglist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    private List<Item> shoppingList;
    private ConstraintLayout constraintLayout;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, type, est_price;
        public ImageView type_icon;
        public Button btnDescription, btnDelete, btnEdit;
        public CheckBox checkBox;

        public MyViewHolder(View view){
            super(view);
            //ImageView
            type_icon = (ImageView) view.findViewById(R.id.type_icon);

            //textView
            type = (TextView) view.findViewById(R.id.type);
            name = (TextView) view.findViewById(R.id.addName);
            est_price= (TextView) view.findViewById(R.id.est_price);

            //Buttons
            btnEdit = (Button) view.findViewById(R.id.btnEdit);
            btnDelete = (Button) view.findViewById(R.id.btnDelete);
            btnDescription = (Button) view.findViewById(R.id.btnDescrip);

            //checkbox
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }

    }



    public ItemsAdapter(List<Item> shoppingList, Context context){
        this.shoppingList = shoppingList;
        this.context = context;
    }

//    public void switchActivity(){
//        Intent intent = new Intent(context, newList.class);
//        context.startActivity(intent);
//    }

    public void switchEdit(){
        Intent intent = new Intent(context, editItem.class);
        context.startActivity(intent);
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopping_list_row, viewGroup, false);
        return new MyViewHolder(itemView);

    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {

        final Item item = shoppingList.get(position);

        myViewHolder.type.setText(item.getType());
        myViewHolder.name.setText(item.getName());



        myViewHolder.btnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setSnackbar(myViewHolder.getAdapterPosition());
            }
        });


        myViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    myViewHolder.name.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    myViewHolder.name.setPaintFlags(0);
                }
            }
        });

        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.setDeleteSnackBar(myViewHolder.getAdapterPosition());
                MainActivity.deleteItem(myViewHolder.getAdapterPosition());
            }
        });


        myViewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editItem.position = myViewHolder.getAdapterPosition();
                editItem.item = item;
                switchEdit();
            }
        });

        myViewHolder.est_price.setText(String.valueOf(item.getEst_price()));

        if(item.getType().equals("Toy")) {
            myViewHolder.type_icon.setImageResource(R.drawable.toy);
        }
        if(item.getType().equals("Games")) {
            myViewHolder.type_icon.setImageResource(R.drawable.games);
        }
        if(item.getType().equals("Shoes")) {
            myViewHolder.type_icon.setImageResource(R.drawable.shoes);
        }
        if(item.getType().equals("Shirt")) {
            myViewHolder.type_icon.setImageResource(R.drawable.shirt);

        }
        if(item.getType().equals("Trousers")) {
            myViewHolder.type_icon.setImageResource(R.drawable.trousers);

        }
        if(item.getType().equals("Underwear")) {
            myViewHolder.type_icon.setImageResource(R.drawable.underwear);

        }
        if(item.getType().equals("Fruits")) {
            myViewHolder.type_icon.setImageResource(R.drawable.fruits);

        }
        if(item.getType().equals("Dairy Product")) {
            myViewHolder.type_icon.setImageResource(R.drawable.dairy);

        }
        if(item.getType().equals("Paper Goods")) {
            myViewHolder.type_icon.setImageResource(R.drawable.paper);

        }
        if(item.getType().equals("Movies")) {
            myViewHolder.type_icon.setImageResource(R.drawable.movies);

        }
        if(item.getType().equals("Cleaners")) {
            myViewHolder.type_icon.setImageResource(R.drawable.soap);

        }
        if(item.getType().equals("Canned")) {
            myViewHolder.type_icon.setImageResource(R.drawable.canned);

        }
        if(item.getType().equals("Beverage")) {
            myViewHolder.type_icon.setImageResource(R.drawable.beverages);

        }
        if(item.getType().equals("Meat")) {
            myViewHolder.type_icon.setImageResource(R.drawable.meat);

        }
        if(item.getType().equals("Frozen Food")) {
            myViewHolder.type_icon.setImageResource(R.drawable.frozen);

        }
        if(item.getType().equals("Electronics")) {
            myViewHolder.type_icon.setImageResource(R.drawable.electronics);

        }
        if(item.getType().equals("Books")) {
            myViewHolder.type_icon.setImageResource(R.drawable.books);

        }
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }


}
