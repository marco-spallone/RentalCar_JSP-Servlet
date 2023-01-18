package entities;

import javax.persistence.*;

@Entity
@Table(name="auto")
public class Auto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_auto")
    private int idAuto;

    @Column(name="marca")
    private String marca;

    @Column(name="modello")
    private String modello;

    @Column(name="anno")
    private int anno;

    @Column(name="prezzo")
    private Double prezzo;

    @Column(name="targa")
    private String targa;

    @ManyToOne
    @JoinColumn(name="id_prenotazione", referencedColumnName = "id_prenotazione")
    private Prenotazione prenotazione;

    public Auto() {
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }
}
