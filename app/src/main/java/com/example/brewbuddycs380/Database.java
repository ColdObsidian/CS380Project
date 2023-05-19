package com.example.brewbuddycs380;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Database {
    public static void main(String[] args) {
        System.out.println("Check: "+checkConnect()!=null);
        System.out.println("can create? " + createUser("steiner","aoeu"));
        System.out.println("log in? " + verifyCredentials("steiner","aoeu"));


    }
    private static Connection con = null;
    /**
     * connects to the database
     */
    public static void connect() {
        String url = "jdbc:mysql://USERNAME@sql9.freemysqlhosting.net/USERNAME";
        String userName = "USERNAME";
        String pass = "PASSWORD";

        try {
            con = DriverManager.getConnection(url, userName, pass);
            System.out.println("connected");


        } catch (Exception e) {
            System.out.println("exception " + e.getMessage());
        }
    }

    /**
     * checks if connect, and if not, starts a new connection to the database. Has the connection be a singleton.
     * @return returns the connection to the database
     */
    public static Connection checkConnect(){
        if(con==null) connect();
        return con;
    }

    /**
     * returns true if the username corresponds to a username in the database, and the hash of the password matches
     * @param username
     * @param password
     * @return
     */
    public static boolean verifyCredentials(String username, String password){
        try {
            ResultSet res = executeQuery("SELECT * from logins WHERE username='"+username+"';","login ");
            res.next();
            //System.out.println("Password is: "+ sha256);
            if(!res.getString("password").equals(sha256(password))) return false;
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     * makes a user with username username and password password
     * returns false if such a user already exists of if the username/password is invalid
     * puts entries into the database under the logins table with the username and password
     * @param username
     * @param password
     * @return
     */
    public static boolean createUser(String username, String password){

        //sql injection prevention
        if(username.contains(";")||password.contains(";")||username.contains("(")||password.contains("(")||username.contains(")")||password.contains(")")||username.contains("'")||password.contains("'")) return false;
        try {
            ResultSet res = executeQuery("SELECT * from logins WHERE username='"+username+"';","getuser");

            //if user already exists with same name, can't create user
            if(res.next()) return  false;

            executeUpdate("INSERT INTO logins (username, password) VALUES('"+username+"', '"+sha256(password)+"');","insertuser");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

    /**
     * returns the string of the hash encoded in base64
     * @param s string to hash
     * @return
     */
    public static String sha256(String s) {
        try {
            MessageDigest md =  MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
            return String.format("%064x",new BigInteger(1, hashBytes));
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * executes a MSQL query of query and prints what the query is optionally
     * @param query
     * @param printQueryBeforeExecuting
     * @return
     */
    public static ResultSet executeQuery(String query, String printQueryBeforeExecuting) {
        try {
            if(printQueryBeforeExecuting!=null) System.out.println(printQueryBeforeExecuting+" Query is: "+ query);
            ResultSet res = con.createStatement().executeQuery(query);
            return res;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * executes an update of the mysql database with the query query.
     * @param query
     * @param printQueryBeforeExecuting
     */
    public static void executeUpdate(String query, String printQueryBeforeExecuting) {
        try {
            if(printQueryBeforeExecuting!=null) System.out.println(printQueryBeforeExecuting+" Query is: "+ query);
            con.createStatement().executeUpdate(query);



        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    public static void executeUpdate(String query) {
        executeUpdate(query,null);
    }
    public static ResultSet executeQuery(String query) {
        return executeQuery(query,null);
    }

}