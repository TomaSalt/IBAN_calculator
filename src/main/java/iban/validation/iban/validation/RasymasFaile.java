package iban.validation;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 * Failas, skirtas duomenų įrašymui į failą
 * 
 * @author Toma
 *
 */
public class RasymasFaile {
	/**
	 * Sukuria String tipo kintamąjį failo vardui
	 */
	public String vardas_failo;
	/**
	 * Tuščias konstruktorius
	 */
	public RasymasFaile() {
		
	}
	/**
	 * Konstruktoriui perduodamas failo vardas
	 * @param vardas_failo String tipo kintamasis failo vardui
	 */
	public RasymasFaile( String vardas_failo ) {
		
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
	 * 
	 */
	public void setVardas_failo( String vardas_failo ) {
		
		this.vardas_failo = vardas_failo;
	}	
	/**
	 * Metodas įrašymui į failą po eilutę
	 * @param zodziai String sąrašo tipo kintamasis žodžiams
	 */
	public void iEilutes ( ArrayList<String> zodziai ) {
		
		try  {
			
			BufferedWriter bw = new BufferedWriter( new FileWriter( vardas_failo ) );

			for ( String value : zodziai ) { 
						
				bw.write (value + "\n");
			}
			bw.close();
					
		} catch ( Exception e ) {
				
				System.err.format ( "IOException: %s%n", e );
		}		
	}	
}
