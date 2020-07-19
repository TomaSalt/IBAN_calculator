package iban.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Failas, skirtas pasirinktam 1 režimo programai vykdyti
 *
 * @author Toma
 *
 */
public class Rezimas2 {
	/**
	 * Sukuria String klasės kintamąjį nuskaitomo failo adresui
	 */
	private String nuskaitomo_failo_adresas;
	/**
	 * Sukuria String klasės kintamąjį nuskaitomo failo vardui
	 */
	private String failo_vardas;
	/**
	 * Sukuria String klasės sąrašą nuskaitomiems duomenims
	 */
	private ArrayList<String> duomenys;
	/**
	 * Sukuria String klasės kintamąjį įrašomo failo vardui
	 */
	String irasomo_failo_vardas;
	/**
	 * Sukuria Integer klasės tipo kintamąjį šalies IBAN ilgiui
	 */
	private Integer salies_iban_ilgis;
	/**
	 * Tuščias konstruktorius
	 */
	public Rezimas2() {
		
	}
	/**
	 * Metodas, skirtas pasirinktam 2 režimo programai vykdyti
	 */
	public void rezimasDu() {
		
    	// Pranešimas vartotojui
    	System.out.println("Įveskite nuskaitomo failo adresą");
    	// Skaitoma įvedama reikšmė iš klaviatūros
    	Scanner scan2 = new Scanner(System.in);
    	nuskaitomo_failo_adresas = scan2.nextLine();
    	// Pranešimas vartotojui
    	System.out.println("Įveskite nuskaitomo failo vardą");
    	// Skaitoma įvedama reikšmė iš klaviatūros
    	failo_vardas = scan2.nextLine();
    	// Kuriamas File klasės kintamasis pagal duomenis įvestus iš klaviatūros
		File file = new File(nuskaitomo_failo_adresas + failo_vardas);
		// Kartoti, jei nerandamas failas nurodytame adrese
		while (!file.exists()){
			
			// Pranešimai vartotojui
			System.out.println("Įvestas neteisingas failo adresas arba vardas. Bandykite dar kartą.");
			System.out.println("Įveskite nuskaitomo failo adresą");
			// Skaitoma įvedama reikšmė iš klaviatūros
			nuskaitomo_failo_adresas = scan2.nextLine();
			// Pranešimas vartotojui
			System.out.println("Įveskite nuskaitomo failo vardą");
			// Skaitoma įvedama reikšmė iš klaviatūros
			failo_vardas = scan2.nextLine();
			// Kuriamas File klasės kintamasis pagal duomenis įvestus iš klaviatūros
			file = new File(nuskaitomo_failo_adresas + failo_vardas);
		}
		String iban_numeris;
		Boolean ar_teisingas = false;
		// Pranešimas vartotojui, kai randamas įvestas failas įvestame adrese
		System.out.println("Nuskaitytas failas: " + nuskaitomo_failo_adresas + failo_vardas);
		// Kuriamas SkaitymasIsFailo klasės kintamasis informacijos iš failo skaitymui
		SkaitymasIsFailo nuskaitymas = new SkaitymasIsFailo(nuskaitomo_failo_adresas + failo_vardas);
		// Duomenys iš failo, įkeliami į String tipo sąrašą
		duomenys = nuskaitymas.iMasyva();
		// Tikrinama kiekviena sąrašo eilutė kaip IBAN numeris
		for (int i=0; i < duomenys.size(); i++) {
			
			iban_numeris = duomenys.get(i);
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
						ar_teisingas = iban_nr.ibanTikrinimas();
					} else {
						// Jei ne, išvedamas pranešimas vartotojui
						System.out.println("Įvestas IBAN numeris neatitinka pagal šalį nustatyto ilgio.");
					}
				} else {
					// Jei šalis ne IBAN narė, saugoma false reikšmė
					ar_teisingas = false;
				}
			} else {
				// IBAN numeryje yra netinkamų simbolių, saugoma false reikšmė
				ar_teisingas = false;
			}
			// Duomenų sąrašas papildomas atsakymu ar IBAN numeris yra teisingas
			duomenys.set(i, iban_numeris + ";" + ar_teisingas);
		}
		// Suformuojamas kuriamo failo adresas ir pavadinimas su plėtiniu .out
		irasomo_failo_vardas = nuskaitomo_failo_adresas + failo_vardas + ".out";
		// Kuriamas failas pagal nurodytą adresą ir pavadinimą 
		RasymasFaile rasymas = new RasymasFaile(irasomo_failo_vardas);
		// Įrašomi duomenys į sukurtą failą
		rasymas.iEilutes(duomenys);
		System.out.println("Sukurtas failas: " + irasomo_failo_vardas + ", į kurį įrašyti IBAN numeriai ir ar jie teisingi, ar ne");
		// src/main/resources/
		// nuskaitomas.txt
	}
}
