package com.mlopesla_joliverie.appliedf;

/**
 * Created by Zemica on 14/11/2017.
 */

//Classe permettant le lien entre la table de la base de données et le code java
class Releves {
    protected String dateReleve;
    protected Integer codeCli;
    protected Integer cptHP;
    protected Integer cptHC;
    protected String typeReleve;

    public Releves(String dateReleve, Integer codeCli, Integer cptHP, Integer cptHC, String typeReleve) {
        super();
        this.dateReleve=dateReleve;
        this.codeCli=codeCli;
        this.cptHC=cptHC;
        this.cptHP=cptHP;
        this.typeReleve=typeReleve;
    }

    //Accesseurs
    public String getDateReleve() {
        return dateReleve;
    }

    public String getCodeCli() {
        return Integer.toString(codeCli);
    }

    public String getCptHP() {
        return Integer.toString(cptHP);
    }

    public String getCptHC() {
        return Integer.toString(cptHC);
    }

    public String getTypeReleve() {
        return typeReleve;
    }
}