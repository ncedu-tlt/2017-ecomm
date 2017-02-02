package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.FilterElementViewModel;
import ru.ncedu.ecomm.servlets.models.FilterViewModel;

import java.util.List;

public class FilterViewModelBuilder {
    private String name;
    private List<FilterElementViewModel> values;

    public FilterViewModelBuilder setName(String name){
        this.name = name;
        return this;
    }

    public FilterViewModelBuilder setValues(List<FilterElementViewModel> values){
        this.values = values;
        return this;
    }

    public FilterViewModel build(){
        return new FilterViewModel(
                name,
                values
        );
    }

}
