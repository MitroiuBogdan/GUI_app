/**
 * 
 */
package Exceptions;

/**
 * @author Yllub-pc
 * @date Jul 11, 2019 4:20:04 PM
 * @Swing_Project
 * @InvalitFormException.java
 */
public class InvalitFormException extends Exception {

	/**
	 * 
	 */
	public InvalitFormException() {
		super("InvalitFormException: \n"
				+ "- numele sa contina doar lirere mici si mari spatiu si -, , max 30 caractere\r\n"
				+ "- prenumele sa contina doar lirere mici si mari spatiu si  -,  max 30 caractere \r\n"
				+ "- data introdusa ca string sa fie o data valida\r\n"
				+ "- numele trebuie sa aiba mai mult de 3 carcatere\r\n");
	}

}
