package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prenotazione")
    private int idPrenotazione;

    @Column(name="data_inizio")
    private Date dataInizio;

    @Column(name="data_fine")
    private Date dataFine;

    @Column(name="confermata")
    private boolean confermata;

    @ManyToOne
    @JoinColumn(name="id_utente", referencedColumnName = "id_utente")
    private Utente utente;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "prenotazione", orphanRemoval = true)
    private Set<Auto> auto = new HashSet<>();

    public Prenotazione() {
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isConfermata() {
        return confermata;
    }

    public void setConfermata(boolean confermata) {
        this.confermata = confermata;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Set<Auto> getAuto() {
        return auto;
    }

    public void setAuto(Set<Auto> auto) {
        this.auto = auto;
    }
}
