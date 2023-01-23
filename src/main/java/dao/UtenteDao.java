package dao;

import entities.Utente;

import java.util.List;

public interface UtenteDao {
    List<Utente> trovaUtenti();
    Utente trovaUtenteDaId(int id);
    void inserisciOAggiornaUtente(Utente u);
    //void aggiornaUtente(int id, String nome, String cognome, boolean tipo);
    void eliminaUtente(int id);
}
