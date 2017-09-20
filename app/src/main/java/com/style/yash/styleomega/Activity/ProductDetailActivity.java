package com.style.yash.styleomega.Activity;

import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.style.yash.styleomega.Database.DatabaseHelper;
import com.style.yash.styleomega.Model.Cart;
import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.Model.User;
import com.style.yash.styleomega.R;

import java.text.NumberFormat;

import static com.style.yash.styleomega.R.id.nameInput;

public class ProductDetailActivity extends MainActivity {


    TextView name;
    TextView price;
    ImageView product_view;
    Button cart;
    private String productid;
    DatabaseHelper db;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_detail);
//
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.searchable
        getLayoutInflater().inflate(R.layout.activity_product_detail, contentFrameLayout);


        productid = getIntent().getStringExtra("itemID");

        db = new DatabaseHelper(this);
        product = db.viewProduct(productid);

        //INITIALIZE VIEWS
        name = (TextView) findViewById(R.id.name);
        product_view = (ImageView) findViewById(R.id.product_view);
        price = (TextView) findViewById(R.id.price);
        cart = (Button) findViewById(R.id.cart);

        name.setText(product.getName());

        price.setText(String.valueOf((product.getPrice())));

        int resId = getResources().getIdentifier(product.getImage(), "drawable", getPackageName());
        product_view.setImageResource(resId);


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cart cart = new Cart();
                cart.setProductId(product.getProductId());
                cart.setName(product.getName());
                cart.setImage(product.getImage());
                cart.setPrice(product.getPrice());
                cart.setQuantity("1");
                cart.setTotalprice(product.getPrice());

                db.addShoppingCart(cart);

                Toast.makeText(getApplicationContext(), "added to cart!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}



