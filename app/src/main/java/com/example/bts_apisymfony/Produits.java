package com.example.bts_apisymfony;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

        btn_allProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "http://192.168.187.138:8082/bts-app/public/ApiAllProducts", Toast.LENGTH_SHORT).show();
                Intent intentActivityAllProducts = new Intent(v.getContext(), GetAllProducts_Activity.class);
                startActivity(intentActivityAllProducts);
            }
        });

        btn_allTendanceProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "http://192.168.187.138:8082/bts-app/public/ApiAllTendanceProducts", Toast.LENGTH_SHORT).show();
                Intent intentActivityAllTendanceProducts = new Intent(v.getContext(), GetAllTendance_Activity.class);
                startActivity(intentActivityAllTendanceProducts);
            }
        });

        btn_allRecentProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "http://192.168.187.138:8082/bts-app/public/ApiAllRecentProducts", Toast.LENGTH_SHORT).show();
                Intent intentActivityAllRecentProducts = new Intent(v.getContext(), GetAllRecentProducts_Activity.class);
                startActivity(intentActivityAllRecentProducts);
            }
        });

        btn_allComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "http://192.168.187.138:8082/bts-app/public/ApiAllCommingSoonProducts", Toast.LENGTH_SHORT).show();
                Intent intentActivityAllComingSoon = new Intent(v.getContext(), GetAllComingSoonProducts_Activity.class);
                startActivity(intentActivityAllComingSoon);
            }
        });




        // Inflate the layout for this fragment
        return root;
    }
}