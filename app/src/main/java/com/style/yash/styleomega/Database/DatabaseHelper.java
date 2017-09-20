package com.style.yash.styleomega.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.style.yash.styleomega.Model.Cart;
import com.style.yash.styleomega.Model.Product;
import com.style.yash.styleomega.Model.User;

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

    /* ---------------------User table---------------------------------*/

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

    private static final String TABLE_PRODUCT = "Product";

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


    /* ----------------AddToCart table---------------------------------*/

    private static final String TABLE_CART = "ShoppingCart";

    // Cart Table Columns names
    private static final String COLUMN_CART_ID = "cart_id";
    private static final String COLUMN_CART_CUSTOMER_ID = "cart_cid";
    private static final String COLUMN_CART_PRODUCTID = "product_id";
    private static final String COLUMN_CART_NAME = "cart_name";
    private static final String COLUMN_CART_IMAGE = "cart_image";
    private static final String COLUMN_CART_PRICE = "cart_price";
    private static final String COLUMN_CART_QUANTITY = "cart_quantity";
    private static final String COLUMN_CART_TOTALPRICE = "cart_totalprice";

    // create table sql query
    private String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "(" +
            COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_CART_CUSTOMER_ID + " TEXT," +
            COLUMN_CART_PRODUCTID + " TEXT," +
            COLUMN_CART_NAME + " TEXT," +
            COLUMN_CART_IMAGE + " TEXT," +
            COLUMN_CART_PRICE + " DOUBLE," +
            COLUMN_CART_QUANTITY + " INTEGER," +
            COLUMN_CART_TOTALPRICE + " DOUBLE" +
            ")";


    /*---------------------DB Options------------------------- */

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //executing the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_CART_TABLE);

        populatedatabase(db);
    }


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    private String DROP_CART_TABLE = "DROP TABLE IF EXISTS " + TABLE_CART;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PRODUCT_TABLE);
        db.execSQL(DROP_CART_TABLE);

        // Create tables automatically upon startup
        onCreate(db);

    }

    /*--------------------User------------------*/

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

    public void addProduct(Product product) {
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

    /*----------------Adding to the Cart--------------------- */

    public void addShoppingCart(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CART_PRODUCTID, cart.getProductId());
        values.put(COLUMN_CART_NAME, cart.getName());
        values.put(COLUMN_CART_IMAGE, cart.getImage());
        values.put(COLUMN_CART_PRICE, cart.getPrice());
        values.put(COLUMN_CART_QUANTITY, cart.getQuantity());
        values.put(COLUMN_CART_TOTALPRICE, cart.getTotalprice());


        // Inserting Row
        db.insert(TABLE_CART, null, values);
        db.close();
    }


    public List<Cart> getCart(String cusId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String [] columns = {
                COLUMN_CART_ID,
                COLUMN_CART_CUSTOMER_ID,
                COLUMN_CART_PRODUCTID,
                COLUMN_CART_PRICE,
                COLUMN_CART_TOTALPRICE,
                COLUMN_CART_QUANTITY
        };

        //List<Cart> cartList = new ArrayList<Cart>();


        String selectQuery = COLUMN_CART_ID + "= ?" + " AND " + COLUMN_CART_CUSTOMER_ID + "= ?";
        String[] selectionArgs = {cusId};

        Cursor cursor = db.query(
                TABLE_CART,
                columns,
                selectQuery,
                selectionArgs,
                null,
                null,
                null
        );

        Cart cart = null;

        if (cursor.moveToFirst()) {
            cart = new Cart();
            cart.setQuantity(cursor.getString(cursor.getColumnIndex(COLUMN_CART_QUANTITY)));
            cart.setCid(cursor.getString(cursor.getColumnIndex(COLUMN_CART_CUSTOMER_ID)));
            cart.setTotalprice(cursor.getDouble(cursor.getColumnIndex(COLUMN_CART_TOTALPRICE)));
            cart.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_CART_PRICE)));
        }
        return (List<Cart>) cart;
    }

    public List<Cart> getAllItems() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CART_ID,
                COLUMN_CART_NAME,
                COLUMN_CART_IMAGE,
                COLUMN_CART_PRICE,
                COLUMN_CART_TOTALPRICE
        };
        // sorting orders
        String sortOrder =
                COLUMN_CART_ID + " ASC";
        List<Cart> itemList = new ArrayList<Cart>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CART, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setCardId(cursor.getString(cursor.getColumnIndex(COLUMN_CART_ID)));
                cart.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CART_NAME)));
                cart.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_CART_IMAGE)));
                cart.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_CART_PRICE)));
                cart.setTotalprice(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_CART_TOTALPRICE))));

                // Adding user record to list
                itemList.add(cart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itemList;
    }

    /*--------------------------------------Items------------------------------------------ */

    public void populatedatabase(SQLiteDatabase db) {

            /*-----------------------MENS-------------------- */

        String insert1 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A1','Men Alpkit Hoodie',2500,'m1') ";
        db.execSQL(insert1);


        String insert2 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A2','Men Black Edge T shirt ',1500,'m2') ";
        db.execSQL(insert2);


        String insert3 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A3','Men Blue Max Trouser',2000,'m3') ";
        db.execSQL(insert3);


        String insert4 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A4','Men Box Shorts ',1000,'m4') ";
        db.execSQL(insert4);


        String insert5 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('A5','Men United T shirt',1500,'m5') ";
        db.execSQL(insert5);

        /*-----------------------WOMENS-------------------- */

        String insert6 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B6','Ladies Arm Cut Dress',3000,'w1') ";
        db.execSQL(insert6);


        String insert7 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B7','Ladies Red Slack Top',1500,'w2') ";
        db.execSQL(insert7);


        String insert8 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B8','Ladies Black Trouser',1250,'w3') ";
        db.execSQL(insert8);


        String insert9 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('B9','Ladies Ripped Short',1650,'w4') ";
        db.execSQL(insert9);

        /*-----------------------BOYS-------------------- */


        String insert10 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('C10','Boys Bulldozer Shorts',750,'b1') ";
        db.execSQL(insert10);


        String insert11 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('C11','Boys Kit ',2000,'b2') ";
        db.execSQL(insert11);


        String insert12 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('C12','Boys Long Sleeved three color',1000,'b3') ";
        db.execSQL(insert12);


        String insert13 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('C13','Boys Shirt light',700,'b4') ";
        db.execSQL(insert13);



        /*-----------------------GIRLS-------------------- */

        String insert14 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('D14','Girls Butterfly Dress',900,'g1') ";
        db.execSQL(insert14);


        String insert15 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('D15','Girls Limited Full dress ',2500,'g2') ";
        db.execSQL(insert15);


        String insert16 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('D16','Girls Mermaid Top',1000,'g3') ";
        db.execSQL(insert16);


        String insert17 = "INSERT INTO " + TABLE_PRODUCT + " ( " +
                COLUMN_PRODUCT_ID + "," +
                COLUMN_PRODUCT_NAME + "," +
                COLUMN_PRODUCT_PRICE + "," +
                COLUMN_PRODUCT_IMAGE +
                " ) " + " VALUES ('D17','Girls Shades of Pink trouser',800,'g4') ";
        db.execSQL(insert17);


    }
}
