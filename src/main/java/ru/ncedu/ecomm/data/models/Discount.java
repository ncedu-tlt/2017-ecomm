package ru.ncedu.ecomm.data.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {
    private long discountId;
    private String name;
    private int value;

    public Discount() {
    }

    public Discount(long discountId,
                    String name,
                    int value) {

        this.discountId = discountId;
        this.name = name;
        this.value = value;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
