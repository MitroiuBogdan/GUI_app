package App;

import GUI.MainFrame;

/**
 * Hello world! java tema - swing + validare formala si de busines
 * 
 * de facut o clasa java CautareCandidatiExamen care sa afiseje un ecran tip
 * pagina de cautare adica o lista care sa afizeze persoane ( nume, prenume si
 * data nasterii ) si nota la examen romana si matematica( populati lista cu 2 3
 * inregistrari )
 * 
 * pagina trebuie da aiba un buton de adaugare care va deschide o pagina noua de
 * adaugare clasa AdaugareCandidatiExamen , si butoane de paginare
 * 
 * AdaugareCandidatiExamen trebuie sa aiba campurile nume prenume scoala data
 * nasterii, si adresa ( judet, oras, strada si nr , ...)
 * 
 * pentru aceste doua clase vom creea clase pentru validare formala si de
 * busines de ex AdaugareCandidatiExamenValidareFormala
 * AdaugareCandidatiExamenValidareBusiness
 * 
 * in AdaugareCandidatiExamenValidareFormala vim valida - numele sa contina doar
 * lirere mici si mari spatiu si -, , max 30 caractere - prenumele sa contina
 * doar lirere mici si mari spatiu si -, max 30 caractere - data introdusa ca
 * string sa fie o data valida
 * 
 * in AdaugareCandidatiExamenValidareBusiness vom valida - nume si prenume sunt
 * obligatorii - data - persona nu trebuie sa aiba varsta mai mare de 18 ani
 * 
 * pentru CautareCandidatiExamen imaginati campurile de cautare si validarile
 */
public class App {
	public static void main(String[] args) {

		new MainFrame();

		
		
	}
}
