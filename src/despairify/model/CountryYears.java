package despairify.model;

import java.time.Year;

public class CountryYears
{


    private int countryYearsId;
    private String countryAlpha3Code;
    private Year year;


    public CountryYears(int countryYearsId, String countryAlpha3Code, Year year) {
        this.countryYearsId = countryYearsId;
        this.countryAlpha3Code = countryAlpha3Code;
        this.year = year;
    }
    
    public CountryYears(String countryAlpha3Code, Year year) {
        this.countryAlpha3Code = countryAlpha3Code;
        this.year = year;
    }

    public int getCountryYearsId() {
        return this.countryYearsId;
    }

    public void setCountryYearsId(int countryYearsId) {
        this.countryYearsId = countryYearsId;
    }

    public String getCountryAlpha3Code() {
        return this.countryAlpha3Code;
    }

    public void setCountryAlpha3Code(String countryAlpha3Code) {
        this.countryAlpha3Code = countryAlpha3Code;
    }

    public Year getYear() {
        return this.year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}