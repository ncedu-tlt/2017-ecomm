package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.RecomendedProduct;

public class RecomendedProductBuilder {
    private long sourceProductId;
    private long targetProductId;

    public RecomendedProductBuilder() {
    }

    public RecomendedProductBuilder setSourceProductId(long sourceProductId) {
        this.sourceProductId = sourceProductId;

        return this;
    }

    public RecomendedProductBuilder setTargetProductId(long targetProductId) {
        this.targetProductId = targetProductId;

        return this;
    }

    public RecomendedProduct build(){
        return new RecomendedProduct(
                sourceProductId,
                targetProductId
        );
    }
}
