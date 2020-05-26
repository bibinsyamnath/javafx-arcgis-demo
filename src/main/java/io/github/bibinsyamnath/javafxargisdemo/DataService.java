package io.github.bibinsyamnath.javafxargisdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("countries")
    Call<List<Country>> getAffectedCountries();

    @GET("all")
    Call<Report> getTotal();
}