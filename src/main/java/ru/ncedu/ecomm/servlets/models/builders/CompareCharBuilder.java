package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CompareChar;

import java.util.List;

public class CompareCharBuilder {
    private String name;
    private List<String> CharLines;

    public CompareCharBuilder() {
    }

    public CompareCharBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public CompareCharBuilder setCharLines(List<String> charLines) {
        CharLines = charLines;
        return this;
    }

    public CompareChar build(){
        return new CompareChar(name, CharLines);
    }

}
