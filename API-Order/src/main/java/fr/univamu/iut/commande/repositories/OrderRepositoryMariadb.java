package fr.univamu.iut.commande.repositories;

import fr.univamu.iut.commande.entities.Order;
import fr.univamu.iut.commande.entities.OrderLine;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implémentation JDBC MariaDB du dépôt de commandes.
 */
public class OrderRepositoryMariadb implements OrderRepositoryInterface, Closeable {
    protected Connection dbConnection;

    public OrderRepositoryMariadb(String infoConnection, String user, String pwd) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
        } catch (Exception e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try { if (dbConnection != null) dbConnection.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

    private ArrayList<OrderLine> parseLignesJson(String jsonLignes) {
        if (jsonLignes == null || jsonLignes.isEmpty()) return new ArrayList<>();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            OrderLine[] lignesArray = jsonb.fromJson(jsonLignes, OrderLine[].class);
            return new ArrayList<>(Arrays.asList(lignesArray));
        } catch (Exception e) { return new ArrayList<>(); }
    }

    @Override
    public Order getOrder(int id) {
        Order selectedOrder = null;
        String query = "SELECT * FROM Commande WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                selectedOrder = new Order(id, result.getInt("abonneId"), result.getString("dateCommande"),
                        result.getString("adresseLivraison"), result.getString("dateLivraison"),
                        result.getDouble("prixTotal"));
                selectedOrder.setLignes(parseLignesJson(result.getString("ligne")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return selectedOrder;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> listOrders = new ArrayList<>();
        String query = "SELECT * FROM Commande";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Order current = new Order(result.getInt("id"), result.getInt("abonneId"), result.getString("dateCommande"),
                        result.getString("adresseLivraison"), result.getString("dateLivraison"),
                        result.getDouble("prixTotal"));
                current.setLignes(parseLignesJson(result.getString("ligne")));
                listOrders.add(current);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return listOrders;
    }

    @Override public boolean addOrder(Order order) { return false; }
    @Override public boolean updateOrder(int id, Order order) { return false; }
    @Override public boolean deleteOrder(int id) { return false; }
}