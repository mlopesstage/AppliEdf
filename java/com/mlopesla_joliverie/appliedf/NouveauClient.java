package com.mlopesla_joliverie.appliedf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Zemica on 03/10/2017.
 */

public class NouveauClient extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nouveau_client);

        Button btnEnregistrer = (Button) findViewById(R.id.buttonEnregistrer);
        btnEnregistrer.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                enregistreClient();
            }
        });

        Button btnRetour = (Button) findViewById(R.id.buttonRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Classe permettant l'enregistrement des clients
    public void enregistreClient() {
        BdClients clientBdd= new BdClients(this);
        clientBdd.open();
        //On récupère les valeurs saisies dans l'interface
        EditText nomsaisi = (EditText) findViewById(R.id.editNomClient);
        String nom = nomsaisi.getText().toString();
        EditText prenomsaisi = (EditText) findViewById(R.id.editPrenomClient);
        String prenom = prenomsaisi.getText().toString();
        EditText adressesaisi = (EditText) findViewById(R.id.textAdresseClient);
        String adresse = adressesaisi.getText().toString();
        //EditText emailsaisi = (EditText) findViewById(R.id.editTextEmail);
        //String email = emailsaisi.getText().toString();
        EditText telsaisi = (EditText) findViewById(R.id.textTelClient);
        String tel = telsaisi.getText().toString();

        //On stocke les données dans la table client
        Client nouveauClient = new Client(nom, prenom, adresse, /*email,*/ tel);
        clientBdd.insererClient(nouveauClient);
        Toast.makeText(getApplicationContext(), "Nouveau client enregistré", Toast.LENGTH_LONG).show();
    }
}