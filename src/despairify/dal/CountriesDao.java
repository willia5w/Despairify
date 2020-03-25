package despairify.dal;

import despairify.model.Countries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesDao {

    protected ConnectionManager connectionManager;

    private static CountriesDao instance = null;

    public CountriesDao() {
        connectionManager = new ConnectionManager();
    }

    public static CountriesDao getInstance() {
        if (instance == null) {
            instance = new CountriesDao();
        }
        return instance;
    }

    public Countries create(Countries country) throws SQLException {
        String insertCountry = "INSERT INTO Countries(Country) VALUES(?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCountry);
            insertStmt.setString(1, country.getCountryAlpha3Code());
            insertStmt.setString(2, country.getCountryName());
            insertStmt.executeUpdate();
            return country;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Countries getCountryByAlpha3Code(String alpha3Code) throws SQLException {
        String selectCountry = "SELECT * FROM Countries WHERE Alpha3Code=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCountry);
            selectStmt.setString(1, alpha3Code);

            results = selectStmt.executeQuery();

            if(results.next()) {
                String resultAlpha3Code = results.getString("Alpha3Code");
                String resultCountryName = results.getString("CountryName");

                Countries country = new Countries(resultAlpha3Code, resultCountryName);
                return country;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
}
