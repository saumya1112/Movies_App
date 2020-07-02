package com.example.movies_app.network;
import com.example.movies_app.models.MovieNetworkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


interface TMDbAPI {
    @GET("movie/popular")
    Call<MovieNetworkResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieNetworkResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}