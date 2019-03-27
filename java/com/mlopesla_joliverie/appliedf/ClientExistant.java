package com.mlopesla_joliverie.appliedf;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zemica on 03/10/2017.
 */

//Classe permettant l'affichage des clients existant dans la BDD
public class ClientExistant extends Activity {

    final String EXTRA_ID="id_cli";
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_existant);

        BdClients clientBdd=new BdClients(this);
        clientBdd.open();
        Cursor c = clientBdd.getData();
        String [] colonnes = new String[] {BdClients.COL_NOM, BdClients.COL_PREN, BdClients.COL_ADR, /*BdClients.COL_EMAIL,*/ BdClients.COL_TEL};
        int [] to = new int[] {R.id.textViewNom, R.id.textViewPren, R.id.textViewAdr, /*R.id.textViewEmail,*/ R.id.textViewTel};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this,R.layout.client_existant,c,colonnes,to);

        /*List<String> clients = new ArrayList<String>();
        clients.add("1 Contant Nelly");
        clients.add("2 Ghaddar Sami");
        clients.add("3 Bourgeois Nicolas");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clients);*/

        ListView liste = (ListView) findViewById(R.id.listViewCli);
        TextView titre =new TextView(this);
        titre.setText("liste des clients");
        //permet d'afficher une entête à la liste des clients
        liste.addHeaderView(titre);
        liste.setAdapter(dataAdapter);
        clientBdd.close();


        // Gestion des clics sur les lignes
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // faites ici ce que vous voulez
                Toast.makeText(getApplicationContext(), " vous avez selectionné le "+id+" client ", Toast.LENGTH_LONG).show();
                Intent i1=new Intent();
                //pour transformer le long en string
                String numcli=id+"";
                i1.putExtra(EXTRA_ID,numcli);
                setResult(RESULT_OK,i1);
                finish();
            }};

        // Utilisation avec notre listview
        liste.setOnItemClickListener(itemClickListener);

        Button btnRetourReleveCompteur = (Button) findViewById(R.id.buttonRetourReleveCompteur);
        btnRetourReleveCompteur.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                finish();
            }
        });
    }
}