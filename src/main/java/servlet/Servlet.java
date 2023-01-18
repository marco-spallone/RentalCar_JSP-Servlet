package servlet;

import config.HibernateUtil;
import dao.UtentiDaoImpl;
import entities.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/provaServlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtentiDaoImpl dao = new UtentiDaoImpl();
        List<Utente> list = dao.trovaUtenti();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_utente1.jsp");
        dispatcher.forward(request, response);
    }
}
