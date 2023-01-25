package servlet;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import entities.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JOptionPane;


@WebServlet(name = "utenteServlet", value = "/utenteServlet")
public class UtenteServlet extends HttpServlet {
    private final UtenteDao utenteDao = new UtenteDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        switch(request.getParameter("action")){
            case "home":
                List<Utente> customers = utenteDao.trovaCustomers();
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("listaUt", customers);
                dispatcher = request.getRequestDispatcher("homeAdmin.jsp");
                break;
            case "profilo":
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("tipo", request.getParameter("tipo"));
                dispatcher = request.getRequestDispatcher("profiloUtente.jsp");
                break;
            case "modifica":
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("customer", request.getParameter("customer"));
                dispatcher = request.getRequestDispatcher("modificaCustomer.jsp");
                break;
            case "aggiungi":
                request.setAttribute("id", request.getParameter("id"));
                dispatcher = request.getRequestDispatcher("aggiungiCustomer.jsp");
                break;
            default:
                request.setAttribute("action", "errore");
                dispatcher = request.getRequestDispatcher("feedback.jsp");
                break;
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = new Utente();
        if(request.getParameter("id")!=null){
            u.setIdUtente(Integer.parseInt(request.getParameter("id")));
            u.setTipo(utenteDao.trovaUtenteDaId(Integer.parseInt(request.getParameter("id"))).getTipo());
        } else u.setTipo(false);
        u.setNome(request.getParameter("nome"));
        u.setCognome(request.getParameter("cognome"));
        u.setUsername(request.getParameter("user"));
        u.setPassword(request.getParameter("pass"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
        request.setAttribute("id", request.getParameter("id"));
        switch (request.getParameter("action")){
            case "modifica":
            case "aggiungi":
                utenteDao.inserisciOAggiornaUtente(u);
                if(request.getParameter("tipo")!=null){
                    request.setAttribute("richiestada", request.getParameter("tipo"));
                }
                request.setAttribute("action", "modifica_utente");
                break;
            case "elimina":
                utenteDao.eliminaUtente(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("action", "elimina_utente");
                break;
            case "login":
                List<Utente> utenti = utenteDao.trovaUtenti();
                Utente utenteLoggato = new Utente();
                boolean login = false;
                for(Utente utente:utenti) {
                    utente = utenteDao.login(request.getParameter("user"),request.getParameter("pass"));
                    if(utente != null){
                        login = true;
                        utenteLoggato = utente;
                    } else {
                        System.out.println("ERRORE!");
                    }
                }
                if(login){
                    request.setAttribute("utente", utenteLoggato);
                    request.setAttribute("action", "redirect");
                    if(utenteLoggato.getTipo()){
                        request.setAttribute("tipo", 1);
                    } else {
                        request.setAttribute("tipo", 0);
                    }
                } else {
                    request.setAttribute("action", "login_failed");
                }
                break;
            default:
                request.setAttribute("action", "errore");
                break;
        }
        dispatcher.forward(request, response);
    }
}
