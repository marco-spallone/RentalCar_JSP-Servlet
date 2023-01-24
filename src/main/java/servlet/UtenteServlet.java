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
        List<Utente> utenti = utenteDao.trovaUtenti();
        List<Utente> customers = new ArrayList<>();
        for (Utente u : utenti) {
            if(!u.getTipo()){
                customers.add(u);
            }
        }
        request.setAttribute("listaUt", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeAdmin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u = new Utente();
        if(request.getParameter("id")!=null){
            u.setIdUtente(Integer.parseInt(request.getParameter("id")));
        }
        u.setNome(request.getParameter("nome"));
        u.setCognome(request.getParameter("cognome"));
        u.setUsername(request.getParameter("user"));
        u.setPassword(request.getParameter("pass"));
        u.setTipo(false);
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
        switch (request.getParameter("action")){
            case "modifica":
            case "aggiungi":
                utenteDao.inserisciOAggiornaUtente(u);
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
                    if(utente.getUsername().equals(request.getParameter("user")) && utente.getPassword().equals(request.getParameter("pass"))){
                        login = true;
                        utenteLoggato = utente;
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
                System.out.println("ERRORE");
                break;
        }
        dispatcher.forward(request, response);
    }
}
