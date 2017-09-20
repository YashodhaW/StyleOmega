package com.style.yash.styleomega.Model;

/**
 * Created by Yash on 9/20/2017.
 */

public class Cart {

    private String cardId;
    private String productId;
    private String name;
    private String image;
    private String cid;
    private double price;
    private String quantity;
    private double totalprice;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Cart() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public Cart(String cardId, String productId, String name, String image, String cid, double price, String quantity, double totalprice) {
        this.cardId = cardId;
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.cid = cid;
        this.price = price;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }
}
