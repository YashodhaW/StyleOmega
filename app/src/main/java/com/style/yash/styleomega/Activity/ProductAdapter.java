package com.style.yash.styleomega.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.R;

import java.util.List;
class ProductAdapter extends ArrayAdapter<Product>{

    public static List<Product> itemList;
    Context context;

    public ProductAdapter(@NonNull Context context, List<Product> itemList) {
        super(context, R.layout.activity_product_adapter, itemList);
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater itemInflater = LayoutInflater.from(getContext()); //context is background information
        View customView = itemInflater.inflate(R.layout.activity_product_adapter, parent, false);

        //getting reference
        final String name=itemList.get(position).getName();
        final String image=itemList.get(position).getImage();
        final String code=itemList.get(position).getProductId();

        TextView itemDesc = (TextView)customView.findViewById(R.id.nameText);
        ImageView itemImage = (ImageView)customView.findViewById(R.id.image);
        itemDesc.setText(name);

        int resId = context.getResources().getIdentifier(image,"drawable",context.getPackageName());
        itemImage.setImageResource(resId);

        return customView;
    }


}
