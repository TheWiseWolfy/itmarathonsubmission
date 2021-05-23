package data;

import java.sql.*;

public class SQLite {

    private Connection conn = null;
    private ListaParcari listaParcari;
    private ListaUsers listaUsers;

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
        listaUsers = new ListaUsers(conn);
    }

    public ListaParcari getListaParcari() {
         return listaParcari;
    }

    public ListaUsers getListaRezervari() {
        return listaUsers;
    }

}
