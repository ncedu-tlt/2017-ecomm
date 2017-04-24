package ru.ncedu.ecomm.data.models.dao.builders;

import ru.ncedu.ecomm.data.models.dao.RecomendedProductDAOObject;

public class RecomendedProductDAOObjectBuilder {
    private long sourceProductId;
    private long targetProductId;

    public RecomendedProductDAOObjectBuilder() {
    }

    public RecomendedProductDAOObjectBuilder setSourceProductId(long sourceProductId) {
        this.sourceProductId = sourceProductId;

        return this;
    }

    public RecomendedProductDAOObjectBuilder setTargetProductId(long targetProductId) {
        this.targetProductId = targetProductId;

        return this;
    }

    public RecomendedProductDAOObject build(){
        return new RecomendedProductDAOObject(
                sourceProductId,
                targetProductId
        );
    }
}
