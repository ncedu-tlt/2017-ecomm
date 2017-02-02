package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.PriceRangeModel;

public class PriceRangeModelBuilder {
    private double min;
    private double max;

    public PriceRangeModelBuilder() {
    }

    public PriceRangeModelBuilder PriceRangeViewModel() {
        return new PriceRangeModelBuilder();
    }

    public PriceRangeModelBuilder setMin(double min) {
        this.min = min;
        return this;
    }

    public PriceRangeModelBuilder setMax(double max) {
        this.max = max;
        return this;
    }

    public PriceRangeModel build() {
        return new PriceRangeModel(min, max);
    }
}
