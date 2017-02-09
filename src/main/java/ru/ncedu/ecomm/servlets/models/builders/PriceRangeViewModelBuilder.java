package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.PriceRangeViewModel;

public class PriceRangeViewModelBuilder {
    private double min;
    private double max;

    public PriceRangeViewModelBuilder() {
    }

    public PriceRangeViewModelBuilder PriceRangeViewModel() {
        return new PriceRangeViewModelBuilder();
    }

    public PriceRangeViewModelBuilder setMin(double min) {
        this.min = min;
        return this;
    }

    public PriceRangeViewModelBuilder setMax(double max) {
        this.max = max;
        return this;
    }

    public PriceRangeViewModel build() {
        return new PriceRangeViewModel(min, max);
    }
}
