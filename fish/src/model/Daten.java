package model;


import java.util.Date;


public class Daten {
	int id;
	float wtemp;            //Wassertemperatur in Celsius
	float ltemp;			 //Lufttemperatur in Celsius
	Date zeitpunkt;		 //Uhrzeit und Datum als datetime
	
	public Daten(float wtemp, float ltemp) {
		super();
		this.wtemp = wtemp;
		this.ltemp = ltemp;	    
	}
	
	public Daten(float wtemp, float ltemp,Date zeitpunkt) {
		super();
		this.wtemp = wtemp;
		this.ltemp = ltemp;
		this.zeitpunkt = zeitpunkt;
	}
	
	public Daten(int id, float wtemp, float ltemp, Date zeitpunkt) {
		super();
		this.id = id;
		this.wtemp = wtemp;
		this.ltemp = ltemp;
		this.zeitpunkt = zeitpunkt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getWtemp() {
		return wtemp;
	}

	public void setWtemp(float wtemp) {
		this.wtemp = wtemp;
	}

	public float getLtemp() {
		return ltemp;
	}

	public void setLtemp(float ltemp) {
		this.ltemp = ltemp;
	}

	public Date getZeitpunkt() {
		return zeitpunkt;
	}

	public void setZeitpunkt(Date zeitpunkt) {
		this.zeitpunkt = zeitpunkt;
	}
}
