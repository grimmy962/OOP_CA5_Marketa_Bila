
import java.sql.*;
import java.util.Scanner;
import DAOs.MySqlDao;

/**
 * Main author: Daniel Ferrer
 * Other contributors: Marketa Bila
 */
public class Main {
    private MySqlDao countryDao;

    public static void main(String[] args) throws SQLException{
        Main app = new Main();
        app.start();
    }

    public void start() throws SQLException {
        String url = "jdbc:mysql://localhost/";
        String dbName = "DTOs.Countries";
        String userName = "root";
        String password = "";
        boolean db = true;

        try (Connection conn = DriverManager.getConnection(url + dbName, userName, password)) {
            System.out.println("\nConnected to the database.");

            // Statements allow us to issue SQL queries to the database
            Statement statement = conn.createStatement();
        }
            Scanner scanner = new Scanner(System.in);
            String choice;

            while (db == true) {
                System.out.println("\nMenu:");
                System.out.println("1. Feature 1");
                System.out.println("2. Feature 2");
                System.out.println("3. Delete a country by ID");
                System.out.println("4. Feature 4");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int userChoice = scanner.nextInt();

                if (userChoice == 1) {
                    countryDao.getAllCountries();
                } else if (userChoice == 2) {
                    System.out.print("Enter the ID of the country to get: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    countryDao.getCountryById(id);
                } else if (userChoice == 3) {
                    System.out.print("Enter the ID of the country to delete: ");
                    int ID = scanner.nextInt();
                    scanner.nextLine();
                    countryDao.deleteCountryById(ID);
                } else if (userChoice == 4) {
                    countryDao.insertCountry();
                } else if (userChoice == 5) {
                    System.out.println("Exiting the program.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        }
    }