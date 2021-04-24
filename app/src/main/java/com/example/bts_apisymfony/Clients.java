package com.example.bts_apisymfony;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Clients#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Clients extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Clients() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Clients.
     */
    // TODO: Rename and change types and number of parameters
    public static Clients newInstance(String param1, String param2) {
        Clients fragment = new Clients();
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

        View root = inflater.inflate(R.layout.fragment_clients, container, false);
        final Button btnClient = root.findViewById(R.id.btn_getAllClients);
        final Button btn_getNbClients = root.findViewById(R.id.btn_getNbClients);

        btnClient.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "http://192.168.187.136:8082/bts-app/public/ApiAllProducts", Toast.LENGTH_SHORT).show();
                Intent intentActivityAllClients = new Intent(v.getContext(), GetAllClients_Activity.class);
                startActivity(intentActivityAllClients);
            }
        });

        btn_getNbClients.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                //le contexte est l'instance (this) de l'activity APIActivity
                RequestQueue queue = Volley.newRequestQueue(v.getContext());

                //url du service à consommer
                String url = "http://192.168.187.138:8082/bts-app/public/ApiNbClientsInscrits";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //parcours des Clients retournés
                                ArrayList<String> leNbClient = new ArrayList<String>();
                                String nbCVar = null;
                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject item = jsonArray.getJSONObject(i);
                                        String nbC = item.getString("nb_client");
                                        nbCVar = nbC;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                builder.setTitle("Nombre de clients inscrits");
                                builder.setMessage(nbCVar);
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

        // Inflate the layout for this fragment
        return root;
    }
}