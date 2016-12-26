package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.Discount;

public class DiscountBuilder {
    private long discountId;
    private String name;
    private int value;

    public DiscountBuilder() {
    }

    public DiscountBuilder setDiscountId(long discountId) {
        this.discountId = discountId;

        return this;
    }

    public DiscountBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public DiscountBuilder setValue(int value) {
        this.value = value;

        return this;
    }

    public Discount build(){
        return new Discount(
                discountId,
                name,
                value
        );
    }
}
