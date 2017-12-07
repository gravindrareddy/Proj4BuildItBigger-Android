package com.udacity.redgun.jokegenerator;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rguntaka on 01/12/17.
 */

public class Joke {
    public String getJokeContent() {
        return joke;
    }

    public void setJokeContent(String jokeContent) {
        this.joke = jokeContent;
    }

    @SerializedName("joke")
    private String joke;
}
