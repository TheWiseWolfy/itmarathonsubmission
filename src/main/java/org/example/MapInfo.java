package org.example;

import java.util.ArrayList;
import java.util.List;

import data.*;

public class MapInfo {

    private ArrayList<Parcare> listaParcari;
    private int idCurrent;

    public MapInfo(List<Parcare> list) {
        listaParcari = (ArrayList<Parcare>) list;
    }

    public void setCurrentId(int id) {
        idCurrent = id;
    }

    public ArrayList<Parcare> getLista() {
        return listaParcari;
    }

    public Parcare getCurrentPin() {

        for(Parcare p : listaParcari)
            if(p.getID() == idCurrent)
                return p;

        return null;
    }

}
