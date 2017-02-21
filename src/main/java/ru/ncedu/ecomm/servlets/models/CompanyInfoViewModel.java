package ru.ncedu.ecomm.servlets.models;

import java.util.Map;

public class CompanyInfoViewModel {
    private Map<String, String> socials;
    private String email;
    private String phone;
    private String rights;

    public CompanyInfoViewModel(Map<String, String> socials, String email, String phone, String rights) {
        this.socials = socials;
        this.email = email;
        this.phone = phone;
        this.rights = rights;
    }

    public Map<String, String> getSocials() {
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