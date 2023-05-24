package com.domaci.vebdomaci5.service;

import com.domaci.vebdomaci5.entities.Clanak;
import com.domaci.vebdomaci5.repository.ClanakRepository;

import javax.inject.Inject;
import java.util.List;

public class ClanakService {
    public ClanakService() {
        System.out.println(this);
    }
    @Inject
    private ClanakRepository repository;

    public List<Clanak> all(){ return this.repository.all(); }
    public Clanak add( Clanak clanak){ return this.repository.add(clanak); }
    public Clanak find( Integer id){ return this.repository.find(id); }
    public Clanak addComm(String komentar, Integer id){ return this.repository.addComm(komentar, id); }
}
