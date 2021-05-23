package data;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite {

    private Connection conn = null;
    private ListaParcari listaParcari;
    private Rezervari listaRezervari;

     public SQLite() {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:BazaDeDate.db");
        }
        catch (SQLException e )
        {
            e.printStackTrace();
            System.exit(1);
        }
         listaParcari = new ListaParcari(conn);
         listaRezervari = new Rezervari(conn);
    }

    public ListaParcari getListaParcari() {
         return listaParcari;
    }

    public Rezervari getListaRezervari() {
        return listaRezervari;
    }

}
