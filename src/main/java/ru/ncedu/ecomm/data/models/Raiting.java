package ru.ncedu.ecomm.data.models;

public class Raiting {
    private long productId;
    private int raiting;

    public Raiting(){

    }

    public Raiting(long productId, int raiting) {
        this.productId = productId;
        this.raiting = raiting;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }
}
