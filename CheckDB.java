import java.sql.*;

public class CheckDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/coffee_db";
        String user = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to DB");
            
            System.out.println("--- USERS ---");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, email, role, password, status FROM users")) {
                while (rs.next()) {
                    System.out.println("--- USER START ---");
                    System.out.println("ID: " + rs.getLong("id"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Role: " + rs.getString("role"));
                    System.out.println("Status: " + rs.getString("status"));
                    System.out.println("Password: " + rs.getString("password"));
                    System.out.println("--- USER END ---");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
