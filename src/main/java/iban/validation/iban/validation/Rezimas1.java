package iban.validation;

import java.util.Scanner;
/**
 * Failas, skirtas pasirinktam 1 režimo programai vykdyti
 *
 * @author Toma
 *
 */
public class Rezimas1 {
	/**
	 * Sukuria String klasės tipo kintamąjį IBAN numeriui
	 */
	private String iban_numeris;
	/**
	 * Sukuria Integer klasės tipo kintamąjį šalies IBAN ilgiui
	 */
	private Integer salies_iban_ilgis;
	/**
	 * Tuščias konstruktorius
	 */
	public Rezimas1() {
		
	}
	/**
	 * Metodas, skirtas pasirinktam 1 režimo programai vykdyti
	 */
	public void rezimasVienas() {
    	
    	System.out.println("Įveskite IBAN numerį:");
    	Scanner scan1 = new Scanner(System.in);
    	iban_numeris = scan1.nextLine();
		String salis = iban_numeris.substring(0, 2);
		IbanSalis iban_salis = new IbanSalis(salis);
		if(iban_salis.arIbanSalis()) {
			salies_iban_ilgis = iban_salis.saliesSimboliuKiekis();
			if (salies_iban_ilgis == iban_numeris.length()) {
				
				String sk_tikrinimui = iban_numeris.substring(2, 4);
				String bban_nr = iban_numeris.substring(4);
				IbanNr iban_nr = new IbanNr(salis, sk_tikrinimui, bban_nr);
				Boolean ar_teisingas = iban_nr.ibanTikrinimas();
				System.out.println("Įvestas IBAN numeris " + iban_numeris);
				System.out.println("Salis " + salis);
				System.out.println("Skaiciai tikrinimui " + sk_tikrinimui);
				System.out.println("BBAN " + bban_nr);
				if (ar_teisingas)
					System.out.println("Įvestas IBAN numeris yra teisingas.");
				else System.out.println("Įvestas IBAN numeris yra neteisingas.");
			} else {
				
				System.out.println("Įvestas IBAN numeris neatitinka ilgio.");
			}
		} else System.out.println("Šalis nepalaiko IBAN.");
	}

}
