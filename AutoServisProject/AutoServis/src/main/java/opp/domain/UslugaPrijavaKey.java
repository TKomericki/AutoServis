package opp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class UslugaPrijavaKey implements Serializable{
	@Column(name = "id_usluge")
	private int idUsluge;
	@Column(name = "idKorisnika")
	private int idKorisnika;
	@Column(name = "vrijemePrijave")
	private Timestamp vrijemePrijave;
	
	public UslugaPrijavaKey() {
		
	}
	
	
	public int getIdUsluge() {
		return idUsluge;
	}
	public void setIdUsluge(int idUsluge) {
		this.idUsluge = idUsluge;
	}
	public Timestamp getVrijemePrijave() {
		return vrijemePrijave;
	}
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	public void setVrijemePrijave(Timestamp vrijemePrijave) {
		this.vrijemePrijave = vrijemePrijave;
	}
	public int getIdKorisnika() {
		return idKorisnika;
	}
}
