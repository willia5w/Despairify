package despairify.model;

public class Countries {
    protected String countryAlpha3Code;
    protected String countryName;

    public Countries(String countryAlpha3Code, String countryName) {
        this.countryAlpha3Code = countryAlpha3Code;
        this.countryName = countryName;
    }

    public String getCountryAlpha3Code() {
        return countryAlpha3Code;
    }

    public void setCountryAlpha3Code(String countryAlpha3Code) {
        this.countryAlpha3Code = countryAlpha3Code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
