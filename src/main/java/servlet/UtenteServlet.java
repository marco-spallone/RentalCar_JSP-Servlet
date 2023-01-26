package servlet;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import entities.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;


@WebServlet(name = "utenteServlet", value = "/utenteServlet")
public class UtenteServlet extends HttpServlet {
    private final UtenteDao utenteDao = new UtenteDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        switch(request.getParameter("action")){
            case "home":
                List<Utente> customers = utenteDao.filtra("isAdmin", "0");
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("listaUt", customers);
                dispatcher = request.getRequestDispatcher("homeAdmin.jsp");
                break;
            case "profilo":
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("utente", utenteDao.trovaUtenteDaId(Integer.parseInt(request.getParameter("id"))));
                request.setAttribute("isAdmin", request.getParameter("isAdmin"));
                dispatcher = request.getRequestDispatcher("profiloUtente.jsp");
                break;
            case "modifica":
                request.setAttribute("id", request.getParameter("id"));
                request.setAttribute("customer", request.getParameter("customer"));
                request.setAttribute("utente", utenteDao.trovaUtenteDaId(Integer.parseInt(request.getParameter("customer"))));
                request.setAttribute("edit", "edit");
                dispatcher = request.getRequestDispatcher("formUtente.jsp");
                break;
            case "aggiungi":
                request.setAttribute("id", request.getParameter("id"));
                dispatcher = request.getRequestDispatcher("formUtente.jsp");
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
        if(request.getParameter("customer")!=null){
            u.setIdUtente(Integer.parseInt(request.getParameter("customer")));
            u.setIsAdmin(utenteDao.trovaUtenteDaId(Integer.parseInt(request.getParameter("customer"))).getIsAdmin());
        } else u.setIsAdmin(false);
        if(request.getParameter("richiestada")!=null && request.getParameter("richiestada").equals("profilo")){
            u.setIdUtente(Integer.parseInt(request.getParameter("id")));
            if(request.getParameter("isAdmin").equals("1")){
                u.setIsAdmin(true);
            }
        }
        u.setNome(request.getParameter("nome"));
        u.setCognome(request.getParameter("cognome"));
        u.setUsername(request.getParameter("user"));
        u.setPassword(request.getParameter("pass"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
        request.setAttribute("id", request.getParameter("id"));
        switch (request.getParameter("action")){
            case "formutente":
                utenteDao.inserisciOAggiornaUtente(u);
                if(request.getParameter("isAdmin")!=null){
                    request.setAttribute("richiestada", request.getParameter("isAdmin"));
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
                    if(utenteLoggato.getIsAdmin()){
                        request.setAttribute("isAdmin", 1);
                    } else {
                        request.setAttribute("isAdmin", 0);
                    }
                } else {
                    request.setAttribute("action", "login_failed");
                }
                break;
            case "filtra":
                String campo = request.getParameter("filtraper");
                String valore = new String();
                if(request.getParameter("filtra")!=null){
                    valore = request.getParameter("filtra");
                }
                List<Utente> filtrata = utenteDao.filtra(campo, valore);
                request.setAttribute("listaUt", filtrata);
                request.setAttribute("action", "home");
                dispatcher = request.getRequestDispatcher("homeAdmin.jsp");
                break;
            default:
                request.setAttribute("action", "errore");
                break;
        }
        dispatcher.forward(request, response);
    }
}
