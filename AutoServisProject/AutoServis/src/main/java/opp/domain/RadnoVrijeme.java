package opp.domain;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RadnoVrijeme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_radnog_vremena")
	private int idRadnogVremena;
	
	@Column(name = "pon_pocetak")
	@NotEmpty
	private LocalTime ponedjeljakPocetak;
	
	@Column(name = "pon_kraj")
	@NotEmpty
	private LocalTime ponedjeljakKraj;
	
	
	@Column(name = "uto_pocetak")
	@NotEmpty
	private LocalTime utorakPocetak;
	
	@Column(name = "uto_kraj")
	@NotEmpty
	private LocalTime utorakKraj;
	
	@Column(name = "sri_pocetak")
	@NotEmpty
	private LocalTime srijedaPocetak;
	
	@Column(name = "sri_kraj")
	@NotEmpty
	private LocalTime srijedaKraj;
	
	@Column(name = "cet_pocetak")
	@NotEmpty
	private LocalTime cetvrtakPocetak;
	
	@Column(name = "cet_kraj")
	@NotEmpty
	private LocalTime cetvrtakKraj;
	
	@Column(name = "pet_pocetak")
	@NotEmpty
	private LocalTime petakPocetak;
	
	@Column(name = "pet_kraj")
	@NotEmpty
	private LocalTime petakKraj;

	public int getIdRadnogVremena() {
		return idRadnogVremena;
	}

	public LocalTime getPonedjeljakPocetak() {
		return ponedjeljakPocetak;
	}

	public void setPonedjeljakPocetak(LocalTime ponedjeljakPocetak) {
		this.ponedjeljakPocetak = ponedjeljakPocetak;
	}

	public LocalTime getPonedjeljakKraj() {
		return ponedjeljakKraj;
	}

	public void setPonedjeljakKraj(LocalTime ponedjeljakKraj) {
		this.ponedjeljakKraj = ponedjeljakKraj;
	}

	public LocalTime getUtorakPocetak() {
		return utorakPocetak;
	}

	public void setUtorakPocetak(LocalTime utorakPocetak) {
		this.utorakPocetak = utorakPocetak;
	}

	public LocalTime getUtorakKraj() {
		return utorakKraj;
	}

	public void setUtorakKraj(LocalTime utorakKraj) {
		this.utorakKraj = utorakKraj;
	}

	public LocalTime getSrijedaPocetak() {
		return srijedaPocetak;
	}

	public void setSrijedaPocetak(LocalTime srijedaPocetak) {
		this.srijedaPocetak = srijedaPocetak;
	}

	public LocalTime getSrijedaKraj() {
		return srijedaKraj;
	}

	public void setSrijedaKraj(LocalTime srijedaKraj) {
		this.srijedaKraj = srijedaKraj;
	}

	public LocalTime getCetvrtakPocetak() {
		return cetvrtakPocetak;
	}

	public void setCetvrtakPocetak(LocalTime cetvrtakPocetak) {
		this.cetvrtakPocetak = cetvrtakPocetak;
	}

	public LocalTime getCetvrtakKraj() {
		return cetvrtakKraj;
	}

	public void setCetvrtakKraj(LocalTime cetvrtakKraj) {
		this.cetvrtakKraj = cetvrtakKraj;
	}

	public LocalTime getPetakPocetak() {
		return petakPocetak;
	}

	public void setPetakPocetak(LocalTime petakPocetak) {
		this.petakPocetak = petakPocetak;
	}

	public LocalTime getPetakKraj() {
		return petakKraj;
	}

	public void setPetakKraj(LocalTime petakKraj) {
		this.petakKraj = petakKraj;
	}
}
