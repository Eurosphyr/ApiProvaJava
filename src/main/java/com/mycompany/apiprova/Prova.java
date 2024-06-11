package com.mycompany.apiprova;

import com.mycompany.apiprova.model.Root;
import com.mycompany.apiprova.service.StarWars;
import com.mycompany.apiprova.service.StarWarsApi;
import com.mycompany.apiprova.model.People;
import com.mycompany.apiprova.model.Planet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Prova {

    public static void main(String[] args) {
        StarWarsApi.init();
        StarWars swapi = StarWarsApi.getApi();

        // Call the getRootUrls method
        Call<Root> rootCall = swapi.getRootUrls();
        rootCall.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    System.out.println("Root urls: " + root);
                } else {
                    System.out.println("Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                System.out.println("Failed to retrieve root urls: " + t.getMessage());
            }
        });

        // Call the getPeople method to fetch a specific person (e.g., Luke Skywalker)
        int lukeSkywalkerId = 1;
        Call<People> peopleCall = swapi.getPeople(lukeSkywalkerId);
        peopleCall.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                if (response.isSuccessful()) {
                    People lukeSkywalker = response.body();
                    System.out.println("Luke Skywalker: " + lukeSkywalker);
                } else {
                    System.out.println("Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                System.out.println("Failed to retrieve Luke Skywalker: " + t.getMessage());
            }
        });

        // Call the getPlanet method to fetch a specific planet (e.g., Tatooine)
        int tatooineId = 1;
        Call<Planet> planetCall = swapi.getPlanet(tatooineId);
        planetCall.enqueue(new Callback<Planet>() {
            @Override
            public void onResponse(Call<Planet> call, Response<Planet> response) {
                if (response.isSuccessful()) {
                    Planet tatooine = response.body();
                    System.out.println("Tatooine: " + tatooine);
                } else {
                    System.out.println("Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Planet> call, Throwable t) {
                System.out.println("Failed to retrieve Tatooine: " + t.getMessage());
            }
        });
    }
}
