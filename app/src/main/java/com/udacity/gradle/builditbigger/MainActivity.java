package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import udacity.redgun.jokeconsumerandroidlib.JokeDisplayActivity;

import com.udacity.redgun.jokegenerator.Joke;
import com.udacity.redgun.jokegenerator.JokeGenerator;


public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {
    private String joke;
    private final static String TAG = MainActivity.class.getSimpleName();
    private View linlaHeaderProgress;
    public static int LOADER_ID_FETCH_JOKE = 100;
    protected boolean loaderFinished = false;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Method to be called on button click
     *
     * @param view
     */
    public void tellJoke(View view) {
        getSupportLoaderManager().initLoader(LOADER_ID_FETCH_JOKE, null, this);

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        CustomAsyncTaskLoader asyncTaskLoader = new CustomAsyncTaskLoader(this);
        loaderFinished = false;
        linlaHeaderProgress.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        asyncTaskLoader.forceLoad();
        Log.i(TAG, "onCreateLoader");
        return asyncTaskLoader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        this.joke = data;
        linlaHeaderProgress.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        loaderFinished = true;
        //destroy loader. else, it will navigate back to child view automatically
        getSupportLoaderManager().destroyLoader(loader.getId());
        Intent myIntent = new Intent(this, JokeDisplayActivity.class);
        myIntent.putExtra(this.getString(R.string.joke_string), joke);
        startActivity(myIntent);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


    public static class CustomAsyncTaskLoader extends
            AsyncTaskLoader<String> {
        private String mData;

        public CustomAsyncTaskLoader(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {

            forceLoad();

        }

        @Override
        public String loadInBackground() {
            String data = (new JokeGenerator()).fetchJokeFromCloud();
            return data;
        }

        @Override
        public void deliverResult(String data) {
            // We’ll save the data for later retrieval
            mData = data;
            super.deliverResult(mData);
        }
    }


}
