import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    private String dbName = "ImmoInhabDB";
    private String tableName1 = "`Immobilie`";
    private String tableName2 = "`Inhaber`";

    private Connection connection;

    static {
        try {
            Class<?> c = Class.forName("com.mysql.jdbc.Driver");
            if (c != null) {
                System.out.println("JDBC-Treiber geladen");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            System.exit(1);
        }
    }

    public CreateDB() {
        createConnection();
        createDBStructure();
        Thread shutDownHook = new Thread() {
            public void run() {
                System.out.println("Running shutdown hook");
                if(connection == null) System.out.println("Connedtion to database already closed");
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to database closed");
                    }
                } catch (SQLException e) {
                    System.err.println(
                            "Shutdown hook couldn't close database connection.");
                }
            }
        };
        Runtime.getRuntime().addShutdownHook(shutDownHook);
    }

    private void createConnection() {
        String url = "jdbc:mysql://localhost/?rewriteBatchedStatements=true";
        String user = "root";
        String pass = "";
        try {
            System.out.println("Creating DBConnection");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.err.println("Couldn't create DBConnection");
            System.exit(1);
        }
    }

    private boolean createDBStructure(String dbName, String tableName1, String tableName2) {

        String query0 = "CREATE DATABASE IF NOT EXISTS `" + dbName + "`";

        String query1 = "USE `" + dbName + "`";

        String query2 = "SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO'; ";

        String query3 = "CREATE TABLE IF NOT EXISTS " + tableName1
                + "`Straße` varchar(100) NOT NULL DEFAULT '', "
                + "`Hausnummer` varchar(100) NOT NULL DEFAULT '', "
                + "`Ort` varchar(100) NOT NULL DEFAULT '', "
                + "`PLZ` varchar(100) NOT NULL DEFAULT '', "
                + "`Art` varchar(100) NOT NULL DEFAULT '', "
                + "`Inhaber` varchar(100) NOT NULL DEFAULT '', "
                + "`ID` varchar(50) NOT NULL, " + "UNIQUE KEY `ID` (`ID`)"
                + ") ENGINE=MyISAM DEFAULT CHARSET=utf8 "
                + "DEFAULT COLLATE=utf8_german2_ci";

        String query4 = "CREATE TABLE IF NOT EXISTS " + tableName2
                + "`Name` varchar(100) NOT NULL DEFAULT '', "
                + "`Vorname` varchar(100) NOT NULL DEFAULT '', "
                + "`ID` varchar(50) NOT NULL, " + "UNIQUE KEY `ID` (`ID`)"
                + ") ENGINE=MyISAM DEFAULT CHARSET=utf8 "
                + "DEFAULT COLLATE=utf8_german2_ci";



        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.addBatch(query0);
            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            stmt.addBatch(query4);

            for(int i = 0; i < 5; i++) {
                String fillDBQuery1 = "INSERT INTO " + tableName1 + " (Platzhalter Straße, " + i + ", Altstadt, 12345, Wohnhaus, " + i + ")";
                stmt.addBatch(fillDBQuery1);
                String fillDBQuery2 = "INSERT INTO " + tableName2 + " (Mustermann_" + i + ", Max)";
                stmt.addBatch(fillDBQuery2);
            }

            stmt.executeBatch();
            connection.commit();
            stmt.close();
            connection.close();
            System.out.println("Database successfully created or just existing");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new CreateDB();
    }
} 