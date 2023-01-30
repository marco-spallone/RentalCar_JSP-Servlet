package dao;

import entities.Auto;
import entities.Prenotazione;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface PrenotazioneDao {
    List<Prenotazione> elencoPrenotazioni();
    List<Prenotazione> prenotazioniPerUtente(int id);
    Prenotazione trovaPrenotazioneDaId(int id);
    List<Prenotazione> prenotazioniPerMacchina(int idAuto);
    List<Prenotazione> filtra(String campo, String valore) throws ParseException;
    void inserisciPrenotazione(Prenotazione p);
    void aggiornaPrenotazione(int id, boolean confermata);
    void eliminaPrenotazione(int id);
}
