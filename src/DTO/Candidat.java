/**
 * 
 */
package DTO;

/**
 * @author Yllub-pc
 * @date Jul 11, 2019 3:59:51 PM
 * @Swing_Project
 * @Candidat.java
 */
public class Candidat {
	private String nume;
	private String prenume;
	private String dataNasterii;
	private float notaRomana ;
	private float notaMatematica ;
	private Adresa adresa=null;

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public float getNotaRomana() {
		return notaRomana;
	}

	public void setNotaRomana(float nota) {
		nota = nota*100;
		nota = (float)((int) nota);
		this.notaRomana = nota /100;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public float getNotaMatematica() {
		return notaMatematica;
	}

	public void setNotaMatematica(float nota) {
		nota = nota*100;
		nota = (float)((int) nota);
		this.notaMatematica = nota /100;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getDataNasterii() {
		return dataNasterii;
	}

	public void setDataNasterii(String dataNasterii) {
		this.dataNasterii = dataNasterii;
	}

	@Override
	public String toString() {
		return "Candidat [nume=" + nume + ", prenume=" + prenume + ", dataNasterii=" + dataNasterii + ", notaRomana="
				+ notaRomana + ", notaMatematica=" + notaMatematica + "]";
	}

}
