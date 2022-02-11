package com.example.lab_1_2_surabhirupani_c0829588_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_1_2_surabhirupani_c0829588_android.Database.AppDatabase;
import com.example.lab_1_2_surabhirupani_c0829588_android.Database.DAO;
import com.example.lab_1_2_surabhirupani_c0829588_android.Model.Product;

public class AddProductActivity extends AppCompatActivity {
    EditText et_name, et_desc, et_price, et_lat, et_long;
    Product product;
    boolean editMode = false;
    Button btn_add;
    TextView tv_location;
    private DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        dao = AppDatabase.getDb(this).getDAO();
        if(getIntent().getSerializableExtra("product")!=null) {
            product = (Product) getIntent().getSerializableExtra("product");
            editMode = true;
        }

        et_name = findViewById(R.id.et_productname);
        et_desc = findViewById(R.id.et_desc);
        et_price = findViewById(R.id.et_price);
        et_lat = findViewById(R.id.et_lat);
        et_long = findViewById(R.id.et_long);
        btn_add = findViewById(R.id.btn_add);
        tv_location = findViewById(R.id.tv_location);

        initToolbar();

        if(editMode) {
            getSupportActionBar().setTitle("Edit Product");
            et_long.setText(""+product.getProductLong());
            et_lat.setText(""+product.getProductLat());
            et_name.setText(""+product.getProductName());
            et_desc.setText(""+product.getProductDesc());
            et_price.setText(""+product.getProductPrice());
            btn_add.setText("Update Product");
            tv_location.setVisibility(View.VISIBLE);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
            }
        });

        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductActivity.this, MapActivity.class);
                intent.putExtra("product_lat", product.getProductLat());
                intent.putExtra("product_long", product.getProductLong());
                startActivity(intent);
            }
        });
    }

    private void validateFields() {
        if (et_name.getText().toString().equals("")) {
            et_name.setError("Please enter product Name");
        } else if (et_desc.getText().toString().equals("")) {
            et_desc.setError("Please enter product description");
        } else if (et_price.getText().toString().equals("")) {
            et_price.setError("Please enter product price");
        } else if (et_lat.getText().toString().equals("")) {
            et_lat.setError("Please enter latitude");
        } else if (et_long.getText().toString().equals("")) {
            et_long.setError("Please enter longitude");
        } else {
            Product product1;
            String message = "";
            if(editMode) {
                product1 = product;
                message = "Product updated!";
            } else {
                product1 = new Product();
                message = "Product added!";
            }
            product1.setProductName(et_name.getText().toString());
            product1.setProductDesc(et_desc.getText().toString());
            product1.setProductPrice(Double.valueOf(et_price.getText().toString()));
            product1.setProductLat(Double.valueOf(et_lat.getText().toString()));
            product1.setProductLong(Double.valueOf(et_long.getText().toString()));
            dao.insertProduct(product1);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            finish();
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