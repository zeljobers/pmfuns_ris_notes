package beans;

import java.util.List;

import manager.GlumacManager;
import model.Predstava;

public class PredstavaBeans {
	private List<Predstava> svePredstave;
	
	public PredstavaBeans() {
		svePredstave = new GlumacManager().getSvePredstave();
	}

	public List<Predstava> getSvePredstave() {
		return svePredstave;
	}

	public void setSvePredstave(List<Predstava> svePredstave) {
		this.svePredstave = svePredstave;
	}
	
}
