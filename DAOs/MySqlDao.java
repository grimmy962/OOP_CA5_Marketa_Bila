package DAOs;

import DTOs.Countries;
import Exceptions.DaoExceptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class MySqlDao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Countries";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() throws DaoExceptions {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DaoExceptions("Failed to establish a database connection", e);
        }

        return connection;
    }

    public void freeConnection(Connection connection) throws DaoExceptions {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoExceptions("Failed to free database connection", e);
        } finally {
            // Ensure the connection is closed even if an exception occurs
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Log or handle the exception as needed
                throw new DaoExceptions("Failed to close database connection", e);
            }
        }
    }

    public Countries getCountriesById (int id) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries where id = ?");
        ResultSet resultSet = statement.executeQuery();
        statement.setInt(1, id); // Set the value of the parameter in the prepared statement
        Countries country = null;

        if(resultSet.next()){
            country = new Countries();
            country.setId(resultSet.getInt("ID"));
            country.setName(resultSet.getString("Name"));
            country.setCapital(resultSet.getString("Capital"));
            country.setPopulation(resultSet.getInt("Population"));
            country.setReligion(resultSet.getString("Religion"));
            country.setArea(resultSet.getDouble("Area"));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return country;
    }
}

