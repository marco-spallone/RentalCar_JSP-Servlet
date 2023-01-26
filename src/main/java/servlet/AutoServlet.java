package servlet;

import dao.AutoDaoImpl;
import entities.Auto;
import entities.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "autoServlet", value = "/autoServlet")
public class AutoServlet extends HttpServlet {
    private final AutoDaoImpl autoDao = new AutoDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        request.setAttribute("id", request.getParameter("id"));
        switch (request.getParameter("action")){
            case "aggiungi":
            case "modifica":
                if(request.getParameter("idAuto")!=null){
                    request.setAttribute("idAuto", request.getParameter("idAuto"));
                    request.setAttribute("auto", autoDao.trovaAutoDaId(Integer.parseInt(request.getParameter("idAuto"))));
                }
                request.setAttribute("id", request.getParameter("id"));
                dispatcher = request.getRequestDispatcher("formAuto.jsp");
                break;
            default:
                List<Auto> auto = autoDao.elencoAuto();
                request.setAttribute("isAdmin", request.getParameter("isAdmin"));
                request.setAttribute("auto", auto);
                dispatcher = request.getRequestDispatcher("parcoAuto.jsp");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
        request.setAttribute("id", request.getParameter("id"));
        switch (request.getParameter("action")){
            case "formauto":
                Auto a = new Auto();
                if(request.getParameter("idAuto")!=null){
                    a.setIdAuto(Integer.parseInt(request.getParameter("idAuto")));
                }
                a.setMarca(request.getParameter("marca"));
                a.setModello(request.getParameter("modello"));
                a.setAnno(Integer.parseInt(request.getParameter("anno")));
                a.setPrezzo(Double.valueOf(request.getParameter("prezzo")));
                a.setTarga(request.getParameter("targa"));
                autoDao.inserisciOAggiornaAuto(a);
                request.setAttribute("action", "modifica_auto");
                break;
            case "elimina":
                autoDao.eliminaAuto(Integer.parseInt(request.getParameter("idAuto")));
                request.setAttribute("action", "elimina_auto");
                break;
            default:
                request.setAttribute("action", "errore");
                request.setAttribute("servlet", "auto");
                break;
        }
        dispatcher.forward(request, response);
    }
}
