package dao;

import entities.Auto;
import entities.Prenotazione;
import entities.Utente;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PrenotazioneDao {
    List<Prenotazione> elencoPrenotazioni();
    Prenotazione trovaPrenotazioneDaId(int id);
    void inserisciPrenotazione(Date inizio, Date fine, Utente u, Auto a);
}
