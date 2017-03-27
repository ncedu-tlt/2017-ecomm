package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.DiscountDAOObject;

public class DiscountDAOObjectBuilder {
    private long discountId;
    private String name;
    private int value;

    public DiscountDAOObjectBuilder() {
    }

    public DiscountDAOObjectBuilder setDiscountId(long discountId) {
        this.discountId = discountId;

        return this;
    }

    public DiscountDAOObjectBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public DiscountDAOObjectBuilder setValue(int value) {
        this.value = value;

        return this;
    }

    public DiscountDAOObject build(){
        return new DiscountDAOObject(
                discountId,
                name,
                value
        );
    }
}
