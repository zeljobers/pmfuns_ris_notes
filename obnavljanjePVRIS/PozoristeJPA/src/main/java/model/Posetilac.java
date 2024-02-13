package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Posetilac database table.
 * 
 */
@Entity
@NamedQuery(name="Posetilac.findAll", query="SELECT p FROM Posetilac p")
public class Posetilac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPosetilac;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="posetilac")
	private List<Karta> kartas;

	public Posetilac() {
	}

	public int getIdPosetilac() {
		return this.idPosetilac;
	}

	public void setIdPosetilac(int idPosetilac) {
		this.idPosetilac = idPosetilac;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setPosetilac(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setPosetilac(null);

		return karta;
	}

}