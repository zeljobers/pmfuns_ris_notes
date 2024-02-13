package com.example.demo.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.IzvodjenjeRepository;
import com.example.demo.repository.KartaRepository;
import com.example.demo.repository.MestoRepository;
import com.example.demo.repository.PosetilacRepository;
import com.example.demo.repository.PredstavaRepository;
import com.example.demo.repository.ReziserRepository;
import com.example.demo.repository.ZanrRepository;

import model.Izvodjenje;
import model.Karta;
import model.Mesto;
import model.Predstava;
import model.Reziser;
import model.Zanr;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/predstave")
public class PredstavaController {

	@Autowired
	ZanrRepository zr;

	@Autowired
	ReziserRepository rr;

	@Autowired
	PredstavaRepository pr;

	@Autowired
	IzvodjenjeRepository ir;

	@Autowired
	KartaRepository kr;

	@Autowired
	MestoRepository mr;

	@Autowired
	PosetilacRepository ppr;
	
	@RequestMapping(value="izlistajRezisere", method=RequestMethod.GET) 
	public String izlistajRezisere(HttpServletRequest request) { 
		List<Reziser> reziseri = rr.findAll();
		request.getSession().setAttribute("reziseri", reziseri);
		return "PredstaveRezisera";
	}

	@RequestMapping(value = "getPredstaveRezisera", method = RequestMethod.GET)
	public String getPredstaveRezisera(HttpServletRequest request, Integer idReziser) {
		Reziser r = rr.findById(idReziser).get();
		List<Predstava> predstave = pr.findByReziser(r);
		request.getSession().setAttribute("predstave", predstave);
		return "PredstaveRezisera";
	}

	@RequestMapping(value = "generisiIzvestaj", method = RequestMethod.GET)
	public void generisiIzvestaj(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Predstava> predstave = (List<Predstava>) request.getSession().getAttribute("predstave");

		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(predstave);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/PredstaveRezisera.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		String reziser = "";
		reziser = predstave.get(0).getReziser().getIme() + " " + predstave.get(0).getReziser().getPrezime();
		if (predstave != null && predstave.size() > 0)
			params.put("reziser", reziser);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();

		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=PredstaveRezisera.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}

	@RequestMapping(value = "Rezervisanje", method = RequestMethod.GET)
	public String dovuciMesta(Integer idIzvodjenja, Model m, HttpServletRequest request) {
		List<Mesto> slobodnaMesta = mr.slobodnaMesta(idIzvodjenja);
		request.getSession().setAttribute("mesta", slobodnaMesta);
		Izvodjenje i = ir.findById(idIzvodjenja).get();
		request.getSession().setAttribute("izvodjenje", i);
		return "/Rezervacija";
	}

	@ModelAttribute("karta")
	public Karta napraviKartu() {
		return new Karta();
	}

	@RequestMapping(value = "sacuvajRezervaciju", method = RequestMethod.POST)
	public String sacuvajRezervaciju(@ModelAttribute("karta") Karta k, HttpServletRequest request, Model m) {
		Izvodjenje i = (Izvodjenje) request.getSession().getAttribute("izvodjenje");
		k.setIzvodjenje(i);
		Karta karta = kr.save(k);
		m.addAttribute("karta", karta);
		m.addAttribute("uspesnaRezervacija", true);
		return "/Rezervacija";
	}

	@RequestMapping(value = "pretraziPredstave", method = RequestMethod.GET)
	public String pretraziPredstave(String naziv, Model m) {
		Predstava p = pr.findByNaziv(naziv);
		List<Izvodjenje> izvodjenja = ir.findByPredstava(p);
		m.addAttribute("izvodjenja", izvodjenja);
		m.addAttribute("predstava", naziv);
		return "/PretragaPredstava";
	}

	@RequestMapping(value = "/getPodaci", method = RequestMethod.GET)
	public String getPodaciZaCB(HttpServletRequest request) {
		List<Zanr> zanrovi = zr.findAll();
		List<Reziser> reziseri = rr.findAll();

		request.getSession().setAttribute("reziseri", reziseri);
		request.getSession().setAttribute("zanrovi", zanrovi);

		return "unos/UnosPredstave";
	}

	@RequestMapping(value = "/savePredstava", method = RequestMethod.POST)
	public String savePredstava(String naziv, Integer trajanje, String opis, Integer zanr, Integer reziser, Model m) {
		Reziser r = (rr.findById(reziser)).get();
		Zanr z = (zr.findById(zanr)).get();

		Predstava p = new Predstava();
		p.setNaziv(naziv);
		p.setTrajanje(trajanje);
		p.setOpis(opis);
		p.setZanr(z);
		p.setReziser(r);

		Predstava predstava = pr.save(p);
		m.addAttribute("poruka", "Predstava je uspesno sacuvana. Id predstave je " + predstava.getIdPredstava());

		return "unos/UnosPredstave";
	}
}
