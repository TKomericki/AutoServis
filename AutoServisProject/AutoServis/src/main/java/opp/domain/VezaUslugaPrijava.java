package opp.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VezaUslugaPrijava {
	@EmbeddedId
	private UslugaPrijavaKey vezaUslugaPrijava;
	
	public UslugaPrijavaKey getVezaUslugaPrijava() {
		return vezaUslugaPrijava;
	}
	public void setVezaUslugaPrijava(UslugaPrijavaKey vezaUslugaPrijava) {
		this.vezaUslugaPrijava = vezaUslugaPrijava;
	}
	
}
