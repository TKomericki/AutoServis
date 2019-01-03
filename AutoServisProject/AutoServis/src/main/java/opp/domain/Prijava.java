package opp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Prijava {
	
	@EmbeddedId
	private PrijavaKey prijavaKey;
	@Column(name = "idServisera")
	private int idServisera;
	@Column(name = "vrijemeDolaska")
	private Date vrijemeDolaska;
	@Column(name = "preuzeto")
	private boolean preuzeto;
	@Column(name = "zavrseno")
	private boolean zavrseno;
	@Column(name = "vrijemeZavrsetka")
	private Date vrijemeZavrsetka;
	@Column(name = "dodatniZahtjevi")
	private String dodatniZahtjevi;
	@Column(name = "idZamjensko")
	private int idZamjensko;
	public int getIdServisera() {
		return idServisera;
	}
	public void setIdServisera(int idServisera) {
		this.idServisera = idServisera;
	}
	public Date getVrijemeDolaska() {
		return vrijemeDolaska;
	}
	public void setVrijemeDolaska(Date vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}
	public boolean isPreuzeto() {
		return preuzeto;
	}
	public void setPreuzeto(boolean preuzeto) {
		this.preuzeto = preuzeto;
	}
	public boolean isZavrseno() {
		return zavrseno;
	}
	public void setZavrseno(boolean zavrseno) {
		this.zavrseno = zavrseno;
	}
	public Date getVrijemeZavrsetka() {
		return vrijemeZavrsetka;
	}
	public void setVrijemeZavrsetka(Date vrijemeZavrsetka) {
		this.vrijemeZavrsetka = vrijemeZavrsetka;
	}
	public String getDodatniZahtjevi() {
		return dodatniZahtjevi;
	}
	public void setDodatniZahtjevi(String dodatniZahtjevi) {
		this.dodatniZahtjevi = dodatniZahtjevi;
	}
	public int getIdZamjensko() {
		return idZamjensko;
	}
	public void setIdZamjensko(int idZamjensko) {
		this.idZamjensko = idZamjensko;
	}
	public PrijavaKey getPrijavaKey() {
		return prijavaKey;
	}
}
