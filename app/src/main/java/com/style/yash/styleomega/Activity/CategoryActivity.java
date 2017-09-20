package com.style.yash.styleomega.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

        handleIntent(getIntent());


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        return true;
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }


    public void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void doSearch(String queryStr) {


        //Log.i("Your search: ",queryStr);
    }


}
