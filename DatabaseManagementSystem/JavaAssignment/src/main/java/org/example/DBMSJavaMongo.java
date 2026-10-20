package org.example;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DBMSJavaMongo {

    private static final String URI         = "mongodb+srv://Ilya:Ilya@clusterdbms.g7lr7h5.mongodb.net/?appName=ClusterDBMS";
    private static final String DB_NAME     = "library_db";
    private static final String COLLECTION  = "books";

    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database   = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> books = database.getCollection(COLLECTION);

            System.out.println("Connected to MongoDB — database: " + DB_NAME + "\n");

            // ── INSERT ────────────────────────────────────────────────────────
            System.out.println("=== INSERT ===");
            addNewBook(books, "4", "NoSQL Database Design", "901234", "Emily Johnson", "Computer Science");

            // ── READ (all) ────────────────────────────────────────────────────
            System.out.println("\n=== READ (all) ===");
            readAllBooks(books);

            // ── READ (single) ─────────────────────────────────────────────────
            System.out.println("\n=== READ (single: 4) ===");
            readBookById(books, "4");

            // ── UPDATE ────────────────────────────────────────────────────────
            System.out.println("\n=== UPDATE ===");
            updateBook(books, "4", "Updated Title", "999999", "New Author", "IT");

            // ── READ after update ─────────────────────────────────────────────
            System.out.println("\n=== READ after UPDATE ===");
            readBookById(books, "4");

            // ── DELETE ────────────────────────────────────────────────────────
            System.out.println("\n=== DELETE ===");
            deleteBook(books, "4");

            // ── Confirm deletion ──────────────────────────────────────────────
            System.out.println("\n=== READ after DELETE ===");
            readBookById(books, "4");
        }
    }

    // ── INSERT ────────────────────────────────────────────────────────────────
    public static void addNewBook(MongoCollection<Document> collection,
                                  String BookID,
                                  String Title,
                                  String ISBN,
                                  String Authors,
                                  String Categories) {

        Document newBook = new Document("BookID", BookID)
                .append("Title", Title)
                .append("ISBN", ISBN)
                .append("Authors", Authors)
                .append("Categories", Categories);

        InsertOneResult result = collection.insertOne(newBook);
        System.out.println("Inserted document ID : " + result.getInsertedId());
        System.out.println("BookID           : " + BookID);
    }

    // ── READ (all) ────────────────────────────────────────────────────────────
    public static void readAllBooks(MongoCollection<Document> collection) {

        FindIterable<Document> docs = collection.find();

        int count = 0;
        for (Document doc : docs) {
            printBook(doc);
            count++;
        }
        System.out.println("Total documents: " + count);
    }

    // ── READ (single) ─────────────────────────────────────────────────────────
    public static void readBookById(MongoCollection<Document> collection,
                                    String BookID) {

        Bson filter = Filters.eq("BookID", BookID);
        Document doc = collection.find(filter).first();

        if (doc != null)
            printBook(doc);
        else
            System.out.println("No book found with BookID: " + BookID);
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public static void updateBook(MongoCollection<Document> collection,
                                  String BookID,
                                  String newTitle,
                                  String newISBN,
                                  String newAuthors,
                                  String newCategories) {

        Bson filter = Filters.eq("BookID", BookID);
        Bson update = Updates.combine(
                Updates.set("Title", newTitle),
                Updates.set("ISBN", newISBN),
                Updates.set("Authors", newAuthors),
                Updates.set("Categories", newCategories)
        );

        UpdateResult result = collection.updateOne(filter, update);

        if (result.getMatchedCount() > 0)
            System.out.println("Updated BookID  : " + BookID
                    + " | Modified count: " + result.getModifiedCount());
        else
            System.out.println("No book found with BookID: " + BookID);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    public static void deleteBook(MongoCollection<Document> collection,
                                  String BookID) {

        Bson filter = Filters.eq("BookID", BookID);
        DeleteResult result = collection.deleteOne(filter);

        if (result.getDeletedCount() > 0)
            System.out.println("Deleted BookID  : " + BookID);
        else
            System.out.println("No book found with BookID: " + BookID);
    }

    // ── Helper ────────────────────────────────────────────────────────────────
    private static void printBook(Document doc) {
        System.out.printf("ID: %-10s | Title: %-30s | ISBN: %-20s | Authors: %-20s | Categories: %s%n",
                doc.getString("BookID"),
                doc.getString("Title"),
                doc.getString("ISBN"),
                doc.getString("Authors"),
                doc.getString("Categories"));
    }
}