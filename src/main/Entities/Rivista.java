package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("Magazine")
public class Rivista extends ElementoBibliotecario {
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	public Rivista(Long isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "Rivista [periodicita=" + periodicita + ", getIsbn()=" + getIsbn() + ", getTitolo()=" + getTitolo()
				+ ", getAnnoPubblicazione()=" + getAnnoPubblicazione() + ", getNumeroPagine()=" + getNumeroPagine()
				+ "]";
	}

}
