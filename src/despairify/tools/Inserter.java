package despairify.tools;

import java.sql.SQLException;
import java.time.Year;
import java.util.List;

import despairify.dal.*;
import despairify.model.*;


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
		
//		ageRangesDao.delete(ageRange);
		
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
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}