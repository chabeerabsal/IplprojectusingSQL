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
            matchesWonAllyearAllteam(conn);
            extraRunsConceededperteam(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static void extraRunsConceededperteam(Connection conn) {
        String query="Select bowling_team,Count(extra_runs) as extra_runs from deliveries join matches on deliveries.match_id=matches.id where season=\"2016\" group by bowling_team order by extra_runs desc ";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nExtra runs conceeded!");
            while (rs.next()) {
                System.out.println(rs.getString("bowling_team") + " - " + rs.getInt("extra_runs"));
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);



        }
    }

    private static void matchesWonAllyearAllteam(Connection conn) {
        String query="Select winner,Count(*) As total_count from matches where winner!=\"\" group by winner";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Matches Win");
            while (rs.next()) {
                System.out.println(rs.getString("winner") + " - " + rs.getInt("total_count"));
            }
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