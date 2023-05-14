package com.epicode.library;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class Main{
	
	public static Logger log = LoggerFactory.getLogger(Main.class);
	public static Scanner sc = new Scanner(System.in);
	public static List<Biblioteca> biblioteca = new ArrayList<Biblioteca>();
	public static String path = "Backup/backup.txt";
	public static File file = new File(path);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Esempi Libri
		
		biblioteca.add(new Libro(1, "Il Codice da Vinci", LocalDate.of(2009, 05, 04), 516, "Dan Brown", "Thriller"));
		biblioteca.add(new Libro(2, "Harry Potter e la Pietra Filosofale", LocalDate.of(2003, 05, 23), 320, "Rowling", "Fantasy"));
		biblioteca.add(new Libro(3, "Il Principe", LocalDate.of(1532, 06, 6), 236, "Machiavelli", "Trattato"));
		biblioteca.add(new Libro(4, "1984", LocalDate.of(1949, 6, 8), 328, "George Orwell", "Romanzo distopico"));
		biblioteca.add(new Libro(5, "Il Signore degli Anelli", LocalDate.of(1954, 7, 29), 1216, "J.R.R. Tolkien", "Fantasy"));
		biblioteca.add(new Libro(6, "Le cronache di Narnia", LocalDate.of(1950, 10, 16), 764, "C.S. Lewis", "Fantasy"));
		biblioteca.add(new Libro(7, "La Divina Commedia", LocalDate.of(1321, 9, 14), 798, "Dante Alighieri", "Poema epico"));
		biblioteca.add(new Libro(8, "Il nome della rosa", LocalDate.of(1980, 9, 27), 536, "Umberto Eco", "Giallo storico"));
		biblioteca.add(new Libro(9, "La fattoria degli animali", LocalDate.of(1945, 8, 17), 120, "George Orwell", "Satira politica"));
		biblioteca.add(new Libro(10, "Il fu Mattia Pascal", LocalDate.of(1904, 11, 10), 230, "Luigi Pirandello", "Romanzo"));
		
		//Esempio Rivista 
		
	

		biblioteca.add(new Rivista(20, "Wired Italia", LocalDate.of(2022, 1, 1), 100,Periodicità.MENSILE));
		biblioteca.add(new Rivista(21, "National Geographic Italia", LocalDate.of(2022, 2, 1), 80, Periodicità.MENSILE));
		biblioteca.add(new Rivista(22, "Vanity Fair Italia", LocalDate.of(2022, 2, 7), 60, Periodicità.MENSILE));
		biblioteca.add(new Rivista(23, "Focus Storia", LocalDate.of(2022, 3, 1), 90, Periodicità.SEMESTRALE));
		biblioteca.add(new Rivista(24, "Donna Moderna", LocalDate.of(2022, 3, 7), 70, Periodicità.MENSILE));
		biblioteca.add(new Rivista(25, "Il Mio Papa", LocalDate.of(2022, 4, 1), 50,Periodicità.MENSILE));
		biblioteca.add(new Rivista(26, "La Cucina Italiana", LocalDate.of(2022, 4, 7), 100, Periodicità.SEMESTRALE));
		biblioteca.add(new Rivista(27, "Gioia!", LocalDate.of(2022, 5, 1), 60,Periodicità.MENSILE));
		biblioteca.add(new Rivista(28, "Topolino", LocalDate.of(2022, 5, 7), 40, Periodicità.MENSILE));
		biblioteca.add(new Rivista(29, "GQ Italia", LocalDate.of(2022, 6, 1), 80, Periodicità.SEMESTRALE));

		
		//Gestione menu principale
		
		  int scelta;
		    try {
		        System.out.println("------OpenLibrary-----");
		        do {
		            System.out.println("1 - Elenco Biblioteca");
		            System.out.println("2 - Aggiunta Titolo");
		            System.out.println("3 - Rimuovi Titolo");
		            System.out.println("4 - Ricerca ISBN");
		            System.out.println("5 - Ricerca Anno");
		            System.out.println("6 - Ricerca Autore");
		            System.out.println("7 - Scrittura Backup");
		            System.out.println("8 - Lettura Backup");
		            System.out.println("0 - Termina Esecuzione");
		            scelta = sc.nextInt();
		            switch (scelta) {
		                case 0:
		                    break;
		                case 1:
		                    biblioteca.forEach(e-> System.out.println(e));
		                    break;
		                case 2:
		                    biblioteca.add(aggiungiTitolo());
		                    break;
		                case 3:
		                    eliminaTitolo();
		                    break;
		                case 4:
		                    ricercaIsbn();
		                    break;
		                case 5:
		                    ricercaAnno();
		                    break;
		                case 6:
		                    ricercaAutore();
		                    break;
		                case 7:
		                    scritturaBackup();
		                    break;
		                case 8:
		                    letturaBackup();
		                    break;
		                default:
		                    System.out.println("Inserire un numero da 0 - 8");
		                    break;
		            }
		        } while (scelta != 0);
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (InputMismatchException e) {
		        log.error("Formato non corretto" + e);
		    } catch (Exception e) {
		        log.error("Errore: " + e);
		    }
			
	}
	
	public static Biblioteca aggiungiTitolo() {
	    int type;
	    long isbn;
	    do {
	        System.out.println("Cosa vuoi aggiungere ?");
	        System.out.println("1 - Libro");
	        System.out.println("2 - Rivista");
	        type = sc.nextInt();
	        if (type < 1 || type > 2) {
	            System.out.println("Selezionare un numero compreso tra 1 - 2");
	        }
	    } while (type < 1 || type > 2);

	    System.out.println("Inserisci l'ISBN");
	    while (true) {
	        long query = sc.nextLong();
	        if (biblioteca.stream().noneMatch(e -> e.getIsbn() == query)) {
	            isbn = query;
	            break;
	        }
	        System.out.println("Titolo presente in biblioteca, inserisci un nuovo ISBN");
	    }

	    System.out.println("Titolo");
	    String nome = sc.next();
	    System.out.println("Anno di pubblicazione");
	    int anno = sc.nextInt();
	    System.out.println("Mese di pubblicazione");
	    int mese = sc.nextInt();
	    System.out.println("Giorno di pubblicazione");
	    int giorno = sc.nextInt();
	    System.out.println("Numero Pagine");
	    int pagine = sc.nextInt();

	    if (type == 1) {
	        System.out.println("Autore");
	        String autore = sc.next();
	        System.out.println("Genere");
	        String genere = sc.next();
	        return new Libro(isbn, nome, LocalDate.of(anno, mese, giorno), pagine, autore, genere);
	    } else {
	        System.out.println("Inserisci la periodicità");
	        System.out.println("1 - Settimanale");
	        System.out.println("2 - Mensile");
	        System.out.println("3 - Semestrale");
	        int s = sc.nextInt();
	        Periodicità p = switch (s) {
	            case 1 -> Periodicità.SETTIMANALE;
	            case 2 -> Periodicità.MENSILE;
	            case 3 -> Periodicità.SEMESTRALE;
	            default -> Periodicità.SETTIMANALE;
	        };
	        return new Rivista(isbn, nome, LocalDate.of(anno, mese, giorno), pagine, p);
	    }
	}

	
	public static void eliminaTitolo() {
	    System.out.println("Inserisci ISBN");
	    long isbn = sc.nextLong();
	    Optional<Biblioteca> titoloTrovato = biblioteca.stream()
	        .filter(e -> e.getIsbn() == isbn)
	        .findFirst();
	    if (titoloTrovato.isPresent()) {
	        System.out.println("Titolo eliminato: " + titoloTrovato.get());
	        biblioteca.remove(titoloTrovato.get());
	    } else {
	        System.out.println("Titolo non trovato");
	    }
	}

	
	public static Optional<Biblioteca> ricercaIsbn() {	
	    System.out.println("Inserisci ISBN");
	    long isbn = sc.nextLong();
			
	    Optional<Biblioteca> found = biblioteca.stream()
	            .filter(e -> e.getIsbn() == isbn)
	            .findFirst();
			
	    if (found.isPresent()) {
	        System.out.println(found.get());
	        return found;
	    }
	    else {
	        System.out.println("Titolo non presente");
	        return Optional.empty();
	    }
	}

	
	public static List<Biblioteca> ricercaAnno() {
	    System.out.println("Inserisci anno");
	    int anno = sc.nextInt();
	    List<Biblioteca> list = new ArrayList<>();
	    for (Biblioteca b : biblioteca) {
	        if (b.getAnnoPubblicazione().getYear() == anno) {
	            System.out.println(b);
	            list.add(b);
	        }
	    }
	    if (list.isEmpty()) {
	        System.out.println("Titolo non presente");
	    }
	    return list;
	}

	public static List<Biblioteca> ricercaAutore(){
		
		System.out.println("Inserisci l'autore");
		String autore = sc.next();
		List<Biblioteca> list = biblioteca.stream().filter(e -> e instanceof Libro).filter(e -> ((Libro) e).getAutore().toLowerCase().contains(autore.toLowerCase())).collect(Collectors.toList());
		list.forEach(e -> System.out.println(e));
		if (list.size()==0) {
			System.out.println("Titolo non presente");
		}
		
       return list;
   }
	
	public static void scritturaBackup() throws IOException {
		
		String elemento = "";
		for (Biblioteca e : biblioteca) {
			if(elemento.length() != 0) {
				elemento += "/";
			}
			elemento += e;
		}
		
		FileUtils.writeStringToFile(file, elemento, "UTF-8");
		log.info("Backup Salvato");
	}
	
	public static void letturaBackup() throws IOException {
		List<Biblioteca> backup = new ArrayList<Biblioteca>();
		String elementi = FileUtils.readFileToString(file, "UTF-8");
		String[] arrEl = elementi.split("/"); 
		

		for (int i=0; i<arrEl.length; i++) {
			
			long isbn=0;
			String titolo="";
			LocalDate annoPubblicazione = LocalDate.now();
			int numeroPagine=0;
			
			String autore="";
			String genere="";
			Periodicità periodicita = Periodicità.SETTIMANALE;
			
			String[] fields;
			String values;
			
			fields = arrEl[i].split(",");
			for (int j=0; j<fields.length; j++) {
				values = fields[j].split(":")[1];
				if (j==0) {
					isbn = Long.parseLong(values);
				}
				else if (j==1) {
					titolo= values.substring(1, values.length());
				}
				else if (j==2) {
					annoPubblicazione = LocalDate.of(Integer.parseInt(values.split("-")[0].substring(1,5)), Integer.parseInt(values.split("-")[1]), Integer.parseInt(values.split("-")[2]));
				}
				else if (j==3) {
					numeroPagine = Integer.parseInt(values.substring(1, values.length()));
				}
				 if (fields.length>5) {
					 
					 if (j==4) {
							autore = values.substring(1, values.length());
						}
					 else if (j==5) {
							genere = values.substring(1, values.length());
						}
				 }
				 else if (fields.length<=5 ){
				
				if (j==4) {
					if (values.equals(" SETTIMANALE"))
							{
					periodicita = Periodicità.SETTIMANALE;
					}
					else if (values.equals(" MENSILE"))
					{
						periodicita = Periodicità.MENSILE;
					}
					else if (values.equals(" SEMESTRALE"))
					{
						periodicita = Periodicità.SEMESTRALE;
					}
					
				}

				 }
				
				}
			 if (fields.length<=5) {
				backup.add(new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, periodicita));
			 }
			 else {
				backup.add(new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere));
			 }
		}
		System.out.println("Backup OpenLibrary");
		backup.forEach(e-> System.out.println(e));
	}

}