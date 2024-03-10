
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() throws SQLException {
        String url = "jdbc:mysql://localhost/";
        String dbName = "DTOs.Countries";
        String userName = "root";
        String password = "";

        try( Connection conn = DriverManager.getConnection(url + dbName, userName, password) )
        {
            System.out.println("\nConnected to the database.");

            // Statements allow us to issue SQL queries to the database
            Statement statement = conn.createStatement();


    }

        void start() {
        return;
        }
        }
}
