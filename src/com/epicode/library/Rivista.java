package com.epicode.library;
import java.time.LocalDate;

public class Rivista extends Biblioteca{

	Periodicità periodicita;

	public Rivista(long isbn, String titolo, LocalDate annoPubblicazione, int numeroPagine, Periodicità periodicita) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.periodicita = periodicita;
	}

	public Periodicità getPeriodicita() {
		return periodicita;
	}
	@Override
	public String toString() {
		return "Rivista ISBN:" + super.getIsbn() + ", titolo: " + super.getTitolo() + ", anno di pubblicazione: " + super.getAnnoPubblicazione()
				+ ", numero di pagine: " + super.getNumeroPagine() + ", periodicità: " + periodicita;
	} 
}
