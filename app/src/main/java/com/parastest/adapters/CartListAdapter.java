package com.parastest.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parastest.R;
import com.parastest.model.Product;

import java.util.List;


public class CartListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> productList;
    private Activity activity;


    public CartListAdapter(Activity activity, List<Product> productList) {

        this.productList = productList;
        this.activity = activity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_list, parent, false);
        vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if (productList.get(position).getImage().getSrc() != null) {
            Glide.with(activity)
                    .load(productList.get(position).getImage().getSrc())
                    .centerCrop()
                    .error(R.drawable.no_image_placeholder)
                    .placeholder(R.drawable.image_loading_placeholder)
                    .into(((ViewHolder) holder).imgProductThumbnail);
        }

        if (productList.get(position).getTitle() != null) {
            ((ViewHolder) holder).txtProductTitle.setText(productList.get(position).getTitle());
        }

        if (productList.get(position).getTitle() != null) {
            ((ViewHolder) holder).txtSubTitle.setText(productList.get(position).getTitle());
        }

        if (productList.get(position).getVariants().get(0).getPrice() != null) {
            ((ViewHolder) holder).txtCartPrice.setText(productList.get(position).getVariants().get(0).getPrice());
        }

        ((ViewHolder) holder).llRowMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llRowMain;
        TextView txtProductTitle, txtSubTitle, txtCartPrice;
        ImageView imgProductThumbnail;

        public ViewHolder(@NonNull View view) {
            super(view);

            llRowMain = view.findViewById(R.id.llRowMain);
            imgProductThumbnail = view.findViewById(R.id.imgProductThumbnail);


            txtProductTitle = view.findViewById(R.id.txtProductTitle);
            txtSubTitle = view.findViewById(R.id.txtSubTitle);
            txtCartPrice = view.findViewById(R.id.txtCartPrice);
        }

    }

    public void setData( List<Product> productList) {

        this.productList = productList;
        notifyDataSetChanged();

    }

}