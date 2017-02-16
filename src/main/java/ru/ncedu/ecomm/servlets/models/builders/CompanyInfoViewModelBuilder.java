package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.data.models.Property;
import ru.ncedu.ecomm.servlets.models.CompanyInfoViewModel;

import java.util.List;

public class CompanyInfoViewModelBuilder {
    private List<Property> socials;
    private String email;
    private String phone;
    private String rights;


    public CompanyInfoViewModelBuilder CompanyInfoViewModel() {
        return new CompanyInfoViewModelBuilder();
    }

    public CompanyInfoViewModelBuilder setSocials(List<Property> socials) {
        this.socials = socials;
        return this;
    }

    public CompanyInfoViewModelBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CompanyInfoViewModelBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CompanyInfoViewModelBuilder setRights(String rights) {
        this.rights = rights;
        return this;
    }

    public CompanyInfoViewModel build() {
        return new CompanyInfoViewModel(socials, email, phone, rights);
    }
}
