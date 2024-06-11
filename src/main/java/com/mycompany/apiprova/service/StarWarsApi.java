package com.mycompany.apiprova.service;

import com.mycompany.apiprova.util.api;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarWarsApi {

    private static StarWars starWarsApi;
    private StarWars mSwApi;
    private static StarWarsApi sInstance;
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://swapi.dev/api/";

    private StarWarsApi() {

        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mSwApi = restAdapter.create(StarWars.class);
    }

    public static void init() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }
    public static StarWars getApi() {
        if (starWarsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://swapi.dev/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            starWarsApi = retrofit.create(StarWars.class);
        }
        return starWarsApi;
    }

    public void setApi(StarWars starWarsApi) {
        sInstance.mSwApi = starWarsApi;
    }
}
