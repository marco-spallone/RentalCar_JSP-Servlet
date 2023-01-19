package dao;

import entities.Utente;

import java.util.List;

public interface UtentiDao {
    List<Utente> trovaUtenti();
    Utente trovaUtenteDaId(int id);
    void aggiornaUtente(int id, String nome, String cognome, boolean tipo);

}
