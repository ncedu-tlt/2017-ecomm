package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.PriceRangeModel;

public class PriceRangeModelBuilder {
    private Long min;
    private Long max;

    public PriceRangeModelBuilder() {
    }

    public PriceRangeModelBuilder PriceRangeViewModel() {
        return new PriceRangeModelBuilder();
    }

    public PriceRangeModelBuilder setMin(Long min) {
        this.min = min;
        return this;
    }

    public PriceRangeModelBuilder setMax(Long max) {
        this.max = max;
        return this;
    }

    public PriceRangeModel build() {
        return new PriceRangeModel(min, max);
    }
}
