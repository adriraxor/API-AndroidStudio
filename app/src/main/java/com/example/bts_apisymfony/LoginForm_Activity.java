package com.example.bts_apisymfony;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class LoginForm_Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form_);
        VariablesGlobales v = new VariablesGlobales();

        Log.d("IpServeur", v.IPServeur);

        Button access_website = (Button) findViewById(R.id.btn_siteWeb);
        Button access_app = (Button) findViewById(R.id.btn_application);

        access_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VariablesGlobales vc = new VariablesGlobales();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(vc.IPServeur + "/"));
                startActivity(intent);
            }
        });

        access_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });





    }
}