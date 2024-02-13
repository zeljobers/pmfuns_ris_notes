package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.GlumacManager;
import model.Izvodjenje;

/**
 * Servlet implementation class PretragaPredstojecihIzvodjenja
 */
@WebServlet("/PretragaPredstojecihIzvodjenja")
public class PretragaPredstojecihIzvodjenja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PretragaPredstojecihIzvodjenja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String naziv = request.getParameter("naziv");
		GlumacManager gm = new GlumacManager();
		List<Izvodjenje> izvodjenja = gm.prikazipredstojecaIzvodjenjaPredstave(naziv);
		request.setAttribute("izvodjenja", izvodjenja);
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/PrikazPredstojecihIzvodjenja.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
