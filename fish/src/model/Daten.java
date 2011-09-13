package model;


import control.Tool;


public class Daten {
	int id;
	String wtemp;            //Wassertemperatur in Celsius
	String ltemp;			 //Lufttemperatur in Celsius
	String wasserstand;		 //Wasserstand in cm
	String uhrzeit;			 //Uhrzeit als String
	String datum;			 //Datum als String
	
	public Daten(String wtemp, String ltemp, String wasserstand) {
		super();
		this.wtemp = wtemp;
		this.ltemp = ltemp;
		this.wasserstand = wasserstand;
		uhrzeit=Tool.SgetTime("HH:mm");
		datum=Tool.SgetTime("dd.MM.yyyy");
	}
	
	public Daten(String wtemp, String ltemp, String wasserstand, String uhrzeit, String datum) {
		super();
		this.wtemp = wtemp;
		this.ltemp = ltemp;
		this.wasserstand = wasserstand;
		this.uhrzeit = uhrzeit;
		this.datum = datum;
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

	public String getWasserstand() {
		return wasserstand;
	}

	public void setWasserstand(String wasserstand) {
		this.wasserstand = wasserstand;
	}

	public String getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(String uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
}
