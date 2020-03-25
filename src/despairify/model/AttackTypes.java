package despairify.model;

public class AttackTypes {
	protected int attackTypeId;
	protected String attackType;
	
	public AttackTypes(int attackTypeId, String attackType) {
		this.attackTypeId = attackTypeId;
		this.attackType = attackType;
	}
	
	public AttackTypes(String attackType) {
		this.attackType = attackType;
	}
	
	public int getAttackTypeId() {
		return attackTypeId;
	}

	public void setAttackTypeId(int attackTypeId) {
		this.attackTypeId = attackTypeId;
	}
	
	public String getAttackType() {
		return attackType;
	}

	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}
}
