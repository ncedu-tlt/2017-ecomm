package ru.ncedu.ecomm.servlets.models;

public class PriceRangeViewModel {
    private double min;
    private double max;

    public PriceRangeViewModel() {
    }

    public PriceRangeViewModel(double min, double max) {
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
