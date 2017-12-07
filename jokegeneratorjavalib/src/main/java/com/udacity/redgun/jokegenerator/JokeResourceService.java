package com.udacity.redgun.jokegenerator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by rguntaka on 01/12/17.
 */

public interface JokeResourceService {


    //todo: move this key to constants file
    String auth_key = "sPTxIafgVE8KGK2hGAwkYmLITa3KgZRPOwi7L7de";

    @Headers({"Accept: application/json"})


    @GET("jokes.json?auth=" + auth_key)
    Call<Joke> getJoke();


}

