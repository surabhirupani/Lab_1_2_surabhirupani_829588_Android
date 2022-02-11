package com.example.lab_1_2_surabhirupani_c0829588_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.lab_1_2_surabhirupani_c0829588_android.Model.Product;

public class AddProductActivity extends AppCompatActivity {
    EditText et_name, et_desc, et_price, et_lat, et_long;
    Product product;
    boolean editMode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        if(getIntent().getSerializableExtra("product")!=null) {
            product = (Product) getIntent().getSerializableExtra("product");
            editMode = true;
        }

        et_name = findViewById(R.id.et_productname);
        et_desc = findViewById(R.id.et_desc);
        et_price = findViewById(R.id.et_price);
        et_lat = findViewById(R.id.et_lat);
        et_long = findViewById(R.id.et_long);

        initToolbar();

        if(editMode) {
            getSupportActionBar().setTitle("Edit Product");
            et_long.setText(""+product.getProductLong());
            et_lat.setText(""+product.getProductLat());
            et_name.setText(""+product.getProductName());
            et_desc.setText(""+product.getProductDesc());
            et_price.setText(""+product.getProductPrice());
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}