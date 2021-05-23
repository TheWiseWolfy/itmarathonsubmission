package org.example;

import data.ListaParcari;
import data.ListaRezervari;

public class RezervationClass {

    private String numeParcare;
    private String masina;

    public RezervationClass(String parcare, String masina)
    {
        numeParcare = parcare;
        this.masina = masina;

    }

    public String getNumeParcare()
    {
        return numeParcare;
    }

    public String getMasina()
    {
        return masina;
    }
}