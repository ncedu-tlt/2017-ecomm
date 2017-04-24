package ru.ncedu.ecomm.data.models.dao;

public class RecomendedProductDAOObject {
    private long sourceProductId;
    private long targetProductId;

    public RecomendedProductDAOObject() {
    }

    public RecomendedProductDAOObject(long sourceProductId,
                                      long targetProductId) {
        this.sourceProductId = sourceProductId;
        this.targetProductId = targetProductId;
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
