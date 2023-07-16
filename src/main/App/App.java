package App;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.Logger;

import com.github.javafaker.Faker;

import Entities.ElementoBibliotecario;
import Entities.Libro;
import Entities.Periodicita;
import Entities.Rivista;
import Entities.Utente;
import Utils.CatalogoDAO;

public class App {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Catalog");
	private static Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		CatalogoDAO cat = new CatalogoDAO(em);
		Faker fake = new Faker();

		////////////////////////////////////////////////////
		
		Utente persona1 = new Utente("012345", fake.name().firstName(), fake.name().lastName(),
				LocalDate.of(1980, 07, 20));
		Utente persona2 = new Utente("098786", fake.name().firstName(), fake.name().lastName(),
				LocalDate.of(1995, 12, 20));

		Libro nuovo = new Libro(1188336666l, fake.book().title(), 2007, 662, fake.book().author(),
				fake.book().genre());
		Libro nuovo1 = new Libro(9876543210L, fake.book().title(), 2007, 662, fake.book().author(),
				fake.book().genre());

		Rivista nuovo3 = new Rivista(1234567892L, fake.book().title(), 1994, 20, Periodicita.SEMESTRALE);
		
		/////////////////////////////////////////////////

		cat.addUser(persona1);
		cat.addUser(persona2);

		cat.addItem(nuovo);
		cat.addItem(nuovo1);
		cat.addItem(nuovo3);

		cat.findByIsbnAndDelete(1188336666l);

		ElementoBibliotecario item = cat.ricercaPerISBN(1188336666l);
		log.info(item);

		List<ElementoBibliotecario> elementiAnno = cat.ricercaPerAnnoPubblicazione(2007);
		log.info(elementiAnno);

		List<ElementoBibliotecario> elementiAutore = cat.ricercaPerAutore(fake.book().author());
		log.info(elementiAutore);

		List<ElementoBibliotecario> elementiTitolo = cat.ricercaPerTitolo(fake.book().title());
		log.info(elementiTitolo);
		
//////////////////////////////////////////////

		em.close();
		emf.close();
	}
}
