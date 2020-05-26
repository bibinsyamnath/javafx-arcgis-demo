package io.github.bibinsyamnath.javafxargisdemo;

import com.google.gson.annotations.SerializedName;

public class CountryInfo {
    @SerializedName("lat")
    private Double latitude;
    @SerializedName("long")
    private Double longitude;

    public CountryInfo() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CountryInfo [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

}
