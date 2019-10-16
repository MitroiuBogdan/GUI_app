/**
 * 
 */
package Exceptions;

/**
 * @author Yllub-pc
 * @date Jul 11, 2019 4:20:55 PM
 * @Swing_Project
 * @InvalidDataException.java
 */
public class InvalidBussinesLogicException extends Exception {

	public InvalidBussinesLogicException() {

		super("InvalidBussinesLogic+ :\n" + "" + "Varsta trebuie sa fie maxim de 18 ani\n" + ""
				+ "Numele sau/si Prenumele lipsesc\n"+"Numele sau/si Prenumele au mai putin de 4 litere\n");
	}
}
