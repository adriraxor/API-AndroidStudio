package com.example.bts_apisymfony;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Produits#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Produits extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Produits() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Produits.
     */
    // TODO: Rename and change types and number of parameters
    public static Produits newInstance(String param1, String param2) {
        Produits fragment = new Produits();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_produits, container, false);
        final Button btn_allProducts = root.findViewById(R.id.btn_getAllProducts);
        final Button btn_allTendanceProducts = root.findViewById(R.id.btn_getTendance);
        final Button btn_allRecentProducts = root.findViewById(R.id.btn_getRecent);
        final Button btn_allComingSoon = root.findViewById(R.id.btn_getComingSoon);
        final Button btn_nbProducts = root.findViewById(R.id.btn_getNbProducts);
        final Button btn_valueStock = root.findViewById(R.id.btn_getMontantStock);


        btn_allProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariablesGlobales vp = new VariablesGlobales();
                Toast allPrd = Toast.makeText(v.getContext(), "Fetch from =>" + vp.IPServeur, Toast.LENGTH_SHORT);
                allPrd.show();
                Intent intentActivityAllProducts = new Intent(v.getContext(), GetAllProducts_Activity.class);
                startActivity(intentActivityAllProducts);
            }
        });

        btn_allTendanceProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariablesGlobales vp = new VariablesGlobales();
                Toast tendancePrd = Toast.makeText(v.getContext(), "Fetch from =>" + vp.IPServeur, Toast.LENGTH_SHORT);
                tendancePrd.show();
                Intent intentActivityAllTendanceProducts = new Intent(v.getContext(), GetAllTendance_Activity.class);
                startActivity(intentActivityAllTendanceProducts);
            }
        });

        btn_allRecentProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariablesGlobales vp = new VariablesGlobales();
                Toast recentPrd = Toast.makeText(v.getContext(), "Fetch from =>"+ vp.IPServeur, Toast.LENGTH_SHORT);
                recentPrd.show();
                Intent intentActivityAllRecentProducts = new Intent(v.getContext(), GetAllRecentProducts_Activity.class);
                startActivity(intentActivityAllRecentProducts);
            }
        });

        btn_allComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariablesGlobales vp = new VariablesGlobales();
                Toast comingSoonPrd = Toast.makeText(v.getContext(), "Fetch from =>" + vp.IPServeur, Toast.LENGTH_SHORT);
                comingSoonPrd.show();
                Intent intentActivityAllComingSoon = new Intent(v.getContext(), GetAllComingSoonProducts_Activity.class);
                startActivity(intentActivityAllComingSoon);
            }
        });

        btn_nbProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //le contexte est l'instance (this) de l'activity APIActivity
                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                VariablesGlobales vp = new VariablesGlobales();

                //url du service à consommer
                String url = vp.IPServeur + vp.API_FetchApiCountTotalProducts;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //parcours des Clients retournés
                                ArrayList<String> leTotalPrd = new ArrayList<String>();
                                String nbVar = null;
                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject item = jsonArray.getJSONObject(i);
                                        String nb = item.getString("Nombre_de_produit");
                                        nbVar = nb;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                builder.setTitle("Nombre de produit total");
                                builder.setMessage(nbVar);
                                builder.setCancelable(true);
                                builder.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Fermeture de la boîte de dialogue
                                    }
                                });
                                builder.create();
                                builder.show();
                            }
                        }, new Response.ErrorListener() {        // CAS d’ERREUR
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(), "[NBPRODUITS] Une erreur à été détecté !", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);
            }
        });

        btn_valueStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //le contexte est l'instance (this) de l'activity APIActivity
                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                VariablesGlobales vp = new VariablesGlobales();

                //url du service à consommer
                String url = vp.IPServeur + vp.API_FetchApiValueStockProducts;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //parcours
                                String totalVar = null;
                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject item = jsonArray.getJSONObject(i);
                                        String valeur_stock = item.getString("valeur_stock");
                                        totalVar = valeur_stock;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                builder.setTitle("Valeur du stock");
                                builder.setMessage(totalVar + "€");
                                builder.setCancelable(true);
                                builder.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Fermeture de la boîte de dialogue
                                    }
                                });
                                builder.create();
                                builder.show();
                            }
                        }, new Response.ErrorListener() {        // CAS d’ERREUR
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(), "[NBPRODUITS] Une erreur à été détecté !", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);
            }
        });
        return root;
    }
}