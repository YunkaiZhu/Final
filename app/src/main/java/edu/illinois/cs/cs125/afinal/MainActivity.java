package edu.illinois.cs.cs125.afinal;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreat ran");

        requestQueue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"Our app was created");

        final Button windmap = findViewById(R.id.windmap);
        windmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Windmap clicked");
            }
        });
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

}