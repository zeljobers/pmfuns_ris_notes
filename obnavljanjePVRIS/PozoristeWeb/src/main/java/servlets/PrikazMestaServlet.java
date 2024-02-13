package servlets;

import java.io.IOException;
import java.io.PrintWriter;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.PosetilacManager;
import model.Mesto;

/**
 * Servlet implementation class PrikazMestaServlet
 */
@WebServlet("/PrikazMestaServlet")
public class PrikazMestaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazMestaServlet() {
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
		PosetilacManager pm = new PosetilacManager();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date;
		
//			date = sdf.parse("2019-03-08");
		List<Mesto> ms = pm.getMesta("2019-03-08", "Laza i paralaza");
		if (ms != null) {
			PrintWriter pw = response.getWriter();
			for (Mesto m : ms) {
				pw.write(m.getRed() + " " + m.getSekcija() + " " + m.getBroj() + "\n");
			}
			pw.flush();
			pw.close();
		} else {
			System.out.println("nema podataka");
		}
		
	}

}
