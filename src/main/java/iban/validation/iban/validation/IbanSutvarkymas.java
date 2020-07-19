package iban.validation;

public class IbanSutvarkymas {
	/**
	 * Sukuria String klasės tipo kintamąjį IBAN numeriui
	 */
	private String iban_numeris;
	
	public IbanSutvarkymas(String iban_numeris) {
		
		this.iban_numeris = iban_numeris;
		
	}
	private void visosRaidesDidziosios() {
		
		System.out.println("Įvestas IBAN numeris " + this.iban_numeris);
		this.iban_numeris = this.iban_numeris.toUpperCase();
		System.out.println("Uppercase " + iban_numeris);
		
	}
	private void panaikinaTarpus() {
		
		this.iban_numeris = this.iban_numeris.replaceAll("\\s+","");
		System.out.println("Be tarpu " + iban_numeris);
	}
	public Boolean arNeraNetinkamuSimboliu() {
		
		visosRaidesDidziosios();
		panaikinaTarpus();
		Boolean ar_nera_netinkamu_simb = this.iban_numeris.matches("^[a-zA-Z0-9]*$");
		return ar_nera_netinkamu_simb;
	}
	public String getIban_numeris() {
		
		return iban_numeris;
	}
	public void setIban_numeris(String iban_numeris) {
		
		this.iban_numeris = iban_numeris;
	}
}
