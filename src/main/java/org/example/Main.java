package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/ipl_db";
    static final String USER = "root";
    static final String PASSWORD = "0000";
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            System.out.println("Connected to MySQL IPL database!");
            matchesPerYear(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void matchesPerYear(Connection conn) {

        String query = "SELECT season, COUNT(*) AS matches_played FROM matches GROUP BY season";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Matches per Year:");
            while (rs.next()) {
                System.out.println(rs.getInt("season") + " - " + rs.getInt("matches_played"));
            }
    } catch (SQLException e) {

            throw new RuntimeException(e);


        }
    }
}