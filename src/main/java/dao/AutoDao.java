package dao;

import entities.Auto;

import java.util.List;

public interface AutoDao {
    List<Auto> elencoAuto();
    Auto trovaAutoDaTarga(String targa);
    void inserisciAuto(String marca, String modello, int anno, double prezzo, String targa);
    void aggiornaAuto(int id, String marca, String modello, int anno, double prezzo, String targa);
    void eliminaAuto(String targa);
}
