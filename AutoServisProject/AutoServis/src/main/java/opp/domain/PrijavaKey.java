package opp.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class PrijavaKey implements Serializable{
	@Column(name = "vrijemePrijave")
	private Date vrijemePrijave;
	@Column(name = "idKorisnika")
	private int idKorisnika;
	public Date getVrijemePrijave() {
		return vrijemePrijave;
	}
	public int getIdKorisnika() {
		return idKorisnika;
	}
}
