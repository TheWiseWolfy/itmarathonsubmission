package data;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaParcari
{
    public static Connection connect()
    {
        Connection conn = null;
        //System.out.println("connected to db");

        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:BazaDeDate.db");
        }
        catch (SQLException | ClassNotFoundException throwables)
        {
            throwables.printStackTrace();
            System.exit(1);
        }

        return conn;
    }

    public static void insert(int x, int y, int locuri_libere, int tarif, String nume_parcare, int id_parcare) throws SQLException
    {
        Connection conn = ListaRezervari.connect();
        PreparedStatement ps = null;

        String sql = "INSERT INTO ParkingLots(x, y, locuri_libere, tarif, nume_parcare, id_parcare) VALUES(?,?,?,?,?,?) ";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, x);
        ps.setInt(2, y);
        ps.setInt(3, locuri_libere);
        ps.setInt(4, tarif);
        ps.setString(5, nume_parcare);
        ps.setInt(6, id_parcare);
        ps.execute();

        System.out.println("Data has been inserted!");
    }

    public static List<Parcare> readAllData()
    {
        List<Parcare> parcari = new ArrayList<Parcare>();

        Connection conn = ListaParcari.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println("All Parking Lots");
        try
        {
            String sql = "SELECT * FROM ParkingLots";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int locuri_libere = rs.getInt("locuri_libere");
                int tarif = rs.getInt("tarif");
                String nume_parcare = rs.getString("nume_parcare");
                int id_parcare = rs.getInt("id_parcare");

                parcari.add(new Parcare(x, y, locuri_libere, tarif, nume_parcare, id_parcare));


                /*
                System.out.println("x: " + x);
                System.out.println("y: " + y);
                System.out.println("Locuri Libere: " + locuri_libere);
                System.out.println("Tarif: " + tarif);
                System.out.println("Nume Parcare: " + nume_parcare);
                System.out.println("ID Parcare: " + id_parcare + "\n");
                */
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            try
            {
                rs.close();
                ps.close();
                conn.close();
            }
            catch (SQLException e)
            {
                System.out.println(e.toString());
            }
        }

        return parcari;
    }

    public static void change_data(int x, int y, int de_modificat) throws IOException, SQLException
    {
        Connection conn = ListaRezervari.connect();
        PreparedStatement ps = null;

        String sql = "UPDATE ParkingLots SET locuri_libere = " + de_modificat + " WHERE x = " + x + " AND y = " + y;
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }

}
