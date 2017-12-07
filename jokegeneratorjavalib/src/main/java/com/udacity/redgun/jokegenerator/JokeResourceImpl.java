package com.udacity.redgun.jokegenerator;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rguntaka on 01/12/17.
 */


public class JokeResourceImpl {

    private static final String URI_BOOK = "https://proj4builditbigger.firebaseio.com";

    public Joke getJoke() throws Exception {
        //Create retrofit, set the API base URL and GSonConverterFactory
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URI_BOOK)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //Create service
        JokeResourceService jokeResource = retrofit.create(JokeResourceService.class);
        Call<Joke> joke = jokeResource.getJoke();
        return joke.execute().body();
    }

}