package ru.ncedu.ecomm.servlets.models;

public class FilterValueViewModel {
    private String name;
    private boolean checked;

    public FilterValueViewModel(String name, boolean checked) {
        this.name = name;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
