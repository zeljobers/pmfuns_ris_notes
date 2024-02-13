package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Predstava database table.
 * 
 */
@Entity
@NamedQuery(name="Predstava.findAll", query="SELECT p FROM Predstava p")
public class Predstava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPredstava;

	private String naziv;

	private String opis;

	private int trajanje;

	//bi-directional many-to-one association to Izvodjenje
	@OneToMany(mappedBy="predstava")
	private List<Izvodjenje> izvodjenjes;

	//bi-directional many-to-one association to Reziser
	@ManyToOne
	@JoinColumn(name="idReziser")
	private Reziser reziser;

	//bi-directional many-to-one association to Zanr
	@ManyToOne
	@JoinColumn(name="idZanr")
	private Zanr zanr;

	//bi-directional many-to-one association to Uloga
	@OneToMany(mappedBy="predstava")
	private List<Uloga> ulogas;

	public Predstava() {
	}

	public int getIdPredstava() {
		return this.idPredstava;
	}

	public void setIdPredstava(int idPredstava) {
		this.idPredstava = idPredstava;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getTrajanje() {
		return this.trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public List<Izvodjenje> getIzvodjenjes() {
		return this.izvodjenjes;
	}

	public void setIzvodjenjes(List<Izvodjenje> izvodjenjes) {
		this.izvodjenjes = izvodjenjes;
	}

	public Izvodjenje addIzvodjenje(Izvodjenje izvodjenje) {
		getIzvodjenjes().add(izvodjenje);
		izvodjenje.setPredstava(this);

		return izvodjenje;
	}

	public Izvodjenje removeIzvodjenje(Izvodjenje izvodjenje) {
		getIzvodjenjes().remove(izvodjenje);
		izvodjenje.setPredstava(null);

		return izvodjenje;
	}

	public Reziser getReziser() {
		return this.reziser;
	}

	public void setReziser(Reziser reziser) {
		this.reziser = reziser;
	}

	public Zanr getZanr() {
		return this.zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public List<Uloga> getUlogas() {
		return this.ulogas;
	}

	public void setUlogas(List<Uloga> ulogas) {
		this.ulogas = ulogas;
	}

	public Uloga addUloga(Uloga uloga) {
		getUlogas().add(uloga);
		uloga.setPredstava(this);

		return uloga;
	}

	public Uloga removeUloga(Uloga uloga) {
		getUlogas().remove(uloga);
		uloga.setPredstava(null);

		return uloga;
	}

}