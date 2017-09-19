package com.style.yash.styleomega.Activity;

import android.content.ClipData;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.style.yash.styleomega.Database.DatabaseHelper;
import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.R;

import java.text.NumberFormat;

public class ItemDetailActivity extends MainActivity {


    TextView name;
    TextView price;
    ImageView product_view;
    Button cart;
    private String productid;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_item_detail);
//
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_item_detail, contentFrameLayout);


        productid = getIntent().getStringExtra("itemID");

        db = new DatabaseHelper(this);
        Product product = db.viewProduct(productid);

        //INITIALIZE VIEWS
        name = (TextView) findViewById(R.id.name);
        product_view = (ImageView) findViewById(R.id.product_view);
        price = (TextView) findViewById(R.id.price);
        cart = (Button) findViewById(R.id.cart);


//
        name.setText(product.getName());
//
        price.setText(String.valueOf((product.getPrice())));



        int resId =getResources().getIdentifier(product.getImage(),"drawable",getPackageName());
        product_view.setImageResource(resId);


    }
}
