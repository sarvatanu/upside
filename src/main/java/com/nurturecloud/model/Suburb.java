package com.nurturecloud.model;

import com.google.gson.annotations.SerializedName;
import com.nurturecloud.geospatial.GeospatialPoint;

import java.util.Objects;

public class Suburb implements GeospatialPoint {

    @SerializedName("Pcode")
    private Long pcode;

    @SerializedName("Locality")
    private String locality;

    @SerializedName("State")
    private String state;

    @SerializedName("Comments")
    private String comments;

    @SerializedName("Category")
    private String category;

    @SerializedName("Longitude")
    private Double longitude;

    @SerializedName("Latitude")
    private Double latitude;


    public Suburb() {
    }

    public Long getPcode() {
        return pcode;
    }

    public String getLocality() {
        return locality;
    }

    public String getState() {
        return state;
    }

    public String getComments() {
        return comments;
    }

    public String getCategory() {
        return category;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suburb suburb = (Suburb) o;
        return Objects.equals(pcode, suburb.pcode) &&
                Objects.equals(locality, suburb.locality) &&
                Objects.equals(state, suburb.state) &&
                Objects.equals(comments, suburb.comments) &&
                Objects.equals(category, suburb.category) &&
                Objects.equals(longitude, suburb.longitude) &&
                Objects.equals(latitude, suburb.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pcode, locality, state, comments, category, longitude, latitude);
    }

    @Override
    public String toString() {
        return "Suburb{" +
                "pcode='" + pcode + '\'' +
                ", locality='" + locality + '\'' +
                ", state='" + state + '\'' +
                ", comments='" + comments + '\'' +
                ", category='" + category + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public boolean hasGeocodes() {
        return this.latitude != null && this.longitude != null;
    }
}
