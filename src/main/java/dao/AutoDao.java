package dao;

import entities.Auto;

import java.util.List;

public interface AutoDao {
    List<Auto> elencoAuto();
    Auto trovaAutoDaTarga(String targa);
    void inserisciAuto(Auto a);
    void eliminaAuto(String targa);
}
