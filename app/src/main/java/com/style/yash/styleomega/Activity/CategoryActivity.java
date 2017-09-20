package com.style.yash.styleomega.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.style.yash.styleomega.R;

public class CategoryActivity extends MainActivity implements View.OnClickListener {


    Button men;
    Button women;
    Button boys;
    Button girls;
    Button accessories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_category, contentFrameLayout);

        men = (Button) findViewById(R.id.men);
        women = (Button) findViewById(R.id.women);
        boys = (Button) findViewById(R.id.boys);
        girls = (Button) findViewById(R.id.girls);
        accessories = (Button) findViewById(R.id.accessories);

        men.setOnClickListener(this);
        women.setOnClickListener(this);
        boys.setOnClickListener(this);
        girls.setOnClickListener(this);
        accessories.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.men) {

            finish();
            Intent homepage = new Intent(CategoryActivity.this, DisplayCategoryActivity.class);
            homepage.putExtra("TYPE", "men");
            startActivity(homepage);

        } else if (v.getId() == R.id.women) {
            finish();
            Intent homepage = new Intent(CategoryActivity.this, DisplayCategoryActivity.class);
            homepage.putExtra("TYPE", "ladies");
            startActivity(homepage);
        } else if (v.getId() == R.id.boys) {

            finish();
            Intent homepage = new Intent(CategoryActivity.this, DisplayCategoryActivity.class);
            homepage.putExtra("TYPE", "boys");
            startActivity(homepage);

        } else if (v.getId() == R.id.boys) {

            finish();
            Intent homepage = new Intent(CategoryActivity.this, DisplayCategoryActivity.class);
            homepage.putExtra("TYPE", "girls");
            startActivity(homepage);

        }
    }
}
