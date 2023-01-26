package dao;

import entities.Utente;

import java.util.List;

public interface UtenteDao {
    List<Utente> trovaUtenti();
    Utente trovaUtenteDaId(int id);
    List<Utente> filtra(String campo, String valore);
    void inserisciOAggiornaUtente(Utente u);
    Utente login(String username, String password);
    void eliminaUtente(int id);
}
