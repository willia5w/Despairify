package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data access object (DAO) class to interact with the underlying AttackTypes table in your MySQL
 * instance. This is used to store {@link Companies} into your MySQL instance and retrieve 
 * {@link Companies} from MySQL instance.
 */
public class AttackTypesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AttackTypesDao instance = null;
	protected AttackTypesDao() {
		connectionManager = new ConnectionManager();
	}
	public static AttackTypesDao getInstance() {
		if(instance == null) {
			instance = new AttackTypesDao();
		}
		return instance;
	}

	public AttackTypes create(AttackTypes attackType) throws SQLException {
		String insertAttackType = "INSERT INTO AttackTypes(AttackType) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertAttackType,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, attackType.getAttackType());
			insertStmt.executeUpdate();
			
			
			resultKey = insertStmt.getGeneratedKeys();
			int attackTypeId = -1;
			if(resultKey.next()) {
				attackTypeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			attackType.setAttackTypeId(attackTypeId);
			
			return attackType;

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
	
	public AttackTypes updateAttackType(AttackTypes attackType, String newAttackType) throws SQLException {
		String updateAttackType = "UPDATE AttackTypes SET AttackType=? WHERE AttackTypeId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAttackType);
			
			updateStmt.setString(1, newAttackType);
			updateStmt.setInt(2, attackType.getAttackTypeId());
			
			updateStmt.executeUpdate();
			
			attackType.setAttackType(newAttackType);
			
			return attackType;
			
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

	public AttackTypes delete(AttackTypes attackType) throws SQLException {
		String deleteAttackType = "DELETE FROM AttackTypes WHERE AttackTypeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAttackType);
			
			deleteStmt.setInt(1, attackType.getAttackTypeId());
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

	public AttackTypes getAttackTypeByAttackTypeId(int attackTypeId) throws SQLException {
		String selectAttackType = "SELECT * FROM AttackTypes WHERE AttackTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttackType);
			selectStmt.setInt(1, attackTypeId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultAttackTypeId = results.getInt("AttackTypeId");
				String resultAttackType = results.getString("AttackType");

				AttackTypes attackType = new AttackTypes(resultAttackTypeId, resultAttackType);
				return attackType;
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
