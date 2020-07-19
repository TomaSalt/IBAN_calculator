package iban.validation;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
/**
 * Failas, skirtas IbanNr klasės kintamajam aprašyti
 *
 * @author Toma
 *
 */
public class IbanNr {

	/**
	 * Sukuria String klasės tipo kintamąjį šalies kodui
	 */
	private String salies_kodas;
	/** 
	 * Sukuria Integer klasės tipo kintamąjį BBAN tikrinimui
	 */
	private String skaiciai_tikrinimui;
	/**
	 * Sukuria String klasės tipo kintamąjį BBAN kodui
	 */
	private String bban;
	/**
	 * Sukuria Long klasės tipo kintamąjį konvertuotam šalies kodui
	 */
	private Long konv_salies_kodas;
	private final Character[] raides = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private final String[] raides_atitinkantys_skaiciai = {"10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35"};
	/**
	 * Konstruktoriui perduodami duomenys
	 * @param salies_kodas String klasės tipo kintamasis
	 * @param skaiciai_tikrinimui String klasės tipo kintamasis
	 * @param bban String klasės tipo kintamasis
	 */
	public IbanNr( String salies_kodas, String skaiciai_tikrinimui, String bban ) {
		super();
		this.salies_kodas = salies_kodas;
		this.skaiciai_tikrinimui = skaiciai_tikrinimui;
		this.bban = bban;
	}
	/**
	 * Tuščias konstruktorius
	 */
	public IbanNr() {

	}
	/**
	 * konvertuotasSaliesKodas String klasės tipo metodas konvertuoti šalies kodą į skaičius
	 * @return konv_salies_kodas
	 */
	private String konvertuotasSaliesKodas() {
		
		Character pirma_raide = this.salies_kodas.charAt(0);
		String pirmos_raides_sk = raidesSkaitinisAtitikmuo(pirma_raide);
		Character antra_raide = this.salies_kodas.charAt(1);
		String antros_raides_sk = raidesSkaitinisAtitikmuo(antra_raide);
		String konv_salies_kodas = pirmos_raides_sk + antros_raides_sk;
		return konv_salies_kodas;
	}
	/**
	 * raidesSkaitinisAtitikmuo String klasės tipo metodas raidę paversti į skaičių junginį
	 * @param raide Character klasės tipo kintamasis raidei perduoti
	 * @return raides_sk
	 */
	private String raidesSkaitinisAtitikmuo(Character raide){
		
		String raides_sk = "";
		for(int i = 0; i < raides.length; i++) {
			
			if (raide == raides[i]) {
				raides_sk = raides_atitinkantys_skaiciai[i];
				break;
			}
		}
		return raides_sk;
	}
	/**
	 * bbanKonvertuotas String klasės tipo metodas konvertuoti bban į skaičių junginį
	 * @return bban_konvertuotas
	 */
	private String bbanKonvertuotas() {
		
		String bban_konvertuotas = "";
		for(int i = 0; i < this.bban.length(); i++) {
			
			char tikrinamas_simbolis = this.bban.charAt(i);
			if(!Character.isDigit(tikrinamas_simbolis)) {
				
				String raides_sk = raidesSkaitinisAtitikmuo(tikrinamas_simbolis);
				bban_konvertuotas += raides_sk;
			} else 
				bban_konvertuotas += tikrinamas_simbolis;
		}
		return bban_konvertuotas;
	}
	/**
	 * konvertuotasIBAN String klasės tipo metodas konvertuoti iban į skaičių junginį
	 * @return konvertuotas_iban
	 */
	private String konvertuotasIBAN() {
		
		String konvertuotas_iban = bbanKonvertuotas() + konvertuotasSaliesKodas() + this.skaiciai_tikrinimui;
		return konvertuotas_iban;
	}
	/**
	 * mod97 Integer klasės tipo metodas devynis skaičius padalinti iš 97 ir gauti liekaną
	 * @param devyni_sk Integer klasės tipo kintamasis skaičiams perduoti
	 * @return mod_97 
	 */
	private Integer mod97(Integer devyni_sk) {
		
		Integer mod_97 = devyni_sk % 97;
		//System.out.println(mod_97);
		return mod_97;
	}
	/**
	 * ibanTikrinimas Boolean klasės tipo metodas IBAN patikrinimui, ar teisinga struktūra
	 * @return iban_tikrinimo_rezultatas
	 */
	public Boolean ibanTikrinimas() {
		
		Boolean iban_tikrinimo_rezultatas;
		Integer i = 0;
		if(skaiciuTikrinimoStruktura(this.skaiciai_tikrinimui)) {
			Integer konvertuoto_iban_ilgis = konvertuotasIBAN().length();
			String devyni_simboliai = konvertuotasIBAN().substring(i, i + 9);
			Integer devyni_sk = Integer.valueOf(devyni_simboliai);
			Integer liekana = mod97(devyni_sk);
			konvertuoto_iban_ilgis -= 9;
			i += 9;
			while(konvertuoto_iban_ilgis > 7) {
				
				devyni_simboliai = liekana + konvertuotasIBAN().substring(i, i + 7);
				devyni_sk = Integer.valueOf(devyni_simboliai);
				liekana = mod97(devyni_sk);
				konvertuoto_iban_ilgis -= 7;
				i += 7;
			}
			String like_simboliai = liekana + konvertuotasIBAN().substring(i);
			Integer like_sk = Integer.valueOf(like_simboliai);
			liekana = mod97(like_sk);
			if(liekana == 1) {
				
				iban_tikrinimo_rezultatas = true;
				// Pranešimas vartotojui, jei IBAN teisingas
				System.out.println("Įvestas IBAN numeris yra teisingas.");
			
			} else {
				iban_tikrinimo_rezultatas = false;
				// Pranešimas vartotojui, jei IBAN neteisingas
				System.out.println("Įvesto IBAN numerio skaičiai tikrinimui pagal mod 97 yra netinkami.");
			}
		} else {
			
			iban_tikrinimo_rezultatas = false;
			// Pranešimas vartotojui, jei IBAN neteisingas
			System.out.println("Įvestame IBAN numeryje skaičiai tikrinimui yra netinkamos struktūros.");
		}
		return iban_tikrinimo_rezultatas;
	}
	/**
	 * skaiciuTikrinimoStruktura Boolean klasės tipo metodas IBAN skaičių tikrinimo struktūrai tikrinti
	 * @return sk_tikr_struktura
	 */
	public Boolean skaiciuTikrinimoStruktura(String ar_skaiciai) {
	
		Boolean sk_tikr_struktura = true;
		Character a;
		for (int i = 0; i < ar_skaiciai.length(); i++ ) {
			a = ar_skaiciai.charAt(i);
			if(arSimbolisYraSkaicius(a)) {
				sk_tikr_struktura = true;
			} else {

				sk_tikr_struktura = false;
				break;
			}
		}
		return sk_tikr_struktura;
	}
	private Boolean arSimbolisYraSkaicius(Character a) {
		
		Boolean ar_simb_yra_sk = Character.isDigit(a);
		return ar_simb_yra_sk;
	}
}
