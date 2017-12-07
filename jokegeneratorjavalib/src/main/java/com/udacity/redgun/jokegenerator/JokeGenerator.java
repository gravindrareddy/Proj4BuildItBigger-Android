package com.udacity.redgun.jokegenerator;

/**
 * Class to access jokes fetched from cloud
 */
public class JokeGenerator {

    /**
     * Android lib will use this method to access Joke
     *
     * @return
     */
    public String getJokeFromLocal() {
        return "ha ha ha ha";
    }

    /**
     *
     */
    public String fetchJokeFromCloud() {
        JokeResourceImpl jri = new JokeResourceImpl();
        Joke joke = new Joke();
        try {
            joke = jri.getJoke();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return joke.getJokeContent();
    }
}
