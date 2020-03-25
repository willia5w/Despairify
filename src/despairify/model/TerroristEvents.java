package despairify.model;

public class TerroristEvents {
	int EventId;
    int Year;
    int Month;
    int Day;
    String CountryName;
    String Region;
    String ProvState;
    String City;
    String Latitude;
    String Longitude;
    int Suicide;
    int AttackTypeId;
    int TargetTypeId;
    String Target;
    String Nationality;
    int WeaponTypeId;
    
    public TerroristEvents(
    	    int Year,
    	    int Month,
    	    int Day,
    	    String CountryName,
    	    String Region,
    	    String ProvState,
    	    String City,
    	    String Latitude,
    	    String Longitude,
    	    int Suicide,
    	    int AttackTypeId,
    	    int TargetTypeId,
    	    String Target,
    	    String Nationality,
    	    int WeaponTypeId) {
    		    this.Year = Year;
    		    this.Month = Month;
    		    this.Day = Day;
    		    this.CountryName = CountryName;
    		    this.Region = Region;
    		    this.ProvState = ProvState;
    		    this.City = City;
    		    this.Latitude = Latitude;
    		    this.Longitude = Longitude;
    		    this.Suicide = Suicide;
    		    this.AttackTypeId = AttackTypeId;
    		    this.TargetTypeId = TargetTypeId;
    		    this.Target = Target;
    		    this.Nationality = Nationality;
    		    this.WeaponTypeId = WeaponTypeId;
    		}
    		
	public TerroristEvents(
	int EventId,
    int Year,
    int Month,
    int Day,
    String CountryName,
    String Region,
    String ProvState,
    String City,
    String Latitude,
    String Longitude,
    int Suicide,
    int AttackTypeId,
    int TargetTypeId,
    String Target,
    String Nationality,
    int WeaponTypeId) {
		this.EventId = EventId;
	    this.Year = Year;
	    this.Month = Month;
	    this.Day = Day;
	    this.CountryName = CountryName;
	    this.Region = Region;
	    this.ProvState = ProvState;
	    this.City = City;
	    this.Latitude = Latitude;
	    this.Longitude = Longitude;
	    this.Suicide = Suicide;
	    this.AttackTypeId = AttackTypeId;
	    this.TargetTypeId = TargetTypeId;
	    this.Target = Target;
	    this.Nationality = Nationality;
	    this.WeaponTypeId = WeaponTypeId;
	}
	
 	  public int getEventId() {
	    return EventId;
	  }

	  public void setEventId(int eventId) {
	    EventId = eventId;
	  }

	  public int getYear() {
	    return Year;
	  }

	  public void setYear(int year) {
	    Year = year;
	  }

	  public int getMonth() {
	    return Month;
	  }

	  public void setMonth(int month) {
	    Month = month;
	  }

	  public int getDay() {
	    return Day;
	  }

	  public void setDay(int day) {
	    Day = day;
	  }

	  public String getCountryName() {
	    return CountryName;
	  }

	  public void setCountryName(String countryName) {
	    CountryName = countryName;
	  }

	  public String getRegion() {
	    return Region;
	  }

	  public void setRegion(String region) {
	    Region = region;
	  }

	  public String getProvState() {
	    return ProvState;
	  }

	  public void setProvState(String provState) {
	    ProvState = provState;
	  }

	  public String getCity() {
	    return City;
	  }

	  public void setCity(String city) {
	    City = city;
	  }

	  public String getLatitude() {
	    return Latitude;
	  }

	  public void setLatitude(String latitude) {
	    Latitude = latitude;
	  }

	  public String getLongitude() {
	    return Longitude;
	  }

	  public void setLongitude(String longitude) {
	    Longitude = longitude;
	  }

	  public int getSuicide() {
	    return Suicide;
	  }

	  public void setSuicide(int suicide) {
	    Suicide = suicide;
	  }

	  public int getAttackTypeId() {
	    return AttackTypeId;
	  }

	  public void setAttackTypeId(int attackTypeId) {
	    AttackTypeId = attackTypeId;
	  }

	  public int getTargetTypeId() {
	    return TargetTypeId;
	  }

	  public void setTargetTypeId(int targetTypeId) {
	    TargetTypeId = targetTypeId;
	  }

	  public String getTarget() {
	    return Target;
	  }

	  public void setTarget(String target) {
	    Target = target;
	  }

	  public String getNationality() {
	    return Nationality;
	  }

	  public void setNationality(String nationality) {
	    Nationality = nationality;
	  }

	  public int getWeaponTypeId() {
	    return WeaponTypeId;
	  }

	  public void setWeaponTypeId(int weaponTypeId) {
	    WeaponTypeId = weaponTypeId;
	  }
}
