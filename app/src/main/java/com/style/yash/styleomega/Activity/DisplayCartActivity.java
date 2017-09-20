package com.style.yash.styleomega.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.style.yash.styleomega.Database.DatabaseHelper;
import com.style.yash.styleomega.Model.Cart;
import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.R;

import java.util.List;

public class DisplayCartActivity extends MainActivity {

    List<Cart> shoppingCartList;
    Context context = this;
    private DatabaseHelper db;
    CartAdapter adap;
    ListView list;
    private String productID;
    private String keyword;
    TextView grandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_display_cart, contentFrameLayout);

        db = new DatabaseHelper(context);
        shoppingCartList = db.getAllItems();
        adap = new CartAdapter(context, shoppingCartList);


        list = (ListView) findViewById(R.id.cartList);
        list.setAdapter(adap);

    }
}
