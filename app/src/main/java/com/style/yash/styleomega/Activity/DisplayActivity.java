package com.style.yash.styleomega.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.style.yash.styleomega.Database.DatabaseHelper;
import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.R;

import java.util.List;

public class DisplayActivity extends MainActivity {

    List<Product> itemList;
    Context context = this;
    private DatabaseHelper db;
    ProductAdapter adap;
    ListView list;
    private  String productID;
    private  String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_display, contentFrameLayout);

        keyword = getIntent().getStringExtra("TYPE");

        db = new DatabaseHelper(this);

        itemList = db.searchItemsByKeyword(keyword);

        adap = new ProductAdapter(context, itemList);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adap);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String itemCode = itemList.get(position).getProductId();

                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra("itemID", itemCode);
                        context.startActivity(intent);
                    }
                }
        );



    }


}
