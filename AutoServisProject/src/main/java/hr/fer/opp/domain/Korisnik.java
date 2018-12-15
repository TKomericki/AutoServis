package hr.fer.opp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idKorisnik;
	private String email;
	private String lozinka;
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	private String imeKorisnika;
	private String prezimeKorisnik;
	private String brojTelefona;
	private String regOznakaVozila;
	private String uloga;
	
	public Korisnik() {}
	public Integer getIdKorisnik() {
		return idKorisnik;
	}
	public void setIdKorisnik(Integer idKorisnik) {
		this.idKorisnik = idKorisnik;
	}
	public String getEmailKorisnik() {
		return email;
	}
	public void setEmailKorisnik(String emailKorisnik) {
		this.email = emailKorisnik;
	}
	public String getImeKorisnika() {
		return imeKorisnika;
	}
	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}
	public String getPrezimeKorisnika() {
		return prezimeKorisnik;
	}
	public void setPrezimeKorisnika(String prezimeKorisnika) {
		this.prezimeKorisnik = prezimeKorisnika;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getRegOznakaVozila() {
		return regOznakaVozila;
	}
	public void setRegOznakaVozila(String regOznakaVozila) {
		this.regOznakaVozila = regOznakaVozila;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
}
