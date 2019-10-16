/**
 * 
 */
package Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DTO.Candidat;
import Exceptions.InvalidBussinesLogicException;
import Exceptions.InvalidGradeException;



/**
 * @author Yllub-pc
 * @date Jul 11, 2019 4:17:22 PM
 * @Swing_Project
 * @AdaugareCandidatiExamenValidareBusiness.java
 */
public class BusinessValidation {
	
	
	public static boolean validBussines(Candidat candidat) throws InvalidBussinesLogicException {
		if (candidat.getNume().isEmpty() || candidat.getPrenume().isEmpty() || convertDate(candidat) > 18) {
			
	
			throw new InvalidBussinesLogicException();
		} else {
			
			return true;
		}

	}

	private static long convertDate(Candidat candidat) {
		long mils;
		final long seconds = 1000;
		final long minutes = 60 * seconds;
		final long hours = 60 * minutes;
		final long days = 24 * hours;
		final long years = 360 * days;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date parsedDate = dateFormat.parse(candidat.getDataNasterii());
			mils = Calendar.getInstance().getTimeInMillis() - parsedDate.getTime();
			return mils / years;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (Long) null;

	}
	public static void validGrade(float a,float b) throws InvalidGradeException 
	{
		if(a>10||b>10||a<0||b<0) {
			throw new InvalidGradeException();
		}
		
		
	}
}
