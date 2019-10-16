/**
 * 
 */
package DTO;

/**
 * @author Yllub-pc
 * @date Jul 11, 2019 4:11:44 PM
 * @Swing_Project
 * @Adresa.java
 */
public class Adresa {

	private String judet;
	private String ora;
	private String strada;

	
	
	/**
	 * 
	 */
	public Adresa() {
		judet=new String();
		ora=new String();
		strada=new String();
	}
	
	public String getJudet() {
		return judet;
	}

	public void setJudet(String judet) {
		this.judet = judet;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public String getStrada() {
		return strada;
	}

	public void setStrada(String strada) {
		this.strada = strada;
	}

	@Override
	public String toString() {
		return "Adresa [judet=" + judet + ", ora=" + ora + ", strada=" + strada +"]";
	}

	private boolean hasRecords() {
		if (judet.isEmpty() && ora.isEmpty() && strada.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public String adresatoString() {
		StringBuilder stringBuilder = new StringBuilder();
		if (hasRecords()) {
			stringBuilder.append(judet + " " + ora + " " + strada + " ");
			String temp = stringBuilder.toString().trim().replaceAll("\\s+", " ");
			return temp;
		} else {
			return "ADRESA NESPECIFICATA";
		}

	}

}
