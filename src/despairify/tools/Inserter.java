package despairify.tools;

import java.sql.SQLException;
import java.time.Year;
import java.util.List;

import despairify.dal.*;
import despairify.model.*;
import despairify.model.NaturalDisasterEvents.DisasterType;


public class Inserter {
	
	public static void main(String[] args) throws SQLException {
//		AgeRanges Test
		AgeRangesDao ageRangesDao = AgeRangesDao.getInstance();
		
		AgeRanges ageRange = new AgeRanges("15-24 years");
		
		ageRange = ageRangesDao.create(ageRange);
		
		AgeRanges returnedAgeRange = ageRangesDao.getAgeRangeByAgeRangeId(ageRange.getAgeRangeId());
		System.out.format("Reading AgeRanges id:%s ageRange:%s \n",
				returnedAgeRange.getAgeRangeId(), returnedAgeRange.getAgeRange());
		
		AgeRanges updatedAgeRange = ageRangesDao.updateAgeRange(ageRange, "25-34 years");
		System.out.format("Reading updated AgeRanges id:%s ageRange:%s \n",
				updatedAgeRange.getAgeRangeId(), updatedAgeRange.getAgeRange());
		
		ageRangesDao.delete(ageRange);
		
//		Sexes Test
		SexesDao sexesDao = SexesDao.getInstance();
		
		Sexes sex = new Sexes("male");
		
		sex = sexesDao.create(sex);
		
		Sexes returnedSex = sexesDao.getSexBySexId(sex.getSexId());
		System.out.format("Reading Sexes id:%s sex:%s \n",
				returnedSex.getSexId(), returnedSex.getSex());
		
		Sexes updatedSex = sexesDao.updateSex(sex, "female");
		System.out.format("Reading updated Sexes id:%s sex:%s \n",
				updatedSex.getSexId(), updatedSex.getSex());
		
		sexesDao.delete(sex);
		
//		Suicides Test
		Sexes male = new Sexes("male");
		sex = sexesDao.create(male);

		AgeRanges ageRange1 = new AgeRanges("15-24 years");
		AgeRanges ageRange2 = new AgeRanges("5-14 years");
		ageRange1 = ageRangesDao.create(ageRange1);
		ageRange2 = ageRangesDao.create(ageRange2);

		Year year = Year.of(1987);
		Suicides suicide = new Suicides("US", year, male, 1, 3092493, ageRange1);
		Suicides suicide2 = new Suicides("Mexico", year, male, 1, 4083843, ageRange2);
		Suicides suicide3 = new Suicides("Canada", year, male, 5, 3948382, ageRange2);
		SuicidesDao suicidesDao = SuicidesDao.getInstance();
		
		suicide = suicidesDao.create(suicide);
		suicide2 = suicidesDao.create(suicide2);
		suicide3 = suicidesDao.create(suicide3);
		
		Suicides returnedSuicide = suicidesDao.getSuicideBySuicideId(suicide.getSuicideId());
		System.out.format("Reading Suicides id:%s suicides:%s \n",
				returnedSuicide.getSuicideId(), returnedSuicide.getSuicides());
		
		Suicides updatedSuicide = suicidesDao.updateSuicides(suicide, 5);
		System.out.format("Reading updated Suicides id:%s suicide:%s \n",
				updatedSuicide.getSuicideId(), updatedSuicide.getSuicides());
		
		List<Suicides> suicideList1 = suicidesDao.getSuicidesByAgeRangeId(ageRange1.getAgeRangeId());
		for(Suicides a : suicideList1) {
			System.out.format("Looping updated Suicides id:%s suicide:%s \n",
					a.getSuicideId(), a.getSuicides());
		}
		
		suicidesDao.delete(suicide);

		// Countries Test
		CountriesDao countriesDao = CountriesDao.getInstance();

		Countries country = new Countries("USA", "United States");
		
		country = countriesDao.create(country);
		
		Countries returnedCountry = countriesDao.getCountryByAlpha3Code("USA");
		System.out.format("Reading Countries a3c:%s name:%s\n",
			returnedCountry.getCountryAlpha3Code(), returnedCountry.getCountryName());

		Countries updatedCountry = countriesDao.updateCountryName(country, "United States of America");
		System.out.format("Reading Updated Countries a3c:%s name:%s\n",
			updatedCountry.getCountryAlpha3Code(), updatedCountry.getCountryName());

//		countriesDao.delete(country);
		
		
		// NaturalDisastersEvents Test
		NaturalDisasterEventsDao naturalDisasterEventsDao = NaturalDisasterEventsDao.getInstance();
		NaturalDisasterEvents naturalDisasterEvent =
		   new NaturalDisasterEvents(
		      "USA",
		      0,
		      100,
		      150,
		      15,
		      1000000,
		      DisasterType.AnimalAccident);
		
		NaturalDisasterEvents returnedEvent = naturalDisasterEventsDao.create(naturalDisasterEvent);
		System.out.format(
		   "Reading Natural Disaster Events: id:%s, cc:%s, d:%s, i:%s, a:%s, h:%s, dusd:%s, dt:%s\n",
		   returnedEvent.getEventId(), returnedEvent.getCountryAlpha3Code(),
		   returnedEvent.getTotalDeaths(), returnedEvent.getInjured(),
		   returnedEvent.getAffected(), returnedEvent.getHomeless(), returnedEvent.getDamageUSD(),
		   returnedEvent.getDisasterType());
		NaturalDisasterEvents updatedEvent = naturalDisasterEventsDao.updateTotalDeaths(naturalDisasterEvent, 10);
		System.out.format(
		   "Reading Updated Natural Disaster Events: id:%s, cc:%s, d:%s, i:%s, a:%s, h:%s, dusd:%s, dt:%s\n",
		   updatedEvent.getEventId(), updatedEvent.getCountryAlpha3Code(),
		   updatedEvent.getTotalDeaths(), updatedEvent.getInjured(),
		   updatedEvent.getAffected(), updatedEvent.getHomeless(), updatedEvent.getDamageUSD(),
		   updatedEvent.getDisasterType());
		naturalDisasterEventsDao.delete(naturalDisasterEvent);
		
		
		//Instantiate DAO’s
		AttackTypesDao attackTypesDao = AttackTypesDao.getInstance();
		TargetTypesDao targetTypesDao = TargetTypesDao.getInstance();
		WeaponTypesDao weaponTypesDao = WeaponTypesDao.getInstance();
		CountryYearsDao countryYearsDao = CountryYearsDao.getInstance();
		
		
		// Create
		AttackTypes attackType = new AttackTypes("Brown Noise");
		attackType = attackTypesDao.create(attackType);
		TargetTypes targetType = new TargetTypes("Sheepseye");
		targetType = targetTypesDao.create(targetType);
		WeaponTypes weaponType = new WeaponTypes("Hard");
		weaponType = weaponTypesDao.create(weaponType);
		CountryYears countryYear = new CountryYears("USA", Year.of(1999));
		countryYear = countryYearsDao.create(countryYear);
		
		// Read
		AttackTypes returnedAttackType = attackTypesDao.getAttackTypeByAttackTypeId(attackType.getAttackTypeId());
		System.out.format("Reading AttackTypes id:%s attackType:%s \n",
				returnedAttackType.getAttackTypeId(), returnedAttackType.getAttackType());
		TargetTypes returnedTargetType = targetTypesDao.getTargetTypeByTargetTypeId(targetType.getTargetTypeId());
		System.out.format("Reading TargetTypes id:%s attackType:%s \n",
				returnedTargetType.getTargetTypeId(), returnedTargetType.getTargetType());
		WeaponTypes returnedWeaponType = weaponTypesDao.getWeaponTypeByWeaponTypeId(weaponType.getWeaponTypeId());
		System.out.format("Reading WeaponTypes id:%s attackType:%s \n",
				returnedWeaponType.getWeaponTypeId(), returnedWeaponType.getWeaponType());
		CountryYears returnedCountryYear = countryYearsDao.getCountryYearById(countryYear.getCountryYearsId());
		System.out.format("Reading Country Year id:%s alpha3Code:%s year:%s \n",
				returnedCountryYear.getCountryYearsId(), returnedCountryYear.getCountryAlpha3Code(), returnedCountryYear.getYear());

		// Update
		AttackTypes updatedAttackType = attackTypesDao.updateAttackType(attackType, "Scorpions");
		System.out.format("Reading updated AttackTypes id:%s attackType:%s \n",
				updatedAttackType.getAttackTypeId(), updatedAttackType.getAttackType());
		TargetTypes updatedTargetType = targetTypesDao.updateTargetType(targetType, "Sphere");
		System.out.format("Reading updated TargetTypes id:%s targetType:%s \n",
				updatedTargetType.getTargetTypeId(), updatedTargetType.getTargetType());
		WeaponTypes updatedWeaponType = weaponTypesDao.updateWeaponType(weaponType, "Soft");
		System.out.format("Reading updated WeaponTypes id:%s weaponType:%s \n",
				updatedWeaponType.getWeaponTypeId(), updatedWeaponType.getWeaponType());
		CountryYears updatedCountryYear = countryYearsDao.updateAlpha3Code(countryYear, "XYZ");
		System.out.format("Reading Updated Country Year id:%s New alpha3Code(formerly USA):%s year:%s \n",
				updatedCountryYear.getCountryYearsId(), updatedCountryYear.getCountryAlpha3Code(), updatedCountryYear.getYear());

		// Delete
		attackTypesDao.delete(attackType);
		targetTypesDao.delete(targetType);
		weaponTypesDao.delete(weaponType);
		countryYearsDao.delete(countryYear);
		
		
		
		
		
		
		
	}

}