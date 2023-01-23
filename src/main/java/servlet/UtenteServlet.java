package servlet;

import dao.UtenteDaoImpl;
import entities.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "utenteServlet", value = "/utenteServlet")
public class UtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDaoImpl utenteDao = new UtenteDaoImpl();
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
        UtenteDaoImpl utenteDao = new UtenteDaoImpl();
        Utente u = new Utente();
        if(request.getParameter("id")!=null){
            u.setIdUtente(Integer.parseInt(request.getParameter("id")));
        }
        u.setNome(request.getParameter("nome"));
        u.setCognome(request.getParameter("cognome"));
        u.setUsername(request.getParameter("user"));
        u.setPassword(request.getParameter("pass"));
        u.setTipo(false);
        if(Objects.equals(request.getParameter("action"), "modifica") || Objects.equals(request.getParameter("action"), "aggiungi")){
            utenteDao.inserisciOAggiornaUtente(u);
            request.setAttribute("action", "modifica_utente");
            RequestDispatcher dispatcher = request.getRequestDispatcher("confermaModifica.jsp");
            dispatcher.forward(request, response);
        } else if(Objects.equals(request.getParameter("action"), "elimina")){
            utenteDao.eliminaUtente(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("action", "elimina_utente");
            RequestDispatcher dispatcher = request.getRequestDispatcher("confermaModifica.jsp");
            dispatcher.forward(request, response);
        }
    }
}
