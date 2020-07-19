package iban.validation;

public class IbanSalis {
	/**
	 * Sukuria String klasės tipo kintamąjį salies kodui
	 */
	private String salis;
	/**
	 * Sukuria String klasės sąrašą šalių kodams
	 */
	private final String[] saliu_kodai = {"AD","AE","AL","AO","AT","AZ","BA","BE","BF","BG","BH","BI","BJ","BR","BY","CF","CG","CH","CI","CM","CR","CV","CY","CZ","DE","DJ","DK","DO","DZ","EE","EG","ES","FI","FO","FR","GA","GB","GE","GI","GL","GQ","GR","GT","GW","HN","HR","HU","IE","IL","IQ","IR","IS","IT","JO","KM","KW","KZ","LB","LC","LI","LT","LU","LV","MA","MC","MD","ME","MG","MK","ML","MR","MT","MU","MZ","NE","NI","NL","NO","PK","PL","PS","PT","QA","RO","RS","SA","SC","SE","SI","SK","SM","SN","ST","SV","TD","TG","TL","TN","TR","UA","VA","VG","XK"};
	/**
	 * Sukuria String klasės sąrašą šalių IBAN simbolių kiekiams
	 */
	private final String[] saliu_simb_kiekiai = {"24","23","28","25","20","28","20","16","28","22","22","16","28","29","28","27","27","21","28","27","22","25","28","24","22","27","18","28","26","20","29","24","18","18","27","27","22","22","23","18","27","27","28","25","28","21","28","22","23","23","26","26","27","30","27","30","20","28","32","21","20","20","21","28","27","24","22","27","19","28","27","31","30","25","28","32","18","15","24","28","29","25","29","24","22","24","31","24","19","24","27","28","25","28","27","28","23","24","26","29","22","24","20"};
	/**
	 * Sukuria Integer klasės tipo kintamąjį salies numeriui
	 */
	private Integer salies_nr;
	private final String[][] saliu_bban_struktura = {{},{}};
	
	public IbanSalis(String salis) {
		
		this.salis = salis;
	}
	public Boolean arIbanSalis() {
		Boolean ar_iban_salis = false;
		for(int i = 0; i < saliu_kodai.length; i++) {
			if(saliu_kodai[i].equals(this.salis)) {
				ar_iban_salis = true;
				salies_nr = i;
				break;
			}
		}
		return ar_iban_salis;
	}
	public Integer saliesSimboliuKiekis() {
		Integer salies_simboliu_kiekis = 0;
		salies_simboliu_kiekis = Integer.valueOf(saliu_simb_kiekiai[salies_nr]);
		return salies_simboliu_kiekis;
	}
}
