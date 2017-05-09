package ru.ncedu.ecomm.servlets.models;

import java.util.ArrayList;
import java.util.List;

public class SessionCompareObject {
    private List<CompareTabCharGroup> productChars = new ArrayList<>();
    private List<ProductViewModel> productView = new ArrayList<>();
    private List<ProductDetailsModel> sourceList = new ArrayList<>();

    public SessionCompareObject() {
    }

    public int getProductListSize() {
        return sourceList != null ? sourceList.size() : 0;
    }

    public List<CompareTabCharGroup> getProductChars() {
        return productChars;
    }

    public void setProductChars(List<CompareTabCharGroup> productChars) {
        this.productChars = productChars;
    }

    public List<ProductViewModel> getProductView() {
        return productView;
    }

    public void setProductView(List<ProductViewModel> productView) {
        this.productView = productView;
    }

    public List<ProductDetailsModel> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<ProductDetailsModel> sourceList) {
        this.sourceList = sourceList;
    }
}
