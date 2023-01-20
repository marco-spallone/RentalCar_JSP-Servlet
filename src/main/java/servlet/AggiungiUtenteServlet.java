package servlet;

import dao.UtenteDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "aggiungiUtenteServlet", value = "/aggiungiUtenteServlet")
public class AggiungiUtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("aggiungiUtente.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDaoImpl utenteDao = new UtenteDaoImpl();
        utenteDao.inserisciUtente(request.getParameter("nome"), request.getParameter("cognome"),false);
        RequestDispatcher dispatcher = request.getRequestDispatcher("confermaAggiunta.jsp");
        dispatcher.forward(request, response);
    }
}
