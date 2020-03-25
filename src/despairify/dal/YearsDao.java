package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Years table in your MySQL
 * instance. This is used to store {@link Companies} into your MySQL instance and retrieve 
 * {@link Companies} from MySQL instance.
 */
public class YearsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static YearsDao instance = null;
	protected YearsDao() {
		connectionManager = new ConnectionManager();
	}
	public static YearsDao getInstance() {
		if(instance == null) {
			instance = new YearsDao();
		}
		return instance;
	}

	public Years create(Years year) throws SQLException {
		String insertYear = "INSERT INTO Years(Year) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertYear);

			insertStmt.setInt(1, year.getYear().getValue());
			insertStmt.executeUpdate();
			
			return year;

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
		}
	}
	

	public Years delete(Years year) throws SQLException {
		String deleteYear = "DELETE FROM Years WHERE YearId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteYear);
			
			deleteStmt.setInt(1, year.getYear().getValue());
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

	public Years getYearByYear(short yearSearch) throws SQLException {
		String selectYear = "SELECT * FROM Years WHERE Year=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectYear);
			selectStmt.setShort(1, yearSearch);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultYear = results.getInt("Year");
				Year resultYearConverted = Year.of(resultYear);
				Years year = new Years(resultYearConverted);
				return year;
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


	public List<Years> getAll()
			throws SQLException {
		List<Years> years = new ArrayList<Years>();
		String selectYears =
			"SELECT * " +
			"FROM Years";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectYears);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultYear = results.getInt("Year");
				Year resultYearConverted = Year.of(resultYear);
				Years year = new Years(resultYearConverted);
				
				years.add(year);
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
		return years;
	}
}
