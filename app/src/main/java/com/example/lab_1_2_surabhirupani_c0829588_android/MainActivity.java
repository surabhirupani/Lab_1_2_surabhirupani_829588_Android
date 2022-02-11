package com.example.lab_1_2_surabhirupani_c0829588_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.lab_1_2_surabhirupani_c0829588_android.Adapter.ProductAdapter;
import com.example.lab_1_2_surabhirupani_c0829588_android.Database.AppDatabase;
import com.example.lab_1_2_surabhirupani_c0829588_android.Database.DAO;
import com.example.lab_1_2_surabhirupani_c0829588_android.Model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private DAO dao;
    private String productName, lastSearch = "";
    private View llEmptyBox;
    private List<Product> productList, searchedLists;
    private ProductAdapter productAdapter;
    Toolbar toolbar;

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            System.out.println("New Text:" + newText);
            searchedLists.clear();
            productAdapter.notifyDataSetChanged();
            for (Product pp : productList) {
                if (pp.getProductName().toUpperCase().contains(newText.toUpperCase(new Locale("tr"))) || pp.getProductDesc().toUpperCase().contains(newText.toUpperCase(new Locale("tr")))) {
                    searchedLists.add(pp);
                }
            }
            productAdapter.notifyDataSetChanged();
            lastSearch = newText;
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = AppDatabase.getDb(this).getDAO();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" Products");
//        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);

        llEmptyBox = findViewById(R.id.llEmptyBox);

        FloatingActionButton floatingActionButton = findViewById(R.id.fabNewListItem);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

        productList = new ArrayList<>();
        searchedLists = new ArrayList<>();
        insertProducts();
        productAdapter = new ProductAdapter(searchedLists);

        RecyclerView recyclerViewTodoList = findViewById(R.id.recyclerViewTaskItems);
        recyclerViewTodoList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTodoList.setHasFixedSize(true);
        recyclerViewTodoList.setAdapter(productAdapter);

        getProductItems();
    }

    private void insertProducts() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            Product product1 = new Product();
            product1.setProductName("IPhone");
            product1.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product1.setProductPrice(2500.0);
            product1.setProductLat(42.546245);
            product1.setProductLong(1.601554);
            productList.add(product1);

            Product product2 = new Product();
            product2.setProductName("Macbook Air");
            product2.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product2.setProductPrice(4500.0);
            product2.setProductLat(23.424076);
            product2.setProductLong(53.847818);
            productList.add(product2);

            Product product3 = new Product();
            product3.setProductName("Airpods Pro");
            product3.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product3.setProductPrice(500.0);
            product3.setProductLat(33.93911);
            product3.setProductLong(67.709953);
            productList.add(product3);

            Product product4 = new Product();
            product4.setProductName("Android TV");
            product4.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product4.setProductPrice(1200.0);
            product4.setProductLat(56.130366);
            product4.setProductLong(-106.346771);
            productList.add(product4);

            Product product5 = new Product();
            product5.setProductName("Chair");
            product5.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product5.setProductPrice(500.0);
            product5.setProductLat(28.033886);
            product5.setProductLong(1.659626);
            productList.add(product5);

            Product product6 = new Product();
            product6.setProductName("Refrigerator");
            product6.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product6.setProductPrice(1700.0);
            product6.setProductLat(26.820553);
            product6.setProductLong(30.802498);
            productList.add(product6);

            Product product7 = new Product();
            product7.setProductName("Microwave");
            product7.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product7.setProductPrice(1500.0);
            product7.setProductLat(55.378051);
            product7.setProductLong(-3.435973);
            productList.add(product7);

            Product product8 = new Product();
            product8.setProductName("Dining Table");
            product8.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product8.setProductPrice(700.0);
            product8.setProductLat(49.465691);
            product8.setProductLong(-2.585278);
            productList.add(product8);

            Product product9 = new Product();
            product9.setProductName("Apple TV");
            product9.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product9.setProductPrice(6500.0);
            product9.setProductLat(20.593684);
            product9.setProductLong(78.96288);
            productList.add(product9);

            Product product10 = new Product();
            product10.setProductName("Macbook Pro");
            product10.setProductDesc("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.");
            product10.setProductPrice(2500.0);
            product10.setProductLat(37.09024);
            product10.setProductLong(-95.712891);
            productList.add(product10);

            for (int i=0;i<productList.size();i++) {
                dao.insertProduct(productList.get(i));
            }
            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
    }

    private void getProductItems() {
        productList.clear();
        searchedLists.clear();
        List<Product> productList1 = dao.getProducts();
        if (productList1.isEmpty())
            llEmptyBox.setVisibility(View.VISIBLE);
        else {
            llEmptyBox.setVisibility(View.GONE);
            productList.addAll(productList1);
            searchedLists.addAll(productList1);
            productAdapter.notifyDataSetChanged();
        }
    }
}