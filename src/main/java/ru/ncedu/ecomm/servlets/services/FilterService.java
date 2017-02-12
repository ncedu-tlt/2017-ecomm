package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.Characteristic;
import ru.ncedu.ecomm.data.models.CharacteristicValue;
import ru.ncedu.ecomm.servlets.models.FilterValueViewModel;
import ru.ncedu.ecomm.servlets.models.FilterViewModel;
import ru.ncedu.ecomm.servlets.models.builders.FilterViewModelBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class FilterService {
    private static FilterService instance;

    public static FilterService getInstance() {
        if (instance == null) {
            instance = new FilterService();
        }
        return instance;
    }

    public List<FilterViewModel> getFilters(long categoryId) {

        List<FilterViewModel> filters = new ArrayList<>();
        List<Characteristic> filterableCharacteristics = getDAOFactory()
                .getChracteristicDAO()
                .getFilterableCharacteristicsByCategoryId(categoryId);

        for (Characteristic characteristic : filterableCharacteristics) {
            filters.add(new FilterViewModelBuilder()
                    .setId(characteristic.getCharacteristicId())
                    .setName(characteristic.getCharacteristicName())
                    .setValues(getValues(characteristic.getCharacteristicId(), categoryId))
                    .build());
        }
        return filters;
    }

    private List<FilterValueViewModel> getValues(long characteristicId, long categoryId) {
        List<CharacteristicValue> characteristicValues =
                getDAOFactory().getCharacteristicValueDAO()
                        .getCharacteristicValuesByIdAndProductsCategoryId(characteristicId, categoryId);
        List<FilterValueViewModel> values = new ArrayList<>();

        for (CharacteristicValue characteristicValue : characteristicValues) {
            values.add(new FilterValueViewModel(
                    characteristicValue.getCharacteristicValue(),false));
        }
        return values;
    }

    public List<FilterViewModel> getFiltersValuesChecked(long categoryId, Map<String, String[]> params) {
        List<FilterViewModel> filters = getFilters(categoryId);

        String[] values;

        for (FilterViewModel filter : filters) {
            if (params.get(filter.getName()) != null) {

                values = params.get(filter.getName());

                for (String str : values) {
                    for (FilterValueViewModel filterElement : filter.getValues()) {
                        if (filterElement.getName().compareTo(str) == 0) {
                            filterElement.setChecked(true);
                        }
                    }
                }
            }
        }
        return filters;
    }
}
