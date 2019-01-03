package opp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ZamjenskoVozilo {

	@Id
	@Column(name = "reg_oznaka_vozila")
	@Length(max = 20)
	private String registracijskaOznakaVozila;
	
	@Column(name = "id_korisnika")
	private String idKorisnik;
	
	@NotEmpty
	@Column(name = "tip_vozila")
	private String tipVozila;
	
	@NotEmpty
	@Column(name = "godina_proizvodnje")
	private int godinaProizvodnje;

	public String getRegistracijskaOznakaVozila() {
		return registracijskaOznakaVozila;
	}

	public String getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(String idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(String tipVozila) {
		this.tipVozila = tipVozila;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}
}
