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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDaoImpl daoUtente = new UtenteDaoImpl();

        Utente u = daoUtente.trovaUtenteDaId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("utente", u);
        RequestDispatcher dispatcher = request.getRequestDispatcher("modificaCustomer.jsp");
        dispatcher.forward(request, response);
    }
}
