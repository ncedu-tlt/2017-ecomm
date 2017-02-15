package ru.ncedu.ecomm.servlets.models;

import ru.ncedu.ecomm.data.models.Property;

import java.util.List;

public class CompanyInfoViewModel {
    private List<Property> socials;
    private String email;
    private String phone;
    private String rights;

    public CompanyInfoViewModel(List<Property> socials, String email, String phone, String rights) {
        this.socials = socials;
        this.email = email;
        this.phone = phone;
        this.rights = rights;
    }

    public List<Property> getSocials() {
        return socials;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRights() {
        return rights;
    }
}