package DAOs;
import DTOs.Countries;
import Exceptions.DaoExceptions;

import java.sql.*;
import java.util.ArrayList;

/**
 * Main author: Marketa Bila
 */
//establishing connection to the MySWL database and declares constants for the jdbc driver, database url, username and password to make that connection
public class MySqlDao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Countries";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    //this method establishes the connection to the mysql database
    public Connection getConnection() throws DaoExceptions {
        //loads the jdbc driver
        try {
            Class.forName(DRIVER);

            //establishes connection using provided url, username and a password and returns the connection
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

          //if an error happens it will throw an error message
        } catch (ClassNotFoundException | SQLException e) {
            throw new DaoExceptions("Failed to establish a database connection", e);
        }
    }

    //this method closes the database connection
    public void freeConnection(Connection connection) throws DaoExceptions {
        try {

            //it will check if it's not null and is not already closed
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

            //if an error occurs it will throw a message
        } catch (SQLException e) {
            throw new DaoExceptions("Failed to free database connection", e);
        }
    }

    /**
     * Main author: Daniel Ferrer
     */
    //Feature 1 - Get all entities
    //returns an arraylist of countries objects
    public ArrayList<Countries> getAllCountries() throws SQLException {
        ArrayList<Countries> countries = new ArrayList<>();

        //establishes a database connection using the getConnection method
        try (Connection connection = getConnection();

             //prepared sql statement
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Countries")) {

            //this executes the query, gets a resultSet object that has the query results
            try (ResultSet resultSet = statement.executeQuery()) {

                //goes through each row
                while (resultSet.next()) {

                    //constructs a countries object for each row in the result set
                    Countries country = new Countries(
                            resultSet.getInt("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Capital"),
                            resultSet.getInt("Population"),
                            resultSet.getString("Religion"),
                            resultSet.getDouble("Area")
                    );

                    //adds the constructed countries object to the countries array list
                    countries.add(country);
                }
            }
        }
        //returns the populated array list
        return countries;
    }

    /**
     * Main author: Marketa Bila
     */
    //Feature 2 - Find and Display a single Entity by Key
    //returns a countries object corresponding to the specified id
    public Countries getCountryById(int id) throws SQLException {

        //make a countries object called country which is null
        Countries country = null;

        //establishes database connection using the getConnection method
        try (Connection connection = getConnection();

             //prepared sql statement
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries WHERE id = ?")) {

            //sets the value of the parameter in the prepared statement to the provided id
            //that will fetch the country with the specified id
            statement.setInt(1, id);

            //executes the sql query and obtains the result set object containing the results
            try (ResultSet resultSet = statement.executeQuery()) {

                //checks if there is at least one row and if yes it constructs a Countries object
                if (resultSet.next()) {
                    country = new Countries(
                            resultSet.getInt("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Capital"),
                            resultSet.getInt("Population"),
                            resultSet.getString("Religion"),
                            resultSet.getDouble("Area")
                    );
                }
            }
        }

        return country;
    }

    /**
     * Main author: Daniel Ferrer
     */
    //Feature 3 - Delete an Entity by Key
    //it's void because it doesn't return anything
    //deletes a country based on the id
    public void deleteCountryById(int id) throws SQLException {

        //establishes database connection using the getConnection method
        try (Connection connection = getConnection();

             //prepared sql statement
             PreparedStatement statement = connection.prepareStatement("DELETE FROM countries WHERE id = ?")) {

            //sets a value of the parameter in the prepared statement to the provided id
            //specifies the id of the country to be deleted
            statement.setInt(1, id);

            //executes the statement and returns the number of rows affected by the operation
            int affectedRows = statement.executeUpdate();

            //checks the affected rows
            if (affectedRows == 0) {
                System.out.println("No country found with the given ID, no rows affected.");
            } else {
                System.out.println("Country deleted successfully.");
            }
        }
    }

    /**
     * Main author: Marketa Bila
     */
    //Feature 4 - Insert an Entity
    //doesn't return any value
    //inserts a country into the database based on the countries object
    public void insertCountry(Countries country) throws DaoExceptions {

        //establishes a database connection using the getConnection method
        try (Connection connection = getConnection();

             //prepared sql statement
             //it includes placeholders *?* for the values to be inserted
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO countries (Name, Capital, Population, Religion, Area) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            //setting prepared statement parameters
            statement.setString(1, country.getName());
            statement.setString(2, country.getCapital());
            statement.setInt(3, country.getPopulation());
            statement.setString(4, country.getReligion());
            statement.setDouble(5, country.getArea());

            //executes the insert statements and returns number of rows affected
            int affectedRows = statement.executeUpdate();

            //if no rows inserted
            if (affectedRows == 0) {
                throw new DaoExceptions("Failed to insert country, no rows affected.");
            }
            // if greater than 0, country was inserted
            //retrieves the generated key using the get generated keys statement to obtains the auto generated id of the inserted country
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {

                //if the id obtained from the generated keys, it sets the id of the countries object to the obtained id
                if (generatedKeys.next()) {
                    country.setId(generatedKeys.getInt(1));

                //if no id obtained it throws a dao exception with a message
                } else {
                    throw new DaoExceptions("Failed to insert country, no ID obtained.");
                }
            }

            //if an exception happens it will throw a message
        } catch (SQLException e) {
            throw new DaoExceptions("Failed to insert country", e);
        }
    }
}

