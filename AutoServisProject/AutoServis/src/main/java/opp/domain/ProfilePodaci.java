package opp.domain;

import java.time.LocalDate;

public class ProfilePodaci {
	private PrijavaKey prijavaKey;
	private String imeKorisnika;
	private LocalDate vrijemeDolaska;
	public PrijavaKey getPrijavaKey() {
		return prijavaKey;
	}
	public void setPrijavaKey(PrijavaKey prijavaKey) {
		this.prijavaKey = prijavaKey;
	}
	public String getImeKorisnika() {
		return imeKorisnika;
	}
	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}
	public LocalDate getVrijemeDolaska() {
		return vrijemeDolaska;
	}
	public void setVrijemeDolaska(LocalDate vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}
	
}
