package ru.ncedu.ecomm.data.models;

public class RecomendedProduct {
    private long sourceProductId;
    private long targetProductId;

    public RecomendedProduct() {
    }

    public long getSourceProductId() {
        return sourceProductId;
    }

    public void setSourceProductId(long sourceProductId) {
        this.sourceProductId = sourceProductId;
    }

    public long getTargetProductId() {
        return targetProductId;
    }

    public void setTargetProductId(long targetProductId) {
        this.targetProductId = targetProductId;
    }
}
