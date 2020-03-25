package despairify.model;

import java.time.Year;

public class Suicides {
    protected int suicideId;
    protected String countryName;
    protected Year year;
    protected Sexes Sex;
    protected int suicides;
    protected int population;
    protected AgeRanges ageRange;

    public Suicides(int suicideId, String countryName, Year year, Sexes sex,
        int suicides, int population, AgeRanges ageRange) {
        this.suicideId = suicideId;
        this.countryName = countryName;
        this.year = year;
        Sex = sex;
        this.suicides = suicides;
        this.population = population;
        this.ageRange = ageRange;
    }

    public Suicides(String countryName, Year year, Sexes sex, int suicides,
        int population, AgeRanges ageRange) {
        this.countryName = countryName;
        this.year = year;
        Sex = sex;
        this.suicides = suicides;
        this.population = population;
        this.ageRange = ageRange;
    }

    public int getSuicideId() {
        return suicideId;
    }

    public void setSuicideId(int suicideId) {
        this.suicideId = suicideId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Sexes getSex() {
        return Sex;
    }

    public void setSex(Sexes sex) {
        Sex = sex;
    }

    public int getSuicides() {
        return suicides;
    }

    public void setSuicides(int suicides) {
        this.suicides = suicides;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public AgeRanges getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRanges ageRange) {
        this.ageRange = ageRange;
    }
}
