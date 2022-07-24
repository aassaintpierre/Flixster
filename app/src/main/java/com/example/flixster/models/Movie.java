package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath,tittle,overview,backdropPath;
    double average;
    public  Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        tittle = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        average = jsonObject.getDouble("vote_average");
    }
    public  static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new  Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }


    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);

    }
    public String getTittle() {
        return tittle;
    }

    public String getOverview() {
        return overview;
    }

    public double getAverage() {
        return average;
    }
}
