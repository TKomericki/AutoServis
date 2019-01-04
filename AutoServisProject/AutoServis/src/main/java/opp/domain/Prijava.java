package opp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.Set;

import java.sql.Timestamp;
import java.time.LocalDate;

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
	private LocalDate vrijemeDolaska;
	@Column(name = "preuzeto")
	private boolean preuzeto;
	@Column(name = "zavrseno")
	private boolean zavrseno;
	@Column(name = "vrijemeZavrsetka")
	private Timestamp vrijemeZavrsetka;
	@Column(name = "dodatniZahtjevi")
	private String dodatniZahtjevi;
	@Column(name = "regZamjensko")
	private String regZamjensko;
	@ManyToMany(mappedBy = "prijave")
	private Set<Usluga> usluge;
	
	public int getIdServisera() {
		return idServisera;
	}
	public void setIdServisera(int idServisera) {
		this.idServisera = idServisera;
	}
	public LocalDate getVrijemeDolaska() {
		return vrijemeDolaska;
	}
	public void setVrijemeDolaska(LocalDate vrijemeDolaska) {
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
	public Timestamp getVrijemeZavrsetka() {
		return vrijemeZavrsetka;
	}
	public void setVrijemeZavrsetka(Timestamp vrijemeZavrsetka) {
		this.vrijemeZavrsetka = vrijemeZavrsetka;
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
	public PrijavaKey getPrijavaKey() {
		return prijavaKey;
	}
	
	public void setPrijavaKey(PrijavaKey prijavaKey) {
		this.prijavaKey = prijavaKey;
	}
	public Set<Usluga> getUsluge() {
		return usluge;
	}
	public void setUsluge(Set<Usluga> usluge) {
		this.usluge = usluge;
	}
	
}
