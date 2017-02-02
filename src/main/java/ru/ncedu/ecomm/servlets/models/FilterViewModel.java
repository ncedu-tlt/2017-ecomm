package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class FilterViewModel {
    private String name;
    private List<String> values;

    public FilterViewModel() {
    }

    public FilterViewModel(String name, List<String> values) {

        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }


}
