package dao;

import entities.Auto;
import entities.Prenotazione;
import entities.Utente;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PrenotazioneDao {
    List<Prenotazione> elencoPrenotazioni();
    List<Prenotazione> prenotazioniPerUtente(int id);
    Prenotazione trovaPrenotazioneDaId(int id);
    List<Prenotazione> filtra(String campo, String valore) throws ParseException;
    void inserisciPrenotazione(Prenotazione p);
    void aggiornaStatoPrenotazione(int id, boolean confermata);
    void eliminaPrenotazione(int id);
}
