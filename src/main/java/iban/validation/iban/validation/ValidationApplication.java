package iban.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
/**
 * Failas, skirtas vykdyti IBAN tikrinimą
 *
 * @author Toma
 *
 */
@SpringBootApplication
public class ValidationApplication {
	/**
	 * main metodas programos vykdymui
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(ValidationApplication.class, args);
	    // Kviečiamas metodas		
		pirminiaiPranesimai();
		// Kuriamas Scanner klasės kintamasis reikšmių įvedimui iš klaviatūros
	    Scanner scan = new Scanner(System.in);
	    // Skaitoma įvedama reikšmė iš klaviatūros
	    String rezimas = scan.nextLine();
	    // Kartoti kol neįvedamas q
	    while(!rezimas.equals("q")) {
		    // Kartoti, kol nebus įvestas 1 ar 2
		    while ((!rezimas.equals("1")) && (!rezimas.equals("2"))) {
		    	// Pranešimas vartotojui
		    	System.out.println("Įvestas neteisingas skaičius. Bandykite dar kartą.");
			    // Skaitoma įvedama reikšmė iš klaviatūros
		    	rezimas = scan.nextLine();
		    }
		    // Daryti, jei pasirinktas režimas 1
		    if(rezimas.equals("1")) {
		    	// Kuriamas Rezimas1 klasės kintamasis
		    	Rezimas1 rezim1 = new Rezimas1();
				// Vykdomas metodas rezimasVienas()		    	
		    	rezim1.rezimasVienas();
		    }
		    // Daryti, jei pasirinktas režimas 2
		    if(rezimas.equals("2")) {
		    	// Kuriamas Rezimas2 klasės kintamasis
				Rezimas2 rezim2 = new Rezimas2();
				// Vykdomas metodas rezimasDu()
				rezim2.rezimasDu();
		    }
		    // Kviečiamas metodas
		    pirminiaiPranesimai();
		    // Skaitoma įvedama reikšmė iš klaviatūros
		    rezimas = scan.nextLine();
	    }
	    // Pranešimas vartotojui
	    System.out.println("Programa baigia darbą.");
		SkaitymasIsFailo java_sql = new SkaitymasIsFailo("src/main/resources/saliu_sk_kiekis.txt");
		ArrayList<String> duomenys = java_sql.iMasyva();
		String duomenys_eiluteje = "";
		for(int i = 0; i < duomenys.size(); i++) {
			duomenys_eiluteje += "\"" + duomenys.get(i) + "\",";
		}
		ArrayList<String> duomuo = new ArrayList<String>();
		duomuo.add(duomenys_eiluteje);
		String irasomo_failo_vardas = "src/main/resources/saliu_sk_kiekis2.txt";
		RasymasFaile rasymas2 = new RasymasFaile(irasomo_failo_vardas);
		rasymas2.iEilutes(duomuo);
	}
	/**
	 * pirminiaiPranesimai metodas išvesti pirminius pranešimus vartotojui
	 */
    public static void pirminiaiPranesimai() {
    	
	    System.out.println("Pasirinkite norimą režimą:");
	    System.out.println("1. Interaktyvus IBAN numerių tikrinimas.");
	    System.out.println("2. IBAN numerių iš tekstinio failo tikrinimas.");
	    System.out.println("Jei norite baigti darbą įveskite q");
    }
}
