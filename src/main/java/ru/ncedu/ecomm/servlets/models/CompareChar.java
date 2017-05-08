package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class CompareChar {
    private String name;
    private List<String> charLines;

    public CompareChar() {
    }

    public CompareChar(String name, List<String> charLines) {
        this.name = name;
        this.charLines = charLines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCharLines() {
        return charLines;
    }

    public void setCharLines(List<String> charLines) {
        this.charLines = charLines;
    }
}
