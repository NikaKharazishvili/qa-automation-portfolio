import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/game_accounts";
        String dbUser = "root";  // change if different
        String dbPassword = "nikson";  // change this!

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            System.out.println("Connected to database");

            // Test: Count number of records in 'accounts'
            String countQuery = "SELECT COUNT(*) FROM accounts";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(countQuery)) {

                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Total accounts found: " + count);
                    if (count == 25) {
                        System.out.println("Record count is correct");
                    } else {
                        System.out.println("Record count is unexpected");
                    }
                }
            }

            // Optional: Check for unique usernames
            String duplicateCheck = "SELECT username, COUNT(*) c FROM accounts GROUP BY username HAVING c > 1";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(duplicateCheck)) {

                boolean hasDuplicates = false;
                while (rs.next()) {
                    System.out.println("Duplicate username: " + rs.getString("username"));
                    hasDuplicates = true;
                }
                if (!hasDuplicates) {
                    System.out.println("All usernames are unique");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database connection or query failed");
            e.printStackTrace();
        }
    }
}