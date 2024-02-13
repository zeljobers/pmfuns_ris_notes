package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.PosetilacManager;
import model.Karta;

/**
 * Servlet implementation class RezervacijaMesta
 */
@WebServlet("/RezervacijaMesta")
public class RezervacijaMesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RezervacijaMesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idMesto = Integer.parseInt(request.getParameter("idMesto"));
		request.getSession().setAttribute("idMesto", idMesto);
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/UnosRezervacije.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idPosetilac = Integer.parseInt(request.getParameter("idPosetilac"));
		Integer idMesto = (Integer)request.getSession().getAttribute("idMesto");
		Integer idIzvodjenje = (Integer)request.getSession().getAttribute("idIzvodjenje");
		
		PosetilacManager pm = new PosetilacManager();
		Karta k = pm.rezervacija(idPosetilac, idIzvodjenje, idMesto);
		
		String poruka;
		if(k!=null)
			poruka="Karta je uspesno rezervisana. Broj karte je "+k.getIdKarta();
		else
			poruka="Doslo je do greske. Karta nije reyervisana.";
		request.setAttribute("poruka", poruka);
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/UnosRezervacije.jsp");
		rd.forward(request, response);
	}

}
