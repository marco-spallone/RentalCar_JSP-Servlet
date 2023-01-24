package dao;

import entities.Utente;

import java.util.List;

public interface UtenteDao {
    List<Utente> trovaUtenti();
    List<Utente> trovaCustomers();
    Utente trovaUtenteDaId(int id);
    void inserisciOAggiornaUtente(Utente u);
    Utente login(String username, String password);
    void eliminaUtente(int id);
}
