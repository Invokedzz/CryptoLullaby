package org.cryptolullaby.entity;

import org.springframework.web.multipart.MultipartFile;

public class Images {

    private String url;

    public Images () {}

    public Images (String url) {

        this.url = url;

    }

    public String getUrl () {

        return url;

    }

    public void setUrl (String url) {

        this.url = url;

    }

}
