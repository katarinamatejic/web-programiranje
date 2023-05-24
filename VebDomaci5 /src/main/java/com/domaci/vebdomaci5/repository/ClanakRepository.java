package com.domaci.vebdomaci5.repository;

import com.domaci.vebdomaci5.entities.Clanak;

import java.util.List;

public interface ClanakRepository {
    public List<Clanak> all();
    public Clanak add( Clanak clanak);
    public Clanak find( Integer id);
    public Clanak addComm( String komentar, Integer id);
}
