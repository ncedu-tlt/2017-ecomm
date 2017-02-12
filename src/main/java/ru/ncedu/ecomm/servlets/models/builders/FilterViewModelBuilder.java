package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.FilterValueViewModel;
import ru.ncedu.ecomm.servlets.models.FilterViewModel;

import java.util.List;

public class FilterViewModelBuilder {
    private long id;
    private String name;
    private List<FilterValueViewModel> values;

    public FilterViewModelBuilder setId(long id){
        this.id = id;
        return this;
    }
    public FilterViewModelBuilder setName(String name){
        this.name = name;
        return this;
    }

    public FilterViewModelBuilder setValues(List<FilterValueViewModel> values){
        this.values = values;
        return this;
    }


    public FilterViewModel build(){
        return new FilterViewModel(
                id,
                name,
                values
        );
    }

}
