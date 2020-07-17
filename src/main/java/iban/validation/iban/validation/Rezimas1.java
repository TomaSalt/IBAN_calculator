package iban.validation;

import java.util.Scanner;

public class Rezimas1 {
	
	private String iban_numeris;
	
	public Rezimas1() {
		
	}
	public void rezimasVienas() {
    	
    	System.out.println("Įveskite IBAN numerį:");
    	Scanner scan1 = new Scanner(System.in);
    	iban_numeris = scan1.nextLine();
		String salis = iban_numeris.substring(0, 2);
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
		scan1.close();
	}
}
