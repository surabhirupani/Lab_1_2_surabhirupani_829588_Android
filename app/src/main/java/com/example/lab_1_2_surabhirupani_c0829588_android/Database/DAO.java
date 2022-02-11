package com.example.lab_1_2_surabhirupani_c0829588_android.Database;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab_1_2_surabhirupani_c0829588_android.Model.Product;

import java.util.List;

@Dao
public interface DAO {
    //Insert Querys
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    //Delete Querys
    @Query("DELETE FROM product WHERE productId = :productId")
    void deleteProduct(Long productId);

    @Query("SELECT * FROM product")
    List<Product> getProducts();
}
