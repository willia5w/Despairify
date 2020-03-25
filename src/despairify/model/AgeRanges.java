package despairify.model;

public class AgeRanges {
	protected int ageRangeId;
	protected String ageRange;
	
	public AgeRanges(int ageRangeId, String ageRange) {
		this.ageRangeId = ageRangeId;
		this.ageRange = ageRange;
	}
	
	public AgeRanges(String ageRange) {
		this.ageRange = ageRange;
	}
	
	public AgeRanges(int ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	
	public int getAgeRangeId() {
		return ageRangeId;
	}

	public void setAgeRangeId(int ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	
	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	
}
