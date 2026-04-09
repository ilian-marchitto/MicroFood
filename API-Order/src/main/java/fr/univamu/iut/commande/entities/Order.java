package fr.univamu.iut.commande.entities;

import java.util.ArrayList;

/**
 * Entité représentant une commande client.
 */
public class Order {
    protected int id;
    protected int abonneId;
    protected String dateCommande;
    protected String adresseLivraison;
    protected String dateLivraison;
    protected ArrayList<OrderLine> lignes;
    protected double prixTotal;

    public Order() {
        this.lignes = new ArrayList<>();
    }

    public Order(int id, int abonneId, String dateCommande, String adresseLivraison, String dateLivraison, double prixTotal) {
        this.id = id;
        this.abonneId = abonneId;
        this.dateCommande = dateCommande;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.prixTotal = prixTotal;
        this.lignes = new ArrayList<>();
    }

    public void addLigne(OrderLine ligne) { this.lignes.add(ligne); }
    public int getId() { return id; }
    public int getAbonneId() { return abonneId; }
    public String getDateCommande() { return dateCommande; }
    public String getAdresseLivraison() { return adresseLivraison; }
    public String getDateLivraison() { return dateLivraison; }
    public ArrayList<OrderLine> getLignes() { return lignes; }
    public double getPrixTotal() { return prixTotal; }

    public void setId(int id) { this.id = id; }
    public void setLignes(ArrayList<OrderLine> lignes) { this.lignes = lignes; }
    public void setPrixTotal(double prixTotal) { this.prixTotal = prixTotal; }
}