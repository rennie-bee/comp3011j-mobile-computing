package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ProductAdapter adapter;
    private List<Product> productList = new ArrayList<>();
    private Product thermos, stick, wheelchair, blood_pressure, magnifier, calcium_tablet,
            reading_glasses, first_aid_kit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initProducts();
        // We use recycler view and gridlayout manager here,
        // and set the spanCount as 2, so the view has 2 columns.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        // send the productList to the adapter, prepared for displaying
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    // To init six products, we create all product objects and add them to the productList
    // Each product is accompanied with a name and an image
    void initProducts() {
        thermos = new Product("Thermos", R.drawable.thermo);
        productList.add(thermos);
        stick = new Product("Walk Stick", R.drawable.stick);
        productList.add(stick);
        wheelchair = new Product("Wheelchair", R.drawable.wheelchair);
        productList.add(wheelchair);
        blood_pressure = new Product("Sphygmomanometer", R.drawable.sphygmomanometer);
        productList.add(blood_pressure);
        magnifier = new Product("Magnifier", R.drawable.magnifer);
        productList.add(magnifier);
        calcium_tablet = new Product("Calcium Tablet", R.drawable.calciumtablet);
        productList.add(calcium_tablet);
        reading_glasses = new Product("Reading Glasses", R.drawable.reading_glasses);
        productList.add(reading_glasses);
        first_aid_kit = new Product("First Aid Kit", R.drawable.kit);
        productList.add(first_aid_kit);
    }




}