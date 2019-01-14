package opp.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class PrijavaKey implements Serializable{	
	@Column(name = "idKorisnika")
	private int idKorisnika;
	@Column(name = "vrijemePrijave")
	private Timestamp vrijemePrijave;
	
	public PrijavaKey() {
		
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
