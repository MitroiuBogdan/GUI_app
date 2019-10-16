/**
 * 
 */
package Validation;

import javax.swing.JTextField;

import Exceptions.InvalidBussinesLogicException;
import Exceptions.InvalitFormException;



/**
 * @author Yllub-pc
 * @date   Jul 17, 2019 5:23:22 PM
 * @Swing_Project
 * @SearchCandidatValidation.java
 */
public class UpdateValidation {

	public static void validFields(JTextField namField,JTextField prnumeField,JTextField dataField) throws InvalitFormException, InvalidBussinesLogicException {
		
		if(!FormValidation.validName(namField.getText())||!FormValidation.validName(prnumeField.getText())||!FormValidation.validDate(dataField.getText())) {
			throw new InvalitFormException();
		}
		if(namField.getText().length()<4||prnumeField.getText().length()<3) {
			throw new InvalidBussinesLogicException();
		}
		
		
		
		
		
		
	}
}
