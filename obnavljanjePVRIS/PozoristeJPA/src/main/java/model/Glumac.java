package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Glumac database table.
 * 
 */
@Entity
@NamedQuery(name="Glumac.findAll", query="SELECT g FROM Glumac g")
public class Glumac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGlumac;

	private String ime;

	private String jmbg;

	private String prezime;

	//bi-directional many-to-one association to Uloga
	@OneToMany(mappedBy="glumac")
	private List<Uloga> ulogas;

	public Glumac() {
	}

	public int getIdGlumac() {
		return this.idGlumac;
	}

	public void setIdGlumac(int idGlumac) {
		this.idGlumac = idGlumac;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Uloga> getUlogas() {
		return this.ulogas;
	}

	public void setUlogas(List<Uloga> ulogas) {
		this.ulogas = ulogas;
	}

	public Uloga addUloga(Uloga uloga) {
		getUlogas().add(uloga);
		uloga.setGlumac(this);

		return uloga;
	}

	public Uloga removeUloga(Uloga uloga) {
		getUlogas().remove(uloga);
		uloga.setGlumac(null);

		return uloga;
	}

}