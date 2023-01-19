package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="utente")
public class Utente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente")
    private int idUtente;

    @Column(name="tipo")
    private boolean tipo;

    @Column(name="nome")
    private String nome;

    @Column(name="cognome")
    private String cognome;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "utente", orphanRemoval = true)
    private Set<Prenotazione> prenotazione = new HashSet<>();

    public Utente() {

    }

    public Utente(boolean tipo, String nome, String cognome){
        this.tipo=tipo;
        this.nome=nome;
        this.cognome=cognome;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazione;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazione) {
        this.prenotazione = prenotazione;
    }
}
