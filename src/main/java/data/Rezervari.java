package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Rezervari
{
    private Connection conn;

    public Rezervari(Connection c) {
        conn = c;
    }

    public void insert(String name, String plate, int id) throws
    SQLException
    {
        PreparedStatement ps = null;

        String sql = "INSERT INTO Rezervations(Name, Plate, id) VALUES(?,?,?) ";
        ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,plate);
        ps.setString(3,"" + id);
        ps.execute();

        System.out.println("Data has been inserted!");
    }

    public List<RezervationClass> readAllData()
    {
        List<RezervationClass> rezervari = new ArrayList<RezervationClass>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            String sql = "SELECT * FROM Rezervations";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String n = rs.getString("Name");
                String p = rs.getString("Plate");
                int i = rs.getInt("id");

                rezervari.add(new RezervationClass(n,p,i));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }

        return rezervari;
    }

    public void change_data(int id, String ce, String de) throws IOException, SQLException
    {
        PreparedStatement ps = null;

        String sql = "UPDATE ParkingLots SET " + ce + " = " + de + " WHERE id = " + id;
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = null;

        String sql = "DELETE FROM Rezervations " + " WHERE id = " + id;
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }
}
