package servlet;

import dao.AutoDaoImpl;
import dao.PrenotazioneDaoImpl;
import dao.UtenteDaoImpl;
import entities.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "modifyServlet", value = "/modifyServlet")
public class ModifyServlet extends HttpServlet {

    int id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDaoImpl daoUtente = new UtenteDaoImpl();
        id=Integer.parseInt(request.getParameter("id"));
        Utente u = daoUtente.trovaUtenteDaId(id);
        request.setAttribute("utente", u);
        RequestDispatcher dispatcher = request.getRequestDispatcher("modificaCustomer.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtenteDaoImpl daoUtente = new UtenteDaoImpl();
        daoUtente.aggiornaUtente(id, req.getParameter("nome"), req.getParameter("cognome"),
                Boolean.parseBoolean(req.getParameter("tipo")));
        RequestDispatcher dispatcher = req.getRequestDispatcher("confermaModifica.jsp");
        dispatcher.forward(req, resp);
    }
}
