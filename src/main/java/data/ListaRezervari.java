package data;

import java.io.IOException;
import java.sql.*;

public class ListaRezervari
{
    private Connection conn;

    public ListaRezervari(Connection c) {
        conn = c;
    }

    public void insert(String numar_inmatriculare, String numar_telefon, String nume, int id_user) throws SQLException
    {
        PreparedStatement ps = null;

        String sql = "INSERT INTO users(numar_inmatriculare, numar_telefon, nume, id_user) VALUES(?,?,?,?) ";
        ps = conn.prepareStatement(sql);
        ps.setString(1, numar_inmatriculare);
        ps.setString(2, numar_telefon);
        ps.setString(3, nume);
        ps.setInt(4, id_user);
        ps.execute();

        System.out.println("Data has been inserted!");
    }

    public void readAllData()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;


        System.out.println("All Users");
        try
        {
            String sql = "SELECT * FROM Users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String numar_inmatriculare = rs.getString("numar_inmatriculare");
                String numar_telefon = rs.getString("numar_telefon");
                String nume = rs.getString("nume");
                int id_user = rs.getInt("id_user");

                System.out.println("Numar Inmatriculare: " + numar_inmatriculare);
                System.out.println("Numar Telefon: " + numar_telefon);
                System.out.println("Nume: " + nume);
                System.out.println("Id User " + id_user + "\n");
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
    }

    public void remove_data(int id_user) throws IOException, SQLException
    {
        PreparedStatement ps = null;

        String sql = "DELETE FROM Users WHERE id_user = " + id_user;
        ps = conn.prepareStatement(sql);
        ps.execute();
    }

    public void change_data(int id_user, String coloana, String de_modificat) throws IOException, SQLException
    {
        PreparedStatement ps = null;


        String sql = "UPDATE Users SET " + coloana + " = \"" + de_modificat + "\" WHERE id_user = " + id_user;
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }

}
