package despairify.model;

public class NaturalDisasterEvents {
    private int eventId;
    private String countryAlpha3Code;
    private int totalDeaths;
    private int injured;
    private int affected;
    private int homeless;
    private int damageUSD;
    private DisasterType disasterType;

    public enum DisasterType {
        Drought, Earthquake, Epidemic, Flood, Storm, VolcanicActivity, MassMovement,
            Landslide, Wildfire, InsectInfestation, ExtremeTemperature, Fog, Impact, AnimalAccident
    }

    public NaturalDisasterEvents(int eventId, String countryAlpha3Code, int totalDeaths,
        int injured,
        int affected, int homeless, int damageUSD,
        DisasterType disasterType) {
        this.eventId = eventId;
        this.countryAlpha3Code = countryAlpha3Code;
        this.totalDeaths = totalDeaths;
        this.injured = injured;
        this.affected = affected;
        this.homeless = homeless;
        this.damageUSD = damageUSD;
        this.disasterType = disasterType;
    }
    
    public NaturalDisasterEvents(String countryAlpha3Code, int totalDeaths,
            int injured,
            int affected, int homeless, int damageUSD,
            DisasterType disasterType) {
            this.countryAlpha3Code = countryAlpha3Code;
            this.totalDeaths = totalDeaths;
            this.injured = injured;
            this.affected = affected;
            this.homeless = homeless;
            this.damageUSD = damageUSD;
            this.disasterType = disasterType;
        }


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getCountryAlpha3Code() {
        return countryAlpha3Code;
    }

    public void setCountryAlpha3Code(String countryAlpha3Code) {
        this.countryAlpha3Code = countryAlpha3Code;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getInjured() {
        return injured;
    }

    public void setInjured(int injured) {
        this.injured = injured;
    }

    public int getAffected() {
        return affected;
    }

    public void setAffected(int affected) {
        this.affected = affected;
    }

    public int getHomeless() {
        return homeless;
    }

    public void setHomeless(int homeless) {
        this.homeless = homeless;
    }

    public int getDamageUSD() {
        return damageUSD;
    }

    public void setDamageUSD(int damageUSD) {
        this.damageUSD = damageUSD;
    }

    public DisasterType getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(DisasterType disasterType) {
        this.disasterType = disasterType;
    }
}
