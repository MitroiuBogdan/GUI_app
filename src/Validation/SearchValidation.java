/**
 * 
 */
package Validation;

import java.util.zip.DataFormatException;

import DTO.Candidat;
import Exceptions.InvalitFormException;


/**
 * @author Yllub-pc
 * @date   Jul 18, 2019 11:04:08 PM
 * @Swing_Project
 * @SearchValidation.java
 */
public class SearchValidation {
	
	
	public static void validSearch(Candidat tempCandidat) throws InvalitFormException, DataFormatException {
		
		//date & name case
		boolean nameFlag=tempCandidat.getNume().isBlank();
		boolean dateFlag=tempCandidat.getDataNasterii().isBlank();
		
		//if name is empty search by date ->0K
		if(!nameFlag&&dateFlag) {
			if(!FormValidation.validName(tempCandidat.getNume())) {
				System.out.println("***");
				throw new InvalitFormException();
			}
			
		}
		if(nameFlag&&!dateFlag) {
			if(!FormValidation.validDate(tempCandidat.getDataNasterii())) {
				System.out.println(":::::");
				throw new DataFormatException();
			}
			
			
		}
		if(!nameFlag&&!dateFlag) {
			if(!FormValidation.validName(tempCandidat.getNume())||!FormValidation.validDate(tempCandidat.getDataNasterii())) {
				System.out.println("###");
				throw new InvalitFormException();
			}
			
			
			
			
		}
		
		
		
		//date case
		
		
		
		
		//name case
	}

}
