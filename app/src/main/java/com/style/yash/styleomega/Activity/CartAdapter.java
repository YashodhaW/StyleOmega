package com.style.yash.styleomega.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.style.yash.styleomega.Model.Cart;
import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.R;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Cart> {

    public static List<Cart> cartList;
    Context context;

    public CartAdapter(@NonNull Context context, List<Cart> cartList) {
        super(context, R.layout.activity_cart_adapter, cartList);
        this.cartList = cartList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater itemInflater = LayoutInflater.from(getContext());
        View customView = itemInflater.inflate(R.layout.activity_cart_adapter, parent, false);

        //getting reference
        final String name = cartList.get(position).getName();
        final String image = cartList.get(position).getImage();
        final Double price = cartList.get(position).getPrice();

        TextView itemDesc = (TextView) customView.findViewById(R.id.name);
        ImageView itemImage = (ImageView) customView.findViewById(R.id.image);
        TextView itemPrice = (TextView) customView.findViewById(R.id.priceText);

        itemDesc.setText(name);
        itemPrice.setText(String.valueOf(price));

        int resId = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
        itemImage.setImageResource(resId);

        return customView;
    }

}

