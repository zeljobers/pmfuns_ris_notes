package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.PosetilacManager;
import model.Mesto;

/**
 * Servlet implementation class PrikazSlobodnihMesta
 */
@WebServlet("/PrikazSlobodnihMesta")
public class PrikazSlobodnihMesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazSlobodnihMesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PosetilacManager pm = new PosetilacManager();
			Integer idIzvodjenje = Integer.parseInt(request.getParameter("idIzvodjenje"));
			List<Mesto> mesta = pm.getSlobodnaMesta(idIzvodjenje);
			request.getSession().setAttribute("idIzvodjenje", idIzvodjenje);
			request.setAttribute("mesta", mesta);
			
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Rezervacija.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
