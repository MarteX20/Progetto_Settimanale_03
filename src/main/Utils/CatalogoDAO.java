package Utils;

import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import Entities.ElementoBibliotecario;
import Entities.Prestito;
import Entities.Utente;

public class CatalogoDAO {
	private final EntityManager em;
	private static Logger log = Logger.getLogger(CatalogoDAO.class);

	public CatalogoDAO(EntityManager em) {
		this.em = em;
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public void addItem(ElementoBibliotecario e) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(e);

		t.commit();
		log.info("Elemento salvato :)");
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public void addUser(Utente e) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(e);

		t.commit();
		log.info("Utente salvato :)");
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	public void addPrestito(Prestito s) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		// Salvataggio d'istanza di Elemento se non è stata salvata prima
		if (!em.contains(s.getElemento())) {
			em.persist(s.getElemento());
		}

		em.persist(s);

		t.commit();
		log.info("Prestito salvato correttamente :)");
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public void findByIsbnAndDelete(Long isbn) {
		ElementoBibliotecario found = em.find(ElementoBibliotecario.class, isbn);
		if (found != null) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(found);

			t.commit();
			log.info("Elemento eliminato! :)");
		} else {
			log.error("Elemento non trovato :(");
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public ElementoBibliotecario ricercaPerISBN(Long isbn) {
		ElementoBibliotecario elemento = em.find(ElementoBibliotecario.class, isbn);

		if (elemento == null) {
			log.error("Elemento inesistente o cancellato :3");
		}

		return elemento;
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public List<ElementoBibliotecario> ricercaPerAnnoPubblicazione(int anno) {
		TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM Elemento e WHERE e.annoPubblicazione = :anno",
				ElementoBibliotecario.class);
		query.setParameter("anno", anno);
		return query.getResultList();
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public List<ElementoBibliotecario> ricercaPerAutore(String autore) {
		TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM Libro e WHERE e.autore = :autore", ElementoBibliotecario.class);
		query.setParameter("autore", autore);
		List<ElementoBibliotecario> risultati = query.getResultList();

		if (risultati.isEmpty()) {
			log.error("Elemento non trovato :/");
		}

		return risultati;
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public List<ElementoBibliotecario> ricercaPerTitolo(String titolo) {
		TypedQuery<ElementoBibliotecario> query = em.createQuery("SELECT e FROM Elemento e WHERE e.titolo LIKE :titolo",
				ElementoBibliotecario.class);
		query.setParameter("titolo", "%" + titolo + "%");
		List<ElementoBibliotecario> risultati = query.getResultList();

		if (risultati.isEmpty()) {
			log.error("Elemento non trovato");
		}

		return risultati;
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	public List<Prestito> ricercaPrestitiUtente(String numeroTessera) {
		TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tessera",
				Prestito.class);
		query.setParameter("tessera", numeroTessera);
		List<Prestito> risultati = query.getResultList();

		if (risultati.isEmpty()) {
			log.error("Utente non trovato :(");
		}
		return risultati;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
}
