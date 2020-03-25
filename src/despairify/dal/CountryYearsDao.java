//Daniel English
// HOPEFULLY THIS SHOWS UP
package despairify.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


import despairify.model.*;


public class CountryYearsDao {
	protected ConnectionManager connectionManager;

	private static CountryYearsDao instance = null;
	protected CountryYearsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CountryYearsDao getInstance() {
		if(instance == null) {
			instance = new CountryYearsDao();
		}
		return instance;
	}

	public CountryYears create(CountryYears countryYear) throws SQLException {
		String insertCountryYears =
			"INSERT INTO CountryYears(CountryAlpha3Code, Year) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			// CountryYear has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertCountryYears,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, countryYear.getCountryAlpha3Code());
			insertStmt.setInt(2, countryYear.getYear().getValue());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int countryYearId = -1;
			if(resultKey.next()) {
				countryYearId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			countryYear.setCountryYearsId(countryYearId);
			return countryYear;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}


	/**
	 * Update the Alpha3Code of the CountryYears instance.
	 * This runs a UPDATE statement.
	 */
	public CountryYears updateAlpha3Code(CountryYears countryYear, String newAlpha3Code) throws SQLException {
		String updateCountryYear = "UPDATE CountryYears SET CountryAlpha3Code=? WHERE CountryYearId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCountryYear);
			
			updateStmt.setString(1, newAlpha3Code);
			updateStmt.setInt(2, countryYear.getYear().getValue());
			updateStmt.executeUpdate();
			
			// Update the country year parameter before returning to the caller.
			countryYear.setCountryAlpha3Code(newAlpha3Code);
			return countryYear;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	
	/**
	 * Get the all the CountryYear by Id.
	 */

	public CountryYears getCountryYearById(int countryYearId) throws SQLException {
		String selectCountryYears =
				"SELECT * FROM CountryYears " +
				"WHERE CountryYearId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCountryYears);
			selectStmt.setInt(1,  countryYearId);
			results = selectStmt.executeQuery();
			
			
			if(results.next()) {
                int resultCountryYearsId = results.getInt("CountryYearId");
                String resultCountryAlpha3Code = results.getString("CountryAlpha3Code");
                Year resultYear = Year.of(results.getInt("Year"));
				CountryYears countryYear = new CountryYears(resultCountryYearsId, resultCountryAlpha3Code, resultYear);
				return countryYear;
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

	public List<CountryYears> getAll()
			throws SQLException {
		List<CountryYears> countryYears = new ArrayList<CountryYears>();
		String selectCountryYears =
			"SELECT * " +
			"FROM CountryYears";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCountryYears);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultCountryYearsId = results.getInt("CountryYearId");
                String resultCountryAlpha3Code = results.getString("CountryAlpha3Code");
                Year resultYear = Year.of(results.getInt("Year"));
				CountryYears countryYear = new CountryYears(resultCountryYearsId, resultCountryAlpha3Code, resultYear);
				
				countryYears.add(countryYear);
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
		return countryYears;
	}
	
	public List<CountryYears> getCountryYearsByCountryAlpha3Code(String alphaCode) throws SQLException {
		List<CountryYears> countryYears = new ArrayList<CountryYears>();
		
		String selectCountryYears =
				"SELECT * FROM CountryYears " +
				"WHERE CountryAlpha3Code=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCountryYears);
			selectStmt.setString(1, alphaCode);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
                int resultCountryYearsId = results.getInt("CountryYearId");
                String resultCountryAlpha3Code = results.getString("CountryAlpha3Code");
                Year resultYear = Year.of(results.getInt("Year"));
				CountryYears countryYear = new CountryYears(resultCountryYearsId, resultCountryAlpha3Code, resultYear);
				
				countryYears.add(countryYear);
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
		return countryYears;
	}
	
	public List<CountryYears> getCountryYearsByYear(Year year) throws SQLException {
		List<CountryYears> countryYears = new ArrayList<CountryYears>();
		
		String selectCountryYears =
				"SELECT * FROM CountryYears " +
				"WHERE Year=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCountryYears);
			selectStmt.setInt(1, year.getValue());
			results = selectStmt.executeQuery();
			
			while(results.next()) {
                int resultCountryYearsId = results.getInt("CountryYearId");
                String resultCountryAlpha3Code = results.getString("CountryAlpha3Code");
                
				CountryYears countryYear = new CountryYears(resultCountryYearsId, resultCountryAlpha3Code, year);
				
				countryYears.add(countryYear);
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
		return countryYears;
	}
	
	/**
	 * Delete the CountryYears instance.
	 * This runs a DELETE statement.
	 */
	public CountryYears delete(CountryYears review) throws SQLException {
		String deleteCountryYear = "DELETE FROM CountryYears WHERE CountryYearId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCountryYear);
			deleteStmt.setInt(1, review.getCountryYearsId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
