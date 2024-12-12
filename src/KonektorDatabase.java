import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KonektorDatabase {
    private static final String DB_URL = "jdbc:sqlite:database.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Koneksi ke database berhasil!");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi ke database ditutup!");
            }
        } catch (SQLException e) {
            System.out.println("Error saat menutup koneksi: " + e.getMessage());
        }
    }
}
