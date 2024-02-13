package manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.Glumac;
import model.Izvodjenje;
import model.Predstava;
import model.Uloga;

public class GlumacManager {
	public Glumac saveGlumac (String ime, String prezime, String jmbg) {
		try {			
			EntityManager em = JPAUtil.getEntityManager();
			Glumac glumac = new Glumac();
			glumac.setIme(ime);
			glumac.setPrezime(prezime);
			glumac.setJmbg(jmbg);
			
			em.getTransaction().begin();
			em.persist(glumac);
			em.getTransaction().commit();
			
			return glumac;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Izvodjenje> prikazipredstojecaIzvodjenjaPredstave(String naziv){
		EntityManager em = JPAUtil.getEntityManager();
		Date danas = new Date();
		List<Izvodjenje> izvodjenja = em.createQuery("select i from Izvodjenje i where i.predstava.naziv like :naziv"
				+ " and i.datum > :d",
				Izvodjenje.class).setParameter("naziv", naziv)
				.setParameter("d", new SimpleDateFormat("yyyy-MM-dd").format(danas)).getResultList();
		return izvodjenja;
	}

	public List<Predstava> getSvePredstave() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Predstava> predstave = em.createQuery("from Predstava p", Predstava.class).getResultList();
		return predstave;
	}

	public Uloga sacuvajUlogu(String naziv, Integer idPredstave, Integer idGlumca) {
		try {
			EntityManager em =JPAUtil.getEntityManager();
			Uloga u = new Uloga();
			Predstava p = em.find(Predstava.class, idPredstave);
			Glumac gl = em.find(Glumac.class, idGlumca);
			u.setNaziv(naziv);
			u.setGlumac(gl);
			u.setPredstava(p);
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			return u;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
