package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.PosetilacManager;
import model.Izvodjenje;

/**
 * Servlet implementation class PrikazIzvodjenjaServlet
 */
@WebServlet("/PrikazIzvodjenjaServlet")
public class PrikazIzvodjenjaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public PrikazIzvodjenjaServlet() {
        // TODO Auto-generated constructor stub
    }


    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
//  3. Kreirati servlet, koji poziva metodu manager-a za
//  dobavljanje izvođenja predstave po nazivu (sa prethodnih
//  vežbi) – datum izvođenja i naziv scene ispisati preko
//  PrintWriter-a, testirati iz Postman-a za predstavu Laza i
//  paralaza
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String imePredstave = request.getParameter("imePredstave");
		
		PrintWriter pw = response.getWriter();
		PosetilacManager pm = new PosetilacManager();
		List<Izvodjenje> izs = pm.getIzvodjenja(imePredstave);
		for (Izvodjenje izvodjenje : izs) {
			pw.write(izvodjenje.getDatum() + " " + izvodjenje.getScena().getNaziv());
		}
		pw.flush();
		pw.close();
		
	}

}
