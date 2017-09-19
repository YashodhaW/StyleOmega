package com.style.yash.styleomega.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yash on 8/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "StyleOmega.db";

    /* ----------------User table---------------------------------*/

    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";


    // user sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";


    /* ----------------Product table---------------------------------*/
    private static final String TABLE_PRODUCT = "product";

    // Product Table Columns names
    private static final String COLUMN_PRODUCT_ID = "productid";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PRODUCT_PRICE = "product_price";
    private static final String COLUMN_PRODUCT_IMAGE = "product_image";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";

    // create table sql query
    private String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "(" +
            COLUMN_PRODUCT_ID + " TEXT," +
            COLUMN_PRODUCT_NAME + " TEXT," +
            COLUMN_PRODUCT_IMAGE + " TEXT," +
            COLUMN_PRODUCT_PRICE + " DOUBLE" +
            ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);

        populatedatabase(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PRODUCT_TABLE);

        // Create tables again
        onCreate(db);

    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User selectUser(String email) {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * " +
                "FROM user " +
                "WHERE user_email = '" + email + "' " +
                " ";


        // query user table with conditions
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */


        Cursor cursor = db.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst()) {
            user = new User();

            user.setId(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
        }
        cursor.close();
        db.close();

        return user;
    }

    /*-----------Update User Details------------ */

    public void UpdateUserDetails(String cid, String name, String email) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, name);
        cv.put(COLUMN_USER_EMAIL, email);
        cv.put(COLUMN_USER_PASSWORD, "t");

        db.update(TABLE_USER, cv, "user_id =" + cid, null);
    }

    /*------------Adding Products------------- */

    public void addProduct (Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, product.getProductId());
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());

        // Inserting Row

        db.insert(TABLE_PRODUCT, null, values);
        db.close();
    }

    public List<Product> searchItemsByKeyword(String name) {

        List<Product> productList = new ArrayList<Product>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * " +
                " FROM " + TABLE_PRODUCT +
                " WHERE " + COLUMN_PRODUCT_NAME +
                " LIKE  '%" + name + "%' ";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();

                product.setProductId(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                product.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE)));


                // Adding user record to list
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return productList;
    }

    public Product viewProduct(String id) {

        SQLiteDatabase db = getReadableDatabase();
        Product product = null;

        String selectItemQuery = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + COLUMN_PRODUCT_ID + "='" + id + "' ";

        try {
            Cursor c = db.rawQuery(selectItemQuery, null);
            if (c.moveToFirst()) {
                product = new Product();
                product.setProductId(c.getString(c.getColumnIndex(COLUMN_PRODUCT_ID)));
                product.setName(c.getString(c.getColumnIndex(COLUMN_PRODUCT_NAME)));
                product.setPrice(c.getDouble(c.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                product.setImage(c.getString(c.getColumnIndex(COLUMN_PRODUCT_IMAGE)));
            }
        } catch (Exception er) {


        }

        return product;
    }

    /*-----Items------ */


    public void populatedatabase(SQLiteDatabase db){

            /*-----------------------MENS-------------------- */

        String insert1 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A1','Men Alpkit Hoodie',2500,'M1') ";
        db.execSQL(insert1);


        String insert2 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A2','Men Black Edge T shirt ',1500,'M2') ";
        db.execSQL(insert2);


        String insert3 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A3','Men Blue Max Trouser',2000,'M3') ";
        db.execSQL(insert3);


        String insert4 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A4','Men Box Shorts ',1000,'M4') ";
        db.execSQL(insert4);


        String insert5 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A5','Men United T shirt',1500,'M5') ";
        db.execSQL(insert5);

        /*-----------------------WOMENS-------------------- */

        String insert6 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B6','Women Arm Cut Dress',3000,'W1') ";
        db.execSQL(insert6);


        String insert7 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B7','Women Red Slack Top',1500,'W2') ";
        db.execSQL(insert7);


        String insert8 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B8','Women Black Trouser',1250,'W3') ";
        db.execSQL(insert8);


        String insert9 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B9','Women Ripped Short',1650,'W4') ";
        db.execSQL(insert9);


    }
}
