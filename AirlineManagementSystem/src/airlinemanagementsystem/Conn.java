package airlinemanagementsystem;

import java.sql.*;

public class Conn {

    public Connection c; // allows access from other classes
    public Statement s;

    public Conn() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL Database
            c = DriverManager.getConnection("jdbc:mysql:///airlinemanagementsystem", "root", "Antor");

            // Create a statement for executing queries
            s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Optional: Get connection from outside (alternative to public variable)
    public Connection getConnection() {
        return c;
    }

    // Optional: Properly close resources
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}