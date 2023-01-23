package dao;

import entities.Utente;

import java.util.List;

public interface UtenteDao {
    List<Utente> trovaUtenti();
    Utente trovaUtenteDaId(int id);
    void inserisciOAggiornaUtente(Utente u);
    void eliminaUtente(int id);
}
