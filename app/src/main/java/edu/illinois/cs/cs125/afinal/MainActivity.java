package edu.illinois.cs.cs125.afinal;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.Method.GET;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApplication:Main";

    ImageView image;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate ran");

        requestQueue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"Our app was created");

        final Button windmap = findViewById(R.id.windmap);

        final Button windspeed = findViewById(R.id.windspeed);
        windspeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Windspeed clicked");
                callWindSpeed();
            }
        });
        final Button direction = findViewById(R.id.direction);
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Direction clicked");
                callWindDegree();
            }
        });
        final Button gust = findViewById(R.id.gust);
        gust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Gust clicked");
                callWindGust();
            }
        });
        final Button update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Random button clicked");
                startAPICall();
            }
        });
        final TextView text = findViewById(R.id.textView);
        text.setVisibility(View.INVISIBLE);
    }

    public void browser(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.windy.com/?40.110,-88.212,5"));
        startActivity(browserIntent);
    }

    public void addListenerOnButton() {
        image = (ImageView) findViewById(R.id.imageView);
    }
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&appid=" + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                TextView textView = findViewById(R.id.textView);
                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                JsonParser jsonParser = new JsonParser();
                                JsonElement jsonElement = jsonParser.parse(response.toString());
                                JsonElement wind = jsonElement.getAsJsonObject().get("wind");
                                String prettyJsonString = gson.toJson(wind);
                                textView.setText(prettyJsonString);
                                textView.setVisibility(View.VISIBLE);
                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void callWindSpeed() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&appid=" + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                TextView textView = findViewById(R.id.textView);
                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                JsonParser jsonParser = new JsonParser();
                                JsonElement jsonElement = jsonParser.parse(response.toString());
                                JsonElement wind = jsonElement.getAsJsonObject().get("wind");
                                JsonElement speed = wind.getAsJsonObject().get("speed");
                                String prettyJsonString = gson.toJson(speed);
                                textView.setText(prettyJsonString);
                                textView.setVisibility(View.VISIBLE);
                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void callWindDegree() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&appid=" + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                TextView textView = findViewById(R.id.textView);
                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                JsonParser jsonParser = new JsonParser();
                                JsonElement jsonElement = jsonParser.parse(response.toString());
                                JsonElement wind = jsonElement.getAsJsonObject().get("wind");
                                JsonElement deg = wind.getAsJsonObject().get("deg");
                                String prettyJsonString = gson.toJson(deg);
                                textView.setText(prettyJsonString);
                                textView.setVisibility(View.VISIBLE);
                            } catch (JSONException ignored) {
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void callWindGust() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&appid=" + BuildConfig.API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString(2));
                                TextView textView = findViewById(R.id.textView);
                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                JsonParser jsonParser = new JsonParser();
                                JsonElement jsonElement = jsonParser.parse(response.toString());
                                JsonElement wind = jsonElement.getAsJsonObject().get("wind");
                                JsonElement gust = wind.getAsJsonObject().get("gust");
                                String prettyJsonString = gson.toJson(gust);
                                textView.setText(prettyJsonString);
                                textView.setVisibility(View.VISIBLE);
                            } catch (JSONException ignored) {
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}