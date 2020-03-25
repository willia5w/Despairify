package despairify.model;

public class WeaponTypes {
	protected int weaponTypeId;
	protected String weaponType;
	
	public WeaponTypes(int weaponTypeId, String weaponType) {
		this.weaponTypeId = weaponTypeId;
		this.weaponType = weaponType;
	}
	
	public WeaponTypes(String weaponType) {
		this.weaponType = weaponType;
	}
	
	public int getWeaponTypeId() {
		return weaponTypeId;
	}

	public void setWeaponTypeId(int weaponTypeId) {
		this.weaponTypeId = weaponTypeId;
	}
	
	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
}
