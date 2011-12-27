package model;


import java.util.Date;


public class Daten {
	int id;
	double wtemp;            //Wassertemperatur in Celsius
	double ltemp;			 //Lufttemperatur in Celsius
	Date zeitpunkt;		 //Uhrzeit und Datum als datetime
	
	public Daten(double wtemp, double ltemp) {
		super();
		this.wtemp = wtemp;
		this.ltemp = ltemp;	    
	}
	
	public Daten(double wtemp, double ltemp,Date zeitpunkt) {
		super();
		this.wtemp = wtemp;
		this.ltemp = ltemp;
		this.zeitpunkt = zeitpunkt;
	}
	
	public Daten(int id, double wtemp, double ltemp, Date zeitpunkt) {
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

	public double getWtemp() {
		return wtemp;
	}

	public void setWtemp(double wtemp) {
		this.wtemp = wtemp;
	}

	public double getLtemp() {
		return ltemp;
	}

	public void setLtemp(double ltemp) {
		this.ltemp = ltemp;
	}

	public Date getZeitpunkt() {
		return zeitpunkt;
	}

	public void setZeitpunkt(Date zeitpunkt) {
		this.zeitpunkt = zeitpunkt;
	}
}
