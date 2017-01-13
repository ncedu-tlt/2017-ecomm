package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class Images {
    private String imagesLinks;

    public Images(String imagesLinks) {
        this.imagesLinks = imagesLinks;
    }

    public String getImagesLinks() {
        return imagesLinks;
    }

    public void setImagesLinks(String imagesLinks) {
        this.imagesLinks = imagesLinks;
    }
}
