package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data access object (DAO) class to interact with the underlying AgeRanges table in your MySQL
 * instance. This is used to store {@link Companies} into your MySQL instance and retrieve 
 * {@link Companies} from MySQL instance.
 */
public class AgeRangesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AgeRangesDao instance = null;
	protected AgeRangesDao() {
		connectionManager = new ConnectionManager();
	}
	public static AgeRangesDao getInstance() {
		if(instance == null) {
			instance = new AgeRangesDao();
		}
		return instance;
	}

	public AgeRanges create(AgeRanges ageRange) throws SQLException {
		String insertAgeRange = "INSERT INTO AgeRanges(AgeRange) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertAgeRange,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, ageRange.getAgeRange());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ageRangeId = -1;
			if(resultKey.next()) {
				ageRangeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			ageRange.setAgeRangeId(ageRangeId);
			
			return ageRange;

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
	
	public AgeRanges updateAgeRange(AgeRanges ageRange, String newAgeRange) throws SQLException {
		String updateAgeRange = "UPDATE AgeRanges SET AgeRange=? WHERE AgeRangeId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAgeRange);
			
			updateStmt.setString(1, newAgeRange);
			updateStmt.setInt(2, ageRange.getAgeRangeId());
			
			updateStmt.executeUpdate();
			
			ageRange.setAgeRange(newAgeRange);
			
			return ageRange;
			
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

	public AgeRanges delete(AgeRanges ageRange) throws SQLException {
		String deleteAgeRange = "DELETE FROM AgeRanges WHERE AgeRangeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAgeRange);
			
			deleteStmt.setInt(1, ageRange.getAgeRangeId());
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

	public AgeRanges getAgeRangeByAgeRangeId(int ageRangeId) throws SQLException {
		String selectAgeRange = "SELECT * FROM AgeRanges WHERE AgeRangeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAgeRange);
			selectStmt.setInt(1, ageRangeId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultAgeRangeId = results.getInt("AgeRangeId");
				String resultAgeRange = results.getString("AgeRange");

				AgeRanges ageRange = new AgeRanges(resultAgeRangeId, resultAgeRange);
				return ageRange;
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
	
	public AgeRanges getAgeRangeByAgeRange(String ageRange) throws SQLException {
		String selectAgeRange = "SELECT * FROM AgeRanges WHERE AgeRange=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAgeRange);
			selectStmt.setString(1, ageRange);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultAgeRangeId = results.getInt("AgeRangeId");
				String resultAgeRange = results.getString("AgeRange");

				AgeRanges resultAgeRanges = new AgeRanges(resultAgeRangeId, resultAgeRange);
				return resultAgeRanges;
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
