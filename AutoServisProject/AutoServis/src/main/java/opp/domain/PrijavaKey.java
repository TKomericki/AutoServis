package opp.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PrijavaKey implements Serializable{	
	@Column(name = "idKorisnika")
	private int idKorisnika;
	@Column(name = "vrijemePrijave")
	private Date vrijemePrijave;
	
//	public PrijavaKey() {}
	
	public PrijavaKey(int id, Date vrijeme) {
		idKorisnika = id;
		vrijemePrijave = vrijeme;
	}
	public Date getVrijemePrijave() {
		return vrijemePrijave;
	}
	public int getIdKorisnika() {
		return idKorisnika;
	}
}
