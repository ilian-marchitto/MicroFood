package fr.univamu.iut.commande.entities;

/**
 * Entité représentant une ligne de menu au sein d'une commande.
 */
public class OrderLine {
    protected int menuId;
    protected String menuNom;
    protected int quantite;
    protected double prixUnitaire;
    protected double prixLigne;

    public OrderLine() {}

    public OrderLine(int menuId, String menuNom, int quantite, double prixUnitaire, double prixLigne) {
        this.menuId = menuId;
        this.menuNom = menuNom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixLigne = prixLigne;
    }

    public int getMenuId() { return menuId; }
    public String getMenuNom() { return menuNom; }
    public int getQuantite() { return quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public double getPrixLigne() { return prixLigne; }

    public void setMenuNom(String menuNom) { this.menuNom = menuNom; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public void setPrixLigne(double prixLigne) { this.prixLigne = prixLigne; }
}