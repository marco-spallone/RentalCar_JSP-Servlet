package servlet;

import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import entities.Prenotazione;
import entities.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "prenotazioniServlet", value = "/prenotazioniServlet")
public class PrenotazioniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrenotazioneDaoImpl prenotazioneDao = new PrenotazioneDaoImpl();
        UtenteDaoImpl utenteDao = new UtenteDaoImpl();
        List<Prenotazione> prenotazioni = prenotazioneDao.prenotazioniPerUtente(Integer.parseInt(request.getParameter("id")));
        int ident = 0;
        Utente u = new Utente();
        if (prenotazioni.size() != 0) {
            ident = prenotazioni.get(prenotazioni.size() - 1).getUtente().getIdUtente();
            u = utenteDao.trovaUtenteDaId(ident);
        }
        request.setAttribute("cognomeUt", u.getCognome());
        request.setAttribute("nomeUt", u.getNome());
        request.setAttribute("prenotazioni", prenotazioni);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewPrenotazioni.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
