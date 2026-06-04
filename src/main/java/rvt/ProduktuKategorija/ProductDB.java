package rvt.ProduktuKategorija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDB {
    private static final String DB_URL = "jdbc:sqlite:src/main/java/rvt/ProduktuKategorija/ProductDB.db";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public ProductDB() {
        initSchema();
    }

    private void initSchema() {
        String categoriesTable = "CREATE TABLE IF NOT EXISTS categories ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL"
                + ")";

        String productsTable = "CREATE TABLE IF NOT EXISTS products ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "price REAL NOT NULL,"
                + "category_id INTEGER NOT NULL,"
                + "FOREIGN KEY(category_id) REFERENCES categories(id)"
                + ")";

        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(categoriesTable);
            stmt.execute(productsTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCategory(String name) {
        String sql = "INSERT INTO categories(name) VALUES(?)";
        try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProduct(String name, double price, int categoryId) {
    String sql = "INSERT INTO products(name, price, category_id) VALUES(?, ?, ?)";

    try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, categoryId);
        ps.executeUpdate();
    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printCategories() {
        String sql = "SELECT id, name FROM categories";

        try (
                Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printProducts() {
        String sql = "SELECT products.id, products.name, products.price, categories.name AS category "
                + "FROM products "
                + "JOIN categories ON products.category_id = categories.id";

        try (
                Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - "
                        + rs.getString("name") + " - "
                        + rs.getDouble("price") + " EUR - "
                        + rs.getString("category")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printProductsByCategory(int categoryId) {
        String sql = "SELECT products.id, products.name, products.price, categories.name AS category "
                + "FROM products "
                + "JOIN categories ON products.category_id = categories.id "
                + "WHERE products.category_id = ?";

        try (
                Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " - "
                            + rs.getString("name") + " - "
                            + rs.getDouble("price") + " EUR - "
                            + rs.getString("category")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}