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

        final CheckBox american = findViewById(R.id.American);
        american.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "American check box clicked");
            }
        });
        final CheckBox chinese = findViewById(R.id.Chinese);
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Chinese check box clicked");
            }
        });
        final CheckBox korean = findViewById(R.id.Korean);
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Korean check box clicked");
            }
        });
        final CheckBox italian = findViewById(R.id.Italian);
        italian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Italian check box clicked");
            }
        });
        final CheckBox mexican = findViewById(R.id.Mexican);
        mexican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Mexican check box clicked");
            }
        });
        final CheckBox thai = findViewById(R.id.Thai);
        thai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Thai check box clicked");
            }
        });
        final Button random = findViewById(R.id.random);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Random button clicked");
                startAPICall();
            }
        });
        final TextView text = findViewById(R.id.textView);
    }
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "api.yelp.com/v3"
                            + BuildConfig.API_KEY,
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
                                String prettyJsonString = gson.toJson(jsonElement);
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