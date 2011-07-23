package model;

public class Daten {
	int id;
	String wtemp;            //Wassertemperatur in Celsius
	String ltemp;			 //Lufttemperatur in Celsius
	String breite;           //Aquariumbreite in cm
	String laenge;			 //Aquariumlänge in cm
	String wasserstand;		 //Wasserstand in cm
	String volumen;			 //Volumen in Liter
	String naehrstoffgehalt; //Nährstoffgehalt in Prozent
	String lichtintensitaet; //Lichtintensität in Lumen
	
	public Daten(String wtemp, String ltemp, String breite,
			String laenge, String wasserstand, String volumen, String naehrstoffgehalt,
			String lichtintensitaet) {
		super();
		this.wtemp = wtemp;
		this.ltemp = ltemp;
		this.breite = breite;
		this.laenge = laenge;
		this.wasserstand = wasserstand;
		this.volumen = volumen;
		this.naehrstoffgehalt = naehrstoffgehalt;
		this.lichtintensitaet = lichtintensitaet;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWtemp() {
		return wtemp;
	}
	public void setWtemp(String wtemp) {
		this.wtemp = wtemp;
	}
	public String getLtemp() {
		return ltemp;
	}
	public void setLtemp(String ltemp) {
		this.ltemp = ltemp;
	}
	public String getBreite() {
		return breite;
	}
	public void setBreite(String breite) {
		this.breite = breite;
	}
	public String getLaenge() {
		return laenge;
	}
	public void setLaenge(String laenge) {
		this.laenge = laenge;
	}
	public String getWasserstand() {
		return wasserstand;
	}
	public void setWasserstand(String wasserstand) {
		this.wasserstand = wasserstand;
	}
	public String getVolumen() {
		return volumen;
	}
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	public String getNaehrstoffgehalt() {
		return naehrstoffgehalt;
	}
	public void setNaehrstoffgehalt(String naehrstoffgehalt) {
		this.naehrstoffgehalt = naehrstoffgehalt;
	}
	public String getLichtintensitaet() {
		return lichtintensitaet;
	}
	public void setLichtintensitaet(String lichtintensitaet) {
		this.lichtintensitaet = lichtintensitaet;
	}	
}
