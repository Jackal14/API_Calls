package com.example.assignment_7_agm.placeholder;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_7_agm.Model;
import com.example.assignment_7_agm.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Make the JSON Volley calls, convert the payload to a Model using Gson, and return the array of models back to the ItemListActivity, just like we kinda did with the MediaWeb assignment.
public class ModelContent {

    //List of our models that we'll build based on our payload using GSON
    public static final List<Model> MODELS = new ArrayList<>();
    public static final Map<String, Model> MODELS_MAP = new HashMap<>();

//    public static final List<ExerciseModel> EXERCISE_MODELS = new ArrayList<>();
//    public static final Map<String, ExerciseModel> EXERCISE_MODEL_MAP = new HashMap<>();

    private static boolean BUILT = false;

    public void setExerciseModels(Activity activity, TextView textView) {

        RequestQueue queue2 = Volley.newRequestQueue(activity);
        String url = "https://exercisedb.p.rapidapi.com/exercises/bodyPart/back";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textView.setText(response);
                        try{
                            JSONObject object = response.getJSONObject("");
                            String json = String.valueOf(response);
                            Gson gson = new Gson();
                            ExerciseModel model = gson.fromJson(json, ExerciseModel.class);

                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Failure Callback

                    }
                })
        {

            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("X-RapidAPI-Key", "cb23fb40dfmsh16ecbdf8fc74aefp18a4abjsnf49e635f4cc5");
                headers.put("X-RapidAPI-Host", "exercisedb.p.rapidapi.com");
                return headers;

            }
        };

                ;

        queue2.add(jsonObjReq);
    }


    public void jsonParse(Activity activity)
    {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = activity.getString(R.string.JSON_url);
        // Request a string response from the provided URL.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // NEXT, we need to use GSON to turn that JSON into a model
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("gameCompanies");
                            MODELS.clear();
                            MODELS_MAP.clear();
                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject gameCompany = jsonArray.getJSONObject(i);
                                String json = String.valueOf(gameCompany);
                                Gson gson = new Gson();
                                Model model = gson.fromJson(json, Model.class);

                                MODELS.add(model);
                                MODELS_MAP.put(model.getName(), model);
                                //String name = gameCompany.getString("name");
                                //Integer year = gameCompany.getInt("year");
                                //String recentConsole = gameCompany.getString("recentConsole");
                                //Model model = new Model(name, year, recentConsole);
                            }
                            if(!BUILT)
                            {
                                activity.recreate();
                            }
                            BUILT = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // you should drop a breakpoint RIGHT HERE if you need to see the error coming back
                error.printStackTrace();
            }
        });

        queue.add(objectRequest);
    }

}