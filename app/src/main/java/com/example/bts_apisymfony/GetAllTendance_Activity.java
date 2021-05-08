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

public class GetAllTendance_Activity extends ListActivity {

    VariablesGlobales v = new VariablesGlobales();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_clients_);
    }

    @Override
    protected void onStart() {
        super.onStart();



        //le contexte est l'instance (this) de l'activity APIActivity
        RequestQueue queue = Volley.newRequestQueue(GetAllTendance_Activity.this);

        //url du service à consommer
        String url = v.IPServeur + v.API_FetchApiAllTendanceProducts;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //parcours des Clients retournés
                        //ArrayList<String> lesClients = new ArrayList<String>();
                        ArrayList <HashMap <String, String >> listAllProducts = new ArrayList<HashMap<String, String>>();

                        SimpleAdapter adapter = new SimpleAdapter(GetAllTendance_Activity.this, listAllProducts, R.layout.custom_row_view_products, new String[] {"nom", "libelle", "tarifProduit", "stock", "note_jeu", "nom_categorie", "date_apparition", "image"}, new int[] {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8});
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);

                                String nomPrd = item.getString("nom_produit");
                                String libelle = item.getString("libelle");
                                String tarifProduit = item.getString("tarif_produit");
                                String stock = item.getString("stock");
                                String note_jeu = item.getString("Note_Jeu");
                                String nomCategorie = item.getString("nom_categorie");
                                String date_apparition = item.getString("date_apparition");
                                String image = item.getString("image");

                                HashMap <String, String> temp = new HashMap <String, String> ();
                                temp.put("nom", nomPrd);
                                temp.put("libelle", libelle);
                                temp.put("tarifProduit", tarifProduit + " €");
                                temp.put("stock", "Stock Restant : " + stock);
                                temp.put("note_jeu", note_jeu);
                                temp.put("nom_categorie", "Catégorie : " + nomCategorie);
                                temp.put("date_apparition", date_apparition);
                                temp.put("image", image);
                                listAllProducts.add(temp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Remplissage de la listview
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