package com.example.brewbuddycs380;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private Connection connection;

    // Connection details for the MySQL server
    static final String URL = "jdbc:mysql://sql9619545@sql9.freemysqlhosting.net/sql9619545";
    static final String USER = "sql9619545";
    static final String PASS = "TALaShDLMD";

    // Constructor that establishes a connection to the MySQL server
    public UserService() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    // Method to create a new account with the given username and password
    public boolean createAccount(String username, String password) throws SQLException, NoSuchAlgorithmException {
        // Hash the password using the SHA-512 algorithm
        String hashedPassword = hashPassword(password);
        // SQL statement to insert a new record into the logins table
        String sql = "INSERT INTO sql9619545.logins (username, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, hashedPassword);
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }

    // Method to log in with the given username and password
    public boolean login(String username, String password) throws SQLException, NoSuchAlgorithmException {
        // Hash the password using the SHA-512 algorithm
        String hashedPassword = hashPassword(password);
        // SQL statement to query the logins table for a record with the given username and hashed password
        String sql = "SELECT * FROM sql9619545.logins WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, hashedPassword);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    // Helper method to hash a password using the SHA-512 algorithm
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        // Get an instance of the SHA-512 message digest
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());

        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        // Convert each byte of the message digest to a two-digit hexadecimal string
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        // Return the resulting hexadecimal string
        return sb.toString();
    }

    // Method to close the connection to the MySQL server
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
