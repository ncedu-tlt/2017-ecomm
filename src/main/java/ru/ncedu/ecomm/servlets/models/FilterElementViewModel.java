package ru.ncedu.ecomm.servlets.models;

public class FilterElementViewModel {
    private String name;

    public FilterElementViewModel() {
    }

    public FilterElementViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
