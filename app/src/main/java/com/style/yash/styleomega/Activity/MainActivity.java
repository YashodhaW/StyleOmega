package com.style.yash.styleomega.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.style.yash.styleomega.R;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                final String appPackageName = getPackageName();

                switch (item.getItemId()) {

                    case R.id.nav_Home:
                        Intent main = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(main);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_Search:
/*                        Intent women = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(women);*/
//                        finish();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_OrderHistory:
                        Intent men = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(men);
//                        finish();
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.nav_Cart:
                        Intent shoppinhcart = new Intent(getApplicationContext(), DisplayCartActivity.class);
                        startActivity(shoppinhcart);
                        finish();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_Manage:
                        Intent manage = new Intent(getApplicationContext(), ManageUserActivity.class);
                        startActivity(manage);
//                      finish();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_ContactUs:
                        Intent contact = new Intent(getApplicationContext(), ManageUserActivity.class);
                        startActivity(contact);
//                        finish();
                        drawerLayout.closeDrawers();
                        break;

                }
                return false;
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //overridePendingTransitionEnter();
    }


}
