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
import java.util.Objects;

@WebServlet(name = "prenotazioneServlet", value = "/prenotazioneServlet")
public class PrenotazioneServlet extends HttpServlet {
    private final PrenotazioneDaoImpl prenotazioneDao = new PrenotazioneDaoImpl();
    private final UtenteDaoImpl utenteDao = new UtenteDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prenotazione> prenotazioni = prenotazioneDao.prenotazioniPerUtente(Integer.parseInt(request.getParameter("id")));
        int ident = 0;
        Utente u = new Utente();
        if (prenotazioni.size() != 0) {
            ident = prenotazioni.get(prenotazioni.size() - 1).getUtente().getIdUtente();
            u = utenteDao.trovaUtenteDaId(ident);
        }
        request.setAttribute("prenotazioni", prenotazioni);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewPrenotazioni.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        if(Objects.equals(request.getParameter("action"), "conferma")){

            if(prenotazioneDao.prenotazioniPerUtente(id)!=null){
                prenotazioneDao.aggiornaStatoPrenotazione(id, true);
            }
            request.setAttribute("action", "conferma_prenotazione");
        } else if(Objects.equals(request.getParameter("action"), "rifiuta")){
            prenotazioneDao.eliminaPrenotazione(id);
            request.setAttribute("action", "rifiuta_prenotazione");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
        dispatcher.forward(request, response);
    }
}
