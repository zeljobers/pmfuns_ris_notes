package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Uloga database table.
 * 
 */
@Entity
@NamedQuery(name="Uloga.findAll", query="SELECT u FROM Uloga u")
public class Uloga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUloga;

	private String naziv;

	//bi-directional many-to-one association to Glumac
	@ManyToOne
	@JoinColumn(name="idGlumac")
	private Glumac glumac;

	//bi-directional many-to-one association to Predstava
	@ManyToOne
	@JoinColumn(name="idPredstava")
	private Predstava predstava;

	public Uloga() {
	}

	public int getIdUloga() {
		return this.idUloga;
	}

	public void setIdUloga(int idUloga) {
		this.idUloga = idUloga;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Glumac getGlumac() {
		return this.glumac;
	}

	public void setGlumac(Glumac glumac) {
		this.glumac = glumac;
	}

	public Predstava getPredstava() {
		return this.predstava;
	}

	public void setPredstava(Predstava predstava) {
		this.predstava = predstava;
	}

}