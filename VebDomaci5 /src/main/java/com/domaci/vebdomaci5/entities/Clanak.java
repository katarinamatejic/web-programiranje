package com.domaci.vebdomaci5.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Clanak {
    private Integer id;
    private String autor;
    private String naslov;
    private String sadrzaj;
    private List<String> komentari;
    private String datum;

    public Clanak() {
        this.komentari = new CopyOnWriteArrayList<>();
        this.datum = LocalDate.now().toString();
    }

    public Clanak(Integer id, String autor, String naslov, String sadrzaj) {
        this();
        this.id = id;
        this.autor = autor;
        this.naslov = naslov;
        this.sadrzaj = sadrzaj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public List<String> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<String> komentari) {
        this.komentari = komentari;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
