package iban.validation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * Failas, skirtas duomenų nuskaitymui iš failo
 * 
 * @author Toma
 *
 */
public class SkaitymasIsFailo {
	/**
	 * Sukuria String tipo kintamąjį failo vardui
	 */
	private String vardas_failo;
	/**
	 * Sukuria BufferedReader klasės kintamąjį, kuris padeda skaityti tekstą iš rašmenų įvesties srauto
	 */
	private BufferedReader br;
	/**
	 * Sukuria String tipo kintamąjį failo eilutei
	 */
	private String file_line;
	/**
	 * Tuščias konstruktorius
	 */
	public SkaitymasIsFailo() {
	}
	/**
	 * Konstruktoriui perduodamas failo vardas
	 * @param vardas_failo String tipo kintamasis skaitomo failo vardui
	 */
	public SkaitymasIsFailo ( String vardas_failo ) {
		
		this.vardas_failo = vardas_failo;
	}
	/**
	 * Vardas_failo getter'is
	 * @return vardas_failo
	 */
	public String getVardas_failo() {
		
		return vardas_failo;
	}
	/**
	 * @param vardas_failo setter'is
	 */
	public void setVardas_failo( String vardas_failo ) {
		
		this.vardas_failo = vardas_failo;
	}
	/**
	 * Metodas pradėti skaityti iš failo
	 */
	public void pradeti() {
		
		try {
		
			this.br = new BufferedReader( new FileReader( vardas_failo ) );
			skaitytiEilute();
			
		} catch ( Exception e ) {
			
			System.err.format ( "IOException: %s%n", e );
		}		
	}
	/**
	 * String sąrašo tipo kintamasis, teksto eilučių iš failo sudėjimui į zodziai masyvą
	 * @return zodziai
	 */
	public ArrayList<String> iMasyva() {
		
		ArrayList<String> zodziai = new ArrayList<String>();
		
		pradeti(); 
		
		while ( nuskaitytaEilute() ) {
			
			paimtiEilute();
			
			zodziai.add ( file_line );
			//System.out.println ( file_line );
	
			skaitytiEilute();
		}		
		
		return zodziai;
	}
	/**
	 * Metodas skaitymui iš rašmenų įvesties srauto po eilutę ir skaitymo užbaigimui, jei srautas baigiasi
	 */
	public void skaitytiEilute() {
		
		try {
			
			this.file_line = this.br.readLine();
			
			
			if ( ! nuskaitytaEilute() ) {
				this.br.close();
			}			
		
		} catch ( Exception e ) {
			
			System.err.format ( "IOException: %s%n", e );
		}

	}
	/**
	 * Boolean tipo kintamasis, patvirtinimui, kad nuskaityta eilutė
	 * @return this.file_line != null
	 */
	public boolean nuskaitytaEilute() {
		
		return this.file_line != null;
	}
	/**
	 * String tipo kintamasis, grąžinantis nuskaitytą eilutę
	 * @return this.file_line
	 */
	public String paimtiEilute() {
		
		return this.file_line;
	}
}
