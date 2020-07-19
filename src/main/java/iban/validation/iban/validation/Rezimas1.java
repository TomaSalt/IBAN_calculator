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
		
		// Pranešimas vartotojui
    	System.out.println("Įveskite IBAN numerį:");
    	// Skaitoma įvedama reikšmė iš klaviatūros
    	Scanner scan1 = new Scanner(System.in);
    	iban_numeris = scan1.nextLine();
    	IbanSutvarkymas sutvarkytas_iban = new IbanSutvarkymas(iban_numeris);
		if (sutvarkytas_iban.arNeraNetinkamuSimboliu()) {
			// Perrašomas IBAN numeris
			iban_numeris = sutvarkytas_iban.getIban_numeris();
	    	// Atrenkami pirmi du IBAN simboliai
			String salis = iban_numeris.substring(0, 2);
			IbanSalis iban_salis = new IbanSalis(salis);
			// Tikrinama, ar šalis IBAN narė
			if(iban_salis.arIbanSalis()) {
				
				salies_iban_ilgis = iban_salis.saliesSimboliuKiekis();
				// Tikrinamas IBAN numerio ilgis
				if (salies_iban_ilgis == iban_numeris.length()) {
					
					String sk_tikrinimui = iban_numeris.substring(2, 4);
					String bban_nr = iban_numeris.substring(4);
					IbanNr iban_nr = new IbanNr(salis, sk_tikrinimui, bban_nr);
					//Tikrinama, ar IBAN struktūra teisinga
					Boolean ar_teisingas = iban_nr.ibanTikrinimas();
					/*System.out.println("Salis " + salis);
					System.out.println("Skaiciai tikrinimui " + sk_tikrinimui);
					System.out.println("BBAN " + bban_nr);*/
	
				} else {
					// Pranešimas vartotojui, jei IBAN numerio ilgis netinkamas
					System.out.println("Įvestas IBAN numeris neatitinka pagal šalį nustatyto ilgio.");
				}
			} else {
				// Pranešimas vartotojui, jei šalis ne IBAN narė
				System.out.println("Šalis nepalaiko IBAN.");
			}
		} else {
			// Pranešimas vartotojui, kad IBAN numeryje yra netinkamų simbolių
			System.out.println("IBAN numeryje yra netinkamų simbolių.");
		}	
			
	}

}
