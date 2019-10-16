/**
 * 
 */
package Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import DTO.Candidat;
import Exceptions.InvalitFormException;

/**
 * @author Yllub-pc
 * @date Jul 11, 2019 4:17:07 PM
 * @Swing_Project
 * @AdaugareCandidatiExamenValidareFormala.java
 */
public class FormValidation {
	
	
	
	public static boolean ValidForm(Candidat candidat) throws InvalitFormException {

		if (validName(candidat.getNume()) && validName(candidat.getPrenume())
				&& validDate(candidat.getDataNasterii())) {
			return true;

		} else {

			throw new InvalitFormException();

		}

	}

	public static boolean validName(String nume) {

		if (nume.replaceAll("[a-zA-Z-]", "").isBlank() && nume.trim().replaceAll("\\s+", " ").length() < 30 &&nume.trim().replaceAll("\\s+", " ").length()>=3) {

			
			nume.trim();
			nume = nume.replaceAll("\\s+", " ");
			
			
			return true;

		} else {
			
			return false;
		}

	}

	public static boolean validDate(String date) {

		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		dtFormatter = dtFormatter.withResolverStyle(ResolverStyle.STRICT);

		try {
			LocalDate localDate = LocalDate.parse(date, dtFormatter);
			System.out.println(localDate.toString());

		} catch (DateTimeParseException e) {
			
			// e.printStackTrace();
			return false;
		}

		return true;

	}

}
