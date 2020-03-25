package despairify.model;

public class TargetTypes {
	protected int targetTypeId;
	protected String targetType;
	
	public TargetTypes(int targetTypeId, String targetType) {
		this.targetTypeId = targetTypeId;
		this.targetType = targetType;
	}
	
	public TargetTypes(String targetType) {
		this.targetType = targetType;
	}
	
	public int getTargetTypeId() {
		return targetTypeId;
	}

	public void setTargetTypeId(int targetTypeId) {
		this.targetTypeId = targetTypeId;
	}
	
	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
}
