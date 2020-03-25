package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Suicides table in your MySQL
 * instance. This is used to store {@link Suicides} into your MySQL instance and retrieve 
 * {@link Suicides} from MySQL instance.
 */
public class SuicidesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static SuicidesDao instance = null;
	protected SuicidesDao() {
		connectionManager = new ConnectionManager();
	}
	public static SuicidesDao getInstance() {
		if(instance == null) {
			instance = new SuicidesDao();
		}
		return instance;
	}

	public Suicides create(Suicides suicide) throws SQLException {
		String insertSuicidesStaging = 
		"INSERT INTO SuicidesStaging(CountryName, Year, Sex, Age, Suicides, Population) " +
		"VALUES(?, ?, ?, ?, ?, ?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertSuicidesStaging,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, suicide.getCountryName());
			insertStmt.setInt(2, suicide.getYear().getValue());
			insertStmt.setString(3, suicide.getSex().getSex());
			insertStmt.setString(4, suicide.getAgeRange().getAgeRange());
			insertStmt.setInt(5, suicide.getSuicides());
			insertStmt.setInt(6, suicide.getPopulation());	
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int suicideId = -1;
			if(resultKey.next()) {
				suicideId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			suicide.setSuicideId(suicideId);
			System.out.println("here's the suicide " + suicide.getSuicideId());
			
			return this.insertIntoSuicides(suicide);

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
	
	public Suicides insertIntoSuicides(Suicides suicide) throws SQLException {
		String insertSuicides = 
		"INSERT INTO Suicides (SuicideId, CountryName, Year, SexId, Suicides, Population, AgeRangeId) \n" +
		"VALUES(?, ?, ?, ?, ?, ?, ?); ";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertSuicides);

			insertStmt.setInt(1, suicide.getSuicideId());

			insertStmt.setString(2, suicide.getCountryName());
			
			insertStmt.setInt(3, suicide.getYear().getValue());
			SexesDao sexesDao = SexesDao.getInstance();
			AgeRangesDao ageRangesDao = AgeRangesDao.getInstance();
			
			Sexes sex = sexesDao.getSexBySex(suicide.getSex().getSex());
			AgeRanges ageRange = ageRangesDao.getAgeRangeByAgeRange(suicide.getAgeRange().getAgeRange());
			
			
			insertStmt.setInt(4, sex.getSexId());
			insertStmt.setInt(5, suicide.getSuicides());
			insertStmt.setInt(6, suicide.getPopulation());

			insertStmt.setInt(7, ageRange.getAgeRangeId());
			
			
			insertStmt.executeUpdate();
			
			System.out.println("here's the suicide " + suicide.getSuicideId());
			
			return suicide;

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
	
	public Suicides updateSuicides(Suicides suicide, int newSuicides) throws SQLException {
		String updateSuicides = "UPDATE Suicides SET Suicides=? WHERE SuicideId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSuicides);
			
			updateStmt.setInt(1, newSuicides);
			updateStmt.setInt(2, suicide.getSuicideId());
			
			updateStmt.executeUpdate();
			
			suicide.setSuicides(newSuicides);
			
			return suicide;
			
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

	public Suicides delete(Suicides suicides) throws SQLException {
		String deleteSuicides = "DELETE FROM Suicides WHERE SuicideId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSuicides);
			
			deleteStmt.setInt(1, suicides.getSuicideId());
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

	public Suicides getSuicideBySuicideId(int suicideId) throws SQLException {
		String selectSuicide = "SELECT * FROM Suicides WHERE SuicideId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSuicide);
			selectStmt.setInt(1, suicideId);

			results = selectStmt.executeQuery();
			
			SexesDao sexesDao = SexesDao.getInstance();
			AgeRangesDao ageRangesDao = AgeRangesDao.getInstance();
			
			if(results.next()) {
				int resultSuicideId = results.getInt("SuicideId");
				String countryName = results.getString("CountryName");
				int year = results.getInt("Year");
				Year resultYear = Year.of(year);
				int sexId = results.getInt("SexId");
				int suicides = results.getInt("Suicides");
				int population = results.getInt("Population");
				int ageRangeId = results.getInt("AgeRangeId");
				
				Sexes sex = sexesDao.getSexBySexId(sexId);
				AgeRanges ageRange = ageRangesDao.getAgeRangeByAgeRangeId(ageRangeId);
				
				Suicides suicide = new Suicides(
					resultSuicideId,
					countryName,
					resultYear,
					sex,
					suicides,
					population,
					ageRange
				);
				return suicide;
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
	
	public List<Suicides> getSuicidesByAgeRangeId(int ageRangeId) throws SQLException {
		List<Suicides> suicidesList = new ArrayList<Suicides>();
		String selectSuicide = "SELECT * FROM Suicides WHERE AgeRangeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSuicide);
			selectStmt.setInt(1, ageRangeId);

			results = selectStmt.executeQuery();
			
			SexesDao sexesDao = SexesDao.getInstance();
			AgeRangesDao ageRangesDao = AgeRangesDao.getInstance();
			
			while(results.next()) {
				int resultSuicideId = results.getInt("SuicideId");
				String countryName = results.getString("CountryName");
				int year = results.getInt("Year");
				Year resultYear = Year.of(year);
				int sexId = results.getInt("SexId");
				int suicides = results.getInt("Suicides");
				int population = results.getInt("Population");

				AgeRanges ageRange = ageRangesDao.getAgeRangeByAgeRangeId(ageRangeId);
				Sexes sex = sexesDao.getSexBySexId(sexId);
		
				Suicides suicide = new Suicides(
					resultSuicideId,
					countryName,
					resultYear,
					sex,
					suicides,
					population,
					ageRange
				);
				
				suicidesList.add(suicide);
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
		return suicidesList;
	}
}
