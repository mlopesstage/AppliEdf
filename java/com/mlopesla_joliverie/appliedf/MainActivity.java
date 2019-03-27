package com.mlopesla_joliverie.appliedf;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String type;
    final String EXTRA_ID="id_cli";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Appel du remplissage de la BDD
        //remplirBD();

        Button btnEnvoyer = (Button) findViewById(R.id.button_envoyer);
        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int CptHC1 = 1224;
                int CptHP1 = 568;
                EditText cpthc2saisi = (EditText) findViewById(R.id.compteurHC);
                EditText cpthp2saisi = (EditText) findViewById(R.id.compteurHP);
                int CptHC2 = Integer.parseInt(cpthc2saisi.getText().toString());
                int CptHP2 = Integer.parseInt(cpthp2saisi.getText().toString());
                enregistrerReleve();
                Toast.makeText(getApplicationContext(), "Consommation HC : " + Consommation(CptHC1, CptHC2)+ "\nConsommation HP : " + Consommation(CptHP1, CptHP2), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void enregistrerReleve() {
        //insertion d'un nouveau releve dans la base de données
        BdReleves BdReleves = new BdReleves(this);
        BdReleves.open();
        //on récupère les valeurs saisies dans l'interface
        EditText datereleveSaisi = (EditText) findViewById(R.id.dateReleve);
        String dateReleve = datereleveSaisi.getText().toString();

        EditText cptHCsaisi = (EditText) findViewById(R.id.compteurHC);
        String HC = cptHCsaisi.getText().toString();
        Integer cptHC = Integer.parseInt(HC);

        EditText cptHPsaisi = (EditText) findViewById(R.id.compteurHP);
        String HP = cptHPsaisi.getText().toString();
        Integer cptHP = Integer.parseInt(HP);

        EditText codeclisaisi = (EditText) findViewById(R.id.codeCli);
        String ccli = codeclisaisi.getText().toString();
        Integer codecli = Integer.parseInt(ccli);

        // On place un écouteur sur le groupe de boutons radios pour voir lequel est coché
        ((RadioGroup) findViewById(R.id.RadioGroup1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton typesaisi = (RadioButton) findViewById(checkedId);
                String type = typesaisi.getText().toString();
            }
        });

        //on stocke les données dans la table releve
        Releves nouveauReleve = new Releves(dateReleve, cptHC, cptHP, codecli, type);
        BdReleves.insererReleve(nouveauReleve);
        Toast.makeText(getApplicationContext(), " nouveau releve enregistré ", Toast.LENGTH_LONG).show();
    }

    //Classe permetant l'insertion de deux clients en brut dans la BDD
    public void remplirBD() {
        BdClients clientBdd = new BdClients(this);
        //Création de 2 nouveaux clients
        Client client1 = new Client("Duchamp", "Paul", "2 rue du champ 44000 Nantes", /*"paul@lol.fr",*/ "0256859658");
        Client client2 = new Client("Melice", "Claire", "4 champ eliser 44000 Nantes", /*"claire.bri@hotmail.fr",*/ "0652458596");
        clientBdd.open();
        clientBdd.insererClient(client1);
        clientBdd.insererClient(client2);
        System.out.println("insertion de 2 clients");
        Cursor c = clientBdd.getData();
        Toast.makeText(getApplicationContext(), " il y a " + String.valueOf(c.getCount()) + " clients ", Toast.LENGTH_LONG).show();
    }

    //Création d'un menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public int Consommation(int cptAncien, int cptNouv) {
        int consomme = cptNouv - cptAncien;
        return consomme;
    }

    //Création des sous-menu est affichage d'un message d'ouverture
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_secondaire_client:
                Toast.makeText(getApplicationContext(), "Ouverture de la fenêtre Nouveau Client !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, NouveauClient.class);
                startActivity(intent);
                return true;
            case R.id.menu_secondaire_existant:
                Toast.makeText(getApplicationContext(), "Ouverture de la fenêtre Client existant !", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(MainActivity.this, ClientExistant.class);
                startActivity(intent2);
                return true;
            case R.id.menu_principal_quitter:
                finish();
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            String s=data.getStringExtra(EXTRA_ID);
            final EditText idlu=(EditText) findViewById(R.id.codeCli);
            idlu.setText(s);
        }
    }
}