package ru.ncedu.ecomm.data.models;

public class Rating {
    private long productId;
    private int rating;

    public Rating(){

    }

    public Rating(long productId, int raiting) {
        this.productId = productId;
        this.rating = raiting;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getRaiting() {
        return rating;
    }

    public void setRaiting(int raiting) {
        this.rating = raiting;
    }
}
