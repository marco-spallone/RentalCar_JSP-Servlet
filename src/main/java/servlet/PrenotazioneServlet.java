package servlet;

import dao.AutoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import entities.Auto;
import entities.Prenotazione;
import entities.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "prenotazioneServlet", value = "/prenotazioneServlet")
public class PrenotazioneServlet extends HttpServlet {
    private final PrenotazioneDaoImpl prenotazioneDao = new PrenotazioneDaoImpl();
    private final UtenteDaoImpl utenteDao = new UtenteDaoImpl();
    private final AutoDaoImpl autoDao = new AutoDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Prenotazione> prenotazioni = prenotazioneDao.prenotazioniPerUtente(id);
        List<Auto> auto = autoDao.elencoAuto();
        if(request.getParameter("tipo")==null){
            request.setAttribute("tipo", "0");
        }   else {
            request.setAttribute("tipo", "1");
        }
        request.setAttribute("id", id);
        request.setAttribute("prenotazioni", prenotazioni);
        request.setAttribute("auto", auto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewPrenotazioni.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String inizio = request.getParameter("inizio");
        String fine = request.getParameter("fine");
        switch (request.getParameter("action")){
            case "conferma":
                prenotazioneDao.aggiornaStatoPrenotazione(id, true);
                request.setAttribute("action", "conferma_prenotazione");
                break;
            case "rifiuta":
                prenotazioneDao.eliminaPrenotazione(id);
                request.setAttribute("action", "rifiuta_prenotazione");
                break;
            case "aggiungi":
                Prenotazione p = new Prenotazione();
                p.setUtente(utenteDao.trovaUtenteDaId(id));
                try {
                    Date dataInizio = new SimpleDateFormat("yyyy-MM-dd").parse(inizio);
                    Date dataFine = new SimpleDateFormat("yyyy-MM-dd").parse(fine);
                    p.setDataInizio(dataInizio);
                    p.setDataFine(dataFine);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                p.setConfermata(false);
                p.setAuto(autoDao.trovaAutoDaTarga(request.getParameter("auto")));
                prenotazioneDao.inserisciPrenotazione(p);
                request.setAttribute("action", "prenotazione_inserita");
                request.setAttribute("id", id);
                break;
            case "aggiunta_prenotazione":
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("auto", autoDao.elencoAuto());
                RequestDispatcher dispatcher = request.getRequestDispatcher("aggiungiPrenotazione.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                System.out.println("ERRORE");
                break;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
        dispatcher.forward(request, response);
    }
}
