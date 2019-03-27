package com.mlopesla_joliverie.appliedf;

/**
 * Created by Zemica on 10/10/2017.
 */

//Classe permettant le lien entre la table de la base de données et le code java
class Client {
    protected String nom;
    protected String prenom;
    protected String adresse;
    //protected String email;
    protected String telephone;

    //Constructeur
    public Client(String unNom, String unPrenom, String uneAdresse, /*String unEmail,*/ String unTelephone) {
        super();
        this.nom=unNom;
        this.prenom=unPrenom;
        this.adresse=uneAdresse;
        //this.email=unEmail;
        this.telephone=unTelephone;
    }

    //Accesseurs et mutateurs
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    /*public String getEmail() {
        return email;
    }*/

    public String getTelephone() {
        return telephone;
    }
}