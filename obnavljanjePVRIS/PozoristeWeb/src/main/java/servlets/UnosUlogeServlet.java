package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.GlumacManager;
import model.Glumac;
import model.Uloga;

/**
 * Servlet implementation class UnosUlogeServlet
 */
@WebServlet("/UnosUlogeServlet")
public class UnosUlogeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnosUlogeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idPredstave = Integer.parseInt(request.getParameter("predstava"));
		String uloga = request.getParameter("uloga");
		
		GlumacManager gm = new GlumacManager();
		Glumac g = (Glumac) request.getSession().getAttribute("glumac");
		Uloga u = gm.sacuvajUlogu(uloga, idPredstave,g.getIdGlumac());
		
		String porukaUloga;
		
		if(u!=null) {
			porukaUloga="Uloga je uspesno sacuvana.";
			request.getSession().setAttribute("uloga", u);
		}
		else {
			porukaUloga="Doslo je do greske. Uloga nije sacuvana.";
		}
		request.setAttribute("porukaUloga", porukaUloga);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/UnosUloge.jsp");
		rd.forward(request, response);
	}

}
