package dao;

import entities.Auto;

import java.util.List;

public interface AutoDao {
    List<Auto> elencoAuto();
    Auto trovaAutoDaTarga(String targa);
    void inserisciOAggiornaAuto(Auto a);
    void eliminaAuto(int id);
}
