package dao;

import entities.Auto;

import java.util.Date;
import java.util.List;

public interface AutoDao {
    List<Auto> elencoAuto();
    Auto trovaAutoDaTarga(String targa);
    Auto trovaAutoDaId(int id);
    void inserisciOAggiornaAuto(Auto a);
    void eliminaAuto(int id);
}
