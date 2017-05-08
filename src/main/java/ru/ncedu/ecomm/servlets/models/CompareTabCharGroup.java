package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class CompareTabCharGroup {
    private String name;
    private List<CompareChar> productChars;

    public CompareTabCharGroup() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompareChar> getProductChars() {
        return productChars;
    }

    public void setProductChars(List<CompareChar> productChars) {
        this.productChars = productChars;
    }
}
