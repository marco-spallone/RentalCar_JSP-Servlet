package servlet;

import dao.AutoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import entities.Auto;
import entities.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "Servlet", value = "/provaServlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDaoImpl daoUtente = new UtenteDaoImpl();
        AutoDaoImpl daoAuto = new AutoDaoImpl();
        PrenotazioneDaoImpl daoPrenotazione = new PrenotazioneDaoImpl();
        /*List<Utente> list = dao.trovaUtenti();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_utente1.jsp");
        dispatcher.forward(request, response);*/

        /*Utente u = dao.trovaUtenteDaId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("ut", u);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_utente1.jsp");
        dispatcher.forward(request, response);*/

        /*boolean tipo = false;
        if(request.getParameter("tipo").equals("true")){
            tipo=true;
        }
        dao.aggiornaUtente(Integer.parseInt(request.getParameter("id")), request.getParameter("nome"),
                request.getParameter("cognome"), tipo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_utente1.jsp");
        dispatcher.forward(request, response);*/

        /*daoAuto.inserisciAuto(request.getParameter("marca"), request.getParameter("modello"),
                Integer.parseInt(request.getParameter("anno")),
                Double.parseDouble(request.getParameter("prezzo")), request.getParameter("targa"));
        request.setAttribute("targa", request.getParameter("targa"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_utente1.jsp");
        dispatcher.forward(request, response);*/

        /*daoUtente.inserisciUtente(request.getParameter("nome"), request.getParameter("cognome"),
                Boolean.parseBoolean(request.getParameter("tipo")));
        String cog = request.getParameter("cognome");
        request.setAttribute("cognome", cog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_utente1.jsp");
        dispatcher.forward(request, response);*/

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        Date inizio;
        Date fine;
        Utente u = daoUtente.trovaUtenteDaId(1);
        List<Auto> a = daoAuto.elencoAuto();
        try {
            inizio = dateFormat.parse(request.getParameter("inizio"));
            fine = dateFormat.parse(request.getParameter("fine"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        daoPrenotazione.inserisciPrenotazione(inizio, fine, u, a);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_utente1.jsp");
        dispatcher.forward(request, response);
    }
}
