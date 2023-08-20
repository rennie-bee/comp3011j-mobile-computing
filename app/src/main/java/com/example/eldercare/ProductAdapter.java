package com.example.eldercare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> mProductList;

    // extends the view-holder of recyclerview
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView productImage;
        TextView productName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            productImage = (ImageView) view.findViewById(R.id.product_image);
            productName = (TextView) view.findViewById(R.id.product_name);
        }
    }

    public ProductAdapter(List<Product> product_list) {
        mProductList = product_list;
    }

    // Re-write onCreateViewHolder to create view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // In real time, get which product is clicked by user
                // Send user to the detailed page of this product
                int position = holder.getAdapterPosition();
                Product product = mProductList.get(position);
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                // product name and image ID should be sent together
                intent.putExtra(ProductDetailActivity.PRODUCT_NAME, product.getName());
                intent.putExtra(ProductDetailActivity.PRODUCT_IMAGE_ID, product.getImageID());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    // Use Glide to load images
    // Glide is a package which supports image display in a very sensational way
    // it can be referenced from URL:https://github.com/bumptech/glide
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.productName.setText(product.getName());
        Glide.with(mContext).load(product.getImageID()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
