package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class FilterViewModel {
    private long id;
    private String name;
    private List<String> values;

    public FilterViewModel() {
    }

    public FilterViewModel(long id, String name, List<String> values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }
}
