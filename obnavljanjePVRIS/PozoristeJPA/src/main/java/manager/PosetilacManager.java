package manager;

import java.util.Date;
import java.util.List;
//import java.util.Scanner;

import javax.persistence.EntityManager;

import model.Izvodjenje;
import model.Karta;
import model.Mesto;
import model.Posetilac;
import model.Predstava;

public class PosetilacManager {
	
	public List<Mesto> getMesta(String datumIzvodjenja, String nazivPredstave) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			List<Mesto> ms = em.createQuery("select m from Izvodjenje i inner join i.scena.mestos m"
					+ " where i.predstava.naziv like :ime"
					+ " and i.datum = :datum "
					+ " and not exists (select k from Karta k where k.mesto = m and k.izvodjenje = i) "
					, Mesto.class)
					.setParameter("ime", nazivPredstave).setParameter("datum", datumIzvodjenja)
					.getResultList();
			return ms;
		}catch (Exception e) {
			System.out.println("greska");
			return null;
		}
	}
	
	public List<Mesto> getSlobodnaMesta(Integer idIzvodjenje) {
		EntityManager em = JPAUtil.getEntityManager();
		String upit = "select m from Mesto m inner join m.scena.izvodjenjes i "
				+ "where i.idIzvodjenje=:idI and not exists"
				+ "(select k from Karta k where k.izvodjenje.idIzvodjenje = :idI and "
				+ "k.mesto=m)";
		List<Mesto> mesta = em.createQuery(upit, Mesto.class).setParameter("idI", idIzvodjenje).getResultList();
		return mesta;
	}
	
	public Karta rezervacija(int idPosetioca, int idIzvodjenja, int idMesto) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			// da li je mesto vec zauzeto

			List<Karta> karta = em
					.createQuery("select k from Karta k where k.mesto.idMesto=:idM and k.izvodjenje.idIzvodjenje=:idI",
							Karta.class)
					.setParameter("idM", idMesto).setParameter("idI", idIzvodjenja).getResultList();
			if (!karta.isEmpty())
				return null;

			Karta k = new Karta();
			Posetilac p = em.find(Posetilac.class, idPosetioca);
			Izvodjenje i = em.find(Izvodjenje.class, idIzvodjenja);
			Mesto m = em.find(Mesto.class, idMesto);

			k.setPosetilac(p);
			k.setIzvodjenje(i);
			k.setMesto(m);
			k.setDatumRezervacije(new Date().toString());

			em.getTransaction().begin();
			em.persist(k);
			em.getTransaction().commit();
			return k;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Predstava> getPredstaveZaGlumackoLice(String ime, String prezime) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			List<Predstava> ps = em.createQuery("select u.predstava from  Uloga u where u.glumac.ime like :ime and u.glumac.prezime like :prezime", Predstava.class)
					.setParameter("ime", ime)
					.setParameter("prezime", prezime)
					.getResultList();
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Izvodjenje> getIzvodjenja(String imePredstave) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			List<Izvodjenje> izs = em.createQuery("select iz from Izvodjenje iz where iz.predstava.naziv like :imePredstave", Izvodjenje.class)
					.setParameter("imePredstave", imePredstave)
					.getResultList();
			return izs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Posetilac unosPosetioca(String ime, String prezime) {
		try {	
			EntityManager em = JPAUtil.getEntityManager();
			
			Posetilac p = new Posetilac();
			p.setIme(ime);
			p.setPrezime(prezime);
			
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			
			return p;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Neuspesan unos posetioca!");	
			return null;
		}
	}
	
	public static void main(String[] args) {
		PosetilacManager pm = new PosetilacManager();
//		System.out.println("Uneti ime i prezime :");
//		Scanner input = new Scanner(System.in);
//		
//		String ime = input.next();
//		String prezime = input.next();
//		
//		input.close();
//		Posetilac p = pm.unosPosetioca(ime, prezime);
//		System.out.println("Unet " + p.getIme() + " " + p.getPrezime());
		
//		3. U manager dodati operaciju za dobijanje svih
//		izvođenja predstave sa naslovom Laza i
//		paralaza, testirati (prikazati datum izvođenja i
//		naziv scene)
		
		List<Izvodjenje> izs = pm.getIzvodjenja("Laza i paralaza");
		for (Izvodjenje izvodjenje : izs) {
			System.out.println(izvodjenje.getDatum() + " " + izvodjenje.getScena().getNaziv());
		}
		
//		4. U manager dodati operaciju za dobijanje
//		predstava u kojima glumi Mirjana Miric, testirati
//		(prikazati naziv, trajanje, opis i žanr predstave)
		List<Predstava> ps = pm.getPredstaveZaGlumackoLice("Mirjana", "Miric");
		for (Predstava predstava : ps) {
			System.out.println(predstava.getNaziv() + " " + predstava.getTrajanje() + " " + predstava.getOpis() + " " + predstava.getZanr().getNaziv());
		}
//		5. U manager dodati operaciju za rezervaciju
//		karte za unetog klijenta i proizvoljno izvođenje
//		predstave i proizvoljno mesto (parametri
//		metoda su: id posetioca, id mesta i id
//		izvođenja)
		Karta k = pm.rezervacija(4, 2, 5);
		if (k != null)
			System.out.println("Uspesna rezervacija, broj karte je " + k.getIdKarta());

	}
}
