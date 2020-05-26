package io.github.bibinsyamnath.javafxargisdemo;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country")
    private String name;

    @SerializedName("cases")
    private Long totalCases;

    @SerializedName("deaths")
    private Long totalDeaths;

    @SerializedName("recovered")
    private Long totalRecovered;

    @SerializedName("countryInfo")
    private CountryInfo countryInfo;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }

    public Long getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Long getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Long totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    @Override
    public String toString() {
        return "Country [countryInfo=" + countryInfo + ", name=" + name + ", totalCases=" + totalCases
                + ", totalDeaths=" + totalDeaths + ", totalRecovered=" + totalRecovered + "]";
    }

}