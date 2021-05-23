package data;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaParcari {

    private Connection conn;

    public ListaParcari(Connection c) {
        conn = c;
    }

    public void insert(int x, int y, int locuri_libere, int tarif, String nume_parcare, int id_parcare) throws SQLException
    {
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

    public List<Parcare> readAllData()
    {
        List<Parcare> parcari = new ArrayList<Parcare>();

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
                float x = rs.getFloat("x");
                float y = rs.getFloat("y");
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

    public void change_data(int id, int de_modificat) throws IOException, SQLException
    {
        PreparedStatement ps = null;

        String sql = "UPDATE ParkingLots SET locuri_libere = " + de_modificat + " WHERE id_parcare = " + id;
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }

}
