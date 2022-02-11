package com.example.lab_1_2_surabhirupani_c0829588_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1_2_surabhirupani_c0829588_android.AddProductActivity;
import com.example.lab_1_2_surabhirupani_c0829588_android.Model.Product;
import com.example.lab_1_2_surabhirupani_c0829588_android.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    
    private List<Product> items;
    private Context context;

    public ProductAdapter(List<Product> items) {
        this.items = items;
        }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName;
        TextView tvItemPrice;
        View parentView;

        ViewHolder(View v) {
            super(v);
            tvItemName = v.findViewById(R.id.tvName);
            tvItemPrice = v.findViewById(R.id.tvPrice);
            parentView = v.findViewById(R.id.parentView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_layout, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Product product = getItem(position);
        holder.tvItemName.setText(product.getProductName());
        holder.tvItemPrice.setText("$"+String.valueOf(product.getProductPrice()));
        holder.parentView.setTag(position);
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddProductActivity.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
    }


    private Product getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
