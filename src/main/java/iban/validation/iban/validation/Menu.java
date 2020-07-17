package iban.validation;
/**
 * Failas, skirtas Menu punktams
 *
 * @author Toma
 *
 */
public enum Menu {

	IBANvalidacija("/", "IBAN validacija");
/**
* @param itemurl String tipo kintamasis url adresui
*/
	private final String itemurl;
/**
* @param naujasPavadinimas String tipo kintamasis menu punkto pavadinimui
*/
	private final String naujasPavadinimas;
/**
 * Konstruktoriui perduodami String tipo url adresas ir pavadinimas
 */
	Menu( String url, String pavadinimas ) {
		this.itemurl = url;
		this.naujasPavadinimas = pavadinimas;
	}
/**
 * String tipo kintamasis itemurl
 * @return this.itemurl
 */
	public String itemurl() {
		return this.itemurl;
	}
/**
 * String tipo kintamasis naujasPavadinimas
 * @return this.naujasPavadinimas
 */
	public String naujasPavadinimas() {
		return this.naujasPavadinimas;
	}
}
