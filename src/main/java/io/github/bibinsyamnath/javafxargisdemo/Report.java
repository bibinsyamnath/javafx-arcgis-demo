package io.github.bibinsyamnath.javafxargisdemo;

import com.google.gson.annotations.SerializedName;

public class Report {
    @SerializedName("cases")
    private Long totalCases;

    @SerializedName("deaths")
    private Long totalDeaths;

    @SerializedName("recovered")
    private Long totalRecovered;

    @SerializedName("affectedCountries")
    private Long affectedCountries;

    public Report() {
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

    public Long getAffectedCountries() {
        return affectedCountries;
    }

    public void setAffectedCountries(Long affectedCountries) {
        this.affectedCountries = affectedCountries;
    }

    @Override
    public String toString() {
        return "Report [affectedCountries=" + affectedCountries + ", totalCases=" + totalCases + ", totalDeaths="
                + totalDeaths + ", totalRecovered=" + totalRecovered + "]";
    }

}
