package servlet;

import dao.PrenotazioneDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "confermaPrenotazioneServlet", value = "/confermaPrenotazioneServlet")
public class ConfermaPrenotazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        PrenotazioneDaoImpl prenotazioneDao = new PrenotazioneDaoImpl();
        prenotazioneDao.aggiornaStatoPrenotazione(id, true);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeServlet");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
