package servlet;

import dao.PrenotazioneDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "rifiutaPrenotazioneServlet", value = "/rifiutaPrenotazioneServlet")
public class RifiutaPrenotazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        PrenotazioneDaoImpl prenotazioneDao = new PrenotazioneDaoImpl();
        prenotazioneDao.eliminaPrenotazione(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeServlet");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
