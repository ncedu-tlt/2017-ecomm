package ru.ncedu.ecomm.data.models;

public class PriceRangeModel {
    private Long min;
    private Long max;

    public PriceRangeModel() {
    }

    public PriceRangeModel(Long min, Long max) {
        this.min = min;
        this.max = max;
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }
}
