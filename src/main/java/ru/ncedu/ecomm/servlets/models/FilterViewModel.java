package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class FilterViewModel {
    private Long id;
    private String name;
    private List<FilterValueViewModel> values;

    public FilterViewModel() {
    }

    public FilterViewModel(long id, String name, List<FilterValueViewModel> values) {
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

    public List<FilterValueViewModel> getValues() {
        return values;
    }

    public boolean isValuesHaveChecked(){
        for (FilterValueViewModel element : values) {
            if(element.isChecked()){
                return true;
            }
        }
        return false;
    }

}
