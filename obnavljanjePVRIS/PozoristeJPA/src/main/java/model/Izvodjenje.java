package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Izvodjenje database table.
 * 
 */
@Entity
@NamedQuery(name="Izvodjenje.findAll", query="SELECT i FROM Izvodjenje i")
public class Izvodjenje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idIzvodjenje;

	private String datum;

	//bi-directional many-to-one association to Predstava
	@ManyToOne
	@JoinColumn(name="idPredstava")
	private Predstava predstava;

	//bi-directional many-to-one association to Scena
	@ManyToOne
	@JoinColumn(name="idScena")
	private Scena scena;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="izvodjenje")
	private List<Karta> kartas;

	public Izvodjenje() {
	}

	public int getIdIzvodjenje() {
		return this.idIzvodjenje;
	}

	public void setIdIzvodjenje(int idIzvodjenje) {
		this.idIzvodjenje = idIzvodjenje;
	}

	public String getDatum() {
		return this.datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Predstava getPredstava() {
		return this.predstava;
	}

	public void setPredstava(Predstava predstava) {
		this.predstava = predstava;
	}

	public Scena getScena() {
		return this.scena;
	}

	public void setScena(Scena scena) {
		this.scena = scena;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setIzvodjenje(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setIzvodjenje(null);

		return karta;
	}

}