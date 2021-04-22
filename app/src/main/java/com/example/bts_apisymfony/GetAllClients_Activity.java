package com.example.bts_apisymfony;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetAllClients_Activity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_clients_);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //le contexte est l'instance (this) de l'activity APIActivity
        RequestQueue queue = Volley.newRequestQueue(GetAllClients_Activity.this);

        //url du service à consommer
        String url = "http://192.168.187.138:8082/bts-app/public/ApiAllClients";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //parcours des Clients retournés
                        //ArrayList<String> lesClients = new ArrayList<String>();
                        ArrayList <HashMap <String, String >> listAllClients = new ArrayList<HashMap<String, String>>();

                        SimpleAdapter adapter = new SimpleAdapter(GetAllClients_Activity.this, listAllClients, R.layout.custom_row_view_clients, new String[] {"email", "pseudo", "roles", "numTel", "nom", "prenom", "country", "photo_profil", "style_couleur_profil"}, new int[] {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9});
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);

                                String email = item.getString("email");
                                String pseudo = item.getString("pseudo");
                                String roles = item.getString("roles");
                                String numTel = item.getString("numTel");
                                String nom = item.getString("nom");
                                String prenom = item.getString("prenom");
                                String country = item.getString("country");
                                String photo_profil = item.getString("photo_profil");
                                String style_couleur_profil = item.getString("style_couleur_profil");


                                HashMap <String, String> temp = new HashMap <String, String> ();
                                temp.put("email", email);
                                temp.put("pseudo", pseudo);
                                temp.put("roles", roles);
                                temp.put("numTel", numTel);
                                temp.put("nom", nom);
                                temp.put("prenom", prenom);
                                temp.put("country", country);
                                temp.put("photo_profil", photo_profil);
                                temp.put("style_couleur_profil", style_couleur_profil);
                                listAllClients.add(temp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Remplissage de la listview
                        //ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(GetAllClients_Activity.this, android.R.layout.simple_list_item_1, lesClients);
                        setListAdapter(adapter);
                    }
                }, new Response.ErrorListener() {        // CAS d’ERREUR
            @Override
            public void onErrorResponse(VolleyError error) {
             // Aucun error msg
            }
        });
        queue.add(stringRequest);

    }

}