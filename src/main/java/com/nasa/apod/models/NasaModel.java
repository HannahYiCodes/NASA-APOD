package com.nasa.apod.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class NasaModel {
    //{
//        "copyright": "Douglas J. StrubleFuture World Media",
//        "date": "2022-08-10",
//        "explanation": "Stars can create huge and intricate dust sculptures from the dense and dark molecular clouds from which they are born.  The tools the stars use to carve their detailed works are high energy light and fast stellar winds.  The heat they generate evaporates the dark molecular dust as well as causing ambient hydrogen gas to disperse and glow red.   Pictured here, a new open cluster of stars designated IC 1590 is nearing completion around the intricate interstellar dust structures in the emission nebula  NGC 281, dubbed the Pac-man Nebula because of its overall shape.  The dust cloud on the upper left is classified as a  Bok Globule as it may gravitationally collapse and form a star -- or stars. The Pacman Nebula lies about 10,000 light years away toward the constellation of Cassiopeia.",
//        "hdurl": "https://apod.nasa.gov/apod/image/2208/Pacman_Struble_1222.jpg",
//        "media_type": "image",
//        "service_version": "v1",
//        "title": "Dust Clouds of the Pacman Nebula",
//        "url": "https://apod.nasa.gov/apod/image/2208/Pacman_Struble_960.jpg"
//        }


    public String date;
    public String explanation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String hdurl;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String copyright;
    public String media_type;
    public String service_version;
    public String title;
    public String url;

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
