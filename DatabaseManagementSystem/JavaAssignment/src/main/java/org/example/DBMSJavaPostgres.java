package org.example;

import java.sql.*;

public class DBMSJavaPostgres {

    private static final String URL  = "jdbc:postgresql://localhost:5432/library_db";
    private static final String USER = "postgres";
    private static final String PASS = "00000000";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.println("Connected to PostgreSQL — database: library_db\n");

            // ── INSERT ───────────────────────────────────────────────
            System.out.println("=== INSERT ===");
            addNewBook(conn, 4, "NoSQL Database Design", "901234", "Emily Johnson", "Computer Science");

            // ── READ ALL ──────────────────────────────────────────────
            System.out.println("\n=== READ (all) ===");
            readAllBooks(conn);

            // ── READ ONE ─────────────────────────────────────────────
            System.out.println("\n=== READ (single: 4) ===");
            readBookById(conn, 4);

            // ── UPDATE ────────────────────────────────────────────────
            System.out.println("\n=== UPDATE ===");
            updateBook(conn, 4, "Updated Title", "999999", "New Author", "IT");

            // ── READ AFTER UPDATE ────────────────────────────────────
            System.out.println("\n=== READ after UPDATE ===");
            readBookById(conn, 4);

            // ── DELETE ───────────────────────────────────────────────
            System.out.println("\n=== DELETE ===");
            deleteBook(conn, 4);

            // ── VERIFY DELETE ─────────────────────────────────────────
            System.out.println("\n=== READ after DELETE ===");
            readBookById(conn, 4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ── INSERT ─────────────────────────────────────────────────────
    public static void addNewBook(Connection conn,
                                  int bookId,
                                  String title,
                                  String isbn,
                                  String authors,
                                  String categories) throws SQLException {

        String sql = "INSERT INTO books (bookid, title, isbn, authors, categories) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setString(2, title);
            stmt.setString(3, isbn);
            stmt.setString(4, authors);
            stmt.setString(5, categories);

            int rows = stmt.executeUpdate();
            System.out.println("Inserted rows: " + rows + " | BookID: " + bookId);
        }
    }

    // ── READ ALL ───────────────────────────────────────────────────
    public static void readAllBooks(Connection conn) throws SQLException {

        String sql = "SELECT * FROM books";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int count = 0;

            while (rs.next()) {
                printBook(rs);
                count++;
            }

            System.out.println("Total rows: " + count);
        }
    }

    // ── READ ONE ───────────────────────────────────────────────────
    public static void readBookById(Connection conn, int bookId) throws SQLException {

        String sql = "SELECT * FROM books WHERE bookid = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    printBook(rs);
                } else {
                    System.out.println("No book found with BookID: " + bookId);
                }
            }
        }
    }

    // ── UPDATE ─────────────────────────────────────────────────────
    public static void updateBook(Connection conn,
                                  int bookId,
                                  String newTitle,
                                  String newISBN,
                                  String newAuthors,
                                  String newCategories) throws SQLException {

        String sql = """
                UPDATE books
                SET title = ?, isbn = ?, authors = ?, categories = ?
                WHERE bookid = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newTitle);
            stmt.setString(2, newISBN);
            stmt.setString(3, newAuthors);
            stmt.setString(4, newCategories);
            stmt.setInt(5, bookId);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("Updated BookID: " + bookId + " | Rows affected: " + rows);
            else
                System.out.println("No book found with BookID: " + bookId);
        }
    }

    // ── DELETE ─────────────────────────────────────────────────────
    public static void deleteBook(Connection conn, int bookId) throws SQLException {

        String sql = "DELETE FROM books WHERE bookid = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("Deleted BookID: " + bookId);
            else
                System.out.println("No book found with BookID: " + bookId);
        }
    }

    // ── HELPER ─────────────────────────────────────────────────────
    private static void printBook(ResultSet rs) throws SQLException {

        System.out.printf(
                "ID: %-5d | Title: %-30s | ISBN: %-10s | Authors: %-20s | Categories: %s%n",
                rs.getInt("bookid"),
                rs.getString("title"),
                rs.getString("isbn"),
                rs.getString("authors"),
                rs.getString("categories")
        );
    }
}