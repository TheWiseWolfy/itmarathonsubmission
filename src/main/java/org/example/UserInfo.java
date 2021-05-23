package org.example;

import java.util.ArrayList;

public class UserInfo {

    private String name;
    private String tel;
    private ArrayList<RezervationClass> listaRezervari = new ArrayList<>();
    private int index;
    private static int count = 0;

    public UserInfo() {
        name = "";
        tel = "";
        index = count++;
    }

    public ArrayList<RezervationClass> getListaRezervari() {
        return listaRezervari;
    }

    public boolean invalid() {
        return tel.equals("") || name.equals("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIndex() {
        return index;
    }
}
