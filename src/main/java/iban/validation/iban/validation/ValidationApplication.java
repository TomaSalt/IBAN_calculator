package iban.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
@SpringBootApplication
public class ValidationApplication {

	public static void main(String[] args) {

		/**
		 * Sukuria String tipo kintamąjį IBAN numeriui
		 */
		String iban_numeris;
		
		SpringApplication.run(ValidationApplication.class, args);
		// Kuriamas Scanner klasės tipo kintamasis reikšmių įvedimui iš klaviatūros
	    Scanner scan = new Scanner(System.in);
	    
	    // Pranešimai vartotojui
	    System.out.println("Pasirinkite norimą režimą:");
	    System.out.println("1. Interaktyvus IBAN numerių tikrinimas.");
	    System.out.println("2. IBAN numerių iš tekstinio failo tikrinimas.");
	    System.out.println("Jei norite baigti darbą įveskite \"q\"");
	    // Skaitoma įvedama reikšmė iš klaviatūros
	    String rezimas = scan.nextLine();
	    while(!rezimas.equals("q")) {
		    // Kartoti, kol nebus įvestas 1 ar 2
		    while ((!rezimas.equals("1")) && (!rezimas.equals("2"))) {
		    	
		    	System.out.println("Įvestas neteisingas skaičius. Bandykite dar kartą.");
		    	rezimas = scan.nextLine();
		    }
		    // Daryti, jei pasirinktas režimas 1
		    if(rezimas.equals("1")) {
	
		    	Rezimas1 rezim1 = new Rezimas1();
		    	rezim1.rezimasVienas();
		    }
		    // Daryti, jei pasirinktas režimas 2
		    if(rezimas.equals("2")) {
				Rezimas2 rezim2 = new Rezimas2();
				rezim2.rezimasDu();
		    }
		    // Pranešimai vartotojui
		    System.out.println("Pasirinkite norimą režimą:");
		    System.out.println("1. Interaktyvus IBAN numerių tikrinimas.");
		    System.out.println("2. IBAN numerių iš tekstinio failo tikrinimas.");
		    System.out.println("Jei norite baigti darbą įveskite \"q\"");
		    // Skaitoma įvedama reikšmė iš klaviatūros
		    rezimas = scan.nextLine();
	    }
	    System.out.println("Programa baigia darbą.");
	    scan.close();
	}

}
