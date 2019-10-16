/**
 * 
 */
package App;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import DTO.Adresa;
import DTO.Candidat;

/**
 * @author Yllub-pc
 * @date Jul 12, 2019 8:01:52 PM
 * @Swing_Project
 * @RandomGenerator.java
 */
public class RandomGenerator {
	private ThreadLocalRandom tLocalRandom;

	private final List<String> nameList = Arrays.asList("Popescu", "Ionescu", "Popa", "Pop", "Niță", "Nițu",
			"Constantinescu", "Stan", "Stanciu", "Dumitrescu", "Dima", "Gheorghiu", "Ioniță", "Marin", "Tudor", "Dobre",
			"Barbu", "Nistor", "Florea", "Frățilă", "Dinu", "Dinescu", "Georgescu", "Stoica", "Diaconu", "Diaconescu",
			"Mocanu", "Voinea", "Albu", "Petrescu", "Manole", "Cristea", "Toma", "Stănescu", "Pușcașu", "Tomescu",
			"Sava", "Ciobanu", "Rusu", "Ursu", "Lupu", "Munteanu", "Mehedințu", "Andreescu", "Sava", "Mihăilescu",
			"Iancu", "Blaga", "Teodoru", "Teodorescu", "Moise", "Moisescu", "Călinescu", "Tabacu", "Negoiță");

	private final List<String> firstNameList = Arrays.asList("Ema", "Alina", "Bogdan", "Cosmin", "Vlad", "Ionut",
			"Elena", "Rebeca", "Daria", "Maia", "Florin", "Ana", "Mihai", "Stefania", "George");

	public RandomGenerator() {

		tLocalRandom = ThreadLocalRandom.current();

	}

	public Candidat generatCandidat() {
		Candidat tempCandidat = new Candidat();
		Adresa adresa = new Adresa();

		tempCandidat.setNume(nameList.get(tLocalRandom.nextInt(nameList.size())));
		tempCandidat.setPrenume(firstNameList.get(tLocalRandom.nextInt(firstNameList.size())));
		tempCandidat.setDataNasterii(generateDate());
		tempCandidat.setNotaMatematica(tLocalRandom.nextFloat() * 10);
		tempCandidat.setNotaRomana(tLocalRandom.nextFloat() * 10);
		tempCandidat.setAdresa(adresa);

		return tempCandidat;
	}

	public List<Candidat> addRandomCandidats(int number, List<Candidat> list) {
		for (int i = 0; i < number; i++) {
			list.add(generatCandidat());
		}

		return list;
	}

	public String generateDate() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, tLocalRandom.nextInt(2002, 2010));
		calendar.set(Calendar.MONTH, tLocalRandom.nextInt(1, calendar.getActualMaximum(Calendar.MONTH) + 1));
		calendar.set(Calendar.DAY_OF_MONTH,
				tLocalRandom.nextInt(1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(calendar.getTime()).toString();
	}

}
