package com.gpch.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Usluga {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usluge")
	private int idUsluge;
	
	@Column(name = "ime_usluge")
	@NotEmpty(message = "*Unesite ime usluge")
	private String imeUsluge;
	
	@Column(name = "cijena_usluge")
	@NotEmpty
	private int cijena;

	public int getIdUsluge() {
		return idUsluge;
	}

	public String getImeUsluge() {
		return imeUsluge;
	}

	public void setImeUsluge(String imeUsluge) {
		this.imeUsluge = imeUsluge;
	}

	public int getCijena() {
		return cijena;
	}

	public void setCijena(int cijena) {
		this.cijena = cijena;
	}
}
