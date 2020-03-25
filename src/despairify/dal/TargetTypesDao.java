package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying TargetTypes table in your MySQL
 * instance. This is used to store {@link Companies} into your MySQL instance and retrieve 
 * {@link Companies} from MySQL instance.
 */
public class TargetTypesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static TargetTypesDao instance = null;
	protected TargetTypesDao() {
		connectionManager = new ConnectionManager();
	}
	public static TargetTypesDao getInstance() {
		if(instance == null) {
			instance = new TargetTypesDao();
		}
		return instance;
	}

	public TargetTypes create(TargetTypes targetType) throws SQLException {
		String insertTargetType = "INSERT INTO TargetTypes(TargetType) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertTargetType,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, targetType.getTargetType());
			insertStmt.executeUpdate();
			
			
			resultKey = insertStmt.getGeneratedKeys();
			int targetTypeId = -1;
			if(resultKey.next()) {
				targetTypeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			targetType.setTargetTypeId(targetTypeId);
			
			return targetType;

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
	
	public TargetTypes updateTargetType(TargetTypes targetType, String newTargetType) throws SQLException {
		String updateTargetType = "UPDATE TargetTypes SET TargetType=? WHERE TargetTypeId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateTargetType);
			
			updateStmt.setString(1, newTargetType);
			updateStmt.setInt(2, targetType.getTargetTypeId());
			
			updateStmt.executeUpdate();
			
			targetType.setTargetType(newTargetType);
			
			return targetType;
			
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

	public TargetTypes delete(TargetTypes targetType) throws SQLException {
		String deleteTargetType = "DELETE FROM TargetTypes WHERE TargetTypeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTargetType);
			
			deleteStmt.setInt(1, targetType.getTargetTypeId());
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

	public TargetTypes getTargetTypeByTargetTypeId(int targetTypeId) throws SQLException {
		String selectTargetType = "SELECT * FROM TargetTypes WHERE TargetTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTargetType);
			selectStmt.setInt(1, targetTypeId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultTargetTypeId = results.getInt("TargetTypeId");
				String resultTargetType = results.getString("TargetType");

				TargetTypes targetType = new TargetTypes(resultTargetTypeId, resultTargetType);
				return targetType;
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
