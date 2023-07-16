package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("Book")
public class Libro extends ElementoBibliotecario {
	private String autore;
	private String genere;

	public Libro(Long isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Libro [autore=" + autore + ", genere=" + genere + ", getIsbn()=" + getIsbn() + ", getTitolo()="
				+ getTitolo() + ", getAnnoPubblicazione()=" + getAnnoPubblicazione() + ", getNumeroPagine()="
				+ getNumeroPagine() + "]";
	}

}
