package opp.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

public class PrijavaModel {
	private int korisnikId;
	private String vrijemePrijave;
	private int idServisera;
	private String vrijemeDolaska;
	private String dodatniZahtjevi;
	private String regZamjensko;
	private Set<Integer> usluge;
	
	public PrijavaModel() {
		
	}

	public int getKorisnikId() {
		return korisnikId;
	}

	public String getVrijemePrijave() {
		return vrijemePrijave;
	}

	public void setVrijemePrijave(String vrijemePrijave) {
		this.vrijemePrijave = vrijemePrijave;
	}

	public void setKorisnikId(int korisnikId) {
		this.korisnikId = korisnikId;
	}

	public int getIdServisera() {
		return idServisera;
	}

	public void setIdServisera(int idServisera) {
		this.idServisera = idServisera;
	}

	public String getVrijemeDolaska() {
		return vrijemeDolaska;
	}

	public void setVrijemeDolaska(String vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}

	public String getDodatniZahtjevi() {
		return dodatniZahtjevi;
	}

	public void setDodatniZahtjevi(String dodatniZahtjevi) {
		this.dodatniZahtjevi = dodatniZahtjevi;
	}

	public String getRegZamjensko() {
		return regZamjensko;
	}

	public void setRegZamjensko(String regZamjensko) {
		this.regZamjensko = regZamjensko;
	}

	public Set<Integer> getUsluge() {
		return usluge;
	}

	public void setUsluge(Set<Integer> usluge) {
		this.usluge = usluge;
	}
	
	
}
