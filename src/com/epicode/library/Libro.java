package com.epicode.library;
import java.time.LocalDate;

public class Libro extends Biblioteca{
	
	private String autore;
	private String genere;
	
	public Libro(long isbn, String titolo, LocalDate annoPubblicazione, int numeroPagine, String autore,
			String genere) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}
	public String getAutore() {
		return autore;
	}


	public String getGenere() {
		return genere;
	}
	
	@Override
	public String toString() {
		return "Libro ISBN:" + super.getIsbn() + ", titolo: " + super.getTitolo() + ", anno di pubblicazione: " + super.getAnnoPubblicazione()
				+ ", numero di pagine: " + super.getNumeroPagine() + ", autore: " + autore + ", genere: " + genere;
	} 


}
