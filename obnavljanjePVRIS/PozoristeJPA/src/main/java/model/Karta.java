package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Karta database table.
 * 
 */
@Entity
@NamedQuery(name="Karta.findAll", query="SELECT k FROM Karta k")
public class Karta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKarta;

	private double cena;

	private String datumPlacanja;

	private String datumRezervacije;

	//bi-directional many-to-one association to Izvodjenje
	@ManyToOne
	@JoinColumn(name="idIzvodjenje")
	private Izvodjenje izvodjenje;

	//bi-directional many-to-one association to Mesto
	@ManyToOne
	@JoinColumn(name="idMesto")
	private Mesto mesto;

	//bi-directional many-to-one association to Posetilac
	@ManyToOne
	@JoinColumn(name="idPosetilac")
	private Posetilac posetilac;

	public Karta() {
	}

	public int getIdKarta() {
		return this.idKarta;
	}

	public void setIdKarta(int idKarta) {
		this.idKarta = idKarta;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getDatumPlacanja() {
		return this.datumPlacanja;
	}

	public void setDatumPlacanja(String datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public String getDatumRezervacije() {
		return this.datumRezervacije;
	}

	public void setDatumRezervacije(String datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	public Izvodjenje getIzvodjenje() {
		return this.izvodjenje;
	}

	public void setIzvodjenje(Izvodjenje izvodjenje) {
		this.izvodjenje = izvodjenje;
	}

	public Mesto getMesto() {
		return this.mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public Posetilac getPosetilac() {
		return this.posetilac;
	}

	public void setPosetilac(Posetilac posetilac) {
		this.posetilac = posetilac;
	}

}