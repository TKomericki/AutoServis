package opp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
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
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Usluga_prijava", 
        joinColumns = { @JoinColumn(name = "id_usluge") }, 
        inverseJoinColumns = { @JoinColumn(name = "idKOrisnika"), @JoinColumn(name = "vrijemePrijave" )}
    )
    Set<Prijava> prijave = new HashSet<>();

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
