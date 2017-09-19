package com.style.yash.styleomega.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.style.yash.styleomega.R;

public class SearchResults extends AppCompatActivity {

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);



        String type = getIntent().getStringExtra("TYPE");



/*  List<> searchdatabase(type)*/
    }
}
