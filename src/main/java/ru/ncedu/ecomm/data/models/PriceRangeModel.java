package ru.ncedu.ecomm.data.models;

public class PriceRangeModel {
    private double min;
    private double max;

    public PriceRangeModel() {
    }

    public PriceRangeModel(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {

        return min;
    }

    public double getMax() {
        return max;
    }
}
