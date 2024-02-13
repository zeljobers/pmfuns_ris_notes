package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Zanr database table.
 * 
 */
@Entity
@NamedQuery(name="Zanr.findAll", query="SELECT z FROM Zanr z")
public class Zanr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idZanr;

	private String naziv;

	//bi-directional many-to-one association to Predstava
	@OneToMany(mappedBy="zanr")
	private List<Predstava> predstavas;

	public Zanr() {
	}

	public int getIdZanr() {
		return this.idZanr;
	}

	public void setIdZanr(int idZanr) {
		this.idZanr = idZanr;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Predstava> getPredstavas() {
		return this.predstavas;
	}

	public void setPredstavas(List<Predstava> predstavas) {
		this.predstavas = predstavas;
	}

	public Predstava addPredstava(Predstava predstava) {
		getPredstavas().add(predstava);
		predstava.setZanr(this);

		return predstava;
	}

	public Predstava removePredstava(Predstava predstava) {
		getPredstavas().remove(predstava);
		predstava.setZanr(null);

		return predstava;
	}

}