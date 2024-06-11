package com.mycompany.apiprova.service;

import com.mycompany.apiprova.model.Film;
import com.mycompany.apiprova.model.People;
import com.mycompany.apiprova.model.Planet;
import com.mycompany.apiprova.model.Root;
import com.mycompany.apiprova.model.SWModelList;
import com.mycompany.apiprova.model.Species;
import com.mycompany.apiprova.model.Starship;
import com.mycompany.apiprova.model.Vehicle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StarWars {

    @GET("/")
    public Call<Root> getRootUrls();

    @GET("people/")
    public Call<SWModelList<People>> getAllPeople(@Query("page") int page);

    @GET("people/{id}/")
    public Call<People> getPeople(@Path("id") int peopleId);

    @GET("films/")
    public Call<SWModelList<Film>> getAllFilms(@Query("page") int page);

    @GET("films/{id}/")
    public Call<Film> getFilm(@Path("id") int filmId);

    @GET("starships/")
    public Call<SWModelList<Starship>> getAllStarships(@Query("page") int page);

    @GET("starships/{id}/")
    public Call<Starship> getStarship(@Path("id") int starshipId);

    @GET("vehicles/")
    public Call<SWModelList<Vehicle>> getAllVehicles(@Query("page") int page);

    @GET("vehicles/{id}/")
    public Call<Vehicle> getVehicle(@Path("id") int vehicleId);

    @GET("species/")
    public Call<SWModelList<Species>> getAllSpecies(@Query("page") int page);

    @GET("species/{id}/")
    public Call<Species> getSpecies(@Path("id") int speciesId);

    @GET("planets/")
    public Call<SWModelList<Planet>> getAllPlanets(@Query("page") int page);

    @GET("planets/{id}/")
    public Call<Planet> getPlanet(@Path("id") int planetId);
}
