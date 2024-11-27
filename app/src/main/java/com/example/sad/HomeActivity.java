package com.example.sad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        // RecyclerView setup
        RecyclerView recyclerView = findViewById(R.id.grid_section);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        // Data for the adapter
        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Bihun Goreng", "RM4.50", R.drawable.pic1_bihun));
        foodItems.add(new FoodItem("Keuy Teow Goreng", "RM4.50", R.drawable.pic2_keuy));
        foodItems.add(new FoodItem("Nasi Campur", "RM6.00", R.drawable.pic3_nasi));
        foodItems.add(new FoodItem("Roti Canai", "RM1.00", R.drawable.pic4_roti));
        foodItems.add(new FoodItem("Crispy Waffle", "RM5.00", R.drawable.pic5_waffle));
        foodItems.add(new FoodItem("Burger Special", "RM5.00", R.drawable.pic6_burger));

        // Adapter setup
        FoodAdapter adapter = new FoodAdapter(foodItems);
        recyclerView.setAdapter(adapter);
    }
}