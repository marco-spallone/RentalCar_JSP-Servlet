package servlet;

import dao.AutoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import entities.Auto;
import entities.Prenotazione;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        if (request.getParameter("isAdmin") == null) {
            request.setAttribute("isAdmin", "0");
        } else {
            request.setAttribute("isAdmin", "1");
            request.setAttribute("nav", request.getParameter("isAdmin"));
        }
        if (request.getParameter("myid") != null) {
            request.setAttribute("myid", request.getParameter("myid"));
        } else {
            request.setAttribute("myid", request.getParameter("id"));
        }
        request.setAttribute("id", id);
        request.setAttribute("prenotazioni", prenotazioni);
        request.setAttribute("auto", auto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewPrenotazioni.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String inizio = request.getParameter("inizio");
        String fine = request.getParameter("fine");
        RequestDispatcher dispatcher;
        switch (request.getParameter("action")) {
            case "conferma":
                prenotazioneDao.aggiornaPrenotazione(id, true);
                request.setAttribute("action", "conferma_prenotazione");
                break;
            case "rifiuta":
                prenotazioneDao.eliminaPrenotazione(id);
                request.setAttribute("action", "rifiuta_prenotazione");
                break;
            case "formpren":
                if (request.getParameter("autoSel").equals("no") && inizio != null && fine != null) {
                    try {
                        Date in = new SimpleDateFormat("yyyy-MM-dd").parse(inizio);
                        Date fin = new SimpleDateFormat("yyyy-MM-dd").parse(fine);
                        List<Prenotazione> prenotazioniDate = prenotazioneDao.prenotazioniDate(in, fin);
                        List<Auto> elencoAuto = autoDao.elencoAuto();
                        List<Auto> autoPrenotate = new ArrayList<>();
                        for (Prenotazione x : prenotazioniDate) {
                            autoPrenotate.add(x.getAuto());
                        }
                        Iterator<Auto> iter1 = elencoAuto.iterator();
                        Iterator<Auto> iter2 = autoPrenotate.iterator();
                        while (iter1.hasNext()) {
                            Auto auto1 = iter1.next();
                            while (iter2.hasNext()) {
                                Auto auto2 = iter2.next();
                                if (auto1.getTarga().equals(auto2.getTarga())) {
                                    iter1.remove();
                                    break;
                                }
                            }
                        }
                        request.setAttribute("auto", elencoAuto);
                        request.setAttribute("inizio", inizio);
                        request.setAttribute("fine", fine);
                        request.setAttribute("id", request.getParameter("id"));
                        dispatcher = request.getRequestDispatcher("selectAuto.jsp");
                        dispatcher.forward(request, response);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else if (request.getParameter("autoSel").equals("si")) {
                    Prenotazione p = new Prenotazione();
                    p.setUtente(utenteDao.trovaUtenteDaId(id));
                    if (request.getParameter("idPren") != null) {
                        p.setIdPrenotazione(Integer.parseInt(request.getParameter("idPren")));
                    }
                    try {
                        Date dataInizio = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("inizio"));
                        Date dataFine = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fine"));
                        p.setDataInizio(dataInizio);
                        p.setDataFine(dataFine);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    p.setConfermata(false);
                    if (autoDao.trovaAutoDaTarga(request.getParameter("auto")) != null) {
                        p.setAuto(autoDao.trovaAutoDaTarga(request.getParameter("auto")));
                    } else {
                        request.setAttribute("id", request.getParameter("id"));
                        request.setAttribute("action", "nessuna_auto");
                        dispatcher = request.getRequestDispatcher("feedback.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                    prenotazioneDao.inserisciPrenotazione(p);
                    request.setAttribute("action", "prenotazione_inserita");
                    if (request.getParameter("isAdmin") != null) {
                        request.setAttribute("isAdmin", request.getParameter("isAdmin"));
                        request.setAttribute("nav", request.getParameter("isAdmin"));
                    }
                    request.setAttribute("id", id);
                    break;
                }
            case "aggiunta_prenotazione":
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("auto", autoDao.elencoAuto());
                dispatcher = request.getRequestDispatcher("aggiungiPrenotazione.jsp");
                dispatcher.forward(request, response);
                break;
            case "modifica_prenotazione":
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("idPren", request.getParameter("idPren"));
                request.setAttribute("auto", autoDao.elencoAuto());
                request.setAttribute("isAdmin", request.getParameter("isAdmin"));
                request.setAttribute("prenotazione", prenotazioneDao.trovaPrenotazioneDaId(Integer.parseInt(request.getParameter("idPren"))));
                dispatcher = request.getRequestDispatcher("formPrenotazione.jsp");
                dispatcher.forward(request, response);
                break;
            case "elimina_prenotazione":
                prenotazioneDao.eliminaPrenotazione(Integer.parseInt(request.getParameter("idPren")));
                request.setAttribute("action", "pren_eliminata");
                request.setAttribute("isAdmin", request.getParameter("isAdmin"));
                request.setAttribute("id", request.getParameter("id"));
                break;
            case "filtra":
                String campo = request.getParameter("filtraper");
                String valore = request.getParameter("filtra");
                List<Prenotazione> filtrata;
                if (campo.equals("targa")) {
                    valore = String.valueOf(autoDao.trovaAutoDaTarga(request.getParameter("filtra")).getIdAuto());
                }
                try {
                    filtrata = prenotazioneDao.filtra(campo, valore);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("myid", request.getParameter("id"));
                request.setAttribute("action", "home");
                request.setAttribute("nav", request.getParameter("isAdmin"));
                request.setAttribute("prenotazioni", filtrata);
                dispatcher = request.getRequestDispatcher("viewPrenotazioni.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                request.setAttribute("action", "errore");
                break;
        }
        dispatcher = request.getRequestDispatcher("feedback.jsp");
        dispatcher.forward(request, response);
    }
}
