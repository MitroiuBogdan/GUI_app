/**
 * 
 */
package Exceptions;

/**
 * @author Yllub-pc
 * @date   Jul 17, 2019 7:36:35 PM
 * @Swing_Project
 * @InvalidGradeException.java
 */
public class InvalidGradeException extends Exception {
	
	public InvalidGradeException() {
		super("Nota Invalida\n"
				+ "Nota trebuie sa fie intre 0 si 10");
		
	}
	
}
